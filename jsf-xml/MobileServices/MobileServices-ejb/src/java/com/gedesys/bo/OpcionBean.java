package com.gedesys.bo;

import com.gedesys.persistence.Opcion;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author econtreras
 */
@Stateless
public class OpcionBean implements OpcionLocal {

    @PersistenceContext(unitName="MobileServices-ejbPU")
    protected EntityManager em;

    public Opcion createOpcion(String opcion, String usuario) {
        Opcion newOpcion = null;
        newOpcion = new Opcion();
        newOpcion.setOpcion(opcion);
        newOpcion.setCreador(usuario);
        em.persist(newOpcion);
        return newOpcion;
    }

    public Opcion updateOpcion(Opcion opcion) {
        em.merge(opcion);
        return opcion;
    }

    public void deleteOpcion(short opcionId) {
        Opcion opcion = em.find(Opcion.class, opcionId);
        em.remove(opcion);
    }

    public Opcion getOpcion(short opcionId) {
        Opcion opcion = em.find(Opcion.class, opcionId);
        return opcion;
    }

    public List<Opcion> getAllOpciones() {
        Query query = em.createNamedQuery("Opcion.findAll");
        List<Opcion> opciones = query.getResultList();
        return opciones;
    }

}
