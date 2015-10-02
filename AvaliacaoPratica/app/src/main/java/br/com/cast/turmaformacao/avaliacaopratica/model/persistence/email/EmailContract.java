package br.com.cast.turmaformacao.avaliacaopratica.model.persistence.email;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import br.com.cast.turmaformacao.avaliacaopratica.model.entities.Email;

public class EmailContract {

    public static final String TABLE = "email";
    public static final String ID = "id";
    public static final String EMAIL_ADDRESS = "email_address";
    public static final String CONTACT_ID = "contact_id";

    public static final String[] COLUMNS = { ID, EMAIL_ADDRESS, CONTACT_ID };

    public static String getCreateTableScript(){
        StringBuilder sql = new StringBuilder();
        sql.append(" CREATE TABLE ");
        sql.append(TABLE);
        sql.append(" ( ");
        sql.append(ID + " INTEGER PRIMARY KEY, ");
        sql.append(EMAIL_ADDRESS + " TEXT NOT NULL, ");
        sql.append(CONTACT_ID + " INTEGER NOT NULL ");
        sql.append(" ); ");
        return sql.toString();
    }

    public static ContentValues getContentValues(Email email){
        ContentValues values = new ContentValues();
        values.put(EmailContract.ID, email.getId());
        values.put(EmailContract.EMAIL_ADDRESS, email.getEmailAddress());
        values.put(EmailContract.CONTACT_ID, email.getContactId());
        return values;
    }

    public static Email getEmail(Cursor cursor) {
        Email email = new Email();
        email.setId(cursor.getLong(cursor.getColumnIndex(EmailContract.ID)));
        email.setEmailAddress(cursor.getString(cursor.getColumnIndex(EmailContract.EMAIL_ADDRESS)));
        email.setContactId(cursor.getLong(cursor.getColumnIndex(EmailContract.CONTACT_ID)));
        return email;
    }

    public static List<Email> getEmails(Cursor cursor) {
        ArrayList<Email> emails = new ArrayList<>();
        while (cursor.moveToNext()) {
            emails.add(getEmail(cursor));
        }
        return emails;
    }

}
