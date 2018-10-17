package com.example.aluno.appbio.Interface;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.aluno.appbio.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ModoEstudo extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.drawerLayout)
    DrawerLayout layout;

    @BindView(R.id.navView)
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modo_estudo);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, layout, toolbar, R.string.abrir_menu, R.string.fechar_menu);
        layout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_item_inicio: {
                Intent i = new Intent(this, TelaPrincipal.class);
                startActivity(i);
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
}
