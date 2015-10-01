package br.com.cast.turmaformacao.avaliacaopratica.model.persistence.email;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

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

    public static ContentValues getContentValues(String emailAddress, Long contactId){
        ContentValues values = new ContentValues();
        values.put(EmailContract.EMAIL_ADDRESS, emailAddress);
        values.put(EmailContract.CONTACT_ID, contactId);
        return values;
    }

    public static String getEmail(Cursor cursor) {
        if (!cursor.isBeforeFirst() || cursor.moveToNext()) {
            return cursor.getString(cursor.getColumnIndex(EmailContract.EMAIL_ADDRESS));
        }
        return null;
    }

    public static List<String> getEmails(Cursor cursor) {
        ArrayList<String> tasks = new ArrayList<>();
        while (cursor.moveToNext()) {
            tasks.add(getEmail(cursor));
        }
        return tasks;
    }

}
