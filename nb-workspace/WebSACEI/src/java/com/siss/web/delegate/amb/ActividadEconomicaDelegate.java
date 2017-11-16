package com.siss.web.delegate.amb;

import com.siss.dto.amb.ActividadEconomica;
import com.siss.dto.amb.PtoDescarga;
import com.siss.dto.riles.ReporteCumplimiento;
import com.siss.dto.riles.ReporteParametro;
import com.siss.entity.amb.CronogramaAE;
import com.siss.entity.amb.Parametro;
import com.siss.exception.EntityException;
import com.siss.ifacade.amb.ActividadEconomicaFacadeRemote;
import com.siss.web.resource.WebConstants;
import com.siss.web.Util;
import com.siss.web.util.locator.ServiceLocator;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;

/**
 *
 * @author hmoya
 */
public class ActividadEconomicaDelegate {

    private ServiceLocator servicio;
    private ActividadEconomicaFacadeRemote ambFacade;
    private static ActividadEconomicaDelegate me;

    static {
        me = new ActividadEconomicaDelegate();
    }

    /**
     *
     */
    public ActividadEconomicaDelegate() {
        try {
            servicio = ServiceLocator.getInstance();
            ambFacade = (ActividadEconomicaFacadeRemote) servicio.getRemoteHome(WebConstants.FACADE_AMB, ActividadEconomicaFacadeRemote.class);
        } catch (NamingException ex) {
            Logger.getLogger(ActividadEconomicaDelegate.class.getName()).log(Level.SEVERE, Util.getText(ex));
        }
    }

    /**
     *
     * @return
     */
    public static ActividadEconomicaDelegate getInstance() {
        return me;
    }

    /***** ***** ***** ***** ***** ***** ***** ***** ***** ***** ***** *****
     ***** ***** *****       FACADE AMB                    ***** ***** *****
     ***** ***** ***** ***** ***** ***** ***** ***** ***** ***** ***** *****/
    /**
     * Obtiene los reportes de parámetros informados para las empresas especificadas,
     * dentro de un rango de período y los parámetros deseados.
     * @param empresas String con los idEmpresa1@idActividadEconomica1, idEmpresa2@idActividadEconomica2, etc.
     * @param parametros String concatenando los idParametro. ej: 123,456,789
     * @param periodos
     * @see com.siss.dto.amb.ReporteParametro
     * @see com.siss.ifacade.amb.ActividadEconomicaFacadeRemote
     * @return List<{@link com.siss.dto.amb.ReporteParametro ReporteParametro}> necesario para elaborar el reporte de parámetros informados.
     */
    public List<ReporteParametro> getReporteParametrosInformados(
            String empresas, String parametros, String periodos) throws EntityException {
        return ambFacade.getReporteParametrosInformados(empresas, parametros, periodos);
    }

    /**
     * Recupera la lista con todos los parámetros existentes.
     * @see com.siss.entity.amb.Parametro
     * @see com.siss.ifacade.amb.ActividadEconomicaFacadeRemote
     * @return Lista con los parámetros.
     */
    public List<Parametro> getParametrosMuestreadosByAll() throws EntityException {
        return ambFacade.getParametrosMuestreadosByAll();
    }

    /**
     * Obtiene los reportes de cumplimiento de las empresas especificadas,
     * dentro de un rango de período y los parámetros deseados.
     * @param empresas String con los idEmpresa1
     * @param periodos
     * @see com.siss.dto.amb.ReporteCumplimiento
     * @see com.siss.ifacade.amb.ActividadEconomicaFacadeRemote
     * @return List<{@link com.siss.dto.amb.ReporteCumplimiento ReporteCumplimiento}> necesario para elaborar el reporte de cumplimiento de la empresa.
     */
    public List<ReporteCumplimiento> getReporteCumplimiento(String empresas, String periodos) throws EntityException {
        return ambFacade.getReporteCumplimiento(empresas, periodos);
    }

    /**
     * Crea un cronograma que se guarda en la lista de cronogramas de la empresa en una fecha determinada.
     * @param rut Identificador de la empresa.
     * @param actividadEconomica Identificador de la actividad económica.
     * @param descripcion Descripcion de Hito.
     * @param fechaTermino Fecha de término del Hito,
     * después de esta fecha el hito no puede ser modificado o eliminado.
     * @see com.siss.ifacade.amb.ActividadEconomicaFacadeRemote
     */
    public void createCronogramaAE(long rut, long actividadEconomica, String descripcion, Date fechaTermino) throws EntityException {
        ambFacade.createCronogramaAE(rut, actividadEconomica, descripcion, fechaTermino);
    }

