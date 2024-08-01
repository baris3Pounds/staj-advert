package com.threepounds.advert.notification;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationDto {
    private UUID id;
    @NotBlank(message = " title cannot be empty")
    private String title;
    @NotBlank(message = " message cannot be empty")
    private String message;
    private String toUser;
    private boolean isSent;
    private Timestamp createdTimestamp;
    private Timestamp updateTimestamp;
    private String notificationType;
}
