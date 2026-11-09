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

        Document documento = new Document();

        PdfWriter.getInstance(documento, new FileOutputStream("programadores.pdf"));

        documento.open();

        documento.add(new Paragraph("Listado de programadores"));
        documento.add(new Paragraph("\n\n\n"));

        PdfPTable tabla = new PdfPTable(3);

        Font negrita = new Font(Font.HELVETICA, 12, Font.BOLD);

        tabla.addCell(new PdfPCell(new Phrase("Usuario", negrita)));
        tabla.addCell(new PdfPCell(new Phrase("Genero", negrita)));
        tabla.addCell(new PdfPCell(new Phrase("Lenguaje", negrita)));

        for (Programadores p : lista) {

            tabla.addCell(p.getNombre());
            tabla.addCell(p.getGenero());
            tabla.addCell(p.getLenguaje());

        }

        documento.add(tabla);

        documento.close();
    }
}

