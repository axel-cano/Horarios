package org.orzhov.Recursos;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Aula {
    private SimpleStringProperty clave;
    private SimpleStringProperty nombre;
    private SimpleStringProperty tipo;
    private SimpleIntegerProperty capacidad;
    private SimpleStringProperty descripcion;
    private SimpleStringProperty ubicacion;

    public Aula(String clave, String nombre, String tipo, int capacidad, String descripcion, String ubicacion) {
        this.clave = new SimpleStringProperty(clave);
        this.nombre = new SimpleStringProperty(nombre);
        this.tipo = new SimpleStringProperty(tipo);
        this.capacidad = new SimpleIntegerProperty(capacidad);
        this.descripcion = new SimpleStringProperty(descripcion);
        this.ubicacion = new SimpleStringProperty(ubicacion);
    }

    public String getClave() {
        return clave.get();
    }

    public String getNombre() {
        return nombre.get();
    }

    public String getTipo() {
        return tipo.get();
    }

    public int getCapacidad() {
        return capacidad.get();
    }

    public String getDescripcion() {
        return descripcion.get();
    }

    public String getUbicacion() {
        return ubicacion.get();
    }
}
