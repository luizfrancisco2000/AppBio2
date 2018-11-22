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
    private int resposta;

    @NonNull
    @ColumnInfo(name = "assunto_id")
    private long assunto_id;

    public Pergunta() {
    }

    @Ignore
    public Pergunta(@NonNull String pergunta, @NonNull String op1, @NonNull String op2, @NonNull int resposta, @NonNull long assunto_id) {
        this.pergunta = pergunta;
        this.op1 = op1;
        this.op2 = op2;
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
                ", resposta=" + resposta +
                ", assunto_id=" + assunto_id +
                '}';
    }

    public static Pergunta[] populaBanco() {
        return new Pergunta[]{
                new Pergunta("O tecido epitelial glandular tem a capacidade de:", "Sintetizar", "Secretar", 2, 1),
                new Pergunta("O tecido epitelial caracteriza-se por apresentar:", "Muita quantidade de material intercelular", "Ausência de vasos sanguíneos", 2, 1),
                new Pergunta("Os epitélios podem ser classificados em:", "Não Estratificado ( sem camada)", "Estratificados (mais de uma camada)", 2, 1),
                new Pergunta("A forma das células do tecido epitelial são classificadas em:", "Pavimentosas (achatadas)", "Ondulares (alongadas)", 1, 1),
                new Pergunta("Os epitélios são classificados em dois tipos principais:", "Epitélios de Revestimento e Glandulares", "Epitélios de Fixação e Nervoso", 1, 1),
                new Pergunta("O tecido epitelial apresenta células…", "Mantidas pelo Glicocálix e íon cálcio", "Mantidas pelo RER e íon potássio", 1, 1),
                new Pergunta("A substância intercelular do tecido epitelial é formada por:", "Lipídios", "Glicoproteínas", 2, 1),
                new Pergunta("Os tecidos epiteliais são:", "Vascularizados", "Avascularizados", 2, 1),
                new Pergunta("O tecido epitelial é apoiado pelo tecido conjuntivo e...", "Nutrem o epitélio por meio de fusão", "Nutrem o epitélio por meio de difusão", 2, 1),
                new Pergunta("Entre o tecido epitelial e conjuntivo está presente a:", "Lâmina Basal", "Canal Central", 1, 1),
                new Pergunta("A lâmina basal é constituida por:", "Colágeno e glicoproteínas diversas", "Melanina e glicoproteínas diversas", 1, 1),
                new Pergunta("A lâmina basal prende o epitélio ao tecido conjuntivo adjacente", "Seu aspecto possibilita a troca de substâncias entre eles", "Seu aspecto NÃO possibilita a troca de substâncias entre eles", 1, 1),
                new Pergunta("O tecido epitelial tem origem em três folhetos embrionários:", "Mesoderme, Epiderme e Ortoderme", "Ectoderme. Mesoderme e Endoderme", 2, 1),
                new Pergunta("Originam-se da ectoderme:", "Esôfago, Pulmão e Boca", "Boca, cavidades nasais e ânus", 2, 1),
                new Pergunta("Originam-se da Mesoderme:", "Epitélio de revestimento dos vasos sanguíneos", "Epitélios do Sistema Geniturinário ( incluindo a bexiga)", 1, 1),
                new Pergunta("Originam-se da Endoderme:", "Tecidos epiteliais de revestimento do tubo digestório (exceto boca e ânus)", "Tecidos do Sistema Nervoso", 1, 1),
                new Pergunta("Função do Tecido Epitelial", "Circulação e sintetização", "Proteção e revestimento", 2, 1),
                new Pergunta("Função do Tecido Epitelial", "Nutrição dos tecidos", "Absorção de nutrientes:", 2, 1),
                new Pergunta("Função do Tecido Epitelial", "Trocas gasosas", "Preenchimento", 1, 1),
                new Pergunta("Função do Tecido Epitelial", "Secreção de substâncias", "Condução de substâncias", 1, 1),
                new Pergunta("Função do Tecido Epitelial", "Vascularização", "Percepção de estímulos sensoriais:", 2, 1),
                new Pergunta("Especialização das Células Epiteliais", "Apresentam polaridade funcional", "Apresentam polaridade estrutural", 2, 1),
                new Pergunta("Especialização das Células Epiteliais", "Cílios", "Dentritos", 1, 1),
                new Pergunta("Especialização das Células Epiteliais", "Microvilosidades", "Macrovilosidades", 1, 1),
                new Pergunta("Especialização das Células Epiteliais", "Intercalações", "Interdigitações", 2, 1),
                new Pergunta("Especialização das Células Epiteliais", "Junções Extracelulares", "Junções Intercelulares", 2, 1),
                new Pergunta("Especialização das Células Epiteliais", "Junção de Oclusão - Junção de Adesão", "Junções de Inclusão – Junção de Reação", 1, 1),
                new Pergunta("Tecido Epitelial Glandular", "Produzem substâncias chamadas secreções", "Não produzem substâncias", 1, 1),
                new Pergunta("As secreções do tecido epitelial glandular podem ser:", "Unificadas, Secas e Líquidas", "Mucosas, Serosas e Mistas", 2, 1),
                new Pergunta("As glândulas podem ser:", "Apenas Unicelulares", "Unicelulares e multicelulares", 2, 1),
                new Pergunta("Tecido Epitelial Glandular contém:", "Glândula Unicelular", "Glândula Pluricelular", 1, 1),
                new Pergunta("Tecido Epitelial Glandular NÃO possui:", "Glândulas Multicelulares", "Glândulas Neuronais", 1, 1),
                new Pergunta("Existem três tipos de glândulas multicelulares:", "Glândulas Serosas, Externas e Unificadas", "Glândulas Exócrinas, Endócrinas e Mistas", 2, 1),
                new Pergunta("Tecido Epitelial Glandular possui:", "Glândulas Digestivas", "Glândulas Exócrinas", 2, 1),
                new Pergunta("Glândulas Endócrinas:", "Apresentam dutos associados à porção secretora", "Não apresentam dutos associados à porção secretora", 1, 1),

                new Pergunta("O Tecido Conjuntivo Cartilaginoso é formado por:", "Fibras Colágenas", "Fibras Capilares", 1, 2),

                new Pergunta("A função do tecido adiposo é de :", "Preencher espaços entre outros tecidos/órgãos", "Isolar os tecidos dos órgãos", 1, 3),

                new Pergunta("Os tipos celulares do tecido Cartilaginoso é formado por:", "Cloroplastos", "Condroblastos", 2, 4),
                new Pergunta("As características do tecido Cartilaginoso é formada por:", "Origem endequimática", "Origem mesenquimática", 2, 4),
                new Pergunta("Os tipos de cartilagem do tecido cartilaginoso são formados por:", "Hialina, Elástica e Fibrosa", "Mielina, Justas e Alongadas", 1, 4),
                new Pergunta("A cartilagem Hialina tem aspecto:", "Aspecto macroscópico: leitoso, azulado", "Aspecto microscópico, seco e esverdeado", 1, 4),
        };
    }
}