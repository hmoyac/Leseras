package com.siss.web.model;

import com.siss.dto.riles.PeriodoEnvioCertificado;
import com.siss.dto.riles.ReporteCumplimiento;
import com.siss.dto.riles.ReporteParametro;
import com.siss.web.resource.WebConstants;
import com.siss.web.Util;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.faces.context.FacesContext;

/**
 *
 * @author hmoya
 *
 * Modelo de datos encargado de representar los datos de las empresas, especificamente los certificados
 * que una empresa pueda tener asociado en un periodo determinado, además brinda la base de la información para
 * la generación de los certificados.
 */
public class CertificadosIceModel {

    /**
     * String concatenado que almacena empres-planta-puntoDescarga
     */
    private String clave = null;
    /**
     * Lista que guarda los certificados encontrados para una empresa.
     */
    private List<PeriodoEnvioCertificado> listaCertificados;
    /**
     * Lista que guarda los reportes de cumplimiento de una empresa.
     */
    private List<ReporteCumplimiento> listaReportesCumplimiento;
    /**
     * Lista que guarda los reportes de parámetro de una empresa.
     */
    private List<ReporteParametro> listaReportesParametro;
    /**
     * Bandera boolean que indica se el modelo ha sido seleccionado en la tabla.
     */
    private boolean selected;

    /**
     * Nombre de la columna anterior.
     */
    private String oldSort;
    /**
     * Tipo de ordenamiento anterior.
     */
    private boolean oldAscending;
    /**
     * Nombre de la columna para el cual debe realizarse el ordenamiento.
     */
    private String sortColumnName;
    /**
     * Tipo de ordenamiento, True, si es un ordenamiento ascendente.
     */
    private boolean ascending;

    /**
     * Contexto web.
     */
    private FacesContext facesContext;

    /**
     * Constructor de la clase.
     * En el se instancian el contexto web, las listas de certificados, reportes de cumplimiento y reportes de parámetros,
     * se establece que el modelo no ha sido seleccionado en la tabla.
     */
    public CertificadosIceModel() {
        facesContext = FacesContext.getCurrentInstance();
        listaCertificados = new ArrayList<PeriodoEnvioCertificado>();
        listaReportesCumplimiento = new ArrayList<ReporteCumplimiento>();
        listaReportesParametro = new ArrayList<ReporteParametro>();
        selected = false;
        oldSort = WebConstants.STRING_VACIO;
        oldAscending = false;
        sortColumnName = WebConstants.STRING_VACIO;
    }

    /**
     * Método de ordenamiento de una lista con reportes de cumplimiento, el ordenamiento se produce dado un nombre de un campo del modelo de datos,
     * y un boolean indicando si es ascendente o descendente.
     * @param lista Lista a ordenar.
     * @param ascending Tipo de ordenamiento.
     * @return Lista ordenada de los reportes de cumplimiento.
     * @see ReporteCumplimiento
     * @see Comparator
     * @see Collections#sort(java.util.List, java.util.Comparator)
     */
    private List<ReporteCumplimiento> sortCumplimiento(List<ReporteCumplimiento> lista, final boolean ascending) {
        Comparator comparator = new Comparator() {

            @Override
            public int compare(Object o1, Object o2) {
                ReporteCumplimiento c1 = (ReporteCumplimiento) o1;
                ReporteCumplimiento c2 = (ReporteCumplimiento) o2;

                String punto = Util.getString(facesContext, "Punto");
                String periodo = Util.getString(facesContext, "Periodo");
                String estado = Util.getString(facesContext, "Estado");
                String limiteAutoControl = Util.getString(facesContext, "LimiteAutoControl");
                String limiteControlDirecto = Util.getString(facesContext, "LimiteControlDirecto");
                String frecuencia = Util.getString(facesContext, "Frecuencia");
                String parametro = Util.getString(facesContext, "Parametro");
                String comuna = Util.getString(facesContext, "Comuna");
                String tipo = Util.getString(facesContext, "Tipo");
                String numeroDocumento = Util.getString(facesContext, "NumeroDocumento");

                try {
                    if (sortColumnName == null) {
                        return 0;
                    } else if (sortColumnName.equals(punto)) {
                        return ascending ? c1.getDescripcionPuntoDescarga().compareTo(c2.getDescripcionPuntoDescarga()) : c2.getDescripcionPuntoDescarga().compareTo(c1.getDescripcionPuntoDescarga());
                    } else if (sortColumnName.equals(tipo)) {
                        return ascending ? c1.getTipo().compareTo(c2.getTipo()) : c2.getTipo().compareTo(c1.getTipo());
                    } else if (sortColumnName.equals(numeroDocumento)) {
                        return ascending ? c1.getNumeroDocumento().compareTo(c2.getNumeroDocumento()) : c2.getNumeroDocumento().compareTo(c1.getNumeroDocumento());
                    } else if (sortColumnName.equals(periodo)) {
                        return ascending ? c1.getPeriodoInformado().compareTo(c2.getPeriodoInformado()) : c2.getPeriodoInformado().compareTo(c1.getPeriodoInformado());
                    } else if (sortColumnName.equals(comuna)) {
                        return ascending ? c1.getComuna().compareTo(c2.getComuna()) : c2.getComuna().compareTo(c1.getComuna());
                    } else if (sortColumnName.equals(estado)) {
                        return ascending ? c1.getEstado().compareTo(c2.getEstado()) : c2.getEstado().compareTo(c1.getEstado());
                    } else if (sortColumnName.equals(limiteAutoControl)) {
                        return ascending ? c1.getAutoControl().compareTo(c2.getAutoControl()) : c2.getAutoControl().compareTo(c1.getAutoControl());
                    } else if (sortColumnName.equals(limiteControlDirecto)) {
                        return ascending ? c1.getControlDirecto().compareTo(c2.getControlDirecto()) : c2.getControlDirecto().compareTo(c1.getControlDirecto());
                    } else if (sortColumnName.equals(frecuencia)) {
                        return ascending ? c1.getFrecuencia().compareTo(c2.getFrecuencia()) : c2.getFrecuencia().compareTo(c1.getFrecuencia());
                    } else if (sortColumnName.equals(parametro)) {
                        return ascending ? c1.getParametro().compareTo(c2.getParametro()) : c2.getParametro().compareTo(c1.getParametro());
                    } else {
                        return 0;
                    }
                } catch (RuntimeException ex) {
                    return 0;
                }
            }
        };
        Collections.sort(lista, comparator);
        return lista;
    }

