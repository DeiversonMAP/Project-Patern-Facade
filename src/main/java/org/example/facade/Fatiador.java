package org.example.facade;

public class Fatiador {

    private static final int GRAMAS_POR_MM = 2;
    private static final int MINUTOS_POR_MM = 3;

    public int calcularGramas(int alturaMm) {
        validar(alturaMm);
        return alturaMm * GRAMAS_POR_MM;
    }

    public int calcularTempoMinutos(int alturaMm) {
        validar(alturaMm);
        return alturaMm * MINUTOS_POR_MM;
    }

    private void validar(int alturaMm) {
        if (alturaMm <= 0) {
            throw new IllegalArgumentException("Altura invalida");
        }
    }
}
