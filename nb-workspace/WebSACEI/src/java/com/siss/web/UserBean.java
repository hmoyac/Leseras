package com.siss.web;

import com.siss.web.Util.Navigation;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author econtreras
 *
 * Bean gestionado de sesión, encargado de obtener y manipular la sesión del usuario.
 */
public class UserBean {

    /**
     * Nombre del usuario.
     */
    private String nombreUsuario;

    /**
     * Constructor de la clase
     */
    public UserBean() {}

    /**
     * Obtiene el nombre de usuario que inició sesión
     * @return string con el nombre de usuario.
     */
    public String getNombreUsuario() {
        String user = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().toString();
        if (user != null && user.length() > 0) {
            return user;
        } else {
            return "";
        }
    }

    /**
     * Camia el nombre de usuario.
     * @param nombreUsuario nuevo nombre de uauario.
     */
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    /**
     * Metodo encargado de inhabilitar la sesión
     * @return Regla de navegacion a la página principal.
     */
    public Object logout() {
        HttpSession session = (HttpSession) UtileriaWebBean.getExternalContext().getSession(true);
        session.invalidate();
        return Navigation.main;
    }

}
