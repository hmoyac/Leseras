package com.siss.web.bean;

import com.icesoft.faces.component.ext.RowSelectorEvent;
import com.siss.dto.amb.ActividadEconomica;
import com.siss.dto.amb.PtoDescarga;
import com.siss.entity.riles.HorarioDescarga;
import com.siss.exception.EntityException;
import com.siss.web.resource.WebConstants;
import com.siss.web.FacesUtil;
import com.siss.web.MensajesBean;
import com.siss.web.Util;
import com.siss.web.delegate.amb.ActividadEconomicaDelegate;
import com.siss.web.delegate.riles.EnvioRilesDelegate;
import com.siss.web.model.HorarioDescargaIceModel;
import java.util.ArrayList;
import java.util.List;
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
 * Bean encargado de establecer y actualizar la frecuencia mensual de los monitoreos a la empresa seleccionada,
 * junto con mostrar la información general de la empresa.
 */
public class InformacionGeneralIndustriaBean {

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
     * Lista con la data de los puntos de descarga asociados a la empresa.
     */
    private List<PtoDescarga> listaPtoDescarga = new ArrayList<PtoDescarga>();
    /**
     * Lista con el modelo que representa la data de las frecuencias de monitoreo asociadas a una empresa.
     */
    private List<HorarioDescargaIceModel> listaHorarioDescarga = new ArrayList<HorarioDescargaIceModel>();
    /**
     * Lista con el modelo seleccionado que representa la data de las frecuencias de monitoreo asociada a una empresa.
     */
    private List<HorarioDescargaIceModel> listaHorarioDescargaSelected = new ArrayList<HorarioDescargaIceModel>();

    /**
     * Establece la visibilidad de la pantalla emergente.
     */
    private boolean visible = false;
    /**
     * Establece si la información de los puntos se muestra en forma expandida.
     */
    private boolean expandedInfoPunto = false;
    /**
     * Establece si las frecuencias de monitoreo se muestran de forma expandida.
     */
    private boolean expandedDatosDescarga = false;

    /**
     * Establece el contexto web.
     */
    private FacesContext facesContext;

    /**
     * Instancia al bean de mensajes.
     */
    private MensajesBean mensajes;

    /**
     * Constructor de la clase.
     * Inicializa los datos generales de la empresa, el contexto web, y el bean de mensajes.
     */
    public InformacionGeneralIndustriaBean() {
        empresa = new ActividadEconomica();
        facesContext = FacesContext.getCurrentInstance();
        if (mensajes == null) {
            mensajes = (MensajesBean)FacesUtil.getManagedBeanExpression(facesContext, WebConstants.BEAN_MENSAJES, MensajesBean.class);
        }
    }

    /**
     * Método encargado de realizar la carga de los datos de la empresa seleccionada, junto con hacer visibler la
     * ventana de información general de la empresa y las frecuencias de monitoreo.
     * @param actionEvent Método generado al presionar el link de información general especifico de una empresa.
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
                    listaPtoDescarga = ActividadEconomicaDelegate.getInstance().getPtoDescargaInformacionGeneral(empresaId, actividadEconomicaId);
                    visible = true;
                }
            }
        } catch (EntityException  ex) {
            String msjDialog = Util.getString(FacesContext.getCurrentInstance(), "ErrorInternoAplicacion") + "<br>" +
                    Util.getString(FacesContext.getCurrentInstance(), "ErrorPeristente");
            Util.errorExeption(msjDialog, "Error", FacesMessage.SEVERITY_WARN);
            Logger.getLogger(InformacionGeneralIndustriaBean.class.getName()).log(Level.WARNING, Util.getText(ex));
            visible = false;
        } catch (EJBException ex) {
            String msjDialog = Util.getString(FacesContext.getCurrentInstance(), "ErrorInternoAplicacion") + "<br>" +
                    Util.getString(FacesContext.getCurrentInstance(), "ErrorPeristente");
            Util.errorExeption(msjDialog, "Error", FacesMessage.SEVERITY_FATAL);
            Logger.getLogger(InformacionGeneralIndustriaBean.class.getName()).log(Level.SEVERE, Util.getText(ex));
            visible = false;
        } catch (Exception ex) {
            Logger.getLogger(InformacionGeneralIndustriaBean.class.getName()).log(Level.SEVERE, null, ex);
            Util.mostrarError(ex);
            visible = false;
        }
    }

    /**
     * Método encargado de establecer las frecuencias de monitoreo seleccionadas, a las cuales se les puede cambar la data.
     * @param event Evento de selección que ocurre al seleccionar una fila de la tabla.
     */
    public void rowSelectionListenerHorario(RowSelectorEvent event) {
        HorarioDescargaIceModel modelo = listaHorarioDescarga.get(event.getRow());
        if (modelo != null) {
            modelo.setEditar(true);
            if (!listaHorarioDescargaSelected.contains(modelo)) {
                modelo.setSelected(true);
                listaHorarioDescargaSelected.add(modelo);
            }
        }
    }

