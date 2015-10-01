package br.com.cast.turmaformacao.avaliacaopratica.controller.activities;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import br.com.cast.turmaformacao.avaliacaopratica.R;

public class ContactListActivity extends AppCompatActivity {

    ListView contact_list_lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);

        bindContactList();
    }

    private void bindContactList() {
        contact_list_lv = (ListView) findViewById(R.id.contact_list_lv);
    }

}
