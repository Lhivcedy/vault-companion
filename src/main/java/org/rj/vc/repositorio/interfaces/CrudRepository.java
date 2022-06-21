package org.rj.vc.repositorio.interfaces;

import java.util.List;

@SuppressWarnings("ALL")
public interface CrudRepository<T> {

    List<T> listar() throws Exception;

    T porId(Long id) throws Exception;

    T guardar(T t) throws Exception;

    Boolean eliminar(Long id) throws Exception;

}
