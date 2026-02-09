package com.compunet.osint.controller;


import com.compunet.osint.model.DeHashedResponse;
import com.compunet.osint.model.ExposicionDTO;
import com.compunet.osint.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.compunet.osint.service.MockDeHashedService;

@RestController
@RequestMapping("/api/v1/reportes")
public class ReportControlle
    @Autowired
    private MockDeHashedService mockService;

    @Autowired
    private ScoreService scoreService;

    @GetMapping("/simular")
    public ExposicionDTO simularAnalisis(@RequestParam String dominio) {
        // 1. Obtener datos falsos
        DeHashedResponse data = mockService.getMockData(dominio);

        // 2. Ejecutar lógica del Score (Usando valores del ejemplo [cite: 53])
        // Simulamos: 18 cuentas, 3 filtraciones, Gravedad 15 (Hash), Antigüedad 1 año
        int scoreFinal = scoreService.calcularScoreTotal(18, 3, 15, 1);
        String nivel = scoreService.obtenerNivelRiesgo(scoreFinal);

        // 3. Mapear al DTO de salida profesional
        ExposicionDTO dto = new ExposicionDTO();
        dto.setDominio(dominio);
        dto.setTotalCuentasExpuestas(data.getTotal());
        dto.setTotalFiltraciones(3); 
        dto.setScoreFinal(scoreFinal);
        dto.setNivelRiesgo(nivel);

        return dto;
    }
}