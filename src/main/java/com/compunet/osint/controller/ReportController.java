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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/v1/reportes")
public class ReportController {

    @Autowired
    private MockDeHashedService mockService;

    @Autowired
    private ScoreService scoreService;

    @Autowired
    private PdfGenerator pdfGenerator;

    // Carga la interfaz visual para el usuario 
    @GetMapping("/inicio")
    public String mostrarInicio() {
        return "index"; 
    }

    // Procesa la lógica y entrega el reporte en PDF 
    @GetMapping("/descargar")
    public ResponseEntity<byte[]> descargarReporte(@RequestParam String dominio) {
        // Datos basados en el ejemplo del documento formal
        int score = scoreService.calcularScoreTotal(18, 3, 15, 1);
        
        ExposicionDTO dto = new ExposicionDTO();
        dto.setDominio(dominio);
        dto.setScoreFinal(score);
        dto.setNivelRiesgo(scoreService.obtenerNivelRiesgo(score));
        dto.setTotalCuentasExpuestas(18);
        dto.setTotalFiltraciones(3);

        byte[] pdfBytes = pdfGenerator.generarReporteExposicion(dto);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "Reporte_" + dominio + ".pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .body(pdfBytes);
    }
}