package com.edu.upc.usersubscription.command.infra.proyections.user;

import ILenguage.user.contracts.events.UserRegistered;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.Timestamp;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class UserInfraProyection {
    private final UserInfraRepository _userRepo;

    public UserInfraProyection(UserInfraRepository _userRepo) {
        this._userRepo = _userRepo;
    }

    @EventHandler
    public void on(UserRegistered event, @Timestamp Instant timestamp){
        UserInfra userInfra = new UserInfra(event.getDni(),event.getUserId());
        _userRepo.save(userInfra);
    }
}
