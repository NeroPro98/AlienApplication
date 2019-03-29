/*package zeon.com.chatapplication;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {

    public static void main(String[] args){

        Thread ServerThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ServerSocket Server = new ServerSocket(400);

                    System.out.println("Running.......");


                    while (true){

                        Socket s = Server.accept();

                        System.out.println("Accept Connection ....");
                        DataInputStream input = new DataInputStream(s.getInputStream());

                        System.out.println(input.readUTF());

                        input.close();

                        s.close();




                    }


                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        });

        ServerThread.start();

    }






}
*/