package br.com.cast.turmaformacao.avaliacaopratica.controller.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.cast.turmaformacao.avaliacaopratica.R;
import br.com.cast.turmaformacao.avaliacaopratica.controller.asynctasks.FindAddressOnWeb;
import br.com.cast.turmaformacao.avaliacaopratica.controller.asynctasks.SaveContactTask;
import br.com.cast.turmaformacao.avaliacaopratica.model.entities.Address;
import br.com.cast.turmaformacao.avaliacaopratica.model.entities.Contact;
import br.com.cast.turmaformacao.avaliacaopratica.model.entities.Email;
import br.com.cast.turmaformacao.avaliacaopratica.model.entities.Phone;
import br.com.cast.turmaformacao.avaliacaopratica.model.services.EmailBusinessService;
import br.com.cast.turmaformacao.avaliacaopratica.model.services.PhoneBusinessService;
import br.com.cast.turmaformacao.avaliacaopratica.util.FormHelper;

public class ContactFormActivity extends AppCompatActivity{

    public static final String PARAM_CONTACT = "PARAM_CONTACT";
    private Contact contact;
    private EditText contact_form_et_name;
    private EditText contact_form_et_zipcode;
    private EditText contact_form_et_address_type;
    private EditText contact_form_et_address_street;
    private EditText contact_form_et_address_neighborhood;
    private EditText contact_form_et_address_city;
    private EditText contact_form_et_address_state;
    private EditText contact_form_et_email;
    private EditText contact_form_et_phone;
    private Button contact_form_bt_search_zipcode;
    private Button btnContact;

    private List<EditText> listPhones;
    private List<EditText> listEmails;

