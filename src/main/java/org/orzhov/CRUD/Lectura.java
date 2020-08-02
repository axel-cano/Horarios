package org.orzhov.CRUD;

import javafx.collections.ObservableList;

import javax.swing.table.TableColumn;
import javax.swing.text.TableView;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

public class Lectura {

    public static void leerColumnas(Connection conexion, String nombreTabla) {

        try {
            PreparedStatement preparacion;
            ResultSet resultado;

            String sentencia = "SELECT * FROM `" + nombreTabla + "`;";
            preparacion = conexion.prepareStatement(sentencia);
            resultado = preparacion.executeQuery();

            while (resultado.next()) {
                ResultSetMetaData metadata = resultado.getMetaData();
                int numeroColumna = metadata.getColumnCount();

                for (int i = 1; i <= numeroColumna; i++) {
                    System.out.println(metadata.getColumnName(i).toUpperCase() + ": " + resultado.getString(metadata.getColumnName(i)));
                }
                System.out.println("\n__________________________\n");
            }
        } catch (Exception ex) {
            System.out.println("Revisa el estado de la conexión");
        }
    }

}
