package br.com.cast.turmaformacao.avaliacaopratica.model.entities;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Contact implements Parcelable {

    private Long id;
    private String name;
    private List<Phone> telephones;
    private List<Email> emails;
    private List<SocialNetwork> socialNetworks;
    private Address address;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Phone> getTelephones() {
        return telephones;
    }

    public void setTelephones(List<Phone> telephones) {
        this.telephones = telephones;
    }

    public List<Email> getEmails() {
        return emails;
    }

    public void setEmails(List<Email> emails) {
        this.emails = emails;
    }

    public List<SocialNetwork> getSocialNetworks() {
        return socialNetworks;
    }

    public void setSocialNetworks(List<SocialNetwork> socialNetworks) {
        this.socialNetworks = socialNetworks;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Contact)) return false;

        Contact contact = (Contact) o;

        if (getId() != null ? !getId().equals(contact.getId()) : contact.getId() != null)
            return false;
        if (getName() != null ? !getName().equals(contact.getName()) : contact.getName() != null)
            return false;
        if (getTelephones() != null ? !getTelephones().equals(contact.getTelephones()) : contact.getTelephones() != null)
            return false;
        if (getEmails() != null ? !getEmails().equals(contact.getEmails()) : contact.getEmails() != null)
            return false;
        if (getSocialNetworks() != null ? !getSocialNetworks().equals(contact.getSocialNetworks()) : contact.getSocialNetworks() != null)
            return false;
        return !(getAddress() != null ? !getAddress().equals(contact.getAddress()) : contact.getAddress() != null);

    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getTelephones() != null ? getTelephones().hashCode() : 0);
        result = 31 * result + (getEmails() != null ? getEmails().hashCode() : 0);
        result = 31 * result + (getSocialNetworks() != null ? getSocialNetworks().hashCode() : 0);
        result = 31 * result + (getAddress() != null ? getAddress().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", telephones=" + telephones +
                ", emails=" + emails +
                ", socialNetworks=" + socialNetworks +
                ", address=" + address +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.name);
        dest.writeTypedList(telephones);
        dest.writeTypedList(emails);
        dest.writeTypedList(socialNetworks);
        dest.writeParcelable(this.address, 0);
    }

    public Contact() {
    }

    protected Contact(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.name = in.readString();
        this.telephones = in.createTypedArrayList(Phone.CREATOR);
        this.emails = in.createTypedArrayList(Email.CREATOR);
        this.socialNetworks = in.createTypedArrayList(SocialNetwork.CREATOR);
        this.address = in.readParcelable(Address.class.getClassLoader());
    }

    public static final Creator<Contact> CREATOR = new Creator<Contact>() {
        public Contact createFromParcel(Parcel source) {
            return new Contact(source);
        }

        public Contact[] newArray(int size) {
            return new Contact[size];
        }
    };
}
