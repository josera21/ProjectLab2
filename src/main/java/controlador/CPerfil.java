/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import asistencia.IAsistencia;
import dto.Usuario;
import helper.Validation;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.zkoss.zk.ui.Component;
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
public class CPerfil extends SelectorComposer {
    ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:applicationContext.xml");
    private final IAsistencia asistencia = (IAsistencia) context.getBean("servicio");
    
    @Wire
    private Intbox cedula;
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
        Usuario user = asistencia.buscarUsuarioPorCedula(cedula.getText());
        
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
