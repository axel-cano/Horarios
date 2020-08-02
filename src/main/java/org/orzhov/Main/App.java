package org.orzhov.Main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import org.orzhov.Archivos.LecturaExcel;
import org.orzhov.Archivos.LecturaJSON;
import org.orzhov.Archivos.ObtenerRecursos;
import org.orzhov.CRUD.Actualizar;
import org.orzhov.CRUD.Alta;
import org.orzhov.CRUD.Borrar;
import org.orzhov.CRUD.Lectura;
import org.orzhov.Excepciones.ConfigFormatoInvalidoException;
import org.orzhov.Excepciones.GestorInvalidoException;
import org.orzhov.Gestores.MySQL;
import org.orzhov.Gestores.PostgresSQL;
import org.orzhov.Gestores.SQLite;

import java.io.File;
import java.sql.Connection;
import java.util.Scanner;


public class App extends Application {

    private static Connection conexion;
    private static String gestor;
    private static Config conf;
    private static int baseDeDatos;

    public static void main(String[] args) {

        //String rutaConfig = System.getProperty("configFile");
        String rutaConfig = "config.json";

        if (rutaConfig == null || rutaConfig.equals("")) {
            conf = new Config();

        } else {
            conf = new Config(rutaConfig);
        }

        conexion = seleccionarGestor(conf.getHosts(), conf.getNombre(), conf.getUsuario(), conf.getClave());
        //controlador(conexion, conf);
        launch(args);
    }

    public static Connection getConexion() {
        return conexion;
    }

    public static String getGestor() {
        return gestor;
    }

    public static Config getConf() {
        return conf;
    }

    public static Integer getBaseDeDatos(){
        return baseDeDatos;
    }

    private static Connection seleccionarGestor(String hosts, String nombre, String usuario, String clave) {
        if (!hosts.equals("")) {
            String[] hostsLista = hosts.split(" ");

            try {
                PostgresSQL bd = new PostgresSQL(hostsLista, nombre, usuario, clave);
                gestor = "Se a conectado mediante PostgresSQL.";
                baseDeDatos = 2;
                return bd.getConexion();
            } catch (GestorInvalidoException ex) {
                SQLite bd = new SQLite();
                bd.crearTablas(bd.getConexion());
                gestor = "Se a conectado mediante una base de datos temporal.";
                baseDeDatos = 3;
                return bd.getConexion();
            }
        } else {
            try {
                MySQL bd = new MySQL(nombre, usuario, clave);
                gestor = "Se a conectado mediante MySQL.";
                baseDeDatos = 1;
                return bd.getConexion();
            } catch (GestorInvalidoException ex) {
                SQLite bd = new SQLite();
                bd.crearTablas(bd.getConexion());
                gestor = "Se a conectado mediante una base de datos temporal.";
                baseDeDatos = 3;
                return bd.getConexion();
            }
        }
    }

    private static String obtenerId(String accion) {
        Scanner scannerId = new Scanner(System.in);
        System.out.println("Introduce el elemento a " + accion + " ");
        return scannerId.nextLine();
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage = FXMLLoader.load(getClass().getResource("/fxml/crud.fxml"));
        stage.show();
    }
}
