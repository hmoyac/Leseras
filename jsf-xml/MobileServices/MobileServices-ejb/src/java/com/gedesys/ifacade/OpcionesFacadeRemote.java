package com.gedesys.ifacade;

import com.gedesys.persistence.Opcion;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author econtreras
 */
@Remote
public interface OpcionesFacadeRemote {

    Opcion createOpcion(String opcion, String usuario);
    Opcion updateOpcion(Opcion opcion);
    void deleteOpcion(short opcionId);
    Opcion getOpcion(short opcionId);
    List<Opcion> getAllOpciones();

}
