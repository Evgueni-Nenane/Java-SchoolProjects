package controller;

import dao.LoginDAO;

public class LoginController {
	private LoginDAO loginDAO;
	private LogsController logController;
	
	
	public LoginController() {
		loginDAO = new LoginDAO();
		logController = new LogsController();
	}
	
	public boolean login(String nome, String senha) {
		return loginDAO.login(nome, senha, logController);
	}
	
	public boolean isPrimeiroAcesso(String username, String senha) {
		return loginDAO.isPrimeiroAcesso(username, senha);
	}
	
	public boolean atualizarSenha(String username, String senhaAntiga, String senhaNova) {
		return loginDAO.atualizarSenha(username, senhaAntiga, senhaNova);
	}
	
	public boolean resetarSenha(int codigoUser, String senha) {
		return loginDAO.resetarSenha(codigoUser, senha);
	}
}
