package zeon.com.chatapplication.Fragment;

import android.content.Context;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import zeon.com.chatapplication.Adapter.FriendAdapter;
import zeon.com.chatapplication.Adapter.Story_Adapter;
import zeon.com.chatapplication.Model.UserProfile;
import zeon.com.chatapplication.Model.story;
import zeon.com.chatapplication.MyApplication;
import zeon.com.chatapplication.R;

public class Fragment2 extends Fragment {

    GridView grid;
    ImageView imagestory;
    TextView textadd;
    TextView textdate;
    ArrayList<story> listStory = new ArrayList<>();

    ArrayList<Object> list = new ArrayList<Object>();
    Fragment1 fragment1 = new Fragment1();
    Story_Adapter adapter = new Story_Adapter();
    ArrayList<String> EmailListFriends = new ArrayList<String>();
    MyApplication data = (MyApplication) MyApplication.getAppContext().getApplicationContext();
    private UserProfile ObjConnection = data.getUser();
    private int count = 0;
    //For SwipeRefreshLayout
    private SwipeRefreshLayout mRefreshLayout;
    private ArrayList<Object> helper_List = new ArrayList<>();
    private ArrayList<Object> helper_List2 = new ArrayList<>();


    public void InitStory(ArrayList<Object> list2) throws IOException {

        listStory.removeAll(listStory);
        EmailListFriends.removeAll(EmailListFriends);
        data.user.User_Friend_Info.removeAll(data.user.User_Friend_Info);
     //   list2 = helper_List;
        if (count == 0) {
            list = (ArrayList) data.user.getUser_Friend_Info();
          //   list.remove(0);
          //  list.remove(0);
            count++;
        } else if (list2.size() >= 2) {
            list = list2;
          //  data.user.User_Friend_Info.removeAll(data.user.User_Friend_Info);
             //list.remove(0);
            // list.remove(0);
            count++;
        }
        System.out.println("The Count Is:" + count);


        for (int i = 0; i < list.size(); i = i + 2) {

            listStory.add(new story(1, "https://www.google.com/url?sa=i&source=images&cd=&ved=2ahUKEwjR_qew--HhAhWMxoUKHRKwCA0QjRx6BAgBEAU&url=http%3A%2F%2Fsteezo.com%2F%3Fproduct%3Dman-in-stripped-suit&psig=AOvVaw0BK6qUf6tcpUZ1lNMSG0bo&ust=1555962818897341", (String) list.get(i), (String) list.get(i + 1)));
            EmailListFriends.add((String) list.get(i));


            // listStory.add(new story(1, "https://www.google.com/url?sa=i&source=images&cd=&cad=rja&uact=8&ved=2ahUKEwjB4IbhheLhAhUvxYUKHZESChQQjRx6BAgBEAU&url=https%3A%2F%2Fwww.almasryalyoum.com%2Fnews%2Fdetails%2F998120&psig=AOvVaw0BK6qUf6tcpUZ1lNMSG0bo&ust=1555962818897341", "Adnan Ktan", "June"));
        }
       // for (int j = 0; j < helper_List.size(); j++) {
       //     data.user.setUser_Friend_Info((String) list.get(j));
       // }
        data.user.setUser_Friend_Info_List(list);
        data.Save_File();
        data.Read_File();
        data.setFriendEmails(EmailListFriends);
        System.out.println("The EmailListFriends is:" + EmailListFriends);

        adapter.notifyDataSetChanged();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.layout_fragment2, container, false);

        grid = (GridView) view.findViewById(R.id.gridviewstatus);
        textadd = (TextView) view.findViewById(R.id.textadd);
        textdate = (TextView) view.findViewById(R.id.textdate);
        imagestory = (ImageView) view.findViewById(R.id.imagestory);
        // Picasso.with(getContext()).load("G:\\github\\ChatApplication\\app\\src\\main\\res\\drawable\\astro3.jpg")
        //   .into(imagestory); //don't work
        imagestory.setImageResource(R.drawable.man);
        final MyApplication data = (MyApplication) getContext().getApplicationContext();
        textadd.setText(data.getUser_Name());
        textdate.setText("June");
        //ArrayList<Object> list3 = (ArrayList) data.getUserFriend_List_Every_init();
        ArrayList<Object> list3 = (ArrayList)  data.user.getUser_Friend_Info();
        try {
            InitStory(list3);
        } catch (IOException e) {
            e.printStackTrace();
        }
        adapter = new Story_Adapter(getContext(), listStory);

        grid.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        mRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout_frag2);


        if (count > 0) {
            mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    System.out.println("The User Friend is" + data.user.getUserFriends());
                    System.out.println("I am Refresh Swap");
                    Thread thread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            if (count > 0) {
                                ArrayList arrayList = serilaizeToStringsForFriendList();
                                System.out.println("The arraylist :" + arrayList);
                                try {
                                    ObjConnection.output.writeObject(arrayList);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                final ArrayList<Object> inputlist;
                                try {
                                    inputlist = (ArrayList<Object>) ObjConnection.input.readObject();
                                    data.setHelper_List(inputlist);
                                    ObjConnection.output.flush();
                                    helper_List = data.getHelper_List();
                                    helper_List2 = data.user.getUser_Friend_Info();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                } catch (ClassNotFoundException e) {
                                    e.printStackTrace();
                                }

                            }
                        }
                    });
                    thread.start();

                    if (helper_List.size() > 2) {
                        helper_List.remove(0);
                        helper_List.remove(0);
                        try {
                            InitStory(helper_List);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        //  }
                        adapter = new Story_Adapter(getContext(), listStory);
                        grid.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    } else {
                        adapter.notifyDataSetChanged();
                    }


                    mRefreshLayout.setRefreshing(false);

                }


            });


            //  }

        }
        return view;
    }


    public ArrayList<Object> serilaizeToStringsForFriendList() {
        ArrayList<Object> list = new ArrayList<>();
        list.add(11);
        list.add(data.getUser_Email());
        // list.add(email);
        return list;
    }



    /* @Override
   public void onResume() {
        super.onResume();
        Toast.makeText(getContext(), "M", Toast.LENGTH_SHORT).show();
        Check_All_Friend();
    }*/


}
