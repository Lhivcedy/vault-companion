package org.rj.vc.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.rj.vc.entidades.Usuario;

import java.util.Date;

@SuppressWarnings("ALL")
@Getter
@Setter
@ToString
public class UsuarioDTO {

    private String nombreUsuario;

    private String contrasena;

    private Long idRol;

    private String correo;

    private String nombre;

    private String apellido;

    private Boolean logeado;

    private Date creado;

    private Date actualizado;

    public UsuarioDTO() {
    }

    public UsuarioDTO(Usuario usuario) {
        this.setNombreUsuario(usuario.getNombreUsuario());
        this.setNombre(usuario.getNombre());
        this.setApellido(usuario.getApellido());
        this.setCorreo(usuario.getCorreo());
        this.setIdRol(usuario.getRol().getIdRol());
        this.setCreado(usuario.getAuditoria().getCreado());
        this.setActualizado(usuario.getAuditoria().getActualizado());
    }
}
