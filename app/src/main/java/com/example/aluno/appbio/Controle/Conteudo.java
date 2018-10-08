package com.example.aluno.appbio.Controle;

import java.io.Serializable;

/**
 * Created by Aluno on 08/10/2018.
 */

public class Conteudo implements Serializable {

    private long id;

    private String conceito;

    private String nome;

    private byte[] imagem;

    private long nivel;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getConceito() {
        return conceito;
    }

    public void setConceito(String conceito) {
        this.conceito = conceito;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public byte[] getImagem() {
        return imagem;
    }

    public void setImagem(byte[] imagem) {
        this.imagem = imagem;
    }

    public long getNivel() {
        return nivel;
    }

    public void setNivel(long nivel) {
        this.nivel = nivel;
    }
}
