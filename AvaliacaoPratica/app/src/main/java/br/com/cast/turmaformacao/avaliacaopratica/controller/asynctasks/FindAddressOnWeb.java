package br.com.cast.turmaformacao.avaliacaopratica.controller.asynctasks;


import android.os.AsyncTask;

import br.com.cast.turmaformacao.avaliacaopratica.model.entities.Address;
import br.com.cast.turmaformacao.avaliacaopratica.model.services.AddressBusinessService;

public class FindAddressOnWeb extends AsyncTask<String, Void, Address> {
    @Override
    protected Address doInBackground(String... params) {
        return AddressBusinessService.getAdressByZipCodeOnWeb(params[0]);
    }
}
