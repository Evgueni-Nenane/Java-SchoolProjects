package model;

public class Musico {
	private String nomeArtistico;
	private String nomeMusico;
	private String apelidoMusico;
	private String contactoMusico;
	private String emailMusico;
	
	public Musico(String nomeArtistico, String nomeMusico, String apelidoMusico, String contactoMusico, String emailMusico) {
		this.nomeArtistico = nomeArtistico;
		this.nomeMusico = nomeMusico;
		this.apelidoMusico = apelidoMusico;
		this.contactoMusico = contactoMusico;
		this.emailMusico = emailMusico;
	}

	public String getNomeArtistico() {
		return nomeArtistico;
	}

	public void setNomeArtistico(String nomeArtistico) {
		this.nomeArtistico = nomeArtistico;
	}

	public String getNomeMusico() {
		return nomeMusico;
	}

	public void setNomeMusico(String nomeMusico) {
		this.nomeMusico = nomeMusico;
	}

	public String getApelidoMusico() {
		return apelidoMusico;
	}

	public void setApelidoMusico(String apelidoMusico) {
		this.apelidoMusico = apelidoMusico;
	}

	public String getContactoMusico() {
		return contactoMusico;
	}

	public void setContactoMusico(String contactoMusico) {
		this.contactoMusico = contactoMusico;
	}

	public String getEmailMusico() {
		return emailMusico;
	}

	public void setEmailMusico(String emailMusico) {
		this.emailMusico = emailMusico;
	}
	
	

}
