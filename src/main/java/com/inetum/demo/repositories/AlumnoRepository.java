package com.inetum.demo.repositories;

import com.inetum.demo.domain.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlumnoRepository extends JpaRepository<Alumno, Long> {

    public List<Alumno> findByNombre(String name);

    @Query(value = "SELECT a FROM Alumnos a")
    List<Alumno> findAllAlumnos();

    @Query(value = "SELECT a FROM Alumnos a WHERE a.nombre = :name")
    List<Alumno> findAlumnoByName(String name);

    // see named queries in Alumno.java
    List<Alumno> searchAllAlumnosByName(String name);
    List<Alumno> searchAllAlumnosByApellidos(String apellidos);

}
