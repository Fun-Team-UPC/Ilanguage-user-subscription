package com.edu.upc.usersubscription.query.projections;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserSubscriptionViewRepository extends JpaRepository<UserSubscriptionView, String> {
}
