package com.siss.web.servlet;

import com.siss.entity.mae.Region;
import com.siss.web.FacesUtil;
import com.siss.web.Util;
import com.siss.web.UtileriaWebBean;
import com.siss.web.bean.CronogramaBean;
import com.siss.web.bean.FiscalizadorEIBean;
import com.siss.web.bean.CumplimientoGeneralBean;
import com.siss.web.bean.ExportarExcelBean;
import com.siss.web.bean.ParametrosBean;
import com.siss.web.model.ModeloExcel;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

/**
 *
 * @author hmoya
 *
 * Servlet encargado de la generación de las exportaciones de los datos a excel.
 */
public class ExportExcelServlet extends RecursoServlet {

    /**
     * Contexto web del servlet.
     */
    private FacesContext facesContext = null;

    /**
     * Implementación del método abstracto customizeParameters(HttpServletRequest request, HttpServletResponse response)
     * heredado desde el {@link com.siss.web.servlet.RecursoServlet RecursoServlet}.
     * En el cual se inicializan los elementos necesarios para la generación de los excel.
     * @param request HttpServletRequest del servlet.
     * @param response HttpServletResponse del servlet.
     */
    @Override
    public void customizeParameters(HttpServletRequest request, HttpServletResponse response) {
        try {
            facesContext = FacesUtil.getFacesContext(request, response);
            String mime = "application/vnd.ms-excel";
            this.setMime(mime);
            ExportarExcelBean exportarExcelBean = (ExportarExcelBean)FacesUtil.getManagedBeanExpression(facesContext, "#{exportarExcelBean}", ExportarExcelBean.class);
            if (exportarExcelBean != null) {
                generarExcel(exportarExcelBean.getModeloExcel());
            }
        } catch (Exception ex) {
            Logger.getLogger(ExportExcelServlet.class.getName()).log(Level.SEVERE, null, ex);
            Util.mostrarError(ex);
        }
    }

    /**
     * Método encargado de realizar la transformación del modelo {@link com.siss.web.modelo.ModeloExcel ModeloExcel}
     * y pasar los parámetros a el servlet {@link com.siss.web.servlet.RecursoServlet RecursoServlet} que se encarga
     * de la generación del documento excel.
     * @param excel Modelo con la data necesaria para generar un documento excel.
     */
    private void generarExcel(ModeloExcel excel) throws Exception {
        ByteArrayOutputStream outputXml = new ByteArrayOutputStream();
        outputXml = jaxbExcel(excel);
        InputStream inputXml = new ByteArrayInputStream(outputXml.toByteArray());
        outputXml.close();
        String path_xsl = getPathXsl(excel.getTitulo());
        this.setMyXml(inputXml);
        this.setUrlXsl(path_xsl);
        this.setNombreArchivo(excel.getTitulo().concat(".xls"));
    }

    /**
     * Método que obtiene el nombre de la región a partir del identificador de ésta.
     * @param regionId identificador de la región.
     * @param itRegion Iterator con la lista de las regiones.
     * @return Nombre completo de la región.
     */
    public static String getDescripcionRegion(Integer regionId, Iterator<Region> itRegion) throws Exception {
        String strRegion = "";
        while (itRegion.hasNext()) {
            Region region = itRegion.next();
            if (region.getRegionId().equals(regionId)) {
                strRegion = region.getRegion();
                break;

            }
        }
        return strRegion;
    }

    /**
     * Método encargado de obtener el path del servidor donde se encuentra el archivo xsl
     * necesario para la generación del excel.
     * @param reporte Tipo de reporte.
     * @return Ruta del servidor del archivo xsl necesario.
     */
    private String getPathXsl(String reporte) throws Exception {
        String path_xsl = "";
        if (reporte.equals(FiscalizadorEIBean.REPORTE_INDUSTRIA)) {
            path_xsl = "/xsl/FizcalizadorIndustriasExportExcel.xsl";
        } else if (reporte.equals(CumplimientoGeneralBean.REPORTE_CUMPLIMIENTO)) {
            path_xsl = "/xsl/ReporteCumplimientoExportExcel.xsl";
        } else if (reporte.equals(ParametrosBean.REPORTE_PARAMETROS)) {
            path_xsl = "/xsl/ReporteParametroExportExcel.xsl";
        } else if (reporte.equals(CronogramaBean.REPORTE_CRONOGRAMA)) {
            path_xsl = "/xsl/FizcalizadorIndustriasCronogramaExcel.xsl";
        }

        path_xsl = UtileriaWebBean.getContextoUrl(path_xsl).toString();
        return path_xsl;
    }

    /**
     * Método encargado de realizar la transformación el modelo lógico a una representación en formato xml,
     * necesaria para la generación del excel.
     * @param certificado Modelo con la data del excel a generar.
     * @return Puntero de memoria con la data del modelo en representación de xml.
     * @throws Exception
     */
    public static ByteArrayOutputStream jaxbExcel(ModeloExcel certificado) throws Exception {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        JAXBContext contexto = JAXBContext.newInstance(ModeloExcel.class);
        Marshaller marshaller = contexto.createMarshaller();
        marshaller.marshal(certificado, out);
        return out;
    }
}
