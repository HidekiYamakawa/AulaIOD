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
import com.umc.iod.aulaiod.viewmodel.TelaCadastroViewModel;

import org.w3c.dom.Text;

public class TelaCadastro extends AppCompatActivity {

    private TelaCadastroViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cadastro);
        Log.i(this.getClass().getName(), "Dentro do onCreate");

        ViewModelProvider vmp = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory());
        viewModel = vmp.get(TelaCadastroViewModel.class);
        Log.d(this.getClass().getName(), "Associou a view model " + viewModel + " a atividade " + this);

        viewModel.getErrorMessageCode().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer numeroDeErro) {
                TextView mensagemErro = (TextView) findViewById(R.id.txt_msgErro);
                if(numeroDeErro != null) {
                    mensagemErro.setText(R.string.email_erro);
                    mensagemErro.setVisibility(View.VISIBLE);
                    Log.d(getClass().getName(), "Mensagem de erro foi alterada para " + mensagemErro);
                } else {
                    mensagemErro.setText(R.string.msg_erro);
                    mensagemErro.setVisibility(View.INVISIBLE);
                    Log.d(getClass().getName(), "Mensagem de erro foi alterada para " + mensagemErro);
                }
            }
        });
    }

    public void botaoVoltarClick(View view) {
        Log.i(this.getClass().getName(), "Dentro do botaoVoltarClick");
        Intent intencao = new Intent();
        intencao.setClass(getApplicationContext(), TelaPrincipal.class);
        startActivity(intencao);
    }

    public void botaoCadastrarClick(View view) {
        Log.i(this.getClass().getName(), "Dentro do botaoCadastrarClick");

        EditText campoEmail = findViewById(R.id.txt_email);
        EditText campoSenha = findViewById(R.id.txt_senha);

        String email = campoEmail.getText().toString();
        String senha = campoSenha.getText().toString();

        Usuario usuario = new Usuario(email, senha);
        viewModel.cadastrar(usuario);
    }

}