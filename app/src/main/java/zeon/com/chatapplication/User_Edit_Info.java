package zeon.com.chatapplication;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import zeon.com.chatapplication.Activity.Main_Chats_Page;
import zeon.com.chatapplication.Model.UserProfile;

public class User_Edit_Info extends AppCompatActivity {

    private CircleImageView image;
    private EditText name;
    private EditText Story;
    private Button edit_button;
    private Button camera_button;
    private Button SaveImagebutton;
    private Button ReadButton;
    MyApplication data = (MyApplication) MyApplication.getAppContext();
    private UserProfile ObjConnection = data.getUser();
    private ObjectOutputStream WriteToFile;
    private UserProfile userProfile = data.user;
    private ArrayList<Object> User_Info = new ArrayList<>();
    private String File_Name = "user_info";

    //for pick photo
    private static final int IMAGE_PICK_CODE = 1000;
    private static final int PERMISSION_CODE = 1001;
    private static final int REQUEST_IMAGE_CAPTURE = 101;
    private static final int REQUEST_CAMERA = 1;
    private static final int SELECT_FILE = 1;

    private BottomSheetBehavior selectphoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile_new);

        image = (CircleImageView) findViewById(R.id.user_circle_Edit);
        name = (EditText) findViewById(R.id.name_user);
        Story = (EditText) findViewById(R.id.user_id_edit);
        edit_button = (Button) findViewById(R.id.Edit_Button);
        camera_button = (Button) findViewById(R.id.Edit_Button_camera);
        SaveImagebutton = (Button) findViewById(R.id.SaveImage);
        //   ReadButton = (Button)findViewById(R.id.Read_File_Button);
        Read_File_UserInfo();
        edit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //here we check the permission
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                        //if the user dont give the app the permission and here we ask the requset
                        String[] permssion = {Manifest.permission.READ_EXTERNAL_STORAGE};
                        //here we ask the user to give the permission
                        requestPermissions(permssion, PERMISSION_CODE);
                    } else {
                        //after user give the app the permission
                        pickImageFromGalleryOfUser();

                    }

                } else {
                    //here if the os is less than marshmallow
                    pickImageFromGalleryOfUser();

                }
            }
        });

       /* camera_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    //   Capture_Photo_Camera();
            }
        });*/

/*        requestWindowFeature(1);
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }*/


    }

    private void pickImageFromGalleryOfUser() {

        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, IMAGE_PICK_CODE);
    }

    //to handle the result of runtime exception

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode) {
            case PERMISSION_CODE: {
                if (grantResults.length > 0 && grantResults[0] ==
                        PackageManager.PERMISSION_GRANTED) {
                    //Permission was granted
                    pickImageFromGalleryOfUser();
                } else {
                    //Permission was denied
                    Toast.makeText(this, "Permission was denied....", Toast.LENGTH_SHORT).show();

                }
            }
        }
        //  super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {

            if (requestCode == IMAGE_PICK_CODE) {
                //set image to the image view
                MyApplication dataapp = (MyApplication) getApplicationContext();
                image.setImageURI(data.getData());


                // dataapp.setImage();

            } else if (resultCode == REQUEST_CAMERA) {
                Bundle bundle = data.getExtras();
                final Bitmap cameraimage = (Bitmap) bundle.get("data");
                image.setImageBitmap(cameraimage);
            }

        }
        //  Bitmap cameraimage = (Bitmap) data.getExtras().get("data");
        //   image.setImageBitmap(cameraimage);


    }

    public void Capture_Photo_Camera() {

        Intent intent2 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent2, REQUEST_CAMERA);
        //  Intent intent2 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //intent2.setType("image/*");
        //if(intent2.resolveActivity(getPackageManager())!=null){
        //     startActivityForResult(intent2,REQUEST_IMAGE_CAPTURE);
        //}

    }


    public ArrayList<Object> serilaizeToStrings() {

        ArrayList<Object> list = new ArrayList<>();
        String Email_Curr_User = data.getUser_Email();
        list.add(5);
        list.add(Email_Curr_User);
        list.add(name.getText().toString());
        list.add(Story.getText().toString());
        return list;
    }


    public void Save_Image(View view) throws IOException {
        final ArrayList<Object> arrayList = serilaizeToStrings();
        String Name = name.getText().toString();
        String story = Story.getText().toString();
        String Email_User = data.getUser_Email();
        //User_Info.add(Email_User);
        //User_Info.add(Name);
        //User_Info.add(story);
        data.user.setUserName(Name);
        data.user.setStory(story);
        //data.user.setThe_User_List_Info_File_Element(User_Info);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("The arraylist :" + arrayList);
                    ObjConnection.output.writeObject(arrayList);
                    ObjConnection.output.flush();
                    ArrayList<Object> inputlist = (ArrayList<Object>) ObjConnection.input.readObject();
                    System.out.println("The inputList :" + inputlist);
                    ObjConnection.handleReceivedRequest(inputlist);

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

            }
        });
        thread.start();
        UserProfile user = new UserProfile();
        user = data.user.getUserObject();
        File file = new File(getFilesDir(), File_Name);
        if (!file.exists()) {
            file.createNewFile();
        }
        FileOutputStream outputStream;
        try {
            outputStream = openFileOutput(File_Name, Context.MODE_PRIVATE);
            ObjectOutputStream objectoutputStream = new ObjectOutputStream(outputStream);
            objectoutputStream.writeObject(user);
            //WriteToFile = new ObjectOutputStream(openFileOutput(File_Name,MODE_PRIVATE));
            //WriteToFile.writeObject(userProfile);
            objectoutputStream.flush();
            objectoutputStream.close();
            outputStream.close();
            Toast.makeText(getApplicationContext(), "The Info Edit", Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("The File Write Error :" + e.toString());
        }


    }

    public void Read_File_UserInfo() {
        FileInputStream fileInputStream;
        File file = new File(getFilesDir(), File_Name);

        if (file.exists()) {
            try {
                fileInputStream = openFileInput(File_Name);
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                UserProfile user_read = (UserProfile) objectInputStream.readObject();
                System.out.println("userRead:" + user_read);
                name.setText(user_read.getName());
                Story.setText(user_read.getStory());
                Save_UserActivity_Info();
                objectInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
    public void Save_UserActivity_Info() throws IOException {

        UserProfile user = new UserProfile();
        user = data.user.getUserObject();
        File file = new File(data.getFilesDir(), File_Name);
        if (!file.exists()) {
            file.createNewFile();
        }
        FileOutputStream outputStream;
        try {
            outputStream = data.openFileOutput(File_Name, Context.MODE_PRIVATE);
            ObjectOutputStream objectoutputStream = new ObjectOutputStream(outputStream);
            objectoutputStream.writeObject(user);
            objectoutputStream.flush();
            objectoutputStream.close();
            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("The File Write Error :" + e.toString());
        }


    }

}

