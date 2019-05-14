package zeon.com.chatapplication.Weather;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import zeon.com.chatapplication.R;

public class ChangeCity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_city);

        final EditText editText = (EditText)findViewById(R.id.queryET);
        ImageButton button = (ImageButton)findViewById(R.id.backButton);
        TextView view = (TextView)findViewById(R.id.getWeatherTV);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                String myCity = editText.getText().toString();
                Intent myIntent = new Intent(ChangeCity.this,WeatherController.class);
                myIntent.putExtra("City",myCity);
                startActivity(myIntent);
                return false;
            }
        });


    }
}
