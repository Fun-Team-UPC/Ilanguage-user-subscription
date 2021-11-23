package com.edu.upc.usersubscription.command.api;

import com.edu.upc.usersubscription.command.application.dtos.request.UserSubscriptionRequest;
import com.edu.upc.usersubscription.command.application.dtos.response.UserSubscriptionResponse;
import com.edu.upc.usersubscription.command.application.services.UserSubscriptionApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary="Asig a subscription to user", description="This endpoind is the one that assign an active sibscription to some user", tags = {"UserSubscriptions"} )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description="Saved correctly", content = @Content(mediaType = "application/json", examples = {
                    @ExampleObject(
                            name="Get subscription example",
                            value = "a"
                    )
            })),
            @ApiResponse(responseCode = "404", description="Subscription Not Found"),
            @ApiResponse(responseCode = "500", description = "Error inesperado del sistema")

    })
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
