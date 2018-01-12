/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import asistencia.IAsistencia;
import dto.Articulo;
import dto.Usuario;
import java.util.ArrayList;
import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;

/**
 *
 * @author josera
 */
public class CAdmin extends SelectorComposer {
    ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:applicationContext.xml");
    private final IAsistencia asistencia = (IAsistencia) context.getBean("servicio");
    
    @Wire
    private Listbox boxUsers;
    
    @Override
    public void doAfterCompose(Component component) throws Exception {
        super.doAfterCompose(component);
        List<Usuario> users = asistencia.buscarUsuariosTodos();
        List filterUsers = new ArrayList();
        
        for(Usuario user : users) {
            if(user.getRol() != 2) {
                filterUsers.add(user);
            }
        }
        
        if(boxUsers == null)
            Messagebox.show("No estas Autorizado para acceder aqui", "Autenticacion", Messagebox.OK, Messagebox.ERROR);
        else
            boxUsers.setModel(new ListModelList<>(filterUsers));
    }
    
    @Listen("onSelect = #boxUsers")
    public void deleteUser() {
        Usuario userSelect = (Usuario)((Listitem) boxUsers.getSelectedItem()).getValue();
        
        if(Messagebox.show("Estas seguro que deseas eliminar a este usuario?", "Confirmar", 
                Messagebox.OK + Messagebox.CANCEL, Messagebox.QUESTION) == Messagebox.OK) {
            
            List<Articulo> userArt = asistencia.buscarArticulosTodos();
            
            for(Articulo art : userArt) {
                if(art.getUsuario().getCedula().trim().contains(userSelect.getCedula().trim())) {
                    asistencia.eliminarArticulo(art);
                }
            }
            
            asistencia.eliminarUsuario(userSelect);
            
            Messagebox.show("Usuario Eliminado correctamente!","Usuario",Messagebox.OK,Messagebox.INFORMATION);
        }
    }
}