    /**
     * Método de ordenamiento de una lista con reportes de parámetros, el ordenamiento se produce dado un nombre de un campo del modelo de datos,
     * y un boolean indicando si es ascendente o descendente.
     * @param lista Lista a ordenar.
     * @param ascending Tipo de ordenamiento.
     * @return Lista ordenada de los reportes de parámetros.
     * @see ReporteParametro
     * @see Comparator
     * @see Collections#sort(java.util.List, java.util.Comparator)
     */
    private List<ReporteParametro> sortParametro(List<ReporteParametro> lista, final boolean ascending) {
        Comparator comparator = new Comparator() {

            @Override
            public int compare(Object o1, Object o2) {
                ReporteParametro c1 = (ReporteParametro) o1;
                ReporteParametro c2 = (ReporteParametro) o2;

                String tipoMuestreo = Util.getString(facesContext, "TipoMuestreo");
                String parametros = Util.getString(facesContext, "Parametros");
                String unidadMedida = Util.getString(facesContext, "UnidadMedida");
                String descripcion = Util.getString(facesContext, "Descripcion");
                String periodoInformado = Util.getString(facesContext, "PeriodoInformado");
                String valorInformado = Util.getString(facesContext, "ValorInformado");
                String control = Util.getString(facesContext, "Control");
                String comuna = Util.getString(facesContext, "Comuna");
                String tipo = Util.getString(facesContext, "Tipo");
                String numeroDocumento = Util.getString(facesContext, "NumeroDocumento");

                if (sortColumnName == null) {
                    return 0;
                } else if (sortColumnName.equals(tipoMuestreo)) {
                    return ascending ? c1.getTipoMuestreo().compareTo(c2.getTipoMuestreo()) : c2.getTipoMuestreo().compareTo(c1.getTipoMuestreo());
                } else if (sortColumnName.equals(tipo)) {
                    return ascending ? c1.getTipo().compareTo(c2.getTipo()) : c2.getTipo().compareTo(c1.getTipo());
                } else if (sortColumnName.equals(numeroDocumento)) {
                    return ascending ? c1.getNumeroDocumento().compareTo(c2.getNumeroDocumento()) : c2.getNumeroDocumento().compareTo(c1.getNumeroDocumento());
                } else if (sortColumnName.equals(comuna)) {
                        return ascending ? c1.getComuna().compareTo(c2.getComuna()) : c2.getComuna().compareTo(c1.getComuna());
                } else if (sortColumnName.equals(parametros)) {
                    return ascending ? c1.getDescripcionParametro().compareTo(c2.getDescripcionParametro()) : c2.getDescripcionParametro().compareTo(c1.getDescripcionParametro());
                } else if (sortColumnName.equals(unidadMedida)) {
                    return ascending ? c1.getDescripcionUnidadMedida().compareTo(c2.getDescripcionUnidadMedida()) : c2.getDescripcionUnidadMedida().compareTo(c1.getDescripcionUnidadMedida());
                } else if (sortColumnName.equals(descripcion)) {
                    return ascending ? c1.getDescripcionPuntoDescarga().compareTo(c2.getDescripcionPuntoDescarga()) : c2.getDescripcionPuntoDescarga().compareTo(c1.getDescripcionPuntoDescarga());
                } else if (sortColumnName.equals(periodoInformado)) {
                    return ascending ? c1.getPeriodoInformado().compareTo(c2.getPeriodoInformado()) : c2.getPeriodoInformado().compareTo(c1.getPeriodoInformado());
                } else if (sortColumnName.equals(valorInformado)) {
                    return ascending ? c1.getValorInformado().compareTo(c2.getValorInformado()) : c2.getValorInformado().compareTo(c1.getValorInformado());
                } else if (sortColumnName.equals(control)) {
                    return ascending ? c1.getDescipcionControl().compareTo(c2.getDescipcionControl()) : c2.getDescipcionControl().compareTo(c1.getDescipcionControl());
                } else {
                    return 0;
                }
            }
        };
        Collections.sort(lista, comparator);
        return lista;
    }

