package com.edu.upc.usersubscription.query.api;

import com.edu.upc.usersubscription.query.projections.UserSubscriptionView;
import com.edu.upc.usersubscription.query.projections.UserSubscriptionViewRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/usersubscription")
public class UserSubscriptionControllerQuery {
    private final UserSubscriptionViewRepository _repository;

    public UserSubscriptionControllerQuery(UserSubscriptionViewRepository _repository) {
        this._repository = _repository;
    }

    @Operation(summary="Get all users subscriptions", description="This endpoind returns all the availeble user subscriptions for Ilanguage Application", tags = {"UserSubscriptions"} )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description="All subscriptions returned", content = @Content(mediaType = "application/json", examples = {
                    @ExampleObject(
                            name="Get subscription example",
                            value = "a"
                    )
            })),
            @ApiResponse(responseCode = "404", description="Subscription Not Found"),
            @ApiResponse(responseCode = "500", description = "Error inesperado del sistema")

    })
    @GetMapping("")
    public ResponseEntity<List<UserSubscriptionView>> getAll(){
        try {
            return new ResponseEntity<List<UserSubscriptionView>>(_repository.findAll(), HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
