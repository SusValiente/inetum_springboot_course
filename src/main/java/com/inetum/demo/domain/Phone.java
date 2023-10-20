package com.inetum.demo.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "Phone")
@Table(name = "PHONES")
public class Phone {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "number")
    private String number;

    @ManyToOne
    @JoinColumn(name = "alumno_id", insertable = false, updatable = false)
    private Alumno alumno;

}
