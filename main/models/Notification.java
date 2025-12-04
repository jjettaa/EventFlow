package models;

import java.time.LocalDateTime;

public class Notification {

    private int notificationId;
    private int recipientId;
    private String message;
    private LocalDateTime sentTime;

    public Notification() {}

    public Notification(int notificationId, int recipientId, String message) {
        this.notificationId = notificationId;
        this.recipientId = recipientId;
        this.message = message;
        this.sentTime = LocalDateTime.now();
    }

    // Getters & Setters
    public int getNotificationId() { return notificationId; }
    public void setNotificationId(int notificationId) { this.notificationId = notificationId; }

    public int getRecipientId() { return recipientId; }
    public void setRecipientId(int recipientId) { this.recipientId = recipientId; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public LocalDateTime getSentTime() { return sentTime; }
}
