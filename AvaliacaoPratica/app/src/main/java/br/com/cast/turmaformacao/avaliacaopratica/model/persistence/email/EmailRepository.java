package br.com.cast.turmaformacao.avaliacaopratica.model.persistence.email;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import br.com.cast.turmaformacao.avaliacaopratica.model.entities.Email;
import br.com.cast.turmaformacao.avaliacaopratica.model.persistence.DatabaseHelper;

public class EmailRepository {

    public static void save(Email email) {
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        ContentValues values = EmailContract.getContentValues(email);
        if (email.getId() == null) {
            db.insert(EmailContract.TABLE, null, values);
        } else {
            String where = EmailContract.ID + " = ? ";
            String[] params = {email.getId().toString()};
            db.update(EmailContract.TABLE, values, where, params);
        }

        db.close();
        databaseHelper.close();
    }

    public static List<Email> getByContactId(long contactId) {
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        String where = EmailContract.CONTACT_ID + " = ? ";
        String params[] = {String.valueOf(contactId)};

        Cursor cursor = db.query(EmailContract.TABLE, EmailContract.COLUMNS, where, params, null, null, null);
        List<Email> contacts = EmailContract.getEmails(cursor);

        db.close();
        databaseHelper.close();
        return contacts;
    }

    public static void deleteAllByContactId(long contactId) {
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase writer = databaseHelper.getWritableDatabase();
        String[] whereArgs = {String.valueOf(contactId)};
        writer.delete(EmailContract.TABLE, EmailContract.CONTACT_ID + " = ?", whereArgs);
        writer.close();
        databaseHelper.close();
    }
}
