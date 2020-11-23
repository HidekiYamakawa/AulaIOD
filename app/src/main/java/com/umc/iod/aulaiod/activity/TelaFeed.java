package com.umc.iod.aulaiod.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.umc.iod.aulaiod.R;
import com.umc.iod.aulaiod.model.Postagem;
import com.umc.iod.aulaiod.model.Usuario;
import com.umc.iod.aulaiod.repository.UsuarioRepository;
import com.umc.iod.aulaiod.viewmodel.TelaCadastroViewModel;
import com.umc.iod.aulaiod.viewmodel.TelaFeedViewModel;

import java.util.List;

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
        viewModel.getPosts().observe(this, observadorPosts);
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
            Log.i(getClass().getName(), "Dentro do observadorUsuarioLogado");
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

    private Observer<List<Postagem>> observadorPosts = new Observer<List<Postagem>>() {
        @Override
        public void onChanged(List<Postagem> listaPosts) {
            Log.i(getClass().getName(), "Dentro do observadorPosts");
            if (listaPosts != null) {
                LinearLayout layout = findViewById(R.id.postLinearLayout);
                layout.removeAllViews();

                for (Postagem postagem : listaPosts) {

                    TextView textViewPost = new TextView(getApplicationContext());
                    textViewPost.setText(postagem.getTitulo() + "\r\n" + postagem.getTexto() + "\r\n\r\n");

                    layout.addView(textViewPost);
                }
            }
        }
    };
}