package com.example.aluno.appbio.Interface;

import android.app.ProgressDialog;
import android.content.Context;
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

import com.example.aluno.appbio.Model.Assunto;
import com.example.aluno.appbio.R;
import com.example.aluno.appbio.Repository.AssuntoRepository;
import com.example.aluno.appbio.Repository.ConteudoRepository;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TelaPrincipal extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.drawerLayout)
    DrawerLayout layout;

    @BindView(R.id.navView)
    NavigationView navigationView;

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

        popularBanco();
    }

    private void popularBanco() {
        new PopularBanco().execute(TelaPrincipal.this);
    }

    @OnClick(R.id.btnModoEstudo)
    public void modoEstudo() {
        Intent i = new Intent(this, ModoEstudo.class);
        startActivity(i);
    }

    @OnClick(R.id.btnModoQuiz)
    public void modoQuiz() {
        Intent i = new Intent(this, ModoQuiz.class);
        startActivity(i);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_item_inicio: {
                break;
            }
            case R.id.nav_item_conteudo_programatico: {
                Intent i = new Intent(this, ConteudoProgramatico.class);
                startActivity(i);
                break;
            }
            case R.id.nav_item_configuracoes: {
                Intent i = new Intent(this, Configuracoes.class);
                startActivity(i);
                break;
            }
            case R.id.nav_item_ajuda: {
                Intent i = new Intent(this, Ajuda.class);
                startActivity(i);
                break;
            }
            case R.id.nav_item_legal: {
                Intent i = new Intent(this, Legal.class);
                startActivity(i);
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
        protected Void doInBackground(Context... contexts) {
            try {
                List<Assunto> assuntos = AssuntoRepository.populaBanco(TelaPrincipal.this);

                for (Assunto a : assuntos) {
                    ConteudoRepository.populaBanco(a, TelaPrincipal.this);
                }

            } catch (Exception e) {
                Log.i("ERRO NO CADASTRO", e.getMessage());

                progressDialog.dismiss();
                cancel(true);
            }

            return null;        }

        @Override
        protected void onPreExecute() {
            progressDialog = new ProgressDialog(TelaPrincipal.this);
            progressDialog.setMessage("Iniciando banco de dados!");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            progressDialog.dismiss();
        }
    }
}
