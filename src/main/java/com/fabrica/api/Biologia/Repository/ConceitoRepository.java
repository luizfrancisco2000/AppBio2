/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fabrica.api.Biologia.Repository;

import com.fabrica.api.Biologia.Models.Conceito;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Aluno
 */
public interface ConceitoRepository extends JpaRepository<Conceito, Long>{
    
}
