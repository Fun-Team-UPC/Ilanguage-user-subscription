package com.edu.upc.usersubscription.command.infra;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface UserSubscriptionInfraRepository extends JpaRepository<UserSubscriptionInfra, String> {

    @Query(value = "SELECT * FROM user_subscriptions us WHERE us.user_id = ?1 order by us.final_date desc limit 1", nativeQuery = true)
    public Optional<UserSubscriptionInfra> findLastUSerSubscriptionByUserId(String userId);
}
