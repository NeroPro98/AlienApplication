package zeon.com.chatapplication;


public class InstanceMessage {

    private String author;
    private String Message;

    public InstanceMessage(String author, String message) {
        this.author = author;
        Message = message;
    }

    public InstanceMessage() {
    }

    public String getAuthor() {
        return author;
    }

    public String getMessage() {
        return Message;
    }
}
