package com.inetum.demo.services;

import com.inetum.demo.domain.Alumno;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface AlumnoService {
    public List<Alumno> findAll();
    public void save(Alumno alumno);
    public Optional<Alumno> findById(Long id);
    public void remove(Alumno alumno);
}
