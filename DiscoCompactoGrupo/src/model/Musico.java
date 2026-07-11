package model;

import java.util.List;

public class Musico {
    private int codigoMusico;
    private String nomeMusico;
    private String apelidoMusico;
    private List<Instrumento> instrumento;
    private String contactoMusico;
    private String emailMusico;

    public Musico() {}
    
    public Musico(String nomeMusico, String apelidoMusico,
            List<Instrumento> instrumento, String contactoMusico, String emailMusico) {
        this.nomeMusico = nomeMusico;
        this.apelidoMusico = apelidoMusico;
        this.instrumento = instrumento;
        this.contactoMusico = contactoMusico;
        this.emailMusico = emailMusico;
    }

    public Musico(int codigoMusico, String nomeMusico, String apelidoMusico,
    		List<Instrumento> instrumento, String contactoMusico, String emailMusico) {
        this(nomeMusico, apelidoMusico, instrumento, contactoMusico, emailMusico);
        this.codigoMusico = codigoMusico;
    }

	public int getCodigoMusico() {
		return codigoMusico;
	}

	public void setCodigoMusico(int codigoMusico) {
		this.codigoMusico = codigoMusico;
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

	public List<Instrumento> getInstrumento() {
		return instrumento;
	}

	public void setInstrumento(List<Instrumento> instrumento) {
		this.instrumento = instrumento;
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
	
	public String getNomeCompleto() {
		return nomeMusico + " " + apelidoMusico;
	}
    
}