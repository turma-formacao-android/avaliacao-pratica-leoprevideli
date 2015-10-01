package br.com.cast.turmaformacao.avaliacaopratica.model.entities;

import android.os.Parcel;
import android.os.Parcelable;

public class SocialNetwork implements Parcelable {

    private Long id;

    private String name;

    private String url;

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SocialNetwork)) return false;

        SocialNetwork that = (SocialNetwork) o;

        if (getId() != null ? !getId().equals(that.getId()) : that.getId() != null) return false;
        if (getName() != null ? !getName().equals(that.getName()) : that.getName() != null)
            return false;
        return !(getUrl() != null ? !getUrl().equals(that.getUrl()) : that.getUrl() != null);

    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getUrl() != null ? getUrl().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "SocialNetwork{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
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
        dest.writeString(this.url);
    }

    public SocialNetwork() {
    }

    protected SocialNetwork(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.name = in.readString();
        this.url = in.readString();
    }

    public static final Parcelable.Creator<SocialNetwork> CREATOR = new Parcelable.Creator<SocialNetwork>() {
        public SocialNetwork createFromParcel(Parcel source) {
            return new SocialNetwork(source);
        }

        public SocialNetwork[] newArray(int size) {
            return new SocialNetwork[size];
        }
    };
}
