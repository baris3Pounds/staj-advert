package com.threepounds.advert.notification;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "notification")
public class Notification {

  @Id
  @GeneratedValue
  private UUID id;

  // private title
  // private message
  // private toUser (String email değeri )
  // send (boolean)
  // created timestamp
  // send timestamp
  // notificationType 
}
