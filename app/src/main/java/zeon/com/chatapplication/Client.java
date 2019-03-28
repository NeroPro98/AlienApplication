package zeon.com.chatapplication;
import android.widget.EditText;
import android.widget.ListView;

import java.io.*;
import java.net.*;
//import java.awt.*;
//import java.awt.event.*;
//import javax.swing.*;



public class Client {

    private EditText text; // the user text
    private ListView ChatList; //the window of the chat
    private ObjectOutputStream Output;
    private ObjectInputStream Input;
    private String Message = "";
    private String ServerIP;
    private Socket Connection;


    public Client(String host){
        ServerIP = host;
        // here connect between the text and and place of write
        // set enable of text
        //set the listner to the text and but the SendMessage() function then reset the message

        // add the text to chat List
    }


    //Connect to the Server
    public void StartRunning(){
        try{
            ConnectToServer(); // to connect to specific Server So you need IP Address of server
            SetupStreams();
            WhileChatting();

        }catch (EOFException eofE){  //This when the user end the connection
            ShowMessage("\n Client Terminated the Connection \n");

        }catch (IOException ioE){ // this when there is an error while the user not end the connection
            ioE.printStackTrace();
        }finally {
            CloseCrap();
        }

    }


    //Connect to server
    private void ConnectToServer() throws IOException{

        ShowMessage("Attempting Connection...\n");
        Connection = new Socket(InetAddress.getByName(ServerIP),6789);  // here we setup the connection to specific server of IP address to specific port on this server
        ShowMessage("Connected to :"+Connection.getInetAddress().getHostAddress());


    }

    //Send Message to server
    private void SendMessage(String Message){
        try {

            Output.writeObject("Client -"+Message);
            Output.flush();
            ShowMessage("\n Client -"+Message);
        }catch (IOException IOE){
            //but a Toast here
        }

    }

    //Update Chat Window
    private void ShowMessage(final String Text){
        //here Update your ListView

    }


    private void AbleToType(final boolean type){ // if it true the user can type and if it false the user can't type if there is no one connect with him
        //type on the chat list if  type = true
        //make the Edit text UserText.setEditable(type);

    }


    //Setup Stream Streams to send and receive message
    private void SetupStreams()throws IOException{
        Output = new ObjectOutputStream(Connection.getOutputStream());
        Output.flush();
        Input = new ObjectInputStream(Connection.getInputStream());
        ShowMessage("\n The Streams is Ready\n");

    }

    //While Chatting With Server
    private void WhileChatting()throws IOException{
        AbleToType(true);
        do{
            try {
                Message = (String)Input.readObject(); //here after we setup our stream and our connection we receive every thing that come from server
                ShowMessage("\n"+Message);

            }catch (ClassNotFoundException e){
                ShowMessage("\n I don't Know that Object you send");
            }


        }while (!Message.equals("SERVER - END"));
    }

    //Close The Streams and Socket
    private void CloseCrap(){
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
}
