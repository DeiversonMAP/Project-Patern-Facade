package org.example.facade;

import java.util.HashMap;
import java.util.Map;

public class EstoqueFilamento {

    private final Map<String, Integer> gramasPorCor = new HashMap<>();

    public void adicionar(String cor, int gramas) {
        gramasPorCor.merge(cor, gramas, Integer::sum);
    }

    public int getGramas(String cor) {
        return gramasPorCor.getOrDefault(cor, 0);
    }

    public void reservar(String cor, int gramas) {
        if (getGramas(cor) < gramas) {
            throw new IllegalStateException("Filamento insuficiente");
        }
        gramasPorCor.put(cor, getGramas(cor) - gramas);
    }
}
