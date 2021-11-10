package com.edu.upc.usersubscription.config;

import com.edu.upc.usersubscription.command.domain.UserSubscription;
import com.edu.upc.usersubscription.command.infra.UserSubscriptionInfra;
import org.axonframework.commandhandling.SimpleCommandBus;
import org.axonframework.eventhandling.EventBus;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.messaging.interceptors.BeanValidationInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AxonConfig {
    @Bean
    public EventSourcingRepository<UserSubscription> eventSourcingRepository(EventStore eventStore) {
        return EventSourcingRepository.builder(UserSubscription.class)
                .eventStore(eventStore)
                .build();
    }
}
