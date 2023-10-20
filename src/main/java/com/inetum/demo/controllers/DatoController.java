package com.inetum.demo.controllers;

import com.inetum.demo.dtos.Dato;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@RestController
@Tag(
        name = "dato",
        description = "the dato simple API"
)
@RequestMapping("/api/v1/dato")
public class DatoController {

    private List<Dato> listado = new ArrayList<>();
    private long lastID;

    @GetMapping("/")
    @Operation(
            summary = "show list of dato objects",
            description = "Shows a list of dato in an output array",
            tags = { "dato" }
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successful operation",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Dato.class))),
                            })
    })
    public ResponseEntity<List<Dato>> getDatos() {
        HttpHeaders headers = new HttpHeaders();
        HttpStatus status = HttpStatus.OK;

        return new ResponseEntity<List<Dato>>(this.listado, headers, status);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "show dato",
            description = "Shows dato",
            tags = { "dato" }
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successful operation",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Dato.class)),
                    }),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not found operation",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Dato.class)),
                    })
    })
    public ResponseEntity<Dato> getDato(@Parameter(description = "data identifier") @PathVariable("id") Long id) {
        HttpHeaders headers = new HttpHeaders();
        HttpStatus status = HttpStatus.OK;

        Dato dato = this.listado.stream().filter(d -> d.getId().equals(id)).findFirst().orElseThrow(() -> new ResourceNotFoundException("Not found with id = " + id));

        return new ResponseEntity<Dato>(dato, headers, status);
    }

    @PostMapping ("/")
    public ResponseEntity<Dato> createDato(@RequestBody Dato dato) {
        HttpHeaders headers = new HttpHeaders();
        HttpStatus status = HttpStatus.CREATED;
        this.listado.add(dato);
        return new ResponseEntity<Dato>(dato, headers, status);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Dato> editDatoById(@PathVariable("id") Long id, @RequestBody Dato dato) {
        HttpHeaders headers = new HttpHeaders();
        Dato d = this.listado.stream().filter(elemento -> elemento.getId().equals(id)).findFirst().orElse(null);
        HttpStatus status = HttpStatus.OK;

        if (dato == null){
            status = HttpStatus.NOT_FOUND;
            return new ResponseEntity<Dato>(null, headers, status);
        }

        int index = this.listado.indexOf(d);
        dato.setCadena(dato.getCadena());
        this.listado.set(index, dato);
        return new ResponseEntity<Dato>(dato, headers, status);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Dato> deleteDatoById(@PathVariable Long id){
        Dato d = this.listado.stream().filter(dato -> dato.getId().equals(id)).findFirst().orElseThrow(() -> new ResourceNotFoundException("Not found with id = " + id));
        HttpHeaders headers = new HttpHeaders();
        this.listado.remove(d);
        return new ResponseEntity<Dato>(null, headers, HttpStatus.OK);
    }

    @GetMapping("/clear")
    List<Dato> clear(){
        this.listado = new LinkedList<>();
        this.lastID = 0L;
        return this.listado;
    }

}
