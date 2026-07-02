// Gravadora.java
package model;

public class Gravadora {
    private int codigoGravadora;
    private String nomeGravadora;
    private String contactoGravadora;
    private String enderecoGravadora;
    private String emailGravadora;

    public Gravadora(String nomeGravadora, String contactoGravadora,
            String enderecoGravadora, String emailGravadora) {
        this.nomeGravadora = nomeGravadora;
        this.contactoGravadora = contactoGravadora;
        this.enderecoGravadora = enderecoGravadora;
        this.emailGravadora = emailGravadora;
    }

    public Gravadora(int codigoGravadora, String nomeGravadora, String contactoGravadora,
            String enderecoGravadora, String emailGravadora) {
        this(nomeGravadora, contactoGravadora, enderecoGravadora, emailGravadora);
        this.codigoGravadora = codigoGravadora;
    }

    public int getCodigoGravadora() { return codigoGravadora; }
    public void setCodigoGravadora(int codigoGravadora) { this.codigoGravadora = codigoGravadora; }
    public String getNomeGravadora() { return nomeGravadora; }
    public void setNomeGravadora(String nomeGravadora) { this.nomeGravadora = nomeGravadora; }
    public String getContactoGravadora() { return contactoGravadora; }
    public void setContactoGravadora(String contactoGravadora) { this.contactoGravadora = contactoGravadora; }
    public String getEnderecoGravadora() { return enderecoGravadora; }
    public void setEnderecoGravadora(String enderecoGravadora) { this.enderecoGravadora = enderecoGravadora; }
    public String getEmailGravadora() { return emailGravadora; }
    public void setEmailGravadora(String emailGravadora) { this.emailGravadora = emailGravadora; }
}