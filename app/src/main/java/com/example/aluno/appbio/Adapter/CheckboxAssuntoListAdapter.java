package com.example.aluno.appbio.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;

import com.example.aluno.appbio.Model.Assunto;
import com.example.aluno.appbio.R;

import java.util.List;

public class CheckboxAssuntoListAdapter extends BaseAdapter {
    private Activity activity;
    private Context mContext;
    private List<Assunto> assuntos;

    public CheckboxAssuntoListAdapter(Activity a, Context context, List<Assunto> assuntos) {
        activity = a;
        mContext = context;
        this.assuntos = assuntos;

    }

    public int getCount() {
        // TODO Auto-generated method stub
        return assuntos.size();
    }

    public Object getItem(int arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        Assunto assunto = assuntos.get(position);

        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View vi = convertView;
        if (convertView == null)
            vi = inflater.inflate(R.layout.checkbox_layout, parent, false);

        CheckBox checkBox = (CheckBox) vi.findViewById(R.id.checkbox_assunto);

        checkBox.setText(assunto.getNome());

        checkBox.setChecked(assunto.isAtivo());

        return vi;
    }
}
