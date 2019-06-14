package zeon.com.chatapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.IOException;
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

        final View view = mInflater.inflate(R.layout.for_block_menu, null);
        ImageView Image = (ImageView) view.findViewById(R.id.block_image);
        TextView Name = (TextView) view.findViewById(R.id.name);
        TextView Active = (TextView) view.findViewById(R.id.email_block);
        //ImageView SendAdd = (ImageView)view.findViewById(R.id.unblock_menu);
        ImageView UnBlock = (ImageView) view.findViewById(R.id.unblock_menu);
        //Picasso.with(mContext).load(lists.get(position).getS4()).into(Image);
        //Name.setText(lists.get(position).getS1());
        //Active.setText(lists.get(position).getS3());

        UnBlock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int number = position;
                MyApplication data = (MyApplication) mContext.getApplicationContext();
                String Specific_email = (String) data.user.getUser_Block_List().get(position);
                data.setSpecific_Email_Press(Specific_email);
                String User_Curr_Email = data.getUser_Email();

                final ArrayList<Object> arrayList3 = new ArrayList<>();
                arrayList3.add(10);
                arrayList3.add(User_Curr_Email);
                arrayList3.add(Specific_email);


                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            System.out.println("The arraylist :" + arrayList3);
                            ObjConnection.output.writeObject(arrayList3);
                            ArrayList<Object> inputlist = (ArrayList<Object>) ObjConnection.input.readObject();
                            System.out.println("The inputList :" + inputlist);
                            ObjConnection.handleReceivedRequest(inputlist);
                            ObjConnection.output.flush();
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }

                    }
                });
                thread.start();
                int size = data.user.getUser_Block_List().size();
                for(int i=0;i<lists.size();i++) {
                    for (int j = 0; j < size; j++) {
                        if (data.user.getUser_Block_List().get(j).equals(lists.get(i).getDatestory())) {
                            data.user.getUser_Block_List().remove(j);
                            data.user.getUser_Block_List().remove(j);
                            break;
                        }
                    }
                }

                lists.remove(position);
                notifyDataSetChanged(); // here delete it if there problame
            }


        });
        return view;
    }
}