    /**
     * Borra un cronograma de la empresa por el identificador del cronograma.
     * @param cronogramaId Identificador del cronograma.
     * @param rut Identificador de la empresa.
     * @param actividadEconomica Identificador de la actividad económica.
     * @see com.siss.ifacade.amb.ActividadEconomicaFacadeRemote
     */
    public void deleteCronogramaAE(int cronogramaId, long rut, long actividadEconomica) throws EntityException {
        ambFacade.deleteCronogramaAE(cronogramaId, rut, actividadEconomica);
    }

    /**
     * Actualiza los datos de un cronograma para una empresa.
     * @param cronogramaId Identificador del cronograma.
     * @param rut Identificador de la empresa.
     * @param actividadEconomicaId Identificador de la actividad económica.
     * @param descripcion descripción del cronograma.
     * @param fechaTermino fecha de término para el cronograma.
     * @see com.siss.entity.amb.CronogramaAE
     * @see com.siss.ifacade.amb.ActividadEconomicaFacadeRemote
     * @return El modelo {@link com.siss.entity.amb.CronogramaAE CronogramaAE} con los datos actualizaados.
     */
    public CronogramaAE updateCronogramaAE(int cronogramaId, long rut, long actividadEconomicaId, String descripcion, Date fechaTermino) throws EntityException {
        return ambFacade.updateCronogramaAE(cronogramaId, rut, actividadEconomicaId, descripcion, fechaTermino);
    }

    /**
     * Obtiene todos los cronogramas asociados a la empresa.
     * @param rut Identificador de la empresa.
     * @param actividadEconomicaId Identificador de la actividad económica.
     * @see com.siss.entity.amb.CronogramaAE
     * @see com.siss.ifacade.amb.ActividadEconomicaFacadeRemote
     * @return Lista con todos los cronogramas asociados a la empresa.
     */
    public List<CronogramaAE> getCronogramaAEByRutByActividadEconomica(long rut, long actividadEconomicaId) throws EntityException {
        return ambFacade.getCronogramaAEByRutByActividadEconomica(rut, actividadEconomicaId);
    }

    /**
     * Obtiene un cronograma asociado a una empresa.
     * @param cronogramaId Identificador del cronograma.
     * @param rut Identificador de la empresa.
     * @param actividadEconomicaId Identificador de la actividad económica.
     * @see com.siss.entity.amb.CronogramaAE
     * @see com.siss.ifacade.amb.ActividadEconomicaFacadeRemote
     * @return El modelo {@link com.siss.entity.amb.CronogramaAE CronogramaAE} con los datos cargados.
     */
    public CronogramaAE getCronogramaAefindByCronogramaIdByRutByActividadEconomicaId(int cronogramaId, long rut, long actividadEconomicaId) throws EntityException {
        return ambFacade.getCronogramaAEfindByCronogramaIdByRutByActividadEconomicaId(cronogramaId, rut, actividadEconomicaId);
    }

    /**
     * Obtiene información sobre los puntos de descarga asociados a una empresa.
     * @param empresaId Identificador de la empresa.
     * @param actividadEconomicaId Identificador de la actividad económica.
     * @see com.siss.dto.amb.PtoDescarga
     * @see com.siss.ifacade.amb.ActividadEconomicaFacadeRemote
     * @return Lista con los {@link com.siss.dto.amb.PtoDescarga PtoDescarga} asociados a la empresa.
     */
    public List<PtoDescarga> getPtoDescargaInformacionGeneral(int empresaId, int actividadEconomicaId) throws EntityException {
        return ambFacade.getPtoDescargaInformacionGeneral(empresaId, actividadEconomicaId);
    }

    /**
     * Obtiene información sobre la resolución de calificación ambiental (RCA) de la empresa especificada.
     * @param empresaId Identificador de la empresa.
     * @param actividadEconomicaId Identificador de la actividad económica.
     * @see com.siss.dto.amb.ActividadEconomica
     * @see com.siss.ifacade.amb.ActividadEconomicaFacadeRemote
     * @return Modelo {@link com.siss.dto.amb.ActividadEconomica ActividadEconomica} con la información de la resolución.
     */
    public ActividadEconomica getEIndustrialRca(int empresaId, int actividadEconomicaId) throws EntityException {
        return ambFacade.getEIndustrialRca(empresaId, actividadEconomicaId);
    }

    /**
     * Obtiene la data de {@link com.siss.dto.amb.ActividadEconomica ActividadEconomica} asociada a la empresa.
     * @param empresaId Identificador de la empresa.
     * @param actividadEconomicaId Identificador de la actividad económica.
     * @see com.siss.dto.amb.ActividadEconomica
     * @see com.siss.ifacade.amb.ActividadEconomicaFacadeRemote
     * @return Data de la {@link com.siss.dto.amb.ActividadEconomica ActividadEconomica} asociada a la empresa.
     */
    public ActividadEconomica getEIndustrialInformacion(int empresaId, int actividadEconomicaId) throws EntityException {
        return ambFacade.getEIndustrialInformacion(empresaId, actividadEconomicaId);
    }

