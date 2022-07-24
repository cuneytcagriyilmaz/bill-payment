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
public class Payment {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;

    private Double totalAmount;

    @Column(nullable = false)
    private LocalDate paymentDate;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;



}
