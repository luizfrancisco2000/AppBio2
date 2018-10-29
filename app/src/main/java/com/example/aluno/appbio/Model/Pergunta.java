package com.example.aluno.appbio.Model;

import android.arch.persistence.room.*;
import android.support.annotation.NonNull;

import java.io.Serializable;

@Entity(tableName = "pergunta",
        foreignKeys = {@ForeignKey(entity = Assunto.class,
                parentColumns = "id",
                childColumns = "assunto_id")},
        indices = {@Index(value = {"id"}, unique = true),
                @Index(value = {"assunto_id"})})
public class Pergunta implements Serializable {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    private long id;

    @NonNull
    private String pergunta;

    @NonNull
    private String op1;

    @NonNull
    private String op2;

    @NonNull
    private String op3;

    @NonNull
    private int resposta;

    @NonNull
    @ColumnInfo(name = "assunto_id")
    private long assunto_id;

    public Pergunta() {
    }

    public Pergunta(@NonNull String pergunta, @NonNull String op1, @NonNull String op2, @NonNull String op3, @NonNull int resposta, @NonNull long assunto_id) {
        this.pergunta = pergunta;
        this.op1 = op1;
        this.op2 = op2;
        this.op3 = op3;
        this.resposta = resposta;
        this.assunto_id = assunto_id;
    }

    @NonNull
    public long getId() {
        return id;
    }

    public void setId(@NonNull long id) {
        this.id = id;
    }

    @NonNull
    public String getPergunta() {
        return pergunta;
    }

    public void setPergunta(@NonNull String pergunta) {
        this.pergunta = pergunta;
    }

    @NonNull
    public String getOp1() {
        return op1;
    }

    public void setOp1(@NonNull String op1) {
        this.op1 = op1;
    }

    @NonNull
    public String getOp2() {
        return op2;
    }

    public void setOp2(@NonNull String op2) {
        this.op2 = op2;
    }

    @NonNull
    public String getOp3() {
        return op3;
    }

    public void setOp3(@NonNull String op3) {
        this.op3 = op3;
    }

    @NonNull
    public int getResposta() {
        return resposta;
    }

    public void setResposta(@NonNull int resposta) {
        this.resposta = resposta;
    }

    @NonNull
    public long getAssunto_id() {
        return assunto_id;
    }

    public void setAssunto_id(@NonNull long assunto_id) {
        this.assunto_id = assunto_id;
    }

    @Override
    public String toString() {
        return "Pergunta{" +
                "id=" + id +
                ", pergunta='" + pergunta + '\'' +
                ", op1='" + op1 + '\'' +
                ", op2='" + op2 + '\'' +
                ", op3='" + op3 + '\'' +
                ", resposta=" + resposta +
                ", assunto_id=" + assunto_id +
                '}';
    }
}
