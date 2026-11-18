package org.example.facade;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class NotificadorTest {

    @Test
    void deveGuardarMensagensComNomeDoCliente() {
        Notificador notificador = new Notificador();
        notificador.avisar("Maria", "pedido confirmado");
        notificador.avisar("Joao", "pedido pronto");

        assertEquals(Arrays.asList("Maria: pedido confirmado", "Joao: pedido pronto"),
                notificador.getMensagens());
    }
}
