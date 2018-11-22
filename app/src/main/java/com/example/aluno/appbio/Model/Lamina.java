package com.example.aluno.appbio.Model;

import android.arch.persistence.room.*;
import android.support.annotation.NonNull;

import java.io.Serializable;

@Entity(tableName = "laminas",
        indices = {@Index(value = {"id"}, unique = true)})
public class Lamina implements Serializable {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    private long id;

    @NonNull
    private long id_img;

    @NonNull
    private int num_lamina;

    @NonNull
    private String local;

    @NonNull
    private String descricao;

    public Lamina() {
    }

    @Ignore
    public Lamina(@NonNull long id_img, @NonNull int num_lamina, @NonNull String local, @NonNull String descricao) {
        this.id_img = id_img;
        this.num_lamina = num_lamina;
        this.local = local;
        this.descricao = descricao;
    }

    @NonNull
    public long getId() {
        return id;
    }

    public void setId(@NonNull long id) {
        this.id = id;
    }

    @NonNull
    public long getId_img() {
        return id_img;
    }

    public void setId_img(@NonNull long id_img) {
        this.id_img = id_img;
    }

    @NonNull
    public int getNum_lamina() {
        return num_lamina;
    }

    public void setNum_lamina(@NonNull int num_lamina) {
        this.num_lamina = num_lamina;
    }

    @NonNull
    public String getLocal() {
        return local;
    }

    public void setLocal(@NonNull String local) {
        this.local = local;
    }

    @NonNull
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(@NonNull String descricao) {
        this.descricao = descricao;
    }


    @Override
    public String toString() {
        return "Lamina{" +
                "id=" + id +
                ", id_img=" + id_img +
                ", num_lamina=" + num_lamina +
                ", local='" + local + '\'' +
                ", descricao='" + descricao + '\'' +
                '}';
    }

    public static Lamina[] popularBanco(){
        return new Lamina[]{
                new Lamina(0, 0, "", ""),
        };
    }
}
