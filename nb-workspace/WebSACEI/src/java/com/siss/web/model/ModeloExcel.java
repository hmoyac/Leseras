package com.siss.web.model;

import com.siss.dto.amb.PtoDescarga;
import com.siss.dto.riles.ReporteCumplimiento;
import com.siss.dto.riles.ReporteParametro;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author hmoya
 *
 * Modelo con el cual se realiza la exportación a excel de diversas antallas de la aplicación.
 */
@XmlRootElement
public class ModeloExcel implements Serializable {

    /**
     * Titulo del documento excel.
     */
    private String titulo;
    /**
     * Fecha de generación del excel.
     */
    private String fecha;
    /**
     * Periodo inicial que contiene la data del execel.
     */
    private String periodoInicial;
    /**
     * Periodo inicial que contiene la data del execel.
     */
    private String periodoFinal;
    /**
     * region a la que pertenecen las empresas del excel.
     */
    private String region;

    /**
     * Lista con las empresas exportables en la página de certificados.
     */
    private List<PtoDescarga> industrias;
    /**
     * Lista con las empresas exportables en la página de reportes de cumplimiento.
     */
    private List<ReporteCumplimiento> reporteCumplimiento;
    /**
     * Lista con las empresas exportables en la página de reportes de parámetros.
     */
    private List<ReporteParametro> reporteParametro;
    /**
     * Lista con los cronógramas asociados a una empresa.
     */
    private List<HitoIcemodel> reporteCronograma;
    /**
     * Nombre de la industria asociada a los cronogramas.
     */
    private String industria;
    /**
     * Nombre de la planta asociada a los cronogramas.
     */
    private String planta;
    /**
     * Nombre de la comuna asociada a los industria.
     */
    private String comuna;
    /**
     * Tipo de resolución (RCA)
     */
    private String rca;
    /**
     * Fecha de la resolución (RCA)
     */
    private String fechaRca;
    /**
     * Tipo de resolución.
     */
    private String norma;
    /**
     * Fecha de la resolución.
     */
    private String fechaNorma;

    /**
     * Constructor por defecto.
     */
    public ModeloExcel() {
        this.industrias = new ArrayList<PtoDescarga>();
        this.reporteCumplimiento = new ArrayList<ReporteCumplimiento>();
        this.reporteParametro = new ArrayList<ReporteParametro>();
        this.reporteCronograma = new ArrayList<HitoIcemodel>();
    }

    /**
     * Constructor con parámetros del modelo excel.
     * @param titulo String con el titulo del excel.
     * @param fecha string con la fecha de generación del excel.
     * @param periodoInicial String con el periodo inicial.
     * @param periodoFinal String con el periodo final.
     * @param region String con el nombre de la región a la que pertenecen las industrias.
     * @param industrias Lista con las industrias asociadas a los certificados.
     * @param reporteCumplimiento Lista con los reportes de cumplimiento.
     * @param reporteParametro lista con los reportes de parámetros.
     */
    public ModeloExcel(String titulo, String fecha, String periodoInicial, String periodoFinal, String region, List<PtoDescarga> industrias, List<ReporteCumplimiento> reporteCumplimiento, List<ReporteParametro> reporteParametro) {
        this.titulo = titulo;
        this.fecha = fecha;
        this.periodoInicial = periodoInicial;
        this.periodoFinal = periodoFinal;
        this.region = region;
        this.industrias = industrias;
        this.reporteCumplimiento = reporteCumplimiento;
        this.reporteParametro = reporteParametro;
        this.reporteCronograma = new ArrayList<HitoIcemodel>();
    }

    /**
     * Constructor con parámetros del modelo excel.
     * @param titulo String con el titulo del excel.
     * @param fecha string con la fecha de generación del excel.
     * @param periodoInicial String con el periodo inicial.
     * @param periodoFinal String con el periodo final.
     * @param region String con el nombre de la región a la que pertenecen las industrias.
     */
    public ModeloExcel(String titulo, String fecha, String periodoInicial, String periodoFinal, String region) {
        this.titulo = titulo;
        this.fecha = fecha;
        this.periodoInicial = periodoInicial;
        this.periodoFinal = periodoFinal;
        this.region = region;
        this.industrias = new ArrayList<PtoDescarga>();
        this.reporteCumplimiento = new ArrayList<ReporteCumplimiento>();
        this.reporteParametro = new ArrayList<ReporteParametro>();
        this.reporteCronograma = new ArrayList<HitoIcemodel>();
    }

    /**
     * Retorna la fecha de generación del excel.
     * @return Fecha de generación del excel.
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * Setea la fecha de generación del excel.
     * @param fecha String con la fecha de generación del excel.
     */
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    /**
     * Retorna la lista con las industrias obtenidas en la página de certificados.
     * @return Lista con las industrias obtenidas en la página de certificados.
     */
    public List<PtoDescarga> getIndustrias() {
        return industrias;
    }

    /**
     * Setea la lista con las industrias obtenidas en la página de certificados.
     * @param industrias Lista con las industrias obtenidas en la página de certificados.
     */
    public void setIndustrias(List<PtoDescarga> industrias) {
        this.industrias = industrias;
    }

    /**
     * Retorna el periodo final que se ocupó para obtener la data del excel.
     * @return Periodo final que se ocupó para obtener la data del excel.
     */
    public String getPeriodoFinal() {
        return periodoFinal;
    }

