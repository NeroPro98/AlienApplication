package zeon.com.chatapplication;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.CountDownTimer;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ebanx.swipebtn.OnStateChangeListener;
import com.ebanx.swipebtn.SwipeButton;

public class Background extends AppCompatActivity {

    ImageView Alien_Text;
    ImageView AstroMan;
    TextView tap;
    TextView text1;
    TextView text2;
   // SwipeButton swipe;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_background);

        Alien_Text = (ImageView)findViewById(R.id.Zeon_chat);
        //AstroMan = (ImageView)findViewById(R.id.alien3);
        tap = (TextView)findViewById(R.id.tap_any_where);
        text1 = (TextView)findViewById(R.id.textView1);
        text2 = (TextView)findViewById(R.id.textView2);

        Text_Animation();



        Play_Opener();


    }

    public void Play_Opener(){
        new CountDownTimer(2000,1000){

            @Override
            public void onTick(long millisUntilFinished) {
            }
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onFinish() {


            }
        }.start();
    }

    public void To_Register(View v){

         Log.d("ChatApp","To_Register was called");

                Intent intent = new Intent(getApplicationContext(),Register.class);
                startActivity(intent);

       }

    public void Text_Animation(){

     //   ObjectAnimator anime = new ObjectAnimator.ofInt()
        tap.animate().alpha(1).setDuration(3000);
        text1.animate().alpha(1).setDuration(3000);
        text2.animate().alpha(1).setDuration(3000);
        Alien_Text.animate().alpha(1).setDuration(3000);

    }


}
