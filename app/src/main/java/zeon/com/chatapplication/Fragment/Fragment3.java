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
import java.util.ArrayList;

import zeon.com.chatapplication.Adapter.ForBaseAdapter;
import zeon.com.chatapplication.Model.MessagePerson;
import zeon.com.chatapplication.MyApplication;
import zeon.com.chatapplication.R;

public class Fragment3 extends Fragment {

    ArrayList<MessagePerson> message = new ArrayList<>();
    private ListView listview ;
    ActionBar action;
    private BottomNavigationView mView;


    public void init(){
        message.add(new MessagePerson(1,"Mohamad Nesart","Hello There","April","https://www.google.com/url?sa=i&source=images&cd=&ved=2ahUKEwjR_qew--HhAhWMxoUKHRKwCA0QjRx6BAgBEAU&url=http%3A%2F%2Fsteezo.com%2F%3Fproduct%3Dman-in-stripped-suit&psig=AOvVaw0BK6qUf6tcpUZ1lNMSG0bo&ust=1555962818897341"));
        message.add(new MessagePerson(2,"Mohamad Moazen","Good Morning","june","https://www.google.com/url?sa=i&source=images&cd=&cad=rja&uact=8&ved=2ahUKEwjB4IbhheLhAhUvxYUKHZESChQQjRx6BAgBEAU&url=https%3A%2F%2Fwww.almasryalyoum.com%2Fnews%2Fdetails%2F998120&psig=AOvVaw0BK6qUf6tcpUZ1lNMSG0bo&ust=1555962818897341"));
        message.add(new MessagePerson(3,"Alaa Ajee","Yep ","March","https://www.google.com/url?sa=i&source=images&cd=&cad=rja&uact=8&ved=2ahUKEwj6z5D4heLhAhVCrxoKHbEVCC8QjRx6BAgBEAU&url=http%3A%2F%2Fdralatar.com%2F%25D8%25A7%25D8%25B9%25D9%2584%25D8%25A7%25D9%2586-%25D8%25AF%25D9%2588%25D8%25B1%25D8%25A9-%25D8%25A7%25D9%2584%25D9%2583%25D8%25A7%25D8%25B1%25D9%258A%25D8%25B2%25D9%2585%25D8%25A7%2F&psig=AOvVaw0BK6qUf6tcpUZ1lNMSG0bo&ust=1555962818897341"));
        message.add(new MessagePerson(4,"Adnan Ktan",":)","April","G:\\github\\ChatApplication\\app\\src\\main\\res\\drawable\\dog_back.jpg"));
        message.add(new MessagePerson(5,"Areej Derze","welcome","April","G:\\github\\ChatApplication\\app\\src\\main\\res\\drawable\\dog_back.jpg"));
        message.add(new MessagePerson(6,"Hiba Abdalmalek","Hi","April","G:\\github\\ChatApplication\\app\\src\\main\\res\\drawable\\dog_back.jpg"));

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.layout_fragment3, container, false);

        listview = (ListView)view.findViewById(R.id.ListViewMessage3);
        init();
        ForBaseAdapter adapter = new ForBaseAdapter(getContext(),message);
        Log.d("AlienChat","Context"+getContext());
        Log.d("AlienChat","message"+message);
        listview.setAdapter(adapter);

        return view;


    }




}