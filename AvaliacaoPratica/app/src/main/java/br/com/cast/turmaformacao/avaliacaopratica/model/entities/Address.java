package br.com.cast.turmaformacao.avaliacaopratica.model.entities;


import android.os.Parcel;
import android.os.Parcelable;

public class Address implements Parcelable {

    private String zipCode;

    private String type;

    private String street;

    private String neighborhood;

    private String city;

    private String state;

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address)) return false;

        Address address = (Address) o;

        if (getZipCode() != null ? !getZipCode().equals(address.getZipCode()) : address.getZipCode() != null)
            return false;
        if (getType() != null ? !getType().equals(address.getType()) : address.getType() != null)
            return false;
        if (getStreet() != null ? !getStreet().equals(address.getStreet()) : address.getStreet() != null)
            return false;
        if (getNeighborhood() != null ? !getNeighborhood().equals(address.getNeighborhood()) : address.getNeighborhood() != null)
            return false;
        if (getCity() != null ? !getCity().equals(address.getCity()) : address.getCity() != null)
            return false;
        return !(getState() != null ? !getState().equals(address.getState()) : address.getState() != null);

    }

    @Override
    public int hashCode() {
        int result = getZipCode() != null ? getZipCode().hashCode() : 0;
        result = 31 * result + (getType() != null ? getType().hashCode() : 0);
        result = 31 * result + (getStreet() != null ? getStreet().hashCode() : 0);
        result = 31 * result + (getNeighborhood() != null ? getNeighborhood().hashCode() : 0);
        result = 31 * result + (getCity() != null ? getCity().hashCode() : 0);
        result = 31 * result + (getState() != null ? getState().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Address{" +
                "zipCode='" + zipCode + '\'' +
                ", type='" + type + '\'' +
                ", street='" + street + '\'' +
                ", neighborhood='" + neighborhood + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.zipCode);
        dest.writeString(this.type);
        dest.writeString(this.street);
        dest.writeString(this.neighborhood);
        dest.writeString(this.city);
        dest.writeString(this.state);
    }

    public Address() {
    }

    protected Address(Parcel in) {
        this.zipCode = in.readString();
        this.type = in.readString();
        this.street = in.readString();
        this.neighborhood = in.readString();
        this.city = in.readString();
        this.state = in.readString();
    }

    public static final Parcelable.Creator<Address> CREATOR = new Parcelable.Creator<Address>() {
        public Address createFromParcel(Parcel source) {
            return new Address(source);
        }

        public Address[] newArray(int size) {
            return new Address[size];
        }
    };
}
