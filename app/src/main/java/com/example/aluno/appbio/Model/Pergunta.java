package com.example.aluno.appbio.Model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.io.Serializable;

@Entity(tableName = "pergunta",
        foreignKeys = {@ForeignKey(entity = Assunto.class,
                parentColumns = "id",
                childColumns = "assunto_id")},
        indices = {@Index(value = {"id"}, unique = true),
                @Index(value = {"assunto_id"})})
public class Pergunta implements Serializable {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    private long id;

    @NonNull
    private String pergunta;

    @NonNull
    private String op1;

    @NonNull
    private String op2;

    @NonNull
    private String op3;

    @NonNull
    private int resposta;

    @NonNull
    @ColumnInfo(name = "assunto_id")
    private long assunto_id;

    public Pergunta() {
    }

    @Ignore
    public Pergunta(@NonNull String pergunta, @NonNull String op1, @NonNull String op2, @NonNull String op3, @NonNull int resposta, @NonNull long assunto_id) {
        this.pergunta = pergunta;
        this.op1 = op1;
        this.op2 = op2;
        this.op3 = op3;
        this.resposta = resposta;
        this.assunto_id = assunto_id;
    }

    @NonNull
    public long getId() {
        return id;
    }

    public void setId(@NonNull long id) {
        this.id = id;
    }

    @NonNull
    public String getPergunta() {
        return pergunta;
    }

    public void setPergunta(@NonNull String pergunta) {
        this.pergunta = pergunta;
    }

    @NonNull
    public String getOp1() {
        return op1;
    }

    public void setOp1(@NonNull String op1) {
        this.op1 = op1;
    }

    @NonNull
    public String getOp2() {
        return op2;
    }

    public void setOp2(@NonNull String op2) {
        this.op2 = op2;
    }

    @NonNull
    public String getOp3() {
        return op3;
    }

    public void setOp3(@NonNull String op3) {
        this.op3 = op3;
    }

    @NonNull
    public int getResposta() {
        return resposta;
    }

    public void setResposta(@NonNull int resposta) {
        this.resposta = resposta;
    }

    @NonNull
    public long getAssunto_id() {
        return assunto_id;
    }

    public void setAssunto_id(@NonNull long assunto_id) {
        this.assunto_id = assunto_id;
    }

    @Override
    public String toString() {
        return "Pergunta{" +
                "id=" + id +
                ", pergunta='" + pergunta + '\'' +
                ", op1='" + op1 + '\'' +
                ", op2='" + op2 + '\'' +
                ", op3='" + op3 + '\'' +
                ", resposta=" + resposta +
                ", assunto_id=" + assunto_id +
                '}';
    }

