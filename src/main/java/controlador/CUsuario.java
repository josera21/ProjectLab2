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
import java.util.ArrayList;
import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.ListModelList;
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
    private Combobox cmbCarr;
    @Wire
    private Intbox cedula;
    @Wire
    private Textbox rol;
    @Wire
    private Textbox nombre;
    @Wire
    private Textbox apellido;
    @Wire
    private Textbox correo;
    @Wire
    private Textbox telefono;
    
    @Override
    public void doAfterCompose(Component component) throws Exception {
        super.doAfterCompose(component);
        List<Carrera> listCarr = asistencia.listCarreras();
        List allCarr = new ArrayList();
        
        // Cargo todas las carreras
        listCarr.forEach((carr) -> {
            allCarr.add(carr);
        });
        
        cmbCarr.setModel(new ListModelList<>(allCarr));
    }
   
    @Listen("onClick = #btnGuardar")
    public void grabar() throws Exception {
        
        if (Validation.ValidarCamposVacios(nombre, apellido, correo, telefono)) {
            Messagebox.show("No puede haber campos vacios", "Warning", Messagebox.OK, Messagebox.EXCLAMATION);
            return;
        }
        
        if(cedula.getText().isEmpty() || cmbCarr.getText().isEmpty()) {
           Messagebox.show("Debes colocar cedula y carrera", "Warning", Messagebox.OK, Messagebox.EXCLAMATION);
           return; 
        }
        
        // Busco la carrera y el usuario para validar
        Usuario user = asistencia.buscarUsuarioPorCedula(cedula.getText().trim());
        int codCarr = cmbCarr.getSelectedItem().getValue();
        Carrera carrera = asistencia.buscarCarreraPorCodigo(codCarr);
        
        if(user != null) {
            Messagebox.show("Error! El usuario ya existe", "Error", Messagebox.OK, Messagebox.ERROR);
            return;
        }
        
        if(carrera == null) {
            Messagebox.show("Carrera no encontrada", "Error", Messagebox.OK, Messagebox.ERROR);
            return;
        }
        
        Usuario estudiante = new Usuario();
        estudiante.setCedula(cedula.getText());
        estudiante.setNombre(nombre.getText());
        estudiante.setApellido(apellido.getText());
        estudiante.setCorreo(correo.getText());
        estudiante.setTelefono(telefono.getText());
        
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
        nombre.setText("");
        apellido.setText("");
        telefono.setText("");
        correo.setText("");
        
        throw new WrongValueException(cedula, "Guardado exitoso");
    }
}
