/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asistencia.implementacion;

import dao.IHibernateDAO;
import dto.Usuario;
import java.util.List;
import asistencia.IAsistencia;
import dto.Articulo;
import dto.Carrera;
import dto.Decanato;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author josera
 */
public class Asistencia implements IAsistencia {
    IHibernateDAO hibernateDAO;
    
    public IHibernateDAO getHibernateDAO() {
        return hibernateDAO;
    }

    public void setHibernateDAO(IHibernateDAO hibernateDAO) {
        this.hibernateDAO = hibernateDAO;
    }
    
    @Override
    public void guardarUsuario(Usuario usuario) throws Exception {
        if (usuario.getCedula() == null)
        {
            throw new Exception("Debe Insertar La Cedula");
        }
        else if (usuario.getCedula().trim().equals(""))
        {
            throw new Exception("Debe Insertar La Cedula");
        }
        hibernateDAO.saveOrUpdate(usuario);
    }
    
    @Override
    public void guardarArticulo(Articulo articulo) throws Exception {
        if(articulo.getServicios().isEmpty()) {
            throw new Exception("Debe agregar la informacion del servicio primero.");
        }
        hibernateDAO.saveOrUpdate(articulo);
    }
    
    @Override
    public void guardarCarrera(Carrera carrera) throws Exception {
         if(carrera.getDecanato() == null) {
             throw new Exception("La carrera debe tener un Decanato");
         }
         else {
             hibernateDAO.saveOrUpdate(carrera);
         }
    }
    
    @Override
    public Usuario buscarUsuarioPorCedula(String cedula) {
        return (Usuario) hibernateDAO.findByKey(Usuario.class, cedula);
    }

    @Override
    public List buscarUsuariosTodos() {
        return hibernateDAO.loadAll(Usuario.class);
    }

    @Override
    public void eliminarUsuario(Usuario usuario) {
        hibernateDAO.delete(usuario);
    }

    @Override
    @Transactional
    public void guardarDecanato(Decanato decanato) throws Exception {
        this.hibernateDAO.saveOrUpdate(decanato);
    }

    @Override
    @Transactional
    public List listDecanatos() {
        return hibernateDAO.loadAll(Decanato.class);
    }

    @Override
    public void eliminarDecanato(Decanato decanato) {
        hibernateDAO.delete(decanato);
    }
}
