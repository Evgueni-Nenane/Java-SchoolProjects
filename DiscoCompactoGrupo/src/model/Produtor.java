// Produtor.java
package model;

public class Produtor {
    private int codigoProdutor;
    private String nomeProdutor;
    private String apelidoProdutor;
    private String nomeArtProdutor;
    private String contactoProdutor;
    private String emailProdutor;

    public Produtor(String nomeProdutor, String apelidoProdutor, String nomeArtProdutor,
            String contactoProdutor, String emailProdutor) {
        this.nomeProdutor = nomeProdutor;
        this.apelidoProdutor = apelidoProdutor;
        this.nomeArtProdutor = nomeArtProdutor;
        this.contactoProdutor = contactoProdutor;
        this.emailProdutor = emailProdutor;
    }

    public Produtor(int codigoProdutor, String nomeProdutor, String apelidoProdutor,
            String nomeArtProdutor, String contactoProdutor, String emailProdutor) {
        this(nomeProdutor, apelidoProdutor, nomeArtProdutor, contactoProdutor, emailProdutor);
        this.codigoProdutor = codigoProdutor;
    }

    public int getCodigoProdutor() { return codigoProdutor; }
    public void setCodigoProdutor(int codigoProdutor) { this.codigoProdutor = codigoProdutor; }
    public String getNomeProdutor() { return nomeProdutor; }
    public void setNomeProdutor(String nomeProdutor) { this.nomeProdutor = nomeProdutor; }
    public String getApelidoProdutor() { return apelidoProdutor; }
    public void setApelidoProdutor(String apelidoProdutor) { this.apelidoProdutor = apelidoProdutor; }
    public String getNomeArtProdutor() { return nomeArtProdutor; }
    public void setNomeArtProdutor(String nomeArtProdutor) { this.nomeArtProdutor = nomeArtProdutor; }
    public String getContactoProdutor() { return contactoProdutor; }
    public void setContactoProdutor(String contactoProdutor) { this.contactoProdutor = contactoProdutor; }
    public String getEmailProdutor() { return emailProdutor; }
    public void setEmailProdutor(String emailProdutor) { this.emailProdutor = emailProdutor; }
}