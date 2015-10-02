package br.com.cast.turmaformacao.avaliacaopratica.model.services;

import java.util.List;

import br.com.cast.turmaformacao.avaliacaopratica.model.entities.Email;
import br.com.cast.turmaformacao.avaliacaopratica.model.persistence.email.EmailRepository;

public class EmailBusinessService {

    public EmailBusinessService() {
        super();
    }

    public static void save(Email email) {
        EmailRepository.save(email);
    }

    public static List<Email> getByContactId(long contactId){
        return EmailRepository.getByContactId(contactId);
    }

    public static void deleteAllByContactId(long contactId) {
        EmailRepository.deleteAllByContactId(contactId);
    }
}
