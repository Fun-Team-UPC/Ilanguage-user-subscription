package com.edu.upc.usersubscription.command.application.dtos.response;

import lombok.Value;

import java.time.LocalDateTime;

@Value
public class UserSubscriptionResponse {
    String userSubscriptionId;
    LocalDateTime initialDate;
    LocalDateTime finalDate;
    String userId;
    String subscriptionId;
}
