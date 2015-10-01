package br.com.cast.turmaformacao.avaliacaopratica.model.services;


import java.util.List;

import br.com.cast.turmaformacao.avaliacaopratica.model.entities.Address;
import br.com.cast.turmaformacao.avaliacaopratica.model.http.AddressService;

public class AddressBusinessService {

    public AddressBusinessService() {
        super();
    }

    public static Address getAdressByZipCodeOnWeb(String zipCode){
        return AddressService.getAdressByZipCode(zipCode);
    }
}
