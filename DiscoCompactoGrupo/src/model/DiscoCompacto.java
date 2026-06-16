package model;

import java.time.LocalDate;

public class DiscoCompacto {

    private int ID_DC;
    private String musico;
    private String generoMusica;
    private String gravadora;
    private double preco;
    private String produtor;
    private String editora;
    private int anoEdicao;

    public DiscoCompacto(String musico, String generoMusica, String gravadora,
            double preco, String editora, String produtor, int anoEdicao) {
        this.musico = musico;
        this.generoMusica = generoMusica;
        this.gravadora = gravadora;
        this.preco = preco;
        this.produtor = produtor;
        this.editora = editora;
        this.anoEdicao = anoEdicao;
    }

    public DiscoCompacto(int ID_DC, String musico, String generoMusica, String gravadora,
            double preco, String editora, String produtor, int anoEdicao) {
        this(musico, generoMusica, gravadora, preco, editora, produtor, anoEdicao);
        this.ID_DC = ID_DC;
    }

    public int existenciaDisco() {
        LocalDate hoje = LocalDate.now();
        int anoActual = hoje.getYear();
        return anoActual - anoEdicao;
    }

    public String getMusico() {
        return musico;
    }

    public void setMusico(String musico) {
        this.musico = musico;
    }

    public String getGeneroMusica() {
        return generoMusica;
    }

    public void setGeneroMusica(String generoMusica) {
        this.generoMusica = generoMusica;
    }

    public String getGravadora() {
        return gravadora;
    }

    public void setGravadora(String gravadora) {
        this.gravadora = gravadora;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getProdutor() {
        return produtor;
    }

    public void setProdutor(String produtor) {
        this.produtor = produtor;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public int getAnoEdicao() {
        return anoEdicao;
    }

    public void setAnoEdicao(int anoEdicao) {
        this.anoEdicao = anoEdicao;
    }

    public int getID_DC() {
        return ID_DC;
    }

    public void setID_DC(int ID_DC) {
        this.ID_DC = ID_DC;
    }
}