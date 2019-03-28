package zeon.com.chatapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

public class Chat_Page extends AppCompatActivity {

    protected ListView MyListView;
    protected EditText TypeText;
    protected ImageButton Send_Button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat__page);

        MyListView = (ListView)findViewById(R.id.Chat_List);
        TypeText = (EditText)findViewById(R.id.Message_Type);
        Send_Button = (ImageButton)findViewById(R.id.sendButton);

    }


}
