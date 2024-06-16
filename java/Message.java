package wafoot.becoming.wafoot;
public class Message {
    private String content;
    private String sender;

    private MessageType type;

    public Message(String content, String sender) {
        this.content = content;
        this.sender = sender;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    public MessageType getType() {
        return type;
    }

    public String getContent() {
        return content;
    }
    public String getSender() {
        return sender;
    }

    public enum MessageType {
        SENT, RECEIVED
    }
}