package zeon.com.chatapplication.Fragment;


import android.app.ActionBar;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Message;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

import zeon.com.chatapplication.Adapter.ForBaseAdapter;
import zeon.com.chatapplication.Adapter.FriendAdapter;
import zeon.com.chatapplication.Adapter.Story_Adapter;
import zeon.com.chatapplication.Model.FriendComp;
import zeon.com.chatapplication.Model.MessagePerson;
import zeon.com.chatapplication.Model.UserProfile;
import zeon.com.chatapplication.MyApplication;
import zeon.com.chatapplication.R;

public class Fragment1 extends Fragment {

    ArrayList<FriendComp> Friend = new ArrayList<>();
    private ListView listview;
    ActionBar action;

    ArrayList<Object> list = new ArrayList<Object>();
    ArrayList<String> EmailList = new ArrayList<String>();
    FriendAdapter adapter = new FriendAdapter();
    MyApplication data = (MyApplication) MyApplication.getAppContext().getApplicationContext();
    private UserProfile ObjConnection = data.getUser();
    private SwipeRefreshLayout mRefreshLayout;
    private int count = 0;
    private ArrayList<Object> helper_List = new ArrayList<>();
    private ArrayList<Object> helper_List2 = new ArrayList<>();
    private int FriendSuggectionCount = 0;

    public void init(ArrayList<Object> list2) {

        EmailList.removeAll(EmailList);
        //   int size = (int) list.get(2);
        if (data.getFragment1_Counter() == 0) {
            list = (ArrayList) data.user.getUser_List();
            data.setFragment1_Counter();
        } else {
            list = list2;
            Friend.removeAll(Friend);

            // list.remove(0);
            // list.remove(0);
            //list.remove(0);
            data.setFragment1_Counter();
        }
        System.out.println("The Count of Fragment 1 Is:" + data.getFragment1_Counter());
        System.out.println("The List of Fragment 1 Is:" + list);

        //   if (list.size() != FriendSuggectionCount) {

        // }

        if (list.size() != 0) {
            for (int i = 0; i < list.size(); i = i + 2) {
                //Friend.add(new FriendComp(1, "Mohamad Nesart", "April", "https://www.google.com/url?sa=i&source=images&cd=&ved=2ahUKEwjR_qew--HhAhWMxoUKHRKwCA0QjRx6BAgBEAU&url=http%3A%2F%2Fsteezo.com%2F%3Fproduct%3Dman-in-stripped-suit&psig=AOvVaw0BK6qUf6tcpUZ1lNMSG0bo&ust=1555962818897341"));
                //Friend.add(new FriendComp(1,"Mohamad Al Moazen","April","https://www.google.com/url?sa=i&source=images&cd=&ved=2ahUKEwjR_qew--HhAhWMxoUKHRKwCA0QjRx6BAgBEAU&url=http%3A%2F%2Fsteezo.com%2F%3Fproduct%3Dman-in-stripped-suit&psig=AOvVaw0BK6qUf6tcpUZ1lNMSG0bo&ust=1555962818897341"));
                //     for (int j = 0; j < Friend.size(); j++) {
                //       if (!data.getEmails().get(j).equals(list.get(i + 1))) {
                Friend.add(new FriendComp(1, (String) list.get(i + 1), (String) list.get(i), "https://www.google.com/url?sa=i&source=images&cd=&ved=2ahUKEwjR_qew--HhAhWMxoUKHRKwCA0QjRx6BAgBEAU&url=http%3A%2F%2Fsteezo.com%2F%3Fproduct%3Dman-in-stripped-suit&psig=AOvVaw0BK6qUf6tcpUZ1lNMSG0bo&ust=1555962818897341"));
                FriendSuggectionCount += 2;
                EmailList.add((String) list.get(i + 1));
                adapter.notifyDataSetChanged();
            }
        }else{
            Friend.removeAll(Friend);
            adapter.notifyDataSetChanged();
        }
        //}
        System.out.println("FriendSuggectionCount is:" + FriendSuggectionCount);

        //ArrayList<String> SaveEmails =data.getEmails();
        //if(EmailList.size() >SaveEmails.size()) {
        data.setEmails(EmailList);
        //EmailList.removeAll(EmailList);
        //  }
//        for(int i =0;i<EmailList.size();i++) {
        //           EmailList.remove(i);
        //      }
        System.out.println("The Email List is :" + data.getUser_Email());
        data.getUser_Email();




    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup
            container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.layout_fragment1, container, false);
        listview = (ListView) view.findViewById(R.id.ListViewMessage1);
        ArrayList<Object> list3;
        // if(data.getFragment1_Counter()==0) {
        list3 = data.user.getUser_List();
        //       list3= (ArrayList) data.user.getUser_List(); ///////////////here
        init(list3);
        // }
        adapter = new FriendAdapter(getContext(), Friend);
        Log.d("AlienChat", "Context" + getContext());
        Log.d("AlienChat", "message" + Friend);
        listview.setAdapter(adapter);
        mRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout_frag1);


        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                boolean bool = false;
                System.out.println("The User Friend is" + data.user.getUserFriends());
                System.out.println("I am Refresh Swap");
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        if (data.getFragment1_Counter() > 0) {
                            ArrayList arrayList = serilaizeToStrings();
                            System.out.println("The arraylist :" + arrayList);
                            try {
                                ObjConnection.output.writeObject(arrayList);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            final ArrayList<Object> inputlist;
                            try {
                                inputlist = (ArrayList<Object>) ObjConnection.input.readObject();
                                data.user.getUser_List();
                                ObjConnection.output.flush();
                                data.setThe_User_List_From_Server(inputlist);
                                helper_List = data.getThe_User_List_From_Server();
                                helper_List2 = data.user.getUser_List();
                            } catch (IOException e) {
                                e.printStackTrace();
                            } catch (ClassNotFoundException e) {
                                e.printStackTrace();
                            }


                            //for(int i =0;i<helper_List.size();i++) {
                            // if(helper_List.size() != helper_List2.size() && helper_List.size()>=2) {
                            // for(int i =0;i<Friend.size();i++) {

                            //   }

                        }
                    }
                });
                thread.start();


                if (helper_List.size() > 2) {
                    helper_List.remove(0);
                    helper_List.remove(0);
                    helper_List.remove(0);
                    init(helper_List);
                    data.user.setUser_List(helper_List);

                    adapter = new FriendAdapter(getContext(), Friend);
                    listview.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }

               /* if (helper_List.size() >= 2) {

                } else {
                    adapter.notifyDataSetChanged();
                }*/
                mRefreshLayout.setRefreshing(false);

            }
        });

        return view;

    }


    public ArrayList<Object> serilaizeToStrings() {
        ArrayList<Object> list = new ArrayList<>();
        list.add(4);
        list.add(data.getUser_Email());
        // list.add(email);
        return list;
    }


 /*   @Override
    public void onResume() {
        super.onResume();
        Check_All();
    }*/
}
