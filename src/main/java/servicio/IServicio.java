/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicio;

import dao.IHibernateDAO;
import dto.Estudiante;
import java.util.List;

/**
 *
 * @author josera
 */
public interface IServicio {
    public void guardarEstudiante(Estudiante estudiante) throws Exception;
    public void setHibernateDAO(IHibernateDAO hibernateDAO);
    public Estudiante buscarEstudiantePorCedula(String cedula);
    public List buscarEstudiantesTodos();
    public void eliminarEstudiante(Estudiante estudiante);
}
