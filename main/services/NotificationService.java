package services;

import models.Notification;
import repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;

public class NotificationService {

    private List<Notification> notifications = new ArrayList<>();
    private int nextId = 1;

    private UserRepository userRepository = UserRepository.getInstance();

    public boolean sendNotification(Notification notification) {
        if (notification == null) return false;
        if (userRepository.findById(notification.getRecipientId()) == null) return false;

        notification.setNotificationId(nextId++);
        notifications.add(notification);

        return true;
    }

    public List<Notification> getAllNotifications() {
        return notifications;
    }
}

