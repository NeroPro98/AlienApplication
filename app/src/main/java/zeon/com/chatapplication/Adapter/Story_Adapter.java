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

import zeon.com.chatapplication.Model.story;
import zeon.com.chatapplication.R;

public class Story_Adapter extends BaseAdapter {
    private ArrayList <story> mArrayList;
    private Context mContext;
    private LayoutInflater mInflater;

    public Story_Adapter(Context context , ArrayList <story> list){

    this.mContext = context;
    this.mArrayList = list;
    this.mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return mArrayList.size();
    }

    @Override
    public story getItem(int position) {
        return mArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mArrayList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = mInflater.inflate(R.layout.for_peroson_story,null);
        ImageView image = (ImageView)view.findViewById(R.id.storyimage);
        TextView txt1 = (TextView)view.findViewById(R.id.addstory);
        TextView txt2 = (TextView)view.findViewById(R.id.timesotry);

        txt1.setText(mArrayList.get(position).getAddtext());
        txt2.setText(mArrayList.get(position).getDatestory());
        Picasso.with(mContext).load(mArrayList.get(position).getImage()).into(image);



        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        return view;
    }
}
