package com.edu.upc.usersubscription.command.infra.proyections;

import contracts.events.SubscriptionRegistered;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.Timestamp;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class SubscriptionInfraProyection {
    private final SubscriptionInfraRepository _repository;

    public SubscriptionInfraProyection(SubscriptionInfraRepository _repository) {
        this._repository = _repository;
    }

    @EventHandler
    public void on (SubscriptionRegistered event, @Timestamp Instant timestamp){
        SubscriptionInfra subscriptionInfra = new SubscriptionInfra(event.getSubscriptionId(), event.getPrice(), event.getMonthDuration(), event.getName(), event.getOccurredOn());
        _repository.save(subscriptionInfra);
    }
}
