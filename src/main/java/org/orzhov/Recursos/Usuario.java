package org.orzhov.Recursos;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Usuario {
    private SimpleStringProperty clave;
    private SimpleIntegerProperty idCarrera;
    private SimpleStringProperty nombre;
    private SimpleStringProperty nivel;
    private SimpleStringProperty contrato;


    public Usuario(String clave, int idCarrera, String nombre, String nivel, String contrato) {
        this.clave = new SimpleStringProperty(clave);
        this.idCarrera = new SimpleIntegerProperty(idCarrera);
        this.nombre = new SimpleStringProperty(nombre);
        this.nivel = new SimpleStringProperty(nivel);
        this.contrato = new SimpleStringProperty(contrato);
    }

    public String getClave() {
        return clave.get();
    }

    public int getIdCarrera() {
        return idCarrera.get();
    }

    public String getNombre() {
        return nombre.get();
    }

    public String getNivel() {
        return nivel.get();
    }

    public String getContrato() {
        return contrato.get();
    }
}
