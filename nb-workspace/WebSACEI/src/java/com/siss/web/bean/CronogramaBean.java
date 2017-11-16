package com.siss.web.bean;

import com.icesoft.faces.component.ext.RowSelectorEvent;
import com.siss.dto.amb.ActividadEconomica;
import com.siss.dto.amb.PtoDescarga;
import com.siss.dto.riles.ReporteCumplimiento;
import com.siss.dto.riles.ReporteParametro;
import com.siss.entity.amb.CronogramaAE;
import com.siss.exception.EntityException;
import com.siss.web.resource.WebConstants;
import com.siss.web.FacesUtil;
import com.siss.web.MensajesBean;
import com.siss.web.Util;
import com.siss.web.delegate.amb.ActividadEconomicaDelegate;
import com.siss.web.model.HitoIcemodel;
import com.siss.web.model.ModeloExcel;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJBException;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

/**
 *
 * @author hmoya
 *
 * Bean encargado de manipular el cronograma de una empresa específica, mostrando información detallada de las
 * plantas asociadas a la empresa. Lo principal es la generación de cronogramas de trabajo, en la que los fiscalizadores pueden establecer
 * pautas que ellos mismos deben seguir, estos cronogramas son especificos de una fecha de término, cuando llega esta
 * fecha, el cronograma pasa a ser un historial, el cual no puede ser modificado o eliminado.
 */
public class CronogramaBean {

    public static final String REPORTE_CRONOGRAMA = "REPORTE_CRONOGRAMA";

    /**
     * Establece la visibilidad de la pantalla emergente.
     */
    private boolean visible = false;
    /**
     * Identificador de la empresa seleccionada.
     */
    private Integer empresaId = null;
    /**
     * Identificador de la actividad económica seleccionada.
     */
    private Integer actividadEconomicaId = null;
    /**
     * Detalle de la empresa seleccionada.
     */
    private ActividadEconomica empresa = null;
    /**
     * Fecha de vencimiento para crear o modificar un cronograma.
     */
    private Date fechaVencimiento = null;
    /**
     * Descripción del hito para acciones como crear o modificar.
     */
    private String hito = null;
    /**
     * Identificador del hito a modificar o eliminar.
     */
    private Integer idHito;
    /**
     * Boolean que establece el modo de edición de la data de un cronograma.
     */
    private boolean editar = false;
    /**
     * Boolean que establece la actualización de los datos del cronograma.
     */
    private boolean cambiar = false;
    /**
     * Lista con el modelo que representa la data de un cronograma asociado a una empresa.
     */
    private List<HitoIcemodel> listaCronograma = new ArrayList<HitoIcemodel>();
    /**
     * Nombre de la columna antigua para la que se desea ordenar la lista.
     */
    private String oldSort = "";
    /**
     * Tipo de ordenamiento anterior.
     */
    private boolean oldAscending = false;
    /**
     * Nombre de la columna por la cual se va a ordenar la lista.
     */
    private String sortColumnName = "";
    /**
     * Boolean con el tipo de oredenamiento indicando si es ascendente o descendente.
     */
    private boolean ascending;
    /**
     * Instancia al bean de mensajes.
     */
    private MensajesBean mensajes;
    /**
     * contexto web de la aplicación.
     */
    private FacesContext facesContext;
    /**
     * Representa la región donde se está ejecutando la aplicación y permite cambier el idioma.
     */
    private Locale aLocale;

    private String reporteExcelCronograma = "";

    /**
     * Constructor de la clase.
     * Aca, se inicializan el contexto web, el idioma y el bean de mensajes.
     */
    public CronogramaBean() {
        facesContext = FacesContext.getCurrentInstance();
        aLocale = facesContext.getExternalContext().getRequestLocale();
        if (mensajes == null) {
            mensajes = (MensajesBean) FacesUtil.getManagedBeanExpression(facesContext, WebConstants.BEAN_MENSAJES, MensajesBean.class);
        }
        iniciaParam();
    }

    /**
     * Método que resetea los valores de los controles que permiten la creación o modificación de un cronograma.
     * El calendario es seteado a un día posterior a la fecha actual, y la descripción del hito es seteada a vacío.
     */
    private void iniciaParam() {
        Calendar calendar = Calendar.getInstance(aLocale);
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        fechaVencimiento = calendar.getTime();
        hito = "";

    }

