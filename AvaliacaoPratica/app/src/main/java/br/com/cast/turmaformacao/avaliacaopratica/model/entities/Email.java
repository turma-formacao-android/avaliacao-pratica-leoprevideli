package br.com.cast.turmaformacao.avaliacaopratica.model.entities;


import android.os.Parcel;
import android.os.Parcelable;

public class Email implements Parcelable {

    private Long id;

    private String emailAddress;

    private Long contactId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public Long getContactId() {
        return contactId;
    }

    public void setContactId(Long contactId) {
        this.contactId = contactId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Email)) return false;

        Email email = (Email) o;

        if (getId() != null ? !getId().equals(email.getId()) : email.getId() != null) return false;
        if (getEmailAddress() != null ? !getEmailAddress().equals(email.getEmailAddress()) : email.getEmailAddress() != null)
            return false;
        return !(getContactId() != null ? !getContactId().equals(email.getContactId()) : email.getContactId() != null);

    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getEmailAddress() != null ? getEmailAddress().hashCode() : 0);
        result = 31 * result + (getContactId() != null ? getContactId().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Email{" +
                "id=" + id +
                ", emailAddress='" + emailAddress + '\'' +
                ", contactId=" + contactId +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.emailAddress);
        dest.writeValue(this.contactId);
    }

    public Email() {
    }

    protected Email(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.emailAddress = in.readString();
        this.contactId = (Long) in.readValue(Long.class.getClassLoader());
    }

    public static final Creator<Email> CREATOR = new Creator<Email>() {
        public Email createFromParcel(Parcel source) {
            return new Email(source);
        }

        public Email[] newArray(int size) {
            return new Email[size];
        }
    };
}
