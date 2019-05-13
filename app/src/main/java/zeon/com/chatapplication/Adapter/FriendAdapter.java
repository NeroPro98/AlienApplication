package zeon.com.chatapplication.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import zeon.com.chatapplication.Activity.ChatActivity;
import zeon.com.chatapplication.Model.FriendComp;
import zeon.com.chatapplication.Model.story;
import zeon.com.chatapplication.R;

public class FriendAdapter extends BaseAdapter {
    private ArrayList<FriendComp> lists;
    private Context mContext;
    private LayoutInflater mInflater;

    public FriendAdapter(Context context , ArrayList <FriendComp> list){

        this.mContext = context;
        this.lists = list;
        this.mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }
    @Override
    public int getCount() {
        return lists.size();
    }

    @Override
    public Object getItem(int position) {
        return lists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return lists.get(position).getId();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final View view = mInflater.inflate(R.layout.for_freind,null);
        ImageView Image = (ImageView)view.findViewById(R.id.Friendimage);
        TextView Name = (TextView)view.findViewById(R.id.Name_Friend);
        TextView Active = (TextView)view.findViewById(R.id.Active);

        Picasso.with(mContext).load(lists.get(position).getS4()).into(Image);
        Name.setText(lists.get(position).getS1());
        Active.setText(lists.get(position).getS3());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        return view;
    }
}
