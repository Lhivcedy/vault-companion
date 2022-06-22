package org.rj.vc.repositorio.interfaces;

import org.rj.vc.entidades.Usuario;

@SuppressWarnings("ALL")
public interface UsuarioRepository extends CrudRepository<Usuario> {

    Usuario porNombreUsuario(String nombreUsuario) throws Exception;

}
