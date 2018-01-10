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
import java.util.ArrayList;
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

    @Override
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
        if(articulo.getUsuario() == null) {
            throw new Exception("El Articulo debe pertenecer a un usuario.");
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
    public Decanato buscarDecanatoPorCodigo(int codigo) {
        return (Decanato) hibernateDAO.findByKey(Decanato.class, codigo);
    }

    @Override
    public Carrera buscarCarreraPorCodigo(int codigo) {
        return (Carrera) hibernateDAO.findByKey(Carrera.class, codigo);
    }

    @Override
    public List buscarUsuariosTodos() {
        return hibernateDAO.loadAll(Usuario.class);
    }

    @Override
    public List buscarArticulosTodos() {
        return hibernateDAO.loadAll(Articulo.class);
    }
    
    @Override
    public void eliminarUsuario(Usuario usuario) {
        hibernateDAO.delete(usuario);
    }

    @Override
    public void guardarDecanato(Decanato decanato) throws Exception {
        this.hibernateDAO.saveOrUpdate(decanato);
    }

    @Override
    public List listDecanatos() {
        return hibernateDAO.loadAll(Decanato.class);
    }

    @Override
    public List listCarreras() {
        return hibernateDAO.loadAll(Carrera.class);
    }

    
    @Override
    public void eliminarDecanato(Decanato decanato) {
        hibernateDAO.delete(decanato);
    }
}
