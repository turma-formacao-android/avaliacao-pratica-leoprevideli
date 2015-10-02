package br.com.cast.turmaformacao.avaliacaopratica.model.services;


import br.com.cast.turmaformacao.avaliacaopratica.model.entities.Address;
import br.com.cast.turmaformacao.avaliacaopratica.model.http.AddressService;
import br.com.cast.turmaformacao.avaliacaopratica.model.persistence.address.AddressRepository;

public class AddressBusinessService {

    public AddressBusinessService() {
        super();
    }

    public static Address getAdressByZipCodeOnWeb(String zipCode){
        return AddressService.getAdressByZipCode(zipCode);
    }

    public static Address getByZipCode(String zipCode) {
        return AddressRepository.getByZipCode(zipCode);
    }

    public static Long save(Address address){
        return AddressRepository.save(address);
    }

    public static Address getById(long id){
        return AddressRepository.getById(id);
    }
}
