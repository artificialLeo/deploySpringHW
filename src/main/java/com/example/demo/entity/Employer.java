package com.example.demo.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "employer")
public class Employer extends AbstractEntity {
    private String name;
    private String address;
}