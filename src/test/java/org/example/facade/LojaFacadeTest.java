package org.example.facade;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class LojaFacadeTest {

    private EstoqueFilamento estoque;
    private Pagamento pagamento;
    private FilaImpressao fila;
    private Notificador notificador;
    private LojaFacade loja;

    @BeforeEach
    void montarLoja() {
        estoque = new EstoqueFilamento();
        estoque.adicionar("Branco", 100);
        pagamento = new Pagamento();
        fila = new FilaImpressao();
        notificador = new Notificador();
        loja = new LojaFacade(new Fatiador(), estoque, pagamento, fila, notificador);
    }

    @Test
    void deveOrcarPeloPesoMaisTaxaFixa() {
        // altura 10 -> 20 g -> 20 * 0,5 + 5 = 15
        assertEquals(15.0, loja.orcar(10), 0.001);
    }

    @Test
    void deveRetornarResumoDoPedido() {
        String resumo = loja.encomendar("Maria", "Busto", 10, "Branco");

        assertEquals("Pedido de Maria: Busto (Branco) - R$ 15.0 - posicao 1 na fila", resumo);
    }

    @Test
    void deveReservarFilamento() {
        loja.encomendar("Maria", "Busto", 10, "Branco");

        assertEquals(80, estoque.getGramas("Branco"));
    }

    @Test
    void deveCobrarOCliente() {
        loja.encomendar("Maria", "Busto", 10, "Branco");

        assertEquals(15.0, pagamento.getTotalRecebido(), 0.001);
    }

    @Test
    void deveColocarNaFilaEAvisarOCliente() {
        loja.encomendar("Maria", "Busto", 10, "Branco");

        assertEquals(Arrays.asList("Busto (Branco)"), fila.getTrabalhos());
        assertEquals(
                Arrays.asList("Maria: pedido de Busto confirmado, entrega estimada em 30 min"),
                notificador.getMensagens()
        );
    }

    @Test
    void segundoPedidoDeveFicarNaSegundaPosicao() {
        loja.encomendar("Maria", "Busto", 10, "Branco");
        String resumo = loja.encomendar("Joao", "Chaveiro", 5, "Branco");

        assertEquals("Pedido de Joao: Chaveiro (Branco) - R$ 10.0 - posicao 2 na fila", resumo);
        assertEquals(70, estoque.getGramas("Branco"));
    }

    @Test
    void semFilamentoNaoDeveCobrarNemEnfileirarNemAvisar() {
        // altura 60 -> 120 g, mas so ha 100 g
        IllegalStateException excecao = assertThrows(
                IllegalStateException.class,
                () -> loja.encomendar("Maria", "Estatua", 60, "Branco")
        );

        assertEquals("Filamento insuficiente", excecao.getMessage());
        assertEquals(100, estoque.getGramas("Branco"));
        assertEquals(0.0, pagamento.getTotalRecebido(), 0.001);
        assertEquals(0, fila.tamanho());
        assertEquals(Collections.emptyList(), notificador.getMensagens());
    }

    @Test
    void alturaInvalidaDeveSerRecusada() {
        assertThrows(IllegalArgumentException.class,
                () -> loja.encomendar("Maria", "Busto", 0, "Branco"));
        assertEquals(100, estoque.getGramas("Branco"));
    }
}
