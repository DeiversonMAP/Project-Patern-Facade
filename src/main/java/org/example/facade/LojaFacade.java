package org.example.facade;

public class LojaFacade {

    private static final double PRECO_POR_GRAMA = 0.5;
    private static final double TAXA_FIXA = 5.0;

    private final Fatiador fatiador;
    private final EstoqueFilamento estoque;
    private final Pagamento pagamento;
    private final FilaImpressao fila;
    private final Notificador notificador;

    public LojaFacade(Fatiador fatiador, EstoqueFilamento estoque, Pagamento pagamento,
                      FilaImpressao fila, Notificador notificador) {
        this.fatiador = fatiador;
        this.estoque = estoque;
        this.pagamento = pagamento;
        this.fila = fila;
        this.notificador = notificador;
    }

    public double orcar(int alturaMm) {
        return fatiador.calcularGramas(alturaMm) * PRECO_POR_GRAMA + TAXA_FIXA;
    }

    public String encomendar(String cliente, String modelo, int alturaMm, String cor) {
        int gramas = fatiador.calcularGramas(alturaMm);
        int tempo = fatiador.calcularTempoMinutos(alturaMm);

        estoque.reservar(cor, gramas);
        double preco = orcar(alturaMm);
        pagamento.cobrar(cliente, preco);
        int posicao = fila.enfileirar(modelo, cor);
        notificador.avisar(cliente,
                "pedido de " + modelo + " confirmado, entrega estimada em " + tempo + " min");

        return "Pedido de " + cliente + ": " + modelo + " (" + cor + ") - R$ " + preco
                + " - posicao " + posicao + " na fila";
    }
}
