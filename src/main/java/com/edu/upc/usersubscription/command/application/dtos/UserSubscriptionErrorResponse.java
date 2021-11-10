package com.edu.upc.usersubscription.command.application.dtos;

public class UserSubscriptionErrorResponse {
    private String message;

    public UserSubscriptionErrorResponse() {
        this.message = "Error assinging a subscription to user";
    }

    public String getMessage() {
        return message;
    }
}
