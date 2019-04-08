package zeon.com.chatapplication;
import android.app.Activity;
import android.content.Intent;
import android.icu.util.Output;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.io.*;
import java.net.*;



public class Client implements Serializable {

   // private EditText text; // the user text
    //private ListView ChatList; //the window of the chat
    private ObjectOutputStream Output;
    private ObjectInputStream Input;
    private String Message ;
    private Socket Connection;
    private Activity ChatActivity;
    //private EditText Port;
    //private EditText IP;
    //private Button SaveButton;
    //private EditText message;
    //private ImageButton SendButton;
    private String IPString;
    private String PortString;




    public Client(){

        IPString = "0.0.0.0";
        PortString = "0000";
        Message = "";
        // here connect between the text and and place of write
        // set enable of text
        //set the listner to the text and but the SendMessage() function then reset the message

        // add the text to chat List
    }


    //Connect to the Server
    public void StartRunning(){
        Log.d("Aliens Chat","StartRunning() was called");
        Thread ClientThread = new Thread(new Runnable() {
            @Override
            public void run() {

                try{
                    ConnectToServer(); // to connect to specific Server So you need IP Address of server
                    SetupStreams();
                    WhileChatting();

                }catch (EOFException eofE){  //This when the user end the connection
                   // ShowMessage("\n Client Terminated the Connection \n");
                    Toast.makeText(ChatActivity.getApplicationContext(),"Client Terminated the Connection",Toast.LENGTH_SHORT).show();

                }catch (IOException ioE){ // this when there is an error while the user not end the connection
                    ioE.printStackTrace();
                }finally {
                    CloseCrap();
                }

            }
        });

        ClientThread.start();


    }


    //Connect to server
    private void ConnectToServer() throws IOException{

        Log.d("Aliens Chat","Attempting Connection...\n");
        Toast.makeText(ChatActivity.getApplicationContext(),"Attempting Connection...",Toast.LENGTH_SHORT).show();
        Connection = new Socket(InetAddress.getByName(IPString),Integer.parseInt(PortString));  // here we setup the connection to specific server of IP address to specific port on this server Port:
        Log.d("Aliens Chat","Connected to :"+Connection.getInetAddress().getHostAddress());
        Toast.makeText(ChatActivity.getApplicationContext(),"Connected to :"+Connection.getInetAddress().getHostAddress(),Toast.LENGTH_SHORT).show();

    }


    //Setup Stream Streams to send and receive message
    private void SetupStreams()throws IOException
    {
        Log.d("Aliens Chat","SetupStreams() was called");
        Output = new ObjectOutputStream(Connection.getOutputStream());
        Output.flush();
        Input = new ObjectInputStream(Connection.getInputStream());
        Log.d("Aliens Chat","\n The Streams are Ready\n");
        Toast.makeText(ChatActivity.getApplicationContext(),"The Streams are Ready...",Toast.LENGTH_SHORT).show();

    }


    //While Chatting With Server
    private void WhileChatting()throws IOException{
        Log.d("Aliens Chat","WhileChatting() was called");
        Message = "You are Connected ....";
        SendMessage(Message);
        AbleToType(true);
        do{
            try {
                Message = (String)Input.readObject(); //here after we setup our stream and our connection we receive every thing that come from server
               //Update the List View here
                Log.d("Aliens Chat","Message :"+Message+"\n");
               // ShowMessage("\n"+Message); // Update the ChatList

            }catch (ClassNotFoundException e){
                //ShowMessage("\n I don't Know that Object you send");
                Toast.makeText(ChatActivity.getApplicationContext(),"I don't Know that Object you send",Toast.LENGTH_SHORT).show();

            }


        }while (!Message.equals("SERVER - END")); // Type End To End connection
    }



    //Send Message to server
    private void SendMessage(String Message){
        try {

            Log.d("Aliens Chat","SendMessage() was called");
            Output.writeObject("Client -"+Message);
            Output.flush();
           // ShowMessage("\n Client -"+Message);// Update The Chat List

        }catch (IOException IOE){
            Toast.makeText(ChatActivity.getApplicationContext(),"You can't send your Message",Toast.LENGTH_SHORT).show();
            //but a Toast here
        }

    }

    //Update Chat Window
    private void ShowMessage(final String Text){
        //here Update your ListView

    }


    private void AbleToType(final boolean type)
    { // if it true the user can type and if it false the user can't type if there is no one connect with him
        //type on the chat list if  type = true
        //make the Edit text UserText.setEditable(type);

    }




    //Close The Streams and Socket
    private void CloseCrap(){
        Log.d("Aliens Chat","CloseCrap() was called");
        ShowMessage("\n Closing Every thing");
        AbleToType(false);
        try {
            Output.close();
            Input.close();
            Connection.close();

        }catch (IOException e){
            e.printStackTrace();
        }

    }



   public void Set_IP(String IP){
       IPString = IP;
       Log.d("AlienChat","IP:"+IPString);
   }

    public void Set_Port(String Port){
        PortString = Port;
        Log.d("AlienChat","PORT:"+PortString);
    }

    public String getIPString() {
        return IPString;
    }

    public String getPortString() {
        return PortString;
    }

    public String getMessage() {
        return Message;
    }
    public void setMessage(String message){
       Message = message;
       Log.d("AlienChat","message:"+Message);
    }
}
