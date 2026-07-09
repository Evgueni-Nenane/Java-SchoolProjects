package model;

public class Genero {

    private int codigoGenero;
    private String nomeGenero;

    public Genero(int codigoGenero, String nomeGenero) {
        this.codigoGenero = codigoGenero;
        this.nomeGenero = nomeGenero;
    }
    public Genero(String nomeGenero) {
    	this.nomeGenero = nomeGenero;
    }
    
    public int getCodigoGenero() {
        return codigoGenero;
    }

    public String getNomeGenero() {
        return nomeGenero;
    }

    @Override
    public String toString() {
        return nomeGenero;
    }
}