package org.rj.vc.controladores;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.rj.vc.utils.GrowlMsg;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static jakarta.faces.application.FacesMessage.SEVERITY_ERROR;
import static jakarta.faces.application.FacesMessage.SEVERITY_INFO;

@SuppressWarnings("ALL")
@SessionScoped
@Named
@Getter
@Setter
public class Lenguaje implements Serializable, GrowlMsg {

    private static final long serialVersionUID = 69L;

    @Setter(AccessLevel.NONE)
    private Locale locale;


    @Setter(AccessLevel.NONE)
    private Map<String, String> lenguajes;

    @PostConstruct
    public void init() {
        this.locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
        lenguajes = new HashMap<>();
        lenguajes.put("Español", "es");
        lenguajes.put("Ingles", "en");
    }

    public void seleccionarLenguaje(String lang) {
        lenguajes.values().forEach(v -> {
            if (v.equals(lang)) {
                this.locale = new Locale(lang);
                FacesContext.getCurrentInstance().getViewRoot().setLocale(this.getLocale());
                if ("es".equals(lang)) {
                    addMessage(SEVERITY_INFO, "Cambio de Idioma", "Se ha establecido el idioma 'español'");
                } else if ("en".equals(lang)) {
                    addMessage(SEVERITY_INFO, "Language Change", "Language set to 'english'");
                } else {
                    addMessage(SEVERITY_ERROR, "No se detectó el idioma seleccionado", "No sé pudo cambiar el idioma");
                }
            }
        });
    }

}
