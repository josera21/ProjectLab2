/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicio;

import dao.IHibernateDAO;
import dto.Usuario;
import java.util.List;

/**
 *
 * @author josera
 */
public interface IAsistencia {
    public void guardarUsuario(Usuario usuario) throws Exception;
    public void setHibernateDAO(IHibernateDAO hibernateDAO);
    public Usuario buscarUsuarioPorCedula(String cedula);
    public List buscarUsuariosTodos();
    public void eliminarUsuario(Usuario usuario);
}
