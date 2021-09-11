package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployerRequest {
    @Size(min = 3, message = "Name can't be less than 3 chars!")
    private String name;

    @Size(min = 3, message = "Address can't be less than 3 chars!")
    private String address;
}
