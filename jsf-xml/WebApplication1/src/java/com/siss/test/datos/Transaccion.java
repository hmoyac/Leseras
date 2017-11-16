package com.siss.test.datos;

/**
 *
 * @author econtreras
 */
public class Transaccion {

    private String id_entidad;
    private SgsEstadistica[] sgs_estadistica;

    public Transaccion() {
    }

    public Transaccion(String id_entidad, SgsEstadistica[] sgs_estadistica) {
        this.id_entidad = id_entidad;
        this.sgs_estadistica = sgs_estadistica;
    }

    public String getId_entidad() {
        return id_entidad;
    }

    public void setId_entidad(String id_entidad) {
        this.id_entidad = id_entidad;
    }

    public SgsEstadistica[] getSgs_estadistica() {
        return sgs_estadistica;
    }

    public void setSgs_estadistica(SgsEstadistica[] sgs_estadistica) {
        this.sgs_estadistica = sgs_estadistica;
    }

}
