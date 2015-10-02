package br.com.cast.turmaformacao.avaliacaopratica.controller.asynctasks;

import android.os.AsyncTask;

import java.util.List;

import br.com.cast.turmaformacao.avaliacaopratica.model.entities.Contact;
import br.com.cast.turmaformacao.avaliacaopratica.model.services.ContactBusinessService;

public class GetAllContactsTask extends AsyncTask<Void, Void, List<Contact>> {

    @Override
    protected List<Contact> doInBackground(Void... params) {
        return ContactBusinessService.getAll();
    }
}
