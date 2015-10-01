package br.com.cast.turmaformacao.avaliacaopratica.model.persistence.contact;

import android.content.ContentValues;
import android.database.Cursor;

import br.com.cast.turmaformacao.avaliacaopratica.model.entities.Contact;

public class ContactContract {

    public static final String TABLE = "contact";
    public static final String ID = "id";
    public static final String NAME = "name";
//    public static final String TELEPHONE_ID = "telephone_id";
//    public static final String EMAIL_ID = "email_id";
//    public static final String SOCIALNETWORK_ID = "socialnetwork_id";
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
        return  values;
    }



}
