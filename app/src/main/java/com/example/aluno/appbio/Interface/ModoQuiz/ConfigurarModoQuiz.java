package com.example.aluno.appbio.Interface.ModoQuiz;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aluno.appbio.Model.Assunto;
import com.example.aluno.appbio.R;
import com.example.aluno.appbio.Repository.AssuntoRepository;
import com.example.aluno.appbio.Repository.PerguntaRepository;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemSelected;

public class ConfigurarModoQuiz extends AppCompatActivity {

    @BindView(R.id.spin_assuntos)
    public Spinner spinAssuntos;

    @BindView(R.id.spin_num_perguntas)
    public Spinner spinNumPerguntas;

    @BindView(R.id.lblResultado)
    public TextView txtResultado;

    private List<Assunto> assuntos;

    private static final int REQUEST_CODE_QUIZ = 1;
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String KEY_PONTUACAO = "keyPontuacao";
    private int maiorPontuacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configura_modo_quiz);

        ButterKnife.bind(this);

        try {
            assuntos = new BuscarAssuntos().execute().get();

            Log.e("ASSUNTOS", assuntos.toString());

            ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, assuntos);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinAssuntos.setAdapter(adapter);

        } catch (Exception e) {
            Toast.makeText(this, "Houve um erro ao listar os assuntos!", Toast.LENGTH_SHORT).show();
            Log.e("ERRO LISTAR ASSUNTOS", e.getMessage());
            e.printStackTrace();
            finish();
        }

        carregarPontuacao();
    }

    @OnItemSelected(R.id.spin_assuntos)
    public void popularSpinNumPerguntas(int position) {
        List<Integer> integers = new ArrayList<>();
        int numPerguntas = 0;

        try {
            numPerguntas = new BuscarNumeroDePerguntas().execute(assuntos.get(position).getId()).get();
        } catch (Exception e) {
            Toast.makeText(this, "Houve um erro ao listar o número de perguntas!", Toast.LENGTH_SHORT).show();
            Log.e("ERRO LISTAR NUM PERG", e.getMessage());
            e.printStackTrace();
        }

        if (numPerguntas == 0) {
            Toast.makeText(this, "Não há perguntas cadastradas com esse assunto!", Toast.LENGTH_SHORT).show();
        } else {
            for (int i = 0; i < numPerguntas; i++) {
                integers.add(i + 1);
            }
        }

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, integers);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinNumPerguntas.setAdapter(adapter);
    }

    @OnClick(R.id.btn_iniciar_quiz)
    public void iniciarQuiz() {

        Assunto assunto = (Assunto) spinAssuntos.getSelectedItem();
        Integer numPerguntas = (Integer) spinNumPerguntas.getSelectedItem();

        Intent intent = new Intent(this, ModoQuiz.class);
        intent.putExtra("assuntoId", assunto.getId());
        intent.putExtra("numPerguntas", numPerguntas);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregarPontuacao();
    }

    private void carregarPontuacao() {
        SharedPreferences preferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        maiorPontuacao = preferences.getInt(KEY_PONTUACAO, 0);
        txtResultado.setText("Maior pontuação: " + maiorPontuacao);
    }


    private class BuscarAssuntos extends AsyncTask<Void, Void, List<Assunto>> {

        @Override
        protected List<Assunto> doInBackground(Void... voids) {
            try {
                return AssuntoRepository.getAll(ConfigurarModoQuiz.this);
            } catch (Exception e) {
                Log.e("ERRO ASYNC ASSUNTOS", e.getMessage());
                e.printStackTrace();
                return null;
            }
        }
    }

    private class BuscarNumeroDePerguntas extends AsyncTask<Long, Void, Integer> {

        @Override
        protected Integer doInBackground(Long... longs) {
            try {
                return PerguntaRepository.contarPerguntasPorAssunto(longs[0], ConfigurarModoQuiz.this);
            } catch (Exception e) {
                Log.e("ERRO ASYNC NUM PERG", e.getMessage());
                e.printStackTrace();
                return 0;
            }
        }
    }
}
