package ch.lepeit.stundenabrechnung.controller;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Observable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 * Paging für die Auswertung
 * 
 * Benachrichtigt Observer, wenn sich der anzuzeigende Monat ändert.
 * 
 * @author Sven Tschui C910511
 * 
 */
@Named
@SessionScoped
public class AuswertungPagingController extends Observable implements Serializable {
    private static final long serialVersionUID = 20120524L;

    private Date monat;

    public Date getMonat() {
        return monat;
    }

    @PostConstruct
    public void init() {
        this.monat = new Date();
    }

    public String letzterMonat() {
        Calendar c = new GregorianCalendar();

        c.setTime(monat);

        c.add(Calendar.MONTH, -1);

        this.monat = c.getTime();

        this.setChanged();
        this.notifyObservers();

        return null;
    }

    public String naechsterMonat() {
        Calendar c = new GregorianCalendar();

        c.setTime(monat);

        c.add(Calendar.MONTH, 1);

        this.monat = c.getTime();

        this.setChanged();
        this.notifyObservers();

        return null;
    }

}
