package com.example.aluno.appbio.Model;

import android.arch.persistence.room.*;
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

    public Assunto() {
    }

    @Ignore
    public Assunto(@NonNull String nome) {
        this.nome = nome;
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

    @Override
    public String toString() {
        return "Assunto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }

    public static Assunto[] popularBanco() {
        return new Assunto[] {
                new Assunto("Tecido Epitelial"),
                new Assunto("Tecido Conjuntivo"),
                new Assunto("Tecido Adiposo"),
                new Assunto("Tecido Cartilaginoso")
        };
    }
}