    /**
     * Método encargado de realizar la carga de los datos de la empresa seleccionada, junto con hacer visibler la
     * ventana de cronogramas de la empresa.
     * @param actionEvent Método generado al presionar el link del cronograma especifico de una empresa.
     * @see ActionEvent
     */
    public void openPopup(ActionEvent actionEvent) {
        try {

            UIComponent componenteOrigen = actionEvent.getComponent();
            this.empresaId = Integer.parseInt(String.valueOf(componenteOrigen.getAttributes().get("paramEmpresaId")));
            this.actividadEconomicaId = Integer.parseInt(String.valueOf(componenteOrigen.getAttributes().get("paramActividadEconomicaId")));
            if (empresaId != null & actividadEconomicaId != null) {
                empresa = ActividadEconomicaDelegate.getInstance().getEIndustrialInformacion(empresaId, actividadEconomicaId);
                ActividadEconomica empresaRca = ActividadEconomicaDelegate.getInstance().getEIndustrialRca(empresaId, actividadEconomicaId);
                if (empresa != null) {
                    String noDefinido = Util.getString(facesContext, "NoDefinido");
                    if (empresaRca != null) {
                        if (empresaRca.getRca() != null) {
                            empresa.setRca(empresaRca.getRca());
                        } else {
                            empresa.setRca(noDefinido);
                        }
                        if (empresaRca.getFechaRca() != null) {
                            empresa.setFechaRca(empresaRca.getFechaRca());
                        } else {
                            empresa.setFechaRca(noDefinido);
                        }
                    } else {
                        empresa.setRca(noDefinido);
                        empresa.setFechaRca(noDefinido);
                    }
                    cargarCronogramas(empresaId, actividadEconomicaId);
                    visible = true;
                }
            }
        } catch (EntityException ex) {
            String msjDialog = Util.getString(FacesContext.getCurrentInstance(), "ErrorInternoAplicacion") + "<br>" +
                    Util.getString(FacesContext.getCurrentInstance(), "ErrorPeristente");
            Util.errorExeption(msjDialog, "Error", FacesMessage.SEVERITY_WARN);
            Logger.getLogger(CronogramaBean.class.getName()).log(Level.WARNING, Util.getText(ex));
        } catch (EJBException ex) {
            String msjDialog = Util.getString(FacesContext.getCurrentInstance(), "ErrorInternoAplicacion") + "<br>" +
                    Util.getString(FacesContext.getCurrentInstance(), "ErrorPeristente");
            Util.errorExeption(msjDialog, "Error", FacesMessage.SEVERITY_FATAL);
            Logger.getLogger(CronogramaBean.class.getName()).log(Level.SEVERE, Util.getText(ex));
        } catch (Exception ex) {
            Logger.getLogger(CronogramaBean.class.getName()).log(Level.SEVERE, null, ex);
            Util.mostrarError(ex);
        }
    }

    /**
     * Obtiene la lista con los cronogramas asociados a la empresa.
     * @param rut Identificador de la empresa.
     * @param actEconomicaId Identificador de la planta o actividad económica.
     * @see CronogramaAE
     * @see InvocarEjb#getCronogramaAEByRutByActividadEconomica(long, long)
     */
    private void cargarCronogramas(long rut, long actEconomicaId) throws Exception {
        try {
            List<CronogramaAE> listaCrono = ActividadEconomicaDelegate.getInstance().getCronogramaAEByRutByActividadEconomica(rut, actEconomicaId);
            listaCronograma = new ArrayList<HitoIcemodel>();
            if (listaCrono != null && !listaCrono.isEmpty()) {
                int contador = 0;
                for (CronogramaAE crma : listaCrono) {
                    HitoIcemodel hitoModel = new HitoIcemodel(crma);
                    hitoModel.setIndice(contador++);
                    listaCronograma.add(hitoModel.getIndice(), hitoModel);
                }
                sort();
                getReporteExcelCronograma();
            }
        } catch (EntityException ex) {
            String msjDialog = Util.getString(FacesContext.getCurrentInstance(), "ErrorInternoAplicacion") + "<br>" +
                    Util.getString(FacesContext.getCurrentInstance(), "ErrorPeristente");
            Util.errorExeption(msjDialog, "Error", FacesMessage.SEVERITY_WARN);
            Logger.getLogger(CronogramaBean.class.getName()).log(Level.WARNING, Util.getText(ex));
        } catch (EJBException ex) {
            String msjDialog = Util.getString(FacesContext.getCurrentInstance(), "ErrorInternoAplicacion") + "<br>" +
                    Util.getString(FacesContext.getCurrentInstance(), "ErrorPeristente");
            Util.errorExeption(msjDialog, "Error", FacesMessage.SEVERITY_FATAL);
            Logger.getLogger(CronogramaBean.class.getName()).log(Level.SEVERE, Util.getText(ex));
        } catch (Exception ex) {
            Logger.getLogger(CronogramaBean.class.getName()).log(Level.SEVERE, null, ex);
            Util.mostrarError(ex);
        }
    }

