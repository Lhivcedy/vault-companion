package org.rj.vc.services.interfaces;

import jakarta.ejb.Local;
import org.rj.vc.dto.UsuarioDTO;
import org.rj.vc.entidades.Usuario;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("ALL")
@Local
public interface UsuarioService {

    List<Usuario> listar();

    Optional<Usuario> porId(Long id);

    Optional<Usuario> porNombreUsuario(String nombreUsuario);

    void guardar(Usuario usuario);

    void eliminar(Long id);

    List<UsuarioDTO> listarDto();

    Optional<UsuarioDTO> porIdDto(Long id);

}
