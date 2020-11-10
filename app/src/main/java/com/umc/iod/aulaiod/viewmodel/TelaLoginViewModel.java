package com.umc.iod.aulaiod.viewmodel;

import android.app.Application;
import android.util.Log;
import android.widget.TextView;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

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

    public LiveData<Usuario> autenticarUsuario(Usuario usuario) {
        Log.d(getClass().getName(), "Dentro do autenticarUsuario - " + usuario.getEmail() + " " + usuario.getSenha());
        LiveData<Usuario> usuarioLogado = usuarioRepository.buscaPorEmailSenha(usuario);
        return usuarioLogado;
    }
}
