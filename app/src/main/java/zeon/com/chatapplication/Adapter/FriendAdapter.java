package zeon.com.chatapplication.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import zeon.com.chatapplication.Activity.ChatActivity;
import zeon.com.chatapplication.Fragment.Fragment1;
import zeon.com.chatapplication.Model.FriendComp;
import zeon.com.chatapplication.Model.story;
import zeon.com.chatapplication.MyApplication;
import zeon.com.chatapplication.R;

public class FriendAdapter extends BaseAdapter {
    private ArrayList<FriendComp> lists;
    private Context mContext;
    private LayoutInflater mInflater;

    public FriendAdapter(Context context, ArrayList<FriendComp> list) {

        this.mContext = context;
        this.lists = list;
        this.mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return lists.size();
    }

    @Override
    public Object getItem(int position) {
        return lists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return lists.get(position).getId();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final View view = mInflater.inflate(R.layout.for_freind, null);
        ImageView Image = (ImageView) view.findViewById(R.id.Friendimage);
        TextView Name = (TextView) view.findViewById(R.id.Name_Friend);
        TextView Active = (TextView) view.findViewById(R.id.Active);
        ImageView SendAdd = (ImageView)view.findViewById(R.id.add_friend);

        Picasso.with(mContext).load(lists.get(position).getS4()).into(Image);
        Name.setText(lists.get(position).getS1());
        Active.setText(lists.get(position).getS3());

        SendAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int number = position;
                MyApplication data = (MyApplication) mContext.getApplicationContext();
                String Specific_email = data.getEmails(number);
                data.setSpecific_Email_Press(Specific_email);
                String User_Curr_Email = data.getUser_Email();
                SharedPreferences sharedPreferences = mContext.getSharedPreferences("EmailClick",Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("The_User_Email_Click",Specific_email);
                Fragment1 fragment1 = new Fragment1();
                fragment1.Check_Answer_Freind(Specific_email,User_Curr_Email);
            }
        });




        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int number = position;
                MyApplication data = (MyApplication) mContext.getApplicationContext();
                String Specific_Email = data.getEmails(number);

                data.setSpecific_Email_Press(Specific_Email);

            }
        });


        return view;
    }


    /*public class ViewHloder extends AppCompatActivity {
        ImageView add_btn;
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.for_freind);

            add_btn = (ImageView)findViewById(R.id.add_friend);

            add_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Fragment1 fragment1 = new Fragment1();
                    fragment1.Check_Answer_Freind();
                }
            });
        }
    }*/
  //  public class ViewHloder{
   //     ImageView sendadd;
   // }
}

