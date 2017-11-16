package com.siss.web.model;

import com.siss.entity.riles.HorarioDescarga;

/**
 *
 * @author hmoya
 *
 * Modelo de datos referente a los horarios de descarga, {@link com.siss.entity.riles.HorarioDescarga HorarioDescarga}, de las empresas.
 * @see com.siss.entity.riles.HorarioDescarga
 */
public class HorarioDescargaIceModel extends HorarioDescarga {

    /**
     * Bandera boolean que indica se el modelo ha sido seleccionado en la tabla.
     */
    private boolean selected = false;
    /**
     * Boolean que indica si el modelo se puede modificar.
     */
    private boolean editar = false;

    /**
     * Retorna un boolean que indica si el modelo se puede modificar.
     * @return Boolean que indica si el modelo se puede modificar.
     */
    public boolean isEditar() {
        return editar;
    }

    /**
     * Setea un boolean que indica si el modelo se puede modificar.
     * @param editar Boolean que indica si el modelo se puede modificar.
     */
    public void setEditar(boolean editar) {
        this.editar = editar;
    }

    /**
     * Retorna booleano indicando si el modelo está seleccionado en la tabla.
     * @return booleano indicando si el modelo está seleccionado en la tabla.
     */
    public boolean isSelected() {
        return selected;
    }

    /**
     * Setea booleano indicando si el modelo está seleccionado en la tabla.
     * @param selected booleano indicando si el modelo está seleccionado en la tabla.
     */
    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    /**
     * Método que transforma un modelo {@link com.siss.entity.riles.HorarioDescarga HorarioDescarga} a un modelo propio,
     * en el cual el modelo se puede seleccionar y establecer un modo de edición.
     * @param horario Modelo {@link com.siss.entity.riles.HorarioDescarga HorarioDescarga} original.
     * @return Modelo propio con la data del modelo original más si el nodo está seleccionado o está en modo de edición.
     */
    public HorarioDescargaIceModel transforma(HorarioDescarga horario) {
        HorarioDescargaIceModel aux = new HorarioDescargaIceModel();
        aux.setHorario(horario.getHorario());
        aux.setHorarioDescargaPK(horario.getHorarioDescargaPK());
        aux.setInfoControl(horario.getInfoControl());
        aux.setMonitoreo(horario.getMonitoreo());
        aux.setSelected(false);
        aux.setEditar(false);
        return aux;
    }

}
