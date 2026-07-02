// Compositor.java
package model;

public class Compositor {
    private int codigoCompositor;
    private String nomeCompositor;
    private String apelidoCompositor;
    private String contactoCompositor;

    public Compositor(String nomeCompositor, String apelidoCompositor, String contactoCompositor) {
        this.nomeCompositor = nomeCompositor;
        this.apelidoCompositor = apelidoCompositor;
        this.contactoCompositor = contactoCompositor;
    }

    public Compositor(int codigoCompositor, String nomeCompositor,
            String apelidoCompositor, String contactoCompositor) {
        this(nomeCompositor, apelidoCompositor, contactoCompositor);
        this.codigoCompositor = codigoCompositor;
    }

    public int getCodigoCompositor() { return codigoCompositor; }
    public void setCodigoCompositor(int codigoCompositor) { this.codigoCompositor = codigoCompositor; }
    public String getNomeCompositor() { return nomeCompositor; }
    public void setNomeCompositor(String nomeCompositor) { this.nomeCompositor = nomeCompositor; }
    public String getApelidoCompositor() { return apelidoCompositor; }
    public void setApelidoCompositor(String apelidoCompositor) { this.apelidoCompositor = apelidoCompositor; }
    public String getContactoCompositor() { return contactoCompositor; }
    public void setContactoCompositor(String contactoCompositor) { this.contactoCompositor = contactoCompositor; }
}