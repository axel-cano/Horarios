package org.orzhov.CRUD;

import javafx.collections.ObservableList;
import org.orzhov.Recursos.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;

public class Alta {

    public static void cargarAulas(Connection conexion, ObservableList<Aula> aulas) {
        try {
            PreparedStatement preparacion;

            for (Aula aula : aulas) {
                String sentencia = "INSERT INTO `aulas` (`id_aula`, `nombre`, `tipo`, `capacidad`, `descripcion`, `ubicacion`) VALUES ('" + aula.getClave() + "', '" + aula.getNombre() + "', '" + aula.getTipo() + "', " + aula.getCapacidad() + ", '" + aula.getDescripcion() + "', '" + aula.getUbicacion() + "');";
                preparacion = conexion.prepareStatement(sentencia);
                preparacion.executeUpdate();

                System.out.println("Se agrego el aula " + aula.getClave());
            }
        } catch (SQLIntegrityConstraintViolationException ex) {
            System.out.println("Se encontraron llaves duplicadas: " + ex.getMessage() + " y no se pudo completar el proceso");
        } catch (Exception ex) {
            System.out.println("Revisa los datos ingresados");
        }
    }

    public static void cargarCarreras(Connection conexion, ObservableList<Carrera> carreras) {
        try {
            PreparedStatement preparacion;

            for (Carrera carrera : carreras) {
                String sentencia = "INSERT INTO `carrera` (`idcarrera`, `nombre_carrera`) VALUES (" + carrera.getClave() + ", '" + carrera.getNombre() + "');";
                preparacion = conexion.prepareStatement(sentencia);
                preparacion.executeUpdate();

                System.out.println("Se agrego la carrera " + carrera.getClave());
            }
        } catch (SQLIntegrityConstraintViolationException ex) {
            System.out.println("Se encontraron llaves duplicadas: " + ex.getMessage() + " y no se pudo completar el proceso");
        } catch (Exception ex) {
            System.out.println("Revisa los datos ingresados");
        }
    }


    public static void cargarCategorias(Connection conexion, ObservableList<Categoria> categorias) {
        try {
            PreparedStatement preparacion;

            for (Categoria categoria : categorias) {
                String sentencia = "INSERT INTO `categorias_equipo` (`id_categoria`, `nombre`, `descripcion`) VALUES (" + categoria.getClave() + ", '" + categoria.getNombre() + "', '" + categoria.getDescripcion() + "');";
                preparacion = conexion.prepareStatement(sentencia);
                preparacion.executeUpdate();

                System.out.println("Se agrego la categoria " + categoria.getClave());
            }
        } catch (SQLIntegrityConstraintViolationException ex) {
            System.out.println("Se encontraron llaves duplicadas: " + ex.getMessage() + " y no se pudo completar el proceso");
        } catch (Exception ex) {
            System.out.println("Revisa los datos ingresados");
        }
    }


    public static void cargarEquipos(Connection conexion, ObservableList<Equipo> equipos) {
        try {
            PreparedStatement preparacion;

            for (Equipo equipo : equipos) {
                String sentencia = "INSERT INTO `equipo` (`id_equipo`, `id_categoria`, `nombre`, `descripcion`) VALUES (" + equipo.getIdEquipo() + ", " + equipo.getIdCategoria() + ", '" + equipo.getNombre() + "', '" + equipo.getDescripcion() + "');";
                preparacion = conexion.prepareStatement(sentencia);
                preparacion.executeUpdate();

                System.out.println("Se agrego el equipo " + equipo.getIdEquipo());
            }
        } catch (SQLIntegrityConstraintViolationException ex) {
            System.out.println("Se encontraron llaves duplicadas: " + ex.getMessage() + " y no se pudo completar el proceso");
        } catch (Exception ex) {
            System.out.println("Revisa los datos ingresados");
        }
    }


