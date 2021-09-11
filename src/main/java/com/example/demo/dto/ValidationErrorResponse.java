package com.example.demo.dto;

import com.example.demo.utils.Violation;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ValidationErrorResponse {
    private List<Violation> violations;
}