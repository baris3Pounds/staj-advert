package com.threepounds.advert.notification;


import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "notifications")
public class Notification {

  @Id
  @GeneratedValue
  private UUID id;

  // private title
  @Column(nullable = false,name ="title")
  private String title;
  // private message
  @Column(name="message")
  private String message;
  // private toUser (String email değeri )
  @Column(name = "to_user")
  private String toUser;
  // send (boolean)
  @Column(name="sent")
  private boolean sent;
  // created timestamp
  @Column(name="created_timestamp" )
  private Timestamp createdTimestamp;
  // send timestamp
  @Column(name="updated_timestamp")
  private Timestamp updatedTimestamp;
  // notificationType
  @Column(name="notification_Type")
  private String notificationType;
}
