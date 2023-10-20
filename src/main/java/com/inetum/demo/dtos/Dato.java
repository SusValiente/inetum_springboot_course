package com.inetum.demo.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class Dato implements Serializable {

    private Long id;
    private String cadena;

    public Dato() {
        this.id = 1L;
        this.cadena = "empty";
    }
}
