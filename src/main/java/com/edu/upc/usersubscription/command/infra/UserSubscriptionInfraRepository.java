package com.edu.upc.usersubscription.command.infra;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface UserSubscriptionInfraRepository extends JpaRepository<UserSubscriptionInfra, String> {
}
