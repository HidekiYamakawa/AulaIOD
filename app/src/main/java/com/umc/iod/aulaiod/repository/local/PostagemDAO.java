package com.umc.iod.aulaiod.repository.local;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.umc.iod.aulaiod.model.Postagem;

import java.util.List;

@Dao
public interface PostagemDAO {

    @Insert
    public long inserir(Postagem postagem);

    @Query("SELECT * FROM postagem")
    public LiveData<List<Postagem>> listarTodos();
}
