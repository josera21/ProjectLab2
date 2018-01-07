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
    // metodos de guardar 
    public void guardarUsuario(Usuario usuario) throws Exception;
    public void guardarArticulo(Articulo articulo) throws Exception;
    public void guardarCarrera(Carrera carrera) throws Exception;
    public void guardarDecanato(Decanato decanato) throws Exception;
    public void setHibernateDAO(IHibernateDAO hibernateDAO);

    // metodos de buscar
    public Usuario buscarUsuarioPorCedula(String cedula);
    public Decanato buscarDecanatoPorCodigo(int codigo);
    public List buscarUsuariosTodos();
    public List listDecanatos();
    
    // metodos de eliminar
    public void eliminarUsuario(Usuario usuario);
    public void eliminarDecanato(Decanato decanato);
}
