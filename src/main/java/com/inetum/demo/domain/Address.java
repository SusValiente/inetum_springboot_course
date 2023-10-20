package com.inetum.demo.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "Address")
@Table(name = "ADDRESSES")
public class Address {
    @Id
    @GeneratedValue
    private Long id;
    private String street;
    @Column(name = "`number`")
    private String number;
}