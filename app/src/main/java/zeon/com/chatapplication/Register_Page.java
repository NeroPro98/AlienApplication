package zeon.com.chatapplication;

import android.content.Intent;
import android.os.Build;
import android.os.CountDownTimer;
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
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import in.shadowfax.proswipebutton.ProSwipeButton;

public class Register_Page extends AppCompatActivity {

    EditText First_Name;
    EditText Middle_Name;
    EditText Final_Name;
    EditText Reg_email;
    EditText Reg_Pass;
    EditText Reg_ConPass;
    User_Information User;
    ArrayList<User_Information> User_List;
    private Button Register;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register__page);


        First_Name = (EditText)findViewById(R.id.set_name);
        Middle_Name = (EditText)findViewById(R.id.set_Middlename);
        Final_Name = (EditText)findViewById(R.id.set_Middlename);
        Reg_email = (EditText)findViewById(R.id.AddNewEmail_Faild);
        Reg_Pass = (EditText)findViewById(R.id.AddNewPass_Faild);
        Reg_ConPass = (EditText)findViewById(R.id.AddNewPassConfirm_Faild);
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

    public boolean Check_Email(){
        String email = Reg_email.getText().toString();

        if(!email.contains("@")|| !email.contains(".com") || email.length()<6) {

            Toast.makeText(getApplicationContext(), "Please enter your email again", Toast.LENGTH_SHORT).show();
            return false;
        }else{
            return true;
        }
    }

    public boolean Check_Pass(){

        String Pass = Reg_Pass.getText().toString();
        String Conf_Pass = Reg_ConPass.getText().toString();

        if(!Pass.equals(Conf_Pass) || Pass.length()<6){
            Toast.makeText(getApplicationContext(), "There is no match in Password", Toast.LENGTH_SHORT).show();
            return false;
        }else{
            return true;
        }
    }

    public boolean Check_Name(){
        String first = First_Name.getText().toString();
        String middle = Middle_Name.getText().toString();
        String last = Final_Name.getText().toString();

        if(first == "" || middle == "" || last== ""){

            Toast.makeText(getApplicationContext(),"Enter Name Again", Toast.LENGTH_SHORT).show();
            return false;
        }else{
            return true;
        }



    }

    public void Return_To_Rigester(View v){

        boolean bool_email = Check_Email();
        boolean bool_pass = Check_Pass();
        boolean bool_name = Check_Name();
        if(bool_email && bool_pass && bool_name) {
            Save_In_List();
            Toast.makeText(getApplicationContext(), "Success Register", Toast.LENGTH_SHORT).show();
        }

    }

    public void Save_In_List(){

        Random random = new Random();
        int id = random.nextInt(100);
        String Firstname = First_Name.getText().toString();
        String Middlename = Middle_Name.getText().toString();
        String Finalname = Final_Name.getText().toString();
        String email = Reg_email.getText().toString();
        String Password = Reg_Pass.getText().toString();
        User = new User_Information(id,email,Password,Firstname,Middlename,Finalname);
        User_List.add(User);
        Save_In_File(User);
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


}
