package zeon.com.chatapplication;

import android.content.Intent;
import android.os.Build;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    private EditText Password_Text;
    private AutoCompleteTextView Email_Text;
    private Button Sign_Button;
    private Button Register_Button;
    ImageView Dog;
    ImageView Alien;



    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Password_Text = (EditText)findViewById(R.id.Password_Faild);
        Email_Text = (AutoCompleteTextView)findViewById(R.id.Email_Faild);
        Sign_Button = (Button)findViewById(R.id.Sign_Button);
        Register_Button = (Button)findViewById(R.id.Register_Button);
        Dog = (ImageView)findViewById(R.id.dog);
        Alien = (ImageView)findViewById(R.id.alien);

        Alien.animate().alpha(1).setDuration(3000);
        Dog.animate().translationXBy(-1000).setDuration(0);
        Dog_Animate();
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


    public void To_Chat_Page(View v){


        Log.d("ChatApp","To_Register_Page was called");
        boolean bool1 = Cheack_Password();
        boolean bool2 = Cheack_Email();

        if(bool1 && bool2) {
            Intent intent = new Intent(getApplicationContext(), Chat_Page.class);
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









}
