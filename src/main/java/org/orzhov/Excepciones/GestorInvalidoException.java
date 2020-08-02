package org.orzhov.Excepciones;

public class GestorInvalidoException extends Throwable {
    public GestorInvalidoException(String mensaje) {
        System.out.println(mensaje);
    }
}
