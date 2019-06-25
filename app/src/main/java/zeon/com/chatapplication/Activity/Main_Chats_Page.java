package zeon.com.chatapplication.Activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
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
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.SearchView;
import android.widget.ImageView;
import android.widget.Toast;
//import zeon.com.chatapplication.Adapter.Fragment_Adapter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;
import zeon.com.chatapplication.Adapter.BlockPage_Adapter;
import zeon.com.chatapplication.Adapter.Fragment_Adapter;
import zeon.com.chatapplication.Fragment.Fragment1;
import zeon.com.chatapplication.Fragment.Fragment2;
import zeon.com.chatapplication.Games.Activity.Games_main;
import zeon.com.chatapplication.Model.UserProfile;
import zeon.com.chatapplication.MyApplication;
import zeon.com.chatapplication.R;
import zeon.com.chatapplication.Register_Page;
import zeon.com.chatapplication.Search_network;
import zeon.com.chatapplication.Setting;
import zeon.com.chatapplication.Style_Change;
import zeon.com.chatapplication.User_Edit_Info;
import zeon.com.chatapplication.Weather.WeatherController;

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
    private ActionBar mActionBar;
    private ImageView SendAdd;
    private Fragment1 fragment1 = new Fragment1();
    private Fragment2 fragment2 = new Fragment2();

    MyApplication data = (MyApplication) MyApplication.getAppContext();
    private UserProfile ObjConnection = data.getUser();


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        LoadLocal();
        setContentView(R.layout.activity_main);


        toolbar =(Toolbar) findViewById(R.id.nav_action);
        mActionBar = getSupportActionBar();
        //mActionBar
        drawer = findViewById(R.id.drawer_layout);
        Toggle = new ActionBarDrawerToggle(this,drawer, R.string.open, R.string.close);
        user_image = (CircleImageView)findViewById(R.id.user_circle);
        mView = (BottomNavigationView)findViewById(R.id.bottomview1);
        SendAdd = (ImageView)findViewById(R.id.add_friend);

        drawer.addDrawerListener(Toggle);
        Toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Alien Chat");
        NavigationView navigationView = findViewById(R.id.nav_viewnew);
        navigationView.setNavigationItemSelectedListener(this);
        final MyApplication data = (MyApplication) getApplicationContext();
        int color = data.getColor();
        System.out.println(color);
        mActionBar.setBackgroundDrawable(new ColorDrawable(0));
        //mActionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.background5));
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
        mTablLayout.addTab(mTablLayout.newTab().setIcon(R.drawable.chat_icon));
        mTablLayout.addTab(mTablLayout.newTab().setIcon(R.drawable.ic_group_black_24dp));
        mTablLayout.addTab(mTablLayout.newTab().setIcon(R.drawable.person_add));
        //mTablLayout.setBackgroundColor(getResources().getColor(R.color.black));
        mTablLayout.setTabGravity(TabLayout.GRAVITY_FILL);

     //   mTablLayout.set
       /* mTablLayout.addOnLayoutChangeListener(new View.OnLayoutChangeListener() { ////////////////////////////Here Check the listener of the taps
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {

            }
        });*/
     //  mTablLayout.OnTabSelectedListener(new ArrayList<>()


        final Fragment_Adapter fs
                = new Fragment_Adapter(getSupportFragmentManager(),mTablLayout.getTabCount());

        mTablLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                switch (tab.getPosition()) {
                    case 0:
                        System.out.println("Fragment 1 ababa");



                        break;
                    case 1:
                        System.out.println("The User Friend Email is of case1"+data.user.getUserFriends());
                        System.out.println("Fragment 2 ababa");
                        System.out.println("The User Emails  is"+data.getEmails());
                        ArrayList <String> FriendEmails = data.user.getUser_Friend_Emails();
                        System.out.println("The  FriendEmails  is:"+FriendEmails);
                        Thread thread1 = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    ArrayList arrayList = serilaizeToStringsForFriendList();
                                    System.out.println("The arraylist :"+arrayList);
                                    ObjConnection.output.writeObject(arrayList);
                                    final ArrayList<Object> inputlist =(ArrayList<Object>)ObjConnection.input.readObject();
                                    System.out.println("The inputList :"+inputlist);
                                    ObjConnection.handleReceivedRequest(inputlist);
                                    //data.setHelper_List(inputlist);
                                    ObjConnection.output.flush();
                                    data.setUserFriend_List_Every_init(inputlist);
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                        //    fragment2.InitStory(inputlist);

                                            Toast.makeText(getApplicationContext(),"Fragment2",Toast.LENGTH_SHORT).show();
                                          //  fragment2.notifyAll();
                                        }
                                    });

                                } catch (IOException e) {
                                    e.printStackTrace();
                                } catch (ClassNotFoundException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                        thread1.start();





                        break;
                    case 2:
                        System.out.println("Fragment 1 ababa");


                        Thread thread = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    ArrayList arrayList = serilaizeToStrings();
                                    System.out.println("The arraylist :"+arrayList);
                                    ObjConnection.output.writeObject(arrayList);
                                    final ArrayList<Object> inputlist =(ArrayList<Object>)ObjConnection.input.readObject();
                                    System.out.println("The inputList :"+inputlist);
                                    data.user.getUser_List();
                                    //data.setThe_User_List_From_Server(inputlist);
                                    ObjConnection.handleReceivedRequest(inputlist);
                                    ObjConnection.output.flush();
                                    data.setGetUser_List_Every_init(inputlist);
                                   // fragment1.init(inputlist);
                                    runOnUiThread(new Runnable() {  //Don't work
                                        @Override
                                        public void run() {
                                            //fragment1.init(inputlist);
                                            Toast.makeText(getApplicationContext(),"Fragment1",Toast.LENGTH_SHORT).show();
                                           // fragment1.notifyAll();
                                        }
                                    });
                                    //fs.notifyDataSetChanged();

                                } catch (IOException e) {
                                    e.printStackTrace();
                                } catch (ClassNotFoundException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                        thread.start();


                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });



        mViewPager.setAdapter(fs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTablLayout));
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            } ////////////////////////////////////////////////////
            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
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
                         //  mView.setItemIconTintList(getReso);
                        Intent intent1 = new Intent(getApplicationContext(),User_Edit_Info.class);
                        startActivity(intent1);
                        return true;

                    case R.id.Game_nav2:
                        //   mView.setItemBackgroundResource(R.color.yellow);
                        Intent intent3 = new Intent(getApplicationContext(),Games_main.class);
                        startActivity(intent3);
                        return true;

                    case R.id.weather:
                        Intent intent4 = new Intent(getApplicationContext(),WeatherController.class);
                        startActivity(intent4);
                        return true;

                    case R.id.nav_manage:


                        return true;

                    default:
                        return false;
                }

            }
        });

     /*   SendAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment1 fragment1 = new Fragment1();
                fragment1.Check_Answer_Freind();
            }
        });*/

    }

    private void setLocale(String lang) {

        Locale locale = new Locale(lang);
        locale.setDefault(locale);
        Configuration configuration = new Configuration();
        configuration.locale = locale;
        getBaseContext().getResources().updateConfiguration(configuration,getBaseContext().getResources().getDisplayMetrics());
        //Save data in shared Preference
        SharedPreferences.Editor editor  = getSharedPreferences("Settings",MODE_PRIVATE).edit();
        editor.putString("App_Lang",lang);
        editor.apply();

    }

    //To Load Lang That Saved In Shared Preferences
    public void LoadLocal(){
        SharedPreferences sharedPreferences = getSharedPreferences("Settings", Activity.MODE_PRIVATE);
        String lang = sharedPreferences.getString("App_Lang","");
        setLocale(lang);
    }

    public ArrayList<Object> serilaizeToStrings() {
        ArrayList<Object> list = new ArrayList<>();
        list.add(4);
        list.add(data.getUser_Email());
        // list.add(email);
        return list;
    }

    public ArrayList<Object> serilaizeToStringsForFriendList() {
        ArrayList<Object> list = new ArrayList<>();
        list.add(11);
        list.add(data.getUser_Email());
        // list.add(email);
        return list;
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
            SetLanguage();
        }else if (id == R.id.nav_logout) {

        }else if (id == R.id.adduser) {

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

        }else if(id==R.id.Block_Page){
            Intent intent5 = new Intent(getApplicationContext(), Block_Page.class);
            System.out.println("HAHAHA");
            startActivity(intent5);
            System.out.println("AKAKAKA");
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



public void SetLanguage(){

    final String[] LangList = {"English","Le français","日本語"};
    AlertDialog.Builder builder = new AlertDialog.Builder(Main_Chats_Page.this);
    builder.setTitle("Choose Language...");
    builder.setSingleChoiceItems(LangList, -1, new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            if(which==0){
                setLocale("en");
                recreate();
            }
            else if(which==1){
                setLocale("oc");
                recreate();
            }
            else if(which==2){
                setLocale("ja");
                recreate();
            }

            dialog.dismiss();
        }


    });

    AlertDialog alertDialog = builder.create();
    alertDialog.show();

}



    //   public void SendRequestForList(){



    // }


}
