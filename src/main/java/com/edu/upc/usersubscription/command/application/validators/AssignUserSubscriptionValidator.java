package com.edu.upc.usersubscription.command.application.validators;

import com.edu.upc.usersubscription.command.application.dtos.request.UserSubscriptionRequest;
import com.edu.upc.usersubscription.command.infra.UserSubscriptionInfra;
import com.edu.upc.usersubscription.command.infra.UserSubscriptionInfraRepository;
import com.edu.upc.usersubscription.command.infra.proyections.subscription.SubscriptionInfra;
import com.edu.upc.usersubscription.command.infra.proyections.subscription.SubscriptionInfraRepository;
import com.edu.upc.usersubscription.command.infra.proyections.user.UserInfra;
import com.edu.upc.usersubscription.command.infra.proyections.user.UserInfraRepository;
import org.springframework.stereotype.Component;
import pe.edu.upc.banking.common.application.Notification;


import java.time.LocalDateTime;
import java.util.Optional;

@Component
public class AssignUserSubscriptionValidator {
    private final UserSubscriptionInfraRepository _repository;
    private final SubscriptionInfraRepository _subscriptionRepository;
    private final UserInfraRepository _userRepository;

    public AssignUserSubscriptionValidator(UserSubscriptionInfraRepository _repository, SubscriptionInfraRepository _subscriptionRepository, UserInfraRepository _userRepository) {
        this._repository = _repository;
        this._subscriptionRepository = _subscriptionRepository;
        this._userRepository = _userRepository;
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

        SubscriptionInfra existingSubscription = _subscriptionRepository.getById(subscriptionId);
        if(existingSubscription == null){
            notification.addError("Subscription does not exists");
        }
       UserInfra existingUser = _userRepository.getById(userId);
        if(existingUser == null){
            notification.addError("User does not exists");
        }

        /*Optional<UserSubscriptionInfra> existingUserWithSubscriptionActive= _repository.findLastUSerSubscriptionByUserId(userId);
        if (existingUserWithSubscriptionActive.isPresent()){
            LocalDateTime foundDateTime = existingUserWithSubscriptionActive.get().getFinalDate();
            int compareValue = foundDateTime.compareTo(LocalDateTime.now());
            if(compareValue>0){
                notification.addError("The user already has an active subscription");
            }
        }*/

        return  notification;
    }
}
