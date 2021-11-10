package com.edu.upc.usersubscription.command.domain;

import commands.AssingSubscriptionToUser;
import events.UserSubscriptionAssigned;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import java.time.Instant;
import java.time.LocalDateTime;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@NoArgsConstructor
@AllArgsConstructor
@Aggregate
public class UserSubscription {
    @AggregateIdentifier
    private String userSubscriptionId;
    private LocalDateTime initialDate;
    private LocalDateTime finalDate;
    private String userId;
    private String subscriptionId;

    @CommandHandler
    public UserSubscription(AssingSubscriptionToUser assingSubscriptionToUser){
        Instant now = Instant.now();
        apply(new UserSubscriptionAssigned(assingSubscriptionToUser.getUserSubscriptionId(), assingSubscriptionToUser.getInitialDate(), assingSubscriptionToUser.getFinalDate(), assingSubscriptionToUser.getUserId(), assingSubscriptionToUser.getSubscriptionId()));
    }

    @EventSourcingHandler
    public void on(UserSubscriptionAssigned event){
        this.userSubscriptionId = event.getUserSubscriptionId();
        this.finalDate = event.getFinalDate();
        this.initialDate = event.getInitialDate();
        this.subscriptionId = event.getSubscriptionId();
        this.userId = event.getUserId();
    }
}
