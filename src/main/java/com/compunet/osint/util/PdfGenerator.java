package com.compunet.osint.util;

import com.compunet.osint.model.ExposicionDTO;
import com.compunet.osint.service.ScoreService;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.io.ByteArrayOutputStream;
import java.util.List;

@Component
public class PdfGenerator {

    @Autowired
    private ScoreService scoreService; // Inyectamos para obtener las recomendaciones [cite: 61]

    public byte[] generarReporteExposicion(ExposicionDTO datos) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Document document = new Document();
        
        try {
            PdfWriter.getInstance(document, out);
            document.open();

            // Título y Datos Generales [cite: 57]
            Font boldFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14);
            document.add(new Paragraph("REPORTE DE EXPOSICIÓN: " + datos.getDominio(), boldFont));
            document.add(new Paragraph("Nivel: " + datos.getNivelRiesgo()));
            document.add(new Paragraph("Score Final: " + datos.getScoreFinal() + "/100"));
            document.add(new Paragraph(" "));

            // Hallazgos [cite: 60]
            document.add(new Paragraph("HALLAZGOS RELEVANTES", boldFont));
            document.add(new Paragraph("Cuentas expuestas: " + datos.getTotalCuentasExpuestas()));
            document.add(new Paragraph("Filtraciones detectadas: " + datos.getTotalFiltraciones()));
            document.add(new Paragraph(" "));

            // Recomendaciones (Esta es la parte que causaba error en el controller) [cite: 61]
            document.add(new Paragraph("RECOMENDACIONES DE SEGURIDAD", boldFont));
            List<String> recomendaciones = scoreService.obtenerRecomendaciones(datos.getScoreFinal());
            for (String rec : recomendaciones) {
                document.add(new Paragraph("• " + rec));
            }

            // Nota de alcance [cite: 63]
            document.add(new Paragraph(" "));
            document.add(new Paragraph("NOTA: Este análisis es estrictamente OSINT y defensivo.", 
                         FontFactory.getFont(FontFactory.HELVETICA, 8)));

            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out.toByteArray();
    }
}