package com.example.aluno.appbio.Dao;

import android.arch.persistence.room.*;

import com.example.aluno.appbio.Model.Assunto;
import com.example.aluno.appbio.Model.Pergunta;

import java.util.List;

@Dao
public interface PerguntaDao {

    @Insert
    void salvarTodos(Pergunta[] perguntas);

    @Query("SELECT * FROM pergunta")
    List<Pergunta> getAll();

    @Query("SELECT * FROM pergunta WHERE assunto_id = :id")
    List<Pergunta> getByAssuntoId(long id);

    @Query("SELECT COUNT(id) FROM pergunta WHERE assunto_id = :id")
    int contarPorAssuntoId(long id);

}
