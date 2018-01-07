/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import asistencia.IAsistencia;
import asistencia.implementacion.Asistencia;
import dao.HibernateDAO;
import dao.IHibernateDAO;
import dto.Decanato;
import helper.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
public class CDecanato extends SelectorComposer {
    ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:applicationContext.xml");
    private final IAsistencia asistencia = (IAsistencia) context.getBean("servicio");
    
    @Wire
    private Textbox nombre;
    @Wire
    private Textbox direccion;
    
    @Listen("onClick = #btnGuardar")
    public void grabar() throws Exception {
        
        if(Validation.ValidarCamposVacios(nombre, direccion)) {
            Messagebox.show("No puede haber campos vacios", "Warning", Messagebox.OK, Messagebox.EXCLAMATION);
            return;
        }

        Decanato decanato = new Decanato();

        decanato.setNombre(nombre.getText());
        decanato.setDireccion(direccion.getText());
        asistencia.guardarDecanato(decanato);
        
        Messagebox.show("Registro exitoso", "Information", Messagebox.OK, Messagebox.INFORMATION);
        
        nombre.setText("");
        direccion.setText("");

        throw new WrongValueException(nombre, "Guardado exitoso");
    }
}
