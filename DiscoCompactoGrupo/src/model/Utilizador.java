package model;

public class Utilizador {
	private int codigo;
	private String nome;
	private String nomeCompleto;
	private String apelido;
	private String user_name;
	private Sexo genero;
	private NivelAcesso perfil;
	private String email;
	private String contacto;
	private String senha;
	private boolean primeiroAcesso;
	
	public Utilizador(String nome, String apelido, String user_name, Sexo genero, NivelAcesso perfil, String email,
			String contacto, String senha, boolean primeiroAcesso) {
		this.nome = nome;
		this.apelido = apelido;
		this.user_name = user_name;
		this.genero = genero;
		this.perfil = perfil;
		this.email = email;
		this.contacto = contacto;
		this.senha = senha;
		this.primeiroAcesso = primeiroAcesso;
	}
	public Utilizador(int codigo, String nome, String apelido, String user_name, Sexo genero, NivelAcesso perfil,
			String email, String contacto) {
		this.codigo = codigo;
	    this.nome = nome;
	    this.apelido = apelido;
	    this.user_name = user_name;
	    this.genero = genero;
	    this.perfil = perfil;
	    this.email = email;
	    this.contacto = contacto;
	}
	public Utilizador(String user_name, NivelAcesso perfil) {
		this.user_name = user_name;
		this.perfil = perfil;
	}
	public Utilizador(String user_name, boolean primeiroAcesso) {
		this.user_name = user_name;
		this.primeiroAcesso = primeiroAcesso;
	}

public Utilizador(String nomeCompleto, String contacto, String senha) {
    this.nomeCompleto = nomeCompleto;
    this.contacto = contacto;
    this.senha = senha;
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
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public Sexo getGenero() {
		return genero;
	}
	public void setGenero(Sexo genero) {
		this.genero = genero;
	}
	public NivelAcesso getPerfil() {
		return perfil;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getContacto() {
		return contacto;
	}
	public void setContacto(String contacto) {
		this.contacto = contacto;
	}
	public String getSenha() {
		return senha;
	}
	public int getCodigo() {
		return codigo;
	}
	public boolean isPrimeiroAcesso() {
		return primeiroAcesso;
	}
	public void setPrimeiroAcesso(boolean primeiroAcesso) {
		this.primeiroAcesso = primeiroAcesso;
	}
	
	public String toString() {
		return "====================================\r\n"
				+ "======= Credenciais de " + nome + " =======\r\n"
				+ "======================================\r\n"
				+ "==----------------------------------==\r\n"
				+ "--- Nome:       " + nome + "	     ---\r\n"
				+ "--- Apelido:    " + apelido + "	 ---\r\n"
				+ "--- Username:   "+ user_name + "	 ---\r\n"
				+ "--- Senha:      "+ senha +" 	     ---\r\n"
				+ "--- Contacto:   "+ contacto +"     ---\r\n"
				+ "==--------------------------------==\r\n"
				+ "====================================\r\n"
				+ "\r\n"
				+ "";
	}

	public String toStringReset() {
		return "===========================================\r\n"
				+ "======= Credenciais de "+nomeCompleto+"=======\r\n"
				+ "===========================================\r\n"
				+ "==---------------------------------------==\r\n"
				+ "--- Nome:       "+ nomeCompleto+ "	     	---\r\n"
				+ "--- Senha:      "+ senha +" 			---\r\n"
				+ "--- Contacto:   "+ contacto +"    	  	---\r\n"
				+ "==---------------------------------------==\r\n"
				+ "===========================================";
	}


}