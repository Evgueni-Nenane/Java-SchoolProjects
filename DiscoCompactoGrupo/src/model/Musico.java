package model;

public class Musico {
    private int codigoMusico;
    private String nomeMusico;
    private String apelidoMusico;
    private String instrumento;
    private String emailMusico;

    public Musico(String nomeMusico, String apelidoMusico,
            String instrumento, String emailMusico) {
        this.nomeMusico = nomeMusico;
        this.apelidoMusico = apelidoMusico;
        this.instrumento = instrumento;
        this.emailMusico = emailMusico;
    }

    public Musico(int codigoMusico, String nomeMusico, String apelidoMusico,
    		String instrumento, String emailMusico) {
        this(nomeMusico, apelidoMusico, instrumento, emailMusico);
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

	public String getInstrumento() {
		return instrumento;
	}

	public void setInstrumento(String instrumento) {
		this.instrumento = instrumento;
	}

	public String getEmailMusico() {
		return emailMusico;
	}

	public void setEmailMusico(String emailMusico) {
		this.emailMusico = emailMusico;
	}

    
}