    public void setReporteExcelCronograma(String reporteExcelCronograma) {
        this.reporteExcelCronograma = reporteExcelCronograma;
    }

    public String getReporteExcelCronograma() {
        ModeloExcel modeloExcel = new ModeloExcel();
        try {
            if (!getListaCronograma().isEmpty()) {
                modeloExcel.setTitulo(CronogramaBean.REPORTE_CRONOGRAMA);
                modeloExcel.setFecha("");
                modeloExcel.setIndustrias(new ArrayList<PtoDescarga>());
                modeloExcel.setReporteCumplimiento(new ArrayList<ReporteCumplimiento>());
                modeloExcel.setReporteParametro(new ArrayList<ReporteParametro>());
                modeloExcel.setPeriodoInicial("");
                modeloExcel.setPeriodoFinal("");
                modeloExcel.setIndustria(getEmpresa().getEmpresa());
                modeloExcel.setPlanta(getEmpresa().getActividadEconomica());
                modeloExcel.setRegion(getEmpresa().getRegion());
                modeloExcel.setComuna(getEmpresa().getComuna());
                modeloExcel.setRca(getEmpresa().getRca());
                modeloExcel.setFechaRca(getEmpresa().getFechaRca());
                modeloExcel.setNorma(getEmpresa().getNorma());
                modeloExcel.setFechaNorma(getEmpresa().getFechaResolucion());
                modeloExcel.setReporteCronograma(getListaCronograma());
            }
            ExportarExcelBean exportarExcelBean = (ExportarExcelBean)FacesUtil.getManagedBeanExpression(FacesContext.getCurrentInstance(), "#{exportarExcelBean}", ExportarExcelBean.class);
            if (exportarExcelBean != null) {
                exportarExcelBean.setModeloExcel(modeloExcel);
            }
        } catch (Exception ex) {
            Logger.getLogger(ExportarExcelBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }

    /**
     * Método de ordenamiento de una lista, el ordenamiento se produce dado un nombre de campo FechaVencimiento,
     * y un boolean indicando si es ascendente o descendente.
     * @see HitoIcemodel
     * @see Comparator
     * @see Collections#sort(java.util.List, java.util.Comparator)
     */
    protected void sort() {
        Comparator comparator = new Comparator() {

            public int compare(Object o1, Object o2) {
                String fechaVencimiento = Util.getString(facesContext, "FechaVencimiento");
                HitoIcemodel c1 = (HitoIcemodel) o1;
                HitoIcemodel c2 = (HitoIcemodel) o2;
                if (sortColumnName.equals(fechaVencimiento)) {
                    return ascending ? c1.getFechaTermino().compareTo(c2.getFechaTermino()) : c2.getFechaTermino().compareTo(c1.getFechaTermino());
                } else {
                    return ascending ? c1.getFechaTermino().compareTo(c2.getFechaTermino()) : c2.getFechaTermino().compareTo(c1.getFechaTermino());
                }
            }
        };
        Collections.sort(this.listaCronograma, comparator);
        List<HitoIcemodel> lista = new ArrayList<HitoIcemodel>();
        Iterator<HitoIcemodel> it = this.listaCronograma.iterator();
        int contador = 0;
        while (it.hasNext()) {
            HitoIcemodel hitoModel = it.next();
            hitoModel.setIndice(contador++);
            lista.add(hitoModel.getIndice(), hitoModel);
        }
        this.listaCronograma = lista;
    }

    /**
     * Método encagado de establecer la data de un cronograma en los componentes encargados de realizar la modificación.
     * @param actionEvent Acción al hacer click en el botón modificar de un cronograma.
     */
    public void editarCronograma(ActionEvent actionEvent) {
        try {
            UIComponent componenteOrigen = actionEvent.getComponent();
            SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
            HitoIcemodel modelo = (HitoIcemodel) componenteOrigen.getAttributes().get("paramHito");
            if (modelo != null) {
                if (modelo.isEnabled()) {
                    if (modelo.isSelected()) {
                        setFechaVencimiento(formato.parse(modelo.getFechaTermino()));
                        setHito(modelo.getDescripcion());
                        setIdHito(modelo.getId());
                        setEditar(false);
                        setCambiar(true);
                    }
                }
            }
        } catch (ParseException ex) {
            Logger.getLogger(CronogramaBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Metodo que establece el cronograma seleccionado a modo de edición.
     * @param event Evento ocurre cuando hace click en una fila  de la tabla.
     */
    public void rowSelectionListenerCronograma(RowSelectorEvent event) {
        if (event.isSelected()) {
            iniciaParam();
            setEditar(true);
            setCambiar(false);
        } else {
            iniciaParam();
            setEditar(false);
            setCambiar(false);
        }
    }

    /**
     * Método encargado de realizar la actualización de la data del cronograma que se desea modificar.
     * @param actionEvent Evento de botón asociado a la acción de modificar.
     */
    public void updateCronograma(ActionEvent actionEvent) {
        try {
            if (cambiar) {
                if (validarHito()) {
                    ActividadEconomicaDelegate.getInstance().updateCronogramaAE(idHito, empresaId, actividadEconomicaId, hito, fechaVencimiento);
                    Util.mostrarMensaje(Util.getString(facesContext, "MensajeActualiza"));
                    iniciaParam();
                    cargarCronogramas(empresaId, actividadEconomicaId);
                    setEditar(false);
                    setCambiar(false);
                    getReporteExcelCronograma();
                }
            }
        } catch (EntityException ex) {
            String msjDialog = Util.getString(FacesContext.getCurrentInstance(), "ErrorInternoAplicacion") + "<br>" +
                    Util.getString(FacesContext.getCurrentInstance(), "ErrorPeristente");
            Util.errorExeption(msjDialog, "Error", FacesMessage.SEVERITY_WARN);
            Logger.getLogger(CronogramaBean.class.getName()).log(Level.WARNING, Util.getText(ex));
        } catch (EJBException ex) {
            String msjDialog = Util.getString(FacesContext.getCurrentInstance(), "ErrorInternoAplicacion") + "<br>" +
                    Util.getString(FacesContext.getCurrentInstance(), "ErrorPeristente");
            Util.errorExeption(msjDialog, "Error", FacesMessage.SEVERITY_FATAL);
            Logger.getLogger(CronogramaBean.class.getName()).log(Level.SEVERE, Util.getText(ex));
        } catch (Exception ex) {
            Logger.getLogger(CronogramaBean.class.getName()).log(Level.SEVERE, null, ex);
            Util.mostrarError(ex);
        }
    }

    /**
     * Método encargado de agregar un cronograma a una empresa específica.
     * @param actionEvent Evento de botón asociado al agregar cronograma.
     */
    public void agregarHito(ActionEvent actionEvent) {
        try {
            if (validarHito()) {
                ActividadEconomicaDelegate.getInstance().createCronogramaAE(empresaId, actividadEconomicaId, hito, fechaVencimiento);
                cargarCronogramas(empresaId, actividadEconomicaId);
                iniciaParam();
                Util.mostrarMensaje(Util.getString(facesContext, "MensajeInserta"));
                getReporteExcelCronograma();
            }
        } catch (EntityException ex) {
            String msjDialog = Util.getString(FacesContext.getCurrentInstance(), "ErrorInternoAplicacion") + "<br>" +
                    Util.getString(FacesContext.getCurrentInstance(), "ErrorPeristente");
            Util.errorExeption(msjDialog, "Error", FacesMessage.SEVERITY_WARN);
            Logger.getLogger(CronogramaBean.class.getName()).log(Level.WARNING, Util.getText(ex));
        } catch (EJBException ex) {
            String msjDialog = Util.getString(FacesContext.getCurrentInstance(), "ErrorInternoAplicacion") + "<br>" +
                    Util.getString(FacesContext.getCurrentInstance(), "ErrorPeristente");
            Util.errorExeption(msjDialog, "Error", FacesMessage.SEVERITY_FATAL);
            Logger.getLogger(CronogramaBean.class.getName()).log(Level.SEVERE, Util.getText(ex));
        } catch (Exception ex) {
            Logger.getLogger(CronogramaBean.class.getName()).log(Level.SEVERE, null, ex);
            Util.mostrarError(ex);
        }
    }

    /**
     * Método encargado de eliminar un cronograma a una empresa específica.
     * @param actionEvent Evento de botón asociado al eliminar cronograma.
     */
    public void eliminarHito(ActionEvent actionEvent) {
        try {
            UIComponent componenteOrigen = actionEvent.getComponent();
            HitoIcemodel selectedHito = (HitoIcemodel) componenteOrigen.getAttributes().get("paramHito");
            if (selectedHito != null) {
                ActividadEconomicaDelegate.getInstance().deleteCronogramaAE(selectedHito.getId(), selectedHito.getRut(), selectedHito.getActividadEconomicaId());
                cargarCronogramas(selectedHito.getRut(), selectedHito.getActividadEconomicaId());
                Util.mostrarMensaje(Util.getString(facesContext, "MensajeElimina"));
                getReporteExcelCronograma();
            }
        } catch (EntityException ex) {
            String msjDialog = Util.getString(FacesContext.getCurrentInstance(), "ErrorInternoAplicacion") + "<br>" +
                    Util.getString(FacesContext.getCurrentInstance(), "ErrorPeristente");
            Util.errorExeption(msjDialog, "Error", FacesMessage.SEVERITY_WARN);
            Logger.getLogger(CronogramaBean.class.getName()).log(Level.WARNING, Util.getText(ex));
        } catch (EJBException ex) {
            String msjDialog = Util.getString(FacesContext.getCurrentInstance(), "ErrorInternoAplicacion") + "<br>" +
                    Util.getString(FacesContext.getCurrentInstance(), "ErrorPeristente");
            Util.errorExeption(msjDialog, "Error", FacesMessage.SEVERITY_FATAL);
            Logger.getLogger(CronogramaBean.class.getName()).log(Level.SEVERE, Util.getText(ex));
        } catch (Exception ex) {
            Logger.getLogger(CronogramaBean.class.getName()).log(Level.SEVERE, null, ex);
            Util.mostrarError(ex);
        }
    }

    /**
     * Método encargado de validar la data de un cronograma antes de asociarlo a una empresa.
     * @return verdadero si se asoció correctamente, Falso, si no se asoció.
     */
    public boolean validarHito() {
        boolean resp = true;
        if (fechaVencimiento == null || Calendar.getInstance().getTime().after(fechaVencimiento)) {
            Util.mostrarMensaje(Util.getString(facesContext, "HitoFechaPosteriorActual"));
            resp = false;
        }
        if (hito == null || hito.isEmpty()) {
            Util.mostrarMensaje(Util.getString(facesContext, "HitoNoDescripcion"));
            resp = false;
        }
        return resp;
    }

    /**
     * Método encargado de cerrar la ventana de cronogramas de la empresa.
     */
    public void closePopup() {
        visible = false;
        this.editar = false;
        this.cambiar = false;
        this.iniciaParam();
    }

    /**
     * Método capaz de cancelar la modificación de la data de un cronograma.
     * @param actionEvent Evento de botón asociado a cacelar modificar.
     */
    public void cancelarUpdateCronograma(ActionEvent actionEvent) {
        this.editar = false;
        this.cambiar = false;
        this.iniciaParam();
    }

    /**
     * Obtiene la visibilidad de la pantalla emergente de cronograma.
     * @return Boolean visibilidad de la pantalla emergente de cronograma.
     */
    public boolean isVisible() {
        return visible;
    }

    /**
     * Setea la visibilidad de la pantalla emergente de cronograma.
     * @param visible Boolean visibilidad de la pantalla emergente de cronograma.
     */
    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    /**
     * Obtiene el identificador de la actividad económica seleccionada.
     * @return El identificador de la actividad económica seleccionada.
     */
    public Integer getActividadEconomicaId() {
        return actividadEconomicaId;
    }

    /**
     * Setea el identificador de la actividad económica seleccionada.
     * @param actividadEconomicaId Identificador de la actividad económica seleccionada.
     */
    public void setActividadEconomicaId(Integer actividadEconomicaId) {
        this.actividadEconomicaId = actividadEconomicaId;
    }

    /**
     * Obtiene el detalle de la empresa seleccionada.
     * @return Detalle de la empresa seleccionada.
     * @see ActividadEconomica
     */
    public ActividadEconomica getEmpresa() {
        return empresa;
    }

    /**
     * Setea el detalle de la empresa seleccionada.
     * @param empresa Detalle de la empresa seleccionada.
     * @see ActividadEconomica
     */
    public void setEmpresa(ActividadEconomica empresa) {
        this.empresa = empresa;
    }

    /**
     * Obtiene el identificador de la empresa.(rut de la empresa)
     * @return Identificador de la empresa.
     */
    public Integer getEmpresaId() {
        return empresaId;
    }

    /**
     * Setea el identificador de la empresa.(rut de la empresa)
     * @param empresaId Identificador de la empresa.
     */
    public void setEmpresaId(Integer empresaId) {
        this.empresaId = empresaId;
    }

    /**
     * Obtiene la fecha de vencimiento de un nuevo cronograma.
     * @return Fecha de vencimiento de un nuevo cronograma.
     * @see Date
     */
    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    /**
     * Setea la fecha de vencimiento de un nuevo cronograma.
     * @param fechaVencimiento Fecha de vencimiento de un nuevo cronograma.
     * @see Date
     */
    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    /**
     * Obtiene la descripción de un cronograma que se va a insertar o modificar.
     * @return Descripción de un cronograma.
     */
    public String getHito() {
        return hito;
    }

    /**
     * Setea la descripción de un cronograma que se va a insertar o modificar.
     * @param hito Descripción de un cronograma.
     */
    public void setHito(String hito) {
        this.hito = hito;
    }

    /**
     * Retorna la lista con los cronogramas asociados a la empresa.
     * @return Lista con los cronogramas asociados a la empresa.
     */
    public List<HitoIcemodel> getListaCronograma() {
        if (!oldSort.equals(sortColumnName) || oldAscending != ascending) {
            sort();
            oldSort = sortColumnName;
            oldAscending = ascending;
        }
        return listaCronograma;
    }

    /**
     * Setea la lista con los cronogramas asociados a la empresa.
     * @param listaCronograma Lista con los cronogramas asociados a la empresa.
     */
    public void setListaCronograma(List<HitoIcemodel> listaCronograma) {
        this.listaCronograma = listaCronograma;
    }

    /**
     * Obtiene el tipo de ordenamiento.
     * @return boolean con el tipo de ordenamiento.
     */
    public boolean isAscending() {
        return ascending;
    }

    /**
     * Establece el tipode ordenamiento.
     * @param ascending tipo de ordenamiento, true si es ascendente, false si es descendente.
     */
    public void setAscending(boolean ascending) {
        this.ascending = ascending;
    }

    /**
     * Obtiene el nombre de la columna por la cual se va a ordenar la lista.
     * @return String con el nombre de la columna por la cual se va a ordenar la lista.
     */
    public String getSortColumnName() {
        return sortColumnName;
    }

    /**
     * Establece el nombre de la columna por la cual se va a ordenar la lista.
     * @param sortColumnName Nombre de la columna por la cual se va a ordenar la lista.
     */
    public void setSortColumnName(String sortColumnName) {
        this.sortColumnName = sortColumnName;
    }

    /**
     * Retorna el identificador del cronograma que se va a eliminar o modificar.
     * @return Identificador del cronograma que se va a eliminar o modificar.
     */
    public Integer getIdHito() {
        return idHito;
    }

    /**
     * Establece el identificador del cronograma que se va a eliminar o modificar.
     * @param idHito Identificador del cronograma que se va a eliminar o modificar.
     */
    public void setIdHito(Integer idHito) {
        this.idHito = idHito;
    }

    /**
     * Retorna el indicador de modo de edición.
     * @return boolean con el indicador de modo de edición.
     */
    public boolean isEditar() {
        return editar;
    }

    /**
     * Establece el modo de edición
     * @param editar Boolean que establece el modo de edición.
     */
    public void setEditar(boolean editar) {
        this.editar = editar;
    }

    /**
     * Obtiene la zona horaria por defecto.
     * @return Zona horaria por defecto.
     */
    public TimeZone getTimeZone() {
        return java.util.TimeZone.getDefault();
    }

    /**
     * Retorna el indicador de actualización.
     * @return Boolean con el indicador de actualización.
     */
    public boolean isCambiar() {
        return cambiar;
    }

    /**
     * Establece el indicador de actualización.
     * @param cambiar Boolean con el indicador de actualización.
     */
    public void setCambiar(boolean cambiar) {
        this.cambiar = cambiar;
    }
}