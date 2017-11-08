/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal;

import dao.HibernateDAO;
import dao.IHibernateDAO;
import dto.Articulo;
import dto.Carrera;
import dto.Decanato;
import dto.Usuario;
import java.io.File;
import java.util.List;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import asistencia.implementacion.Asistencia;
import asistencia.IAsistencia;
import dto.Servicio;

/**
 *
 * @author josera
 */
public class Main {
    public static void main(String[] args) throws Exception {
        try {
            SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        
            IHibernateDAO hibernateDAO = new HibernateDAO();
            hibernateDAO.setSessionFactory(sessionFactory);
            IAsistencia asistencia = new Asistencia();
            asistencia.setHibernateDAO(hibernateDAO);
            
            // Guardo un decanato
            Decanato decanato = new Decanato();
            decanato.setNombre("Ciencias y Tecnologia");
            decanato.setDireccion("Av. Florencio Jimenez");
            hibernateDAO.saveOrUpdate(decanato);
            
            
            // Guardo una carrera
            Carrera carrera = new Carrera();
            carrera.setCodigo(1);
            carrera.setNombre("Ing. Informatica");
            carrera.setDecanato(decanato);
            
            asistencia.guardarCarrera(carrera);
            
            // Guardo un estudiante
            Usuario estudiante = new Usuario();
            estudiante.setCedula("24386994");
            estudiante.setNombre("Jose");
            estudiante.setApellido("Camacaro");
            estudiante.setRol(1);
            estudiante.setCorreo("correo.com");
            estudiante.setTelefono("0414");
            estudiante.setCarrera(carrera);

            asistencia.guardarUsuario(estudiante);

            // Guardo otro estudiante
            Usuario estudiante2 = new Usuario();
            estudiante2.setCedula("21995512");
            estudiante2.setNombre("Rafael");
            estudiante2.setApellido("Barraez");
            estudiante2.setRol(1);
            estudiante2.setCorreo("correo2.com");
            estudiante2.setTelefono("0416");
            estudiante2.setCarrera(carrera);

            asistencia.guardarUsuario(estudiante2);

            // Guardo un Articulo
            Articulo articulo = new Articulo();
            articulo.setNombre("Calculadora");
            articulo.setDescripcion("Vendo calculadora en buen estado");
            articulo.setMonto(50000);
            articulo.setEstado("En venta");
            
            // Instancio el servicio
            Servicio servicio = new Servicio();
            servicio.setTipoServicio("Venta");
            servicio.setArticulo(articulo);
            servicio.setUsuario(estudiante);
            servicio.setArticulocodigo(Integer.toString(articulo.getCodigo()));
            servicio.setUsuariocedula(estudiante.getCedula());
            
            articulo.getServicios().add(servicio);
            // Guardo el Articulo
            asistencia.guardarArticulo(articulo);
                    
            List<Usuario> listado = asistencia.buscarUsuariosTodos();
            
            System.out.println("** Estudiantes registrados **");
            for (Usuario est : listado)
            {   
                System.out.println(est.getCedula());
            }

            estudiante2 = asistencia.buscarUsuarioPorCedula("21995512");
            asistencia.eliminarUsuario(estudiante2);
            System.out.println("Ejecucion Terminada!");
        }
        catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
}
