package org.rj.vc.repositorio;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import org.rj.vc.entidades.Usuario;
import org.rj.vc.repositorio.interfaces.UsuarioRepository;

import java.util.List;

@SuppressWarnings("ALL")
@RequestScoped
public class UsuarioRepositoryImpl implements UsuarioRepository {

    @Inject
    private EntityManager em;

    @Override
    public List<Usuario> listar() {
        return em.createQuery("SELECT u FROM Usuario u LEFT OUTER JOIN FETCH u.rol", Usuario.class).getResultList();
    }

    @Override
    public Usuario porId(Long id) {
        return em.createQuery("SELECT u FROM Usuario u LEFT OUTER JOIN FETCH u.rol WHERE u.idUsuario = :idUsuario", Usuario.class)
                .setParameter("idUsuario", id)
                .getSingleResult();
    }

    @Override
    public Usuario guardar(Usuario usuario) {
        if (usuario.getIdUsuario() != null && usuario.getIdUsuario() > 0) {
            return em.merge(usuario);
        } else {
            em.persist(usuario);
            return usuario;
        }
    }

    @Override
    public Boolean eliminar(Long id) {
        Usuario u = this.porId(id);
        em.remove(em.merge(u));
        return true;
    }

    @Override
    public Usuario porNombreUsuario(String nombreUsuario) {
        return em.createQuery("SELECT u FROM Usuario u LEFT OUTER JOIN FETCH u.rol WHERE u.nombreUsuario = :nombreUsuario", Usuario.class)
                .setParameter("nombreUsuario", nombreUsuario.toUpperCase())
                .getSingleResult();
    }
}
