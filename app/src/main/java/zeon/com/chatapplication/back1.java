package zeon.com.chatapplication;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.ViewPager;
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

public class back1 extends AppCompatActivity {

    private Animation smalltobig;
    private Animation nothing;
    private Animation button;
    private ImageView Icon3;
    private TextView text1;
    private TextView text2;
    private Button btn;
    private Button PrevButton;


    private TextView[] Dot;


    //   private ViewPager SliderPager;
    private LinearLayout LinLay;
    // private SliderAdapter slideradapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_back1);

        smalltobig = AnimationUtils.loadAnimation(this, R.anim.smalltobig);
        nothing = AnimationUtils.loadAnimation(this, R.anim.nothing);
        button = AnimationUtils.loadAnimation(this, R.anim.button);
        Icon3 = (ImageView) findViewById(R.id.icon3);
        text1 = (TextView) findViewById(R.id.text1);
        text2 = (TextView) findViewById(R.id.text2);
        btn = (Button) findViewById(R.id.go);
        PrevButton = (Button)findViewById(R.id.prev0);
        PrevButton.setEnabled(false);
        Icon3.startAnimation(smalltobig);
        text2.startAnimation(nothing);
        text1.startAnimation(nothing);
        btn.startAnimation(button);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), back2.class);
                startActivity(intent);
            }
        });


        //for Slider Adapter
        //SliderPager = (ViewPager)findViewById(R.id.viwer1);
        LinLay = (LinearLayout) findViewById(R.id.linearlayout1);
        //slideradapter = new SliderAdapter(this);
        //SliderPager.setAdapter(slideradapter);

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
            Dot[0].setTextColor(getResources().getColor(R.color.white));

    }

}
