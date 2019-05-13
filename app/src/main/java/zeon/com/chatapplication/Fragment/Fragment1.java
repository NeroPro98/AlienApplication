package zeon.com.chatapplication.Fragment;


import android.app.ActionBar;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Message;
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

public class Fragment1 extends Fragment{

    ArrayList<FriendComp> Friend = new ArrayList<>();
    private ListView listview ;
    ActionBar action;
    private BottomNavigationView mView;





    public void init(){

        Friend.add(new FriendComp(1,"Mohamad Nesart","April","https://www.google.com/url?sa=i&source=images&cd=&ved=2ahUKEwjR_qew--HhAhWMxoUKHRKwCA0QjRx6BAgBEAU&url=http%3A%2F%2Fsteezo.com%2F%3Fproduct%3Dman-in-stripped-suit&psig=AOvVaw0BK6qUf6tcpUZ1lNMSG0bo&ust=1555962818897341"));
        Friend.add(new FriendComp(1,"Mohamad Al Moazen","April","https://www.google.com/url?sa=i&source=images&cd=&ved=2ahUKEwjR_qew--HhAhWMxoUKHRKwCA0QjRx6BAgBEAU&url=http%3A%2F%2Fsteezo.com%2F%3Fproduct%3Dman-in-stripped-suit&psig=AOvVaw0BK6qUf6tcpUZ1lNMSG0bo&ust=1555962818897341"));

    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.layout_fragment1,container,false);
        listview = (ListView)view.findViewById(R.id.ListViewMessage1);

        init();
        FriendAdapter adapter = new FriendAdapter(getContext(),Friend);
        Log.d("AlienChat","Context"+getContext());
        Log.d("AlienChat","message"+Friend);
        listview.setAdapter(adapter);
        return view;

    }
}
