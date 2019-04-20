package zeon.com.chatapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

public class Search_network extends AppCompatActivity {

    ImageView astrodog;
    ImageButton search;
    EditText question;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_network);

        astrodog = (ImageView)findViewById(R.id.astrodog);
        search = (ImageButton)findViewById(R.id.searchnet);
        question = (EditText)findViewById(R.id.Whatareyoulookingfor);

        astrodog.setTranslationX(-1000);

        astrodog.animate().translationXBy(1000).setDuration(3000);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                astrodog.animate().translationXBy(1000).setDuration(3000);
            }
        });

    }
}
