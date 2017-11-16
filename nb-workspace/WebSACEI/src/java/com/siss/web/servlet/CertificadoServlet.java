package com.siss.web.servlet;

import com.siss.dto.riles.Certificado;
import com.siss.web.FacesUtil;
import com.siss.web.Util;
import com.siss.web.UtilCrypto;
import com.siss.web.bean.CertificadosBean;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author hmoya
 *
 * Servlet encargado de la generación de los certifiados correspondientes a las diferentes empresas.
 * Para ello, el servlet necesita una serie de parámetros como:</br>
 * <li>Integer paramEmpresaID</li>
 * <li>Integer paramPlantaID</li>
 * <li>Integer paramPuntoID</li>
 * <li>String paramPeriodo</li>
 * <li>Integer paramTipoControl</li>
 * <li>Integer paramTipoMuestreo</li>
 * <li>Integer paramEstadoEnvio</li>
 * <li>Integer paramEstadoDescarga</li>
 * <li>String paramTipoNoDescarga</li>
 * <li>String tipoCertificado</li>
 * </br>
 * Con estos parámetros el servlet es capaz de generar un archivo pdf, con la información solicitada de la empresa.
 */
public class CertificadoServlet extends RecursoServlet {

    /**
     * Implementación del método abstracto customizeParameters(HttpServletRequest request, HttpServletResponse response) 
     * heredado desde el {@link com.siss.web.servlet.RecursoServlet RecursoServlet}.
     * En el cual se inicializan los elementos necesarios para la generación de los certificados.
     * @param request HttpServletRequest del servlet.
     * @param response HttpServletResponse del servlet.
     */
    @Override
    public void customizeParameters(HttpServletRequest request, HttpServletResponse response) {
        generarCertificado(request, response);
    }

