package org.rj.vc.entidades;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Date;

@SuppressWarnings("ALL")
@Embeddable
@Getter
@Setter
public class Auditoria {

    @Column(name = "creado", columnDefinition = "DATETIME", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date creado;

    @Column(name = "actualizado", columnDefinition = "DATETIME", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date actualizado;


    @PrePersist
    private void crearTimestamp() {
        long tiempo = System.currentTimeMillis();
        this.setActualizado(new Timestamp(tiempo));
        this.setCreado(new Timestamp(tiempo));
    }

    @PreUpdate
    private void actualizarTimestamp() {
        this.setActualizado(new Timestamp(System.currentTimeMillis()));
    }

}
