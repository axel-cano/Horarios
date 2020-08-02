package org.orzhov.Recursos;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Carrera {
    private SimpleIntegerProperty clave;
    private SimpleStringProperty nombre;

    public Carrera(int clave, String nombre) {
        this.clave = new SimpleIntegerProperty(clave);
        this.nombre = new SimpleStringProperty(nombre);
    }

    public int getClave() {
        return clave.get();
    }

    public String getNombre() {
        return nombre.get();
    }
}
