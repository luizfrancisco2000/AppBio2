/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fabrica.api.Biologia.Controller;

import com.fabrica.api.Biologia.Models.Conteudo;
import com.fabrica.api.Biologia.Repository.ConteudoRepository;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author Aluno
 */
@RestController
@RequestMapping("/api")
public class ConteudoController {

    @Autowired
    ConteudoRepository conteudoRepository;

    @RequestMapping(method = RequestMethod.GET, path = "/conteudo")
    public ResponseEntity<?> getAllConteudo() {
        return new ResponseEntity<>(conteudoRepository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/conteudo/cadastrar")
    public ResponseEntity<?> insertConteudo(@Valid @RequestBody Conteudo conteudo) {
        return new ResponseEntity<>(conteudoRepository.save(conteudo), HttpStatus.OK);
    }
}
