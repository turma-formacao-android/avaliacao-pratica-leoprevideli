package br.com.cast.turmaformacao.avaliacaopratica.model.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import br.com.cast.turmaformacao.avaliacaopratica.model.persistence.address.AddressContract;
import br.com.cast.turmaformacao.avaliacaopratica.model.persistence.contact.ContactContract;
import br.com.cast.turmaformacao.avaliacaopratica.model.persistence.email.EmailContract;
import br.com.cast.turmaformacao.avaliacaopratica.model.persistence.phone.PhoneContract;
import br.com.cast.turmaformacao.avaliacaopratica.util.ApplicationUtil;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "contactmanagerdb";
    private static final int DATABASE_VERSION = 1;

    private DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static DatabaseHelper getInstance() {
        return new DatabaseHelper(ApplicationUtil.getContext());
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ContactContract.getCreateTableScript());
        db.execSQL(EmailContract.getCreateTableScript());
        db.execSQL(PhoneContract.getCreateTableScript());
        db.execSQL(AddressContract.getCreateTableScript());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
