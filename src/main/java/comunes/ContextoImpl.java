/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comunes;

import dto.Usuario;
import java.util.HashMap;

/**
 *
 * @author josera
 */
public class ContextoImpl implements Contexto {
    private Usuario usuarioActual;
    private HashMap datosApp = new HashMap();
    
    @Override
    public Usuario getUsuarioActual() {
        return this.usuarioActual;
    }
  
    @Override
    public void setUsuarioActual(Usuario usuario) {
      this.usuarioActual = usuario;
    }
  
    @Override
    public HashMap getDatosAplicacion() { 
        return this.datosApp; 
    }
}
