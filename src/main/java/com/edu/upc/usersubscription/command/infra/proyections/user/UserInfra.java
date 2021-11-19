package com.edu.upc.usersubscription.command.infra.proyections.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserInfra {

    @Id
    @Column(length = 8)
    public String dni;
    public String userId;
}
