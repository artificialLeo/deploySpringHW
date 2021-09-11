package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class PageableResponse<T> {
    private Long total;
    private Iterable<T> list;
}