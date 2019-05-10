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
    ImageView Dog;
    ImageView Alien;

    public  ObjectOutputStream output;
    public  ObjectInputStream input;
    private  Socket Connection;
    private String IPString="10.0.2.2";
    private String PortString;
    private UserProfile ObjConnection = new UserProfile();




    // String path = Environment.getExternalStorageDirectory().getPath()+"/Android/zeon.com.chatapplication";

    UserProfile userProfile = new UserProfile();
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


        arrayList.add(1);
        arrayList.add(Password_Text.getText());
        arrayList.add(Email_Text.getText());

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

        return true;
    }
    public boolean sendRequest(ArrayList<Object> arrayList) throws IOException, InterruptedException, ClassNotFoundException {
        userProfile.connectToServer();
        userProfile.SetupStreams();
        userProfile.output.writeObject(arrayList);
        userProfile.output.flush();
        userProfile.input.readObject();
        ArrayList<Object>list= (ArrayList<Object>)input.readObject();
        boolean res = userProfile.handleReceivedRequest(list);
        if(res)
            userProfile.setUserId((String) list.get(2));
        return res;
    }


    public void To_Chat_Page(View v) throws IOException, ClassNotFoundException {

        Log.d("ChatApp","To_Register_Page was called");
        boolean bool1 = Cheack_Password();
        boolean bool2 = Cheack_Email();
        boolean bool3 = Check_Email_Exist();

        if(bool1 && bool2 && bool3) {
            boolean res = signIn(arrayList);
            Intent intent = new Intent(getApplicationContext(), Main_Chats_Page.class);
            startActivity(intent);
        }else {
            Toast.makeText(getApplicationContext(), "Unveiled... Please Enter your password Again", Toast.LENGTH_SHORT).show();
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

    public boolean Check_Email_Exist() throws IOException, ClassNotFoundException {
        ObjConnection.connectToServer();
        ObjConnection.SetupStreams();
        ObjConnection.input.readObject();

        ArrayList<Object> list = (ArrayList<Object>)ObjConnection.input.readObject();
        boolean res = ObjConnection.handleReceivedRequest(list);
        if(!res){

            Toast.makeText(getApplicationContext(),"Email not Exist",Toast.LENGTH_SHORT).show();
            ObjConnection.CloseCrap();
            return false;

        }else {
            Toast.makeText(getApplicationContext(),"Welcome...",Toast.LENGTH_SHORT).show();
            MyApplication data = (MyApplication) getApplicationContext();
            data.isSignedIn();
           // data.setUser(() list.get(2));
            ObjConnection.CloseCrap();
            return true;
        }
    }

    public boolean handleReceivedRequestForReadFile(ArrayList<Object> list)
    {
        int type = (int) list.get(0);
        switch (type)
        {
            case 1://Read User Email File
            {
                return (boolean) list.get(1);
            }
        }
        return (boolean) list.get(1);
    }

    public void connectToServer() throws IOException {
        System.out.println("Connecting to Server");

        Connection = new Socket(IPString,6790);  // here we setup the connection to specific server of IP address to specific port on this server Port:
      //  System.out.println("I am here");
        System.out.println("Connected");
    }

    public void SetupStreams()throws IOException
    {
        output = new ObjectOutputStream(Connection.getOutputStream());
        output.flush();
        input = new ObjectInputStream(Connection.getInputStream());
        output.writeObject(null);
        System.out.println("The Stream Is Ready");

    }

    public void CloseCrap(){
        try {
            output.close();
            input.close();
            Connection.close();

        }catch (IOException e){
            e.printStackTrace();
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
        boolean res = sendRequest(serilaizeToStrings());
        return res;
    }




}
