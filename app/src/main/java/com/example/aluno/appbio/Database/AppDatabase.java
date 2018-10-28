package com.example.aluno.appbio.Database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.aluno.appbio.Dao.AssuntoDao;
import com.example.aluno.appbio.Dao.ConteudoDao;
import com.example.aluno.appbio.Dao.UsuarioDao;
import com.example.aluno.appbio.Model.Assunto;
import com.example.aluno.appbio.Model.Conteudo;
import com.example.aluno.appbio.Model.Usuario;

@Database(entities = {Assunto.class, Conteudo.class, Usuario.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract AssuntoDao assuntoDao();

    public abstract ConteudoDao conteudoDao();

    public abstract UsuarioDao usuarioDao();

    private static AppDatabase INSTANCE;

    public static AppDatabase getAppDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "user-database")
                            .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}
