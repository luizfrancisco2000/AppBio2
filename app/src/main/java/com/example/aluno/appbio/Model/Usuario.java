package com.example.aluno.appbio.Model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.io.Serializable;


@Entity(tableName = "usuario",
        indices = {@Index(value = {"id"}, unique = true)})
public class Usuario implements Serializable {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    private long id;

    @NonNull
    private String nome;

    @NonNull
    private int progresso;

    public Usuario() {
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
    public int getProgresso() {
        return progresso;
    }

    public void setProgresso(@NonNull int progresso) {
        this.progresso = progresso;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", progresso=" + progresso +
                '}';
    }
}
