package model;

import java.time.LocalDate;
import java.util.List;


public class DiscoCompacto {
		
    private int codigoDisco;
    private String titulo;
    private double preco;
    private int anoEdicao;
    private Genero generoMusical;
    private List<Cantor> cantores;
    private List<Musico> musicos;
    private List<Compositor> compositores;
    private List<Produtor> produtores;
    private List<Gravadora> gravadoras;
    private List<Editora> editoras;
    private Edicao edicao;

    public DiscoCompacto(String titulo, Genero generoMusical, double preco,
            int anoEdicao, List<Cantor> cantores, List<Musico> musicos, List<Compositor> compositores, List<Gravadora> gravadoras,
            List<Produtor> produtores, List<Editora> editoras) {
        this.titulo = titulo;
        this.generoMusical = generoMusical;
        this.preco = preco;
        this.anoEdicao = anoEdicao;
        this.cantores = cantores;
        this.compositores = compositores;
        this.musicos = musicos;
        this.gravadoras = gravadoras;
        this.produtores = produtores;
        this.editoras = editoras;
    }

    public DiscoCompacto(int codigoDisco,String titulo, Genero generoMusical, double preco,
            int anoEdicao, List<Cantor> cantores, List<Musico> musicos, List<Compositor> compositores, List<Gravadora> gravadoras,
            List<Produtor> produtores, List<Editora> editoras) {
        this(titulo, generoMusical, preco, anoEdicao, cantores, musicos, compositores, gravadoras, produtores, editoras);
        this.codigoDisco = codigoDisco;
    }

    
    
    public DiscoCompacto() {
		// TODO Auto-generated constructor stub
	}

	public int getCodigoDisco() {
		return codigoDisco;
	}

	public void setCodigoDisco(int codigoDisco) {
		this.codigoDisco = codigoDisco;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public int getAnoEdicao() {
		return anoEdicao;
	}

	public void setAnoEdicao(int anoEdicao) {
		this.anoEdicao = anoEdicao;
	}

	public Genero getGeneroMusical() {
		return generoMusical;
	}

	public void setGeneroMusical(Genero generoMusical) {
		this.generoMusical = generoMusical;
	}

	public List<Cantor> getCantores() {
		return cantores;
	}

	public void setCantores(List<Cantor> cantores) {
		this.cantores = cantores;
	}

	public List<Musico> getMusicos() {
		return musicos;
	}

	public void setMusicos(List<Musico> musicos) {
		this.musicos = musicos;
	}

	public List<Compositor> getCompositores() {
		return compositores;
	}

	public void setCompositores(List<Compositor> compositores) {
		this.compositores = compositores;
	}

	public List<Produtor> getProdutores() {
		return produtores;
	}

	public void setProdutores(List<Produtor> produtores) {
		this.produtores = produtores;
	}

	public List<Gravadora> getGravadoras() {
		return gravadoras;
	}

	public void setGravadoras(List<Gravadora> gravadoras) {
		this.gravadoras = gravadoras;
	}

	public List<Editora> getEditoras() {
		return editoras;
	}

	public void setEditoras(List<Editora> editoras) {
		this.editoras = editoras;
	}
	
	public Edicao getEdicao() {
		return edicao;
	}

	public void setEdicao(Edicao edicao) {
		this.edicao = edicao;
	}

	public int discoExistencia(int ano) {
    	LocalDate hoje = LocalDate.now();
    	int anoActual = hoje.getYear();
    	return anoActual - ano;
    };
    
}