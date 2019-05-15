package zeon.com.chatapplication;

import android.content.Intent;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class welcom_page extends AppCompatActivity implements GestureDetector.OnGestureListener,GestureDetector.OnDoubleTapListener{

    GestureDetectorCompat mGestureDetectorCompat;
    TextView text1;
    TextView text2;
    ImageView image1;
    Animation togo,toin;
    TextView text3;
    TextView text4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcom_page);
        mGestureDetectorCompat = new GestureDetectorCompat(this,this);
        mGestureDetectorCompat.setOnDoubleTapListener(this);

        text1 = (TextView) findViewById(R.id.journey);
        text2 = (TextView)findViewById(R.id.swap_up);
        image1 = (ImageView)findViewById(R.id.Zeon_chat2);
        togo = AnimationUtils.loadAnimation(this,R.anim.togo);
        toin = AnimationUtils.loadAnimation(this,R.anim.toin);
        text3 = (TextView)findViewById(R.id.textView1);
        text4 = (TextView)findViewById(R.id.textView2);

        text1.animate().alpha(1).setDuration(3000);
        text2.animate().alpha(1).setDuration(3000);
        image1.animate().alpha(1).setDuration(3000);
        text3.animate().alpha(1).setDuration(3000);
        text4.animate().alpha(1).setDuration(3000);


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mGestureDetectorCompat.onTouchEvent(event);

        return super.onTouchEvent(event);
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        Intent intent = new Intent(getApplicationContext(),Register.class);
        startActivity(intent);
        overridePendingTransition(R.anim.toin,R.anim.togo);

        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }
}
