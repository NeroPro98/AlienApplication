package zeon.com.chatapplication.Model;

import java.util.Date;

public class Message {
    private String messageID;

    public String getMessageID() {
        return messageID;
    }

    public void setMessageID(String messageID) {
        this.messageID = messageID;
    }

    private String senderID;
    private String recieverID;
    private boolean groupMessage;
    private boolean recieved;
    private Object object;
    private messageType type;
    private Date sendDate;
    private Date recieveDate;


    //The Setters And The Getters
        public String getSenderID () {
        return senderID;
    }

        public void setSenderID (String senderID){
        this.senderID = senderID;
    }

        public String getRecieverID () {
        return recieverID;
    }

        public void setRecieverID (String recieverID){
        this.recieverID = recieverID;
    }

        public boolean isGroupMessage () {
        return groupMessage;
    }

        public void setGroupMessage ( boolean groupMessage){
        this.groupMessage = groupMessage;
    }

        public boolean isRecieved () {
        return recieved;
    }

        public void setRecieved ( boolean recieved){
        this.recieved = recieved;
    }

        public Object getObject () {
        return object;
    }

        public void setObject (Object object){
        this.object = object;
    }

        public messageType getType () {
        return type;
    }

        public void setType (messageType type){
        this.type = type;
    }

        public Date getSendDate () {
        return sendDate;
    }

        public void setSendDate (Date sendDate){
        this.sendDate = sendDate;
    }

        public Date getRecieveDate () {
        return recieveDate;
    }

        public void setRecieveDate (Date recieveDate){
        this.recieveDate = recieveDate;
    }

}
