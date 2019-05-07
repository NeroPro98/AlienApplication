package zeon.com.chatapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class NewBackgroundHello extends AppCompatActivity {

    private Animation smalltobig;
    private Animation nothing;
    private Animation button;
    private ImageView Icon1;
    private TextView text1;
    private TextView text2;
    private Button btn;
    private Button PrevButton;

    private TextView[] Dot;
    private LinearLayout LinLay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_background_hello);


            smalltobig = AnimationUtils.loadAnimation(this,R.anim.smalltobig);
            nothing = AnimationUtils.loadAnimation(this,R.anim.nothing);
            button = AnimationUtils.loadAnimation(this,R.anim.button);
            Icon1 = (ImageView)findViewById(R.id.aliengame);
            text1 = (TextView)findViewById(R.id.back5text1);
            text2 = (TextView)findViewById(R.id.back5text2);
            btn = (Button)findViewById(R.id.go5);
            PrevButton = (Button)findViewById(R.id.prev4);

            LinLay = (LinearLayout)findViewById(R.id.linearlayout5);


            Icon1.startAnimation(smalltobig);
            text2.startAnimation(nothing);
            text1.startAnimation(nothing);
            btn.startAnimation(button);

            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(),Register.class);
                    startActivity(intent);
                }
            });

            AddDot();
        }

        public void AddDot() {
            Dot = new TextView[5];

            for (int i = 0; i < Dot.length; i++) {
                Dot[i] = new TextView(this);
                Dot[i].setText(Html.fromHtml("&#8226;"));
                Dot[i].setTextSize((35));
                Dot[i].setTextColor(getResources().getColor(R.color.TransparentColor));
                LinLay.addView(Dot[i]);
            }

            Dot[4].setTextColor(getResources().getColor(R.color.white));

        }

        public void To_back4(View view) {
            Intent intent = new Intent(getApplicationContext(),back4.class);
            startActivity(intent);
        }
    }
