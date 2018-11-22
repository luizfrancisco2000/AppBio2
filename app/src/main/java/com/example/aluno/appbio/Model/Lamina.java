package com.example.aluno.appbio.Model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.example.aluno.appbio.R;

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

    public static Lamina[] popularBanco() {
        return new Lamina[]{
                new Lamina(R.drawable.lamina_3, 3, "Tendão", "Conjuntivo denso modelado"),
                new Lamina(R.drawable.lamina_6, 6, "Ossos longos", "Ossificação Endrocondral"),
                new Lamina(R.drawable.lamina_6_2, 6, "Ossos longos", "Ossificação Endrocondral"),
                new Lamina(R.drawable.lamina_7, 7, "Pele fina", "Epitélio estratificado queratinizado"),
                new Lamina(R.drawable.lamina_7_2, 7, "Pele fina", "Epitélio estratificado queratinizado"),
                new Lamina(R.drawable.lamina_8, 8, "Pele grossa", "Epitélio estratificado queratinizado"),
                new Lamina(R.drawable.lamina_8_2, 8, "Pele grossa", "Epitélio estratificado queratinizado"),
                new Lamina(R.drawable.lamina_21, 21, "Esôfago", "Epitélio pavimentoso estratificado não queratinizado e glândulas mucosas"),
                new Lamina(R.drawable.lamina_24, 24, "Jejuno-íleo", "Eoitélio cilíndrico simples com células caliciformes"),
                new Lamina(R.drawable.lamina_24_2, 24, "Jejuno-íleo", "Eoitélio cilíndrico simples com células caliciformes"),
                new Lamina(R.drawable.lamina_29, 29, "Traqueia", "Epitélio pseudoestratificado cilíndrico ciliado, conjuntivo frouxo e cartilagem hiliana"),
                new Lamina(R.drawable.lamina_29_2, 29, "Traqueia", "Epitélio pseudoestratificado cilíndrico ciliado, conjuntivo frouxo e cartilagem hiliana"),
                new Lamina(R.drawable.lamina_32, 32, "Bexiga", "Epitélio de transição e conjuntivo frouxo"),
                new Lamina(R.drawable.lamina_32_2, 32, "Bexiga", "Epitélio de transição e conjuntivo frouxo"),
                new Lamina(R.drawable.lamina_39, 39, "Hipófise", "Glândula cordonal"),
                new Lamina(R.drawable.lamina_40, 40, "Tireoide", "Glândula Vesicular"),
                new Lamina(R.drawable.lamina_42, 42, "Medula Espinhal", ""),
                new Lamina(R.drawable.lamina_42_2, 42, "Medula Espinhal", ""),
        };
    }
}
