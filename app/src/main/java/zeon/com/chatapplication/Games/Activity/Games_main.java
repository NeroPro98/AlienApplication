
package zeon.com.chatapplication.Games.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import zeon.com.chatapplication.R;

public class Games_main extends AppCompatActivity {

    ImageView go;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.games_main);


    }

    public void To_Dice_Game(View view) {
        Intent intent = new Intent(getApplicationContext(),Dice_Game.class);
        startActivity(intent);
    }

    public void To_MagicBall_Game(View view) {

        Intent intent = new Intent(getApplicationContext(), Magic_Ball_Game.class);
        startActivity(intent);

    }

    public void To_Quizzler_Game(View view) {
        Intent intent = new Intent(getApplicationContext(), Quizzler_Game.class);
        startActivity(intent);
    }

    public void To_Xylophone_Game(View view) {
        Intent intent = new Intent(getApplicationContext(), Xylophone.class);
        startActivity(intent);
    }
}
