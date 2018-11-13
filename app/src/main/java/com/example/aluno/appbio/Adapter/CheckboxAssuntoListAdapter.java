package com.example.aluno.appbio.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

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
        return assuntos.get(arg0);
    }

    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return assuntos.get(position).getId();
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        final Assunto assunto = assuntos.get(position);

        View view = LayoutInflater.from(mContext).inflate(R.layout.checkbox_layout, null);

        CheckBox checkBox = view.findViewById(R.id.checkbox1);
        TextView textView = view.findViewById(R.id.lbl_assunto);

        if (assunto.isAtivo() && !checkBox.isChecked()){
            checkBox.setChecked(assunto.isAtivo());
        }

        textView.setText(assunto.getNome());

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                assunto.setAtivo(isChecked);
            }
        });


        return view;
    }
}