    /**
     * Retorna el String concatenado que almacena empres-planta-puntoDescarga.
     * @return String concatenado que almacena empres-planta-puntoDescarga.
     */
    public String getClave() {
        return clave;
    }

    /**
     * Setea el String concatenado que almacena empres-planta-puntoDescarga.
     * @param clave String concatenado que almacena empres-planta-puntoDescarga.
     */
    public void setClave(String clave) {
        this.clave = clave;
    }

    /**
     * Retorna la lista con los certificados obtenidos como resultado de la búsqueda.
     * @return Lista con los certificados obtenidos como resultado de la búsqueda.
     */
    public List<PeriodoEnvioCertificado> getListaCertificados() {
        return listaCertificados;
    }

    /**
     * Setea la lista con los certificados obtenidos como resultado de la búsqueda.
     * @param listaCertificados Lista con los certificados obtenidos como resultado de la búsqueda.
     */
    public void setListaCertificados(List<PeriodoEnvioCertificado> listaCertificados) {
        this.listaCertificados = listaCertificados;
    }

    /**
     * Retorna la lista con los reportes de cumplimiento obtenidos como resultado de la búsqueda.
     * @return Lista con los reportes de cumplimiento obtenidos como resultado de la búsqueda.
     */
    public List<ReporteCumplimiento> getListaReportesCumplimiento() {
        if (!oldSort.equals(sortColumnName) ||
            oldAscending != ascending) {
            listaReportesCumplimiento = sortCumplimiento(listaReportesCumplimiento, ascending);
            oldSort = sortColumnName;
            oldAscending = ascending;
        }
        return listaReportesCumplimiento;
    }

    /**
     * Setea la lista con los reportes de cumplimiento obtenidos como resultado de la búsqueda.
     * @param listaReportesCumplimiento Lista con los reportes de cumplimiento obtenidos como resultado de la búsqueda.
     */
    public void setListaReportesCumplimiento(List<ReporteCumplimiento> listaReportesCumplimiento) {
        this.listaReportesCumplimiento = listaReportesCumplimiento;
    }

    /**
     * Retorna la lista con los reportes de parámetros obtenidos como resultado de la búsqueda.
     * @return Lista con los reportes de parámetros obtenidos como resultado de la búsqueda.
     */
    public List<ReporteParametro> getListaReportesParametro() {
        if (!oldSort.equals(sortColumnName) ||
            oldAscending != ascending) {
            listaReportesParametro = sortParametro(listaReportesParametro, ascending);
            oldSort = sortColumnName;
            oldAscending = ascending;
        }
        return listaReportesParametro;
    }

    /**
     * Setea la lista con los reportes de parámetros obtenidos como resultado de la búsqueda.
     * @param listaReportesParametro Lista con los reportes de parámetros obtenidos como resultado de la búsqueda.
     */
    public void setListaReportesParametro(List<ReporteParametro> listaReportesParametro) {
        this.listaReportesParametro = listaReportesParametro;
    }

    /**
     * Retorna booleano indicando si el modelo está seleccionado en la tabla.
     * @return booleano indicando si el modelo está seleccionado en la tabla.
     */
    public boolean isSelected() {
        return selected;
    }

    /**
     * Setea booleano indicando si el modelo está seleccionado en la tabla.
     * @param selected booleano indicando si el modelo está seleccionado en la tabla.
     */
    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    /**
     * Retorna un boolean indicando si el tipo de ordenamiento es ascendente, entonces devuelve true.
     * @return boolean indicando si el tipo de ordenamiento es ascendente.
     */
    public boolean isAscending() {
        return ascending;
    }

    /**
     * Setea un boolean indicando si el tipo de ordenamiento es ascendente, entonces devuelve true.
     * @param ascending boolean indicando si el tipo de ordenamiento es ascendente.
     */
    public void setAscending(boolean ascending) {
        this.ascending = ascending;
    }

    /**
     * Retorna el nombre de la columna para la cual se realizará el ordenamiento.
     * @return Nombre de la columna para la cual se realizará el ordenamiento.
     */
    public String getSortColumnName() {
        return sortColumnName;
    }

    /**
     * Setea el nombre de la columna para la cual se realizará el ordenamiento.
     * @param sortColumnName Nombre de la columna para la cual se realizará el ordenamiento.
     */
    public void setSortColumnName(String sortColumnName) {
        this.sortColumnName = sortColumnName;
    }

}
