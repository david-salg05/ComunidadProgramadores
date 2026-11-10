package com.example.comunidadprogramadores;

import Entidades.Programadores;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.util.ArrayList;

public class GenerarPDF {

    public GenerarPDF() {
    }

    public static void crearPDF(ArrayList<Programadores> lista) throws Exception {

        //Creo el documento y le doy el nombre
        Document documento = new Document();
        PdfWriter.getInstance(documento, new FileOutputStream("programadores.pdf"));

        //Abro el documento
        documento.open();

        //Escribo el titulo del documento
        documento.add(new Paragraph("Listado de programadores"));
        documento.add(new Paragraph("\n\n\n"));

        //Creo una tabla con las 3 columnas
        PdfPTable tabla = new PdfPTable(3);

        //Indico el tipo de letra
        Font negrita = new Font(Font.HELVETICA, 12, Font.BOLD);

        //Anhado las cabeceras de la tabla
        tabla.addCell(new PdfPCell(new Phrase("Usuario", negrita)));
        tabla.addCell(new PdfPCell(new Phrase("Genero", negrita)));
        tabla.addCell(new PdfPCell(new Phrase("Lenguaje", negrita)));

        //Bucle para añadir a la tabla cada programador
        for (Programadores p : lista) {

            tabla.addCell(p.getNombre());
            tabla.addCell(p.getGenero());
            tabla.addCell(p.getLenguaje());

        }

        //Anhado la tabla al documento
        documento.add(tabla);

        //Cierro el documento
        documento.close();
    }
}

