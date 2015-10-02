package br.com.cast.turmaformacao.avaliacaopratica.model.services;

import java.util.List;

import br.com.cast.turmaformacao.avaliacaopratica.model.entities.Address;
import br.com.cast.turmaformacao.avaliacaopratica.model.entities.Contact;
import br.com.cast.turmaformacao.avaliacaopratica.model.entities.Email;
import br.com.cast.turmaformacao.avaliacaopratica.model.entities.Phone;
import br.com.cast.turmaformacao.avaliacaopratica.model.persistence.address.AddressRepository;
import br.com.cast.turmaformacao.avaliacaopratica.model.persistence.contact.ContactRepository;

public class ContactBusinessService {

    public ContactBusinessService() {
        super();
    }

    public static void save(Contact contact) {
        Address existentAddress = AddressBusinessService.getByZipCode(contact.getAddress().getZipCode());
        if(existentAddress != null){
            contact.getAddress().setId(existentAddress.getId());
        }
        contact.getAddress().setId(AddressBusinessService.save(contact.getAddress()));
        long contactId = ContactRepository.save(contact);
        if(contact.getId() != null)
            contactId = contact.getId();

        PhoneBusinessService.deleteAllByContactId(contactId);
        for(Phone phone : contact.getTelephones()){
            phone.setContactId(contactId);
            PhoneBusinessService.save(phone);
        }

        EmailBusinessService.deleteAllByContactId(contactId);
        for(Email email : contact.getEmails()){
            email.setContactId(contactId);
            EmailBusinessService.save(email);
        }
    }

    public static List<Contact> getAll(){
        return ContactRepository.getAll();
    }

    public static void delete(Contact contact) {
        ContactRepository.delete(contact);
    }

    public static List<Contact> getAllByName(String name) {
        return ContactRepository.getAllByName(name);
    }

}
