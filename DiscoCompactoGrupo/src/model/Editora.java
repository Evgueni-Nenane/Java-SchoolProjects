package model;

public class Editora {
	private String nomeEditora;
	private String emailEditora;
	private String endereco;
	
	public Editora(String nomeEditora, String emailEditora, String endereco) {
		this.nomeEditora = nomeEditora;
		this.emailEditora = emailEditora;
		this.endereco = endereco;
	}

	public String getNomeEditora() {
		return nomeEditora;
	}

	public void setNomeEditora(String nomeEditora) {
		this.nomeEditora = nomeEditora;
	}

	public String getEmailEditora() {
		return emailEditora;
	}

	public void setEmailEditora(String emailEditora) {
		this.emailEditora = emailEditora;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	
}
