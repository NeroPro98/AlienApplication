package zeon.com.chatapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import zeon.com.chatapplication.Model.FriendComp;
import zeon.com.chatapplication.Model.UserProfile;
import zeon.com.chatapplication.Model.story;
import zeon.com.chatapplication.MyApplication;
import zeon.com.chatapplication.R;

public class BlockPage_Adapter extends BaseAdapter {

    private ArrayList<story> lists;
    private Context mContext;
    private LayoutInflater mInflater;
    MyApplication data = (MyApplication) MyApplication.getAppContext();
    private UserProfile ObjConnection = data.getUser();

    public BlockPage_Adapter(Context context, ArrayList<story> list) {

        this.mContext = context;
        this.lists = list;
        this.mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    public  BlockPage_Adapter(){

    }



    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final View view = mInflater.inflate(R.layout.for_block_menu, null);
        ImageView Image = (ImageView) view.findViewById(R.id.block_image);
        TextView Name = (TextView) view.findViewById(R.id.name);
        TextView Active = (TextView) view.findViewById(R.id.email_block);
        ImageView SendAdd = (ImageView)view.findViewById(R.id.unblock_menu);

        //Picasso.with(mContext).load(lists.get(position).getS4()).into(Image);
        //Name.setText(lists.get(position).getS1());
        //Active.setText(lists.get(position).getS3());


        return view;
    }
}
