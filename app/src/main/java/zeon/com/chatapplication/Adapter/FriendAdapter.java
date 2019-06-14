package zeon.com.chatapplication.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;

import zeon.com.chatapplication.Activity.ChatActivity;
import zeon.com.chatapplication.Fragment.Fragment1;
import zeon.com.chatapplication.Model.FriendComp;
import zeon.com.chatapplication.Model.UserProfile;
import zeon.com.chatapplication.Model.story;
import zeon.com.chatapplication.MyApplication;
import zeon.com.chatapplication.R;

public class FriendAdapter extends BaseAdapter {
    private ArrayList<FriendComp> lists;
    private Context mContext;
    private LayoutInflater mInflater;
    MyApplication data = (MyApplication) MyApplication.getAppContext();
    private UserProfile ObjConnection = data.getUser();

    public FriendAdapter(Context context, ArrayList<FriendComp> list) {

        this.mContext = context;
        this.lists = list;
        this.mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    public  FriendAdapter(){

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
                MyApplication data = (MyApplication) MyApplication.getAppContext().getApplicationContext();
                System.out.println("The Emails is"+data.getEmails());
                String Specific_email = data.getEmails(number); //here
                data.setSpecific_Email_Press(Specific_email);
                data.user.SetUserFriendsByPos(Specific_email);
                String User_Curr_Email = data.getUser_Email();
                SharedPreferences sharedPreferences = mContext.getSharedPreferences("EmailClick",Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("The_User_Email_Click",Specific_email);
                final ArrayList<Object> arrayList = new ArrayList<>();
                arrayList.add(6);
                arrayList.add(User_Curr_Email);
                arrayList.add(Specific_email);
                data.user.setUser_Friend_Emails(lists.get(position).getS1());
               // data.getEmails().remove(position);
           //     StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            //    StrictMode.setThreadPolicy(policy);

                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            ObjConnection.output.writeObject(arrayList);
                            ArrayList<Object> inputlist =(ArrayList<Object>)ObjConnection.input.readObject();
                            System.out.println("The inputList :"+inputlist);
                            ObjConnection.handleReceivedRequest(inputlist);
                            ObjConnection.output.flush();
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                });
                thread.start();
                lists.remove(position);
                notifyDataSetChanged();

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

        notifyDataSetChanged();
        Animation animation = AnimationUtils.loadAnimation(mContext,R.anim.slide_left);
        view.startAnimation(animation);
        return view;
    }


}

