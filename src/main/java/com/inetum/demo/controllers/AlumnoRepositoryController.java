package com.inetum.demo.controllers;

import com.inetum.demo.domain.Alumno;
import com.inetum.demo.dtos.AlumnoDTO;
import com.inetum.demo.services.AlumnoService;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/alumno")
public class AlumnoRepositoryController {

    AlumnoService alumnoService;

    @Autowired
    AlumnoRepositoryController(AlumnoService alumnoService){
        this.alumnoService = alumnoService;
    }

    @GetMapping("/")
    public List<Alumno> index(){
        return this.alumnoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Alumno> getAlumno(@PathVariable("id") Long id){
        Alumno alumno = this.alumnoService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not found with id = " + id));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(alumno, headers, org.springframework.http.HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public void deleteAlumno(@PathVariable("id") Long id){
        Alumno alumno = this.alumnoService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not found with id = " + id));
        this.alumnoService.remove(alumno);
    }

    @PutMapping("/{id}")
    public void updateAlumno(@PathVariable("id") Long id, @Valid @RequestBody AlumnoDTO requestAlumno){
        Alumno alumno = this.alumnoService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not found with id = " + id));
        alumno.setApellidos(requestAlumno.getApellidos());
        alumno.setEdad(requestAlumno.getEdad());
        alumno.setNombre(requestAlumno.getNombre());
        this.alumnoService.save(alumno);
    }

    @PostMapping("/")
    public ResponseEntity<Alumno> addAlumno(@Parameter(description = "Created user object") @Valid @RequestBody AlumnoDTO requestAlumno){
        Alumno alumno = new Alumno(requestAlumno);
        this.alumnoService.save(alumno);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(alumno, headers, org.springframework.http.HttpStatus.CREATED);

    }
}
