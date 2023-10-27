package com.backend.dao.impl;

import com.backend.dao.H2Connection;
import com.backend.dao.IDao;
import com.backend.model.Odontologo;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class OdontologoDaoH2 implements IDao<Odontologo> {

    private final Logger LOGGER = Logger.getLogger(OdontologoDaoH2.class);

    @Override
    public Odontologo guardar(Odontologo odontologo) {
        Connection connection = null;
        Odontologo odontologoRegistrado = null;
        try{
            connection = H2Connection.getConnection();
            connection.setAutoCommit(false);

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO ODONTOLOGO (NUMERODEMATRICULA, NOMBRE, APELLIDO) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, odontologo.getNumeroDEmatricula());
            preparedStatement.setString(2, odontologo.getNombre());
            preparedStatement.setString(3, odontologo.getApellido());

            preparedStatement.execute();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            odontologoRegistrado = new Odontologo(odontologo.getNumeroDEmatricula(), odontologo.getNombre(), odontologo.getApellido());

            while (resultSet.next()){
                odontologoRegistrado.setNumeroDEmatricula(resultSet.getInt("numeroDEmatricula"));

                connection.commit();
                LOGGER.info("Se ha registrado un Odontologo" + odontologoRegistrado);
            }

    }
        catch (Exception e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
            if (connection != null) {
                try {
                    connection.rollback();
                    LOGGER.info("Tuvimos un problema");
                    LOGGER.error(e.getMessage());
                    e.printStackTrace();
                } catch (SQLException exception) {
                    LOGGER.error(exception.getMessage());
                    exception.printStackTrace();
                }
            }
        } finally {
            try {
                connection.close();
            } catch (Exception ex) {
                LOGGER.error("No se pudo cerrar la conexion: " + ex.getMessage());
            }
        }
        return odontologoRegistrado;

    @Override
    public List<Odontologo> listarTodos() {
            Connection connection = null;
            List<Odontologo> odontologo = new ArrayList<>();

            try{
                connection = H2Connection.getConnection();

                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM ODONTOLOGO");
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()){

                    Odontologo odontologo = crearObjetoOdontologo(resultSet);
                    odontologo.add(odontologo);

                }

                LOGGER.info("Listado de odontologo obtenido: " + odontologo);
    }catch (Exception e) {
                LOGGER.error(e.getMessage());
                e.printStackTrace();

            } finally {
                try {
                    connection.close();
                } catch (Exception ex) {
                    LOGGER.error("Ha ocurrido un error al intentar cerrar la bdd. " + ex.getMessage());
                    ex.printStackTrace();
                }
            }

            return (Odontologo) odontologo;
    }
}
}

