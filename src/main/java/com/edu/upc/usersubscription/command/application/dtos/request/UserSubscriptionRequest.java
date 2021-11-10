package com.edu.upc.usersubscription.command.application.dtos.request;

import lombok.Getter;
import lombok.NonNull;



@Getter
public class UserSubscriptionRequest {

    private String userSubscriptionId;
    private String userId;
    private String subscriptionId;
}
