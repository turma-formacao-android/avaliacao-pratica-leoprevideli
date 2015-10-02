package br.com.cast.turmaformacao.avaliacaopratica.controller.adapters;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.cast.turmaformacao.avaliacaopratica.R;
import br.com.cast.turmaformacao.avaliacaopratica.model.entities.Contact;

public class ContactListAdapter extends BaseAdapter{

    private List<Contact> contactList;
    private Activity context;

    public ContactListAdapter(Activity context) {
        this.context = context;
        this.contactList = new ArrayList<>();
    }

    public void setDataValues(List<Contact> values) {
        contactList.clear();
        contactList.addAll(values);
    }

    @Override
    public int getCount() {
        return contactList.size();
    }

    @Override
    public Contact getItem(int position) {
        return contactList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Contact contact = getItem(position);
        View contactListItemView = context.getLayoutInflater().inflate(R.layout.list_item_contact, parent, false);

        TextView contact_item_tv_name = (TextView) contactListItemView.findViewById(R.id.contact_item_tv_name);
        contact_item_tv_name.setText(contact.getName());

        return contactListItemView;
    }
}
