package zeon.com.chatapplication;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import zeon.com.chatapplication.InstanceMessage;
import zeon.com.chatapplication.R;

public class ChatListAdapter extends BaseAdapter{

    Activity ChatActivity;
    ArrayList<String>ChatList;
    String UserName;

    public ChatListAdapter(Activity MyActivity,String Name){

        ChatActivity = MyActivity;
        ChatList =new ArrayList<String>();
        UserName = Name;
    }

    static class UserClass{
        TextView AuthorName;
        TextView Body;
        LinearLayout.LayoutParams Params;
    }
    @Override
    public int getCount() {

        return ChatList.size();
    }

    @Override
    public InstanceMessage getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView==null){
            LayoutInflater inflater = (LayoutInflater)ChatActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.activity_chat__msg__row,parent,false);
            UserClass User = new UserClass();
            User.AuthorName = (TextView)convertView.findViewById(R.id.author);
            User.Body = (TextView)convertView.findViewById(R.id.message);
            User.Params = (LinearLayout.LayoutParams)User.AuthorName.getLayoutParams();
            convertView.setTag(User);
        }

        final InstanceMessage message = getItem(position);
        UserClass User = new UserClass();
        String name = message.getAuthor();
        User.AuthorName.setText(name);
        String msg = message.getMessage();
        User.Body.setText(msg);



        return convertView;
    }
}
