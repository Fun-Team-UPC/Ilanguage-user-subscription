package com.edu.upc.usersubscription.command.application.handlers;


import com.edu.upc.usersubscription.command.infra.subscription.SubscriptionInfra;
import com.edu.upc.usersubscription.command.infra.subscription.SubscriptionInfraRepository;
import contracts.events.SubscriptionRegistered;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
@ProcessingGroup("subscription")
public class SubscriptionEventHandler {
    private final SubscriptionInfraRepository _subscriptionRepo;

    public SubscriptionEventHandler(SubscriptionInfraRepository _subscriptionRepo) {
        this._subscriptionRepo = _subscriptionRepo;
    }

    @EventHandler
    public void on(SubscriptionRegistered event){
        SubscriptionInfra newSubscription = new SubscriptionInfra(event.getSubscriptionId(),event.getPrice(),event.getMonthDuration(),event.getName(), event.getOccurredOn());
        _subscriptionRepo.save(newSubscription);
    }
}
