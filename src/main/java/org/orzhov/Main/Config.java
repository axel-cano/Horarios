package org.orzhov.Main;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.orzhov.Archivos.LecturaJSON;
import org.orzhov.Excepciones.ConfigFormatoInvalidoException;
import org.orzhov.Excepciones.FormatoNoSoportado;

import java.io.File;
import java.util.Scanner;

public class Config {
    private String ruta;
    private static String recursos;
    private String hosts;
    private String nombre;
    private String usuario;
    private String clave;


    public Config(String archivo) {
        try {
            LecturaJSON json = new LecturaJSON(archivo);
            JSONObject objecto = json.getObjectos().get(0);

            archivo = objecto.get("ruta").toString();
            if (archivo.endsWith("json") || archivo.endsWith("xlsx")) {
                this.ruta = archivo;
            } else {
                throw new ConfigFormatoInvalidoException("Lo introducido no es un archivo valido");
            }
            //setRecursos();

            this.hosts = objecto.get("hosts").toString();
            this.nombre = objecto.get("nombre").toString();
            this.usuario = objecto.get("usuario").toString();
            this.clave = objecto.get("clave").toString();

        } catch (Exception | ConfigFormatoInvalidoException ex) {
            setRuta();
            setRecursos();
            this.hosts = setDato("hosts");
            this.nombre = setDato("nombre bd");
            this.usuario = setDato("usuario");
            this.clave = setDato("clave");
        }
    }


    public Config() {
        setRuta();
        setRecursos();
        this.hosts = setDato("hosts");
        this.nombre = setDato("nombre bd");
        this.usuario = setDato("usuario");
        this.clave = setDato("clave");
    }

    public String getRuta() {
        return ruta;
    }

    public String getRecursos() {
        return recursos;
    }

    public String getHosts() {
        return hosts;
    }

    public String getNombre() {
        return nombre;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getClave() {
        return clave;
    }

    public static void setRecurso(String recurso) {
        recursos = recurso;
    }

    public void setRuta() {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("Introduce la ruta del archivo: ");

            String dato = sc.nextLine();
            File archivo = new File(dato);

            if (archivo.isFile() && (dato.endsWith("json") || dato.endsWith("xlsx"))) {
                this.ruta = dato;
            } else {
                throw new ConfigFormatoInvalidoException("Lo introducido no es un archivo valido");
            }
        } catch (Exception | ConfigFormatoInvalidoException ex) {
            setRuta();
        }
    }

    public void setRecursos() {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("1.- Aulas\n2.- Carrera\n3.- Categorias\n4.- Equipo\n5.- Grupos\n6.- Materias\n7.- PlanEstudios\n8.- Usuarios");

            int opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    this.recursos = "aulas";
                    break;
                case 2:
                    this.recursos = "carrera";
                    break;
                case 3:
                    this.recursos = "categorias_equipo";
                    break;
                case 4:
                    this.recursos = "equipo";
                    break;
                case 5:
                    this.recursos = "grupos";
                    break;
                case 6:
                    this.recursos = "materias";
                    break;
                case 7:
                    this.recursos = "plan_estudios";
                    break;
                case 8:
                    this.recursos = "usuarios";
                    break;
                default:
                    throw new ConfigFormatoInvalidoException("Lo introducido no es un recurso valido");
            }
        } catch (ConfigFormatoInvalidoException | Exception ex) {
            setRecursos();
        }
    }

    public String setDato(String dato) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce el " + dato + ": ");

        return sc.nextLine();
    }

}
