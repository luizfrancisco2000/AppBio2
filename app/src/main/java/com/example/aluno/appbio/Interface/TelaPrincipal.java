package com.example.aluno.appbio.Interface;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.Switch;
import android.widget.Toast;

import com.example.aluno.appbio.Interface.ModoEstudo.MostrarAssuntos;
import com.example.aluno.appbio.Interface.ModoQuiz.ConfigurarModoQuiz;
import com.example.aluno.appbio.Model.Assunto;
import com.example.aluno.appbio.R;
import com.example.aluno.appbio.Repository.AssuntoRepository;
import com.example.aluno.appbio.Repository.ConteudoRepository;
import com.example.aluno.appbio.Repository.PerguntaRepository;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;

public class TelaPrincipal extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.drawerLayout)
    DrawerLayout layout;

    @BindView(R.id.navView)
    NavigationView navigationView;

    @BindView(R.id.switch_tela_bloqueio)
    Switch switch_tela_bloqueio;

    private boolean ativo_tela_bloqueio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_principal);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, layout, toolbar, R.string.abrir_menu, R.string.fechar_menu);
        layout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        Log.e("POPULAR BANCO", "Chamando função");
        popularBanco();


        try {
            SharedPreferences sharedPreferences = getSharedPreferences("prefs", Context.MODE_PRIVATE);
            ativo_tela_bloqueio = sharedPreferences.getBoolean("ativo_tela_bloqueio", false);
            switch_tela_bloqueio.setChecked(ativo_tela_bloqueio);
        } catch (Exception e) {
            Log.e("ERRO INTENT BLOQUEIO", e.getMessage());
            e.printStackTrace();
        }

    }

    @OnCheckedChanged(R.id.switch_tela_bloqueio)
    public void ativarTelaBloqueio(boolean isChecked) {

        ativo_tela_bloqueio = isChecked;

        try {
            SharedPreferences preferences = getSharedPreferences("prefs", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("ativo_tela_bloqueio", ativo_tela_bloqueio);
            editor.apply();

            if (ativo_tela_bloqueio)
                Toast.makeText(this, "Um conceito será exibido sempre que a tela for desbloqueada!", Toast.LENGTH_LONG);
            else
                Toast.makeText(this, "Desativado!", Toast.LENGTH_LONG);

        } catch (Exception e) {
            Log.e("ERRO INTENT BLOQUEIO", e.getMessage());
            e.printStackTrace();
        }
    }

    private void popularBanco() {
        boolean res = true;

        try {
            res = new IsBancoVazio().execute().get();
        } catch (Exception e) {
            Log.e("ERRO TESTE", e.getMessage());
        }

        if (res) {
            Log.e("TESTE BANCO", "POPULANDO BANCO");
            new PopularBanco().execute(TelaPrincipal.this);
        }
    }

    @OnClick(R.id.btnModoEstudo)
    public void modoEstudo() {
        Intent i = new Intent(this, MostrarAssuntos.class);
        startActivity(i);
    }

    @OnClick(R.id.btnModoQuiz)
    public void modoQuiz() {
        Intent i = new Intent(this, ConfigurarModoQuiz.class);
        startActivity(i);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_item_inicio: {
                break;
            }
            case R.id.nav_item_configuracoes: {
                Intent i = new Intent(this, Configuracoes.class);
                startActivity(i);
                finish();
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

    private class PopularBanco extends AsyncTask<Context, Void, Void> {
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            progressDialog = new ProgressDialog(TelaPrincipal.this);
            progressDialog.setMessage("Iniciando banco de dados!");
            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progressDialog.setMax(100);
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Context... contexts) {
            try {
                Log.e("POPULAR BANCO", "Salvando assuntos");
                List<Assunto> assuntos = AssuntoRepository.populaBanco(TelaPrincipal.this);
                progressDialog.setProgress(10);
                for (Assunto a : assuntos) {
                    Log.e("POPULAR BANCO", "SALVANDO " + a.getNome() + " id: " + a.getId());
                    ConteudoRepository.populaBanco(a, TelaPrincipal.this);
                    progressDialog.setProgress(progressDialog.getProgress() * 2);
                }
                PerguntaRepository.populaBanco(TelaPrincipal.this);
                progressDialog.setProgress(100);
            } catch (Exception e) {
                Log.e("ERRO NO CADASTRO", e.getMessage());
                progressDialog.dismiss();
                cancel(true);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            progressDialog.dismiss();
        }
    }

    private class IsBancoVazio extends AsyncTask<Void, Void, Boolean> {
        @Override
        protected Boolean doInBackground(Void... voids) {
            try {
                List<Assunto> assuntos = AssuntoRepository.getAll(TelaPrincipal.this);
                if (assuntos.size() > 0) {
                    return false;
                } else {
                    Log.e("ASSUNTO TESTE NULL", "" + assuntos.size());
                    return true;
                }
            } catch (Exception e) {
                Log.e("ERRO TESTE BANCO", e.getMessage());
                return true;
            }
        }
    }
}
