package br.com.cast.turmaformacao.avaliacaopratica.model.entities;

import android.os.Parcel;
import android.os.Parcelable;

public class Phone implements Parcelable {

    private Long id;

    private String phoneNumber;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Phone)) return false;

        Phone phone = (Phone) o;

        if (getId() != null ? !getId().equals(phone.getId()) : phone.getId() != null) return false;
        return !(getPhoneNumber() != null ? !getPhoneNumber().equals(phone.getPhoneNumber()) : phone.getPhoneNumber() != null);

    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getPhoneNumber() != null ? getPhoneNumber().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "id=" + id +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.phoneNumber);
    }

    public Phone() {
    }

    protected Phone(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.phoneNumber = in.readString();
    }

    public static final Parcelable.Creator<Phone> CREATOR = new Parcelable.Creator<Phone>() {
        public Phone createFromParcel(Parcel source) {
            return new Phone(source);
        }

        public Phone[] newArray(int size) {
            return new Phone[size];
        }
    };
}
