package br.com.cast.turmaformacao.avaliacaopratica.model.persistence.address;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import br.com.cast.turmaformacao.avaliacaopratica.model.entities.Address;

public class AddressContract {

    public static final String TABLE = "address";
    public static final String ID = "id";
    public static final String ZIPCODE = "zipcode";
    public static final String TYPE = "type";
    public static final String STREET = "street";
    public static final String NEIGHBORHOOD = "neighborhood";
    public static final String CITY = "city";
    public static final String STATE = "state";
    public static final String[] COLUMNS = { ID, ZIPCODE, TYPE, STREET, NEIGHBORHOOD, CITY, STATE };

    public static String getCreateTableScript(){
        StringBuilder sql = new StringBuilder();
        sql.append(" CREATE TABLE ");
        sql.append(TABLE);
        sql.append(" ( ");
        sql.append(ID + " INTEGER PRIMARY KEY, ");
        sql.append(ZIPCODE + " TEXT NOT NULL, ");
        sql.append(TYPE + " TEXT NOT NULL, ");
        sql.append(STREET + " TEXT NOT NULL, ");
        sql.append(NEIGHBORHOOD + " TEXT NOT NULL, ");
        sql.append(CITY + " TEXT NOT NULL, ");
        sql.append(STATE + " TEXT NOT NULL ");
        sql.append(" ); ");
        return sql.toString();
    }

    public static ContentValues getContentValues(Address address){
        ContentValues values = new ContentValues();
        values.put(AddressContract.ID, address.getId());
        values.put(AddressContract.ZIPCODE, address.getZipCode());
        values.put(AddressContract.TYPE, address.getType());
        values.put(AddressContract.STREET, address.getStreet());
        values.put(AddressContract.NEIGHBORHOOD, address.getNeighborhood());
        values.put(AddressContract.CITY, address.getCity());
        values.put(AddressContract.STATE, address.getState());
        return  values;
    }

    public static Address getAddress(Cursor cursor) {
        if (!cursor.isBeforeFirst() || cursor.moveToNext()) {
            Address address = new Address();
            address.setId((cursor.getLong(cursor.getColumnIndex(ID))));
            address.setZipCode(cursor.getString(cursor.getColumnIndex(ZIPCODE)));
            address.setType(cursor.getString(cursor.getColumnIndex(TYPE)));
            address.setStreet(cursor.getString(cursor.getColumnIndex(STREET)));
            address.setNeighborhood(cursor.getString(cursor.getColumnIndex(NEIGHBORHOOD)));
            address.setCity(cursor.getString(cursor.getColumnIndex(CITY)));
            address.setState(cursor.getString(cursor.getColumnIndex(STATE)));
            return address;
        }
        return null;
    }

    public static List<Address> getAddresses(Cursor cursor){
        ArrayList<Address> values = new ArrayList<>();
        while (cursor.moveToNext()) {
            values.add(getAddress(cursor));
        }
        return values;
    }

}
