/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.siss.test.mbean;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author econtreras
 */

public class PopupBean implements Serializable {

    private boolean visible = false;
    private String mensaje = "";
    public String paramApplet = "valor desde managed bean";

    /** Creates a new instance of PopupBean */
    public PopupBean() {
    }

    public String getParamApplet() {
        return paramApplet;
    }

    public void setParamApplet(String paramApplet) {
        this.paramApplet = paramApplet;
    }

    public boolean isVisible() {
        return visible;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public void openClose(ActionEvent event) {
        visible = !visible;
    }

}
