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

public class back2 extends AppCompatActivity {

    private Animation smalltobig;
    private Animation nothing;
    private Animation button;
    private ImageView Icon2;
    private TextView text1;
    private TextView text2;
    private Button btn;
    private Button PrevButton;
    private ImageView image;


    private TextView[] Dot;
    private LinearLayout LinLay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_back2_new);

        smalltobig = AnimationUtils.loadAnimation(this, R.anim.smalltobig);
        nothing = AnimationUtils.loadAnimation(this, R.anim.nothing);
        button = AnimationUtils.loadAnimation(this, R.anim.button);
        Icon2 = (ImageView) findViewById(R.id.icon2);
        text1 = (TextView) findViewById(R.id.back1text1);
        text2 = (TextView) findViewById(R.id.back1text2);
        btn = (Button) findViewById(R.id.go2);
        LinLay = (LinearLayout)findViewById(R.id.linearlayout2);
        PrevButton = (Button)findViewById(R.id.prev1);
        image = (ImageView)findViewById(R.id.alien_ride);
        image.animate().translationX(50).setDuration(2000);

        Icon2.startAnimation(smalltobig);
        text2.startAnimation(nothing);
        text1.startAnimation(nothing);
        btn.startAnimation(button);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), back3.class);
                startActivity(intent);
            }
        });
        AddDot();
    }

    public void AddDot() {
        Dot = new TextView[4];

        for (int i = 0; i < Dot.length; i++) {
            Dot[i] = new TextView(this);
            Dot[i].setText(Html.fromHtml("&#8226;"));
            Dot[i].setTextSize((35));
            Dot[i].setTextColor(getResources().getColor(R.color.TransparentColor));
            LinLay.addView(Dot[i]);
        }

            Dot[1].setTextColor(getResources().getColor(R.color.white));

    }


    public void To_back1(View view) {
        Intent intent = new Intent(getApplicationContext(),back1.class);
        startActivity(intent);
    }
}
