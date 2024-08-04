package com.threepounds.advert.notification;

import org.springframework.data.jpa.domain.Specification;

public class NotificationSpesification {
    public Specification<Notification> hasTitle(String title) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("title") , title));
    }
    public Specification<Notification> hasText(String text) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("text") , text));
    }
    public Specification<Notification> hasToUser(String toUser) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("toUser") , toUser));
    }
    public Specification<Notification> hasSent(String isSent) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("isSent") , isSent));
    }
    public Specification<Notification> hasNotificationType(String notificationType) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("notificationType"), notificationType));
    }
}
