package org.orzhov.Gestores;

import org.orzhov.Controladores.CRUDController;
import org.orzhov.Excepciones.GestorInvalidoException;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;

public class MySQL {
    private Connection conexion;

    public MySQL(String nombre, String usuario, String clave) throws GestorInvalidoException {
        try {
            this.conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + nombre + "?serverTimezone=UTC", usuario, clave);
            System.out.println("(MySQL) Conexión creada exitosamente");
            //CRUDController crud = new CRUDController();
            //crud.gestor("(MySQL) Conexión creada exitosamente");
            if (new File("db.sql").isFile()) {
                SQLite.cargarDatos(getConexion(), new SQLite().getConexion());
            }
        } catch (Exception ex) {
            throw new GestorInvalidoException("(MySQL) Error al crear la conexión");
        }
    }

    public Connection getConexion() {
        return conexion;
    }
}
