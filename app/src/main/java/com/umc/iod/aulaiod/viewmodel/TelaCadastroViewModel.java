package com.umc.iod.aulaiod.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.umc.iod.aulaiod.model.Usuario;
import com.umc.iod.aulaiod.repository.UsuarioRepository;

public class TelaCadastroViewModel extends ViewModel {

    private UsuarioRepository usuarioRepository;
    private MutableLiveData<Integer> errorMessageCode = new MutableLiveData<>();

    public TelaCadastroViewModel() {
        usuarioRepository = new UsuarioRepository();
    }

    public MutableLiveData<Integer> getErrorMessageCode() {
        return errorMessageCode;
    }

    public void cadastrar(Usuario usuario) {
        Log.d(getClass().getName(), "Dentro do cadastrar");
        if(usuarioRepository.cadastrar(usuario) == null) {
            errorMessageCode.postValue(1);
        } else {
            errorMessageCode.postValue(null);
        }
    }
}
