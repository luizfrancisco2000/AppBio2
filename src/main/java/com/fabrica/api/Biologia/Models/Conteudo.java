/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fabrica.api.Biologia.Models;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import static javax.persistence.CascadeType.ALL;
/**
 *
 * @author Chico
 */


@Entity
@Table(name = "conteudo")
public class Conteudo implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(name="assunto", nullable=false, length=255)
    private String assunto;

    @Column(name="imagem", nullable=true, length=255, columnDefinition = "BLOB")
    private byte[] imagem;

    @Column(name="nivel", nullable=false, length=10)
    private int nivel;

    @OneToMany(cascade = ALL, mappedBy = "conteudo")
    private List<Conteudo> conteudo;

    public Conteudo() {
    }
    
   
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }
    
}
