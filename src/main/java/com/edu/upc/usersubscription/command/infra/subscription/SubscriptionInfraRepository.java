package com.edu.upc.usersubscription.command.infra.subscription;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SubscriptionInfraRepository extends JpaRepository<SubscriptionInfra, String> {
    @Query("SELECT s from SubscriptionInfra s WHERE s.name=?1")
    public SubscriptionInfra findByName(String subscriptionName);

    @Query("SELECT s from SubscriptionInfra s WHERE s.id=?1")
    public SubscriptionInfra getById(String subscriptionId);
}
