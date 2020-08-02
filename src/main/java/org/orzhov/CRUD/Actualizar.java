package org.orzhov.CRUD;

import javafx.collections.ObservableList;
import org.orzhov.Recursos.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

public class Actualizar {
    public static void actualizarAulas(Connection conexion, ObservableList<Aula> aulas, String id) {
        try {
            PreparedStatement preparacion;

            for (Aula aula : aulas) {
                String sentencia = "UPDATE `aulas` SET `nombre` = '" +  aula.getNombre() + "', `tipo` = '" + aula.getTipo() + "', `capacidad` = '" + aula.getCapacidad() + "', `descripcion` = '" + aula.getDescripcion() + "', `ubicacion` = '" + aula.getUbicacion() + "' WHERE id_aula = '" + id + "'";
                preparacion = conexion.prepareStatement(sentencia);
                preparacion.executeUpdate();

                System.out.println("Actualización completa");
            }
        } catch (Exception ex) {
            System.out.println("Revisa los datos ingresados");
        }
    }

    public static void actualizarCarreras(Connection conexion, ObservableList<Carrera> carreras, String id) {
        try {
            PreparedStatement preparacion;

            for (Carrera carrera : carreras) {
                String sentencia = "UPDATE `carrera` SET `nombre_carrera` = '" + carrera.getNombre() + "' WHERE idcarrera = " + Integer.parseInt(id) + "";
                preparacion = conexion.prepareStatement(sentencia);
                preparacion.executeUpdate();

                System.out.println("Actualización completa");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Revisa los datos ingresados");
        }
    }


    public static void actualizarCategorias(Connection conexion, ObservableList<Categoria> categorias, String id) {
        try {
            PreparedStatement preparacion;

            for (Categoria categoria : categorias) {
                String sentencia = "UPDATE `categorias_equipo` SET `nombre` = '" + categoria.getNombre() + "', `descripcion` = '" + categoria.getDescripcion() + "' WHERE id_categoria = " + Integer.parseInt(id) + ";";
                preparacion = conexion.prepareStatement(sentencia);
                preparacion.executeUpdate();

                System.out.println("Actualización completa");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Revisa los datos ingresados");
        }
    }


    public static void actualizarEquipos(Connection conexion, ObservableList<Equipo> equipos, String id) {
        try {
            PreparedStatement preparacion;

            for (Equipo equipo : equipos) {
                String sentencia = "UPDATE `equipo` SET `nombre` = '" + equipo.getNombre() + "', `id_categoria` = '" + equipo.getIdCategoria() + "', `nombre` = '" + equipo.getNombre() + "', `descripcion` = '" + equipo.getDescripcion() + "' WHERE id_equipo = " + Integer.parseInt(id) + ";";
                preparacion = conexion.prepareStatement(sentencia);
                preparacion.executeUpdate();

                System.out.println("Actualización completa");
            }
        } catch (Exception ex) {
            System.out.println("Revisa los datos ingresados");
        }
    }


    public static void actualizarGrupos(Connection conexion, ObservableList<Grupo> grupos, String id) {
        try {
            PreparedStatement preparacion;

            for (Grupo grupo : grupos) {
                String sentencia = "UPDATE `grupos` SET `turno` = '" + grupo.getTurno() + "' WHERE clv_grupo = " + Integer.parseInt(id) + ";";
                preparacion = conexion.prepareStatement(sentencia);
                preparacion.executeUpdate();

                System.out.println("Actualización completa");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Revisa los datos ingresados");
        }
    }


    public static void actualizarPlanEstudio(Connection conexion, ObservableList<PlanEstudio> planEstudios, String id) {
        try {
            PreparedStatement preparacion;

            for (PlanEstudio planEstudio : planEstudios) {
                String sentencia = "UPDATE `plan_estudios` SET `nombre_plan` = '" +  planEstudio.getNombre() + "', `nivel` = '" + planEstudio.getNivel() + "', `idcarrera` = '" + planEstudio.getClave()+ "' WHERE clv_plan = '" + id + "'";
                preparacion = conexion.prepareStatement(sentencia);
                preparacion.executeUpdate();

                System.out.println("Actualización completa");
            }
        } catch (Exception ex) {
            System.out.println("Revisa los datos ingresados");
        }
    }


    public static void actualizarUsuarios(Connection conexion, ObservableList<Usuario> usuarios, String id) {
        try {
            PreparedStatement preparacion;

            for (Usuario usuario : usuarios) {
                String sentencia = "UPDATE `usuarios` SET `idcarrera` = '" +  usuario.getIdCarrera() + "', `nombre_usuario` = '" + usuario.getNombre() + "', `nivel_ads` = '" + usuario.getNivel() + "', `contrato` = '" + usuario.getContrato() +"' WHERE clv_usuario = '" + id + "'";
                preparacion = conexion.prepareStatement(sentencia);
                preparacion.executeUpdate();

                System.out.println("Actualización completa");
            }
        } catch (Exception ex) {
            System.out.println("Revisa los datos ingresados");
        }
    }
}
