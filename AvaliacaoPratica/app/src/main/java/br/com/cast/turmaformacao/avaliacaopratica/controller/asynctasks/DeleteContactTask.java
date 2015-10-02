package br.com.cast.turmaformacao.avaliacaopratica.controller.asynctasks;

import android.os.AsyncTask;

import br.com.cast.turmaformacao.avaliacaopratica.model.entities.Contact;
import br.com.cast.turmaformacao.avaliacaopratica.model.services.ContactBusinessService;

public class DeleteContactTask  extends AsyncTask<Contact, Void, Void> {
    @Override
    protected Void doInBackground(Contact... params) {
        ContactBusinessService.delete(params[0]);
        return null;
    }
}
