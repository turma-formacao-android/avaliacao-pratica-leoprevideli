package br.com.cast.turmaformacao.avaliacaopratica;

import android.app.Application;

import br.com.cast.turmaformacao.avaliacaopratica.util.ApplicationUtil;

public class ContactManagerApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ApplicationUtil.setContext(getApplicationContext());
    }
}
