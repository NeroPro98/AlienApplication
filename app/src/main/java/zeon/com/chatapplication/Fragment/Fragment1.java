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
    private UserProfile ObjConnection = new UserProfile();




    public void init(){

        Friend.add(new FriendComp(1,"Mohamad Nesart","April","https://www.google.com/url?sa=i&source=images&cd=&ved=2ahUKEwjR_qew--HhAhWMxoUKHRKwCA0QjRx6BAgBEAU&url=http%3A%2F%2Fsteezo.com%2F%3Fproduct%3Dman-in-stripped-suit&psig=AOvVaw0BK6qUf6tcpUZ1lNMSG0bo&ust=1555962818897341"));
        Friend.add(new FriendComp(1,"Mohamad Al Moazen","April","https://www.google.com/url?sa=i&source=images&cd=&ved=2ahUKEwjR_qew--HhAhWMxoUKHRKwCA0QjRx6BAgBEAU&url=http%3A%2F%2Fsteezo.com%2F%3Fproduct%3Dman-in-stripped-suit&psig=AOvVaw0BK6qUf6tcpUZ1lNMSG0bo&ust=1555962818897341"));

    }

    public boolean Friends_List(ArrayList<Object> arrayList) throws IOException, ClassNotFoundException {

        ObjConnection.connectToServer();
        ObjConnection.SetupStreams();
        System.out.println("uouououo");
        System.out.println("The ArrayList of Fragmint1 is :"+arrayList);
        ObjConnection.output.writeObject(arrayList);
        ObjConnection.output.flush();
        ObjConnection.input.readObject();
        ArrayList<Object> list = (ArrayList<Object>)ObjConnection.input.readObject();
        boolean res = ObjConnection.handleReceivedRequest(list);
        Log.d("res","res:"+res);
        if(!res){

            //            Toast.makeText(getApplicationContext(),"Email not Exist",Toast.LENGTH_SHORT).show();
            ObjConnection.CloseCrap();
            return false;

        }else {
//            Toast.makeText(getApplicationContext(),"Welcome...",Toast.LENGTH_SHORT).show();

            // data.setUser(() list.get(2));
            ObjConnection.CloseCrap();
            return true;
        }

    }

    public ArrayList<Object> serilaizeToStrings(){

        ArrayList<Object>list = new ArrayList<>();
        list.add(4);
        list.add("NoThing");
        return list;
    }

    public boolean checkTheList() throws InterruptedException, IOException, ClassNotFoundException {
        boolean res = Friends_List(serilaizeToStrings());
        System.out.println("aoaoaoao");
        System.out.println("resofcheckTheList:"+res);
        return res;
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

    public void Check_All(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("ooooooooooooo2");
                boolean bool = false;
                try {
                    System.out.println("ooooooooooooo3");
                    bool = checkTheList();
                    System.out.println("popopop");
                    System.out.println("The bool of fragment1 is:"+bool);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();

    }
}
