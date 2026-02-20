package common;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a message exchanged between the server and a client.
 * Implements Serializable so it can be sent over an ObjectOutputStream.
 */
public class Message implements Serializable {

    private static final long serialVersionUID = 1L;

    /** Sender identifier (e.g. "SERVER" or a client username). */
    private final String sender;

    /** Text content of the message. */
    private final String content;

    /** Timestamp of when the message was created. */
    private final String timestamp;

    public Message(String sender, String content) {
        this.sender = sender;
        this.content = content;
        this.timestamp = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public String getSender() {
        return sender;
    }

    public String getContent() {
        return content;
    }

    public String getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "[" + timestamp + "] " + sender + ": " + content;
    }
}
