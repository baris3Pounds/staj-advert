package com.threepounds.advert.notification;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class NotificationResource {

    private String title;
    private String message;
    private String toUser;
    private boolean isSent;
    private Timestamp createdTimestamp;
    private Timestamp updateTimestamp;
    private String notificationType;
}
