package com.edu.upc.usersubscription.query.projections;

import events.UserSubscriptionAssigned;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
public class UserSubscriptionViewProyection {
    private final UserSubscriptionViewRepository _userSubscriptionViewRepo;

    public UserSubscriptionViewProyection(UserSubscriptionViewRepository _userSubscriptionViewRepo) {
        this._userSubscriptionViewRepo = _userSubscriptionViewRepo;
    }

    @EventHandler
    public void on(UserSubscriptionAssigned event){
        _userSubscriptionViewRepo.save(new UserSubscriptionView(event.getUserSubscriptionId(), event.getSubscriptionId(), event.getUserId(), event.getInitialDate(),event.getFinalDate()));
    }
}
