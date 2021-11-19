package com.edu.upc.usersubscription.command.api;

import com.edu.upc.usersubscription.command.application.dtos.request.UserSubscriptionRequest;
import com.edu.upc.usersubscription.command.application.dtos.response.UserSubscriptionResponse;
import com.edu.upc.usersubscription.command.application.services.UserSubscriptionApplicationService;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.banking.common.api.ApiController;

import pe.edu.upc.banking.common.application.Notification;
import pe.edu.upc.banking.common.application.Result;

@RestController
@RequestMapping("/usersubscription")

public class UserSubscriptionCommandController {
    private final UserSubscriptionApplicationService _service;
    private final CommandGateway _commandGateway;

    public UserSubscriptionCommandController(UserSubscriptionApplicationService _service, CommandGateway _commandGateway) {
        this._service = _service;
        this._commandGateway = _commandGateway;
    }

    @PostMapping("")
    public ResponseEntity<Object> assign(@Validated @RequestBody UserSubscriptionRequest request) throws Exception {
        try {
            Result<UserSubscriptionResponse, Notification> result = _service.assign(request);
            if (result.isSuccess()) {
                return ApiController.created(result.getSuccess());
            }
            return ApiController.error(result.getFailure().getErrors());
            //return ApiController.serverError();
        } catch (Exception e) {
            return ApiController.serverError();
        }
    }
}
