/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicio.implementacion;

import dao.IHibernateDAO;
import dto.Estudiante;
import java.util.List;
import servicio.IServicio;

/**
 *
 * @author josera
 */
public class Servicio implements IServicio {
    IHibernateDAO hibernateDAO;
    
    public IHibernateDAO getHibernateDAO() {
        return hibernateDAO;
    }

    public void setHibernateDAO(IHibernateDAO hibernateDAO) {
        this.hibernateDAO = hibernateDAO;
    }
    
    @Override
    public void guardarEstudiante(Estudiante estudiante) throws Exception {
        if (estudiante.getCedula() == null)
        {
            throw new Exception("Debe Insertar La Cedula");
        }
        else if (estudiante.getCedula().trim().equals(""))
        {
            throw new Exception("Debe Insertar La Cedula");
        }
        hibernateDAO.saveOrUpdate(estudiante);
    }
    
    @Override
    public Estudiante buscarEstudiantePorCedula(String cedula) {
        return (Estudiante) hibernateDAO.findByKey(Estudiante.class, cedula);
    }

    @Override
    public List buscarEstudiantesTodos() {
        return hibernateDAO.loadAll(Estudiante.class);
    }

    @Override
    public void eliminarEstudiante(Estudiante estudiante) {
        hibernateDAO.delete(estudiante);
    }
}
