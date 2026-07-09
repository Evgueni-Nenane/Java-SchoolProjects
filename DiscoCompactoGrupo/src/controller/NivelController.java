package controller;

import java.util.List;

import dao.NivelAcessoDAO;
import model.NivelAcesso;

public class NivelController {
	private NivelAcessoDAO nivelDAO;
	
	public NivelController() {
		nivelDAO = new NivelAcessoDAO();
	}
	
	public boolean adicionarNivel(String nome) {
		return nivelDAO.inserir(nome);
	}
	
	public List<NivelAcesso> listarNiveis() {
		return nivelDAO.listarNiveis();
	}
}
