package com.gedesys.bo;

import com.gedesys.persistence.Opcion;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author econtreras
 */
@Local
public interface OpcionLocal {

    Opcion createOpcion(String opcion, String usuario);
    Opcion updateOpcion(Opcion opcion);
    void deleteOpcion(short opcionId);
    Opcion getOpcion(short opcionId);
    List<Opcion> getAllOpciones();
    
}
