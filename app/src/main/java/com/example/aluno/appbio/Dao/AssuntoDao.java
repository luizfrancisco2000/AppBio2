package com.example.aluno.appbio.Dao;

import android.arch.persistence.room.*;

import com.example.aluno.appbio.Model.Assunto;

import java.util.List;

@Dao
public interface AssuntoDao {

    @Insert
    void salvar (Assunto assunto);

    @Insert
    void salvarTodos(Assunto... assuntos);

    @Query("SELECT * FROM assunto")
    List<Assunto> getAll();

    @Query("SELECT * FROM assunto WHERE id = :id")
    Assunto getAssuntoById(long id);
}
