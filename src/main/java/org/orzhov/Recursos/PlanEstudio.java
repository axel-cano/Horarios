package org.orzhov.Recursos;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class PlanEstudio {
    private SimpleStringProperty clave;
    private SimpleStringProperty nombre;
    private SimpleStringProperty nivel;
    private SimpleIntegerProperty id;

    public PlanEstudio(String clave, String nombre, String nivel, int id) {
        this.clave = new SimpleStringProperty(clave);
        this.nombre = new SimpleStringProperty(nombre);
        this.nivel = new SimpleStringProperty(nivel);
        this.id = new SimpleIntegerProperty(id);
    }

    public String getClave() {
        return clave.get();
    }

    public String getNombre() {
        return nombre.get();
    }

    public String getNivel() {
        return nivel.get();
    }

    public int getId() {
        return id.get();
    }
}
