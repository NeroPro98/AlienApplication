package zeon.com.chatapplication;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import in.shadowfax.proswipebutton.ProSwipeButton;

public class SocketInfo extends AppCompatActivity {

    EditText port;
    EditText ip;
    Button save;
    EditText message;
    private Button Save_Button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_socket_info);



        port = (EditText) findViewById(R.id.PORT);
        ip = (EditText) findViewById(R.id.IP);
        save = (Button) findViewById(R.id.save);





      }

    public void To_Chat(View v){

        String ipTxt = ip.getText().toString();
        String portTxt = port.getText().toString();
        Intent intent = new Intent(getApplicationContext(),Chat_Page.class);
       // intent.putExtra("IP",ipTxt);
        //intent.putExtra("PORT",portTxt);
        Client client = new Client();
        Log.d("AlienChat","IP:"+client.getIPString());
        client.Set_IP(ipTxt);
        client.Set_Port(portTxt);
        startActivity(intent);
    }

    }


