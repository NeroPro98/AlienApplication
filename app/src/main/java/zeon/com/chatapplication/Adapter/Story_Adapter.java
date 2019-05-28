package zeon.com.chatapplication.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import zeon.com.chatapplication.Fragment.Fragment1;
import zeon.com.chatapplication.Fragment.Fragment2;
import zeon.com.chatapplication.Model.story;
import zeon.com.chatapplication.MyApplication;
import zeon.com.chatapplication.R;

public class Story_Adapter extends BaseAdapter {
    private ArrayList <story> mArrayList;
    private Context mContext;
    private LayoutInflater mInflater;

    public Story_Adapter(Context context , ArrayList <story> list){

    this.mContext = context;
    this.mArrayList = list;
    this.mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return mArrayList.size();
    }

    @Override
    public story getItem(int position) {
        return mArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mArrayList.get(position).getId();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View view = mInflater.inflate(R.layout.for_peroson_story,null);
        ImageView image = (ImageView)view.findViewById(R.id.storyimage);
        TextView txt1 = (TextView)view.findViewById(R.id.addstory);
        TextView txt2 = (TextView)view.findViewById(R.id.timesotry);
        ImageView SendDelete = (ImageView)view.findViewById(R.id.delete_friend);
        ImageView SendBlock = (ImageView)view.findViewById(R.id.block_friend);
        ImageView UnBlock = (ImageView)view.findViewById(R.id.unblock_friend);

        txt1.setText(mArrayList.get(position).getAddtext());
        txt2.setText(mArrayList.get(position).getDatestory());
        Picasso.with(mContext).load(mArrayList.get(position).getImage()).into(image);

        SendDelete.setOnClickListener(new View.OnClickListener() {
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
                Fragment2 fragment2 = new Fragment2();
                fragment2.Check_Answer_Delete(Specific_email,User_Curr_Email);

            }
        });

        SendBlock.setOnClickListener(new View.OnClickListener() {
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
                Fragment2 fragment2 = new Fragment2();
                fragment2.Check_Block_Friend(Specific_email,User_Curr_Email);
            }
        });

        UnBlock.setOnClickListener(new View.OnClickListener() {
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
                Fragment2 fragment2 = new Fragment2();
                fragment2.Check_Block_Friend(Specific_email,User_Curr_Email);

            }
        });



        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        return view;
    }
}
