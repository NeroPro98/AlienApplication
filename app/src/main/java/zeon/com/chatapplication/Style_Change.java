package zeon.com.chatapplication;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;
import yuku.ambilwarna.AmbilWarnaDialog;
import zeon.com.chatapplication.Activity.Main_Chats_Page;

import android.support.v7.widget.Toolbar;

public class Style_Change extends AppCompatActivity {

    RelativeLayout mylayout;
    Button themebtn;
    int defaultcolor;
    //private Toolbar toolbar;
    ActionBar mActionBar;
    private Button save;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_style__change);
        mActionBar = getSupportActionBar();
        //toolbar =(Toolbar) findViewById(R.id.nav_action);
        mylayout = (RelativeLayout)findViewById(R.id.mylayout);
        themebtn = (Button)findViewById(R.id.theme);
        defaultcolor = ContextCompat.getColor(this, R.color.colorPrimary);
        save = (Button)findViewById(R.id.savecolor);
        themebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                OpenColorPicker(false);
            }
        });
    }


    private void OpenColorPicker(boolean alpha){

        AmbilWarnaDialog warnaDialog = new AmbilWarnaDialog(this, defaultcolor, new AmbilWarnaDialog.OnAmbilWarnaListener() {
            @Override
            public void onCancel(AmbilWarnaDialog dialog) {

                Toast.makeText(getApplicationContext(),"Closed",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onOk(AmbilWarnaDialog dialog, int color) {
                defaultcolor = color;
                mylayout.setBackgroundColor(defaultcolor);
                //toolbar.setDrawingCacheBackgroundColor(defaultcolor);
                mActionBar.setBackgroundDrawable(new ColorDrawable(defaultcolor));

            }
        });

        warnaDialog.show();

    }


    public void SaveColor(View view) {
        MyApplication data = (MyApplication) getApplicationContext();
        data.setColor(defaultcolor);
        Intent intent = new Intent(getApplicationContext(), Main_Chats_Page.class);
        startActivity(intent);
    }
}
