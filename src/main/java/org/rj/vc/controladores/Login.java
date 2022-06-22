package org.rj.vc.controladores;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.rj.vc.dto.UsuarioDTO;
import org.rj.vc.entidades.Usuario;
import org.rj.vc.services.interfaces.UsuarioService;
import org.rj.vc.utils.Encriptar;

import java.io.IOException;
import java.io.Serializable;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

import static jakarta.faces.application.FacesMessage.SEVERITY_INFO;
import static jakarta.faces.application.FacesMessage.Severity;

@SuppressWarnings("ALL")
@SessionScoped
@Named
@Getter
@Setter
public class Login implements Serializable {

    private static final long serialVersionUID = 69L;

    Logger log = LogManager.getRootLogger();

    @Inject
    private UsuarioService usuarioService;

    private ResourceBundle bundle;

    @Inject
    private FacesContext facesContext;

    private UsuarioDTO usuarioDto;

    @PostConstruct
    public void init() {
        this.usuarioDto = new UsuarioDTO();
        this.usuarioDto.setLogeado(false);
    }

    public void validarLogin() {
        this.cargarBundle();
        Optional<Usuario> usuarioOptional = usuarioService.porNombreUsuario(this.usuarioDto.getNombreUsuario());
        if (usuarioOptional.isPresent()) {
            String password = Encriptar.encriptarPass(usuarioDto.getContrasena(), usuarioOptional.get().getSalt());
            if (password.equals(usuarioOptional.get().getContrasena())) {
                this.usuarioDto = new UsuarioDTO(usuarioOptional.get());
                this.usuarioDto.setLogeado(true);
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", this.usuarioDto);
                addMessage(SEVERITY_INFO,
                        bundle.getString("app.texto.login.inicio.sesion"),
                        String.format(bundle.getString("app.texto.login.inicio.sesion.detalle"),
                                usuarioDto.getNombreUsuario()));
                redireccionar("index.jsf");
            } else {
                addMessage(SEVERITY_INFO,
                        bundle.getString("app.texto.login.inicio.error"),
                        bundle.getString("app.texto.login.inicio.error.detalle"));
                redireccionar("login.jsf");
            }
        } else {
            addMessage(SEVERITY_INFO,
                    bundle.getString("app.texto.login.inicio.error"),
                    bundle.getString("app.texto.login.inicio.error.detalle"));
            redireccionar("login.jsf");
        }
    }

    public void cerrarSesion() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        this.usuarioDto.setLogeado(false);
        redireccionar("index.jsf");
    }

    private void redireccionar(String destino) {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(destino);
        } catch (IOException e) {
            log.info(e.toString());
        }
    }

    private void addMessage(Severity severity, String mensaje, String detalle) {
        this.facesContext.addMessage(null, new FacesMessage(severity, mensaje, detalle));
    }

    private void cargarBundle() {
        Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
        this.bundle = ResourceBundle.getBundle("/i18n", locale);
    }

}
