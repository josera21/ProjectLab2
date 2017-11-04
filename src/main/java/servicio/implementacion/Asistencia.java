/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicio.implementacion;

import dao.IHibernateDAO;
import dto.Usuario;
import java.util.List;
import servicio.IAsistencia;

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
}
