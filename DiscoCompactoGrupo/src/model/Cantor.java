// Cantor.java
package model;

public class Cantor {
    private int codigoCantor;
    private String nomeCantor;
    private String apelidoCantor;
    private String contactoCantor;
    private String emailCantor;

    public Cantor(String nomeCantor, String apelidoCantor,
            String contactoCantor, String emailCantor) {
        this.nomeCantor = nomeCantor;
        this.apelidoCantor = apelidoCantor;
        this.contactoCantor = contactoCantor;
        this.emailCantor = emailCantor;
    }

    public Cantor(int codigoCantor, String nomeCantor, String apelidoCantor, String contactoCantor, String emailCantor) {
        this(nomeCantor, apelidoCantor, contactoCantor, emailCantor);
        this.codigoCantor = codigoCantor;
    }

    public int getCodigoCantor() { return codigoCantor; }
    public void setCodigoCantor(int codigoCantor) { this.codigoCantor = codigoCantor; }
    public String getNomeCantor() { return nomeCantor; }
    public void setNomeCantor(String nomeCantor) { this.nomeCantor = nomeCantor; }
    public String getApelidoCantor() { return apelidoCantor; }
    public void setApelidoCantor(String apelidoCantor) { this.apelidoCantor = apelidoCantor; }
    public String getContactoCantor() { return contactoCantor; }
    public void setContactoCantor(String contactoCantor) { this.contactoCantor = contactoCantor; }
    public String getEmailCantor() { return emailCantor; }
    public void setEmailCantor(String emailCantor) { this.emailCantor = emailCantor; }
}