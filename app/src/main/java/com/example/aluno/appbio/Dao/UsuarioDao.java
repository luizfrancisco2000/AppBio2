package com.example.aluno.appbio.Dao;

import android.arch.persistence.room.*;

import com.example.aluno.appbio.Model.Usuario;

@Dao
public interface UsuarioDao {
    @Insert
    void salvar(Usuario usuario);

    @Update
    void atualizar(Usuario usuario);

    @Query("SELECT * FROM usuario where id = :id")
    Usuario getUsuarioById(long id);
}
