package com.example.aluno.appbio.Interface.ModoEstudo;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aluno.appbio.Model.Lamina;
import com.example.aluno.appbio.R;
import com.example.aluno.appbio.Repository.LaminaRepository;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LaminaSelecionada extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.lbl_num_lamina)
    public TextView lbl_num_lamina;

    @BindView(R.id.lbl_local)
    public TextView lbl_local;

    @BindView(R.id.lbl_descricao)
    public TextView lbl_descricao;

    @BindView(R.id.img_lamina)
    ImageView img_lamina;

    private int posicao;
    private Lamina lamina;
    private List<Lamina> laminas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lamina_selecionada);

        long id_lamina = 0;

        try {
            Intent intent = getIntent();
            id_lamina = intent.getLongExtra("lamina", 0);
            laminas = new ProcurarLaminas().execute().get();
        } catch (Exception e) {
            Toast.makeText(this, "Não foi possível exibir a lâmina selecionada!", Toast.LENGTH_LONG).show();
            Log.e("ERRO INTENT", e.getMessage());
            e.printStackTrace();
            finish();
        }

        for (int i = 0; i < laminas.size(); i++) {
            if (laminas.get(i).getId() == id_lamina) {
                posicao = i;
            }
        }

        ButterKnife.bind(this);

        toolbar.setTitle("Lâminas");
        setSupportActionBar(toolbar);

        popularTela();
    }


    private void popularTela() {
        Lamina lamina = laminas.get(posicao);

        lbl_num_lamina.setText("Lâmina: " + lamina.getNum_lamina());
        lbl_local.setText("Local: " + lamina.getLocal());
        lbl_descricao.setText(lamina.getDescricao());

        img_lamina.setImageResource((int) lamina.getId_img());
    }

    @OnClick(R.id.btn_anterior)
    public void anterior() {
        if (posicao > 0) {
            posicao--;
            popularTela();
        } else {
            Toast.makeText(this, "Primeira lâmina cadastrada!", Toast.LENGTH_SHORT).show();
        }
    }


    @OnClick(R.id.btn_proximo)
    public void proximo() {
        posicao++;
        if (posicao < laminas.size()) {
            popularTela();
        } else {
            posicao--;
            Toast.makeText(this, "Última lâmina cadastrada!", Toast.LENGTH_SHORT).show();
        }
    }

    private class ProcurarLaminas extends AsyncTask<Void, Void, List<Lamina>> {

        @Override
        protected List<Lamina> doInBackground(Void... voids) {
            try {
                return LaminaRepository.getAll(LaminaSelecionada.this);
            } catch (Exception e) {
                Log.e("ERRO ASYNC LÂMINAS", e.getMessage());
                e.printStackTrace();
                return null;
            }
        }
    }
}
