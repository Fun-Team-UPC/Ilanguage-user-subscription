package com.edu.upc.usersubscription.command.infra.user;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class UserInfra {

    @Id
    public String dni;
    public String userId;
}
