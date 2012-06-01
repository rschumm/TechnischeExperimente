package ch.lepeit.stundenabrechnung.controller;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class PopupController implements Serializable {
    private static final long serialVersionUID = 1L;

    private boolean show = false;

    public String hide() {
        this.show = false;

        return null;
    }

    public boolean isShow() {
        return show;
    }

    public String show() {
        this.show = true;

        return null;
    }
}
