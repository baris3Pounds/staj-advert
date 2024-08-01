package com.threepounds.advert.notification;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface NotificationMapper {

    List<NotificationDto> notificationListToNotifictaionDtolist(List<Notification> notification);

    Notification notificationDtoToNotification(NotificationDto notificationDto);

    NotificationResource notificationToNotificationResource(Notification savedNotification);
}
