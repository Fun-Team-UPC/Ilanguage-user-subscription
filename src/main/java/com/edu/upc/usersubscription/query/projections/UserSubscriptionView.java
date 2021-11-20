package com.edu.upc.usersubscription.query.projections;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserSubscriptionView {
    @Id
    public String userSubscriptionId;
    public String SubscriptionId;
    public String USerId;
    private LocalDateTime initialDate;
    private LocalDateTime finalDate;

}
