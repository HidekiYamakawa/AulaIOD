package com.umc.iod.aulaiod.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.umc.iod.aulaiod.R;
import com.umc.iod.aulaiod.model.Usuario;
import com.umc.iod.aulaiod.viewmodel.TelaLoginViewModel;

public class TelaLogin extends AppCompatActivity {

    private TelaLoginViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_login);
        Log.i(this.getClass().getName(),"Dentro do onCreate");

        ViewModelProvider vmp = new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory(getApplication()));
        viewModel = vmp.get(TelaLoginViewModel.class);
        Log.d(this.getClass().getName(), "Associou a view model " + viewModel + " a atividade " + this);

        viewModel.getMensagemErroId().observe(this, observadorMensagemErro);
        viewModel.getUsuarioLogado().observe(this, observadorUsuarioLogado);
    }

    public void botaoVoltarClick(View view) {
        Log.i(this.getClass().getName(),"Dentro do botaoVoltarClick");
        Intent intencao = new Intent();
        intencao.setClass(getApplicationContext(), TelaPrincipal.class);
        startActivity(intencao);
    }

    public void botaoEntrarClick(View view) {
        Log.i(this.getClass().getName(), "Dentro do botaoEntrarClick");

        EditText campoEmail = findViewById(R.id.txt_email);
        EditText campoSenha = findViewById(R.id.txt_senha);

        String email = campoEmail.getText().toString();
        String senha = campoSenha.getText().toString();

        Usuario usuario = new Usuario(email, senha);
        viewModel.validarLogin(usuario);
    }

    private Observer<Integer> observadorMensagemErro = new Observer<Integer>() {
        @Override
        public void onChanged(Integer mensagemErroId) {
            Log.d(getClass().getName(), "Dentro do observadorMensagemErro, houve mudança no live data");

            TextView mensagemErro = (TextView) findViewById(R.id.txt_credenciais_invalidas);

            if(mensagemErroId != null) {
                mensagemErro.setText(R.string.credenciais_invalidas);
                mensagemErro.setVisibility(View.VISIBLE);
            } else {
                mensagemErro.setText("");
                mensagemErro.setVisibility(View.INVISIBLE);
            }
        }
    };

    private Observer<Usuario> observadorUsuarioLogado = new Observer<Usuario>() {
        @Override
        public void onChanged(Usuario usuario) {
            if(usuario != null) {
                Log.d(getClass().getName(), "Dentro do observadorUsuarioCadastrado, houve mudança no live data, o id é " + usuario.getId());

                Intent intencao = new Intent();
                intencao.setClass(getApplicationContext(), TelaFeed.class);
                startActivity(intencao);
            }
        }
    };
}