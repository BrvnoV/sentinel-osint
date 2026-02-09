package com.compunet.osint.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

// Esta clase se encargará de transformar los datos crudos obtenidos 
// por la API en un nivel de riesgo comprensible

// Se usará la anotación @Value de Spring para inyectar los 
// puntajes que definimos en el application.yml

@Service
public class ScoreService {

    // Inyección de valores desde application.yml (Tablas del encargo)
    @Value("${score.cuentas.rango-bajo}")
    private int scoreCuentasBajo; // 10 pts [cite: 40]
    
    @Value("${score.cuentas.rango-medio}")
    private int scoreCuentasMedio; // 25 pts [cite: 40]
    
    @Value("${score.cuentas.rango-alto}")
    private int scoreCuentasAlto; // 40 pts [cite: 40]

    @Value("${score.filtraciones.rango-bajo}")
    private int scoreFiltraBajo; // 10 pts [cite: 42]

    @Value("${score.recencia.reciente}")
    private int scoreRecenciaMax; // 10 pts [cite: 46]

    
     //Implementación de la fórmula: Score = A(C) + B(B) + D + R

    public int calcularScoreTotal(int cuentas, int filtraciones, int puntosGravedad, int antiguedadAnios) {
        int ac = calcularA_C(cuentas);
        int bb = calcularB_B(filtraciones);
        int d = puntosGravedad; // Valor D (0 a 20) [cite: 37, 44]
        int r = calcularR(antiguedadAnios);

        int total = ac + bb + d + r;
        return Math.min(total, 100); // El score máximo es 100 [cite: 31]
    }

    // Tabla A(C) - Cantidad de cuentas [cite: 40]
    private int calcularA_C(int c) {
        if (c > 20) return scoreCuentasAlto;
        if (c >= 6) return scoreCuentasMedio;
        if (c >= 1) return scoreCuentasBajo;
        return 0;
    }

    // Tabla B(B) - Filtraciones distintas [cite: 42]
    private int calcularB_B(int b) {
        if (b >= 4) return 30; // Rango alto [cite: 42]
        if (b >= 2) return 20; // Rango medio [cite: 42]
        if (b == 1) return scoreFiltraBajo;
        return 0;
    }

    // Tabla R - Recencia [cite: 46]
    private int calcularR(int anios) {
        if (anios < 2) return scoreRecenciaMax; // 10 pts [cite: 46]
        if (anios <= 5) return 5; // 5 pts [cite: 46]
        return 2; // Más de 5 años [cite: 46]
    }

    
    //Determina el nivel de exposición basado en el score final [cite: 50]
    
    public String obtenerNivelRiesgo(int score) {
        if (score >= 81) return "CRÍTICO - Acción inmediata"; // [cite: 50]
        if (score >= 61) return "ALTO - Acción prioritaria"; // [cite: 50]
        if (score >= 41) return "MEDIO - Atención requerida"; // [cite: 50]
        if (score >= 21) return "BAJO - Revisión periódica"; // [cite: 50]
        return "MUY BAJO - Monitoreo rutinario"; // [cite: 50]
    }
}