package com.compunet.osint.util;

import com.compunet.osint.model.ExposicionDTO;
import com.compunet.osint.service.ScoreService;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.util.List;

@Component
public class PdfGenerator {

    @Autowired
    private ScoreService scoreService;

    public byte[] generarReporteExposicion(ExposicionDTO datos) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Document document = new Document(PageSize.A4, 36, 36, 54, 36);
        
        try {
            PdfWriter.getInstance(document, out);
            document.open();

            // 1. Encabezado con Estilo Corporativo
            PdfPTable headerTable = new PdfPTable(1);
            headerTable.setWidthPercentage(100);
            PdfPCell cell = new PdfPCell(new Phrase("REPORTE DE EXPOSICIÓN DE SEGURIDAD", 
                FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, Color.WHITE)));
            cell.setBackgroundColor(new Color(0, 71, 171)); // Azul CompuNet
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setPadding(10);
            cell.setBorder(Rectangle.NO_BORDER);
            headerTable.addCell(cell);
            document.add(headerTable);

            // 2. Información General
            document.add(new Paragraph(" "));
            document.add(new Paragraph("Información del Análisis", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12)));
            document.add(new Paragraph("Dominio: " + datos.getDominio()));
            document.add(new Paragraph("Fecha: 15 de febrero de 2026")); // Fecha emisión [cite: 5]
            document.add(new Paragraph("Tipo de Análisis: OSINT (Fuentes Abiertas)")); //[cite: 4, 9]
            document.add(new Paragraph(" "));

            // 3. Cuadro de Score (Resumen Ejecutivo)
            PdfPTable scoreTable = new PdfPTable(2);
            scoreTable.setWidthPercentage(100);
            
            // Celda Score
            PdfPCell scoreCell = new PdfPCell(new Phrase("SCORE DE EXPOSICIÓN: " + datos.getScoreFinal() + "/100", 
                FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14)));
            scoreCell.setPadding(10);
            scoreTable.addCell(scoreCell);

            // Celda Nivel
            PdfPCell nivelCell = new PdfPCell(new Phrase("NIVEL: " + datos.getNivelRiesgo(), 
                FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14, getColorRiesgo(datos.getScoreFinal()))));
            nivelCell.setPadding(10);
            scoreTable.addCell(nivelCell);
            
            document.add(scoreTable);
            document.add(new Paragraph(" "));

            // 4. Recomendaciones con Viñetas
            document.add(new Paragraph("RECOMENDACIONES DE SEGURIDAD", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12)));
            document.add(new Paragraph("Acciones correctivas sugeridas para mitigar el riesgo detectado:")); //[cite: 61]
            document.add(new Paragraph(" "));

            List<String> recomendaciones = scoreService.obtenerRecomendaciones(datos.getScoreFinal());
            for (String rec : recomendaciones) {
                document.add(new Paragraph("• " + rec, FontFactory.getFont(FontFactory.HELVETICA, 10)));
            }

            // 5. Nota de Limitación y Ética (Pie de página)
            document.add(new Paragraph(" "));
            document.add(new Paragraph("-----------------------------------------------------------------------"));
            Paragraph footer = new Paragraph("Este reporte tiene un enfoque estrictamente defensivo y educativo. " +
                "Solo utiliza información pública (OSINT) y no constituye una prueba de penetración.", 
                FontFactory.getFont(FontFactory.HELVETICA_OBLIQUE, 8)); //[cite: 6, 11, 77]
            document.add(footer);

            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out.toByteArray();
    }

    // Método auxiliar para colores de riesgo según el Score 
    private Color getColorRiesgo(int score) {
        if (score >= 81) return Color.RED;      // Crítico
        if (score >= 61) return Color.ORANGE;   // Alto
        if (score >= 41) return Color.YELLOW;   // Medio
        return new Color(0, 128, 0);            // Verde (Bajo/Muy Bajo)
    }
}