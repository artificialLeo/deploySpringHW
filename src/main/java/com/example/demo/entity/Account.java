package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;


@EqualsAndHashCode(exclude = "customer", callSuper = true)
@ToString(exclude = "customer")
@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "account")
public class Account extends AbstractEntity {
    private String number;
    private Currency currency;
    private Double balance;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id", referencedColumnName = "id", nullable = false, updatable = false)
    private Customer customer;
}
