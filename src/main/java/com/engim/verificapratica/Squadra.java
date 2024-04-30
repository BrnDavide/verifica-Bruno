package com.engim.verificapratica;

public class Squadra {
    private String nome;
    private String nazione;

    public Squadra(String nome, String nazione) {
        this.nome = nome;
        this.nazione = nazione;
    }

    public String getNome() {
        return nome;
    }

    public String getNazione() {
        return nazione;
    }

    public void setNazione(String nazione) {
        this.nazione = nazione;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
