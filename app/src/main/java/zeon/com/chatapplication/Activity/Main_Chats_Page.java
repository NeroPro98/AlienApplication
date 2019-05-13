package zeon.com.chatapplication.Activity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.SearchView;
//import zeon.com.chatapplication.Adapter.Fragment_Adapter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import zeon.com.chatapplication.Adapter.Fragment_Adapter;
import zeon.com.chatapplication.Fragment.Fragment1;
import zeon.com.chatapplication.Fragment.Fragment3;
import zeon.com.chatapplication.Games.Activity.Games_main;
import zeon.com.chatapplication.Model.UserProfile;
import zeon.com.chatapplication.MyApplication;
import zeon.com.chatapplication.R;
import zeon.com.chatapplication.Register_Page;
import zeon.com.chatapplication.Search_network;
import zeon.com.chatapplication.Setting;
import zeon.com.chatapplication.Style_Change;
import zeon.com.chatapplication.User_Edit_Info;
import zeon.com.chatapplication.Weather.ChangeCityController;
import zeon.com.chatapplication.Weather.WeatherController;
import zeon.com.chatapplication.Weather.WeatherDataModel;

public class Main_Chats_Page extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,SearchView.OnQueryTextListener
{

    private DrawerLayout drawer;
    private ActionBarDrawerToggle Toggle;
    private Toolbar toolbar;
    private CircleImageView user_image;
    private CircleImageView image;
    private Toolbar toolbar2;
    private ViewPager mViewPager;
    private TabLayout mTablLayout;
    private BottomNavigationView mView;
    private UserProfile ObjConnection = new UserProfile();

    ActionBar mActionBar;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar =(Toolbar) findViewById(R.id.nav_action);
        mActionBar = getSupportActionBar();
        drawer = findViewById(R.id.drawer_layout);
        Toggle = new ActionBarDrawerToggle(this,drawer,R.string.open,R.string.close);
        user_image = (CircleImageView)findViewById(R.id.user_circle);
        mView = (BottomNavigationView)findViewById(R.id.bottomview1);

        drawer.addDrawerListener(Toggle);
        Toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Alien Chat");
        NavigationView navigationView = findViewById(R.id.nav_viewnew);
        navigationView.setNavigationItemSelectedListener(this);
        MyApplication data = (MyApplication) getApplicationContext();
        int color = data.getColor();
        System.out.println(color);
        mActionBar.setBackgroundDrawable(new ColorDrawable(0));
        //image= data.getImage();
        //user_image.setImageBitmap();
       /* Bundle bundle = getIntent().getExtras();  //this for set value for image of navigation drawer

        if(bundle !=null) {


            int resid = bundle.getInt("UserImage");
            user_image.setImageResource(resid);

        }*/

        //for the toolbar2
        //  this.getSupportActionBar().setTitle("");

        mViewPager = (ViewPager)findViewById(R.id.viewPager);
        mTablLayout = (TabLayout)findViewById(R.id.tabalLayout);
        mTablLayout.addTab(mTablLayout.newTab().setText("Message"));
        mTablLayout.addTab(mTablLayout.newTab().setText("Status"));
        mTablLayout.addTab(mTablLayout.newTab().setText("Connects"));

        mTablLayout.setTabGravity(TabLayout.GRAVITY_FILL);


        Fragment_Adapter fs
                = new Fragment_Adapter(getSupportFragmentManager(),mTablLayout.getTabCount());

        mViewPager.setAdapter(fs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTablLayout));
        mTablLayout.addOnTabSelectedListener(new  TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

        //mView.setItemIconTintList();

        mView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){


                    case R.id.Search_nav2:
                    //    mView.setItemBackgroundResource(R.color.brown_light);
                        Intent intent2 = new Intent(getApplicationContext(),Search_network.class);
                        startActivity(intent2);
                        return true;

                    case R.id.Person_nav2:
                        //   mView.setItemBackgroundResource(R.color.black);
                        Intent intent1 = new Intent(getApplicationContext(),User_Edit_Info.class);
                        startActivity(intent1);
                        return true;

                    case R.id.Game_nav2:
                     //   mView.setItemBackgroundResource(R.color.yellow);
                        Intent intent3 = new Intent(getApplicationContext(),Games_main.class);
                        startActivity(intent3);
                        return true;

                    case R.id.weather:
                        Intent intent4 = new Intent(getApplicationContext(),ChangeCityController.class);
                        startActivity(intent4);
                        return true;

                    default:
                         return false;
                }

            }
        });

    }



    public void to_image_page(View v){
        Intent intent = new Intent(getApplicationContext(),User_Edit_Info.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
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
            case R.id.Refresh:

                System.out.println("ooooooooooooo");
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("ooooooooooooo2");
                        boolean bool = false;
                        try {
                            System.out.println("ooooooooooooo3");
                            bool = checkTheList();
                            System.out.println("popopop");
                            System.out.println("The bool of fragment1 is:"+bool);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                });
                thread.start();

                break;
            case R.id.message:
                break;
            case R.id.style:
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

        MenuItem menuItem = menu.findItem(R.id.searchforthing);
        SearchView searchView = (SearchView)menuItem.getActionView();
        searchView.setOnQueryTextListener(this);
        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        Log.d("abababa","Personal"+id);
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

        }else if(id == R.id.Personalper){
            Log.d("abababa","Personal");
            Intent intent =new Intent(getApplicationContext(),User_Edit_Info.class);
            startActivity(intent);

        }else if (id == R.id.Search){
            Intent intent =new Intent(getApplicationContext(),Search_network.class);
            startActivity(intent);

        }else if(id==R.id.style){
            Intent intent =new Intent(getApplicationContext(),Style_Change.class);
            startActivity(intent);
        }else if(id==R.id.weather){
            Intent intent4 = new Intent(getApplicationContext(), WeatherController.class);
            startActivity(intent4);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    //For search for specific word in tool bar search
    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {

        String userInput = newText.toLowerCase();
        List<String> newList = new ArrayList<>();
        return false;
    }






    public boolean Friends_List(ArrayList<Object> arrayList) throws IOException, ClassNotFoundException {

        ObjConnection.connectToServer();
        ObjConnection.SetupStreams();
        System.out.println("uouououo");
        System.out.println("The ArrayList of Fragmint1 is :"+arrayList);
        ObjConnection.output.writeObject(arrayList);
        ObjConnection.output.flush();
        ObjConnection.input.readObject();
        // System.out.println("readObject:"+ObjConnection.input.readObject().toString());
        ArrayList<Object> list = (ArrayList<Object>)ObjConnection.input.readObject();
        boolean res = ObjConnection.handleReceivedRequest(list);
        Log.d("res","res:"+res);
        if(!res){

            //            Toast.makeText(getApplicationContext(),"Email not Exist",Toast.LENGTH_SHORT).show();
            ObjConnection.CloseCrap();
            return false;

        }else {
//            Toast.makeText(getApplicationContext(),"Welcome...",Toast.LENGTH_SHORT).show();

            // data.setUser(() list.get(2));
            ObjConnection.CloseCrap();
            return true;
        }

    }

    public ArrayList<Object> serilaizeToStrings(){

        System.out.println("kkkkkkkk");
        ArrayList<Object>list = new ArrayList<>();
        list.add(4);
        System.out.println("kkkkkkkk2");
        return list;
    }

    public boolean checkTheList() throws InterruptedException, IOException, ClassNotFoundException {
        boolean res = Friends_List(serilaizeToStrings());
        System.out.println("aoaoaoao");
        System.out.println("resofcheckTheList:"+res);
        return res;
    }
 //   public void SendRequestForList(){



   // }


}
