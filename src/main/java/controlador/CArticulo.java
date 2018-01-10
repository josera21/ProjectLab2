/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import asistencia.IAsistencia;
import dto.Articulo;
import dto.Servicio;
import dto.Usuario;
import helper.Validation;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Decimalbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

/**
 *
 * @author josera
 */
public class CArticulo extends SelectorComposer {
    ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:applicationContext.xml");
    private final IAsistencia asistencia = (IAsistencia) context.getBean("servicio");
    
    @Wire
    private Textbox userCed;
    @Wire
    private Textbox nombre;
    @Wire
    private Textbox descripcion;
    @Wire
    private Combobox cmbEstado;
    @Wire
    private Decimalbox monto;
    
    @Listen("onClick = #btnGuardar")
    public void grabar() throws Exception {
        String montoTxt = monto.getText().trim();
        String estadoTxt = cmbEstado.getText().trim();
        
        if (Validation.ValidarCamposVacios(nombre, descripcion)) {
            Messagebox.show("Hay campos vacios", "Warning", Messagebox.OK, Messagebox.EXCLAMATION);
            return;
        }
        
        if(estadoTxt.isEmpty()) {
            Messagebox.show("Seleccione el tipo de publicacion", "Warning", Messagebox.OK, Messagebox.EXCLAMATION);
            return;
        }
        
        if((montoTxt.isEmpty() || montoTxt.equals(0)) && (estadoTxt.equalsIgnoreCase("venta"))) {
            Messagebox.show("Es necesario un monto", "Warning", Messagebox.OK, Messagebox.EXCLAMATION);
            return;
        }
        
        Articulo articulo = new Articulo();
        
        articulo.setNombre(nombre.getText());
        articulo.setDescripcion(descripcion.getText());
        articulo.setEstado(estadoTxt);
        articulo.setMonto(Float.parseFloat(montoTxt));
        
        Usuario usuario = asistencia.buscarUsuarioPorCedula(userCed.getText());
        // verifico si la carrera existe
        if(usuario == null) {
            Messagebox.show("Usuario no encontrada", "Error", Messagebox.OK, Messagebox.ERROR);
            return;
        }
        
        articulo.setUsuario(usuario);
        // Guardo el Articulo
        asistencia.guardarArticulo(articulo);
        Messagebox.show("Articulo publicado", "Information", Messagebox.OK, Messagebox.INFORMATION);
        
        nombre.setText("");
        descripcion.setText("");
        monto.setText("");

        throw new WrongValueException(nombre, "Guardado exitoso");
    }
}
