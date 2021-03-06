package br.com.cast.turmaformacao.avaliacaopratica.model.persistence.contact;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import br.com.cast.turmaformacao.avaliacaopratica.model.entities.Address;
import br.com.cast.turmaformacao.avaliacaopratica.model.entities.Contact;

public class ContactContract {

    public static final String TABLE = "contact";
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String ADDRESS_ID = "address_id";

    public static final String[] COLUMNS = { ID, NAME,ADDRESS_ID };

    public static String getCreateTableScript(){
        StringBuilder sql = new StringBuilder();
        sql.append(" CREATE TABLE ");
        sql.append(TABLE);
        sql.append(" ( ");
        sql.append(ID + " INTEGER PRIMARY KEY, ");
        sql.append(NAME + " TEXT NOT NULL, ");
        sql.append(ADDRESS_ID + " INTEGER NOT NULL ");
        sql.append(" ); ");
        return sql.toString();
    }

    public static ContentValues getContentValues(Contact contact){
        ContentValues values = new ContentValues();
        values.put(ContactContract.ID, contact.getId());
        values.put(ContactContract.NAME, contact.getName());
        values.put(ContactContract.ADDRESS_ID, contact.getAddress().getId());
        return  values;
    }

    public static Contact getContact(Cursor cursor) {
        if (!cursor.isBeforeFirst() || cursor.moveToNext()) {
            Contact contact = new Contact();
            contact.setId(cursor.getLong(cursor.getColumnIndex(ID)));
            contact.setName(cursor.getString(cursor.getColumnIndex(NAME)));
            if(contact.getAddress() == null){
                contact.setAddress(new Address());
            }
            contact.getAddress().setId(cursor.getLong(cursor.getColumnIndex(ID)));
            return contact;
        }
        return null;
    }

    public static List<Contact> getContacts(Cursor cursor){
        ArrayList<Contact> values = new ArrayList<>();
        while (cursor.moveToNext()) {
            values.add(getContact(cursor));
        }
        return values;
    }
}
