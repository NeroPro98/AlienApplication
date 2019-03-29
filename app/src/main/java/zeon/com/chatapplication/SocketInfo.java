package zeon.com.chatapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class SocketInfo extends AppCompatActivity {

    EditText port;
    EditText ip;
    Button save;
    EditText message;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_socket_info);

        port = (EditText) findViewById(R.id.PORT);
        ip = (EditText) findViewById(R.id.IP);
        save = (Button) findViewById(R.id.save);
        message = (EditText) findViewById(R.id.msg);



       /* Thread T = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    Socket Connection = new Socket(ip.getText().toString(),Integer.parseInt(port.getText().toString()));
                    DataOutputStream output = new DataOutputStream(Connection.getOutputStream());
                    output.writeUTF(message.getText().toString());
                    output.flush();
                    Connection.close();
                    output.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

        T.start();*/


      }

    public void To_Chat(View v){

        String ipTxt = ip.getText().toString();
        String portTxt = port.getText().toString();
        Intent intent = new Intent(getApplicationContext(),Chat_Page.class);
        intent.putExtra("IP",ipTxt);
        intent.putExtra("PORT",portTxt);
        startActivity(intent);
    }

    }


