package org.rj.vc.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@SuppressWarnings("ALL")
@Getter
@Setter
@ToString
public class UsuarioDTO {

    @NotEmpty
    private String usuario;

}
