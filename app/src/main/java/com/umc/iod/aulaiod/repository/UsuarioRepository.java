package com.umc.iod.aulaiod.repository;

import android.app.Application;
import android.util.Log;

import com.umc.iod.aulaiod.model.Usuario;
import com.umc.iod.aulaiod.repository.local.LocalDatabase;
import com.umc.iod.aulaiod.repository.local.UsuarioDAO;

public class UsuarioRepository {

    private LocalDatabase database;
    private UsuarioDAO usuarioDAO;

    public UsuarioRepository(Application context) {
        database = LocalDatabase.getInstance(context);
        usuarioDAO = database.usuarioDAO();
    }

    public boolean verificaEmailExistente(String email) {
        Log.d(getClass().getName(), "Dentro do verificaEmailExistente");
        Boolean existe = usuarioDAO.verificarEmailExistente(email);
        return existe;
    }

    public boolean verificaSincronizacaoUsuario(Long id) {
        Log.d(getClass().getName(), "Dentro do verificaSincronizacaoUsuario");
        Boolean sinc = usuarioDAO.verificarSincronizacaoUsuario(id);
        return sinc;
    }

    public boolean verificaLogin(String email, String senha) {
        Log.d(getClass().getName(), "Dentro do verificaLogin");
        Boolean sucesso = usuarioDAO.verificarLogin(email,senha);
        return sucesso;
    }

    public Usuario cadastrar(Usuario usuario) {
        Log.d(getClass().getName(), "Dentro do cadastrar");

        if(!verificaEmailExistente(usuario.getEmail())) {
            Log.d(getClass().getName(), "Cadastrou o usuario de email: " + usuario.getEmail());
            long id = usuarioDAO.inserir(usuario);
            usuario.setId(id);
            return usuario;
        }
        Log.d(getClass().getName(), "Não cadastrou o usuario de email: " + usuario.getEmail());
        return null;
    }
}
