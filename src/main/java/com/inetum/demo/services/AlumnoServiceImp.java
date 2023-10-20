package com.inetum.demo.services;

import com.inetum.demo.domain.Alumno;
import com.inetum.demo.repositories.AlumnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlumnoServiceImp implements AlumnoService {

    private AlumnoRepository alumnoRepository;
    @Autowired
    AlumnoServiceImp(AlumnoRepository alumnoRepository){
        this.alumnoRepository = alumnoRepository;
    }

    public List<Alumno> findAll() {
        return this.alumnoRepository.findAll();
    }

    public void save(Alumno alumno) {
        this.alumnoRepository.save(alumno);
    }

    public Optional<Alumno> findById(Long id) {
        return this.alumnoRepository.findById(id);
    }

    public void remove(Alumno alumno) {
        this.alumnoRepository.delete(alumno);
    }
}
