/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asistencia;

import dao.IHibernateDAO;
import dto.Articulo;
import dto.Carrera;
import dto.Decanato;
import dto.Usuario;
import java.util.List;

/**
 *
 * @author josera
 */
public interface IAsistencia {
    public void guardarUsuario(Usuario usuario) throws Exception;
    public void guardarArticulo(Articulo articulo) throws Exception;
    public void guardarCarrera(Carrera carrera) throws Exception;
    public void guardarDecanato(Decanato decanato) throws Exception;
    public void setHibernateDAO(IHibernateDAO hibernateDAO);
    public Usuario buscarUsuarioPorCedula(String cedula);
    public List buscarUsuariosTodos();
    public List listDecanatos();
    public void eliminarUsuario(Usuario usuario);
    public void eliminarDecanato(Decanato decanato);
}
