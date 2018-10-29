package com.example.aluno.appbio.Repository;

import android.content.Context;
import android.util.Log;

import com.example.aluno.appbio.Database.AppDatabase;
import com.example.aluno.appbio.Model.Pergunta;

import java.util.List;

public class PerguntaRepository {
    public static void populaBanco(Context context) {
        try {
            AppDatabase.getAppDatabase(context).perguntaDao().salvarTodos(Pergunta.populaBanco());
        } catch (Exception e) {
            Log.e("ERRO POPULA BANCO", e.getMessage());
            e.printStackTrace();
            return;
        }
    }

    public static List<Pergunta> listar(Context context) {
        try {
            return AppDatabase.getAppDatabase(context).perguntaDao().getAll();
        } catch (Exception e) {
            Log.e("ERRO LISTAR PERGUNTAS", e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public static List<Pergunta> listarPorAssunto(Context context, long assunto_id) {
        try {
            return AppDatabase.getAppDatabase(context).perguntaDao().getByAssuntoId(assunto_id);
        } catch (Exception e) {
            Log.e("ERRO LISTAR PERGUNTAS", e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}
