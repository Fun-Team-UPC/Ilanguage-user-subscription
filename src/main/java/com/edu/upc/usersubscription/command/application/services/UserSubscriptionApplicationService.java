package com.edu.upc.usersubscription.command.application.services;

import com.edu.upc.usersubscription.command.application.dtos.request.UserSubscriptionRequest;
import com.edu.upc.usersubscription.command.application.dtos.response.UserSubscriptionResponse;
import com.edu.upc.usersubscription.command.application.validators.AssignUserSubscriptionValidator;
import com.edu.upc.usersubscription.command.infra.UserSubscriptionInfraRepository;
import commands.AssingSubscriptionToUser;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import pe.com.ilanguage.common.application.Notification;
import pe.com.ilanguage.common.application.Result;
import pe.com.ilanguage.common.application.ResultType;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Component
@AllArgsConstructor
public class UserSubscriptionApplicationService {
    private final CommandGateway commandGateway;
    private final UserSubscriptionInfraRepository _respository;
    private final AssignUserSubscriptionValidator validator;



    public Result<UserSubscriptionResponse, Notification> assign(UserSubscriptionRequest request) throws Exception{
        Notification notification = this.validator.validate(request);
        if (notification.hasErrors()){
            return Result.failure(notification);
        }
        String userSubscriptionId = UUID.randomUUID().toString();
        AssingSubscriptionToUser assingSubscriptionToUser = new AssingSubscriptionToUser(
                userSubscriptionId,
                LocalDateTime.now(),
                LocalDateTime.now(),
                request.getUserId(),
                request.getSubscriptionId()
        );

        CompletableFuture<Object>future = commandGateway.send(assingSubscriptionToUser);
        CompletableFuture<ResultType> futureResult = future.handle((ok,ex) -> (ex!= null)? ResultType.FAILURE : ResultType.SUCCESS);
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
