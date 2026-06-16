package model;

public class Gravadora {
	private String nomeGravadora;
	private String emailGravadora;
	private String enderecoGravadora;
	
	public Gravadora(String nomeGravadora, String emailGravadora, String enderecoGravadora) {
		this.nomeGravadora = nomeGravadora;
		this.emailGravadora = emailGravadora;
		this.enderecoGravadora = enderecoGravadora;
	}

	public String getNomeGravadora() {
		return nomeGravadora;
	}

	public void setNomeGravadora(String nomeGravadora) {
		this.nomeGravadora = nomeGravadora;
	}

	public String getEmailGravadora() {
		return emailGravadora;
	}

	public void setEmailGravadora(String emailGravadora) {
		this.emailGravadora = emailGravadora;
	}

	public String getEnderecoGravadora() {
		return enderecoGravadora;
	}

	public void setEnderecoGravadora(String enderecoGravadora) {
		this.enderecoGravadora = enderecoGravadora;
	}
	
}
