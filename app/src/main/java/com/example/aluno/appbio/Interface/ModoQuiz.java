package com.example.aluno.appbio.Interface;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import com.example.aluno.appbio.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ModoQuiz extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.drawerLayout)
    DrawerLayout layout;

    @BindView(R.id.navView)
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modo_quiz);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, layout, toolbar, R.string.abrir_menu, R.string.fechar_menu);
        layout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        comecarQuiz();
    }

    private void comecarQuiz(){

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_item_inicio: {
                Toast.makeText(this, R.string.inicio, Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.nav_item_conteudo_programatico: {
                Toast.makeText(this, R.string.conteudo_programatico, Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.nav_item_configuracoes: {
                Toast.makeText(this, "Configurações", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.nav_item_ajuda: {
                Toast.makeText(this, "Ajuda", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.nav_item_legal: {
                Toast.makeText(this, "Legal", Toast.LENGTH_SHORT).show();
                break;
            }
            default: {
                Toast.makeText(this, "Menu Default", Toast.LENGTH_SHORT).show();
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
