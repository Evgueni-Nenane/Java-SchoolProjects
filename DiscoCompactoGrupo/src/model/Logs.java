package model;

import java.time.LocalDateTime;

public class Logs {
	private int codigo;
	private String nome;
	private String apelido;
	private String perfil;
	private String email;
	private String accao;
	private LocalDateTime dataHora;
	
	public Logs(String nome, String apelido, String perfil, String email, String accao, LocalDateTime dataHora) {
		this.nome = nome;
		this.apelido = apelido;
		this.perfil = perfil;
		this.email = email;
		this.accao = accao;
		this.dataHora = dataHora;
	}
	public Logs(int codigo, String nome, String apelido, String perfil, String email, String accao, LocalDateTime dataHora) {
		this.codigo = codigo;
		this.nome= nome;
		this.apelido = apelido;
		this.perfil = perfil;
		this.email = email;
		this.accao = accao;
		this.dataHora = dataHora;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getApelido() {
		return apelido;
	}
	public void setApelido(String apelido) {
		this.apelido = apelido;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAccao() {
		return accao;
	}

	public void setAccao(String accao) {
		this.accao = accao;
	}

	public LocalDateTime getDataHora() {
		return dataHora;
	}

	public void setDataHora(LocalDateTime dataHora) {
		this.dataHora = dataHora;
	}

	public int getCodigo() {
		return codigo;
	}

	public String getPerfil() {
		return perfil;
	}
	
	
}
