package com.edu.upc.usersubscription.command.application.services;

import com.edu.upc.usersubscription.command.application.dtos.request.UserSubscriptionRequest;
import com.edu.upc.usersubscription.command.application.dtos.response.UserSubscriptionResponse;
import com.edu.upc.usersubscription.command.application.validators.AssignUserSubscriptionValidator;
import com.edu.upc.usersubscription.command.infra.UserSubscriptionInfraRepository;
import com.edu.upc.usersubscription.command.infra.proyections.subscription.SubscriptionInfra;
import com.edu.upc.usersubscription.command.infra.proyections.subscription.SubscriptionInfraRepository;
import commands.AssingSubscriptionToUser;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Component;
import pe.edu.upc.banking.common.application.Notification;
import pe.edu.upc.banking.common.application.Result;
import pe.edu.upc.banking.common.application.ResultType;


import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Component
@AllArgsConstructor
public class UserSubscriptionApplicationService {
    private final CommandGateway commandGateway;
    private final UserSubscriptionInfraRepository _respository;
    private final AssignUserSubscriptionValidator validator;
    private final SubscriptionInfraRepository _subscriptionRepository;



    public Result<UserSubscriptionResponse, Notification> assign(UserSubscriptionRequest request) throws Exception{
        Notification notification = this.validator.validate(request);
        if (notification.hasErrors()){
            return Result.failure(notification);
        }
        String userSubscriptionId = UUID.randomUUID().toString();

        SubscriptionInfra subscription = _subscriptionRepository.getById(request.getSubscriptionId());

        LocalDateTime finalDate = LocalDateTime.now().plusMonths((long) subscription.getMonthDuration());

        AssingSubscriptionToUser assingSubscriptionToUser = new AssingSubscriptionToUser(
                userSubscriptionId,
                LocalDateTime.now(),
                finalDate,
                request.getUserId(),
                request.getSubscriptionId()
        );

        CompletableFuture<Object>future = commandGateway.send(assingSubscriptionToUser);
        CompletableFuture<ResultType> futureResult = future.handle((ok, ex) -> (ex!= null)? ResultType.FAILURE : ResultType.SUCCESS);
        ResultType resultType = futureResult.get();
        if(resultType == ResultType.FAILURE){
            throw new Exception();
        }

        UserSubscriptionResponse response = new UserSubscriptionResponse(
                assingSubscriptionToUser.getUserSubscriptionId(),
                assingSubscriptionToUser.getInitialDate(),
                assingSubscriptionToUser.getFinalDate(),
                assingSubscriptionToUser.getUserId(),
                assingSubscriptionToUser.getSubscriptionId()
        );
        return Result.success(response);
    }
}
