package br.com.cast.turmaformacao.avaliacaopratica.model.persistence.address;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import br.com.cast.turmaformacao.avaliacaopratica.model.entities.Address;
import br.com.cast.turmaformacao.avaliacaopratica.model.persistence.DatabaseHelper;

public class AddressRepository {

    public static Long save(Address address) {
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        long id = 0;
        ContentValues values = AddressContract.getContentValues(address);
        if (address.getId() == null) {
            id = db.insert(AddressContract.TABLE, null, values);
        } else {
            String where = AddressContract.ID + " = ? ";
            String[] params = {address.getId().toString()};
            db.update(AddressContract.TABLE, values, where, params);
        }

        db.close();
        databaseHelper.close();
        return id;
    }

    public static Address getByZipCode(String zipCode) {
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        String where = AddressContract.ZIPCODE + " = ? ";
        String[] params = {String.valueOf(zipCode)};

        Cursor cursor = db.query(AddressContract.TABLE, AddressContract.COLUMNS, where, params, null, null, null);
        Address address = AddressContract.getAddress(cursor);

        db.close();
        databaseHelper.close();
        return address;
    }

    public static Address getById(long id){
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        String where = AddressContract.ID + " = ? ";
        String[] params = {String.valueOf(id)};

        Cursor cursor = db.query(AddressContract.TABLE, AddressContract.COLUMNS, where, params, null, null, null);
        Address returnAddress = AddressContract.getAddress(cursor);

        db.close();
        databaseHelper.close();
        return returnAddress;
    }

}
