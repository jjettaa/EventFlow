package models;

import java.time.LocalDateTime;

public class Notification {
    private int notificationId;
    private int recipientId;
    private String message;
    private LocalDateTime sentTime;

    public boolean sendNotification() { return false; }
}