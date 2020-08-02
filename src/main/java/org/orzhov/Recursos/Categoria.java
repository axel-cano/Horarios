package org.orzhov.Recursos;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Categoria {
    private SimpleIntegerProperty clave;
    private SimpleStringProperty nombre;
    private SimpleStringProperty descripcion;

    public Categoria(int clave, String nombre, String descripcion) {
        this.clave = new SimpleIntegerProperty(clave);
        this.nombre = new SimpleStringProperty(nombre);
        this.descripcion = new SimpleStringProperty(descripcion);
    }

    public int getClave() {
        return clave.get();
    }

    public String getNombre() {
        return nombre.get();
    }

    public String getDescripcion() {
        return descripcion.get();
    }
}
