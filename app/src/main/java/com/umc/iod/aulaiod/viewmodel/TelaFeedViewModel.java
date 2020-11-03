package com.umc.iod.aulaiod.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.umc.iod.aulaiod.R;
import com.umc.iod.aulaiod.model.Usuario;
import com.umc.iod.aulaiod.repository.UsuarioRepository;
import com.umc.iod.aulaiod.util.ThreadManager;

public class TelaFeedViewModel extends AndroidViewModel {

    private UsuarioRepository usuarioRepository;

    private MutableLiveData<Integer> notificacaoSincronizacao = new MutableLiveData<>();

    public TelaFeedViewModel( Application application) {
        super(application);
        usuarioRepository = new UsuarioRepository(application);
    }

    public MutableLiveData<Integer> getNotificacaoSincronizacao() {
        return notificacaoSincronizacao;
    }

    public void verificarSincronizacao(Usuario usuario) {
        Log.d(getClass().getName(), "Dentro do verificarSincronizacao");
        ThreadManager.getExecutor().execute(() -> {
            if (usuarioRepository.verificaSincronizacaoUsuario(usuario.getId())) {
                notificacaoSincronizacao.postValue(null);
            } else {
                notificacaoSincronizacao.postValue(R.string.notificacao_sincronizacao);
            }
        });
    }
}
