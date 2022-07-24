package com.evam.billpayment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Long userId;

    @NotNull
    @Size(max = 255)
    private String userName;

    @NotNull
    @Size(max = 255)
    private String userLastName;

}
