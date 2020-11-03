package com.umc.iod.aulaiod.repository.local;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.umc.iod.aulaiod.model.Usuario;

@Dao
public interface UsuarioDAO {

    @Insert
    public long inserir(Usuario usuario);

    @Query("SELECT count(id)>0 FROM usuario WHERE email = :email")
    public Boolean verificarEmailExistente(String email);

    @Query("SELECT sincronizado FROM usuario WHERE id = :id")
    public Boolean verificarSincronizacaoUsuario(Long id);

    @Query("SELECT count(id)>0 FROM usuario WHERE email = :email and senha = :senha")
    public Boolean verificarLogin(String email, String senha);
}
