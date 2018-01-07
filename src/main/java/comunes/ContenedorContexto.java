/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comunes;

/**
 *
 * @author josera
 */
public class ContenedorContexto {
    private static ThreadLocal contextHolder = new ThreadLocal();
  
    public static void setContexto(Contexto context) {
      contextHolder.set(context);
    }

    public static Contexto getContexto() {
      if (contextHolder.get() == null) {
        contextHolder.set(new ContextoImpl());
      }
      return (Contexto)contextHolder.get();
    }

    public static void borrarContexto() {
      contextHolder.set(null);
    }
}
