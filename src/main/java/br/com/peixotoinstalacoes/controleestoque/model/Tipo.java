package br.com.peixotoinstalacoes.controleestoque.model;

public enum Tipo {

    CPF("CPF"),
    CNPJ("CNPJ");

    public String tipo;

    Tipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

}
