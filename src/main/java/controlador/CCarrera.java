/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import asistencia.IAsistencia;
import dto.Carrera;
import dto.Decanato;
import helper.Validation;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
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
@Controller
public class CCarrera extends SelectorComposer {
    ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:applicationContext.xml");
    private final IAsistencia asistencia = (IAsistencia) context.getBean("servicio");
    
    @Wire
    private Textbox codigo;
    @Wire
    private Textbox codDecanato;
    @Wire
    private Textbox nombre;
    @Wire
    private Textbox direccion;
    
    @Listen("onClick = #btnGuardar")
    public void grabar() throws Exception {

        if (Validation.ValidarCamposVacios(codigo, codDecanato, nombre, direccion)) {
            Messagebox.show("Hay campos vacios", "Warning", Messagebox.OK, Messagebox.EXCLAMATION);
            return;
        }
        
        Carrera carrera = new Carrera();
        
        carrera.setCodigo(Integer.parseInt(codigo.getText()));
        carrera.setNombre(nombre.getText());
        carrera.setDireccion(direccion.getText());
        
        Decanato decanato = asistencia.buscarDecanatoPorCodigo(Integer.parseInt(codDecanato.getText()));
        if(decanato == null) {
            Messagebox.show("Decanato no encontrado", "Error", Messagebox.OK, Messagebox.ERROR);
            return;
        }
        
        carrera.setDecanato(decanato);
        asistencia.guardarCarrera(carrera);
        
        Messagebox.show("Registro exitoso", "Information", Messagebox.OK, Messagebox.INFORMATION);
        
        codigo.setText("");
        codDecanato.setText("");
        nombre.setText("");
        direccion.setText("");

        throw new WrongValueException(nombre, "Guardado exitoso");
    }
}
