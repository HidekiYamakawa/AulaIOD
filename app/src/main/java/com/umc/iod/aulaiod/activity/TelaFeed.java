package com.umc.iod.aulaiod.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.umc.iod.aulaiod.R;
import com.umc.iod.aulaiod.model.Usuario;
import com.umc.iod.aulaiod.repository.UsuarioRepository;
import com.umc.iod.aulaiod.viewmodel.TelaCadastroViewModel;
import com.umc.iod.aulaiod.viewmodel.TelaFeedViewModel;

public class TelaFeed extends AppCompatActivity {

    private TelaFeedViewModel viewModel;

    private Usuario usuarioLogado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_feed);

        ViewModelProvider vmp = new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory(getApplication()));
        viewModel = vmp.get(TelaFeedViewModel.class);

        long id = getIntent().getLongExtra("usuarioId", 0);
        Log.i(getClass().getName(), "Dentro do onCreate, recebemos o id de usuario " + id);

        viewModel.carregarUsuarioLogado(id);
        viewModel.getUsuarioLogado().observe(this, observadorUsuarioLogado);
    }

    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public void botaoSincronizarClick (View view) {
        Log.i(this.getClass().getName(), "Dentro do botaoSincronizarClick");
        viewModel.sincronizarUsuario();
    }

    private Observer<Usuario> observadorUsuarioLogado = new Observer<Usuario>() {
        @Override
        public void onChanged(Usuario usuario) {
            if(usuario == null) {
                Log.i(getClass().getName(), "Dentro do observadorUsuarioLogado - usuario é NULL");
                Intent intencao = new Intent();
                intencao.setClass(getApplicationContext(), TelaLogin.class);
                startActivity(intencao);
            } else {
                Log.i(getClass().getName(), "Dentro do observadorUsuarioLogado - usuario não é NULL, o email é " + usuario.getEmail());
                if(usuario.isSincronizado()) {
                    Log.i(getClass().getName(), "Dentro do observadorUsuarioLogado - está sincronizado");
                    TextView aviso = findViewById(R.id.txt_notificacao_sincronizacao);
                    aviso.setVisibility(TextView.GONE);
                } else {
                    Log.i(getClass().getName(), "Dentro do observadorUsuarioLogado - não está sincronizado");
                    TextView aviso = findViewById(R.id.txt_notificacao_sincronizacao);
                    aviso.setVisibility(TextView.VISIBLE);
                }
            }
        }
    };
}