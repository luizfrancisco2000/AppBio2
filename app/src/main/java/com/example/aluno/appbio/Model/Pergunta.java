package com.example.aluno.appbio.Model;

import android.arch.persistence.room.Entity;

import java.io.Serializable;

@Entity
public class Pergunta implements Serializable {

    private String pergunta;
    private String op1;
    private String op2;
    private String op3;
    private int resposta;

    public Pergunta() {
    }

    public Pergunta(String pergunta, String op1, String op2, String op3, int resposta) {
        this.pergunta = pergunta;
        this.op1 = op1;
        this.op2 = op2;
        this.op3 = op3;
        this.resposta = resposta;
    }

    public String getPergunta() {
        return pergunta;
    }

    public void setPergunta(String pergunta) {
        this.pergunta = pergunta;
    }

    public String getOp1() {
        return op1;
    }

    public void setOp1(String op1) {
        this.op1 = op1;
    }

    public String getOp2() {
        return op2;
    }

    public void setOp2(String op2) {
        this.op2 = op2;
    }

    public String getOp3() {
        return op3;
    }

    public void setOp3(String op3) {
        this.op3 = op3;
    }

    public int getResposta() {
        return resposta;
    }

    public void setResposta(int resposta) {
        this.resposta = resposta;
    }
}
