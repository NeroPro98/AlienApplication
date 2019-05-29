package zeon.com.chatapplication;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Chat_User_New extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat__user__new);
        String Name = getIntent().getStringExtra("name");
        int userid = getIntent().getIntExtra("userid",0);
        this.setTitle(Name);
    }

    public void Pick_Image_Gallary(View view) {
    }
}
