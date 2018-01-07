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
public abstract interface Contexto {
    public static final String ID_CONTEXTO = "CONTEXTO";
  
    public abstract Usuario getUsuarioActual();

    public abstract void setUsuarioActual(Usuario paramUsuario);

    public abstract HashMap getDatosAplicacion();
}
