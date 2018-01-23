/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import asistencia.IAsistencia;
import dto.Articulo;
import java.util.ArrayList;
import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;

/**
 *
 * @author josera
 */
public class CMispublicaciones extends SelectorComposer {
    ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:applicationContext.xml");
    private final IAsistencia asistencia = (IAsistencia) context.getBean("servicio");
    
    @Wire
    private Listbox boxArts;
    @Wire
    private Label currentUser;
    
    @Override
    public void doAfterCompose(Component component) throws Exception {
        super.doAfterCompose(component);
        List<Articulo> arts = asistencia.buscarArticulosTodos();
        List filterArts = new ArrayList();
        String userCed = currentUser.getValue();
        
        for(Articulo art : arts) {
            if(art.getUsuario().getCedula().equals(userCed)) {
                filterArts.add(art);
            }
        }
        
        if(boxArts == null)
            Messagebox.show("Debes iniciar sesion", "Autenticacion", Messagebox.OK, Messagebox.ERROR);
        else
            boxArts.setModel(new ListModelList<>(filterArts));
    }
    
    @Listen("onSelect = #boxArts")
    public void deleteArt() {
        Articulo artSelect = (Articulo)((Listitem) boxArts.getSelectedItem()).getValue();
        
        if(Messagebox.show("Estas seguro que deseas eliminar esta publicacion ?", "Confirmar", 
                Messagebox.OK + Messagebox.CANCEL, Messagebox.QUESTION) == Messagebox.OK) {
            
            asistencia.eliminarArticulo(artSelect);
            
            Messagebox.show("Articulo Eliminado correctamente!","Articulo",Messagebox.OK,Messagebox.INFORMATION);
        }
    }
}
