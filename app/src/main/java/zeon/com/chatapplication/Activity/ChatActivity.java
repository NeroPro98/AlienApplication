package zeon.com.chatapplication.Activity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.media.Image;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.*;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.support.v7.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


import zeon.com.chatapplication.Chats;
import zeon.com.chatapplication.Model.Chat_Model;
import zeon.com.chatapplication.Model.Message;
import zeon.com.chatapplication.Model.MessagePerson;
import zeon.com.chatapplication.Model.UserProfile;
import zeon.com.chatapplication.Model.messageType;
import zeon.com.chatapplication.MyApplication;
import zeon.com.chatapplication.R;

public class ChatActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    String friendEmail = "";
    LinearLayout mLayout;
    ImageView sendbtn;
    ImageView PickImage;
    EditText type;
    private static final int PERMISSION_CODE = 1001;
    private static final int IMAGE_PICK_CODE = 1000;
    private static final int REQUEST_CAMERA = 1;
    ArrayList<String> StringChat = new ArrayList<>();
    MyApplication data = (MyApplication) MyApplication.getAppContext().getApplicationContext();
    ImageView ImageFromStudio;
    private messageType MsgType;
    //private UserProfile ObjConnection = new UserProfile();

    ArrayList<Chat_Model> ChatListServer = new ArrayList<>();
    private MessagePerson Person = new MessagePerson();
    //permission for read storage
    private static  final  int PERMISSION_REQUEST_STORAGE = 1000;
    private static  final  int READ_REQUEST_CODE = 42;
    ImageView FileFromStorage;
    private String FilePath;
    private BufferedReader br;
    private StringBuilder text ;
    private Bitmap bitmap;


    private void MessageFromServer(ArrayList<Chat_Model> msg) throws IOException {

        for (int i = 0; i < msg.size(); i++) {

            LinearLayout linearLayout = new LinearLayout(this);
            linearLayout.setOrientation(LinearLayout.VERTICAL);

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(40, 40, 40, 40);
            //linearLayout.setGravity(Gravity.RIGHT);


            if (msg.get(i).getAny()) {
                linearLayout.setBackgroundResource(R.color.Sender_color);
                layoutParams.gravity = Gravity.RIGHT;
            } else {
                linearLayout.setBackgroundResource(R.color.Reciver_color);
                layoutParams.gravity = Gravity.LEFT;
            }


            TextView text = new TextView(this);
            text.setText(msg.get(i).getMessage());
            text.setTextColor(Color.parseColor("#000000"));
            text.setHeight(100);
            text.setTextSize(20f);
            text.setPadding(20, 20, 20, 20);
            linearLayout.addView(text);
            mLayout.addView(linearLayout, layoutParams);

            ChatListServer.add(new Chat_Model(data.user.getEmail(), friendEmail, (String) msg.get(i).getMessage(), false, new Date(), null, null)); //edit the Date
            data.user.setChatModelList(new Chat_Model(data.user.getEmail(), friendEmail, (String) msg.get(i).getMessage(), false, new Date(), null, null)); //Edit The Date




        }
        data.Save_File();
        data.Read_File();  // here just read to check the value it's don't save

    }

    private void MessageFromClient(String FriendEmail, String message, ImageView image) throws IOException {
        LinearLayout linear = new LinearLayout(this);
        linear.setOrientation(LinearLayout.VERTICAL);
        linear.setBackgroundResource(R.color.Reciver_color);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(30, 30, 30, 30);
        linear.setLayoutParams(layoutParams);
        linear.setGravity(Gravity.LEFT);

        if (message != null) {
            //LinearLayout linear = new LinearLayout(this);
            TextView text = new TextView(this);
            text.setText(message);
            text.setTextColor(Color.parseColor("#000000"));
            text.setTextSize(24f);
            text.setPadding(20, 20, 20, 20);
            linear.addView(text);
            ChatListServer.add(new Chat_Model(data.user.getEmail(), FriendEmail, message, true, new Date(), null, MsgType.Text));  //To Store Client Message
            data.user.setChatModelList(new Chat_Model(data.user.getEmail(), FriendEmail, message, true, new Date(), null, MsgType.Text));
        } else {
            linear.addView(image);
            ChatListServer.add(new Chat_Model(data.user.getEmail(), FriendEmail, message, true, new Date(), image, MsgType.Image));  //To Store Client Message
            data.user.setChatModelList(new Chat_Model(data.user.getEmail(), FriendEmail, message, true, new Date(), image, MsgType.Image));

        }
        data.Save_File();
        data.Read_File();
          /*  ImageView SendImage = new ImageView(this);
            Picasso.with(getApplicationContext()).load(image)
                    .into(SendImage);*/
        /*    ImageView SendImage = new ImageView(this);
            SendImage = image;
            ChatListServer.add(new Chat_Model(data.user.getEmail(), FriendEmail, message, true, new Date(),image));  //To Store Client Message
            data.user.setChatModelList(new Chat_Model(data.user.getEmail(), FriendEmail, message, true, new Date(),image));
            data.Save_File();
            data.Read_File();
            linear.addView(SendImage);*/

        //SendImage.setLayoutParams(layoutParams);
        mLayout.addView(linear);


}

    public void HandlerChatServer() throws IOException {
        Chat_Model ch = data.getUser().getChat(friendEmail);
        if (ch != null) {
            ArrayList<Message> list = ch.getList();
            for (int i = 0; i < list.size(); i++) {
                }
            MessageFromServer(ChatListServer);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat__user__new);

        mLayout = (LinearLayout) findViewById(R.id.linearlayoutforchat);
        PickImage = (ImageView) findViewById(R.id.pick_image_gallary);
        String Name = getIntent().getStringExtra("name");
        friendEmail = getIntent().getStringExtra("userEmail");
        FileFromStorage = (ImageView)findViewById(R.id.FileIcon);
        int userid = getIntent().getIntExtra("userid", 0);
        this.setTitle(Name);

        sendbtn = (ImageView) findViewById(R.id.sendbutton_new);
        type = (EditText) findViewById(R.id.typetext);

        ArrayList<Object> ListBlock = new ArrayList<>();
        ListBlock = data.user.getUser_Block_List();
        boolean IsBlock = false;
        for(int i = 0 ;i<ListBlock.size() ; i = i+2){
            if(ListBlock.get(i).equals(friendEmail)){
                IsBlock = true;
            }
        }
        if(IsBlock){
            type.setEnabled(false);
            Toast.makeText(getApplicationContext(),"Banned",Toast.LENGTH_LONG).show();

        }


  /*      try {
            HandlerChatServer();
        } catch (IOException e) {
            e.printStackTrace();
        }*/

    //Request Permission
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
        != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},PERMISSION_REQUEST_STORAGE);
        }



        // ChatListServer = data.user.getChatModelList();
        for (int i = 0; i < data.user.getThe_User_Chat_Containt().size(); i++) {
            if (data.user.getThe_User_Chat_Containt().get(i).getEmail().equals(friendEmail)) {
                Person = data.user.getThe_User_Chat_Containt().get(i);
                ChatListServer = data.user.getThe_User_Chat_Containt().get(i).getListChat();
            }
        }
        if (ChatListServer.size() != 0) {
            ShowMsgFromFile(ChatListServer);
        }

        data.getUser().setMessageListener(new UserProfile.onValueChangeListener() {
            @Override
            public void onChange() {
                ArrayList<Chat_Model> list = data.getUser().getCurrMessages();
                try {
                    MessageFromServer(list);
                }catch (IOException e){
                    e.printStackTrace();
                }

                //show the list on the screen handle it any way you like
            }
        });

        sendbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //the send photo not work
                try {
                    MessageFromClient(friendEmail, type.getText().toString(), ImageFromStudio);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                StringChat.add(type.getText().toString());
                sendToEmail(friendEmail, type.getText().toString());
                type.setText("");
                data.user.getChatModelList();

            }
        });

        PickImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                        //if the user dont give the app the permission and here we ask the requset
                        String[] permssion = {Manifest.permission.READ_EXTERNAL_STORAGE};
                        //here we ask the user to give the permission
                        requestPermissions(permssion, PERMISSION_CODE);
                    } else {
                        //after user give the app the permission
                        pickImageFromGalleryOfUser();

                    }

                } else {
                    //here if the os is less than marshmallow
                    pickImageFromGalleryOfUser();

                }
            }

        });

        FileFromStorage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performFileSearch();
            }
        });





    }


    //Class for download IMAGE
    public class GetImageFromURL extends AsyncTask<String,Void,Bitmap>{

        ImageView imgV;

        public GetImageFromURL(ImageView imageFromStudio) {
            this.imgV = imageFromStudio;
        }

        @Override
        protected Bitmap doInBackground(String... url) {

            String urldisplay = url[0];
            bitmap = null;
            try{
                InputStream stream = new java.net.URL(urldisplay).openStream();
                bitmap = BitmapFactory.decodeStream(stream);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bitmap;
        }


        @Override
        protected void onPostExecute(Bitmap mbitmap){
            super.onPostExecute(mbitmap);
            imgV.setImageBitmap(mbitmap);


        }
    }



    private void pickImageFromGalleryOfUser() {

        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, IMAGE_PICK_CODE);
    }

    public void sendToEmail(String fEmail, String message) {
        final MyApplication tmpdata = data;
        data.user.getName();
        data.user.getStory();
        data.user.getUserObject();
        final ArrayList<Object> arrayList = new ArrayList<>();
        arrayList.add(13);
        arrayList.add(tmpdata.getUser().getEmail());
        arrayList.add(fEmail);
        arrayList.add(message);
        arrayList.add(new Date());

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    tmpdata.getUser().output.writeObject(arrayList);
                    tmpdata.getUser().output.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.chat_menu, menu);

        MenuItem menuItem = menu.findItem(R.id.searchforthing2);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(this);

        return true;
    }


    public EditText getType() {
        return type;
    }

    public void setType(EditText type) {
        this.type = type;
    }


    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public boolean onQueryTextChange(String newText) {

        String UserInput = newText.toLowerCase();
        List<String> newList = new ArrayList<>();
        //String name = "";
        String listString = StringChat.toString();

        /*for(String s : StringChat){
           name += s + "\t";
        }*/
        for (int i = 0; i < StringChat.size(); i++) {

            String msg = StringChat.get(i);

            if (msg.toLowerCase().contains(UserInput)) {
                newList.add(msg);
            }


        }


        return true;
    }

    /* public void Read_File_UserInfo() {
         FileInputStream fileInputStream;
         File file = new File(data.getFilesDir(), File_Name);

         if (file.exists()) {
             try {
                 fileInputStream = data.openFileInput(File_Name);
                 ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                 UserProfile user_read = (UserProfile) objectInputStream.readObject();
                 if (user_read.getChatModelList().size() != 0) {
                     ChatListServer = user_read.getChatModelList();
                     ShowMsgFromFile(ChatListServer);
                 }
                 System.out.println("userRead:" + user_read);
                 //InputList = user_read.getThe_User_Hwo_Chat_With_Him();
                 objectInputStream.close();
             } catch (IOException e) {
                 e.printStackTrace();
             } catch (ClassNotFoundException e) {
                 e.printStackTrace();
             }
         }
     }
 */
    private void ShowMsgFromFile(ArrayList<Chat_Model> chatListServer) {
        for (int i = 0; i < chatListServer.size(); i++) {
            if (chatListServer.get(i).getAny()) {
                LinearLayout linear = new LinearLayout(this);
                linear.setOrientation(LinearLayout.VERTICAL);
                linear.setBackgroundResource(R.color.Reciver_color);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                layoutParams.setMargins(30, 30, 30, 30);
                linear.setLayoutParams(layoutParams);
                linear.setGravity(Gravity.LEFT);

                if (chatListServer.get(i).getType() == MsgType.Text) {
                    TextView text = new TextView(this);
                    text.setText(chatListServer.get(i).getMessage());
                    text.setTextColor(Color.parseColor("#000000"));
                    text.setTextSize(24f);
                    text.setPadding(20, 20, 20, 20);
                    linear.addView(text);
                    mLayout.addView(linear);
                } else if (chatListServer.get(i).getType() == MsgType.Image) {
                    linear.addView(ImageFromStudio);
                    mLayout.addView(linear);
                }

            } else if (!chatListServer.get(i).getAny()) {
                LinearLayout linearLayout = new LinearLayout(this);
                linearLayout.setOrientation(LinearLayout.VERTICAL);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                layoutParams.setMargins(40, 40, 40, 40);
                linearLayout.setGravity(Gravity.RIGHT);


                if (chatListServer.get(i).getAny()) { // maybe we can delete it
                    linearLayout.setBackgroundResource(R.color.Sender_color);
                    layoutParams.gravity = Gravity.RIGHT;
                } else {// maybe we can delete it
                    linearLayout.setBackgroundResource(R.color.Reciver_color);
                    layoutParams.gravity = Gravity.LEFT;
                }
                if (chatListServer.get(i).getType() == MsgType.Text) {
                    TextView text = new TextView(this);
                    text.setText(chatListServer.get(i).getMessage());
                    text.setTextColor(Color.parseColor("#000000"));
                    text.setHeight(100);
                    text.setTextSize(20f);
                    text.setPadding(20, 20, 20, 20);
                    linearLayout.addView(text);
                    mLayout.addView(linearLayout, layoutParams);
                } else if (chatListServer.get(i).getType() == MsgType.Image) {
                    linearLayout.addView(ImageFromStudio);
                    mLayout.addView(linearLayout);
                }

            }

        }

    }

    /*  public void Save_ChatActivity_Info(String FriendEmail, String message, ImageView Image) throws
              IOException {

          UserProfile user = new UserProfile();
          user = data.user.getUserObject();
          data.user.getChatModelList();
          File file = new File(data.getFilesDir(), File_Name);
          if (!file.exists()) {
              file.createNewFile();
          }
          FileOutputStream outputStream;
          try {
              outputStream = data.openFileOutput(File_Name, Context.MODE_PRIVATE);
              ObjectOutputStream objectoutputStream = new ObjectOutputStream(outputStream);
              objectoutputStream.writeObject(user);
              objectoutputStream.flush();
              objectoutputStream.close();
              outputStream.close();
          } catch (FileNotFoundException e) {
              e.printStackTrace();
          } catch (IOException e) {
              e.printStackTrace();
              System.out.println("The File Write Error :" + e.toString());
          }


      }
  */

    //To Read The Content of file
    private String ReadText(String input){
        File file = new File(input);
        text = new StringBuilder();
        try{
             br = new BufferedReader(new FileReader(file));
            String line ;
            while ((line = br.readLine()) !=null){
                text.append(line);
                text.append("\n");
            }
            br.close();
        }catch (IOException e){
            e.printStackTrace();
        }
        return text.toString();
    }

    //Select file from Storage
    private void performFileSearch(){
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("text/*");
        startActivityForResult(intent,READ_REQUEST_CODE);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {

        switch (requestCode) {
            case PERMISSION_CODE: {
                if (grantResults.length > 0 && grantResults[0] ==
                        PackageManager.PERMISSION_GRANTED) {
                    //Permission was granted
                    pickImageFromGalleryOfUser();
                } else {
                    //Permission was denied
                    Toast.makeText(this, "Permission was denied....", Toast.LENGTH_SHORT).show();

                }
            }
            case PERMISSION_REQUEST_STORAGE:{
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this,"Permission for read file granted",Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(this,"Permission for read file failed",Toast.LENGTH_SHORT).show();
                }
            }
        }
        //  super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {

            if (requestCode == IMAGE_PICK_CODE) {
                //set image to the image view
                ImageFromStudio.setImageURI(data.getData());
                new GetImageFromURL(ImageFromStudio).execute((Runnable) data.getData());   //here to convert image to bitmap I dont know if it work


            } else if (resultCode == REQUEST_CAMERA) {
                //set the image form Camera
                Bundle bundle = data.getExtras();
                final Bitmap cameraimage = (Bitmap) bundle.get("data");
                ImageFromStudio.setImageBitmap(cameraimage);
            }

        }else if(requestCode == READ_REQUEST_CODE && resultCode ==Activity.RESULT_OK){
            if(data !=null){
                Uri uri = data.getData();
                String path = uri.getPath();
                path = path.substring(path.indexOf(":")+1);
                FilePath = path;
                Toast.makeText(this,""+path,Toast.LENGTH_SHORT).show();
                String ContaintFile = ReadText(path);
                type.setText(ContaintFile);
                //here you can show the path of the file
               // tv_output.set

            }
        }

        //  Bitmap cameraimage = (Bitmap) data.getExtras().get("data");
        //   image.setImageBitmap(cameraimage);


    }


}




