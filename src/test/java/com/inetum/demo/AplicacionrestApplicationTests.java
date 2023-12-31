package com.inetum.demo;

import com.inetum.demo.controllers.MiRestController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AplicacionrestApplicationTests {
    // Haciendo una Injección de Dependencias del Controlador en esta clase
    // definir un atributo en la clase que se va a auto cargar
    @Autowired
    private MiRestController controller;
    // prueba sobre el controlador
    @Test
    public void miRestControllerLoads() throws Exception {
        // el controlador no es null, es decir carga correctamente
        assertNotNull(controller);
        assertThat(controller).isNotNull();
    }
    // Cargar el gestor de peticiones a la aplicación Web
    // hace una especie de Mock que carga la aplicación y hace peticioens
    @Autowired
    private MockMvc mockMvc;
    @Test
    public void shouldReturnHello() throws Exception {
        // realizar una petición
        this.mockMvc.perform(
                        // hacer un método get en la petición
                        // indicando la ruta de acceso
                        get("/"))
                // imprimir por pantalla el resultado
                .andDo(print())
                // comprobamos que el status es 200 OK
                .andExpect(status().isOk())
                // comprobamos que el contenido es lo que esperamos
                .andExpect(content().string(containsString("Bienvenido")));
    }
}
