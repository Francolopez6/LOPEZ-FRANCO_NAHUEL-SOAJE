package com.backend.service;

import com.backend.dao.IDao;
import com.backend.model.Odontologo;

import java.util.List;

public class OdontologoService {

    private IDao<Odontologo> OdontologoIDao;

    public OdontologoService(IDao<Odontologo> odontologoIDao) {
        OdontologoIDao = odontologoIDao;
    }

    public Odontologo guardarOdontologo(Odontologo odontologo){
        return OdontologoIDao.guardar(odontologo);
    }

    public List<Odontologo> listarOdontologo(){
        return OdontologoIDao.listarTodos();
    }


}
