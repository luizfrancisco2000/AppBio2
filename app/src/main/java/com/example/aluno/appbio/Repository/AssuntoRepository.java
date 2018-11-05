package com.example.aluno.appbio.Repository;

import android.content.Context;
import android.util.Log;

import com.example.aluno.appbio.Database.AppDatabase;
import com.example.aluno.appbio.Model.Assunto;

import java.util.List;

public class AssuntoRepository {

    public static List<Assunto> populaBanco(Context context) {
        try {
            AppDatabase.getAppDatabase(context).assuntoDao().salvarTodos(Assunto.popularBanco());
            return AppDatabase.getAppDatabase(context).assuntoDao().getAll();
        } catch (Exception e) {
            Log.e("ERRO POPULAR BANCO", e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public static List<Assunto> getAll(Context context) {
        try {
            return AppDatabase.getAppDatabase(context).assuntoDao().getAll();
        } catch (Exception e) {
            Log.e("ERRO CONSUL ASSUNTO", e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public static List<Assunto> getAllAtivos(Context context) {
        try {
            return AppDatabase.getAppDatabase(context).assuntoDao().getAllAtivos();
        } catch (Exception e) {
            Log.e("ERRO CONSUL ASSUNTO", e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public static List<Assunto> pesquisa(String filtro, Context context) {
        try {
            String pesquisa = "%" + filtro + "%";
            return AppDatabase.getAppDatabase(context).assuntoDao().getByFiltro(pesquisa);
        } catch (Exception e) {
            Log.e("ERRO CONSUL ASSUNTO", e.getMessage());
            e.printStackTrace();
            return null;
        }
    }


    public static boolean atualizar(List<Assunto> assuntos, Context context) {
        try {
            AppDatabase.getAppDatabase(context).assuntoDao().atualizaLista(assuntos);
            return true;
        } catch (Exception e) {
            Log.e("ERRO UPDATE ASSUNTO", e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}
