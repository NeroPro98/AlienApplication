package zeon.com.chatapplication.Activity;

import android.app.Fragment;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager.OnAdapterChangeListener;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.support.v7.widget.Toolbar;
import android.widget.TableLayout;

//import zeon.com.chatapplication.Adapter.Fragment_Adapter;
import zeon.com.chatapplication.Adapter.Fragment_Adapter;
import zeon.com.chatapplication.Chat_Page;
import zeon.com.chatapplication.Fragment.Fragment1;
import zeon.com.chatapplication.Games_main;
import zeon.com.chatapplication.R;
import zeon.com.chatapplication.Register_Page;
import zeon.com.chatapplication.Search_network;
import zeon.com.chatapplication.Setting;
import zeon.com.chatapplication.User_Edit_Info;

public class Main_Chats_Page extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
{

    private DrawerLayout drawer;
    private ActionBarDrawerToggle Toggle;
    private Toolbar toolbar;
    private ImageView user_image;
    private Toolbar toolbar2;
    private ViewPager mViewPager;
    private TabLayout mTablLayout;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar =(Toolbar) findViewById(R.id.nav_action);
        //setSupportActionBar(toolbar);
        //toolbar2 = (Toolbar)findViewById(R.id.toolbar2);
       // setSupportActionBar(toolbar2);
        //for the main tool bar 1
        drawer = findViewById(R.id.drawer_layout);
        Toggle = new ActionBarDrawerToggle(this,drawer,R.string.open,R.string.close);
        drawer.addDrawerListener(Toggle);
        Toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        user_image = (ImageView)findViewById(R.id.user_circle);

        //for the toolbar2
      //  this.getSupportActionBar().setTitle("");

        mViewPager = (ViewPager)findViewById(R.id.viewPager);
        mTablLayout = (TabLayout)findViewById(R.id.tabalLayout);
        mTablLayout.addTab(mTablLayout.newTab().setText("Message"));
        mTablLayout.addTab(mTablLayout.newTab().setText("Connects"));
        mTablLayout.addTab(mTablLayout.newTab().setText("Status"));

        mTablLayout.setTabGravity(TabLayout.GRAVITY_FILL);


       Fragment_Adapter fs
                = new Fragment_Adapter(getSupportFragmentManager(),mTablLayout.getTabCount());
        //Log.d("value","getTabCount():"+fs.getCount());
        //Log.d("value","getTabCount2():"+fs.getItem(2));
        mViewPager.setAdapter(fs); //******* the error

       mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTablLayout));
       mTablLayout.addOnTabSelectedListener(new  TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

    }



    public void to_image_page(View v){
        Intent intent = new Intent(getApplicationContext(),User_Edit_Info.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed(){
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        switch (item.getItemId()){

            case R.id.setting:
                break;
            case R.id.new_group:
                break;
            case R.id.search:
                break;
            case R.id.message:
                break;
        }




        if(Toggle.onOptionsItemSelected(item)){
            return true;
        }
        return  super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
            Intent intent =new Intent(getApplicationContext(),Register_Page.class);
            startActivity(intent);
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_Game) {
            Intent intent =new Intent(getApplicationContext(),Games_main.class);
            startActivity(intent);

        } else if (id == R.id.nav_manage) {
            Intent intent =new Intent(getApplicationContext(),Setting.class);
            startActivity(intent);

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }else if(id == R.id.Personal){
            Intent intent =new Intent(getApplicationContext(),User_Edit_Info.class);
            startActivity(intent);

        }else if(id == R.id.Chat){
            Intent intent =new Intent(getApplicationContext(),Chat_Page.class);
            startActivity(intent);

        }else if (id == R.id.Search){
            Intent intent =new Intent(getApplicationContext(),Search_network.class);
            startActivity(intent);
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
