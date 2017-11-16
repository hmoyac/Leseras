/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siss.test.mbean;

import com.siss.dto.pti.InfoArchivoProceso;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author econtreras
 */
public class pruebaServlet extends HttpServlet {

    public void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        doPost(request, response);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        //Recogemos los datos de la peticion
        ObjectInputStream input = new ObjectInputStream(req.getInputStream());

        Object dataInput = null;
        try {
            dataInput = input.readObject();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(pruebaServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        InfoArchivoProceso inputData = (InfoArchivoProceso) dataInput;

        System.out.println(inputData.getCodigoProceso());

        FacesContext facesContext = FacesUtil.getFacesContext(req, res);

        PopupBean popup = (PopupBean) facesContext.getExternalContext()
            .getSessionMap().get("popup");

        inputData.setNombreArchivo("Archivo Prueba");

        Object obj = new InfoArchivoProceso(inputData.getCodigoProceso(), inputData.getCodigoArchivo());
        ((InfoArchivoProceso)obj).setNombreArchivo("Archivo Prueba");

        //Object obj = new String("valor desde managed bean");

        //Especificamos que la respuesta será un objeto serializado
        res.setContentType("java-internal/" + obj.getClass().getName());

        ObjectOutputStream output = new ObjectOutputStream(res.getOutputStream());

        output.writeObject(obj);

        output.flush();
        output.close();

        facesContext = null;
        
    }
}
