package org.orzhov.Archivos;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.*;
import java.util.ArrayList;

public class LecturaJSON {
    private ArrayList<JSONObject> objectos = new ArrayList<>();

    public LecturaJSON(String ruta) {
        try {
            String linea;
            JSONObject objecto;
            FileReader lectorArchivo = new FileReader(ruta);
            BufferedReader lectorBuffer = new BufferedReader(lectorArchivo);

            while ((linea = lectorBuffer.readLine()) != null) {
                objecto = (JSONObject) new JSONParser().parse(linea);
                objectos.add(objecto);
            }

            lectorBuffer.close();
        } catch (Exception ex) {
            System.out.println("Hubo un error al abrir el archivo");
        }
    }


    public ArrayList<JSONObject> getObjectos() {
        return objectos;
    }
}
