package zeon.com.chatapplication.Activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;

import zeon.com.chatapplication.Adapter.BlockPage_Adapter;

import zeon.com.chatapplication.Adapter.FriendAdapter;
import zeon.com.chatapplication.Adapter.Story_Adapter;
import zeon.com.chatapplication.Model.FriendComp;
import zeon.com.chatapplication.Model.UserProfile;
import zeon.com.chatapplication.Model.story;
import zeon.com.chatapplication.MyApplication;
import zeon.com.chatapplication.R;


public class Block_Page extends AppCompatActivity {


    ArrayList<Object> array = new ArrayList<>();
    private ListView BlockList;
    private TextView text;
    ArrayList<story> BlockFriend = new ArrayList<>();
    private ListView listviewBlock;
    private ArrayList<Object> helper_List = new ArrayList<>();
    private ArrayList<String> helper_List2 = new ArrayList<>();

    MyApplication data = (MyApplication) MyApplication.getAppContext().getApplicationContext();
    private UserProfile ObjConnection = data.getUser();
    BlockPage_Adapter adapter = new BlockPage_Adapter();
    // final ArrayList ListBlockFriend = new ArrayList();
    //           ListBlockFriend.add();
    private SwipeRefreshLayout mRefreshLayout;


    public void init(ArrayList<Object> list2) {
        BlockFriend.removeAll(BlockFriend);
        //BlockFriend.add(new story(1, (String) "Mohamad", "www.nero98@gmail.com", "https://www.google.com/url?sa=i&source=images&cd=&ved=2ahUKEwjR_qew--HhAhWMxoUKHRKwCA0QjRx6BAgBEAU&url=http%3A%2F%2Fsteezo.com%2F%3Fproduct%3Dman-in-stripped-suit&psig=AOvVaw0BK6qUf6tcpUZ1lNMSG0bo&ust=1555962818897341"));
        if (list2.size() != 0) {
            for (int i = 0; i < list2.size(); i = i + 2) {
                BlockFriend.add(new story(1, "https://www.google.com/url?sa=i&source=images&cd=&cad=rja&uact=8&ved=2ahUKEwjB4IbhheLhAhUvxYUKHZESChQQjRx6BAgBEAU&url=https%3A%2F%2Fwww.almasryalyoum.com%2Fnews%2Fdetails%2F998120&psig=AOvVaw0BK6qUf6tcpUZ1lNMSG0bo&ust=1555962818897341", (String) list2.get(i + 1), (String) list2.get(i)));

            }
        }
        //BlockFriend.add(new story(1, "https://www.google.com/url?sa=i&source=images&cd=&cad=rja&uact=8&ved=2ahUKEwjB4IbhheLhAhUvxYUKHZESChQQjRx6BAgBEAU&url=https%3A%2F%2Fwww.almasryalyoum.com%2Fnews%2Fdetails%2F998120&psig=AOvVaw0BK6qUf6tcpUZ1lNMSG0bo&ust=1555962818897341", "Adnan Ktan", "June"));
        adapter.notifyDataSetChanged();
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.block_page);

        //listviewBlock = (ListView)findViewById(R.id.ListViewMessage1);
        BlockList = (ListView) findViewById(R.id.ListViewBlock);
        text = (TextView) findViewById(R.id.Block_Menu);
        //final ArrayList<Object> list3 = (ArrayList) data.getGetUser_List_Every_init();
        final ArrayList<Object> list3 = (ArrayList) data.user.getUser_Block_List();
        if (list3.size() != 0) {
            init(list3);
        }
        //BlockFriend.add(new FriendComp(1, (String) "Mohamad", "www.nero98@gmail.com", "https://www.google.com/url?sa=i&source=images&cd=&ved=2ahUKEwjR_qew--HhAhWMxoUKHRKwCA0QjRx6BAgBEAU&url=http%3A%2F%2Fsteezo.com%2F%3Fproduct%3Dman-in-stripped-suit&psig=AOvVaw0BK6qUf6tcpUZ1lNMSG0bo&ust=1555962818897341"));
        //BlockFriend.add(new FriendComp(1, (String) "Mohamad", "www.nero98@gmail.com", "https://www.google.com/url?sa=i&source=images&cd=&ved=2ahUKEwjR_qew--HhAhWMxoUKHRKwCA0QjRx6BAgBEAU&url=http%3A%2F%2Fsteezo.com%2F%3Fproduct%3Dman-in-stripped-suit&psig=AOvVaw0BK6qUf6tcpUZ1lNMSG0bo&ust=1555962818897341"));


        //      Log.d("AlienChat", "Context" + getApplicationContext());
//        Log.d("AlienChat", "message" + BlockFriend);

        mRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout_frag1);
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                System.out.println("I am Refresh Swap");
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        ArrayList arrayList = serilaizeToStringsForBlockList();
                        System.out.println("The arraylist :" + arrayList);
                        try {
                            ObjConnection.output.writeObject(arrayList);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        final ArrayList<Object> inputlist;
                        try {
                            inputlist = (ArrayList<Object>) ObjConnection.input.readObject();
                            ObjConnection.output.flush();
                            helper_List = inputlist;
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }


                    }
                });
                thread.start();


                if (helper_List.size() > 2) {
                    helper_List.remove(0);
                    helper_List.remove(0);
                    // helper_List.remove(0);
                    data.user.setUser_Block_List(helper_List);
                    init(helper_List);
                }

                /*if (helper_List.size() >= 2) {

                }*/
                //Story_Adapter adapter= new Story_Adapter(getApplicationContext(), BlockFriend);
                BlockPage_Adapter adapter = new BlockPage_Adapter(getApplicationContext(), BlockFriend);
                BlockList.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                mRefreshLayout.setRefreshing(false);

            }
        });


    }

    public ArrayList<Object> serilaizeToStringsForBlockList() {
        ArrayList<Object> list = new ArrayList<>();
        list.add(20);
        list.add(data.getUser_Email());
        // list.add(email);
        return list;
    }
}
