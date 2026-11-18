package org.example.facade;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FatiadorTest {

    private final Fatiador fatiador = new Fatiador();

    @Test
    void deveCalcularGramasPelaAltura() {
        assertEquals(20, fatiador.calcularGramas(10));
    }

    @Test
    void deveCalcularTempoPelaAltura() {
        assertEquals(30, fatiador.calcularTempoMinutos(10));
    }

    @Test
    void deveLancarExcecaoParaAlturaInvalida() {
        assertThrows(IllegalArgumentException.class, () -> fatiador.calcularGramas(0));
        assertThrows(IllegalArgumentException.class, () -> fatiador.calcularTempoMinutos(-5));
    }
}
