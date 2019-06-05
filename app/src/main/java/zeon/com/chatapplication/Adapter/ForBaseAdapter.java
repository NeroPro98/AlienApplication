package zeon.com.chatapplication.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

import zeon.com.chatapplication.Activity.ChatActivity;
import zeon.com.chatapplication.Model.MessagePerson;
import zeon.com.chatapplication.Model.MessagePerson;
import zeon.com.chatapplication.MyApplication;
import zeon.com.chatapplication.R;

public class ForBaseAdapter extends BaseAdapter{

    private ArrayList<MessagePerson> lists;
    private LayoutInflater mInflater;
    private Context mContext;


    public ForBaseAdapter(Context context,ArrayList<MessagePerson> listmsg){
        this.mContext = context;
        this.lists = listmsg;
        this.mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return lists.size();
    }

    @Override
    public MessagePerson getItem(int position) {
        return lists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, final View convertView, ViewGroup parent) {
        
        final View view = mInflater.inflate(R.layout.person_menu,null);
        ImageView Image = (ImageView)view.findViewById(R.id.personimage);
        TextView Name = (TextView)view.findViewById(R.id.personename);
        TextView Message = (TextView)view.findViewById(R.id.personemessage);
        TextView Date = (TextView)view.findViewById(R.id.date);

        Picasso.with(mContext).load(lists.get(position).getS4()).into(Image);
        Name.setText(lists.get(position).getS1());
        Message.setText(lists.get(position).getS2());
        Date.setText(lists.get(position).getS3());

        view.setOnClickListener(
                new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(view.getContext(), ChatActivity.class);
                intent.putExtra("name",lists.get(position).getS1());
                intent.putExtra("userEmail",lists.get(position).getEmail());


                mContext.startActivity(intent);
            }
        });

        Animation animation = AnimationUtils.loadAnimation(mContext,R.anim.slide_left);
        view.startAnimation(animation);

        return view;
    }
}
