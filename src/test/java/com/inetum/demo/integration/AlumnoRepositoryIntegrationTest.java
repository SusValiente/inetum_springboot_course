package com.inetum.demo.integration;

import com.inetum.demo.domain.Alumno;
import com.inetum.demo.repositories.AlumnoRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class AlumnoRepositoryIntegrationTest {
    @Autowired
    private TestEntityManager testEntityManager;
    @Autowired private DataSource dataSource;
    @Autowired private JdbcTemplate jdbcTemplate;
    @Autowired private EntityManager entityManager;

    @Test
    void testComponents(){
        assertNotNull(testEntityManager);
        assertNotNull(dataSource);
        assertNotNull(jdbcTemplate);
        assertNotNull(entityManager);
    }

    @Autowired
    private AlumnoRepository alumnoRepository;

    @Test
    public void whenFindByName_thenReturnAlumno() {
        // given
        Alumno alumno = new Alumno();
        alumno.setNombre("Pepe");
        alumno.setEdad(21);
        testEntityManager.persist(alumno);
        entityManager.flush();

        // when
        List<Alumno> found = alumnoRepository.findByNombre(alumno.getNombre());

        // then
        assertEquals(found.get(0).getNombre(), alumno.getNombre());
    }

    @Test
    public void whenFindByName_thenReturnAlumno2() {
        // given
        Alumno alumno = new Alumno();
        alumno.setNombre("Pepe");
        alumno.setEdad(21);
        testEntityManager.persist(alumno);
        entityManager.flush();

        // when
        List<Alumno> found = alumnoRepository.findAlumnoByName(alumno.getNombre());

        // then
        assertEquals(found.get(0).getNombre(), alumno.getNombre());
    }

    @Test
    public void whenFindByName_thenReturnAlumnoList() {
        // given
        Alumno alumno = new Alumno();
        alumno.setNombre("Pepe");
        alumno.setEdad(21);
        testEntityManager.persist(alumno);
        entityManager.flush();

        // when
        List<Alumno> found = alumnoRepository.searchAllAlumnosByName(alumno.getNombre());

        // then
        assertEquals(found.get(0).getNombre(), alumno.getNombre());
    }

    @Test
    public void whenFindByApellido_thenReturnAlumnoList() {
        // given
        Alumno alumno = new Alumno();
        alumno.setNombre("Pepe");
        alumno.setEdad(21);
        testEntityManager.persist(alumno);
        entityManager.flush();

        // when
        List<Alumno> found = alumnoRepository.searchAllAlumnosByApellidos(alumno.getApellidos());

        // then
        assertEquals(found.get(0).getNombre(), alumno.getNombre());
    }

}
