package br.com.cast.turmaformacao.avaliacaopratica.controller.asynctasks;


import android.os.AsyncTask;

import br.com.cast.turmaformacao.avaliacaopratica.model.entities.Contact;
import br.com.cast.turmaformacao.avaliacaopratica.model.services.ContactBusinessService;

public class SaveContactTask extends AsyncTask<Contact, Void, Void> {
    @Override
    protected Void doInBackground(Contact... contacts) {
        ContactBusinessService.save(contacts[0]);
        return null;
    }
}
