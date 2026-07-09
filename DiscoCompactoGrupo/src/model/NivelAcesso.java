package model;

public class NivelAcesso {
	
	public static final int OPERADOR = 1;
	public static final int SUPEROPERADOR = 2;
	public static final int ADMINISTRADOR = 3;
	public static final int AUDITOR = 4;
	
	private int codigoNivel;
	private String nome;
	
	public NivelAcesso() {
	}
	public NivelAcesso(int codigoNivel, String nome) {
		this.codigoNivel = codigoNivel;
		this.nome = nome;
	}
	public NivelAcesso(String nome) {
		this.nome = nome;
	}
	public NivelAcesso(int codigoNivel) {
		this.codigoNivel = codigoNivel;
	}
	
	public int getCodigoNivel() {
		return codigoNivel;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		return this.getNome();
	}
}
