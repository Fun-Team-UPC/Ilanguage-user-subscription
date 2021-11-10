package com.edu.upc.usersubscription.command.infra;
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
public class UserSubscriptionInfra {
    @Id
    public String userSubscriptionId;
    public String SubscriptionId;
    public String USerId;


}
