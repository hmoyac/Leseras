package com.siss.web.delegate.riles;

import com.siss.dto.riles.Certificado;
import com.siss.entity.riles.HorarioDescarga;
import com.siss.exception.EntityException;
import com.siss.ifacade.riles.EnvioRilesFacadeRemote;
import com.siss.web.resource.WebConstants;
import com.siss.web.Util;
import com.siss.web.util.locator.ServiceLocator;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;

/**
 *
 * @author hmoya
 */
public class EnvioRilesDelegate {

    private ServiceLocator servicio;
    private EnvioRilesFacadeRemote rilesFacade;
    private static EnvioRilesDelegate me;

    static {
        me = new EnvioRilesDelegate();
    }

    /**
     *
     */
    public EnvioRilesDelegate() {
        try {
            servicio = ServiceLocator.getInstance();
            rilesFacade = (EnvioRilesFacadeRemote) servicio.getRemoteHome(WebConstants.FACADE_RILES, EnvioRilesFacadeRemote.class);
        } catch (NamingException ex) {
            Logger.getLogger(EnvioRilesDelegate.class.getName()).log(Level.SEVERE, Util.getText(ex));
        }
    }

    /**
     *
     * @return
     */
    public static EnvioRilesDelegate getInstance() {
        return me;
    }

    /***** ***** ***** ***** ***** ***** ***** ***** ***** ***** ***** *****
     ***** ***** *****       FACADE RILES                  ***** ***** *****
     ***** ***** ***** ***** ***** ***** ***** ***** ***** ***** ***** *****/
    /**
     * Actualiza la información de un horario de descarga asociado a una determinada empresa.
     * @param rut Identificador de la empresa.
     * @param actividadEconomicaId Identifiador de la actividad económica.
     * @param mes Mes de la actualización.
     * @param monitoreo Duración del monitoreo.
     * @param horario Descripción de horario. ej:Lunes a Viernes de 08:00 a 14:30
     * @see com.siss.entity.riles.HorarioDescarga
     * @see com.siss.ifacade.riles.EnvioRilesFacadeRemote
     * @return El modelo de {@link com.siss.entity.riles.HorarioDescarga HorarioDescarga} con la informacón actualizada.
     */
    public HorarioDescarga updateHorarioDescarga(long rut, long actividadEconomicaId, short mes, short monitoreo, String horario) throws EntityException {
        return rilesFacade.updateHorarioDescarga(rut, actividadEconomicaId, mes, monitoreo, horario);
    }

    /**
     * Obtiene un HorarioDescarga a partir de la empresa y mes.
     * @param rut Identificador de la empresa.
     * @param actividadEconomicaId Identificador de la actividad económica.
     * @param mes Numerico short correspondiente al mes. [1-12].
     * @see com.siss.entity.riles.HorarioDescarga
     * @see com.siss.ifacade.riles.EnvioRilesFacadeRemote
     * @return El modelo de {@link com.siss.entity.riles.HorarioDescarga HorarioDescarga} asociado a la empresa y mes.
     */
    public HorarioDescarga getHorarioDescargaByRutByActividadEconomicaByMes(long rut, long actividadEconomicaId, short mes) throws EntityException {
        return rilesFacade.getHorarioDescargaByRutByActividadEconomicaByMes(rut, actividadEconomicaId, mes);
    }

    /**
     * Obtiene los HorarioDescarga para todo el año (uno mensual) a partir de la empresa.
     * @param rut Identificador de la empresa.
     * @param actividadEconomicaId Identificador de la actividad económica.
     * @see com.siss.entity.riles.HorarioDescarga
     * @see com.siss.ifacade.riles.EnvioRilesFacadeRemote
     * @return Lista de {@link com.siss.entity.riles.HorarioDescarga HorarioDescarga} asociado a la empresa.
     */
    public List<HorarioDescarga> getHorarioDescargaByRutByActividadEconomica(long rut, long actividadEconomicaId) {
        return rilesFacade.getHorarioDescargaByRutByActividadEconomica(rut, actividadEconomicaId);
    }

