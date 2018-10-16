package com.example.aluno.appbio.Interface;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.aluno.appbio.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TelaPrincipal extends AppCompatActivity {

    private String[] opcoes;

    @BindView(R.id.drawer_layout)
    DrawerLayout menuLateral;

    @BindView(R.id.left_drawer)
    ListView menuList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_principal);

        ButterKnife.bind(this);
        opcoes = getResources().getStringArray(R.array.opcoes);

        menuList.setAdapter(new ArrayAdapter<String>(this, R.layout.drawer_list_item, opcoes));
    }
}
