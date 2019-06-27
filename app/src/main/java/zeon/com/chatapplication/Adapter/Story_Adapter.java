package zeon.com.chatapplication.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import zeon.com.chatapplication.Fragment.Fragment1;
import zeon.com.chatapplication.Fragment.Fragment2;
import zeon.com.chatapplication.Model.UserProfile;
import zeon.com.chatapplication.Model.story;
import zeon.com.chatapplication.MyApplication;
import zeon.com.chatapplication.R;

public class Story_Adapter extends BaseAdapter {
    private ArrayList<story> mArrayList;
    private Context mContext;
    private LayoutInflater mInflater;
    MyApplication data = (MyApplication) MyApplication.getAppContext();
    private UserProfile ObjConnection = data.getUser();
    ArrayList ListBlockFriend = new ArrayList();

    public Story_Adapter(Context context, ArrayList<story> list) {

        this.mContext = context;
        this.mArrayList = list;
        this.mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    public Story_Adapter() {

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

        final View view = mInflater.inflate(R.layout.for_peroson_story, null);
        CircleImageView image = (CircleImageView) view.findViewById(R.id.storyimage);
        final TextView txt1 = (TextView) view.findViewById(R.id.addstory);
        TextView txt2 = (TextView) view.findViewById(R.id.timesotry);
        ImageView SendDelete = (ImageView) view.findViewById(R.id.delete_friend);
        ImageView SendBlock = (ImageView) view.findViewById(R.id.block_friend);
        ImageView ChatFriend = (ImageView) view.findViewById(R.id.Chat_Friend);


        txt1.setText(mArrayList.get(position).getAddtext());
        txt2.setText(mArrayList.get(position).getDatestory());
        Picasso.with(mContext).load(mArrayList.get(position).getImage()).into(image);

        data.user.setFriendnum(mArrayList.size());
        data.user.setFriendfollower(mArrayList.size());

        SendDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int number = position;
                //String Specific_email = data.getEmails(number);
                String Specific_email = data.user.getUserFriendsByPos(number);
                System.out.println("The User Friend Email is" + data.user.getUserFriends());
                data.setSpecific_Email_Press(Specific_email);
                String User_Curr_Email = data.getUser_Email();
                final ArrayList<Object> arrayList = new ArrayList<>();
                arrayList.add(7);
                arrayList.add(User_Curr_Email);
                //Specific_email = "www.alaa@gmail.com";

                arrayList.add(Specific_email);

                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            System.out.println("The arraylist :" + arrayList);
                            ObjConnection.output.writeObject(arrayList);
                            ArrayList<Object> inputlist = (ArrayList<Object>) ObjConnection.input.readObject();
                            System.out.println("The inputList :" + inputlist);
                            ObjConnection.handleReceivedRequest(inputlist);
                            ObjConnection.output.flush();
                            // data.getEmails().remove(position);

                            System.out.println("The List Of Emails is:" + data.getEmails());
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                });
                thread.start();

                data.user.getUserFriends().remove(position);

                ArrayList<Object> beforupdate = data.user.getUser_Friend_Info();
                ArrayList<String> FriendEmails = data.user.getUser_Friend_Emails();
                ArrayList<String> FriendEmailsNserat = data.user.getUserFriends();
                System.out.println("The  FriendEmails  is:" + FriendEmails);
                String Name = mArrayList.get(position).getAddtext();
                if (beforupdate.size() != 0) {
                    for (int i = 0; i < beforupdate.size(); i++) {

                        if (beforupdate.get(i).equals(Name)) {
                            beforupdate.remove(i);
                            beforupdate.remove(i);
                            break;
                        }//else{
                        //  data.user.setUser_Friend_Info(beforupdate.get(i));
                        // }
                    }
                }
                data.user.User_Friend_Info = beforupdate;
                System.out.println("The  beforupdate after delete is:" + beforupdate);
              /*  for(int j =0;j<beforupdate.size();j++){
                    data.user.setUser_Friend_Info(beforupdate.get(j));
                }*/
                System.out.println("The  User_Friend_Info after update is:" + data.user.getUser_Friend_Info());
                mArrayList.remove(position);
                notifyDataSetChanged();

            }
        });

        SendBlock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int number = position;

                //String Specific_email = data.getEmails(number);
                String Specific_email = data.user.getUserFriendsByPos(number);
                System.out.println("The User Friend Email is" + data.user.getUserFriends());
                data.setSpecific_Email_Press(Specific_email);
                String User_Curr_Email = data.getUser_Email();
                SharedPreferences sharedPreferences = mContext.getSharedPreferences("EmailClick", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("The_User_Email_Click", Specific_email);

                data.user.setUser_Block_List_String(Specific_email);
                data.user.setUser_Block_List_String(mArrayList.get(position).getAddtext());
                final ArrayList<Object> arrayList2 = new ArrayList<>();
                arrayList2.add(8);
                arrayList2.add(User_Curr_Email);
                arrayList2.add(Specific_email);


                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            System.out.println("The arraylist :" + arrayList2);
                            ObjConnection.output.writeObject(arrayList2);
                            ArrayList<Object> inputlist = (ArrayList<Object>) ObjConnection.input.readObject();
                            System.out.println("The inputList :" + inputlist);
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
                data.user.setBlockListString(data.user.getUserFriendsByPos(number));
                data.user.getUserFriends().remove(position);

                mArrayList.remove(position);
                notifyDataSetChanged();
            }

        });

        ChatFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean bool = false;
                int number = position;
                ArrayList<Object> UserChat = new ArrayList<>();
                String Specific_email = data.user.getUserFriendsByPos(number);
                String name_user = mArrayList.get(position).getAddtext();
                if(data.user.getThe_User_Hwo_Chat_With_Him().size()!=0) {
                    for (int i = 0; i < data.user.getThe_User_Hwo_Chat_With_Him().size(); i = i+2) {
                        if(data.user.getThe_User_Hwo_Chat_With_Him().get(i).equals(Specific_email)) {
                           bool = true;
                        }
                    }
                    if(bool==false){
                        UserChat.add(Specific_email);
                        UserChat.add(name_user);
                        data.user.setThe_User_Hwo_Chat_With_Him(UserChat);
                        try {
                            data.Save_File();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }else{
                    UserChat.add(Specific_email);
                    UserChat.add(name_user);
                    data.user.setThe_User_Hwo_Chat_With_Him(UserChat);
                    try {
                        data.Save_File();
                        data.Read_File();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                //UserChat.removeAll(UserChat);
            }
        });



        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        notifyDataSetChanged();

        Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.slide_left);
        view.startAnimation(animation);

        return view;
    }



}