    /**
     * Obtiene todos los períodos válidos.
     * @see com.siss.ifacade.riles.EnvioRilesFacadeRemote
     * @return Lista de string con los períodos.
     */
    public List<String> getAllPeriodos() {
        return rilesFacade.getAllPeriodos();
    }

    /**
     * Obtiene el modelo {@link com.siss.dto.amb.Certificado Certificado} que contiene la informacion necesaria para generar
     * el certifiado de cumplimiento para la empresa-planta-punto en un período determinado.
     * @param paramEmpresaID Identificador de la empresa.
     * @param paramPlantaID Identificador de la actividad económica.
     * @param paramPuntoID Identificador del punto de descarga de la empresa.
     * @param paramPeriodo Lista de string con los períodos seleccionados.
     * @see com.siss.dto.amb.Certificado
     * @see com.siss.ifacade.riles.EnvioRilesFacadeRemote
     * @return El modelo {@link com.siss.dto.amb.Certificado Certificado} necesario para generar los certificados de cumplimiento.
     */
    public Certificado getCertificadoCumplimiento(
            Integer paramEmpresaID, Integer paramPlantaID, Integer paramPuntoID,
            String paramPeriodo) throws EntityException {
        return rilesFacade.getCertificadoCumplimiento(paramEmpresaID, paramPlantaID,
                    paramPuntoID, paramPeriodo);

    }

    /**
     * Obtiene el modelo {@link com.siss.dto.amb.Certificado Certificado} que contiene la informacion necesaria para generar
     * los diferentes certifiados para la empresa-planta-punto en un período determinado.
     * @param paramEmpresaID Identificador de la empresa.
     * @param paramPlantaID Identificador de la actividad económica.
     * @param paramPuntoID Identificador del punto de descarga de la empresa.
     * @param paramPeriodo Lista de string con los períodos seleccionados
     * @param paramTipoControl Tipo de control.
     * @param paramTipoMuestreo Tipo Muestreo.
     * @param paramEstadoEnvio Estado Envio.
     * @param paramEstadoDescarga Estado Descarga.
     * @see com.siss.dto.amb.Certificado
     * @see com.siss.ifacade.riles.EnvioRilesFacadeRemote
     * @return El modelo {@link com.siss.dto.amb.Certificado Certificado} necesario para generar los certificados.
     */
    public Certificado getCertificado(
            Integer paramEmpresaID, Integer paramPlantaID, Integer paramPuntoID,
            String paramPeriodo, Integer paramTipoControl,
            Integer paramTipoMuestreo, Integer paramEstadoEnvio, Integer paramEstadoDescarga) throws EntityException {
        return rilesFacade.getCertificado(paramEmpresaID, paramPlantaID,
                    paramPuntoID, paramPeriodo, paramTipoControl,
                    paramTipoMuestreo, paramEstadoEnvio, paramEstadoDescarga);
    }

    /**
     * Obtiene una lista con los datos referentes a los certificados
     * (CUMPLIMIENTO, AUTOCONTROL, REMUESTREO y CONTROL DIRECTO) asociados a los puntos de descarga por período.
     * de cada empresa seleccionada
     * @param empresasIdActividadesId String concatenado las diferentes IdEmpresa@idActividadEconomica. ej:1234567@1,1234567@2,987321741@1
     * @param periodos Períodos seleccionados para obtener la data.
     * @see com.siss.dto.amb.PeriodoEnvioCertificado
     * @see com.siss.ifacade.riles.EnvioRilesFacadeRemote
     * @return HashMap<[nombreEmpresa-nombrePlanta], [List<PeriodoEnvioCertificado>]>
     */
    public HashMap getPeriodoEnvioCertificado(String empresasIdActividadesId, String periodos) throws EntityException {
        return rilesFacade.getPeriodoEnvioCertificado(empresasIdActividadesId, periodos);
    }
}
