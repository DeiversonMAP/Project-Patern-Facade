package org.example.facade;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EstoqueFilamentoTest {

    @Test
    void corSemEstoqueDeveTerZeroGramas() {
        assertEquals(0, new EstoqueFilamento().getGramas("Azul"));
    }

    @Test
    void deveSomarGramasDaMesmaCor() {
        EstoqueFilamento estoque = new EstoqueFilamento();
        estoque.adicionar("Branco", 100);
        estoque.adicionar("Branco", 50);

        assertEquals(150, estoque.getGramas("Branco"));
    }

    @Test
    void deveDescontarGramasReservados() {
        EstoqueFilamento estoque = new EstoqueFilamento();
        estoque.adicionar("Branco", 100);

        estoque.reservar("Branco", 30);

        assertEquals(70, estoque.getGramas("Branco"));
    }

    @Test
    void deveLancarExcecaoQuandoFaltarFilamento() {
        EstoqueFilamento estoque = new EstoqueFilamento();
        estoque.adicionar("Branco", 10);

        IllegalStateException excecao = assertThrows(
                IllegalStateException.class,
                () -> estoque.reservar("Branco", 11)
        );
        assertEquals("Filamento insuficiente", excecao.getMessage());
        assertEquals(10, estoque.getGramas("Branco"));
    }
}
