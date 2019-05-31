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
    FriendAdapter adapter;


    public void init() {
        int i;
     //   int size = (int) list.get(2);
        //MyApplication data = (MyApplication) getActivity().getApplicationContext();
        MyApplication data = (MyApplication)MyApplication.getAppContext().getApplicationContext();

        list =(ArrayList)data.user.getUser_List();
     //   list.remove(0);
      //  list.remove(0);
       // list.remove(0);
        for (i = 0; i < list.size(); i = i + 3) {
            //   Friend.add(new FriendComp(1, "Mohamad Nesart", "April", "https://www.google.com/url?sa=i&source=images&cd=&ved=2ahUKEwjR_qew--HhAhWMxoUKHRKwCA0QjRx6BAgBEAU&url=http%3A%2F%2Fsteezo.com%2F%3Fproduct%3Dman-in-stripped-suit&psig=AOvVaw0BK6qUf6tcpUZ1lNMSG0bo&ust=1555962818897341"));
            // Friend.add(new FriendComp(1,"Mohamad Al Moazen","April","https://www.google.com/url?sa=i&source=images&cd=&ved=2ahUKEwjR_qew--HhAhWMxoUKHRKwCA0QjRx6BAgBEAU&url=http%3A%2F%2Fsteezo.com%2F%3Fproduct%3Dman-in-stripped-suit&psig=AOvVaw0BK6qUf6tcpUZ1lNMSG0bo&ust=1555962818897341"));
            Friend.add(new FriendComp(1,(String) list.get(i+1) ,(String) list.get(i) , "https://www.google.com/url?sa=i&source=images&cd=&ved=2ahUKEwjR_qew--HhAhWMxoUKHRKwCA0QjRx6BAgBEAU&url=http%3A%2F%2Fsteezo.com%2F%3Fproduct%3Dman-in-stripped-suit&psig=AOvVaw0BK6qUf6tcpUZ1lNMSG0bo&ust=1555962818897341"));
           // EmailList.add((String) list.get(i + 1));
        }


        data.setEmails(data.user.getUserFriends());
        System.out.println("The Email List is :" + data.getUser_Email());
        data.getUser_Email();
        try {
            adapter.notifyDataSetInvalidated();
        } catch (Exception e) {
            System.out.println("The Exciption" + e);
            e.printStackTrace();
        }


    }

    public boolean Friends_List(ArrayList<Object> arrayList) throws IOException, ClassNotFoundException {


        ObjConnection.connectToServer();
        ObjConnection.SetupStreams();
        System.out.println("The ArrayList of Fragmint1 is :" + arrayList);
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
//
            ObjConnection.CloseCrap();
            return true;
        }

    }

    public ArrayList<Object> serilaizeToStrings() {
        MyApplication data = (MyApplication) getContext().getApplicationContext();
        ArrayList<Object> list = new ArrayList<>();
        list.add(4);
        list.add(data.getUser_Email());
        // list.add(email);
        return list;
    }

    public boolean checkTheList() throws InterruptedException, IOException, ClassNotFoundException {
        boolean res = Friends_List(serilaizeToStrings());

        System.out.println("resofcheckTheList:" + res);
        return res;
    }

    public void Check_All() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                boolean bool = false;
                try {

                    bool = checkTheList();

                    System.out.println("The bool of fragment1 is:" + bool);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                if (bool) {
                    init();
                }
            }
        });
        thread.start();

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.layout_fragment1, container, false);
        listview = (ListView) view.findViewById(R.id.ListViewMessage1);
        //Check_All();
        init();
        adapter = new FriendAdapter(getContext(), Friend);
        Log.d("AlienChat", "Context" + getContext());
        Log.d("AlienChat", "message" + Friend);

        listview.setAdapter(adapter);


        return view;

    }


    public boolean Add_Freind_SetUp(ArrayList<Object> arrayList) throws IOException, ClassNotFoundException {

        ObjConnection.connectToServer();
        ObjConnection.SetupStreams();
        System.out.println("The ArrayList of Fragmint1 is :" + arrayList);
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

    private Context context;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    public ArrayList<Object> serilaizeToStringsForAddFriend(String email, String email2) {


        ArrayList<Object> list = new ArrayList<>();
        //SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        //  String email =sharedPreferences.getString("The_User_Email_Click","");
        list.add(6);
        list.add(email2);
        list.add(email);
        //  MyApplication data = (MyApplication)getContext().getApplicationContext();
        //   list.add(data.getSpecific_Email_Press());
        return list;
    }


    public boolean Send_Add_Request_ToServer(String email, String email2) throws InterruptedException, IOException, ClassNotFoundException {
        boolean res = Add_Freind_SetUp(serilaizeToStringsForAddFriend(email, email2));

        return res;
    }


    public void Check_Answer_Freind(final String email, final String User_Email) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                boolean bool = false;
                try {

                    bool = Send_Add_Request_ToServer(email, User_Email);

                    System.out.println("The bool of fragment1 is:" + bool);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                if (bool) {

                    System.out.println("Success added");

                } else {
                    System.out.println("Reject added");
                }

            }
        });
        thread.start();
    }


 /*   @Override
    public void onResume() {
        super.onResume();
        Check_All();
    }*/
}
