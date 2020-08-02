package org.orzhov.Excepciones;

public class SQLiteInvalido extends Throwable {
    public SQLiteInvalido(String mensaje) {
        System.out.println("Base de datos en SQLite inaccesible");
    }
}
