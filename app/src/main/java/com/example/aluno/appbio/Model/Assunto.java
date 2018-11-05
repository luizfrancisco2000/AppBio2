package com.example.aluno.appbio.Model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.io.Serializable;

@Entity(tableName = "assunto",
        indices = {@Index(value = {"id"}, unique = true)})
public class Assunto implements Serializable {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    private long id;

    @NonNull
    private String nome;

    @NonNull
    private boolean ativo;

    public Assunto() {
    }

    @Ignore
    public Assunto(@NonNull String nome, @NonNull boolean ativo) {
        this.nome = nome;
        this.ativo = ativo;
    }

    @NonNull
    public long getId() {
        return id;
    }

    public void setId(@NonNull long id) {
        this.id = id;
    }

    @NonNull
    public String getNome() {
        return nome;
    }

    public void setNome(@NonNull String nome) {
        this.nome = nome;
    }

    @NonNull
    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(@NonNull boolean ativo) {
        this.ativo = ativo;
    }

    @Override
    public String toString() {
        return nome;
    }

    public static Assunto[] popularBanco() {
        return new Assunto[]{
                new Assunto("Tecido Epitelial", true),
                new Assunto("Tecido Conjuntivo", true),
                new Assunto("Tecido Adiposo", true),
                new Assunto("Tecido Cartilaginoso", true)
        };
    }
}
