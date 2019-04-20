package zeon.com.chatapplication;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

public class content_main extends AppCompatActivity {

    Toolbar mToolbar;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);

       // mToolbar = (Toolbar)findViewById(R.id.toolbar2);
       // setSupportActionBar(mToolbar);


    }


}
