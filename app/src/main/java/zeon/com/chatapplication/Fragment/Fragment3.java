package zeon.com.chatapplication.Fragment;

import android.os.Bundle;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import java.util.ArrayList;

import zeon.com.chatapplication.Adapter.ForBaseAdapter;
import zeon.com.chatapplication.Model.MessagePerson;
import zeon.com.chatapplication.R;

public class Fragment3 extends Fragment {

    ArrayList<MessagePerson> message = new ArrayList<MessagePerson>();
    private ListView listview ;

    public void init(){

        message.add(new MessagePerson(1,"Mohamad Nesart","Hello There","April","https://www.google.com/url?sa=i&source=images&cd=&cad=rja&uact=8&ved=2ahUKEwjvp8iIj9_hAhWQLFAKHa4kBBgQjRx6BAgBEAU&url=https%3A%2F%2Fwww.youtube.com%2Fwatch%3Fv%3Dx_HL0wiK4Zc&psig=AOvVaw1rnEXGhzbODWyHMDIyIcix&ust=1555865029396123"));
     //   message.add(new MessagePerson(2,"Mohamad Moazen","Good Morning","june","G:\\github\\ChatApplication\\app\\src\\main\\res\\drawable\\man.jpg"));
      //  message.add(new MessagePerson(3,"Alaa Ajee","Yep ","March","G:\\github\\ChatApplication\\app\\src\\main\\res\\drawable\\earth.jpg"));
      //  message.add(new MessagePerson(4,"Adnan Ktan",":)","April","G:\\github\\ChatApplication\\app\\src\\main\\res\\drawable\\dog_back.jpg"));
      //  message.add(new MessagePerson(5,"Areej Derze","welcome","April","G:\\github\\ChatApplication\\app\\src\\main\\res\\drawable\\dog_back.jpg"));
     //   message.add(new MessagePerson(6,"Hiba Abdalmalek","Hi","April","G:\\github\\ChatApplication\\app\\src\\main\\res\\drawable\\dog_back.jpg"));

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.layout_fragment3, container, false);


        listview = (ListView)view.findViewById(R.id.ListViewMessage);
        init();
        ForBaseAdapter adapter = new ForBaseAdapter(getContext(),message);
        listview.setAdapter(adapter); //****************error****************



        return view;


    }




}