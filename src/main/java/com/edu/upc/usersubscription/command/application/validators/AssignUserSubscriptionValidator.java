package com.edu.upc.usersubscription.command.application.validators;

import com.edu.upc.usersubscription.command.application.dtos.request.UserSubscriptionRequest;
import com.edu.upc.usersubscription.command.infra.UserSubscriptionInfraRepository;
import com.edu.upc.usersubscription.command.infra.proyections.SubscriptionInfraRepository;
import org.springframework.stereotype.Component;
import pe.com.ilanguage.common.application.Notification;

@Component
public class AssignUserSubscriptionValidator {
    private final UserSubscriptionInfraRepository _repository;
    private final SubscriptionInfraRepository _subscriptionRepository;

    public AssignUserSubscriptionValidator(UserSubscriptionInfraRepository _repository, SubscriptionInfraRepository _subscriptionRepository) {
        this._repository = _repository;
        this._subscriptionRepository = _subscriptionRepository;
    }

    public Notification validate(UserSubscriptionRequest request){
        Notification notification = new Notification();
        String userSubscriptionId = request.getUserSubscriptionId().trim();
        if(userSubscriptionId.isEmpty()){
            notification.addError("UserSubscriptionId IS REQUIRED");
        }
        String userId = request.getUserId().trim();
        if(userId.isEmpty()){
            notification.addError("User id is required");
        }
        String subscriptionId = request.getSubscriptionId().trim();
        if(subscriptionId.isEmpty()){
            notification.addError("Subscription id is required");
        }

        var existingSubscription = _subscriptionRepository.getById(subscriptionId);
        if(existingSubscription == null){
            notification.addError("Subscription does not exists");
        }

        return  notification;
    }
}
