package br.com.cast.turmaformacao.avaliacaopratica.model.persistence.contact;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.com.cast.turmaformacao.avaliacaopratica.model.entities.Contact;
import br.com.cast.turmaformacao.avaliacaopratica.model.persistence.DatabaseHelper;

public class ContactRepository {

    public static long save(Contact contact) {
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        long id = 0;
        ContentValues values = ContactContract.getContentValues(contact);
        if (contact.getId() == null) {
            id = db.insert(ContactContract.TABLE, null, values);
        } else {
            String where = ContactContract.ID + " = ? ";
            String[] params = {contact.getId().toString()};
            db.update(ContactContract.TABLE, values, where, params);
        }

        db.close();
        databaseHelper.close();
        return id;
    }

    public static List<Contact> getAll() {
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        Cursor cursor = db.query(ContactContract.TABLE, ContactContract.COLUMNS, null, null, null, null, null);
        List<Contact> contacts = ContactContract.getContacts(cursor);

        db.close();
        databaseHelper.close();
        return contacts;
    }

    public static void delete(Contact contact) {
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        String where = ContactContract.ID + " = ? ";
        String[] params = {String.valueOf(contact.getId())};
        db.delete(ContactContract.TABLE, where, params);

        db.close();
        databaseHelper.close();
    }

    public static List<Contact> getAllByName(String name){
        List<Contact> list;
        String[] whereArgs = {"%" + name + "%"};
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase reader = databaseHelper.getReadableDatabase();

        Cursor c = reader.query(ContactContract.TABLE, ContactContract.COLUMNS,
                ContactContract.NAME + " LIKE ? ", whereArgs,
                null, null, null);

        list = ContactContract.getContacts(c);
        reader.close();
        databaseHelper.close();
        return list;
    }
}
