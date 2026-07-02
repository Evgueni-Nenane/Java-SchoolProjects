// Editora.java
package model;

public class Editora {
    private int codigoEditora;
    private String nomeEditora;
    private String emailEditora;
    private String endereco;

    public Editora(String nomeEditora, String emailEditora, String endereco) {
        this.nomeEditora = nomeEditora;
        this.emailEditora = emailEditora;
        this.endereco = endereco;
    }

    public Editora(int codigoEditora, String nomeEditora, String emailEditora, String endereco) {
        this(nomeEditora, emailEditora, endereco);
        this.codigoEditora = codigoEditora;
    }

    public int getCodigoEditora() { return codigoEditora; }
    public void setCodigoEditora(int codigoEditora) { this.codigoEditora = codigoEditora; }
    public String getNomeEditora() { return nomeEditora; }
    public void setNomeEditora(String nomeEditora) { this.nomeEditora = nomeEditora; }
    public String getEmailEditora() { return emailEditora; }
    public void setEmailEditora(String emailEditora) { this.emailEditora = emailEditora; }
    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }

    @Override
    public String toString() {
    	return getNomeEditora();
    }
}