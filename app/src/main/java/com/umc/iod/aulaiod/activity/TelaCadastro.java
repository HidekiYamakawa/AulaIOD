package com.umc.iod.aulaiod.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.umc.iod.aulaiod.R;
import com.umc.iod.aulaiod.viewmodel.TelaCadastroViewModel;

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
    }

    public void botaoVoltarClick(View view) {
        Log.i(this.getClass().getName(), "Dentro do botaoVoltarClick");
        Intent intencao = new Intent();
        intencao.setClass(getApplicationContext(), TelaPrincipal.class);
        startActivity(intencao);
    }

}