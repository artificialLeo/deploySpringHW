package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDtoRequest {
    @NotNull(groups = {Marker.OnUpdate.class})
    private Long from;

    @NotNull(groups = {Marker.OnUpdate.class})
    private Long to;

    @Min(value = 0, message = "Can't be less than 0!")
    private Double balance;
}
