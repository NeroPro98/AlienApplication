package zeon.com.chatapplication;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
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
    private UserProfile ObjConnection = new UserProfile();


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
        setContentView(R.layout.activity_user__edit__info);

        image = (CircleImageView)findViewById(R.id.user_circle_Edit);
        name = (EditText) findViewById(R.id.name_user);
        Story = (EditText) findViewById(R.id.user_id_edit);
        edit_button = (Button)findViewById(R.id.Edit_Button);
        camera_button = (Button)findViewById(R.id.Edit_Button_camera);
        SaveImagebutton = (Button)findViewById(R.id.SaveImage);

        edit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //here we check the permission
                if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
                    if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_DENIED){
                        //if the user dont give the app the permission and here we ask the requset
                        String[] permssion = {Manifest.permission.READ_EXTERNAL_STORAGE};
                        //here we ask the user to give the permission
                        requestPermissions(permssion,PERMISSION_CODE);
                    }else{
                        //after user give the app the permission
                        pickImageFromGalleryOfUser();

                    }

                }else {
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

    }
    private void pickImageFromGalleryOfUser(){

        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent,IMAGE_PICK_CODE);
    }

    //to handle the result of runtime exception

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode){
            case PERMISSION_CODE:{
                if (grantResults.length>0 && grantResults[0]==
                        PackageManager.PERMISSION_GRANTED){
                    //Permission was granted
                    pickImageFromGalleryOfUser();
                }else{
                    //Permission was denied
                    Toast.makeText(this,"Permission was denied....",Toast.LENGTH_SHORT).show();

                }
            }
        }
      //  super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode== Activity.RESULT_OK ) {

            if (requestCode == IMAGE_PICK_CODE) {
                //set image to the image view
                MyApplication dataapp = (MyApplication) getApplicationContext();
                image.setImageURI(data.getData());


               // dataapp.setImage();

            } else if (resultCode == REQUEST_CAMERA){
                Bundle bundle = data.getExtras();
                final Bitmap cameraimage = (Bitmap) bundle.get("data");
                image.setImageBitmap(cameraimage);
            }

        }
          //  Bitmap cameraimage = (Bitmap) data.getExtras().get("data");
         //   image.setImageBitmap(cameraimage);


    }

    public void Capture_Photo_Camera(){

        Intent intent2 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent2, REQUEST_CAMERA);
      //  Intent intent2 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //intent2.setType("image/*");
        //if(intent2.resolveActivity(getPackageManager())!=null){
       //     startActivityForResult(intent2,REQUEST_IMAGE_CAPTURE);
        //}

    }


  /*  public void Save_Image(View view) {
        Intent intent = new Intent(getApplicationContext(), Main_Chats_Page.class);
        intent.putExtra("UserImage",R.id.user_circle_Edit);
        startActivity(intent);
    }*/

    public ArrayList<Object> serilaizeToStrings(){

        ArrayList<Object>list = new ArrayList<>();
      //  MyApplication data = (MyApplication)getApplicationContext();
      //  String Email_Curr_User = data.getUser_Email();
        list.add(5);
       // list.add(Email_Curr_User);
     //   list.add(name.getText().toString());
     //   list.add(Story.getText().toString());
        return list;
    }

  public boolean Edit_User_Info(ArrayList<Object> arrayList) throws IOException, ClassNotFoundException {
      ObjConnection.connectToServer();
      ObjConnection.SetupStreams();
      System.out.println("The ArrayList is :"+arrayList);
      ObjConnection.output.writeObject(arrayList);
      ObjConnection.output.flush();
      ObjConnection.input.readObject();
      ArrayList<Object> list = (ArrayList<Object>)ObjConnection.input.readObject();
      System.out.println("List Come To User Edit Info:"+list);
      boolean res = ObjConnection.handleReceivedRequest(list);

      Log.d("Edit_User_Info res:","resa:"+res);
      if(!res){

//            Toast.makeText(getApplicationContext(),"Email not Exist",Toast.LENGTH_SHORT).show();
            ObjConnection.CloseCrap();
          return false;

      }else {
//            Toast.makeText(getApplicationContext(),"Welcome...",Toast.LENGTH_SHORT).show();
      //    MyApplication data = (MyApplication) getApplicationContext();
        //  data.isSignedIn();
          // data.setUser(() list.get(2));
            ObjConnection.CloseCrap();
          return true;
      }
  }


    public boolean Check_Edit() throws InterruptedException, IOException, ClassNotFoundException {
        boolean res = Edit_User_Info(serilaizeToStrings());
        System.out.println("Check_Edit res :"+res);
        return res;
    }

    public void Save_Image(View view) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                boolean bool3 = false;
                try {
                    bool3 = Check_Edit();
                    System.out.println("bool3:"+bool3);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(bool3) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),"Success Edit",Toast.LENGTH_SHORT).show();

                        }
                    });

                    Intent intent = new Intent(getApplicationContext(), Main_Chats_Page.class);
                    startActivity(intent);}
            }});
        thread.start();
    }
     /*   else{
        runOnUiThread(new Runnable() {  //Don't work
            @Override
            public void run() {
               Toast.makeText(getApplicationContext(),"Faild Edit",Toast.LENGTH_SHORT).show();
            }
        });*/
    }

