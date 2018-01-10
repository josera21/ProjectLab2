/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import asistencia.IAsistencia;
import dto.Articulo;
import dto.Carrera;
import dto.Usuario;
import helper.Validation;
import java.util.ArrayList;
import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

/**
 *
 * @author josera
 */
public class CPublicaciones extends SelectorComposer {
    ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:applicationContext.xml");
    private final IAsistencia asistencia = (IAsistencia) context.getBean("servicio");
    
    private String cedPropietario;
    private String cedCurrentUser;
    
    @Wire
    private Button btnContact;
    @Wire
    private Label userLabel;
    @Wire
    private Hbox detBox;
    @Wire
    private Textbox keywordBox;
    @Wire
    private Listbox box;
    @Wire
    private Label nombreLabel;
    @Wire
    private Label descripcionLabel;
    @Wire
    private Label estadoLabel;
    @Wire
    private Label montoLabel;
    @Wire
    private Label ownerLabel;
    @Wire
    private Label apeLabel;
    @Wire
    private Label carrLabel;
    @Wire
    private Label carrDirLabel;
    @Wire
    private Label phoneLabel;
    
    @Listen("onClick = #btnBuscar")
    public void searchArt() {
        String keyword = keywordBox.getValue();
        List<Articulo> articulos = asistencia.buscarArticulosTodos();
        List artFounds = new ArrayList();
        
        if (keyword != null && !keyword.trim().equals("")) {
            artFounds.clear();
            for (Articulo art : articulos) {
                if ((art.getNombre().toLowerCase().contains(
                        keyword.trim().toLowerCase()) ||
                        art.getEstado().toLowerCase().contains(keyword.trim().toLowerCase())) && 
                        (!art.getEstado().toLowerCase().contains("no disponible")))
                {
                    artFounds.add(art);
                }
            }
        }
        
        box.setModel(new ListModelList<>(artFounds));
    }
    
    @Listen("onSelect = #box")
    public void showDetail() {
        Articulo artSelec =  (Articulo)((Listitem) box.getSelectedItem()).getValue();
        Usuario owner = artSelec.getUsuario();
        Carrera carr = owner.getCarrera();
        
        detBox.setVisible(true);
        
        // Paso los valores a los Labels del Hbox para la vista detalle
        nombreLabel.setValue(artSelec.getNombre());
        descripcionLabel.setValue(artSelec.getDescripcion());
        estadoLabel.setValue(artSelec.getEstado());
        montoLabel.setValue(Float.toString(artSelec.getMonto()));
        ownerLabel.setValue(owner.getNombre());
        apeLabel.setValue(owner.getApellido());
        carrLabel.setValue(carr.getNombre());
        carrDirLabel.setValue(carr.getDireccion());
        phoneLabel.setValue(owner.getTelefono());
        
        // Extraigo ambas cedulas (Propietario y el que inicio session)
        cedPropietario = owner.getCedula().trim();
        cedCurrentUser = userLabel.getValue().trim();
        
        // Compruebo que existan y si son iguales desactivo el boton
        if(Validation.ValidarStringsVacios(cedPropietario, cedCurrentUser) || 
                cedPropietario.equals(cedCurrentUser)) {
            btnContact.setDisabled(true);
        }
        else {
            btnContact.setDisabled(false);
        }
    }
    
    @Listen("onClick = #btnContact")
    public void showOwnerCed() throws Exception {
        if(Messagebox.show("Estas seguro de continuar ?", "Contactar", 
                Messagebox.OK + Messagebox.CANCEL, Messagebox.QUESTION) == Messagebox.OK) {
            
            Articulo artSelec =  (Articulo)((Listitem) box.getSelectedItem()).getValue();
            artSelec.setEstado("No disponible");
            // Actualizo el estado del articulo
            asistencia.guardarArticulo(artSelec);
            Messagebox.show("Listo! Notificaremos al Propietario", "Contactar",
                    Messagebox.OK, Messagebox.INFORMATION);
        }
    }
    
    @Listen("onClick = #btnLimpiar")
    public void clear() {
        detBox.setVisible(false);
        keywordBox.setText("");
        keywordBox.setPlaceholder("Escribe algo...");
        box.getItems().clear();
    }
}
