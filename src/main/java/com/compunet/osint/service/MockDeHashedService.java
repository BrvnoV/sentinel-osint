package com.compunet.osint.service;

import com.compunet.osint.model.DeHashedResponse;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class MockDeHashedService {

    public DeHashedResponse getMockData(String dominio) {
        DeHashedResponse response = new DeHashedResponse();
        response.setSuccess(true);
        response.setTotal(18); // Ejemplo del documento [cite: 53]

        List<DeHashedResponse.Entry> entries = new ArrayList<>();

        // Simular una entrada con Hash (Gravedad 15 pts) [cite: 44]
        DeHashedResponse.Entry entry1 = new DeHashedResponse.Entry();
        entry1.setEmail("admin@" + dominio);
        entry1.setDatabase("Adobe Leak");
        entry1.setHash("5f4dcc3b5aa765d61d8327deb882cf99");
        entry1.setDate("2025-01-10"); // Reciente (< 2 años) [cite: 46]

        entries.add(entry1);
        response.setEntries(entries);

        return response;
    }
}