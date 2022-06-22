package org.rj.vc.entidades;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.rj.vc.utils.Encriptar;

import java.text.SimpleDateFormat;

@SuppressWarnings("ALL")
@Getter
@Setter
@Entity
@Table(name = "usuario")
@ToString
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SEQ_USUARIO")
    @Column(name = "id_usuario")
    private Long idUsuario;

    @JoinColumn(name = "id_rol", referencedColumnName = "id_rol")
    @ManyToOne(fetch = FetchType.LAZY, optional = false, targetEntity = Rol.class)
    private Rol rol;

    @Column(name = "nombre_usuario", length = 50, nullable = false, unique = true)
    private String nombreUsuario;

    @Column(name = "correo_usuario", length = 100, unique = true, nullable = false)
    private String correo;

    @Column(name = "nombre", length = 50, nullable = false)
    private String nombre;

    @Column(name = "apellido", length = 50, nullable = false)
    private String apellido;

    @Column(name = "contrasena", length = 300, nullable = false)
    private String contrasena;

    @Column(name = "sal", length = 50, nullable = false)
    private String salt;

    @Column(name = "estado", length = 50)
    private String estado;

    @Embedded
    private Auditoria auditoria = new Auditoria();

    @PrePersist
    private void generarPassword() {
        long tiempo = System.currentTimeMillis();
        this.setSalt(new SimpleDateFormat("ddmmddyyyyMMhhss").format(tiempo));
        this.setContrasena(Encriptar.encriptarPass(this.getContrasena(), this.getSalt()));
    }

}
