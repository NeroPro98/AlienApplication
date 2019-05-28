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
    /*    switch (position){
           case 0:
               Fragment3 f3 = new Fragment3();
               return f3;

             case 2:
                Fragment2 f2 = new Fragment2();
                return f2;
            case 3:
                Fragment1 f1 = new Fragment1();
                return f1;
            default:
                return null;
        }*/
        if (position == 0) {
            Fragment3 f3 = new Fragment3();
            return f3;
        } else if (position == 1) {
            Fragment2 f2 = new Fragment2();
            return f2;
        } else if (position == 2) {
            Fragment1 f1 = new Fragment1();

            return f1;

        } else {
            return null;
        }
    }

    @Override
    public int getCount() {
        return tabs;
    }
}
