package com.umc.iod.aulaiod.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.umc.iod.aulaiod.R;

public class TelaLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_login);
        Log.i(this.getClass().getName(),"Dentro do onCreate");
    }

    public void botaoVoltarClick(View view) {
        Log.i(this.getClass().getName(),"Dentro do botaoVoltarClick");
        Intent intencao = new Intent();
        intencao.setClass(getApplicationContext(), TelaPrincipal.class);
        startActivity(intencao);
    }
}