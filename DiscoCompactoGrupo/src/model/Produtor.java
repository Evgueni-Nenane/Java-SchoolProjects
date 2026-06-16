package model;

public class Produtor {
	private String nomeProdutor;
	private String nomeArtProdutor;
	private String contactoProdutor;
	private String emailProdutor;
	
	public Produtor(String nomeProdutor, String nomeArtProdutor, String contactoProdutor, String emailProdutor) {
		this.nomeProdutor = nomeProdutor;
		this.nomeArtProdutor = nomeArtProdutor;
		this.contactoProdutor = contactoProdutor;
		this.emailProdutor = emailProdutor;
	}

	public String getNomeProdutor() {
		return nomeProdutor;
	}

	public void setNomeProdutor(String nomeProdutor) {
		this.nomeProdutor = nomeProdutor;
	}

	public String getNomeArtProdutor() {
		return nomeArtProdutor;
	}

	public void setNomeArtProdutor(String nomeArtProdutor) {
		this.nomeArtProdutor = nomeArtProdutor;
	}

	public String getContactoProdutor() {
		return contactoProdutor;
	}

	public void setContactoProdutor(String contactoProdutor) {
		this.contactoProdutor = contactoProdutor;
	}

	public String getEmailProdutor() {
		return emailProdutor;
	}

	public void setEmailProdutor(String emailProdutor) {
		this.emailProdutor = emailProdutor;
	}
	
	
}
