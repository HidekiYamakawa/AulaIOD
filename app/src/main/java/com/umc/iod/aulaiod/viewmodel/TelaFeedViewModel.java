package com.umc.iod.aulaiod.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.umc.iod.aulaiod.R;
import com.umc.iod.aulaiod.model.Postagem;
import com.umc.iod.aulaiod.model.Usuario;
import com.umc.iod.aulaiod.repository.PostagemRepository;
import com.umc.iod.aulaiod.repository.UsuarioRepository;
import com.umc.iod.aulaiod.util.ThreadManager;

import java.util.List;

public class TelaFeedViewModel extends AndroidViewModel {

    private UsuarioRepository usuarioRepository;
    private PostagemRepository postagemRepository;

    private LiveData<Usuario> usuarioLogado;

    public TelaFeedViewModel(Application application) {
        super(application);
        usuarioRepository = new UsuarioRepository(application);
        postagemRepository = new PostagemRepository(application);
    }

    public void carregarUsuarioLogado(long id) {
        usuarioLogado = usuarioRepository.pesquisarPorIdLive(id);
    }

    public LiveData<List<Postagem>> getPosts () {
        return postagemRepository.getListaPostagem();
    }

    public LiveData<Usuario> getUsuarioLogado() {
        return usuarioLogado;
    }

    public void sincronizarUsuario() {
        Usuario usuario = usuarioLogado.getValue();
        usuario.setSincronizado(true);
        ThreadManager.getExecutor().execute(() -> {
            usuarioRepository.atualizar(usuario);
        });
    }
}
