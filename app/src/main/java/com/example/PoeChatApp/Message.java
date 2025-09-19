
package com.example.PoeChatApp;
 import java.time.LocalDateTime;

public class Message {
   
    private String text;
    private boolean isSent;
    private boolean isRead;
    private LocalDateTime timeRecieved;

    public Message(String text) {
        this.text = text;
        this.isSent = false;
        this.isRead = false;
        this.timeRecieved = LocalDateTime.now();
    }

    public void send() {
        this.isSent = true;
    }

    public void markAsRead() {
        this.isRead = true;
    }

    public String getStatus() {
        if (!isSent)
            return "Not sent";
        if (isSent && !isRead)
            return "✅✅"; // recieved
        if (isRead)
            return "✅✅ (blue)";// read
        return "Unknown";
    }

    public String getMessagePanel() {
        return "Message: " + text + "\nTime: " + timeRecieved + "\nStatus: " + getStatus();
    }
}