    /**
     * Método encargado de obtener los parámetros pasados al servlet, obtener el modelo representativo del modelo
     * {@link com.siss.dto.amb.Certificado Certificado}, distinguir el tipo de certificado, el nombre y el archivo xsl
     * con el cual se realizará la transformación a un archivo pdf.
     * @param request HttpServletRequest del servlet.
     * @param response HttpServletResponse del servlet.
     * @see Certificado
     * @see HttpServletRequest
     * @see HttpServletResponse
     * @see FacesUtil#getFacesContext(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     * @see ResourceBundle
     * @see CertificadosBean
     * @see FacesUtil#getManagedBeanExpression(javax.faces.context.FacesContext, java.lang.String, java.lang.Class)
     * @see CertificadosBean#cargarNombre(int, java.lang.String, int, java.lang.String, int)
     * @see CertificadosBean#cargarCertificado(java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer)
     * @see CertificadosBean#jaxb(com.siss.dto.amb.Certificado)
     * @see CertificadosBean#getXsl(java.lang.String, java.lang.Integer, int) 
     */
    private void generarCertificado(HttpServletRequest request, HttpServletResponse response) {
        Integer paramEmpresaID = null;
        Integer paramPlantaID = null;
        Integer paramPuntoID = null;
        String paramPeriodo = null;
        Integer paramTipoControl = null;
        Integer paramTipoMuestreo = null;
        Integer paramEstadoEnvio = null;
        Integer paramEstadoDescarga = null;
        String tipoCertificado = null;
        String paramOpcion = null;


        Certificado certificado = null;


        String paramEncripted = "";
        Map mapaparam = request.getParameterMap();
        Iterator it = mapaparam.keySet().iterator();
        while (it.hasNext()) {
            String enc = String.valueOf(it.next());
            paramEncripted = UtilCrypto.getInstance().decrypt(new BigInteger(enc).toByteArray());
        }

        String[] spliter = paramEncripted.split("&");
        for (int i=0; i<spliter.length; i++) {
            String[] parametro = spliter[i].split("=");
            if (parametro.length > 0) {
                String name = parametro[0];
                if (name.compareToIgnoreCase("paramOpcion") == 0) {
                    paramOpcion = parametro[1];
                } else if (name.compareToIgnoreCase("paramEmpresaID") == 0) {
                    paramEmpresaID = Integer.parseInt(parametro[1]);
                } else if (name.compareToIgnoreCase("paramPlantaID") == 0) {
                    paramPlantaID = Integer.parseInt(parametro[1]);
                } else if (name.compareToIgnoreCase("paramPuntoID") == 0) {
                    paramPuntoID = Integer.parseInt(parametro[1]);
                } else if (name.compareToIgnoreCase("paramPeriodo") == 0) {
                    paramPeriodo = parametro[1];
                } else if (name.compareToIgnoreCase("paramTipoControl") == 0) {
                    paramTipoControl = Integer.parseInt(parametro[1]);
                } else if (name.compareToIgnoreCase("paramTipoMuestreo") == 0) {
                    paramTipoMuestreo = Integer.parseInt(parametro[1]);
                } else if (name.compareToIgnoreCase("paramEstadoEnvio") == 0) {
                    paramEstadoEnvio = Integer.parseInt(parametro[1]);
                } else if (name.compareToIgnoreCase("paramEstadoDescarga") == 0) {
                    paramEstadoDescarga = Integer.parseInt(parametro[1]);
                } else if (name.compareToIgnoreCase("tipoCertificado") == 0) {
                    tipoCertificado = parametro[1];
                }
            }
        }
        
        if (paramOpcion.compareToIgnoreCase("GENERAR") == 0) {
            ByteArrayOutputStream outputXml = new ByteArrayOutputStream();
            try {
                FacesContext ctx = FacesUtil.getFacesContext(request, response);
                String bundleCumplimiento = com.siss.web.Util.getString(ctx, "Cumplimiento");
                String bundleAutoControl = com.siss.web.Util.getString(ctx, "AutoControl");
                String bundleRemuestreo = com.siss.web.Util.getString(ctx, "Remuestreo");
                String bundleControlDirecto = com.siss.web.Util.getString(ctx, "ControlDirecto");
                String bundleNoDescarga = com.siss.web.Util.getString(ctx, "NoDescarga");
                String mime = "application/pdf";

                if (paramPeriodo != null && paramPeriodo.length() > 6) {
                    paramPeriodo = paramPeriodo.replaceAll(" - ", "");
                }
                CertificadosBean certificadoBean = (CertificadosBean) FacesUtil.getManagedBeanExpression(ctx, "#{certifBean}", CertificadosBean.class);
                if (tipoCertificado.compareToIgnoreCase(bundleNoDescarga) == 0) {
                    super.setMime(mime);
                    certificado = certificadoBean.cargarCertificado(
                            paramEmpresaID, paramPlantaID, paramPuntoID,
                            paramPeriodo, paramTipoControl, paramTipoMuestreo,
                            paramEstadoEnvio, paramEstadoDescarga);
                    if (certificado != null) {
                        super.setNombreArchivo(certificadoBean.cargarNombre(paramEstadoDescarga.intValue(), certificado.getTipo(), paramTipoMuestreo.intValue(), paramPeriodo, 0));
                        outputXml = certificadoBean.jaxb(certificado);
                        InputStream inputXml = new ByteArrayInputStream(outputXml.toByteArray());
                        outputXml.close();
                        String xsl = certificadoBean.getXsl(certificado.getTipo(), paramEstadoDescarga, 0);
                        super.setMyXml(inputXml);
                        super.setUrlXsl(xsl);
                    } else {
                        return;
                    }
                } else if (tipoCertificado.compareToIgnoreCase(bundleCumplimiento) == 0) {
                    super.setMime(mime);
                    certificado = certificadoBean.cargarCertificadoCumplimiento(
                            paramEmpresaID, paramPlantaID, paramPuntoID, paramPeriodo);
                    if (certificado != null) {
                        super.setNombreArchivo(certificadoBean.cargarNombre(paramEstadoDescarga.intValue(), certificado.getTipo(), paramTipoMuestreo.intValue(), paramPeriodo, 1));
                        outputXml = certificadoBean.jaxb(certificado);
                        InputStream inputXml = new ByteArrayInputStream(outputXml.toByteArray());
                        outputXml.close();
                        String xsl = certificadoBean.getXsl(certificado.getTipo(), paramEstadoDescarga, 1);
                        super.setMyXml(inputXml);
                        super.setUrlXsl(xsl);
                    } else {
                        return;
                    }
                } else if (tipoCertificado.compareToIgnoreCase(bundleControlDirecto) == 0) {
                    super.setMime(mime);
                    certificado = certificadoBean.cargarCertificado(
                            paramEmpresaID, paramPlantaID, paramPuntoID,
                            paramPeriodo, paramTipoControl, paramTipoMuestreo,
                            paramEstadoEnvio, paramEstadoDescarga);
                    if (certificado != null) {
                        super.setNombreArchivo(certificadoBean.cargarNombre(paramEstadoDescarga.intValue(),
                                certificado.getTipo(), paramTipoMuestreo.intValue(), paramPeriodo, 2));
                        outputXml = certificadoBean.jaxb(certificado);
                        InputStream inputXml = new ByteArrayInputStream(outputXml.toByteArray());
                        outputXml.close();
                        String xsl = certificadoBean.getXsl(certificado.getTipo(), paramEstadoDescarga, 2);
                        super.setMyXml(inputXml);
                        super.setUrlXsl(xsl);
                    } else {
                        return;
                    }
                } else if (tipoCertificado.compareToIgnoreCase(bundleAutoControl) == 0 || tipoCertificado.compareToIgnoreCase(bundleRemuestreo) == 0) {
                    super.setMime(mime);
                    certificado = certificadoBean.cargarCertificado(
                            paramEmpresaID, paramPlantaID, paramPuntoID,
                            paramPeriodo, paramTipoControl, paramTipoMuestreo,
                            paramEstadoEnvio, paramEstadoDescarga);
                    if (certificado != null) {
                        super.setNombreArchivo(certificadoBean.cargarNombre(paramEstadoDescarga.intValue(),
                                certificado.getTipo(), paramTipoMuestreo.intValue(), paramPeriodo, 0));
                        outputXml = certificadoBean.jaxb(certificado);
                        InputStream inputXml = new ByteArrayInputStream(outputXml.toByteArray());
                        outputXml.close();
                        String xsl = certificadoBean.getXsl(certificado.getTipo(), paramEstadoDescarga, 0);
                        super.setMyXml(inputXml);
                        super.setUrlXsl(xsl);
                    } else {
                        return;
                    }
                }
                if (certificado == null && super.getMime().compareTo(mime) == 0) {
                    super.setNombreArchivo("certificado.pdf");
                }
            } catch (Exception ex) {
                Logger.getLogger(CertificadoServlet.class.getName()).log(Level.SEVERE, null, ex);
                Util.mostrarError(ex);
                return;
            }
        }
    }
}
