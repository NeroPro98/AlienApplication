package zeon.com.chatapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

    }
}
