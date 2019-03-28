package zeon.com.chatapplication;
import android.app.Activity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.*;
import java.net.*;
//import java.awt.*;
//import java.awt.event.*;
//import javax.swing.*;


public class Server extends Chat_Page{



    private ObjectOutputStream output;
    private ObjectInputStream input;
    private ServerSocket server;
    private Socket connection;

    private EditText UserText;
    private ImageButton SendButton;

    public Server() {

        TypeText = (EditText)findViewById(R.id.Message_Type);
        Send_Button = (ImageButton)findViewById(R.id.sendButton);

        Send_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SendMessage(TypeText.getText().toString());
                TypeText.setText("");
            }
        });




    }




    //Set Up and run the server

    public void StartRunning(){
        try{
            server = new ServerSocket(6789,100);
            while(true){

                try{
                        WaitForConnection();  //wait someone to connect with me
                        SetUpStream();// after one connect with me I Will setup my Stream InputStream and OutPutStream and setup connection
                        WhileChatting(); // the programme that will send and receive message


                }catch (EOFException eof){
                    eof.printStackTrace();
                    Log.d("Stop:","\n"+"The Server End up the Connection...");
                }finally {
                    CloseCrap();
                }

            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    //wait for connection then display connection information
    private void WaitForConnection()throws IOException{
        ShowMessage("Waiting for someone to connect...\n");
         connection = server.accept(); // to accept any one want to chat with you
         ShowMessage("You Connection with...."+connection.getInetAddress().getHostAddress()); // the address of the server
    }

    //make the stream to send and receive the message
    private void SetUpStream()throws IOException{

        output = new ObjectOutputStream(connection.getOutputStream());
        output.flush();
        input = new ObjectInputStream(connection.getInputStream());
        ShowMessage("\n The Streams Setup \n");

    }

    //The function will execute during the chat
    private void WhileChatting()throws IOException{
        String message = "You are now connected !";
        SendMessage(message);
        AbleToType(true);
        //// here the user can type his message
        do{ //have a conversation
            try {
                message = (String)input.readObject();
                ShowMessage("\n"+message);

            }catch (ClassNotFoundException e){  // if the user send to you something that you don't deal with it or your programme don't deal with it so it throw this Exception
                ShowMessage("\n Some thing Strange");
            }


        }while (!message.equals("CLIENT - END")); // the while will excite until any one type END then the chat will stop here we deal with Just String

    }

    //Close Streams and Socket after you done The Chatting
    private void CloseCrap(){
        ShowMessage("\n Closing Connection...\n");
        // here shut the able to write message
        try{
             output.close();
             input.close();
             connection.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    //Send Message to client
    private void SendMessage(String message){
        try{
            output.writeObject("SERVER - "+message); //send message throw the output stream
            output.flush();
            ShowMessage("\n SERVER -"+message);
        }catch (IOException e){ // if we can't send message to reason
            e.printStackTrace();
            Toast.makeText(getApplicationContext(),"There is an Error in Server",Toast.LENGTH_SHORT).show();
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





}
