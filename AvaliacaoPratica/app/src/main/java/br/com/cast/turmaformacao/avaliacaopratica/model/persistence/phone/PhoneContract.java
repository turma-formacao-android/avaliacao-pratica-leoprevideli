package br.com.cast.turmaformacao.avaliacaopratica.model.persistence.phone;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import br.com.cast.turmaformacao.avaliacaopratica.model.entities.Phone;

public class PhoneContract {

    public static final String TABLE = "phone";
    public static final String ID = "id";
    public static final String PHONE_NUMBER = "phone_number";
    public static final String CONTACT_ID = "contact_id";

    public static final String[] COLUMNS = { ID, PHONE_NUMBER, CONTACT_ID };

    public static String getCreateTableScript(){
        StringBuilder sql = new StringBuilder();
        sql.append(" CREATE TABLE ");
        sql.append(TABLE);
        sql.append(" ( ");
        sql.append(ID + " INTEGER PRIMARY KEY, ");
        sql.append(PHONE_NUMBER + " TEXT NOT NULL, ");
        sql.append(CONTACT_ID + " INTEGER NOT NULL ");
        sql.append(" ); ");
        return sql.toString();
    }

    public static ContentValues getContentValues(Phone phone){
        ContentValues values = new ContentValues();
        values.put(PhoneContract.ID, phone.getId());
        values.put(PhoneContract.PHONE_NUMBER, phone.getPhoneNumber());
        values.put(PhoneContract.CONTACT_ID, phone.getContactId());
        return values;
    }

    public static Phone getPhone(Cursor cursor) {
        if (!cursor.isBeforeFirst() || cursor.moveToNext()) {
            Phone phone = new Phone();
            phone.setId(cursor.getLong(cursor.getColumnIndex(PhoneContract.ID)));
            phone.setPhoneNumber(cursor.getString(cursor.getColumnIndex(PhoneContract.PHONE_NUMBER)));
            phone.setContactId(cursor.getLong(cursor.getColumnIndex(PhoneContract.CONTACT_ID)));
            return  phone;
        }
        return null;
    }

    public static List<Phone> getPhones(Cursor cursor) {
        ArrayList<Phone> phones = new ArrayList<>();
        while (cursor.moveToNext()) {
            phones.add(getPhone(cursor));
        }
        return phones;
    }

}
