package com.example.aluno.appbio.Interface;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aluno.appbio.Adapter.CheckboxAssuntoListAdapter;
import com.example.aluno.appbio.Model.Assunto;
import com.example.aluno.appbio.R;
import com.example.aluno.appbio.Repository.AssuntoRepository;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemSelected;

public class Configuracoes extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.toolbar)
    public Toolbar toolbar;

    @BindView(R.id.drawerLayout)
    public DrawerLayout layout;

    @BindView(R.id.navView)
    public NavigationView navigationView;

    @BindView(R.id.list_assuntos)
    public ListView listViewAssuntos;

    private List<Assunto> assuntos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracoes);

        ButterKnife.bind(this);

        float[] tamanhos = {getResources().getDimension(R.dimen.fonte_pequena), getResources().getDimension(R.dimen.fonte_padrao), getResources().getDimension(R.dimen.fonte_grande)};

        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, layout, toolbar, R.string.abrir_menu, R.string.fechar_menu);
        layout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        try {
            assuntos = new ProcurarAssuntos().execute().get();
        } catch (Exception e) {
            Toast.makeText(this, "Houve um erro ao buscar os assuntos!", Toast.LENGTH_SHORT).show();
            Log.e("ERRO ASSUNTOS", e.getMessage());
            e.printStackTrace();
            finish();
        }

        CheckboxAssuntoListAdapter adapter = new CheckboxAssuntoListAdapter(this, this, assuntos);
        listViewAssuntos.setAdapter(adapter);

    }



    @OnClick(R.id.btn_salvar)
    public void salvarConfiguracoes() {
        Log.e("FUNÇÃO SALVAR", assuntos.toString());

        assuntos = new ArrayList<>();

        try {

            if (listViewAssuntos != null) {
                CheckboxAssuntoListAdapter adapter = (CheckboxAssuntoListAdapter) listViewAssuntos.getAdapter();

                StringBuilder builder = new StringBuilder();

                for (int i = 0; i < adapter.getCount(); i++) {
                    Assunto assunto = (Assunto) adapter.getItem(i);
                    assuntos.add(assunto);
                }

                new AtualizarAssuntos().execute(assuntos);
            }

        } catch (Exception e) {
            Log.e("FUNÇÃO SALVAR", e.getMessage());
            e.printStackTrace();
            Toast.makeText(this, "Houve um erro ao salvar suas configurações", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_item_inicio: {
                Intent i = new Intent(this, TelaPrincipal.class);
                startActivity(i);
                finish();
                break;
            }
            case R.id.nav_item_configuracoes: {
                break;
            }
            case R.id.nav_item_legal: {
                Intent i = new Intent(this, Legal.class);
                startActivity(i);
                finish();
                break;
            }
            default: {
                break;
            }
        }
        layout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (layout.isDrawerOpen(GravityCompat.START)) {
            layout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private class ProcurarAssuntos extends AsyncTask<Void, Void, List<Assunto>> {
        @Override
        protected List<Assunto> doInBackground(Void... voids) {
            try {
                return AssuntoRepository.getAll(Configuracoes.this);
            } catch (Exception e) {
                Log.e("ERRO ASYNC ASSUNTOS", e.getMessage());
                e.printStackTrace();
                return null;
            }
        }
    }

    private class AtualizarAssuntos extends AsyncTask<List<Assunto>, Void, Void> {

        @Override
        protected Void doInBackground(List<Assunto>... lists) {
            List<Assunto> assuntos = lists[0];

            try {
                AssuntoRepository.atualizar(assuntos, Configuracoes.this);
            } catch (Exception e) {
                Log.e("ERRO ASYNC ASSUNTO", e.getMessage());
                e.printStackTrace();
                cancel(true);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            Toast.makeText(Configuracoes.this, "Configurações salvas com sucesso!", Toast.LENGTH_LONG).show();

            Intent i = new Intent(Configuracoes.this, TelaPrincipal.class);
            startActivity(i);
            finish();
        }
    }

}
