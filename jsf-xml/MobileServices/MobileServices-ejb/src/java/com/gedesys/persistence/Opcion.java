package com.gedesys.persistence;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author econtreras
 */
@Entity
@Table(name = "TB_OPCIONES")
@NamedQueries({
    @NamedQuery(name = "Opcion.findAll", query = "SELECT o FROM Opcion o")
})
public class Opcion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator="seqOpcion")
    @SequenceGenerator(name="seqOpcion", sequenceName="SQ_OPCIONES", allocationSize = 1)
    @Column(name = "OPCION_ID")
    private Short opcionId;
    @Basic(optional = false)
    @Column(name = "OPCION")
    private String opcion;
    @Column(name = "CREACION")
    @Temporal(TemporalType.DATE)
    private Date creacion;
    @Column(name = "CREADOR")
    private String creador;
    @Column(name = "MODIFICACION")
    @Temporal(TemporalType.DATE)
    private Date modificacion;
    @Column(name = "MODIFICADOR")
    private String modificador;

    public Opcion() {
    }

    public Opcion(Short opcionId) {
        this.opcionId = opcionId;
    }

    public Opcion(Short opcionId, String opcion) {
        this.opcionId = opcionId;
        this.opcion = opcion;
    }

    public Short getOpcionId() {
        return opcionId;
    }

    public void setOpcionId(Short opcionId) {
        this.opcionId = opcionId;
    }

    public String getOpcion() {
        return opcion;
    }

    public void setOpcion(String opcion) {
        this.opcion = opcion;
    }

    public Date getCreacion() {
        return creacion;
    }

    public void setCreacion(Date creacion) {
        this.creacion = creacion;
    }

    public String getCreador() {
        return creador;
    }

    public void setCreador(String creador) {
        this.creador = creador;
    }

    public Date getModificacion() {
        return modificacion;
    }

    public void setModificacion(Date modificacion) {
        this.modificacion = modificacion;
    }

    public String getModificador() {
        return modificador;
    }

    public void setModificador(String modificador) {
        this.modificador = modificador;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (opcionId != null ? opcionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Opcion)) {
            return false;
        }
        Opcion other = (Opcion) object;
        if ((this.opcionId == null && other.opcionId != null) || (this.opcionId != null && !this.opcionId.equals(other.opcionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gedesys.persistence.Opcion[opcionId=" + opcionId + "]";
    }

}
