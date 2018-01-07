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
    private Textbox cedula;
    @Wire
    private Textbox rol;
    @Wire
    private Textbox codCarrera;
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

        if (Validation.ValidarCamposVacios(cedula, codCarrera, nombre, apellido, correo, telefono)) {
            Messagebox.show("No puede haber campos vacios", "Warning", Messagebox.OK, Messagebox.EXCLAMATION);
            return;
        }
        
        Usuario estudiante = new Usuario();
        
        estudiante.setCedula(cedula.getText());
        estudiante.setRol(Integer.parseInt(rol.getText()));
        estudiante.setNombre(nombre.getText());
        estudiante.setApellido(apellido.getText());
        estudiante.setCorreo(correo.getText());
        estudiante.setTelefono(telefono.getText());
        
        Carrera carrera = asistencia.buscarCarreraPorCodigo(Integer.parseInt(codCarrera.getText()));
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
}
