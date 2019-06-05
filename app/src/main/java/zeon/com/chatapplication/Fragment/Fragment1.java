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
import zeon.com.chatapplication.Model.FriendComp;
import zeon.com.chatapplication.Model.MessagePerson;
import zeon.com.chatapplication.Model.UserProfile;
import zeon.com.chatapplication.MyApplication;
import zeon.com.chatapplication.R;

public class Fragment1 extends Fragment {

    ArrayList<FriendComp> Friend = new ArrayList<>();
    private ListView listview;
    ActionBar action;
    private BottomNavigationView mView;
    private UserProfile ObjConnection = new UserProfile();
    ArrayList<Object> list = new ArrayList<Object>();
    ArrayList<String> EmailList = new ArrayList<String>();
    FriendAdapter adapter = new FriendAdapter();
    MyApplication data = (MyApplication)MyApplication.getAppContext().getApplicationContext();
    private int count = 0;

    public void init(ArrayList<Object> list2) {

     //   int size = (int) list.get(2);
        if(count ==0) {
            list = (ArrayList) data.user.getUser_List();
            count++;
        }else {
            list = list2;
            list.remove(0);
            list.remove(0);
            list.remove(0);
            count++;
        }
        System.out.println("The Count of Fragment 1 Is:"+count);
        System.out.println("The List of Fragment 1 Is:"+list);

        for (int i = 0; i < list.size(); i = i + 2) {
             //Friend.add(new FriendComp(1, "Mohamad Nesart", "April", "https://www.google.com/url?sa=i&source=images&cd=&ved=2ahUKEwjR_qew--HhAhWMxoUKHRKwCA0QjRx6BAgBEAU&url=http%3A%2F%2Fsteezo.com%2F%3Fproduct%3Dman-in-stripped-suit&psig=AOvVaw0BK6qUf6tcpUZ1lNMSG0bo&ust=1555962818897341"));
             //Friend.add(new FriendComp(1,"Mohamad Al Moazen","April","https://www.google.com/url?sa=i&source=images&cd=&ved=2ahUKEwjR_qew--HhAhWMxoUKHRKwCA0QjRx6BAgBEAU&url=http%3A%2F%2Fsteezo.com%2F%3Fproduct%3Dman-in-stripped-suit&psig=AOvVaw0BK6qUf6tcpUZ1lNMSG0bo&ust=1555962818897341"));
            Friend.add(new FriendComp(1,(String) list.get(i+1) ,(String) list.get(i) , "https://www.google.com/url?sa=i&source=images&cd=&ved=2ahUKEwjR_qew--HhAhWMxoUKHRKwCA0QjRx6BAgBEAU&url=http%3A%2F%2Fsteezo.com%2F%3Fproduct%3Dman-in-stripped-suit&psig=AOvVaw0BK6qUf6tcpUZ1lNMSG0bo&ust=1555962818897341"));
            EmailList.add((String) list.get(i + 1));
        }

        //ArrayList<String> SaveEmails =data.getEmails();
        //if(EmailList.size() >SaveEmails.size()) {
            data.setEmails(EmailList);
      //  }
//        for(int i =0;i<EmailList.size();i++) {
 //           EmailList.remove(i);
  //      }
        System.out.println("The Email List is :" + data.getUser_Email());
        data.getUser_Email();

        adapter.notifyDataSetChanged();


    }




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.layout_fragment1, container, false);
        listview = (ListView) view.findViewById(R.id.ListViewMessage1);
        ArrayList <Object>list3 =(ArrayList)data.getGetUser_List_Every_init();
        init(list3);
        adapter = new FriendAdapter(getContext(), Friend);
        Log.d("AlienChat", "Context" + getContext());
        Log.d("AlienChat", "message" + Friend);

        listview.setAdapter(adapter);


        return view;

    }






 /*   @Override
    public void onResume() {
        super.onResume();
        Check_All();
    }*/
}
