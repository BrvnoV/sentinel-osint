package com.compunet.osint.model;

import lombok.Data;
import java.util.List;

/**
 * Clase que representa el resultado estructurado del análisis.
 * No almacena ni muestra credenciales reales[cite: 25, 27].
 */
@Data
public class ExposicionDTO {
    private String dominio;
    private int totalCuentasExpuestas;
    private int totalFiltraciones;
    private int scoreFinal;
    private String nivelRiesgo; // MUY BAJO, BAJO, MEDIO, ALTO, CRÍTICO [cite: 50] 
    private List<String> recomendaciones;
}