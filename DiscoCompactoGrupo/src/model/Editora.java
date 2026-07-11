// Editora.java
package model;

public class Editora {
    private int codigoEditora;
    private String nomeEditora;
    private String contactoEditora;
    private String emailEditora;
    private String endereco;

    	public Editora() {}
    
    public Editora(String nomeEditora, String contactoEditora, String emailEditora, String endereco) {
        this.nomeEditora = nomeEditora;
        this.contactoEditora = contactoEditora;
        this.emailEditora = emailEditora;
        this.endereco = endereco;
    }

    public Editora(int codigoEditora, String nomeEditora, String contactoEditora, String emailEditora, String endereco) {
        this(nomeEditora,contactoEditora, emailEditora, endereco);
        this.codigoEditora = codigoEditora;
    }

	public int getCodigoEditora() {
		return codigoEditora;
	}

	public void setCodigoEditora(int codigoEditora) {
		this.codigoEditora = codigoEditora;
	}

	public String getNomeEditora() {
		return nomeEditora;
	}

	public void setNomeEditora(String nomeEditora) {
		this.nomeEditora = nomeEditora;
	}

	public String getContactoEditora() {
		return contactoEditora;
	}

	public void setContactoEditora(String contactoEditora) {
		this.contactoEditora = contactoEditora;
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