package zeon.com.chatapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

import zeon.com.chatapplication.Model.MessagePerson;
import zeon.com.chatapplication.Model.MessagePerson;
import zeon.com.chatapplication.R;

public class ForBaseAdapter extends BaseAdapter{

    private ArrayList<MessagePerson> list;
    private LayoutInflater mInflater;
    private Context mContext;


    public ForBaseAdapter(Context context,ArrayList<MessagePerson> listmsg){

        this.mContext = context;
        this.list = listmsg;
        this.mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public MessagePerson getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = mInflater.inflate(R.layout.person_menu,null);
        ImageView Image = (ImageView)view.findViewById(R.id.personimage);
        TextView Name = (TextView)view.findViewById(R.id.personename);
        TextView Message = (TextView)view.findViewById(R.id.personimage);
        TextView Date = (TextView)view.findViewById(R.id.date);

        Picasso.with(mContext).load(list.get(position).getS4()).into(Image);
        Name.setText(list.get(position).getS1());
        Name.setText(list.get(position).getS2());
        Name.setText(list.get(position).getS3());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        return view;
    }
}
