package com.siss.web.delegate.mae;

import com.siss.entity.mae.Comuna;
import com.siss.entity.mae.Region;
import com.siss.ifacade.mae.RegionesFacadeRemote;
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
public class RegionesDelegate {

    private ServiceLocator servicio;
    private RegionesFacadeRemote regionFacade;
    private static RegionesDelegate me;

    static {
        me = new RegionesDelegate();
    }

    /**
     *
     */
    public RegionesDelegate() {
        try {
            servicio = ServiceLocator.getInstance();
            regionFacade = (RegionesFacadeRemote) servicio.getRemoteHome(WebConstants.FACADE_REGIONES, RegionesFacadeRemote.class);
        } catch (NamingException ex) {
            Logger.getLogger(RegionesDelegate.class.getName()).log(Level.SEVERE, Util.getText(ex));
        }
    }

    /**
     *
     * @return
     */
    public static RegionesDelegate getInstance() {
        return me;
    }

    /***** ***** ***** ***** ***** ***** ***** ***** ***** ***** ***** *****
     ***** ***** *****       FACADE MAE REGIONES           ***** ***** *****
     ***** ***** ***** ***** ***** ***** ***** ***** ***** ***** ***** *****/
    /**
     * Obtiene todas las regiones ({@link com.siss.entity.mae.Region Region}) del país.
     * @param pais Identificador del país. ej:1   => CHILE.
     * @see com.siss.entity.mae.Region
     * @see com.siss.ifacade.mae.RegionesFacadeRemote
     * @return List<{@link com.siss.entity.mae.Region Region}> asociadas al país.
     */
    public List<Region> getRegionesByPais(short paisId) {
        return regionFacade.getRegionesByPais(paisId);
    }

    /**
     * Obtiene todas las comunas ({@link com.siss.entity.mae.Comuna Comuna}) de la región.
     * @param idRegion Identificador de la región.
     * @see com.siss.entity.mae.Comuna
     * @see com.siss.ifacade.mae.RegionesFacadeRemote
     * @return List<{@link com.siss.entity.mae.Comuna Comuna}> asociadas a la región.
     */
    public List<Comuna> getComunasByRegion(int regionId) {
        return regionFacade.getComunasByRegion(regionId);
    }

}
