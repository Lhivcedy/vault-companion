package org.rj.vc.services;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import org.rj.vc.dto.UsuarioDTO;
import org.rj.vc.entidades.Usuario;
import org.rj.vc.repositorio.interfaces.UsuarioRepository;
import org.rj.vc.services.interfaces.UsuarioService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@SuppressWarnings("ALL")
@Stateless
public class UsuarioServiceImpl implements UsuarioService {

    @Inject
    private UsuarioRepository repository;

    @Override
    public List<Usuario> listar() {
        try {
            return repository.listar();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Usuario> porId(Long id) {
        try {
            return Optional.ofNullable(repository.porId(id));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Usuario> porNombreUsuario(String nombreUsuario) {
        try {
            return Optional.ofNullable(repository.porNombreUsuario(nombreUsuario));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public void guardar(Usuario usuario) {
        try {
            repository.guardar(usuario);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void eliminar(Long id) {
        try {
            repository.eliminar(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<UsuarioDTO> listarDto() {
        return this.listar().stream().map(user -> {
            UsuarioDTO userDto = new UsuarioDTO(user);
            return userDto;
        }).collect(Collectors.toList());
    }

    @Override
    public Optional<UsuarioDTO> porIdDto(Long id) {
        return Optional.ofNullable(new UsuarioDTO(this.porId(id).get()));
    }
}
