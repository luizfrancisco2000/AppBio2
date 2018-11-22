package com.example.aluno.appbio.Dao;

import android.arch.persistence.room.*;

import com.example.aluno.appbio.Model.Lamina;

import java.util.List;

@Dao
public interface LaminaDao {

    @Insert
    void salvarTodos(Lamina... laminas);

    @Query("SELECT * FROM laminas ORDER BY num_lamina ASC")
    List<Lamina> getAll();

    @Query("SELECT * FROM laminas WHERE num_lamina = :num_lamina ORDER BY num_lamina ASC")
    List<Lamina> pesquisar(int num_lamina);

}
