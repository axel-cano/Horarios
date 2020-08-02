package org.orzhov.Archivos;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

public class LecturaExcel {
    private ArrayList<ArrayList<String>> filas = new ArrayList<>();

    public LecturaExcel(String ruta) {
        try {
            FileInputStream file = new FileInputStream(new File(ruta));
            Workbook workbook = new XSSFWorkbook(file);
            Sheet sheet = workbook.getSheetAt(0);

            for (Row hilera : sheet) {
                ArrayList<String> fila = new ArrayList<>();

                for (Cell columna : hilera) {
                    fila.add(columna.getStringCellValue());
                }
                filas.add(fila);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Hubo un error al abrir el archivo");
        }
    }

    public ArrayList<ArrayList<String>> getFilas() {
        return filas;
    }
}
