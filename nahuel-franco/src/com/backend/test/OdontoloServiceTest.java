package com.backend.test;
import com.backend.dao.impl.OdontologoDaoH2;
import com.backend.model.Odontologo;
import com.backend.service.OdontologoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;

import static org.junit.jupiter.api.Assertions.*;
public class OdontoloServiceTest {

private OdontologoService odontologoService = new OdontologoService(new OdontologoDaoH2());

    @BeforeAll
    static void doBefore() {
        Connection connection = null;
        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection("jdbc:h2:~/testClase;INIT=RUNSCRIPT FROM 'create.sql'", "sa", "sa");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    @Test
    void deberiaAgregarUnPaciente(){

        Odontologo odontologo = new  Odontologo (45678888, "Juancito", "Martinez");

        Odontologo odontologoRegistrado = odontologoService.guardarOdontologo(odontologo);

        Assertions.assertTrue(odontologoRegistrado.getNumeroDEmatricula() != 0);

    }


}
