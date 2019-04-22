package zeon.com.chatapplication.Fragment;

import android.media.Image;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import zeon.com.chatapplication.Adapter.Story_Adapter;
import zeon.com.chatapplication.Model.story;
import zeon.com.chatapplication.R;

public class Fragment2 extends Fragment {

    GridView grid;
    ImageView imagestory;
    TextView textadd;
    TextView textdate;
    ArrayList<story>list = new ArrayList<>();


    private void InitStory(){
        list.add(new story(1,"https://www.google.com/url?sa=i&source=images&cd=&ved=2ahUKEwjR_qew--HhAhWMxoUKHRKwCA0QjRx6BAgBEAU&url=http%3A%2F%2Fsteezo.com%2F%3Fproduct%3Dman-in-stripped-suit&psig=AOvVaw0BK6qUf6tcpUZ1lNMSG0bo&ust=1555962818897341","Mohamad Nserat","March"));
        list.add(new story(1,"https://www.google.com/url?sa=i&source=images&cd=&cad=rja&uact=8&ved=2ahUKEwjB4IbhheLhAhUvxYUKHZESChQQjRx6BAgBEAU&url=https%3A%2F%2Fwww.almasryalyoum.com%2Fnews%2Fdetails%2F998120&psig=AOvVaw0BK6qUf6tcpUZ1lNMSG0bo&ust=1555962818897341","Adnan Ktan","June"));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.layout_fragment2, container, false);

        grid = (GridView)view.findViewById(R.id.gridviewstatus);
        textadd = (TextView)view.findViewById(R.id.textadd);
        textdate = (TextView)view.findViewById(R.id.textdate);
        imagestory = (ImageView)view.findViewById(R.id.imagestory);

       // Picasso.with(getContext()).load("G:\\github\\ChatApplication\\app\\src\\main\\res\\drawable\\astro3.jpg")
             //   .into(imagestory); //don't work
        imagestory.setImageResource(R.drawable.man);
        textadd.setText("Mohamad Al Moazen");
        textdate.setText("June");

        InitStory();
        Story_Adapter adapter = new Story_Adapter(getContext(),list);

        grid.setAdapter(adapter);

        return view;
    }
}
