package zeon.com.chatapplication.Fragment;

import android.app.ActionBar;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ListView;

import java.io.IOException;
import java.util.ArrayList;

import zeon.com.chatapplication.Adapter.ForBaseAdapter;
import zeon.com.chatapplication.Adapter.FriendAdapter;
import zeon.com.chatapplication.Model.MessagePerson;
import zeon.com.chatapplication.Model.UserProfile;
import zeon.com.chatapplication.MyApplication;
import zeon.com.chatapplication.R;

public class Fragment3 extends Fragment {

    ArrayList<MessagePerson> message = new ArrayList<>();
    private ListView listview;
    ActionBar action;
    private BottomNavigationView mView;
    MyApplication data = (MyApplication) MyApplication.getAppContext().getApplicationContext();
    private UserProfile ObjConnection = data.getUser();
    private SwipeRefreshLayout mRefreshLayout;
    private ArrayList<Object> helper_List = new ArrayList<>();
    private ForBaseAdapter adapter = new ForBaseAdapter();


    public void init(ArrayList<Object> list_user) {

       /* message.add(new MessagePerson("www.alaa@gmail.com",1,"Mohamad Nesart","Hello There","April","https://www.google.com/url?sa=i&source=images&cd=&ved=2ahUKEwjR_qew--HhAhWMxoUKHRKwCA0QjRx6BAgBEAU&url=http%3A%2F%2Fsteezo.com%2F%3Fproduct%3Dman-in-stripped-suit&psig=AOvVaw0BK6qUf6tcpUZ1lNMSG0bo&ust=1555962818897341"));
        message.add(new MessagePerson("www.alaa@gmail.com",1,"Mohamad Nesart","Hello There","April","https://www.google.com/url?sa=i&source=images&cd=&ved=2ahUKEwjR_qew--HhAhWMxoUKHRKwCA0QjRx6BAgBEAU&url=http%3A%2F%2Fsteezo.com%2F%3Fproduct%3Dman-in-stripped-suit&psig=AOvVaw0BK6qUf6tcpUZ1lNMSG0bo&ust=1555962818897341"));
        message.add(new MessagePerson("www.alaa@gmail.com",1,"Mohamad Nesart","Hello There","April","https://www.google.com/url?sa=i&source=images&cd=&ved=2ahUKEwjR_qew--HhAhWMxoUKHRKwCA0QjRx6BAgBEAU&url=http%3A%2F%2Fsteezo.com%2F%3Fproduct%3Dman-in-stripped-suit&psig=AOvVaw0BK6qUf6tcpUZ1lNMSG0bo&ust=1555962818897341"));
        message.add(new MessagePerson("www.alaa@gmail.com",1,"Mohamad Nesart","Hello There","April","https://www.google.com/url?sa=i&source=images&cd=&ved=2ahUKEwjR_qew--HhAhWMxoUKHRKwCA0QjRx6BAgBEAU&url=http%3A%2F%2Fsteezo.com%2F%3Fproduct%3Dman-in-stripped-suit&psig=AOvVaw0BK6qUf6tcpUZ1lNMSG0bo&ust=1555962818897341"));
        message.add(new MessagePerson("www.alaa@gmail.com",1,"Mohamad Nesart","Hello There","April","https://www.google.com/url?sa=i&source=images&cd=&ved=2ahUKEwjR_qew--HhAhWMxoUKHRKwCA0QjRx6BAgBEAU&url=http%3A%2F%2Fsteezo.com%2F%3Fproduct%3Dman-in-stripped-suit&psig=AOvVaw0BK6qUf6tcpUZ1lNMSG0bo&ust=1555962818897341"));
        message.add(new MessagePerson("www.alaa@gmail.com",1,"Mohamad Nesart","Hello There","April","https://www.google.com/url?sa=i&source=images&cd=&ved=2ahUKEwjR_qew--HhAhWMxoUKHRKwCA0QjRx6BAgBEAU&url=http%3A%2F%2Fsteezo.com%2F%3Fproduct%3Dman-in-stripped-suit&psig=AOvVaw0BK6qUf6tcpUZ1lNMSG0bo&ust=1555962818897341"));
        message.add(new MessagePerson("www.alaa@gmail.com",1,"Mohamad Nesart","Hello There","April","https://www.google.com/url?sa=i&source=images&cd=&ved=2ahUKEwjR_qew--HhAhWMxoUKHRKwCA0QjRx6BAgBEAU&url=http%3A%2F%2Fsteezo.com%2F%3Fproduct%3Dman-in-stripped-suit&psig=AOvVaw0BK6qUf6tcpUZ1lNMSG0bo&ust=1555962818897341"));
        message.add(new MessagePerson("www.alaa@gmail.com",1,"Mohamad Nesart","Hello There","April","https://www.google.com/url?sa=i&source=images&cd=&ved=2ahUKEwjR_qew--HhAhWMxoUKHRKwCA0QjRx6BAgBEAU&url=http%3A%2F%2Fsteezo.com%2F%3Fproduct%3Dman-in-stripped-suit&psig=AOvVaw0BK6qUf6tcpUZ1lNMSG0bo&ust=1555962818897341"));
*/
        if (list_user.size() != 0) {
            for (int i = 0; i < list_user.size(); i=i+2) {
                message.add(new MessagePerson((String) list_user.get(i), 1, (String) list_user.get(i + 1), "Hello There", "April", "https://www.google.com/url?sa=i&source=images&cd=&ved=2ahUKEwjR_qew--HhAhWMxoUKHRKwCA0QjRx6BAgBEAU&url=http%3A%2F%2Fsteezo.com%2F%3Fproduct%3Dman-in-stripped-suit&psig=AOvVaw0BK6qUf6tcpUZ1lNMSG0bo&ust=1555962818897341"));
            }
            adapter.notifyDataSetChanged();
        }

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.layout_fragment3, container, false);

        listview = (ListView) view.findViewById(R.id.ListViewMessage3);
        ArrayList<Object> InputList = data.user.getThe_User_Hwo_Chat_With_Him();
        mRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout_frag3);

        init(InputList);
        adapter = new ForBaseAdapter(getContext(), message);
        Log.d("AlienChat", "Context" + getContext());
        Log.d("AlienChat", "message" + message);

        listview.setAdapter(adapter);


        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                boolean bool = false;
                System.out.println("The User Friend is" + data.user.getUserFriends());
                System.out.println("I am Refresh Swap");
                helper_List = data.user.getThe_User_Hwo_Chat_With_Him();

                if (helper_List.size() >= 2) {
                    init(helper_List);
                    data.user.setUser_List(helper_List);

                    adapter = new ForBaseAdapter(getContext(), message);
                    listview.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }

                mRefreshLayout.setRefreshing(false);

            }
        });


        return view;


    }


}