package zeon.com.chatapplication.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import zeon.com.chatapplication.Fragment.Fragment1;
import zeon.com.chatapplication.Fragment.Fragment2;
import zeon.com.chatapplication.Fragment.Fragment3;


public class Fragment_Adapter extends FragmentStatePagerAdapter {

    private int tabs;

    public Fragment_Adapter(FragmentManager fm, int tab) {

        super(fm);
        Log.d("Fragment_Adapter", "was called");
        this.tabs = tab;
    }

    @Override
    public Fragment getItem(int position) {
        Log.d("alien", "position is :" + position);

        Fragment fragment = null;
        if (position == 0) {
            //Fragment3 f3 = new Fragment3();
            fragment = new Fragment3();
            System.out.println("Fragment 3 called");
            return fragment;
        } else if (position == 1) {
            fragment = new Fragment2();
            System.out.println("Fragment 2 called");
            return fragment;
        } else if (position == 2) {
            fragment = new Fragment1();
            System.out.println("Fragment 1 called");
            return fragment;

        } else {
            return null;
        }
        //return fragment;
    }

    @Override
    public int getCount() {
        return tabs;
    }
}