    ImageButton btnAddPhone;
    ImageButton btnAddEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_form);
        initContact();

        btnContact = (Button) findViewById(R.id.btnContact);
        bindEditTexts();
        bindButtonFindCep();
        bindAddPhoneButton();
        bindAddEmailButton();
        actionButton();
    }

    private void initContact() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.contact = (Contact) extras.getParcelable(PARAM_CONTACT);

            List<Phone> phones = PhoneBusinessService.getByContactId(contact.getId());
            List<Email> emails = EmailBusinessService.getByContactId(contact.getId());

            contact.setTelephones(phones);
            contact.setEmails(emails);
        }
        this.contact = this.contact == null ? new Contact() : this.contact;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_contact_form, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_form_save:
                onMenuSaveClick();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void onMenuSaveClick() {

        String requiredMessage = getString(R.string.required_message);
        if (!FormHelper.checkRequireFields(requiredMessage, contact_form_et_name, contact_form_et_zipcode, contact_form_et_address_type,
                contact_form_et_address_street, contact_form_et_address_neighborhood, contact_form_et_address_city,
                contact_form_et_address_state)) {
            bindContact();
            new SaveContactTask() {
                private ProgressDialog progressDialog;

                @Override
                protected void onPreExecute() {
                    super.onPreExecute();
                    progressDialog = new ProgressDialog(ContactFormActivity.this);
                    progressDialog.setMessage("Saving contact...");
                    progressDialog.show();
                }

                @Override
                protected void onPostExecute(Void result) {
                    super.onPostExecute(result);
                    Toast.makeText(ContactFormActivity.this, "Successfully saved!", Toast.LENGTH_LONG).show();
                    progressDialog.dismiss();
                    finish();
                }

            }.execute(contact);

        }
    }

    private void bindContact() {
        contact.setName(contact_form_et_name.getText().toString());

        Address address = new Address();
        address.setZipCode(contact_form_et_zipcode.getText().toString());
        address.setType(contact_form_et_address_type.getText().toString());
        address.setStreet(contact_form_et_address_street.getText().toString());
        address.setNeighborhood(contact_form_et_address_neighborhood.getText().toString());
        address.setCity(contact_form_et_address_city.getText().toString());
        address.setState(contact_form_et_address_state.getText().toString());

        contact.setAddress(address);

        List<Phone> phoneList = new ArrayList<>();
        List<Email> emailList = new ArrayList<>();

        for(EditText phone : listPhones){
            Phone newPhone = new Phone();
            newPhone.setPhoneNumber(phone.getText().toString().trim());
            phoneList.add(newPhone);
        }
        for(EditText email : listEmails){
            Email newEmail = new Email();
            newEmail.setEmailAddress(email.getText().toString().trim());
            emailList.add(newEmail);
        }

        contact.setTelephones(phoneList);
        contact.setEmails(emailList);


    }

    private void bindButtonFindCep() {
        contact_form_bt_search_zipcode = (Button) findViewById(R.id.contact_form_bt_search_zipcode);
        contact_form_bt_search_zipcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new FindAddressOnWeb() {
                    private ProgressDialog progressDialog;

                    @Override
                    protected void onPreExecute() {
                        super.onPreExecute();
                        progressDialog = new ProgressDialog(ContactFormActivity.this);
                        progressDialog.setMessage("Searching address...");
                        progressDialog.show();
                    }

                    @Override
                    protected void onPostExecute(Address result) {
                        super.onPostExecute(result);
                        if (result == null) {
                            Toast.makeText(ContactFormActivity.this, "Impossible to find zipcode !", Toast.LENGTH_LONG).show();
                        } else {
                            contact_form_et_address_type.setText(result.getType());
                            contact_form_et_address_street.setText(result.getStreet());
                            contact_form_et_address_neighborhood.setText(result.getNeighborhood());
                            contact_form_et_address_city.setText(result.getCity());
                            contact_form_et_address_state.setText(result.getState());
                        }
                        progressDialog.dismiss();
                    }
                }.execute(contact_form_et_zipcode.getText().toString());
            }
        });
    }

    private void bindEditTexts(){
        contact_form_et_name = (EditText) findViewById(R.id.contact_form_et_name);
        contact_form_et_name.setText(contact.getName() == null ? "" : contact.getName());

        contact_form_et_zipcode = (EditText) findViewById(R.id.contact_form_et_zipcode);
        contact_form_et_zipcode.setText(contact.getAddress() == null ? "" : contact.getAddress().getZipCode());

        contact_form_et_address_type = (EditText) findViewById(R.id.contact_form_et_address_type);
        contact_form_et_address_type.setText(contact.getAddress() == null ? "" : contact.getAddress().getType());

        contact_form_et_address_street = (EditText) findViewById(R.id.contact_form_et_address_street);
        contact_form_et_address_street.setText(contact.getAddress() == null ? "" : contact.getAddress().getStreet());

        contact_form_et_address_neighborhood = (EditText) findViewById(R.id.contact_form_et_address_neighborhood);
        contact_form_et_address_neighborhood.setText(contact.getAddress() == null ? "" : contact.getAddress().getNeighborhood());

        contact_form_et_address_city = (EditText) findViewById(R.id.contact_form_et_address_city);
        contact_form_et_address_city.setText(contact.getAddress() == null ? "" : contact.getAddress().getCity());

        contact_form_et_address_state = (EditText) findViewById(R.id.contact_form_et_address_state);
        contact_form_et_address_state.setText(contact.getAddress() == null ? "" : contact.getAddress().getState());

        listEmails = new ArrayList<>();
        contact_form_et_email = (EditText)findViewById(R.id.contact_form_et_email);
        listEmails.add(contact_form_et_email);

        listPhones = new ArrayList<>();
        contact_form_et_phone = (EditText) findViewById(R.id.contact_form_et_phone);
        listPhones.add(contact_form_et_phone);

        if(getIntent().getExtras() != null && getIntent().getExtras().size() > 0){
            for(int i = 0; i < contact.getEmails().size(); i++){
                if(i == 0){
                    contact_form_et_email.setText(contact.getEmails().get(i).getEmailAddress().toString());
                }
                else {
                    final LinearLayout layout = (LinearLayout) findViewById(R.id.contact_form_linear_email);
                    EditText newEditText = new EditText(ContactFormActivity.this);
                    newEditText.setHint(getString(R.string.lbl_email));
                    newEditText.setText(contact.getEmails() == null || contact.getEmails().size() == 0 ? "" : contact.getEmails().get(i).getEmailAddress().toString());
                    listEmails.add(newEditText);
                    layout.addView(newEditText);
                }
            }

            for(int i = 0; i < contact.getTelephones().size(); i++){
                if(i == 0){
                    contact_form_et_phone.setText(contact.getTelephones().get(i).getPhoneNumber().toString());
                }
                else {
                    final LinearLayout layout = (LinearLayout) findViewById(R.id.contact_form_linear_phone);
                    EditText newEditText = new EditText(ContactFormActivity.this);
                    newEditText.setHint(getString(R.string.lbl_phone));
                    newEditText.setText(contact.getTelephones() == null || contact.getTelephones().size() == 0 ? "" : contact.getTelephones().get(i).getPhoneNumber().toString());
                    listPhones.add(newEditText);
                    layout.addView(newEditText);
                }
            }

        }

    }

    public void actionButton(){
        btnContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent goToSOContacts = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                goToSOContacts.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
                startActivityForResult(goToSOContacts, 999);
            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 999) {
            if (resultCode == Activity.RESULT_OK) {
                try {
                    final Uri contactUri = data.getData();
                    final String[] projection = {
                            ContactsContract.CommonDataKinds.Identity.DISPLAY_NAME,
                            ContactsContract.CommonDataKinds.Phone.NUMBER
                    };
                    final Cursor cursor = getContentResolver().query(contactUri, projection, null, null, null);
                    cursor.moveToFirst();

                    contact_form_et_name.setText(cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Identity.DISPLAY_NAME)));
                    contact_form_et_phone.setText(cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)));

                    cursor.close();
                } catch (Exception e) {
                    Log.d("TAG", "Unexpected error");
                }


            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    private void bindAddPhoneButton(){
        btnAddPhone = (ImageButton) findViewById(R.id.contact_form_bt_phone);
        btnAddPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final LinearLayout layout = (LinearLayout)findViewById(R.id.contact_form_linear_phone);
                EditText newEditText = new EditText(ContactFormActivity.this);
                newEditText.setHint(getString(R.string.lbl_phone));
                newEditText.setInputType(InputType.TYPE_CLASS_PHONE);

                if(listPhones.get(listPhones.size() - 1).getText().toString().equals("")){
                    //TODO Colocar required
                }
                else{
                    layout.addView(newEditText);
                    listPhones.add(newEditText);
                }

            }
        });
    }

    private void bindAddEmailButton(){
        btnAddEmail = (ImageButton) findViewById(R.id.contact_form_bt_email);
        btnAddEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final LinearLayout layout = (LinearLayout)findViewById(R.id.contact_form_linear_email);
                EditText newEditText = new EditText(ContactFormActivity.this);
                newEditText.setHint(getString(R.string.lbl_email));

                if(listEmails.get(listEmails.size() - 1).getText().toString().equals("")){
                    //TODO Colocar required
                }
                else{
                    layout.addView(newEditText);
                    listEmails.add(newEditText);
                }

            }
        });
    }


}