    /**
     * Setea el periodo final que se ocupó para obtener la data del excel.
     * @param periodoFinal Periodo final que se ocupó para obtener la data del excel.
     */
    public void setPeriodoFinal(String periodoFinal) {
        this.periodoFinal = periodoFinal;
    }

    /**
     * Retorna el periodo inicial que se ocupó para obtener la data del excel.
     * @return Periodo inicial que se ocupó para obtener la data del excel.
     */
    public String getPeriodoInicial() {
        return periodoInicial;
    }

    /**
     * Setea el periodo inicial que se ocupó para obtener la data del excel.
     * @param periodoInicial Periodo inicial que se ocupó para obtener la data del excel.
     */
    public void setPeriodoInicial(String periodoInicial) {
        this.periodoInicial = periodoInicial;
    }

    /**
     * Retorna el nombre de la región a la que pertenecen las industrias.
     * @return Nombre de la región a la que pertenecen las industrias.
     */
    public String getRegion() {
        return region;
    }

    /**
     * Setea el nombre de la región a la que pertenecen las industrias.
     * @param region Nombre de la región a la que pertenecen las industrias.
     */
    public void setRegion(String region) {
        this.region = region;
    }

    /**
     * Retorna la lista con los reportes de cumplimiento.
     * @return Lista con los reportes de cumplimiento.
     */
    public List<ReporteCumplimiento> getReporteCumplimiento() {
        return reporteCumplimiento;
    }

    /**
     * Setea la lista con los reportes de cumplimiento.
     * @param reporteCumplimiento Lista con los reportes de cumplimiento.
     */
    public void setReporteCumplimiento(List<ReporteCumplimiento> reporteCumplimiento) {
        this.reporteCumplimiento = reporteCumplimiento;
    }

    /**
     * Retorna la lista con los reportes de parámetro.
     * @return Lista con los reportes de parámetro.
     */
    public List<ReporteParametro> getReporteParametro() {
        return reporteParametro;
    }

    /**
     * Setea la lista con los reportes de parámetro.
     * @param reporteParametro Lista con los reportes de parámetro.
     */
    public void setReporteParametro(List<ReporteParametro> reporteParametro) {
        this.reporteParametro = reporteParametro;
    }

    /**
     * Retorna el titulo del documento excel.
     * @return Titulo del documento excel.
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Setea el titulo del documento excel.
     * @param titulo Titulo del documento excel.
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * Obtiene la lista de los cronogramas asociados a la empresa.
     * @return Lista con los cronogramas.
     */
    public List<HitoIcemodel> getReporteCronograma() {
        return reporteCronograma;
    }

    /**
     * Setea la lista de los cronogramas asociados a la empresa.
     * @param reporteCronograma Lista de los cronogramas asociados a la empresa.
     */
    public void setReporteCronograma(List<HitoIcemodel> reporteCronograma) {
        this.reporteCronograma = reporteCronograma;
    }

    /**
     * Retorna el nombre la comuna asociada a la empresa y planta.
     * @return nombre la comuna asociada a la empresa y planta.
     */
    public String getComuna() {
        return comuna;
    }

    /**
     * Setea el nombre la comuna asociada a la empresa y planta.
     * @param comuna nombre la comuna asociada a la empresa y planta.
     */
    public void setComuna(String comuna) {
        this.comuna = comuna;
    }

    /**
     * Retorna la fecha de resolución para la planta.
     * @return la fecha de resolución para la planta.
     */
    public String getFechaNorma() {
        return fechaNorma;
    }

    /**
     * Setea la fecha de resolución para la planta.
     * @param fechaNorma fecha de resolución para la planta.
     */
    public void setFechaNorma(String fechaNorma) {
        this.fechaNorma = fechaNorma;
    }

    /**
     * Retorna la fecha de resolución RCA para la planta.
     * @return la fecha de resolución RCA para la planta.
     */
    public String getFechaRca() {
        return fechaRca;
    }

    /**
     * Setea la fecha de resolución RCA para la planta.
     * @param fechaRca la fecha de resolución RCA para la planta.
     */
    public void setFechaRca(String fechaRca) {
        this.fechaRca = fechaRca;
    }

    /**
     * Retorna el nombre de la industria.
     * @return el nombre de la industria.
     */
    public String getIndustria() {
        return industria;
    }

    /**
     * Setea el nombre de la industria.
     * @param industria el nombre de la industria.
     */
    public void setIndustria(String industria) {
        this.industria = industria;
    }

    /**
     * Retorna el nombre de la norma.
     * @return el nombre de la norma.
     */
    public String getNorma() {
        return norma;
    }

    /**
     * Setea el nombre de la norma.
     * @param norma el nombre de la norma.
     */
    public void setNorma(String norma) {
        this.norma = norma;
    }

    /**
     * Retorna el nombre de la planata asociada a la industria.
     * @return el nombre de la planata asociada a la industria.
     */
    public String getPlanta() {
        return planta;
    }

    /**
     * Stetea el nombre de la planata asociada a la industria.
     * @param planta el nombre de la planata asociada a la industria.
     */
    public void setPlanta(String planta) {
        this.planta = planta;
    }

    /**
     * Retorna el nombre del tipo de resolución.
     * @return nombre del tipo de resolución.
     */
    public String getRca() {
        return rca;
    }

    /**
     * Setea el nombre del tipo de resolución.
     * @param rca nombre del tipo de resolución.
     */
    public void setRca(String rca) {
        this.rca = rca;
    }

}
