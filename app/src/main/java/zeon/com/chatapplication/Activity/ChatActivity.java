package zeon.com.chatapplication.Activity;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import zeon.com.chatapplication.R;

public class ChatActivity extends AppCompatActivity {

    LinearLayout mLayout;

    private void ChatInit(int j){

        for(int i = 0;i<j ; i++) {
            TextView text = new TextView(this);
            text.setText("Hi How are you ?");
            text.setTextColor(Color.parseColor("#000000"));
            text.setHeight(100);
            text.setTextSize(24f);
            mLayout.addView(text);
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat__user__new);

        mLayout = (LinearLayout)findViewById(R.id.linearlayoutforchat);


        ChatInit(10);




        String Name = getIntent().getStringExtra("name");
        int userid = getIntent().getIntExtra("userid",0);
        this.setTitle(Name);
    }
}
