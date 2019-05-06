package zeon.com.chatapplication.Games.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Random;

import zeon.com.chatapplication.R;

public class Dice_Game extends AppCompatActivity {

    Button btn;
    ImageView img1;
    ImageView img2;
    private int ImgArray [] = new int [] {
            R.drawable.dice1,
            R.drawable.dice2,
            R.drawable.dice3,
            R.drawable.dice4,
            R.drawable.dice5,
            R.drawable.dice6,

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dice__game);
        btn = (Button)findViewById(R.id.roll_btn);
        img1 = (ImageView)findViewById(R.id.left_img);
        img2 = (ImageView)findViewById(R.id.right_img);
        final Random random = new Random();

        btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                int num1,num2;
                num1 = random.nextInt(5);
                num2 = random.nextInt(5);
                img1.setImageResource(ImgArray[num1]);
                img2.setImageResource(ImgArray[num2]);
                Log.d("Dicee: ","The Button Is Clicked");


            }

        });


    }
}