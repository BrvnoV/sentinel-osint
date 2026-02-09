package com.compunet.osint.model;

import lombok.Data;
import java.util.List;

// Clase que representa lo que esperamos recibir de DeHashed.

@Data
public class DeHashedResponse {
    private boolean success;
    private int total;
    private List<Entry> entries;

    @Data
    public static class Entry {
        private String email;
        private String database;
        private String password; // Se procesara para ocultarla
        private String hash;
        private String date; // Fecha de la filtración
    }
}