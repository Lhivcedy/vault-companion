package org.rj.vc.utils;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;

public interface GrowlMsg {

    default void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, summary, detail));
    }

}
