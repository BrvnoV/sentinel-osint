package com.compunet.osint.model;

import lombok.Data;

//Esta clase representará el resultado que se quiere mostrar.
@Data
public class ExposicionDTO {
    private String dominio;
    private int totalCuentasExpuestas;
    private int totalFiltraciones;
    private int scoreFinal;
    private String nivelRiesgo; // MUY BAJO, BAJO, MEDIO, ALTO, CRÍTICO
}
