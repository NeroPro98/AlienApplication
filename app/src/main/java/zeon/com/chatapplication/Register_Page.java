package zeon.com.chatapplication;

import android.content.Intent;
import android.os.Build;
import android.os.CountDownTimer;
import android.os.Environment;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import in.shadowfax.proswipebutton.ProSwipeButton;
import zeon.com.chatapplication.Model.UserProfile;

public class Register_Page extends AppCompatActivity {

    EditText firstName;
    EditText middleName;
    EditText finalName;
    EditText regEmail;
    EditText regPass;
    EditText conPass;
    User_Information user;
    ArrayList<User_Information> User_List;
    private Button Register;
    private String path;
    private UserProfile newUser = new UserProfile();

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register__page);

        firstName = (EditText)findViewById(R.id.set_name);
        middleName = (EditText)findViewById(R.id.set_Middlename);
        finalName = (EditText)findViewById(R.id.set_Middlename);
        regEmail = (EditText)findViewById(R.id.AddNewEmail_Faild);
        regPass = (EditText)findViewById(R.id.AddNewPass_Faild);
        conPass = (EditText)findViewById(R.id.AddNewPassConfirm_Faild);
        User_List = new ArrayList<User_Information>();
        Register= (Button) findViewById(R.id.To_Register_Page);





    }


   /* public void Ballown_Animation(){
        new CountDownTimer(3000,1000){
            @Override
            public void onTick(long millisUntilFinished) {

            }
            @Override
            public void onFinish() {
                Ballown.animate().translationYBy(-3000).setDuration(4000);

            }
        }.start();
    }*/

    public void toSignInPage(View v){
        Intent intent = new Intent(getApplicationContext(), Register.class);
        intent.putExtra("user info", (Serializable) newUser);
        startActivity(intent);
    }
    public boolean Check_Email(){
        String email = regEmail.getText().toString();

        if(!email.contains("@")|| !email.contains(".com") || email.length()<6) {

            Toast.makeText(getApplicationContext(), "Please enter your email again", Toast.LENGTH_SHORT).show();
            return false;
        }else{
            return true;
        }
    }

    public boolean Check_Pass(){

        String Pass = regPass.getText().toString();
        String Conf_Pass = conPass.getText().toString();

        if(!Pass.equals(Conf_Pass) || Pass.length()<6){
            Toast.makeText(getApplicationContext(), "There is no match in Password", Toast.LENGTH_SHORT).show();
            return false;
        }else{
            return true;
        }
    }

    public boolean Check_Name(){
        String first = firstName.getText().toString();
        String middle = middleName.getText().toString();
        String last = middleName.getText().toString();

        if(first == "" || middle == "" || last== ""){

            Toast.makeText(getApplicationContext(),"Enter Name Again", Toast.LENGTH_SHORT).show();
            return false;
        }else{
            return true;
        }



    }

    public void Return_To_Rigester(View v) throws IOException {
        boolean bool_email = Check_Email();
        boolean bool_pass = Check_Pass();
        boolean bool_name = Check_Name();
        if(bool_email && bool_pass && bool_name) {
            Save_In_List();
            Toast.makeText(getApplicationContext(), "Success Register", Toast.LENGTH_SHORT).show();
            toSignInPage(v);
        }

    }

    public void Save_In_List() throws IOException {

        //Random random = new Random();
       // int id = random.nextInt(100);
        String Firstname = firstName.getText().toString();
        String Middlename = middleName.getText().toString();
        String Finalname = finalName.getText().toString();
        String email = regEmail.getText().toString();
        String Password = regPass.getText().toString();

        newUser.setUserName(firstName.getText().toString() + " " + middleName.getText().toString() + " " + finalName.getText().toString());
        newUser.setEmail(regEmail.getText().toString());
        newUser.setPassword(regPass.getText().toString());



      //  user = new User_Information(id,email,Password,Firstname,Middlename,Finalname);
       // User_List.add(user);
        createPrivateFolder();
        FileOutputStream fin = new FileOutputStream(path);
        ObjectOutputStream ois = new ObjectOutputStream(fin);
        ois.writeObject(newUser);
        ois.flush();
        ois.close();

        //Save_In_File(user);
    }

    public void Save_In_File(User_Information User){
        try {
            File MyFile = new File("G://UserData.txt");
           // FileOutputStream File = openFileOutput("G:\\UserData.txt",MODE_PRIVATE);
            FileOutputStream file = new FileOutputStream(MyFile);
            //OutputStreamWriter FileWriter = new OutputStreamWriter(File);
            ObjectOutputStream UserObject = new ObjectOutputStream(file);
            UserObject.writeObject(User);
            UserObject.flush();
            UserObject.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void createPrivateFolder(){
        path = Environment.getExternalStorageDirectory().getPath()+"/Android/data/"+getPackageName();
        File file = new File(path);
        if(!file.exists())
            file.mkdirs();
    }


}