    /**
     * Elimina la asociación de un fiscalizador a las empresas seleccionadas.
     * @param funcionarioId identificador del {@link com.siss.entity.mae.Funcionario Funcionario}.
     * @param empresas String con la suma de idEmpresa@idActividadconomica ej:12345678@1,63952841@1
     * @see com.siss.ifacade.amb.ActividadEconomicaFacadeRemote
     */
    public void deleteAsociacionPlantaFiscalizador(Integer funcionarioId, String empresas) throws EntityException {
        ambFacade.deleteAsociacionPlantaFiscalizador(funcionarioId, empresas);
    }

    /**
     * Crea la asociación de un fiscalizador a las empresas seleccionadas.
     * @param funcionarioId identificador del {@link com.siss.entity.mae.Funcionario Funcionario}.
     * @param empresas String con la suma de idEmpresa@idActividadconomica ej:12345678@1,63952841@1
     * @see com.siss.ifacade.amb.ActividadEconomicaFacadeRemote
     */
    public void agregaAsociacionPlantaFiscalizador(Integer funcionarioId, String empresas) throws EntityException {
        ambFacade.agregaAsociacionPlantaFiscalizador(funcionarioId, empresas);
    }

    /**
     * Obtiene una lista con las empresas ({@link com.siss.dto.amb.ActividadEconomica ActividadEconomica}) que NO estén asociados
     * al {@link com.siss.entity.mae.Funcionario Funcionario} dada una lista de comunas.
     * @param comunas String concatenado con los id de las comunas. ej:24,63,35,2
     * @param rpm Tipo de resolución por monitoreo, por defecto 7, TODOS
     * @param funcionarioId Identificador del {@link com.siss.entity.mae.Funcionario Funcionario}.
     * @see com.siss.dto.amb.ActividadEconomica
     * @see com.siss.entity.mae.Funcionario
     * @see com.siss.ifacade.amb.ActividadEconomicaFacadeRemote
     * @return Lista con las {@link com.siss.dto.amb.ActividadEconomica ActividadEconomica} de las comunas seleccionadas
     * y que no esten asignadas al {@link com.siss.entity.mae.Funcionario Funcionario}.
     */
    public List<ActividadEconomica> getEIndustrialSinFiscalizador(String comunas, Integer rpm, Integer funcionarioId) throws EntityException {
        return ambFacade.getEIndustrialSinFiscalizador(comunas, rpm, funcionarioId);
    }

    /**
     * Obtiene una lista con las empresas ({@link com.siss.dto.amb.ActividadEconomica ActividadEconomica}) que SI estén asociados
     * al {@link com.siss.entity.mae.Funcionario Funcionario} dada una lista de comunas.
     * @param comunas String concatenado con los id de las comunas. ej:24,63,35,2
     * @param rpm Tipo de resolución por monitoreo, por defecto 7, TODOS
     * @param funcionarioId Identificador del {@link com.siss.entity.mae.Funcionario Funcionario}.
     * @see com.siss.dto.amb.ActividadEconomica
     * @see com.siss.entity.mae.Funcionario
     * @see com.siss.ifacade.amb.ActividadEconomicaFacadeRemote
     * @return Lista con las {@link com.siss.dto.amb.ActividadEconomica ActividadEconomica} de las comunas seleccionadas
     * y que estan asignadas al {@link com.siss.entity.mae.Funcionario Funcionario}.
     */
    public List<ActividadEconomica> getEIndustrialConFiscalizador(String comunas, Integer rpm, Integer funcionarioId) throws EntityException {
        return ambFacade.getEIndustrialConFiscalizador(comunas, rpm, funcionarioId);
    }

    /**
     * Obtiene una List<{@link com.siss.dto.amb.PtoDescarga PtoDescarga}> con las empresas
     * asociadas a las comunas seleccionadas por {@link com.siss.entity.mae.Funcionario Funcionario} fiscalizador.
     * @param funcionarioId Identificador del {@link com.siss.entity.mae.Funcionario Funcionario}.
     * @param comunas String concatenado con los id de la comunas. ej:23,12,15,20
     * @see com.siss.dto.amb.PtoDescarga
     * @see com.siss.ifacade.amb.ActividadEconomicaFacadeRemote
     * @return List<{@link com.siss.dto.amb.PtoDescarga PtoDescarga}> con las empresas asociadas al fiscalizador y las comunas.
     */
    public List<PtoDescarga> getEIAsignadosPorComuna(Integer funcionarioId, String comunas) throws EntityException {
        return ambFacade.getEIAsignadosPorComuna(funcionarioId, comunas);
    }
}
