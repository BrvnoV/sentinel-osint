package com.compunet.osint.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;


// Servicio encargado de transformar los datos crudos obtenidos por la API 
// en un nivel de riesgo comprensible para el Reporte de Exposición[cite: 9, 15].
 
@Service
public class ScoreService {

    // Inyección de valores desde application.yml basándose en la Tabla A(C) [cite: 40]
    @Value("${score.cuentas.rango-bajo}")
    private int scoreCuentasBajo; // 10 pts [cite: 40]
    
    @Value("${score.cuentas.rango-medio}")
    private int scoreCuentasMedio; // 25 pts [cite: 40]
    
    @Value("${score.cuentas.rango-alto}")
    private int scoreCuentasAlto; // 40 pts [cite: 40]

    // Inyección de valores para la Tabla B(B) [cite: 42]
    @Value("${score.filtraciones.rango-bajo}")
    private int scoreFiltraBajo; // 10 pts [cite: 42]

    // Inyección de valores para la Tabla R [cite: 46]
    @Value("${score.recencia.reciente}")
    private int scoreRecenciaMax; // 10 pts [cite: 46]

    /**
     * Implementación de la fórmula: Score de Exposición = A(C) + B(B) + D + R [cite: 48]
     */
    public int calcularScoreTotal(int cuentas, int filtraciones, int puntosGravedad, int antiguedadAnios) {
        int ac = calcularA_C(cuentas);
        int bb = calcularB_B(filtraciones);
        int d = puntosGravedad; // Gravedad del tipo de datos (0 a 20) [cite: 37, 44]
        int r = calcularR(antiguedadAnios);

        int total = ac + bb + d + r;
        return Math.min(total, 100); // El score máximo es 100 puntos [cite: 31, 32]
    }

    // Tabla A(C) - Cantidad de cuentas expuestas [cite: 39, 40]
    private int calcularA_C(int c) {
        if (c > 20) return scoreCuentasAlto; // 40 pts [cite: 40]
        if (c >= 6) return scoreCuentasMedio; // 25 pts [cite: 40]
        if (c >= 1) return scoreCuentasBajo;  // 10 pts [cite: 40]
        return 0;
    }

    // Tabla B(B) - Filtraciones distintas [cite: 41, 42]
    private int calcularB_B(int b) {
        if (b >= 4) return 30; // 30 pts [cite: 42]
        if (b >= 2) return 20; // 20 pts [cite: 42]
        if (b == 1) return scoreFiltraBajo; // 10 pts [cite: 42]
        return 0;
    }

    // Tabla R - Recencia de la filtración más reciente [cite: 45, 46]
    private int calcularR(int anios) {
        if (anios < 2) return scoreRecenciaMax; // 10 pts [cite: 46]
        if (anios <= 5) return 5;              // 5 pts [cite: 46]
        return 2;                              // 2 pts (Más de 5 años) [cite: 46]
    }

    /**
     * Determina el nivel de exposición y la acción recomendada [cite: 49, 50]
     */
    public String obtenerNivelRiesgo(int score) {
        if (score >= 81) return "CRÍTICO - Acción inmediata"; // [cite: 50]
        if (score >= 61) return "ALTO - Acción prioritaria";   // [cite: 50]
        if (score >= 41) return "MEDIO - Atención requerida";  // [cite: 50]
        if (score >= 21) return "BAJO - Revisión periódica";   // [cite: 50]
        return "MUY BAJO - Monitoreo rutinario";              // [cite: 50]
    }

    /**
     * Genera recomendaciones generales de seguridad basadas en el Score [cite: 61]
     */
    public List<String> obtenerRecomendaciones(int score) {
        List<String> recomendaciones = new ArrayList<>();
        
        // Recomendaciones base para cualquier nivel [cite: 61]
        recomendaciones.add("Implementar políticas de contraseñas robustas y rotación periódica.");
        recomendaciones.add("Activar el Segundo Factor de Autenticación (MFA) en todos los servicios críticos.");

        if (score >= 61) { // Niveles ALTO y CRÍTICO [cite: 50]
            recomendaciones.add("Realizar un restablecimiento de contraseñas forzado para cuentas expuestas.");
            recomendaciones.add("Monitorear activamente intentos de inicio de sesión inusuales.");
            recomendaciones.add("Capacitar al personal afectado en detección de Phishing.");
        }
        
        if (score >= 81) { // Nivel CRÍTICO [cite: 50]
            recomendaciones.add("Auditar el acceso a bases de datos y sistemas sensibles de inmediato.");
            recomendaciones.add("Considerar la contratación de un servicio de monitoreo de Dark Web.");
        }

        return recomendaciones;
    }
}