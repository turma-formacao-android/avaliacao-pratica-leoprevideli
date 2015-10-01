package br.com.cast.turmaformacao.avaliacaopratica.model.entities;


import android.os.Parcel;
import android.os.Parcelable;

public class Email implements Parcelable {

    private Long id;

    private String emailAddress;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Email)) return false;

        Email email = (Email) o;

        if (getId() != null ? !getId().equals(email.getId()) : email.getId() != null) return false;
        return !(getEmailAddress() != null ? !getEmailAddress().equals(email.getEmailAddress()) : email.getEmailAddress() != null);

    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getEmailAddress() != null ? getEmailAddress().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Email{" +
                "id=" + id +
                ", emailAddress='" + emailAddress + '\'' +
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
    }

    public Email() {
    }

    protected Email(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.emailAddress = in.readString();
    }

    public static final Parcelable.Creator<Email> CREATOR = new Parcelable.Creator<Email>() {
        public Email createFromParcel(Parcel source) {
            return new Email(source);
        }

        public Email[] newArray(int size) {
            return new Email[size];
        }
    };
}
