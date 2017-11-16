package com.siss.web.delegate.mae;

import com.siss.entity.mae.Funcionario;
import com.siss.exception.EntityException;
import com.siss.ifacade.mae.FuncionariosFacadeRemote;
import com.siss.web.resource.WebConstants;
import com.siss.web.Util;
import com.siss.web.util.locator.ServiceLocator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;

/**
 *
 * @author hmoya
 */
public class FuncionariosDelegate {

    private ServiceLocator servicio;
    private FuncionariosFacadeRemote funcFacade;
    private static FuncionariosDelegate me;

    static {
        me = new FuncionariosDelegate();
    }

    /**
     *
     */
    public FuncionariosDelegate() {
        try {
            servicio = ServiceLocator.getInstance();
            funcFacade = (FuncionariosFacadeRemote) servicio.getRemoteHome(WebConstants.FACADE_FUNCIONARIOS, FuncionariosFacadeRemote.class);
        } catch (NamingException ex) {
            Logger.getLogger(FuncionariosDelegate.class.getName()).log(Level.SEVERE, Util.getText(ex));
        }
    }

    /**
     *
     * @return
     */
    public static FuncionariosDelegate getInstance() {
        return me;
    }

    /***** ***** ***** ***** ***** ***** ***** ***** ***** ***** ***** *****
     ***** ***** *****       FACADE MAE FUNCIONARIOS       ***** ***** *****
     ***** ***** ***** ***** ***** ***** ***** ***** ***** ***** ***** *****/

    /**
     * Retorna un esquema de Funcionario a partir del nombre de usuario LDAP.
     * @param usuario nombre del usuario LDAP.
     * @see com.siss.entity.mae.Funcionario
     * @see com.siss.ifacade.mae.FuncionariosFacadeRemote
     * @return El ID del {@link com.siss.entity.mae.Funcionario Funcionario}
     */
    public Integer getUsuarioId(String usuario) throws EntityException {
        Integer id = null;
        Funcionario user = funcFacade.getFuncionarioByLdap(usuario);
        if (user != null) {
            id = user.getFuncionarioId();
        }
        return id;
    }

    /**
     * Obtiene la lista con los {@link com.siss.entity.mae.Funcionario Funcionario} que pueden ser fiscalizadores.
     * @see com.siss.entity.mae.Funcionario
     * @see com.siss.ifacade.mae.FuncionariosFacadeRemote
     * @return Lista con {@link com.siss.entity.mae.Funcionario Funcionario} que pueden ser fiscalizadores.
     */
    public List<Funcionario> getFuncionariosFiscalizadores() {
        return funcFacade.getFuncionariosFiscalizadores();
    }

}
