import dto.*;
import comunes.*;

Usuario usuario;
comunes.Contexto obeCtx = (comunes.Contexto) desktop.getSession().getAttribute(comunes.Contexto.ID_CONTEXTO);

if (obeCtx != null) {
    usuario = obeCtx.getUsuarioActual();
}  else {
    obeCtx = new ContextoImpl();

    Usuario usuario = null;
    obeCtx.setUsuarioActual(usuario);

    desktop.getSession().setAttribute(Contexto.ID_CONTEXTO, obeCtx);
}
