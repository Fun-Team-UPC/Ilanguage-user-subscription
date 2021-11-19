package com.edu.upc.usersubscription.command.infra.proyections.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserInfraRepository extends JpaRepository<UserInfra, String> {

    @Query("SELECT s from UserInfra s WHERE s.userId=?1")
    public UserInfra getById(String userId);

}
