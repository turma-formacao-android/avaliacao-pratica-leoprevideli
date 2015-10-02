package br.com.cast.turmaformacao.avaliacaopratica.model.persistence.phone;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import br.com.cast.turmaformacao.avaliacaopratica.model.entities.Phone;
import br.com.cast.turmaformacao.avaliacaopratica.model.persistence.DatabaseHelper;

public class PhoneRepository {

    public static void save(Phone phone) {
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        ContentValues values = PhoneContract.getContentValues(phone);
        if (phone.getId() == null) {
            db.insert(PhoneContract.TABLE, null, values);
        } else {
            String where = PhoneContract.ID + " = ? ";
            String[] params = {phone.getId().toString()};
            db.update(PhoneContract.TABLE, values, where, params);
        }

        db.close();
        databaseHelper.close();
    }

    public static List<Phone> getByContactId(long contactId) {
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        String where = PhoneContract.CONTACT_ID + " = ? ";
        String params[] = {String.valueOf(contactId)};

        Cursor cursor = db.query(PhoneContract.TABLE, PhoneContract.COLUMNS, where, params, null, null, null);
        List<Phone> contacts = PhoneContract.getPhones(cursor);

        db.close();
        databaseHelper.close();
        return contacts;
    }

    public static void deleteAllByContactId(long contactId) {
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase writer = databaseHelper.getWritableDatabase();
        String[] whereArgs = {String.valueOf(contactId)};
        writer.delete(PhoneContract.TABLE, PhoneContract.CONTACT_ID + " = ?", whereArgs);
        writer.close();
        databaseHelper.close();
    }
}
