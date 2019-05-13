package zeon.com.chatapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.CountDownTimer;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
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
import zeon.com.chatapplication.Model.request;
import zeon.com.chatapplication.Model.requestType;

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
   // private String path = Environment.getExternalStorageDirectory().getPath()+"/Android/zeon.com.chatapplication";



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

    public void Return_To_Rigester(final View v) throws IOException, InterruptedException, ClassNotFoundException {
        boolean bool_email = Check_Email();
        boolean bool_pass = Check_Pass();
        boolean bool_name = Check_Name();
        if(bool_email && bool_pass && bool_name) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    boolean res = false;

                    try {
                        res = Save_In_List();

                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Log.d("res","res answer"+res);
                    if (res) {

                        runOnUiThread(new Runnable() {  //Thread that handler with UI
                            @Override
                            public void run() {
                                Toast.makeText(getApplicationContext(), "Success Register", Toast.LENGTH_SHORT).show();
                               // Intent intent = new Intent(getApplicationContext(), Register.class);
                               // startActivity(intent);
                            }
                        });
                        toSignInPage(v);
                    } else
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getApplicationContext(), "The Email Is already taken", Toast.LENGTH_SHORT).show();

                            }
                        });
                }
            });
         thread.start();
        }

    }

    public boolean Save_In_List() throws IOException, ClassNotFoundException, InterruptedException {

        //Random random = new Random();
       // int id = random.nextInt(100);
        Log.i("Called", "Save_In_List: ");
        String Firstname = firstName.getText().toString();
        String Middlename = middleName.getText().toString();
        String Finalname = finalName.getText().toString();
        String email = regEmail.getText().toString();
        String Password = regPass.getText().toString();

        newUser.setUserName(firstName.getText().toString() + " " + middleName.getText().toString() + " " + finalName.getText().toString());
        newUser.setEmail(regEmail.getText().toString());
        newUser.setPassword(regPass.getText().toString());

        System.out.println(newUser.getEmail()+""+newUser.getPassword()+newUser.getUserName()+""+ "Save_In_List: ");

        final boolean[] res = {true};

   //     Thread thread = new Thread(new Runnable() {
    //        @Override
      //      public synchronized void run() {
                try {
                    res[0] = checkIfAvailable();
                    if (res[0]) {
                        createPrivateFolder();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
          //  }
        //    });
        //thread.start();
        return res[0];
    }
    public ArrayList<Object> serilaizeToStrings()
    {
        ArrayList<Object> arrayList = new ArrayList<>();

        arrayList.add(0);
        arrayList.add(newUser.getUserName());
        arrayList.add(newUser.getEmail());
        arrayList.add(newUser.getPassword());
        arrayList.add(newUser.getBirthDate());
        arrayList.add(newUser.getJoinDate());
        return arrayList;
    }
    public boolean checkIfAvailable() throws InterruptedException, IOException, ClassNotFoundException {
        boolean res = sendRequest(serilaizeToStrings());
        return res;
    }

    public boolean sendRequest(ArrayList<Object> arrayList) throws IOException, InterruptedException, ClassNotFoundException {
        newUser.connectToServer();
        newUser.SetupStreams();
        newUser.output.writeObject(arrayList);
        newUser.output.flush();
        newUser.input.readObject();   ////////
        ArrayList<Object> list = (ArrayList<Object>) newUser.input.readObject();//////////
        boolean res = newUser.handleReceivedRequest(list);
        if(res)
            newUser.setUserId((String)arrayList.get(2));
        return res;
    }

    private void createPrivateFolder()
    {
        String filename = "myfileChat";
        FileOutputStream outputStream;
        MyApplication data = (MyApplication)getApplicationContext();
        File file = new File(getFilesDir(), filename);
        Log.i(file.getAbsolutePath()+"", "createPrivateFolder: ");
        try {
            outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
            ObjectOutputStream objectoutputStream = new ObjectOutputStream(outputStream);
            objectoutputStream.writeObject(newUser);
            objectoutputStream.flush();
            objectoutputStream.close();
            outputStream.close();
            data.setUser(newUser);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
