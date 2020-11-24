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
import com.umc.iod.aulaiod.model.Notificacao;
import com.umc.iod.aulaiod.model.Usuario;
import com.umc.iod.aulaiod.service.NotificacaoUsuarioService;
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
    }

    public void botaoVoltarClick(View view) {
        Log.i(this.getClass().getName(),"Dentro do botaoVoltarClick");
        Intent intencao = new Intent();
        intencao.setClass(getApplicationContext(), TelaPrincipal.class);
        startActivity(intencao);
    }

    public void botaoLoginClick(View view) {
        Log.i(this.getClass().getName(), "Dentro do botaoEntrarClick");

        EditText campoEmail = findViewById(R.id.txt_email);
        EditText campoSenha = findViewById(R.id.txt_senha);

        String email = campoEmail.getText().toString();
        String senha = campoSenha.getText().toString();

        Usuario usuario = new Usuario(email, senha);

        viewModel.autenticarUsuario(usuario).observe(this, observadorUsuarioLogado);
    }


    private Observer<Usuario> observadorUsuarioLogado = new Observer<Usuario>() {
        @Override
        public void onChanged(Usuario usuario) {
            if(usuario == null) {
                Log.d(getClass().getName(), "Não está logado, usuario é null");
                TextView mensagemErro = findViewById(R.id.txt_credenciais_invalidas);
                mensagemErro.setText(getResources().getString(R.string.credenciais_invalidas));
                mensagemErro.setVisibility(TextView.VISIBLE);
            } else {
                Log.d(getClass().getName(), "Está logado, vai para tela feed, com id = " + usuario.getId());

                Intent intencaoServicoNotificacao = new Intent();
                intencaoServicoNotificacao.setClass(getApplicationContext(), NotificacaoUsuarioService.class);
                intencaoServicoNotificacao.putExtra("usuarioId", usuario.getId());
                startService(intencaoServicoNotificacao);

                Intent intencao = new Intent();
                intencao.setClass(getApplicationContext(), TelaFeed.class);
                intencao.putExtra("usuarioId", usuario.getId());
                startActivity(intencao);
            }
        }
    };
}