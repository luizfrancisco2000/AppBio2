package com.example.aluno.appbio.Database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.aluno.appbio.Dao.AssuntoDao;
import com.example.aluno.appbio.Dao.ConteudoDao;
import com.example.aluno.appbio.Dao.LaminaDao;
import com.example.aluno.appbio.Dao.PerguntaDao;
import com.example.aluno.appbio.Model.Assunto;
import com.example.aluno.appbio.Model.Conteudo;
import com.example.aluno.appbio.Model.Lamina;
import com.example.aluno.appbio.Model.Pergunta;

@Database(entities = {Assunto.class, Conteudo.class, Lamina.class, Pergunta.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract AssuntoDao assuntoDao();

    public abstract ConteudoDao conteudoDao();

    public abstract LaminaDao laminaDao();

    public abstract PerguntaDao perguntaDao();

    private static AppDatabase INSTANCE;

    public static AppDatabase getAppDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "histo")
                            .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}
