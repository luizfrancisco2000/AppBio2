package com.example.aluno.appbio.Dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.aluno.appbio.Model.Conteudo;

import java.util.List;

@Dao
public interface ConteudoDao {
    @Insert
    void salvar(Conteudo conteudo);

    @Insert
    void salvarTodos(Conteudo... conteudos);

    @Query("SELECT * FROM conteudo")
    List<Conteudo> getAll();

    @Query("SELECT * FROM conteudo WHERE id = :id")
    Conteudo getConteudoById(long id);

    @Query("SELECT * FROM conteudo WHERE assunto_id = :assunto_id ORDER BY caracteristica ASC")
    List<Conteudo> getConteudosByAssuntoId(long assunto_id);

    @Query("SELECT * FROM conteudo WHERE assunto_id = :assunto_id AND caracteristica LIKE :caracteristica ORDER BY caracteristica ASC")
    List<Conteudo> getConteudosByAssuntoIdAndCaracteristica(long assunto_id, String caracteristica);
}
