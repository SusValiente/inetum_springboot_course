package com.inetum.demo.domain;

import com.inetum.demo.dtos.AlumnoDTO;
import jakarta.persistence.*;
import lombok.Data;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity(name = "Alumnos")
@Table(name = "ALUMNOS")
@NamedQueries({
        @NamedQuery(name = "Alumno.searchAllAlumnosByName", query = "SELECT a FROM Alumnos a WHERE a.nombre = :name"),
        @NamedQuery(name = "Alumno.searchAllAlumnosByApellidos", query = "SELECT a FROM Alumnos a WHERE a.apellidos = :apellidos")
})
public class Alumno {
    @Id
    @GeneratedValue
    private Long id;

    @Size(min = 3, max = 20, message = "el nombre debe tener mas de 3 letras y menos de 20.")
    private String nombre;

    private String apellidos;

    @Min(value = 18, message = "el usuario debe tener 18+")
    private Integer edad;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Phone> phones = new ArrayList<Phone>();

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<Address> addresses = new ArrayList<Address>();

    public Alumno(AlumnoDTO alumnoDTO) {
        this.nombre = alumnoDTO.getNombre();
        this.apellidos = alumnoDTO.getApellidos();
        this.edad = alumnoDTO.getEdad();
    }

    public Alumno() {
        this.nombre = "";
        this.apellidos = "";
        this.edad = 0;
    }
}