    public static void cargarGrupos(Connection conexion, ObservableList<Grupo> grupos) {
        try {
            PreparedStatement preparacion;

            for (Grupo grupo : grupos) {
                String sentencia = "INSERT INTO `grupos` (`clv_grupo`, `turno`) VALUES ('" + grupo.getClave() + "', " + grupo.getTurno() + ")";
                preparacion = conexion.prepareStatement(sentencia);
                preparacion.executeUpdate();

                System.out.println("Se agrego el grupo " + grupo.getClave());
            }
        } catch (SQLIntegrityConstraintViolationException ex) {
            System.out.println("Se encontraron llaves duplicadas: " + ex.getMessage() + " y no se pudo completar el proceso");
        } catch (Exception ex) {
            System.out.println("Revisa los datos ingresados");
        }
    }


    public static void cargarMaterias(Connection conexion, ObservableList<Materia> materias) {
        try {
            PreparedStatement preparacion;

            for (Materia materia : materias) {
                String sentencia = "INSERT INTO `materias` (`clv_materia`, `nombre_materia`, `creditos`, `cuatrimestre`, `posicion`, `clv_plan`, `horas_x_semana`, `tipo_materia`) VALUES ('" + materia.getClave() + "', '" + materia.getNombre() + "', " + materia.getCreditos() + ", " + materia.getCuatrimestre() + ", " + materia.getPosicion() + ", '" + materia.getClavePlan() + "', " + materia.getHorasSemana() + ", '" + materia.getTipoMateria() + "');";
                preparacion = conexion.prepareStatement(sentencia);
                preparacion.executeUpdate();

                System.out.println("Se agrego la materia " + materia.getClave());
            }
        } catch (SQLIntegrityConstraintViolationException ex) {
            System.out.println("Se encontraron llaves duplicadas: " + ex.getMessage() + " y no se pudo completar el proceso");
        } catch (Exception ex) {
            System.out.println("Revisa los datos ingresados");
        }
    }


    public static void cargarPlanEstudio(Connection conexion, ObservableList<PlanEstudio> planEstudios) {
        try {
            PreparedStatement preparacion;

            for (PlanEstudio planEstudio : planEstudios) {
                String sentencia = "INSERT INTO `plan_estudios` (`clv_plan`, `nombre_plan`, `nivel`, `idcarrera`) VALUES ('" + planEstudio.getClave() + "', '" + planEstudio.getNombre() + "', '" + planEstudio.getNivel() + "', " + planEstudio.getId() + ");";
                preparacion = conexion.prepareStatement(sentencia);
                preparacion.executeUpdate();

                System.out.println("Se agrego el plan de estudios " + planEstudio.getClave());
            }
        } catch (SQLIntegrityConstraintViolationException ex) {
            System.out.println("Se encontraron llaves duplicadas: " + ex.getMessage() + " y no se pudo completar el proceso");
        } catch (Exception ex) {
            System.out.println("Revisa los datos ingresados");
        }
    }


    public static void cargarUsuarios(Connection conexion, ObservableList<Usuario> usuarios) {
        try {
            PreparedStatement preparacion;

            for (Usuario usuario : usuarios) {
                String sentencia = "INSERT INTO `usuarios` (`clv_usuario`, `idcarrera`, `nombre_usuario`, `nivel_ads`, `contrato`) VALUES ('" + usuario.getClave() + "', " + usuario.getIdCarrera() + ", '" + usuario.getNombre() + "', '" + usuario.getNivel() + "', '" + usuario.getContrato() + "');";
                preparacion = conexion.prepareStatement(sentencia);
                preparacion.executeUpdate();

                System.out.println("Se agrego el usuario " + usuario.getClave());
            }
        } catch (SQLIntegrityConstraintViolationException ex) {
            System.out.println("Se encontraron llaves duplicadas: " + ex.getMessage() + " y no se pudo completar el proceso");
        } catch (Exception ex) {
            System.out.println("Revisa los datos ingresados");
        }
    }
}
