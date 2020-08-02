package org.orzhov.Recursos;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Grupo {
    private SimpleStringProperty clave;
    private SimpleBooleanProperty turno;

    public Grupo(String clave, boolean turno) {
        this.clave = new SimpleStringProperty(clave);
        this.turno = new SimpleBooleanProperty(turno);
    }

    public String getClave() {
        return clave.get();
    }

    public boolean getTurno() {
        return turno.get();
    }
}
