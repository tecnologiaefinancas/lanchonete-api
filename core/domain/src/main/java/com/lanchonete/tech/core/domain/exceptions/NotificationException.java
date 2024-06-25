package com.lanchonete.tech.core.domain.exceptions;


import com.lanchonete.tech.core.domain.validation.handler.Notification;

public class NotificationException extends DomainException {

    public NotificationException(final String message, final Notification notification) {
        super(message, notification.getErrors());
    }
}
