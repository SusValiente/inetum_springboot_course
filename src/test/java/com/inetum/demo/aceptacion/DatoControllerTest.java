package com.inetum.demo.aceptacion;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.inetum.demo.dtos.Dato;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class DatoControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;
    public String basePath = "/api/v1/dato";


    @BeforeEach
    public void clearRestData() throws Exception {
        System.out.println("limpiando");
        mockMvc.perform(
                        MockMvcRequestBuilders
                                .get(basePath + "/clear")
                                .contentType(MediaType.APPLICATION_JSON))
                                .andExpect(status().isOk());
    }

    @Test
    void testListShouldReturnOkResult() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders
                                .get(basePath + "/")
                                .contentType(MediaType.APPLICATION_JSON))
                                .andExpect(status().isOk());
    }

    @Test
    void testListShouldReturnOkResultList() throws Exception {
        List<Dato> listadoEsperado= new ArrayList<Dato>();
        mockMvc.perform(
                        MockMvcRequestBuilders
                                .get(basePath+"/")
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                // comprobación del tipo de contenido
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                // comprobación del contenido
                .andExpect(content().json(mapper.writeValueAsString(listadoEsperado)));
    }

    @Test
    void testAddShouldReturnDato() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders
                                .post(basePath+"/")
                                .content(asJsonString(new Dato(1L,"valor")))
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(mapper.writeValueAsString(new Dato(1L,"valor"))));
    }

    @Test
    void testGetByIDShouldReturnDato() throws Exception {
        // metemos el dato antes de consultarlo
        testAddShouldReturnDato();
        mockMvc.perform(
                        MockMvcRequestBuilders
                                .get(basePath+"/1")
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(mapper.writeValueAsString(new Dato(1L,"valor"))));
    }

    @Test
    void testRemoveByIdShouldNotReturnDato() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders
                                .delete(basePath+ "/1")
                                .contentType(MediaType.APPLICATION_JSON))
                                .andExpect(status().isNotFound())
                                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                                .andExpect(jsonPath("$.statusCode").value(404));
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}