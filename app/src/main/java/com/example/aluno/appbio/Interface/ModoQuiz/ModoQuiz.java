package com.example.aluno.appbio.Interface.ModoQuiz;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aluno.appbio.Model.Pergunta;
import com.example.aluno.appbio.R;
import com.example.aluno.appbio.Repository.PerguntaRepository;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.aluno.appbio.Interface.ModoQuiz.ConfigurarModoQuiz.KEY_PONTUACAO;
import static com.example.aluno.appbio.Interface.ModoQuiz.ConfigurarModoQuiz.SHARED_PREFS;

public class ModoQuiz extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.text_view_pontos)
    public TextView txtPontos;

    @BindView(R.id.text_view_num_perguntas)
    public TextView txtNumPerguntas;

    @BindView(R.id.text_view_tempo)
    public TextView txtTempo;

    @BindView(R.id.txt_pergunta)
    public TextView txtPergunta;

    @BindView(R.id.group_respostas)
    public RadioGroup groupRespostas;

    @BindView(R.id.op1)
    public RadioButton rb1;

    @BindView(R.id.op2)
    public RadioButton rb2;

    @BindView(R.id.op3)
    public RadioButton rb3;

    @BindView(R.id.btn_confirmar_proximo)
    public Button button;

    private ColorStateList textColorDefaultRb;

    private List<Pergunta> perguntaList;

    private int contadorPerguntas;

    private int totalPerguntas;

    private Pergunta perguntaAtual;

    private int pontos;

    private boolean respondido;

    private long assuntoId;

    private long backPressedTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modo_quiz);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        textColorDefaultRb = rb1.getTextColors();


        try {
            Intent intent = getIntent();
            assuntoId = intent.getLongExtra("assuntoId", 0);
            totalPerguntas = intent.getIntExtra("numPerguntas", 0);
        } catch (Exception e) {
            Toast.makeText(this, "Houve um erro ao inicializar o quiz!", Toast.LENGTH_SHORT).show();
            Log.e("ERRO INTENT", e.getMessage());
            e.printStackTrace();
            finish();
        }

        try {
            perguntaList = new GetPerguntas().execute(assuntoId).get();
        } catch (Exception e) {
            Toast.makeText(this, "Houve um erro ao buscar as perguntas!", Toast.LENGTH_LONG).show();
            finish();
        }

        Collections.shuffle(perguntaList);

        proximaPergunta();
    }

    private void proximaPergunta() {
        rb1.setTextColor(textColorDefaultRb);
        rb2.setTextColor(textColorDefaultRb);
        rb3.setTextColor(textColorDefaultRb);

        groupRespostas.clearCheck();

        if (contadorPerguntas < totalPerguntas) {
            perguntaAtual = perguntaList.get(contadorPerguntas);

            txtPergunta.setText(perguntaAtual.getPergunta());
            rb1.setText(perguntaAtual.getOp1());
            rb2.setText(perguntaAtual.getOp2());
            rb3.setText(perguntaAtual.getOp3());

            contadorPerguntas++;
            txtNumPerguntas.setText("Pergunta: " + contadorPerguntas + "/" + totalPerguntas);

            respondido = false;

            button.setText(R.string.confirmar);
        } else {
            finalizarQuiz();
        }
    }

    @OnClick(R.id.btn_confirmar_proximo)
    public void click() {
        if (!respondido) {
            if (rb1.isChecked() || rb2.isChecked() || rb3.isChecked()) {
                verificarResposta();
            } else {
                Toast.makeText(ModoQuiz.this, "Selecione uma opção!", Toast.LENGTH_LONG).show();
            }
        } else {
            proximaPergunta();
        }
    }

    private void verificarResposta() {
        respondido = true;

        RadioButton selecionado = findViewById(groupRespostas.getCheckedRadioButtonId());
        int resposta = groupRespostas.indexOfChild(selecionado) + 1;

        if (resposta == perguntaAtual.getResposta()) {
            pontos++;
            txtPontos.setText("Pontos: " + pontos);
        }
        mostrarResposta();
    }

    private void mostrarResposta() {
        rb1.setTextColor(Color.RED);
        rb2.setTextColor(Color.RED);
        rb3.setTextColor(Color.RED);

        switch (perguntaAtual.getResposta()) {
            case 1:
                rb1.setTextColor(Color.GREEN);
                txtPergunta.setText("Resposta 1 é a correta!");
                break;

            case 2:
                rb2.setTextColor(Color.GREEN);
                txtPergunta.setText("Resposta 2 é a correta!");
                break;

            case 3:
                rb3.setTextColor(Color.GREEN);
                txtPergunta.setText("Resposta 3 é a correta!");
                break;
        }

        if (contadorPerguntas < totalPerguntas) {
            button.setText("Próxima");
        } else {
            button.setText("Finalizar");
        }
    }

    private void finalizarQuiz() {

        SharedPreferences preferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        int maiorPontuacao = preferences.getInt(KEY_PONTUACAO, 0);

        if (pontos > maiorPontuacao) {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt(KEY_PONTUACAO, pontos);
            editor.apply();
        }

        finish();
    }

    @Override
    public void onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            finalizarQuiz();
        } else {
            Toast.makeText(this, "Pressione novamente para voltar", Toast.LENGTH_SHORT).show();
        }
        backPressedTime = System.currentTimeMillis();
    }

    private class GetPerguntas extends AsyncTask<Long, Void, List<Pergunta>> {

        @Override
        protected List<Pergunta> doInBackground(Long... longs) {
            try {
                return PerguntaRepository.listarPorAssunto(ModoQuiz.this, longs[0]);
            } catch (Exception e) {
                return null;
            }
        }
    }
}
