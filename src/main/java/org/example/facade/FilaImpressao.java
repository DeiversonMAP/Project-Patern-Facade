package org.example.facade;

import java.util.ArrayList;
import java.util.List;

public class FilaImpressao {

    private final List<String> trabalhos = new ArrayList<>();

    public int enfileirar(String modelo, String cor) {
        trabalhos.add(modelo + " (" + cor + ")");
        return trabalhos.size();
    }

    public int tamanho() {
        return trabalhos.size();
    }

    public List<String> getTrabalhos() {
        return new ArrayList<>(trabalhos);
    }
}
