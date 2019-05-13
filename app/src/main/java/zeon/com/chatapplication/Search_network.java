package zeon.com.chatapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.CountDownTimer;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Search_network extends AppCompatActivity {

    ImageView astrodog;
    ImageView search;
    TextView question;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_network);

        astrodog = (ImageView)findViewById(R.id.astrodog);
        search = (ImageView)findViewById(R.id.searchnet);
        question = (TextView)findViewById(R.id.Whatareyoulookingfor);

        astrodog.setTranslationX(-1000);

        astrodog.animate().translationXBy(1000).setDuration(3000);

        search.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new CountDownTimer(1000,1000){

                    @Override
                    public void onTick(long millisUntilFinished) {
                        astrodog.animate().translationXBy(1000).setDuration(3000);
                    }

                    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                    @Override
                    public void onFinish() {
                        Search_Web();
                    }
                }.start();


            }
        });

    }

    public void Search_Web(){

        String qus= question.getText().toString();
        String fullqus = "http://"+qus;
        Intent search = new Intent(Intent.ACTION_VIEW, Uri.parse("http://"+ Uri.parse(fullqus)));
        startActivity(search);

    }
}