    /**
     * Método encargado de realizar la actualización de la data de las frecuencias de monitoreo que se desea modificar.
     * @param actionEvent Evento de botón asociado a la acción de modificar.
     */
    public void updateHorarioDescarga(ActionEvent actionEvent) {
        try {
            if (listaHorarioDescargaSelected != null && !listaHorarioDescargaSelected.isEmpty()) {
                for (HorarioDescargaIceModel horario : listaHorarioDescargaSelected) {
                    String valorMonitoreo = String.valueOf(Math.round(new Float(horario.getMonitoreo())));
                    try {
                        new Short(valorMonitoreo);
                    } catch (Exception e) {
                        valorMonitoreo = "0";
                    }
                    HorarioDescarga horarioUpdate = EnvioRilesDelegate.getInstance().updateHorarioDescarga(
                                                                    empresaId, actividadEconomicaId,
                                                                    horario.getHorarioDescargaPK().getMes(),
                                                                    new Short(valorMonitoreo),
                                                                    horario.getHorario());
                }
                for (HorarioDescargaIceModel ice : listaHorarioDescarga) {
                    if (ice.isSelected()) {
                        ice.setSelected(false);
                        ice.setEditar(false);
                    }
                }
                listaHorarioDescargaSelected = new ArrayList<HorarioDescargaIceModel>();
                Util.mostrarMensaje(Util.getString(facesContext, "MensajeActualiza"));
            }
        } catch (EntityException  ex) {
            String msjDialog = Util.getString(FacesContext.getCurrentInstance(), "ErrorInternoAplicacion") + "<br>" +
                    Util.getString(FacesContext.getCurrentInstance(), "ErrorPeristente");
            Util.errorExeption(msjDialog, "Error", FacesMessage.SEVERITY_WARN);
            Logger.getLogger(InformacionGeneralIndustriaBean.class.getName()).log(Level.WARNING, Util.getText(ex));
        } catch (EJBException ex) {
            String msjDialog = Util.getString(FacesContext.getCurrentInstance(), "ErrorInternoAplicacion") + "<br>" +
                    Util.getString(FacesContext.getCurrentInstance(), "ErrorPeristente");
            Util.errorExeption(msjDialog, "Error", FacesMessage.SEVERITY_FATAL);
            Logger.getLogger(InformacionGeneralIndustriaBean.class.getName()).log(Level.SEVERE, Util.getText(ex));
        } catch (Exception ex) {
            Logger.getLogger(InformacionGeneralIndustriaBean.class.getName()).log(Level.SEVERE, null, ex);
            Util.mostrarError(ex);
        }
    }

    /**
     * Método encargado de cerrar la ventana de Información general de la empresa.
     */
    public void closePopup() {
        visible = false;
        expandedDatosDescarga = false;
        listaHorarioDescarga = new ArrayList<HorarioDescargaIceModel>();
        listaHorarioDescargaSelected = new ArrayList<HorarioDescargaIceModel>();
    }

    /**
     * Obtiene la visibilidad de la pantalla emergente de la Información general.
     * @return Boolean visibilidad de la pantalla emergente de la Información general.
     */
    public boolean isVisible() {
        return visible;
    }

