package com.example.aluno.appbio.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aluno.appbio.Model.Lamina;
import com.example.aluno.appbio.Model.Lamina;
import com.example.aluno.appbio.R;

import java.util.List;

public class LaminaListAdapter extends BaseAdapter {
    private Activity activity;
    private Context mContext;
    private List<Lamina> laminas;

    public LaminaListAdapter(Activity a, Context context, List<Lamina> laminas) {
        activity = a;
        mContext = context;
        this.laminas = laminas;

    }

    public int getCount() {
        // TODO Auto-generated method stub
        return laminas.size();
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

        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View vi = convertView;
        if (convertView == null)
            vi = inflater.inflate(R.layout.icone_texto_layout, parent, false);

        TextView nome = (TextView) vi.findViewById(R.id.txtTitle); // title
        ImageView icone = (ImageView) vi.findViewById(R.id.imgIcon); // thumb image

        nome.setText("Lâmina " + laminas.get(position).getNum_lamina());
        icone.setImageResource(R.drawable.ic_point_right);
        return vi;
    }
}
