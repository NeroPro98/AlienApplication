package zeon.com.chatapplication;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.os.CountDownTimer;
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
    SwipeButton swipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_background);

        Alien_Text = (ImageView)findViewById(R.id.Zeon_chat);
        AstroMan = (ImageView)findViewById(R.id.alien3);
       // AstroMan = (ImageView)findViewById(R.id.astroman);
        tap = (TextView)findViewById(R.id.tap_any_where);
        swipe = (SwipeButton)findViewById(R.id.Swip_btn);
        //Translated AstroMan
        AstroMan.setTranslationX(-1000f);
        AstroMan.setTranslationY(+1000f);
        tap.animate().alpha(1).setDuration(5000);

        swipe.setOnStateChangeListener(new OnStateChangeListener() {
            @Override
            public void onStateChange(boolean active) {
                Toast.makeText(getApplicationContext(),"Let's Start",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(),back1.class);
                startActivity(intent);
            }
        });


    }

    public void To_Register(View v){


        new CountDownTimer(3000,1000){

            @Override
            public void onTick(long millisUntilFinished) {

                Log.d("ChatApp","Time is:"+millisUntilFinished);
                 AstroMan.animate()
                       .translationXBy(+1500)
                   .translationYBy(-1500)
                   .setDuration(millisUntilFinished);

            }

            @Override
            public void onFinish() {

                Log.d("ChatApp","To_Register was called");
               // Intent intent = new Intent(getApplicationContext(),Register.class);
                Intent intent = new Intent(getApplicationContext(),back1.class);
                startActivity(intent);

            }
        }.start();


        //      Log.d("ChatApp","To_Register was called");
       //     Intent intent = new Intent(getApplicationContext(),Register.class);
        //    startActivity(intent);
    //    AstroMan.animate().translationYBy(1000).setDuration(3000);
    }

    public void Text_Animation(){

     //   ObjectAnimator anime = new ObjectAnimator.ofInt()



    }


}
