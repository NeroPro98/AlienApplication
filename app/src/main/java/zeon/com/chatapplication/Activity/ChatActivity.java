package zeon.com.chatapplication.Activity;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import zeon.com.chatapplication.Model.Chat_Model;
import zeon.com.chatapplication.R;

public class ChatActivity extends AppCompatActivity {

    LinearLayout mLayout;
    ImageView sendbtn;
    EditText type;
    ArrayList<Chat_Model> ChatListServer = new ArrayList<>();

    private void MessageFromServer(ArrayList<Chat_Model> msg){

        for(int i =0; i<msg.size();i++){

            LinearLayout linearLayout = new LinearLayout(this);
            linearLayout.setOrientation(LinearLayout.VERTICAL);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(40,40,40,40);
        //linearLayout.setGravity(Gravity.RIGHT);


        if (msg.get(i).getAny()) {
            linearLayout.setBackgroundResource(R.color.Sender_color);
            layoutParams.gravity = Gravity.RIGHT;
        }else{
            linearLayout.setBackgroundResource(R.color.Reciver_color);
            layoutParams.gravity = Gravity.LEFT;
        }


            TextView text = new TextView(this);
            text.setText(msg.get(i).getMessage());
            text.setTextColor(Color.parseColor("#000000"));
            text.setHeight(100);
            text.setTextSize(20f);
            text.setPadding(20,20,20,20);
            linearLayout.addView(text);
            mLayout.addView(linearLayout,layoutParams);

        }
    }

    private void MessageFromClient(String message,String image){

        LinearLayout linear = new LinearLayout(this);
        linear.setOrientation(LinearLayout.VERTICAL);

        linear.setBackgroundResource(R.color.Reciver_color);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(30,30,30,30);
        linear.setLayoutParams(layoutParams);
        linear.setGravity(Gravity.LEFT);
        TextView text = new TextView(this);
        text.setText(message);
        text.setTextColor(Color.parseColor("#000000"));
        text.setTextSize(24f);
        text.setPadding(20,20,20,20);
        linear.addView(text);

        if(!image.isEmpty()){
            ImageView SendImage = new ImageView(this);
            Picasso.with(getApplicationContext()).load(image)
                    .into(SendImage);
            linear.addView(SendImage);

            //SendImage.setLayoutParams(layoutParams);
        }



        mLayout.addView(linear);


    }

    public void HandlerChatServer(){
        ChatListServer.add(new Chat_Model(1,1,"Hi How are you ?",true));
        ChatListServer.add(new Chat_Model(1,1,"I am good",true));
        MessageFromServer(ChatListServer);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat__user__new);

        mLayout = (LinearLayout)findViewById(R.id.linearlayoutforchat);


        //MessageFromServer("Hi How are you ?",true);
        //MessageFromServer("I am good",false);

        HandlerChatServer();


        String Name = getIntent().getStringExtra("name");
        int userid = getIntent().getIntExtra("userid",0);
        this.setTitle(Name);

        sendbtn = (ImageView)findViewById(R.id.sendbutton_new);
        type = (EditText)findViewById(R.id.typetext);

        sendbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //the send photo not work
                MessageFromClient(type.getText().toString(),"https://www.google.com/search?q=photos+for+man&rlz=1C1GCEA_enSY826SY826&tbm=isch&source=iu&ictx=1&fir=b8wFZeKFTP7F0M%253A%252CsiXBgr-E-CQ1BM%252C_&vet=1&usg=AI4_-kQrc1zmGS-7WEAbjsdg51faq7IjJQ&sa=X&ved=2ahUKEwiH5rCaiefhAhVlBGMBHTfdADYQ9QEwCXoECAcQFg&biw=1366&bih=695#");
                type.setText("");
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.chat_menu,menu);

        return super.onCreateOptionsMenu(menu);
    }
}
