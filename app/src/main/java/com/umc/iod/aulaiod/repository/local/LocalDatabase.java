package com.umc.iod.aulaiod.repository.local;

import android.app.Application;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.umc.iod.aulaiod.model.Notificacao;
import com.umc.iod.aulaiod.model.Postagem;
import com.umc.iod.aulaiod.model.Usuario;

@Database(entities = {Usuario.class, Postagem.class, Notificacao.class}, version = 1, exportSchema = false)
public abstract class LocalDatabase extends RoomDatabase {

    public abstract UsuarioDAO usuarioDAO();
    public abstract PostagemDAO postagemDAO();
    public abstract NotificacaoDAO notificacaoDAO();
    private static LocalDatabase instacia;

    public static LocalDatabase getInstance(Application context) {
        if (instacia == null) {
            instacia = Room.databaseBuilder(context, LocalDatabase.class, "local_database").build();
        }
        return instacia;
    }
}
