package com.threepounds.advert.notification;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.sql.Timestamp;
import java.util.UUID;

@Data
public class NotificationSearchModel {
    private String title;
    private String message;
    private String toUser;
    private boolean isSent;
    private Timestamp createdTimestamp;
    private Timestamp updateTimestamp;
    private String notificationType;
}
