package com.gedesys.facade;

import com.gedesys.persistence.*;
import com.gedesys.ifacade.*;
import com.gedesys.bo.OpcionLocal;
import com.gedesys.persistence.Opcion;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

/**
 *
 * @author econtreras
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class OpcionesFacadeBean implements OpcionesFacadeRemote {

    @EJB
    private OpcionLocal opcionBO;

    public Opcion createOpcion(String opcion, String usuario) {
        return opcionBO.createOpcion(opcion, usuario);
    }

    public Opcion updateOpcion(Opcion opcion) {
        return opcionBO.updateOpcion(opcion);
    }

    public void deleteOpcion(short opcionId) {
        opcionBO.deleteOpcion(opcionId);
    }

    public Opcion getOpcion(short opcionId) {
        return opcionBO.getOpcion(opcionId);
    }

    public List<Opcion> getAllOpciones() {
        return opcionBO.getAllOpciones();
    }



}