    /**
     * Setea la visibilidad de la pantalla emergente de Información general.
     * @param visible Boolean visibilidad de la pantalla emergente de Información general.
     */
    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    /**
     * Retorna un boolean representando si las frecuencias de monitoreo se muestran en un panel expandido,
     * si es verdadero, se cargan las frecuencias de monitoreo asociadas a la empresa.
     * @return Boolean representando si las frecuencias de monitoreo se muestran en un panel expandido.
     * @see HorarioDescarga
     * @see InvocarEjb#getHorarioDescargaByRutByActividadEconomica(long, long)
     */
    public boolean isExpandedDatosDescarga() {
        try {
            if (expandedDatosDescarga && listaHorarioDescarga.isEmpty()) {
                List<HorarioDescarga> lista = EnvioRilesDelegate.getInstance().getHorarioDescargaByRutByActividadEconomica(empresaId, actividadEconomicaId);
                for (HorarioDescarga item : lista) {
                    HorarioDescargaIceModel modeloIce = (new HorarioDescargaIceModel()).transforma(item);
                    listaHorarioDescarga.add(modeloIce);
                }
            }
        } catch (EJBException ex) {
            String msjDialog = Util.getString(FacesContext.getCurrentInstance(), "ErrorInternoAplicacion") + "<br>" +
                    Util.getString(FacesContext.getCurrentInstance(), "ErrorPeristente");
            Util.errorExeption(msjDialog, "Error", FacesMessage.SEVERITY_FATAL);
            Logger.getLogger(InformacionGeneralIndustriaBean.class.getName()).log(Level.SEVERE, Util.getText(ex));
        } catch (Exception ex) {
            Logger.getLogger(InformacionGeneralIndustriaBean.class.getName()).log(Level.SEVERE, null, ex);
            Util.mostrarError(ex);
        }
        return expandedDatosDescarga;
    }

    /**
     * Setea un boolean representando si las frecuencias de monitoreo se muestran en un panel expandido.
     * @param expandedDatosDescarga Boolean representando si las frecuencias de monitoreo se muestran en un panel expandido.
     */
    public void setExpandedDatosDescarga(boolean expandedDatosDescarga) {
        this.expandedDatosDescarga = expandedDatosDescarga;
    }

    /**
     * Retorna un boolean indicando si la información de los puntos de escarga se muestran en un panel expandido.
     * @return Boolean indicando si la información de los puntos de escarga se muestran en un panel expandido.
     */
    public boolean isExpandedInfoPunto() {
        return expandedInfoPunto;
    }

    /**
     * Setea un boolean indicando si la información de los puntos de escarga se muestran en un panel expandido.
     * @param expandedInfoPunto Boolean indicando si la información de los puntos de escarga se muestran en un panel expandido.
     */
    public void setExpandedInfoPunto(boolean expandedInfoPunto) {
        this.expandedInfoPunto = expandedInfoPunto;
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
     * Retorna la lista de puntos de descarga de la empresa seleccionada.
     * @return Lista de puntos de descarga de la empresa seleccionada.
     */
    public List<PtoDescarga> getListaPtoDescarga() {
        return listaPtoDescarga;
    }

    /**
     * Setea la lista de puntos de descarga de la empresa seleccionada.
     * @param listaPtoDescarga Lista de puntos de descarga de la empresa seleccionada.
     */
    public void setListaPtoDescarga(List<PtoDescarga> listaPtoDescarga) {
        this.listaPtoDescarga = listaPtoDescarga;
    }

    /**
     * Retorna la lista de las frecuencias de monitoreo asociadas a la empresa seleccionada.
     * @return Lista de las frecuencias de monitoreo asociadas a la empresa seleccionada.
     */
    public List<HorarioDescargaIceModel> getListaHorarioDescarga() {
        return listaHorarioDescarga;
    }

    /**
     * Setea la lista de las frecuencias de monitoreo asociadas a la empresa seleccionada.
     * @param listaHorarioDescarga Lista de las frecuencias de monitoreo asociadas a la empresa seleccionada.
     */
    public void setListaHorarioDescarga(List<HorarioDescargaIceModel> listaHorarioDescarga) {
        this.listaHorarioDescarga = listaHorarioDescarga;
    }

    /**
     * Retorna la lista de las frecuencias de monitoreo seleccionadas.
     * @return Lista de las frecuencias de monitoreo seleccionadas.
     */
    public List<HorarioDescargaIceModel> getListaHorarioDescargaSelected() {
        return listaHorarioDescargaSelected;
    }

    /**
     * Setea la lista de las frecuencias de monitoreo seleccionadas.
     * @param listaHorarioDescargaSelected Lista de las frecuencias de monitoreo seleccionadas.
     */
    public void setListaHorarioDescargaSelected(List<HorarioDescargaIceModel> listaHorarioDescargaSelected) {
        this.listaHorarioDescargaSelected = listaHorarioDescargaSelected;
    }

}
