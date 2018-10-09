/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fabrica.api.Biologia.Models;

import java.io.*;
import javax.persistence.*;

/**
 *
 * @author Aluno
 */

@Entity
@Table(name="conceito")
public class Conceito implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(name="divisao", nullable=false,length=255)
    private String divisao;
    
    @Column(name="conceito", nullable=false, length=255)
    private String conceito;
    
    @Column(name="tipoConceito", nullable=false, length=255)
    private String tipoConceito;
    
    @ManyToOne
    @JoinColumn(name = "conteudo", nullable = true)
    private Conteudo conteudo;

    public Conceito(){
        
    }
   
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDivisao() {
        return divisao;
    }

    public void setDivisao(String divisao) {
        this.divisao = divisao;
    }

    public String getConceito() {
        return conceito;
    }

    public void setConceito(String conceito) {
        this.conceito = conceito;
    }

    public String getTipoConceito() {
        return tipoConceito;
    }

    public void setTipoConceito(String tipoConceito) {
        this.tipoConceito = tipoConceito;
    }

    public Conteudo getConteudo() {
        return conteudo;
    }

    public void setConteudo(Conteudo conteudo) {
        this.conteudo = conteudo;
    }
}
