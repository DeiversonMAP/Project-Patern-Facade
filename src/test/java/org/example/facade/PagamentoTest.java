package org.example.facade;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PagamentoTest {

    @Test
    void deveRetornarComprovanteEAcumularTotal() {
        Pagamento pagamento = new Pagamento();

        assertEquals("Pagamento de Maria: R$ 15.0", pagamento.cobrar("Maria", 15.0));
        pagamento.cobrar("Joao", 10.0);

        assertEquals(25.0, pagamento.getTotalRecebido(), 0.001);
    }

    @Test
    void deveLancarExcecaoParaValorInvalido() {
        Pagamento pagamento = new Pagamento();

        assertThrows(IllegalArgumentException.class, () -> pagamento.cobrar("Maria", 0));
        assertEquals(0.0, pagamento.getTotalRecebido(), 0.001);
    }
}
