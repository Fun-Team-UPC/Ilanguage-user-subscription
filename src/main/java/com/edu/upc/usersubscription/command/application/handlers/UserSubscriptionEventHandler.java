package com.edu.upc.usersubscription.command.application.handlers;

import com.edu.upc.usersubscription.command.infra.UserSubscriptionInfra;
import com.edu.upc.usersubscription.command.infra.UserSubscriptionInfraRepository;
import events.UserSubscriptionAssigned;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
@ProcessingGroup("userSubscription")
public class UserSubscriptionEventHandler {
    private final UserSubscriptionInfraRepository _repository;

    public UserSubscriptionEventHandler(UserSubscriptionInfraRepository _repository) {
        this._repository = _repository;
    }

    @EventHandler
    public void on (UserSubscriptionAssigned event){
        _repository.save(new UserSubscriptionInfra(event.getUserSubscriptionId(), event.getSubscriptionId(), event.getUserId(), event.getInitialDate(), event.getFinalDate()));
    }
}
