/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal;

import dao.HibernateDAO;
import dao.IHibernateDAO;
import dto.Carrera;
import dto.Estudiante;
import java.io.File;
import java.util.List;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import servicio.IServicio;
import servicio.implementacion.Servicio;

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
            IServicio servicio = new Servicio();
            servicio.setHibernateDAO(hibernateDAO);

            // Guardo una carrera
            Carrera carrera = new Carrera();
            carrera.setCodigo(1);
            carrera.setNombre("Ing. Informatica");
            hibernateDAO.saveOrUpdate(carrera);

            // Guardo un estudiante
            Estudiante estudiante = new Estudiante();
            estudiante.setCedula("24386994");
            estudiante.setNombre("Jose");
            estudiante.setApellido("Camacaro");
            estudiante.setRol(1);
            estudiante.setCorreo("correo.com");
            estudiante.setTelefono("0414");
            estudiante.setCarrera(carrera);

            servicio.guardarEstudiante(estudiante);

            // Guardo otro estudiante
            Estudiante estudiante2 = new Estudiante();
            estudiante2.setCedula("21995512");
            estudiante2.setNombre("Rafael");
            estudiante2.setApellido("Barraez");
            estudiante2.setRol(1);
            estudiante2.setCorreo("correo2.com");
            estudiante2.setTelefono("0416");
            estudiante2.setCarrera(carrera);

            servicio.guardarEstudiante(estudiante2);

            List<Estudiante> listado = servicio.buscarEstudiantesTodos();
            
            System.out.println("** Estudiantes registrados **");
            for (Estudiante est : listado)
            {   
                System.out.println(est.getCedula());
            }

            estudiante2 = servicio.buscarEstudiantePorCedula("21995512");
            servicio.eliminarEstudiante(estudiante2);
            System.out.println("Ejecucion Terminada!");
        }
        catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
}
