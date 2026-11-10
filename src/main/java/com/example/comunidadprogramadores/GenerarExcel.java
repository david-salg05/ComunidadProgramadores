
package com.example.comunidadprogramadores;

import Entidades.Programadores;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class GenerarExcel {

    public static void crearExcel(ArrayList<Programadores> lista) throws IOException {

        //Creo el excel
        FileOutputStream salida = new FileOutputStream("programadores.xlsx");
        Workbook libro = new XSSFWorkbook();

        //Creo la hoja con el nombre
        Sheet hoja = libro.createSheet("Programadores");

        // Creo la cabecera
        Row cabecera = hoja.createRow(0);

        cabecera.createCell(0).setCellValue("Usuario");
        cabecera.createCell(1).setCellValue("Genero");
        cabecera.createCell(2).setCellValue("Lenguaje");

        // Recorro la lista y creo una fila por cada programador
        for (int i = 0; i < lista.size(); i++) {

            Row fila = hoja.createRow(i + 1);

            fila.createCell(0).setCellValue(lista.get(i).getNombre());
            fila.createCell(1).setCellValue(lista.get(i).getGenero());
            fila.createCell(2).setCellValue(lista.get(i).getLenguaje());

        }

        //Guardo los datos introsucidos
        libro.write(salida);

        //Cierro el libro y el excel
        libro.close();
        salida.close();
    }
}

