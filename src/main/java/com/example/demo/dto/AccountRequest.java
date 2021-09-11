package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountRequest {
    @NotNull(groups = {Marker.OnUpdate.class})
    private Long id;

    @Min(value = 0, message = "Can't be less than 0!")
    private Double balance;

    @Min(value = 1, message = "Can't be less than 1!")
    private Long customerId;
}
