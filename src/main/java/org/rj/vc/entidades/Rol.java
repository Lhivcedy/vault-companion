package org.rj.vc.entidades;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("ALL")
@Getter
@Setter
@Entity
@Table(name = "rol")
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SEQ_ROL")
    @Column(name = "id_rol")
    private Long idRol;

    @Column(name = "nombre_rol", length = 50, nullable = false)
    private String nombreRol;

}
