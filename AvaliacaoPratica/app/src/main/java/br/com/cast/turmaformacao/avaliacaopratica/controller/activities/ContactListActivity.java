package br.com.cast.turmaformacao.avaliacaopratica.controller.activities;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.List;

import br.com.cast.turmaformacao.avaliacaopratica.R;
import br.com.cast.turmaformacao.avaliacaopratica.controller.adapters.ContactListAdapter;
import br.com.cast.turmaformacao.avaliacaopratica.controller.asynctasks.DeleteContactTask;
import br.com.cast.turmaformacao.avaliacaopratica.controller.asynctasks.GetAllContactsTask;
import br.com.cast.turmaformacao.avaliacaopratica.model.entities.Contact;
import br.com.cast.turmaformacao.avaliacaopratica.model.services.AddressBusinessService;
import br.com.cast.turmaformacao.avaliacaopratica.model.services.ContactBusinessService;

public class ContactListActivity extends AppCompatActivity {

    private Contact contact;
    private ListView contact_list_lv;
    private SearchView searchViewContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);

        bindContactList();
        searchViewContact = (SearchView)findViewById(R.id.searchViewContact);
        bindSearchView();
    }

    private void bindSearchView() {
        searchViewContact.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                searchList();
                return true;
            }
        });
    }

    @Override
    protected void onResume() {
        new UpdateContactList().execute();
        super.onResume();
    }

    private void updateList(List<Contact> contacts) {
        ContactListAdapter adapter = (ContactListAdapter) contact_list_lv.getAdapter();
        if (adapter == null) {
            adapter = new ContactListAdapter(this);
            contact_list_lv.setAdapter(adapter);
        }
        adapter.setDataValues(contacts);
        adapter.notifyDataSetChanged();
    }

    private void bindContactList() {
        contact_list_lv = (ListView) findViewById(R.id.contact_list_lv);
        registerForContextMenu(contact_list_lv);
        contact_list_lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                ContactListAdapter adapter = (ContactListAdapter) contact_list_lv.getAdapter();
                contact = adapter.getItem(position);
                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_contact_list, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_list_add:
                onMenuAddClick();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.menu_context_contact_list, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_delete:
                onMenuDeleteClick();
                break;
            case R.id.menu_edit:
                onMenuEditClick();
                break;
        }
        return super.onContextItemSelected(item);
    }

    private void onMenuDeleteClick() {
        new AlertDialog.Builder(ContactListActivity.this)
                .setTitle(getString(R.string.delete_title))
                .setMessage(getString(R.string.confirm_delete))
                .setPositiveButton(getString(R.string.lbl_yes),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                new DeleteContactTask(){
                                    private ProgressDialog progressDialog;
                                    @Override
                                    protected void onPreExecute() {
                                        super.onPreExecute();
                                        progressDialog = new ProgressDialog(ContactListActivity.this);
                                        progressDialog.setMessage("Deletting contact...");
                                        progressDialog.show();
                                    }

                                    @Override
                                    protected void onPostExecute(Void param) {
                                        super.onPostExecute(param);
                                        new UpdateContactList().execute();
                                        progressDialog.dismiss();
                                    }
                                }.execute(contact);
                                contact = null;
                                String message = "Successfully deleted.";
                                new GetAllContactsTask().execute();
                                Toast.makeText(ContactListActivity.this, message, Toast.LENGTH_LONG).show();
                            }
                        })
                .setNeutralButton(getString(R.string.lbl_no), null)
                .create()
                .show();
    }



    private void onMenuEditClick() {
        Intent goToContactForm = new Intent(ContactListActivity.this, ContactFormActivity.class);
        bindClientAddress();
        goToContactForm.putExtra(ContactFormActivity.PARAM_CONTACT, contact);
        startActivity(goToContactForm);
    }

    private void onMenuAddClick() {
        Intent goToContactFormActivity = new Intent(ContactListActivity.this, ContactFormActivity.class);
        startActivity(goToContactFormActivity);
    }

    private void bindClientAddress(){
        if(contact.getAddress() != null){
            contact.setAddress(AddressBusinessService.getById(contact.getAddress().getId()));
        }
    }

    private class UpdateContactList extends GetAllContactsTask {
        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(ContactListActivity.this);
            progressDialog.setMessage("Searching contacts...");
            progressDialog.show();
        }

        @Override
        protected void onPostExecute(List<Contact> contacts) {
            super.onPostExecute(contacts);
            updateList(contacts);
            progressDialog.dismiss();
        }
    }

    private void searchList(){
        if(searchViewContact.getQuery().toString().isEmpty()){
            new UpdateContactList().execute();
        }
        else{
            updateList(ContactBusinessService.getAllByName(searchViewContact.getQuery().toString()));
        }
    }

}
