package com.threepounds.advert.notification;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class NotificationService {

    private NotificationRepository notificationRepository;
    public NotificationService( NotificationRepository notificationRepository){this.notificationRepository = notificationRepository;}

    public List<Notification> findAll(){return (List<Notification>) notificationRepository.findAll();}

    public Notification saveNotification(Notification notification) {return notificationRepository.save(notification);}

    public Notification getById(UUID notificationId) {return notificationRepository.findById(notificationId).orElseThrow(() -> new RuntimeException("Notification not found."));}
}
