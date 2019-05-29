package zeon.com.chatapplication;

import java.util.ArrayList;

import zeon.com.chatapplication.Model.Message;

public class Chats {
    private ArrayList<Message> list;
    private String myEmail;
    private String friendEmail;

    public ArrayList<Message> getList() {
        return list;
    }

    public void setList(ArrayList<Message> list) {
        this.list = list;
    }

    public String getMyEmail() {
        return myEmail;
    }

    public void setMyEmail(String myEmail) {
        this.myEmail = myEmail;
    }

    public String getFriendEmail() {
        return friendEmail;
    }

    public void setFriendEmail(String friendEmail) {
        this.friendEmail = friendEmail;
    }

    public void addMessage(Message message)
    {
        list.add(message);
    }
}
