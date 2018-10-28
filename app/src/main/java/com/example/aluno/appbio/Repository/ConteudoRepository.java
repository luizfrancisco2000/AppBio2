package com.example.aluno.appbio.Repository;

import android.content.Context;
import android.util.Log;

import com.example.aluno.appbio.Database.AppDatabase;
import com.example.aluno.appbio.Model.Assunto;
import com.example.aluno.appbio.Model.Conteudo;

public class ConteudoRepository {

    public static void populaBanco(Assunto assunto, Context context){
        try{
            AppDatabase.getAppDatabase(context).conteudoDao().salvarTodos(Conteudo.popularBanco(assunto.getNome(), assunto.getId()));
        }catch (Exception e){
            Log.e("ERRO POPULA BANCO", e.getMessage());
            e.printStackTrace();
            return;
        }
    }

}
