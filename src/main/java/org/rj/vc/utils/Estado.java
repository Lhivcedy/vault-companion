package org.rj.vc.utils;

@SuppressWarnings("ALL")
public enum Estado {
    ACTIVO("ACTIVO"), INACTIVO("INACTIVO");
    public final String codigoEstado;

    Estado(String codigoEstado) {
        this.codigoEstado = codigoEstado;
    }

}
