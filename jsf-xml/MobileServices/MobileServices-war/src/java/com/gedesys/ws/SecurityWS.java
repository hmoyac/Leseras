package com.gedesys.ws;

import com.gedesys.ifacade.OpcionesFacadeRemote;
import com.gedesys.persistence.Opcion;
import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 *
 * @author econtreras
 */
@WebService()
public class SecurityWS {

    @EJB
    private OpcionesFacadeRemote opcionesFR;

    @WebMethod(operationName = "login")
    public int login(@WebParam(name = "usuario") String usuario,
            @WebParam(name = "password") String password) {
        int logon = 0;
        if (usuario.equals("mcaro") && password.equals("1234")) {
            logon = 1;
        } else if (usuario.equals("econtreras") && password.equals("1234")) {
            logon = 2;
        }
        return logon;
    }

    @WebMethod(operationName = "getOpcion")
    public String getOpcion(@WebParam(name = "opcionId") short opcionId) {
        Opcion opcion = opcionesFR.getOpcion(opcionId);
        return opcion.getOpcion();
    }
}
