package br.com.cast.turmaformacao.avaliacaopratica.controller.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import br.com.cast.turmaformacao.avaliacaopratica.R;
import br.com.cast.turmaformacao.avaliacaopratica.model.entities.Address;
import br.com.cast.turmaformacao.avaliacaopratica.model.entities.Contact;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_form);
        initContact();

        bindEditTexts();
    }

    private void initContact() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.contact = (Contact) extras.getParcelable(PARAM_CONTACT);
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
    }

    private void bindEditTexts(){
        bindEditTextName();
        bindEditTextZipcode();
        bindEditTextAddressType();
        bindEditTextAddressStreet();
        bindEditTextAddressNeighborhood();
        bindEditTextAddressCity();
        bindEditTextAddressState();
    }

    private void bindEditTextName() {
        contact_form_et_name = (EditText) findViewById(R.id.contact_form_et_name);
        contact_form_et_name.setText(contact.getName() == null ? "" : contact.getName());
    }

    private void bindEditTextZipcode() {
        contact_form_et_zipcode = (EditText) findViewById(R.id.contact_form_et_zipcode);
        contact_form_et_zipcode.setText(contact.getAddress().getZipCode() == null ? "" : contact.getAddress().getZipCode());
    }

    private void bindEditTextAddressType() {
        contact_form_et_address_type = (EditText) findViewById(R.id.contact_form_et_address_type);
        contact_form_et_address_type.setText(contact.getAddress().getType() == null ? "" : contact.getAddress().getType());
    }

    private void bindEditTextAddressStreet() {
        contact_form_et_address_street = (EditText) findViewById(R.id.contact_form_et_address_street);
        contact_form_et_address_street.setText(contact.getAddress().getStreet() == null ? "" : contact.getAddress().getStreet());
    }

    private void bindEditTextAddressNeighborhood() {
        contact_form_et_address_neighborhood = (EditText) findViewById(R.id.contact_form_et_address_neighborhood);
        contact_form_et_address_neighborhood.setText(contact.getAddress().getNeighborhood() == null ? "" : contact.getAddress().getNeighborhood());
    }

    private void bindEditTextAddressCity() {
        contact_form_et_address_city = (EditText) findViewById(R.id.contact_form_et_address_city);
        contact_form_et_address_city.setText(contact.getAddress().getCity() == null ? "" : contact.getAddress().getCity());
    }

    private void bindEditTextAddressState() {
        contact_form_et_address_state = (EditText) findViewById(R.id.contact_form_et_address_state);
        contact_form_et_address_state.setText(contact.getAddress().getState() == null ? "" : contact.getAddress().getState());
    }
}
