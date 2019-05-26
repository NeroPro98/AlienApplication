package zeon.com.chatapplication.Fragment;

import android.media.Image;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;

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
    private UserProfile ObjConnection = new UserProfile();
    ArrayList<Object> list = new ArrayList<Object>();
    Fragment1 fragment1 = new Fragment1();
    Story_Adapter adapter;

    private void InitStory() {
        list.remove(0);
        list.remove(0);
        for (int i = 0; i < list.size(); i=i+2) {
            listStory.add(new story(1, "https://www.google.com/url?sa=i&source=images&cd=&ved=2ahUKEwjR_qew--HhAhWMxoUKHRKwCA0QjRx6BAgBEAU&url=http%3A%2F%2Fsteezo.com%2F%3Fproduct%3Dman-in-stripped-suit&psig=AOvVaw0BK6qUf6tcpUZ1lNMSG0bo&ust=1555962818897341", (String)list.get(i), (String)list.get(i+1)));
           // listStory.add(new story(1, "https://www.google.com/url?sa=i&source=images&cd=&cad=rja&uact=8&ved=2ahUKEwjB4IbhheLhAhUvxYUKHZESChQQjRx6BAgBEAU&url=https%3A%2F%2Fwww.almasryalyoum.com%2Fnews%2Fdetails%2F998120&psig=AOvVaw0BK6qUf6tcpUZ1lNMSG0bo&ust=1555962818897341", "Adnan Ktan", "June"));
        }
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
        //Check_All_Story();
        // Picasso.with(getContext()).load("G:\\github\\ChatApplication\\app\\src\\main\\res\\drawable\\astro3.jpg")
        //   .into(imagestory); //don't work
        imagestory.setImageResource(R.drawable.man);
        textadd.setText("Mohamad Al Moazen");
        textdate.setText("June");
      //
        Check_All_Friend();

        // InitStory();
        adapter = new Story_Adapter(getContext(), listStory);

        grid.setAdapter(adapter);
       // fragment1.Check_All();

        return view;
    }

    public boolean Friends_List_Story(ArrayList<Object> arrayList) throws IOException, ClassNotFoundException {


        ObjConnection.connectToServer();
        ObjConnection.SetupStreams();
        System.out.println("The ArrayList of Fragmint2 is :" + arrayList);
        ObjConnection.output.writeObject(arrayList);
        ObjConnection.output.flush();
        ObjConnection.input.readObject();
        list = (ArrayList<Object>) ObjConnection.input.readObject();
        boolean res = ObjConnection.handleReceivedRequest(list);
        Log.d("res", "res:" + res);
        if (!res) {


            ObjConnection.CloseCrap();
            return false;

        } else {
            ObjConnection.CloseCrap();
            return true;
        }

    }

    public ArrayList<Object> serilaizeToStrings() {
        MyApplication data = (MyApplication) getContext().getApplicationContext();
        ArrayList<Object> list = new ArrayList<>();
        list.add(9);
        list.add(data.getUser_Email());
        return list;
    }

    public boolean checkTheListStory() throws InterruptedException, IOException, ClassNotFoundException {
        boolean res = Friends_List_Story(serilaizeToStrings());

        System.out.println("res of checkTheListStory:" + res);
        return res;
    }

    public void Check_All_Story() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                boolean bool = false;
                try {

                    bool = checkTheListStory();

                    System.out.println("The bool of fragment1 is:" + bool);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                if (bool) {
                    InitStory();
                }
            }
        });
        thread.start();

    }

    public void Check_All_Friend() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                boolean bool = false;
                try {

                    bool = checkTheListSpecificFriend();

                    System.out.println("The bool of fragment2 is:" + bool);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                if (bool) {
                    InitStory();
                }
            }
        });
        thread.start();

    }


    public boolean checkTheListSpecificFriend() throws InterruptedException, IOException, ClassNotFoundException {
        boolean res = Friends_List_Story(serilaizeToStringsForSpecificFriend());

        System.out.println("res of checkTheListStory:" + res);
        return res;
    }

    public ArrayList<Object> serilaizeToStringsForSpecificFriend() {
        MyApplication data = (MyApplication) getContext().getApplicationContext();
        ArrayList<Object> list = new ArrayList<>();
        list.add(11);
        list.add(data.getUser_Email());
        return list;
    }

}
