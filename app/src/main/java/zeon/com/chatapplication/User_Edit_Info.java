package zeon.com.chatapplication;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import de.hdodenhof.circleimageview.CircleImageView;

public class User_Edit_Info extends AppCompatActivity {

    private CircleImageView image;
    private TextView name;
    private TextView email;
    private Button edit_button;

    //for pick photo
    private static final int IMAGE_PICK_CODE = 1000;
    private static final int PERMISSION_CODE = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__edit__info);

        image = (CircleImageView)findViewById(R.id.user_circle_Edit);
        name = (TextView)findViewById(R.id.name_user);
        email = (TextView)findViewById(R.id.user_id_edit);
        edit_button = (Button)findViewById(R.id.Edit_Button);

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
                    Toast.makeText(this,"Pwermission was denied....",Toast.LENGTH_SHORT).show();

                }
            }
        }
      //  super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode==RESULT_OK && requestCode == IMAGE_PICK_CODE){
            //set image to the image view
            image.setImageURI(data.getData());
        }
       // super.onActivityResult(requestCode, resultCode, data);
    }
}
