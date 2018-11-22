package com.example.aluno.appbio.Dao;

import android.arch.persistence.room.*;

import com.example.aluno.appbio.Model.Assunto;

import java.util.List;

@Dao
public interface AssuntoDao {

    @Insert
    void salvarTodos(Assunto... assuntos);

    @Update
    void atualizaLista(List<Assunto> assuntos);

    @Query("SELECT * FROM assunto")
    List<Assunto> getAll();

    @Query("SELECT * FROM assunto WHERE ativo = 1")
    List<Assunto> getAllAtivos();

    @Query("SELECT * FROM assunto WHERE nome LIKE :filtro")
    List<Assunto> getByFiltro(String filtro);

    @Query("SELECT * FROM assunto WHERE id = :id")
    Assunto getById(long id);
}
