package com.siss.test.datos;

/**
 *
 * @author econtreras
 */
public class SgsEstadistica {

    private int id_estado;
    private int id_tramo;
    private int cantidad;

    public SgsEstadistica() {
    }

    public SgsEstadistica(int id_estado, int id_tramo, int cantidad) {
        this.id_estado = id_estado;
        this.id_tramo = id_tramo;
        this.cantidad = cantidad;
    }

    public int getId_estado() {
        return id_estado;
    }

    public void setId_estado(int id_estado) {
        this.id_estado = id_estado;
    }

    public int getId_tramo() {
        return id_tramo;
    }

    public void setId_tramo(int id_tramo) {
        this.id_tramo = id_tramo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

}
