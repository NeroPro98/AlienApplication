package zeon.com.chatapplication.Games.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import zeon.com.chatapplication.R;

public class Main1_Destiny extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1__destiny);


    }

    public void start_btn(View v)
    {
        Intent intent = new Intent(Main1_Destiny.this,Main2_Destiny.class);
        startActivity(intent);
    }
}
