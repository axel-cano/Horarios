package org.orzhov.Archivos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.json.simple.JSONObject;
import org.orzhov.Recursos.*;

import java.util.ArrayList;

public class ObtenerRecursos {

    public static ArrayList<Aula> aulass = new ArrayList<Aula>();
    public static ArrayList<Materia> materiass = new ArrayList<Materia>();
    public static ArrayList<PlanEstudio> planEstudioss = new ArrayList<PlanEstudio>();
    public static ArrayList<Usuario> usuarioss = new ArrayList<Usuario>();
    public static ArrayList<Carrera> carrerass = new ArrayList<Carrera>();
    public static ArrayList<Categoria> categoriass = new ArrayList<Categoria>();
    public static ArrayList<Equipo> equiposs = new ArrayList<Equipo>();
    public static ArrayList<Grupo> gruposs = new ArrayList<Grupo>();

    public ObtenerRecursos(LecturaExcel archivo) {
        ArrayList<ArrayList<String>> filas = archivo.getFilas();

        for (ArrayList<String> fila : filas) {

            switch (fila.get(0).toLowerCase()) {
                case "aula":
                    aulass.add(new Aula(fila.get(1), fila.get(2), fila.get(3), Integer.parseInt(fila.get(4)), fila.get(5), fila.get(6)));
                    break;
                case "carrera":
                    carrerass.add(new Carrera(Integer.parseInt(fila.get(1)), fila.get(2)));
                    break;
                case "categoria":
                    categoriass.add(new Categoria(Integer.parseInt(fila.get(1)), fila.get(2), fila.get(3)));
                    break;
                case "equipo":
                    equiposs.add(new Equipo(Integer.parseInt(fila.get(1)), Integer.parseInt(fila.get(2)), fila.get(3), fila.get(4)));
                    break;
                case "grupo":
                    gruposs.add(new Grupo(fila.get(1), Boolean.parseBoolean(fila.get(2))));
                    break;
                case "materia":
                    materiass.add(new Materia(fila.get(1), fila.get(2), Integer.parseInt(fila.get(3)), Integer.parseInt(fila.get(4)), Integer.parseInt(fila.get(5)), fila.get(6), Integer.parseInt(fila.get(7)), fila.get(8)));
                    break;
                case "planestudio":
                    planEstudioss.add(new PlanEstudio(fila.get(1), fila.get(2), fila.get(3), Integer.parseInt(fila.get(4))));
                    break;
                case "usuarios":
                    usuarioss.add(new Usuario(fila.get(1), Integer.parseInt(fila.get(2)), fila.get(3), fila.get(4), fila.get(5)));
            }
        }
    }


    public ObtenerRecursos(LecturaJSON archivo) {
        ArrayList<JSONObject> objectos = archivo.getObjectos();
        int i = 0;
        for (JSONObject objecto : objectos) {
            i++;

            try {
                switch (objecto.get("recurso").toString().toLowerCase()) {
                    case "aulas":
                        aulass.add(new Aula(objecto.get("clave").toString(), objecto.get("nombre").toString(), objecto.get("tipo").toString(), Integer.parseInt(objecto.get("capacidad").toString()), objecto.get("descripcion").toString(), objecto.get("ubicacion").toString()));
                        break;
                    case "carrera":
                        carrerass.add(new Carrera(Integer.parseInt(objecto.get("clave").toString()), objecto.get("nombre").toString()));
                        break;
                    case "categorias_equipo":
                        categoriass.add(new Categoria(Integer.parseInt(objecto.get("clave").toString()), objecto.get("nombre").toString(), objecto.get("descripcion").toString()));
                        break;
                    case "equipo":
                        equiposs.add(new Equipo(Integer.parseInt(objecto.get("idEquipo").toString()), Integer.parseInt(objecto.get("idCategoria").toString()), objecto.get("nombre").toString(), objecto.get("descripcion").toString()));
                        break;
                    case "grupos":
                        gruposs.add(new Grupo(objecto.get("clave").toString(), Boolean.parseBoolean(objecto.get("turno").toString())));
                        break;
                    case "materia":
                        materiass.add(new Materia(objecto.get("clave").toString(), objecto.get("nombre").toString(), Integer.parseInt(objecto.get("creditos").toString()), Integer.parseInt(objecto.get("cuatrimestre").toString()), Integer.parseInt(objecto.get("posicion").toString()), objecto.get("clavePlan").toString(), Integer.parseInt(objecto.get("horasSemana").toString()), objecto.get("tipoMateria").toString()));
                        break;
                    case "plan_estudios":
                        planEstudioss.add(new PlanEstudio(objecto.get("clave").toString(), objecto.get("nombre").toString(), objecto.get("nivel").toString(), Integer.parseInt(objecto.get("id").toString())));
                        break;
                    case "usuarios":
                        usuarioss.add(new Usuario(objecto.get("clave").toString(), Integer.parseInt(objecto.get("idCarrera").toString()), objecto.get("nombre").toString(), objecto.get("nivel").toString(), objecto.get("contrato").toString()));
                }
            } catch (Exception ex) {
                System.out.println("No se pudo cargar el recurso de la línea " + i);
                continue;
            }

        }
    }

    public ArrayList<Aula> getAulass(){
        return aulass;
    }

    public ArrayList<Carrera> getCarrerass(){
        return carrerass;
    }

    public ArrayList<Categoria> getCategoriass(){
        return categoriass;
    }

    public ArrayList<Equipo> getEquiposs(){
        return equiposs;
    }

    public ArrayList<Grupo> getGruposs(){
        return gruposs;
    }

    public ArrayList<Materia> getMateriass(){
        return materiass;
    }

    public ArrayList<PlanEstudio> getPlanEstudioss(){
        return planEstudioss;
    }

    public ArrayList<Usuario> getUsuarioss(){
        return usuarioss;
    }
}
