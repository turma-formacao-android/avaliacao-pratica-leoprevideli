package br.com.cast.turmaformacao.avaliacaopratica.model.services;

import java.util.List;

import br.com.cast.turmaformacao.avaliacaopratica.model.entities.Phone;
import br.com.cast.turmaformacao.avaliacaopratica.model.persistence.phone.PhoneRepository;

public class PhoneBusinessService {

    public PhoneBusinessService() {
        super();

    }

    public static void save(Phone phone){
        PhoneRepository.save(phone);
    }

    public static List<Phone> getByContactId(long contactId){
        return PhoneRepository.getByContactId(contactId);
    }

    public static void deleteAllByContactId(long contactId) {
        PhoneRepository.deleteAllByContactId(contactId);
    }
}
