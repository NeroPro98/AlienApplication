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

public class Background extends AppCompatActivity {

    TextView Alien_Text;
    ImageView AstroMan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_background);

        Alien_Text = (TextView)findViewById(R.id.Zeon_chat);
        AstroMan = (ImageView)findViewById(R.id.astroman);

        //Translated AstroMan
        AstroMan.setTranslationX(-1000f);
        AstroMan.setTranslationY(+1000f);




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
                Intent intent = new Intent(getApplicationContext(),Register.class);
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
