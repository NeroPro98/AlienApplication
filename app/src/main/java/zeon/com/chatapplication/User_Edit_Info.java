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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import de.hdodenhof.circleimageview.CircleImageView;
import zeon.com.chatapplication.Activity.Main_Chats_Page;

public class User_Edit_Info extends AppCompatActivity {

    private CircleImageView image;
    private TextView name;
    private TextView email;
    private Button edit_button;
    private Button camera_button;
    private Button SaveImagebutton;


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
        name = (TextView)findViewById(R.id.name_user);
        email = (TextView)findViewById(R.id.user_id_edit);
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
}
