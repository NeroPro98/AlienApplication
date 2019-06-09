package zeon.com.chatapplication.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import zeon.com.chatapplication.Adapter.BlockPage_Adapter;
import zeon.com.chatapplication.Adapter.FriendAdapter;
import zeon.com.chatapplication.Adapter.Story_Adapter;
import zeon.com.chatapplication.Model.FriendComp;
import zeon.com.chatapplication.Model.UserProfile;
import zeon.com.chatapplication.Model.story;
import zeon.com.chatapplication.MyApplication;
import zeon.com.chatapplication.R;

/*public class BlockPage_Fragment extends Fragment {

    ArrayList<FriendComp> BlockFriend = new ArrayList<>();
    private ListView listviewBlock;

    MyApplication data = (MyApplication) MyApplication.getAppContext().getApplicationContext();
    private UserProfile ObjConnection = data.getUser();
    BlockPage_Adapter adapter = new BlockPage_Adapter();
    private SwipeRefreshLayout mRefreshLayout;


    public void init(ArrayList<Object> list2) {
        BlockFriend.add(new FriendComp(1, (String) "Mohamad", "www.nero98@gmail.com", "https://www.google.com/url?sa=i&source=images&cd=&ved=2ahUKEwjR_qew--HhAhWMxoUKHRKwCA0QjRx6BAgBEAU&url=http%3A%2F%2Fsteezo.com%2F%3Fproduct%3Dman-in-stripped-suit&psig=AOvVaw0BK6qUf6tcpUZ1lNMSG0bo&ust=1555962818897341"));
        BlockFriend.add(new FriendComp(1, (String) "Mohamad", "www.nero98@gmail.com", "https://www.google.com/url?sa=i&source=images&cd=&ved=2ahUKEwjR_qew--HhAhWMxoUKHRKwCA0QjRx6BAgBEAU&url=http%3A%2F%2Fsteezo.com%2F%3Fproduct%3Dman-in-stripped-suit&psig=AOvVaw0BK6qUf6tcpUZ1lNMSG0bo&ust=1555962818897341"));
        adapter.notifyDataSetChanged();
    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        final View view = inflater.inflate(R.layout.block_page, container, false);
        listviewBlock = (ListView) view.findViewById(R.id.ListViewMessage1);
        final ArrayList<Object> list3 = (ArrayList) data.getGetUser_List_Every_init();
        init(list3);
        adapter = new BlockPage_Adapter(getContext(), BlockFriend);
        Log.d("AlienChat", "Context" + getContext());
        Log.d("AlienChat", "message" + BlockFriend);
        listviewBlock.setAdapter(adapter);
        mRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout);
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                init(list3);


                mRefreshLayout.setRefreshing(false);

            }
        });


        return view;
    }
}*/