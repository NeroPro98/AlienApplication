package zeon.com.chatapplication;

import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import java.lang.reflect.Type;

public class Chat_Page extends AppCompatActivity{



    protected ListView MyListView;
    protected EditText TypeText;
    protected ImageButton Send_Button;

    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat__page);



       // Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        //drawer = findViewById(R.id.drawer_layout);
        //ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawer,toolbar,R.string.open,R.string.close);
        //drawer.addDrawerListener(toggle);
        //toggle.syncState();
        // getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        MyListView = (ListView)findViewById(R.id.Chat_List);
        TypeText = (EditText)findViewById(R.id.Message_Type);
        Send_Button = (ImageButton)findViewById(R.id.sendButton);

    }

    public void Send_btn(View view) {

        String Msg = TypeText.getText().toString();
        Client client = new Client();
        client.setMessage(Msg);

        //Log.d("ChatApp","message:"+TypeText.getText().toString());
    }



  /*  @Override
    public void onBackPressed(){
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }*/



}



