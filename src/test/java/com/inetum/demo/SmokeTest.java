package com.inetum.demo;

import com.inetum.demo.controllers.MiRestController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Java6Assertions.assertThat;

@SpringBootTest
public class SmokeTest {


    @Autowired
    private MiRestController controller;


    @Test
    public void miRestControllerLoads() throws Exception {
        assertThat(controller).isNotNull();
    }
}

