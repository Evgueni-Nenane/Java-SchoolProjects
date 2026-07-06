package model;

import java.util.Date;

public class Edicao {
    private int codigoDisco;
    private int codigoEditora;
    private Date dataEdicao;
	
    public Edicao(int codigoDisco, int codigoEditora, Date dataEdicao) {
		this.codigoDisco = codigoDisco;
		this.codigoEditora = codigoEditora;
		this.dataEdicao = dataEdicao;
	}

	public int getCodigoDisco() {
		return codigoDisco;
	}

	public int getCodigoEditora() {
		return codigoEditora;
	}

	public Date getDataEdicao() {
		return dataEdicao;
	}
    
    
    
}