package com.evam.billpayment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BillDTO {

    private Long billId;
    private Double billAmount;
    private LocalDate billDate;
    private Boolean status;
    private Long user;

}
