package com.evam.billpayment.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Bill {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long billId;


    private Double billAmount;

    @Column(nullable = false)
    private LocalDate billDate;

    @Column(nullable = false)
    private Boolean status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
