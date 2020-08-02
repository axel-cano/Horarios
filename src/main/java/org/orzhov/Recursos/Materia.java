package org.orzhov.Recursos;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Materia {
    private SimpleStringProperty clave;
    private SimpleStringProperty nombre;
    private SimpleIntegerProperty creditos;
    private SimpleIntegerProperty cuatrimestre;
    private SimpleIntegerProperty posicion;
    private SimpleStringProperty clavePlan;
    private SimpleIntegerProperty horasSemana;
    private SimpleStringProperty tipoMateria;


    public Materia(String clave, String nombre, int creditos, int cuatrimestre, int posicion, String clavePlan, int horasSemana, String tipoMateria) {
        this.clave = new SimpleStringProperty(clave);
        this.nombre = new SimpleStringProperty(nombre);
        this.creditos = new SimpleIntegerProperty(creditos);
        this.cuatrimestre = new SimpleIntegerProperty(cuatrimestre);
        this.posicion = new SimpleIntegerProperty(posicion);
        this.clavePlan = new SimpleStringProperty(clavePlan);
        this.horasSemana = new SimpleIntegerProperty(horasSemana);
        this.tipoMateria = new SimpleStringProperty(tipoMateria);
    }

    public String getClave() {
        return clave.get();
    }

    public String getNombre() {
        return nombre.get();
    }

    public int getCreditos() {
        return creditos.get();
    }

    public int getCuatrimestre() {
        return cuatrimestre.get();
    }

    public int getPosicion() {
        return posicion.get();
    }

    public String getClavePlan() {
        return clavePlan.get();
    }

    public int getHorasSemana() {
        return horasSemana.get();
    }

    public String getTipoMateria() {
        return tipoMateria.get();
    }
}
