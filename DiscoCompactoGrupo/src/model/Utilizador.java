package model;

public class Utilizador {
	private int codigo;
	private byte[] foto;
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
	
	public Utilizador(byte[] foto, String nome, String apelido, String user_name, Sexo genero, NivelAcesso perfil, String email,
			String contacto, String senha, boolean primeiroAcesso) {
		this.foto = foto;
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
	public Utilizador(byte[] foto, String nome, String apelido, Sexo genero, NivelAcesso perfil, String email, String contacto) {
		this.foto = foto;
	    this.nome = nome;
	    this.apelido = apelido;
	    this.genero = genero;
	    this.perfil = perfil;
	    this.email = email;
	    this.contacto = contacto;
	}
	public Utilizador(byte[] foto, String nome, String apelido, String username, NivelAcesso perfil, String email) {
		this.foto = foto;
		this.nome = nome;
		this.apelido = apelido;
		this.user_name = username;
		this.perfil = perfil;
		this.email = email;
	}
	public Utilizador(byte[] foto, NivelAcesso perfil, String email, String contacto) {
		this.foto = foto;
		this.perfil = perfil;
		this.email = email;
		this.contacto = contacto;
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
	
	
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public void setPerfil(NivelAcesso perfil) {
		this.perfil = perfil;
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
	
	public String getNomeCompleto() {
		return nomeCompleto;
	}
	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
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
	public byte[] getFoto() {
		return foto;
	}
	public void setFoto(byte[] foto) {
		this.foto = foto;
	}
	
	@Override
	public String toString() {
	    return "======= Credenciais de " + this.nome + " =======\n"
	         + "--- Nome:\t" + this.nome + "\n"
	         + "--- Apelido:\t" + this.apelido + "\n"
	         + "--- Username:\t" + this.user_name + "\n"
	         + "--- Senha:\t" + this.senha + "\n"
	         + "--- Contacto:\t" + this.contacto + "\n";
	    }


	public String toStringReset() {
	    return "======= Credenciais de " + nomeCompleto + " =======\n"
	         + "--- Nome:\t" + nomeCompleto + "\n"
	         + "--- Senha:\t" + senha  + "\n"
	         + "--- Contacto:\t" + contacto  + "\n";
	    }
}