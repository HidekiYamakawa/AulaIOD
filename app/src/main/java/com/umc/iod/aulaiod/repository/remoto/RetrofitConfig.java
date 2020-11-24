package com.umc.iod.aulaiod.repository.remoto;

import com.umc.iod.aulaiod.model.Notificacao;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class RetrofitConfig {

    private final Retrofit retrofit;

    public RetrofitConfig() {
        retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.107:8080/RedeSocialRest-master/api/")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
    }

    public PostagemService getPostagemService () {
        return retrofit.create(PostagemService.class);
    }
    public NotificacaoService getNotificacaoService () {
        return retrofit.create(NotificacaoService.class);
    }
}
