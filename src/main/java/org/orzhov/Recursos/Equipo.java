package org.orzhov.Recursos;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Equipo {
    private SimpleIntegerProperty idEquipo;
    private SimpleIntegerProperty idCategoria;
    private SimpleStringProperty nombre;
    private SimpleStringProperty descripcion;

    public Equipo(int idEquipo, int idCategoria, String nombre, String descripcion) {
        this.idEquipo = new SimpleIntegerProperty(idEquipo);
        this.idCategoria = new SimpleIntegerProperty(idCategoria);
        this.nombre = new SimpleStringProperty(nombre);
        this.descripcion = new SimpleStringProperty(descripcion);
    }

    public int getIdEquipo() {
        return idEquipo.get();
    }

    public int getIdCategoria() {
        return idCategoria.get();
    }

    public String getNombre() {
        return nombre.get();
    }

    public String getDescripcion() {
        return descripcion.get();
    }
}
