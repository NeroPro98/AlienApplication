package zeon.com.chatapplication.Games.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Random;

import zeon.com.chatapplication.R;

public class Magic_Ball_Game extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_magic__ball__game);
        final Button roll = (Button)findViewById(R.id.id);
        final ImageView roll_img = (ImageView)findViewById(R.id.Luck);

        final int []images = {
                R.drawable.ball1,
                R.drawable.ball2,
                R.drawable.ball3,
                R.drawable.ball4,
                R.drawable.ball5
        };

        roll.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Random random = new Random();
                                        int num;
                                        num = random.nextInt(4);
                                        roll_img.setImageResource(images[num]);
                                        Log.d("magic-8-ball","the random number is :"+num);

                                    }
                                }

                                );
 }
}
