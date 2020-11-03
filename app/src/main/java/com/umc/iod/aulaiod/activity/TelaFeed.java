package com.umc.iod.aulaiod.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_feed);

        ViewModelProvider vmp = new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory(getApplication()));
        viewModel = vmp.get(TelaFeedViewModel.class);
        Log.d(this.getClass().getName(), "Associou a view model " + viewModel + " a atividade " + this);

        viewModel.getNotificacaoSincronizacao().observe(this, observadorNotificacaoSincronizacao);

        Usuario u = new Usuario();
        u.setEmail("a@email.com");
        u.setId(1);
        u.setSenha("123");
        u.setSincronizado(false);

        viewModel.verificarSincronizacao(u);
    }

    public void botaoSincronizarClick (View view) {
        Log.i(this.getClass().getName(), "Dentro do botaoSincronizarClick");
    }

    private Observer<Integer> observadorNotificacaoSincronizacao = new Observer<Integer>() {
        @Override
        public void onChanged(Integer notificacaoId) {
            Log.d(getClass().getName(), "Dentro do observadorNotificacaoSincronizacao, houve mudança no live data");

            TextView notificacao = (TextView) findViewById(R.id.txt_notificacao_sincronizacao);

            if(notificacaoId != null) {
                notificacao.setText(R.string.notificacao_sincronizacao);
                notificacao.setVisibility(View.VISIBLE);
            } else {
                notificacao.setText("");
                notificacao.setVisibility(View.INVISIBLE);
            }
        }
    };
}