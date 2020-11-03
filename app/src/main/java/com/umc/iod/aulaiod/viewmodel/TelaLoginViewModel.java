package com.umc.iod.aulaiod.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.umc.iod.aulaiod.R;
import com.umc.iod.aulaiod.model.Usuario;
import com.umc.iod.aulaiod.repository.UsuarioRepository;
import com.umc.iod.aulaiod.util.ThreadManager;

public class TelaLoginViewModel extends AndroidViewModel {

    private UsuarioRepository usuarioRepository;

    private MutableLiveData<Integer> mensagemErroId = new MutableLiveData<>();
    private MutableLiveData<Usuario> usuarioLogado = new MutableLiveData<>();

    public TelaLoginViewModel(Application application) {
        super(application);
        usuarioRepository = new UsuarioRepository(application);
    }

    public MutableLiveData<Integer> getMensagemErroId() {
        return mensagemErroId;
    }

    public MutableLiveData<Usuario> getUsuarioLogado() {
        return usuarioLogado;
    }

    public void validarLogin(Usuario usuario) {
        Log.d(getClass().getName(), "Dentro do validarLogin");
        ThreadManager.getExecutor().execute(() -> {
            if (usuarioRepository.verificaLogin(usuario.getEmail(),usuario.getSenha())) {
                mensagemErroId.postValue(null);
                usuarioLogado.postValue(usuario);
            } else {
                mensagemErroId.postValue(R.string.credenciais_invalidas);
                usuarioLogado.postValue(null);
            }
        });
    }
}
