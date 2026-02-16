package com.compunet.osint.controller;

import com.compunet.osint.model.DeHashedResponse;
import com.compunet.osint.model.ExposicionDTO;
import com.compunet.osint.service.ScoreService;
import com.compunet.osint.service.MockDeHashedService;
import com.compunet.osint.util.PdfGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/reportes")
public class ReportController {

    @Autowired
    private MockDeHashedService mockService;

    @Autowired
    private ScoreService scoreService;

    @Autowired
    private PdfGenerator pdfGenerator;

    /**
     * Endpoint para visualizar el análisis en formato JSON (Ideal para Postman).
     */
    @GetMapping("/simular")
    public ExposicionDTO simularAnalisis(@RequestParam String dominio) {
        // 1. Obtener datos del Mock (Simulando respuesta de DeHashed)
        DeHashedResponse data = mockService.getMockData(dominio);

        // 2. Ejecutar lógica del Score (Fórmula: A(C) + B(B) + D + R)
        // Simulamos los valores del ejemplo práctico: 18 cuentas, 3 brechas, Gravedad 15, Recencia 1 año.
        int scoreFinal = scoreService.calcularScoreTotal(18, 3, 15, 1);
        String nivel = scoreService.obtenerNivelRiesgo(scoreFinal);

        // 3. Mapear al DTO de salida
        ExposicionDTO dto = new ExposicionDTO();
        dto.setDominio(dominio);
        dto.setTotalCuentasExpuestas(data.getTotal());
        dto.setTotalFiltraciones(3); 
        dto.setScoreFinal(scoreFinal);
        dto.setNivelRiesgo(nivel);

        return dto;
    }

    /**
     * Endpoint para generar y descargar el reporte profesional en PDF.
     */
    @GetMapping("/descargar")
    public ResponseEntity<byte[]> descargarReporte(@RequestParam String dominio) {
        // 1. Preparamos los datos para el reporte
        int score = scoreService.calcularScoreTotal(18, 3, 15, 1);
        
        ExposicionDTO dto = new ExposicionDTO();
        dto.setDominio(dominio);
        dto.setScoreFinal(score);
        dto.setNivelRiesgo(scoreService.obtenerNivelRiesgo(score));
        dto.setTotalCuentasExpuestas(18);
        dto.setTotalFiltraciones(3);

        // 2. Generamos los bytes del PDF invocando al componente utilitario
        byte[] pdfBytes = pdfGenerator.generarReporteExposicion(dto);

        // 3. Configuramos los Headers para que el navegador/Postman lo reconozca como PDF
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "Reporte_Exposicion_" + dominio + ".pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .body(pdfBytes);
    }
}