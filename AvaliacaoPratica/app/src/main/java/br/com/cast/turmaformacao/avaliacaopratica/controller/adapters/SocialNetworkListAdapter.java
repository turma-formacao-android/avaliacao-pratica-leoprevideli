package br.com.cast.turmaformacao.avaliacaopratica.controller.adapters;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.fasterxml.jackson.databind.deser.Deserializers;

import java.util.List;

import br.com.cast.turmaformacao.avaliacaopratica.R;
import br.com.cast.turmaformacao.avaliacaopratica.model.entities.SocialNetworkEnum;

public class SocialNetworkListAdapter extends BaseAdapter{

    private Activity context;
    private SocialNetworkEnum[] socialNetworks;

    public SocialNetworkListAdapter(Activity activity) {
        this.context = activity;
        this.socialNetworks = SocialNetworkEnum.values();
    }

    @Override
    public int getCount() {
        return this.socialNetworks.length;
    }

    @Override
    public SocialNetworkEnum getItem(int position) {
        return this.socialNetworks[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = context.getLayoutInflater().inflate(R.layout.social_network_list_item, parent, false);

        TextView textView = (TextView) view.findViewById(R.id.social_network_item_tv_name);
        textView.setText(getItem(position).toString());
        return view;

    }
}
