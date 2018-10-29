package com.example.aluno.appbio.Model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.Arrays;

@Entity(tableName = "conteudo",
        foreignKeys = {@ForeignKey(entity = Assunto.class,
                parentColumns = "id",
                childColumns = "assunto_id")},
        indices = {@Index(value = {"id"}, unique = true),
                @Index(value = {"assunto_id"})})
public class Conteudo implements Serializable {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    private long id;

    @NonNull
    private String caracteristica;

    @NonNull
    private String conceito;

    private byte[] imagem;

    @NonNull
    @ColumnInfo(name = "assunto_id")
    private long assunto_id;

    public Conteudo() {
    }

    @Ignore
    public Conteudo(@NonNull String caracteristica, @NonNull String conceito, @NonNull long assunto_id) {
        this.caracteristica = caracteristica;
        this.conceito = conceito;
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
    public String getCaracteristica() {
        return caracteristica;
    }

    public void setCaracteristica(@NonNull String caracteristica) {
        this.caracteristica = caracteristica;
    }

    @NonNull
    public String getConceito() {
        return conceito;
    }

    public void setConceito(@NonNull String conceito) {
        this.conceito = conceito;
    }

    public byte[] getImagem() {
        return imagem;
    }

    public void setImagem(byte[] imagem) {
        this.imagem = imagem;
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
        return "Conteudo{" +
                "id=" + id +
                ", caracteristica='" + caracteristica + '\'' +
                ", conceito='" + conceito + '\'' +
                ", imagem=" + Arrays.toString(imagem) +
                ", assunto_id=" + assunto_id +
                '}';
    }

    public static Conteudo[] popularBanco(String assunto, long assunto_id) {
        Conteudo[] conteudos = new Conteudo[]{};
        switch (assunto) {
            case "Tecido Epitelial":
                conteudos = new Conteudo[]{
                        new Conteudo("O tecido epitelial glandular tem a capacidade de:", "Revestir, Sintetizar, Secretar", assunto_id),
                        new Conteudo("O tecido epitelial caracteriza-se por apresentar:", "Pouca quantidade de material intercelular, Células justapostas, Ausência de vasos sangíneos", assunto_id),
                        new Conteudo("Os epitélios podem ser classificados em:", "Epitélio Simples, Estratificados, Pseudo-estratificados", assunto_id),
                        new Conteudo("As formas das células do tecido epitelial são classificadas em:", "Pavimentosas, Cúbicas, Prismáticas", assunto_id),
                        new Conteudo("Os epitélios são classificados em dois tipos principais:", "Espitélios de Revestimento, Epitélios Glandulares", assunto_id),
                        new Conteudo("O tecido epitelial apresenta células…", "mantidas pelo Glicocálix e íon cálcio, Aderidas pelos desmossomos e interdigitações", assunto_id),
                        new Conteudo("A substância intercelular do tecido epitelial é formada por:", "Glicoproteínas", assunto_id),
                        new Conteudo("Os tecidos epiteliais são:", "Avascularizados (sem vasos sanguíneos)", assunto_id),
                        new Conteudo("O tecido epitelial é apoiado pelo tecido conjuntivo", "Nutrem o epitélio por meio de difusão", assunto_id),
                        new Conteudo("Entre o tecido epitelial e conjuntivo está presente a:", "Lâmina Basal", assunto_id),
                        new Conteudo("A lâmina basal é constituida por:", "Colágeno e glicoproteínas diversas", assunto_id),
                        new Conteudo("A lâmina basal prende o epitélio ao tecido conjuntivo adjacente", "Seu aspecto possibilita a troca de substâncias entre eles", assunto_id),
                        new Conteudo("O tecido epitelial tem origem em três folhetos embrionários:", "Extoderme, Mesoderme, Endoderme", assunto_id),
                        new Conteudo("Originam-se da ectoderme:", "Epitélios de revestimento (epiderme), Boca / cavidades nasais e ânus, Glândulas mamárias / salivares e sebáceas", assunto_id),
                        new Conteudo("Originam-se da Mesoderme:", "Epitélio de revestimento dos vasos sanguíneos (endotélio), Epitélios do Sistema Geniturinário (exceto a bexiga), Epitélio de revestiemnto de menbranas dos órgãos internos (pleura / peritônio e pericárdio)", assunto_id),
                        new Conteudo("Originam-se da Endoderme:", "Tecidos epiteliais de revestimento do tubo digestório (exceto boca e ânus), Tecidos do Sistema Respiratório, Tecidos de órgãos como bexiga / fígado e pâncreas, Tecido das glândulas tireoide e paratireoide", assunto_id),
                        new Conteudo("Função do Tecido Epitelial - Proteção e revestimento:", "Revestem externamente o organismo, diversos órgãos e cavidades do corpo", assunto_id),
                        new Conteudo("Função do Tecido Epitelial - Absorção de nutrientes:", "Epitélio absortivo do intestino", assunto_id),
                        new Conteudo("Função do Tecido Epitelial - Trocas gasosas:", "Tecido epitelial de revestimento dos alvéolos pulmonares", assunto_id),
                        new Conteudo("Função do Tecido Epitelial - Secreção de substâncias:", "Tecido epitelial de secreção", assunto_id),
                        new Conteudo("Função do Tecido Epitelial - Percepção de estímulos sensoriais:", "Neuroepitélio", assunto_id),
                        new Conteudo("Especialização das Células Epiteliais - Apresentam polaridade estrutural:", "Polo Basal: contato com a lâmina basal subjacente, Polo Apical: Superficie celular livre", assunto_id),
                        new Conteudo("Especialização das Células Epiteliais - Cílios:", "Estruturas microscópicas móveis presentes em células da traqueia e das tubas uterinas", assunto_id),
                        new Conteudo("Especialização das Células Epiteliais - Microvilosidades:", "Estruturas projetadas na superfície apical das células absortivas, Ampliam a superfície de contato das células intestinais com o conteúdo externo, Aumentam a absorção de nutrientes", assunto_id),
                        new Conteudo("Especialização das Células Epiteliais - Interdigitações:", "Dobras internas da membrana plasmática em células de alguns epitélios, Aumentam a superfície de contato entre as células, Facilitam o intercâmbio de substâncias.", assunto_id),
                        new Conteudo("Especialização das Células Epiteliais - Junções Intercelulares:", "Fornecem grande coesão ao tecido epitelial tornando altamente resistente a trações", assunto_id),
                        new Conteudo("Especialização das Células Epiteliais - Principais Junções Intercelulares:", "Junção de Oclusão - Junção de Adesão, Desmossomos – Hemidesmossomos, Junções Comunicantes", assunto_id),
                        new Conteudo("Tecido Epitelial Glandular:", "Produzem substâncias chamadas secreções, Podem ser utilizadas em outras partes do corpo ou eliminadas do organismo", assunto_id),
                        new Conteudo("Tecido Epitelial Glandular - Secreções:", "Mucosas: Espessas e ricas em muco. Ex: Glândulas salivares, Serosas: Fluídas, aquosas e ricas em proteína. Ex: Glândulas excretoras do pâncreas, Mistas: Quando ocorre secreções mucosas e serosas juntas. Ex: Glândulas Salivares Parótidas", assunto_id),
                        new Conteudo("Tecido Epitelial Glandular:", "As glândulas podem ser: unicelulares e multicelulares", assunto_id),
                        new Conteudo("Tecido Epitelial Glandular - Glândula Unicelular: Caliciforme", "Ocorre no tecido epitelial da traquéia", assunto_id),
                        new Conteudo("Tecido Epitelial Glandular - Glândulas Multicelulares:", "Originam-se sempre dos epitélios de revestimento por proliferação de suas células para o interior do tecido conjuntivo subjacente", assunto_id),
                        new Conteudo("Tecido Epitelial Glandular - Existem três tipos de glândulas multicelulares:", "Glândulas Exócrinas, Glândulas Endócrinas, Glândulas Mistas", assunto_id),
                        new Conteudo("Tecido Epitelial Glandular - Glândulas Exócrinas:", "Apresentam a porção secretora associada a dutos que lançam suas secreções, Para fora do corpo: Glândulas Sudoríparas / Lacrimais / Mamárias / etc., Para interior das cavidades do corpo: Glândulas Salivares", assunto_id),
                        new Conteudo("Tecido Epitelial Glandular - Glândulas Endócrinas:", "Não apresentam dutos associados à porção secretora, As secreções são hormônios e lançados diretamente nos vasos sanguíneos e linfáticos, Exemplo: hipófise, glândulas da tireoide, glândulas paratireódeas e glândulas adrenais", assunto_id)
                };
                break;
            case "Tecido Conjuntivo":
                conteudos = new Conteudo[]{
                        new Conteudo("O Tecido Conjuntivo Cartilaginoso é formado por:", "Fibras Colágenas, Fibras Reticulares, Glicoproteínas", assunto_id)
                };
                break;
            case "Tecido Adiposo":
                conteudos = new Conteudo[]{
                        new Conteudo("A função do tecido adiposo é de:", "Armazenar energia na forma de triglicerídeos, Contribuir para a forma externa do corpo, Amortecer/proteger o organismo contra choques mecânicos, Realizar isolamento térmico, Preencher espaços entre outros tecidos/órgãos", assunto_id)
                };
                break;
            case "Tecido Cartilaginoso":
                conteudos = new Conteudo[]{

                        new Conteudo("Os tipos celulares do tecido Cartilaginoso são formados por:", "Condroblastos, Condrócitos", assunto_id),
                        new Conteudo("As características do tecido Cartilaginoso são:", "Origem mesenquimática, Proporção células/matriz extracelular semelhante ao conjuntivo, A matriz é secretada pelas células da cartilagem e é a responsável pelas diferentes características e propriedades da mesma", assunto_id),
                        new Conteudo("Os tipos de cartilagem do tecido cartilaginoso são formados por:", "Fibrosa: Fibras colágenas do tipo I, Hialina: Fibras colágenas do tipo II, Elástica: Fibras colágenas do tipo II e fibras elásticas ", assunto_id),
                        new Conteudo("A cartilagem Hialina tem aspecto:", "Aspecto macroscópico: leitoso / azulado, Cortes histológicos: transparente (hialina),  Presente no esqueleto embrionário / articulações dos ossos longos / traqueia e brônquios / nariz.", assunto_id)
                };
                break;
        }

        return conteudos;
    }
}
