package model;

import java.time.LocalDate;
import java.util.List;

public class DiscoCompacto {

    private int codigoDisco;
    private String titulo;
    private double preco;
    private int anoEdicao;
    private Generos generoMusical;
    private List<Banda> bandas;
    private List<Gravadora> gravadoras;
    private List<Produtor> produtores;
    private List<Editora> editoras;

    public DiscoCompacto(String titulo, Generos generoMusical, double preco,
            int anoEdicao, List<Banda> bandas, List<Gravadora> gravadoras,
            List<Produtor> produtores, List<Editora> editoras) {
        this.titulo = titulo;
        this.generoMusical = generoMusical;
        this.preco = preco;
        this.anoEdicao = anoEdicao;
        this.bandas = bandas;
        this.gravadoras = gravadoras;
        this.produtores = produtores;
        this.editoras = editoras;
    }

    public DiscoCompacto(int codigoDisco, String titulo, Generos generoMusical,
            double preco, int anoEdicao, List<Banda> bandas,
            List<Gravadora> gravadoras, List<Produtor> produtores,
            List<Editora> editoras) {
        this(titulo, generoMusical, preco, anoEdicao, bandas, gravadoras, produtores, editoras);
        this.codigoDisco = codigoDisco;
    }

    public int getCodigoDisco() { return codigoDisco; }
    public void setCodigoDisco(int codigoDisco) { this.codigoDisco = codigoDisco; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public double getPreco() { return preco; }
    public void setPreco(double preco) { this.preco = preco; }
    public int getAnoEdicao() { return anoEdicao; }
    public void setAnoEdicao(int anoEdicao) { this.anoEdicao = anoEdicao; }
    public Generos getGeneroMusical() { return generoMusical; }
    public void setGeneroMusical(Generos generoMusical) { this.generoMusical = generoMusical; }
    public List<Banda> getBandas() { return bandas; }
    public void setBandas(List<Banda> bandas) { this.bandas = bandas; }
    public List<Gravadora> getGravadoras() { return gravadoras; }
    public void setGravadoras(List<Gravadora> gravadoras) { this.gravadoras = gravadoras; }
    public List<Produtor> getProdutores() { return produtores; }
    public void setProdutores(List<Produtor> produtores) { this.produtores = produtores; }
    public List<Editora> getEditoras() { return editoras; }
    public void setEditoras(List<Editora> editoras) { this.editoras = editoras; }
    
    public int discoExistencia(int ano) {
    	LocalDate hoje = LocalDate.now();
    	int anoActual = hoje.getYear();
    	return anoActual - ano;
    };
}