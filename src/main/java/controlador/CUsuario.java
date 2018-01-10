/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import asistencia.IAsistencia;
import dto.Carrera;
import dto.Usuario;
import helper.Validation;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

/**
 *
 * @author josera
 */
public class CUsuario extends SelectorComposer {
    ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:applicationContext.xml");
    private final IAsistencia asistencia = (IAsistencia) context.getBean("servicio");

    @Wire
    private Intbox cedula;
    @Wire
    private Textbox rol;
    @Wire
    private Intbox codCarrera;
    @Wire
    private Textbox nombre;
    @Wire
    private Textbox apellido;
    @Wire
    private Textbox correo;
    @Wire
    private Textbox telefono;

    @Listen("onClick = #btnGuardar")
    public void grabar() throws Exception {

        if (Validation.ValidarCamposVacios(nombre, apellido, correo, telefono)) {
            Messagebox.show("No puede haber campos vacios", "Warning", Messagebox.OK, Messagebox.EXCLAMATION);
            return;
        }
        
        if(cedula.getText().isEmpty() || codCarrera.getText().isEmpty()) {
           Messagebox.show("Debes colocar cedula y codigo", "Warning", Messagebox.OK, Messagebox.EXCLAMATION);
           return; 
        }
        
        Usuario estudiante = new Usuario();
        
        estudiante.setCedula(cedula.getText());
        estudiante.setNombre(nombre.getText());
        estudiante.setApellido(apellido.getText());
        estudiante.setCorreo(correo.getText());
        estudiante.setTelefono(telefono.getText());
        
        Carrera carrera = asistencia.buscarCarreraPorCodigo(Integer.parseInt(codCarrera.getText()));
        // verifico si la carrera existe
        if(carrera == null) {
            Messagebox.show("Carrera no encontrada", "Error", Messagebox.OK, Messagebox.ERROR);
            return;
        }
        // Toma decisiones si le pasamos o no el Rol
        if(rol.getText().isEmpty()) {
            estudiante.setRol(1);
        }
        else {
            estudiante.setRol(Integer.parseInt(rol.getText()));
        }
        
        estudiante.setCarrera(carrera);
        asistencia.guardarUsuario(estudiante);
        Messagebox.show("Registro exitoso", "Information", Messagebox.OK, Messagebox.INFORMATION);
        
        cedula.setText("");
        codCarrera.setText("");
        nombre.setText("");
        apellido.setText("");
        telefono.setText("");
        correo.setText("");
        
        throw new WrongValueException(cedula, "Guardado exitoso");
    }
    
    // ----> PARA LA VISTA MI PERFIL <-----
    
    @Listen("onClick = #btnLoad")
    public void cargarUser() throws Exception {
        Usuario user = asistencia.buscarUsuarioPorCedula(cedula.getText());
        
        nombre.setDisabled(false);
        apellido.setDisabled(false);
        correo.setDisabled(false);
        telefono.setDisabled(false);
        
        nombre.setText(user.getNombre());
        apellido.setText(user.getApellido());
        correo.setText(user.getCorreo());
        telefono.setText(user.getTelefono());
    }
    
    @Listen("onClick = #btnActualizar")
    public void actualizar() throws Exception {
        Usuario estudiante = asistencia.buscarUsuarioPorCedula(cedula.getText());
        
        if (Validation.ValidarCamposVacios(nombre, apellido, correo, telefono)) {
            Messagebox.show("No puede haber campos vacios", "Warning", Messagebox.OK, Messagebox.EXCLAMATION);
            return;
        }
        
        estudiante.setNombre(nombre.getText());
        estudiante.setApellido(apellido.getText());
        estudiante.setCorreo(correo.getText());
        estudiante.setTelefono(telefono.getText());
        
        asistencia.guardarUsuario(estudiante);
        Messagebox.show("Datos actualizados", "Information", Messagebox.OK, Messagebox.INFORMATION);
    }
}
