package org.example.facade;

public class Pagamento {

    private double totalRecebido;

    public String cobrar(String cliente, double valor) {
        if (valor <= 0) {
            throw new IllegalArgumentException("Valor invalido");
        }
        totalRecebido += valor;
        return "Pagamento de " + cliente + ": R$ " + valor;
    }

    public double getTotalRecebido() {
        return totalRecebido;
    }
}