    public static Pergunta[] populaBanco() {
        return new Pergunta[]{
                new Pergunta("O tecido epitelial glandular tem a capacidade de:", "Secretar", "Revestir", "", 1, 1),
                new Pergunta("O tecido epitelial caracteriza-se por apresentar:", "", "Pouca quantidade de material intercelular", "", 2, 2),
                new Pergunta("Os epitélios podem ser classificados em:", "", "", "Epitélio Simples, Estratificados, Pseudo-estratificados", 3, 1),
                new Pergunta("As formas das células do tecido epitelial são classificadas em:", "", "Pavimentosas, Cúbicas, Prismáticas", "", 2, 1),
                new Pergunta("Os epitélios são classificados em dois tipos principais:", "", "", "Espitélios de Revestimento, Epitélios Glandulares", 3, 1),
                new Pergunta("O tecido epitelial apresenta células…", "", "mantidas pelo Glicocálix e íon cálcio, Aderidas pelos desmossomos e interdigitações", "", 2, 1),
                new Pergunta("A substância intercelular do tecido epitelial é formada por:", "", "Glicoproteínas", "", 2, 1),
                new Pergunta("Os tecidos epiteliais são:", "Avascularizados (sem vasos sanguíneos)", "", "", 1, 1),
                new Pergunta("O tecido epitelial é apoiado pelo tecido conjuntivo, isso quer dizer que:", "", "", "Nutrem o epitélio por meio de difusão", 3, 1),
                new Pergunta("Entre o tecido epitelial e conjuntivo está presente a:", "", "Lâmina Basal", "", 2, 1),
                new Pergunta("A lâmina basal é constituida por:", "", "", "Colágeno e glicoproteínas diversas", 3, 1),
                new Pergunta("A lâmina basal prende o epitélio ao tecido conjuntivo adjacente, possibilitando:", "Seu aspecto possibilita a troca de substâncias entre eles", "", "", 1, 1),
                new Pergunta("O tecido epitelial tem origem em quais folhetos embrionários?", "", "Exoderme, Mesoderme, Endoderme", "", 2, 1),
                new Pergunta("Originam-se da ectoderme:", "", "Epitélios de revestimento (epiderme)", "", 2, 1),
                new Pergunta("Originam-se da Mesoderme:", "Epitélio de revestimento dos vasos sanguíneos (endotélio)", "", "", 1, 1),
                new Pergunta("Originam-se da Endoderme:", "", "Tecidos epiteliais de revestimento do tubo digestório (exceto boca e ânus)", "", 2, 1),
                new Pergunta("Função do Tecido Epitelial - Proteção e revestimento:", "Revestem externamente o organismo, diversos órgãos e cavidades do corpo", "", "", 1, 1),
                new Pergunta("Função do Tecido Epitelial - Absorção de nutrientes:", "", "Epitélio absortivo do intestino", "", 2, 1),
                new Pergunta("Função do Tecido Epitelial - Trocas gasosas:", "", "", "Tecido epitelial de revestimento dos alvéolos pulmonares", 3, 1),
                new Pergunta("Função do Tecido Epitelial - Secreção de substâncias:", "", "Tecido epitelial de secreção", "", 2, 1),
                new Pergunta("Função do Tecido Epitelial - Percepção de estímulos sensoriais:", "Neuroepitélio", "", "", 1, 1),
                new Pergunta("Especialização das Células Epiteliais - Apresentam polaridade estrutural:", "polo Basal, Polo Apical", "", "", 1, 1),
                new Pergunta("Especialização das Células Epiteliais - Cílios:", "", "Estruturas microscópicas móveis presentes em células da traqueia e das tubas uterinas", "", 2, 1),
                new Pergunta("Especialização das Células Epiteliais - Microvilosidades:", "", "", "Estruturas projetadas na superfície apical das células absortivas", 3, 1),
                new Pergunta("Especialização das Células Epiteliais - Interdigitações:", "", "", "Dobras internas da membrana plasmática em células de alguns epitélios", 3, 1),
                new Pergunta("Especialização das Células Epiteliais - Junções Intercelulares:", "", "", "Fornecem grande coesão ao tecido epitelial tornando altamente resistente a trações", 3, 1),
                new Pergunta("Especialização das Células Epiteliais - Principais Junções Intercelulares:", "", "Junção de Oclusão, Desmossomos, Junções Comunicantes", "", 2, 1),
                new Pergunta("Tecido Epitelial Glandular:", "", "", "Produzem secreções que podem ser utilizadas em outras partes do corpo ou eliminadas do organismo", 3, 1),
                new Pergunta("Tecido Epitelial Glandular - Secreções:", "", "Mucosas, Serosas, Mistas", "", 2, 1),
                new Pergunta("Tecido Epitelial Glandular - As glândulas podem ser:", "Unicelulares e multicelulares", "", "", 1, 1),
                new Pergunta("Tecido Epitelial Glandular - Glândula Unicelular: Caliciforme", "", "Ocorre no tecido epitelial da traquéia", "", 2, 1),
                new Pergunta("Tecido Epitelial Glandular - Glândulas Multicelulares:", "", "Originam-se sempre dos epitélios de revestimento por proliferação de suas células para o interior do tecido conjuntivo subjacente", "", 2, 1),
                new Pergunta("Tecido Epitelial Glandular - Existem três tipos de glândulas multicelulares:", "", "Glândulas Exócrinas, Glândulas Endócrinas, Glândulas Mistas", "", 2, 1),
                new Pergunta("Tecido Epitelial Glandular - Glândulas Exócrinas:", "", "", "Para fora do corpo, Para interior das cavidades do corpo", 3, 1),
                new Pergunta("Tecido Epitelial Glandular - Glândulas Endócrinas:", "As secreções são hormônios e lançados diretamente nos vasos sanguíneos e linfáticos", "", "", 1, 1),

                new Pergunta("O Tecido Conjuntivo Cartilaginoso é formado por?", "", "Fibras Colágenas, Fibras Reticulares, Glicoproteínas", "", 2, 2),

                new Pergunta("A função do tecido adiposo é de?", "Armazenar energia, proteger contra choques mecânicos, isolamento térmico", "", "", 1, 3),

                new Pergunta("Os tipos celulares do tecido Cartilaginoso são formados por?", "Condroblastos, Condrócitos", "", "", 1, 4),
                new Pergunta("As características do tecido Cartilaginoso são?", "", "Origem mesenquimática, proporção células/matriz extracelular semelhante ao conjuntivo", "", 2, 4),
                new Pergunta("Os tipos de cartilagem do tecido cartilaginoso são formados por?", "", "", "Fibrosa, Hialina, Elástica", 3, 4),
                new Pergunta("No aspecto macroscópico, a cartilagem Hialina tem aspecto:", "", "", "Leitoso e azulado", 3, 4),
                new Pergunta("Em um corte histológico, a cartilagem Hialina é:", "Transparente", "", "", 1, 4),
                new Pergunta("A cartilagem Hialima está presente:", "", "No esqueleto embrionário, traqueia e brônquios, nariz", "", 2, 4)
        };
    }
}