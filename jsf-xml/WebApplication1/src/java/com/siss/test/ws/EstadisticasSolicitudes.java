package com.siss.test.ws;

import com.siss.test.datos.SgsEstadistica;
import com.siss.test.datos.Transaccion;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 *
 * @author econtreras
 */
@WebService()
public class EstadisticasSolicitudes {

    @WebMethod(operationName = "operation")
    public Transaccion operation(@WebParam(name = "sgs_estadistica")
    String sgs_estadistica) {
        Transaccion transaccion = new Transaccion();
        //TODO write your implementation code here:
        if (sgs_estadistica.equals("AM011")) {
            transaccion.setId_entidad(sgs_estadistica);
            SgsEstadistica[] sgsEstadistica = new SgsEstadistica[2];
            sgsEstadistica[0] = new SgsEstadistica(1,1,25);
            sgsEstadistica[1] = new SgsEstadistica(1,1,27);
            transaccion.setSgs_estadistica(sgsEstadistica);
        } else {
            transaccion.setId_entidad("CODIGO DE SERVICIO NO CORRESPONDE");
        }
        return transaccion;
    }
}
