package zeon.com.chatapplication;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.PagerAdapter;
import android.text.Layout;
import android.transition.Slide;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.ObjectInputStream;

public class SliderAdapter extends PagerAdapter {

    Context mContext;
    LayoutInflater mInflater;

    public SliderAdapter(Context context) {
        this.mContext = context;
    }

    /*public int ArrayLayout[]={
           R.id.background,
           R.id.back1,
           R.id.back2,
           R.id.back3,
    };*/

    public int[] ArrayImage = {
            R.drawable.icon2withbackground,
            R.drawable.icon4withback,
            R.drawable.alienbackground4,

    };

    public String[] textback1 = {
            "Chat and Security",
            "Make new groups",
            "Add new Aliens"
    };

    public String[] textback2 = {
            "Make a security text with your friends",
            "Make new groups with friends and enjoy chatting",
            "Make enjoy with your friends"
    };

    //   public int textback3[]={

    // };

    public int[] ArrayBackground = {
            R.drawable.newback1,
            R.drawable.newback2,
            R.drawable.newback3,
    };

    public String[] Slide_heading = {

            "Background",
            "Back1",
            "Back2",
            "Back3"
    };

    @Override
    public int getCount() {
        return Slide_heading.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (ConstraintLayout) object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int postion) {

        mInflater = (LayoutInflater) mContext.getSystemService(mContext.LAYOUT_INFLATER_SERVICE);
        View view = mInflater.inflate(R.layout.activity_back1, container, false);


        //ConstraintLayout Backlayout = (ConstraintLayout)view.findViewById(R.id.background);
        ImageView SlideImage = (ImageView) view.findViewById(R.id.icon3);
        TextView UpText = (TextView) view.findViewById(R.id.text1);
        TextView DownText = (TextView) view.findViewById(R.id.text2);
        ImageView background = (ImageView) view.findViewById(R.id.back1);


        //Drawable background = view.findViewById(R.drawable.background1);


        SlideImage.setImageResource(ArrayImage[postion]);
        UpText.setText(textback1[postion]);
        DownText.setText(textback2[postion]);
        background.setImageResource(ArrayBackground[postion]);

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int postion, Object object) {  // to end slide after the ending slide
        container.removeView((RelativeLayout)object);
    }
}
