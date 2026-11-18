package org.example.facade;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class FilaImpressaoTest {

    @Test
    void deveRetornarPosicaoNaFila() {
        FilaImpressao fila = new FilaImpressao();

        assertEquals(1, fila.enfileirar("Busto", "Branco"));
        assertEquals(2, fila.enfileirar("Chaveiro", "Azul"));
        assertEquals(2, fila.tamanho());
    }

    @Test
    void deveGuardarTrabalhosNaOrdemDeChegada() {
        FilaImpressao fila = new FilaImpressao();
        fila.enfileirar("Busto", "Branco");
        fila.enfileirar("Chaveiro", "Azul");

        assertEquals(Arrays.asList("Busto (Branco)", "Chaveiro (Azul)"), fila.getTrabalhos());
    }
}
