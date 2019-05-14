package zeon.com.chatapplication;

import android.content.Intent;
import android.os.Build;
import android.os.CountDownTimer;
import android.os.Environment;
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
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import zeon.com.chatapplication.Activity.Main_Chats_Page;
import zeon.com.chatapplication.Model.UserProfile;

public class Register extends AppCompatActivity {

    private EditText Password_Text;
    private EditText Email_Text;
    private Button Sign_Button;
    private Button Register_Button;
    public  ObjectInputStream input2;
    private String Come_Email;
    ImageView Dog;
    ImageView Alien;


    private UserProfile ObjConnection = new UserProfile();

    // String path = Environment.getExternalStorageDirectory().getPath()+"/Android/zeon.com.chatapplication";

    private UserProfile userProfile = new UserProfile();
    ArrayList<Object> arrayList = new ArrayList<>();

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Password_Text = (EditText)findViewById(R.id.Password_Faild);
        Email_Text = (EditText)findViewById(R.id.Email_Faild);
        Sign_Button = (Button) findViewById(R.id.btn_cart_signin);
        Register_Button = (Button) findViewById(R.id.btn_cart_signup);

        ////////////////here there is error
       /* MyApplication data = (MyApplication)getApplicationContext();
        userProfile = data.getUser();
        System.out.println(data.signedIn);

        if(data.signedIn)
        {
            Email_Text.setText(userProfile.getEmail());
            Password_Text.setText(userProfile.getPassword());
        }*/


       // arrayList.add(1);
      //  arrayList.add(Password_Text.getText());
       // arrayList.add(Email_Text.getText());

        //  Dog = (ImageView)findViewById(R.id.dog);
       // Alien = (ImageView)findViewById(R.id.alien);

      /*  Alien.animate().alpha(1).setDuration(3000);
        Dog.animate().translationXBy(-1000).setDuration(0);
        Dog_Animate();*/

        Password_Text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String test1 =Password_Text.getText().toString();
                String test2 = "Enter Your Password......";
                if(test1.equals(test2)) {
                    Password_Text.setText("");
                }
            }
        });


        Email_Text.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String test1 =Email_Text.getText().toString();
                String test2 = "Enter Your Email......";
                if(test1.equals(test2)) {
                    Email_Text.setText("");
                }

            }
        });



    }

    public void Dog_Animate(){
        //final Handler handler = new Handler();
        new CountDownTimer(5000,1000){

            @Override
            public void onTick(long millisUntilFinished) {
            }
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onFinish() {

                Dog.animate().translationXBy(2000)
                        .translationYBy(500)
                        .translationYBy(-500)
                        . translationYBy(500)
                        .translationYBy(-500)
                        . translationYBy(500)
                        .translationYBy(-500)
                        .setDuration(5000);

            }
        }.start();

      /*  Runnable run = new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void run() {

             //   Dog.animate().translationXBy(2000).setDuration(4000);
                Dog.animate().translationZBy(3000).setDuration(1000);
                handler.postDelayed(this,1000);
               // Dog.animate().translationZBy(-500).setDuration(1000);
            }

        };
        handler.post(run);*/

    }

    public boolean signIn(ArrayList<Object> list)
    {

        Toast.makeText(this, "Sign in...Welcome", Toast.LENGTH_SHORT).show();
        return true;
    }

    public boolean RejectsignIn(ArrayList<Object> list)
    {

        Toast.makeText(getApplicationContext(), "Unveiled... Please Enter Again", Toast.LENGTH_SHORT).show();
        return true;
    }



    public void To_Chat_Page(View v) throws IOException, ClassNotFoundException {

        Log.d("ChatApp","To_Register_Page was called");
        final boolean bool1 = Cheack_Password();
        final boolean bool2 = Cheack_Email();

        if(bool1 && bool2) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    boolean bool3 = false;

                    try {
                        bool3 = checkIfAvailable();
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
                                signIn(arrayList);

                            }
                        });
                        MyApplication data = (MyApplication)getApplicationContext();
                        data.setUser_Email(Come_Email);
                        System.out.println("Come Email is:"+data.getUser_Email());
                        Intent intent = new Intent(getApplicationContext(), Main_Chats_Page.class);
                        startActivity(intent);}
                    }});
            thread.start();
        }
        else{
            runOnUiThread(new Runnable() {  //Don't work
                @Override
                public void run() {
                    RejectsignIn(arrayList);
                }
            });
        }
    }
    public void To_Register_Page(View v){

        Intent intent = new Intent(getApplicationContext(), Register_Page.class);

        startActivity(intent);
    }



    public boolean Cheack_Password(){

        String pass = Password_Text.getText().toString();
        if (pass.length() < 6) {

            return false;

        } else {
           return true;

        }

    }

    public boolean Cheack_Email(){

        String email = Email_Text.getText().toString();

        if(email.length() < 6 && email.contains("@") && email.contains(".com")){
            return false;
        }else{
            return true;
        }

    }

    public void To_Setting(View v){
        Intent intent = new Intent(getApplicationContext(),SocketInfo.class);
        startActivity(intent);

    }

    public boolean Check_Email_Exist(ArrayList<Object> arrayList) throws IOException, ClassNotFoundException {
        ObjConnection.connectToServer();
        ObjConnection.SetupStreams();
        System.out.println("The ArrayList is :"+arrayList);
        ObjConnection.output.writeObject(arrayList);
        ObjConnection.output.flush();
        ObjConnection.input.readObject();
       // System.out.println("readObject:"+ObjConnection.input.readObject().toString());

        ArrayList<Object> list = (ArrayList<Object>)ObjConnection.input.readObject();
        System.out.println("listlist:"+list);
        Come_Email = (String)list.get(3);

        boolean res = ObjConnection.handleReceivedRequest(list);

        Log.d("resa:","resa:"+res);
        if(!res){

//            Toast.makeText(getApplicationContext(),"Email not Exist",Toast.LENGTH_SHORT).show();
            ObjConnection.CloseCrap();
            return false;

        }else {
//            Toast.makeText(getApplicationContext(),"Welcome...",Toast.LENGTH_SHORT).show();
            MyApplication data = (MyApplication) getApplicationContext();
            data.isSignedIn();
           // data.setUser(() list.get(2));
            ObjConnection.CloseCrap();
            return true;
        }
    }





    public ArrayList<Object> serilaizeToStrings(){

        ArrayList<Object>list = new ArrayList<>();

        list.add(1);
        list.add(Email_Text.getText().toString());
        list.add(Password_Text.getText().toString());
        return list;
    }

    public boolean checkIfAvailable() throws InterruptedException, IOException, ClassNotFoundException {
        boolean res = Check_Email_Exist(serilaizeToStrings());
        System.out.println("res"+res);
        return res;
    }




}
