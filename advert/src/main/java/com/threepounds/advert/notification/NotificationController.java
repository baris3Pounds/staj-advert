package com.threepounds.advert.notification;

import com.threepounds.advert.exception.GeneralResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@RequestMapping("/api/v1/notification")
@RestController
public class NotificationController {


    private final NotificationService notificationService;
    private final NotificationMapper notificationMapper;

    public NotificationController(NotificationService notificationService, NotificationMapper notificationMapper) {
        this.notificationService = notificationService;
        this.notificationMapper = notificationMapper;
    }

    @GetMapping
    public ResponseEntity<GeneralResponse<Object>> getNotification() {

        List<Notification> notification = notificationService.findAll();

        List<NotificationDto> notificationDtoList = notificationMapper.notificationListToNotifictaionDtolist(notification);

        return ResponseEntity.ok().body(GeneralResponse.<Object>builder().data(notificationDtoList).build());
    }
    @PostMapping
    public ResponseEntity<GeneralResponse<NotificationResource>> sendNotification(@RequestBody NotificationDto notificationDto) {
        Notification notification = notificationMapper.notificationDtoToNotification(notificationDto);
        Notification savedNotification =notificationService.saveNotification(notification);
        savedNotification.setCreatedTimestamp(new Timestamp(System.currentTimeMillis()));
        NotificationResource notificationResource =notificationMapper.notificationToNotificationResource(savedNotification);

        return ResponseEntity.ok().body(GeneralResponse.<NotificationResource>builder().data(notificationResource).build());
    }

    @PutMapping(path = "/{notificationId}")
    public ResponseEntity<GeneralResponse<NotificationResource>> updateNotification(@PathVariable @NotNull UUID notificationId, @Valid @NotNull @RequestBody NotificationDto notificationDto) {
        notificationService.getById(notificationId);

        Notification notification = notificationMapper.notificationDtoToNotification(notificationDto);
        notification.setId(notificationId);
        Notification savedNotification =notificationService.saveNotification(notification);
        savedNotification.setUpdatedTimestamp(new Timestamp(System.currentTimeMillis()));
        NotificationResource notificationResource =notificationMapper.notificationToNotificationResource(savedNotification);

        return ResponseEntity.ok().body(GeneralResponse.<NotificationResource>builder().data(notificationResource).build());
    }

}
