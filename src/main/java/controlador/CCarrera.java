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
import java.util.ArrayList;
import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.ListModelList;
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
    
    @Override
    public void doAfterCompose(Component component) throws Exception {
        super.doAfterCompose(component);
        List<Decanato> listDeca = asistencia.listDecanatos();
        List allDeca = new ArrayList();
        
        listDeca.forEach((carr) -> {
            allDeca.add(carr);
        });
        
        cmbDeca.setModel(new ListModelList<>(allDeca));
    }
    
    @Wire
    private Textbox codigo;
    @Wire
    private Combobox cmbDeca;
    @Wire
    private Textbox nombre;
    @Wire
    private Textbox direccion;
    
    @Listen("onClick = #btnGuardar")
    public void grabar() throws Exception {

        if (Validation.ValidarCamposVacios(codigo, nombre, direccion)) {
            Messagebox.show("Hay campos vacios", "Warning", Messagebox.OK, Messagebox.EXCLAMATION);
            return;
        }
        
        if(cmbDeca.getSelectedItem() == null) {
            Messagebox.show("Seleccione un Decanato", "Warning", Messagebox.OK, Messagebox.EXCLAMATION);
            return;
        }
        
        int codDeca = cmbDeca.getSelectedItem().getValue();
        int codCar = Integer.parseInt(codigo.getText());
        
        Carrera carr = asistencia.buscarCarreraPorCodigo(codCar);
        Decanato decanato = asistencia.buscarDecanatoPorCodigo(codDeca);
        
        if(carr != null) {
            Messagebox.show("Error! Ya existe una carrera con ese codigo", "Error", Messagebox.OK, Messagebox.ERROR);
            return;
        }
        
        if(decanato == null) {
            Messagebox.show("Decanato no encontrado", "Error", Messagebox.OK, Messagebox.ERROR);
            return;
        }
        
        Carrera carrera = new Carrera();
        carrera.setCodigo(codCar);
        carrera.setNombre(nombre.getText());
        carrera.setDireccion(direccion.getText());
        carrera.setDecanato(decanato);
        asistencia.guardarCarrera(carrera);
        
        Messagebox.show("Registro exitoso", "Information", Messagebox.OK, Messagebox.INFORMATION);
        
        codigo.setText("");
        nombre.setText("");
        direccion.setText("");

        throw new WrongValueException(nombre, "Guardado exitoso");
    }
}
