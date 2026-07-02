// Banda.java
package model;

import java.util.List;

public class Banda {
    private int codigoBanda;
    private String nomeBanda;
    private List<Cantor> cantores;
    private List<Musico> musicos;
    private List<Compositor> compositores;

    public Banda(String nomeBanda, List<Cantor> cantores, 
            List<Musico> musicos, List<Compositor> compositores) {
        this.nomeBanda = nomeBanda;
        this.cantores = cantores;
        this.musicos = musicos;
        this.compositores = compositores;
    }

    public Banda(int codigoBanda, String nomeBanda, List<Cantor> cantores,
            List<Musico> musicos, List<Compositor> compositores) {
        this(nomeBanda, cantores, musicos, compositores);
        this.codigoBanda = codigoBanda;
    }

    public int getCodigoBanda() { return codigoBanda; }
    public void setCodigoBanda(int codigoBanda) { this.codigoBanda = codigoBanda; }
    public String getNomeBanda() { return nomeBanda; }
    public void setNomeBanda(String nomeBanda) { this.nomeBanda = nomeBanda; }
    public List<Cantor> getCantores() { return cantores; }
    public void setCantores(List<Cantor> cantores) { this.cantores = cantores; }
    public List<Musico> getMusicos() { return musicos; }
    public void setMusicos(List<Musico> musicos) { this.musicos = musicos; }
    public List<Compositor> getCompositores() { return compositores; }
    public void setCompositores(List<Compositor> compositores) { this.compositores = compositores; }
}