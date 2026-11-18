package org.example.facade;

import java.util.ArrayList;
import java.util.List;

public class Notificador {

    private final List<String> mensagens = new ArrayList<>();

    public void avisar(String cliente, String mensagem) {
        mensagens.add(cliente + ": " + mensagem);
    }

    public List<String> getMensagens() {
        return new ArrayList<>(mensagens);
    }
}
