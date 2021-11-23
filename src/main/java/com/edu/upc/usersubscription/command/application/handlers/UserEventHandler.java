package com.edu.upc.usersubscription.command.application.handlers;


import ILenguage.user.contracts.events.UserRegistered;
import com.edu.upc.usersubscription.command.infra.user.UserInfra;
import com.edu.upc.usersubscription.command.infra.user.UserInfraRepository;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
@ProcessingGroup("user")
public class UserEventHandler {
    private final UserInfraRepository _userMsRepository;

    public UserEventHandler(UserInfraRepository _userMsRepository) {
        this._userMsRepository = _userMsRepository;
    }

    @EventHandler
    public void on (UserRegistered event){
        UserInfra newUser = new UserInfra(event.getDni(), event.getUserId());
        _userMsRepository.save(newUser);
    }
}
