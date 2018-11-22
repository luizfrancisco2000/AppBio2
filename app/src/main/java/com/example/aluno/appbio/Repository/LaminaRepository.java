package com.example.aluno.appbio.Repository;

import android.content.Context;
import android.util.Log;

import com.example.aluno.appbio.Database.AppDatabase;
import com.example.aluno.appbio.Model.Lamina;

import java.util.List;

public class LaminaRepository {

    public static List<Lamina> populaBanco(Context context) {
        try {
            AppDatabase.getAppDatabase(context).laminaDao().salvarTodos(Lamina.popularBanco());
            return AppDatabase.getAppDatabase(context).laminaDao().getAll();
        } catch (Exception e) {
            Log.e("ERRO POPULAR BANCO", e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public static List<Lamina> getAll(Context context) {
        try {
            return AppDatabase.getAppDatabase(context).laminaDao().getAll();
        } catch (Exception e) {
            Log.e("ERRO CONSUL ASSUNTO", e.getMessage());
            e.printStackTrace();
            return null;
        }
    }


    public static List<Lamina> pesquisa(int num, Context context) {
        try {
            return AppDatabase.getAppDatabase(context).laminaDao().pesquisar(num);
        } catch (Exception e) {
            Log.e("ERRO CONSUL ASSUNTO", e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

}
