package controller;

import java.util.List;

import dao.CantorDAO;
import model.Cantor;


public class CantorController {
	private CantorDAO cantorDAO;
	
	public CantorController() {
		this.cantorDAO = new CantorDAO();
	}
	
    public int cadastrarCantor(Cantor Cantor) {
        return cantorDAO.inserir(Cantor);
    }
    
    public List<Cantor> listarCantor() {
        return cantorDAO.listarTodos();
    }
    
    public Cantor buscarPorCodigo(int codigo) {
    		return cantorDAO.buscarPorCodigo(codigo);
    }
    
    public boolean atualizarCantor(Cantor cantor) {
    	return cantorDAO.atualizar(cantor);
    }
    
    public boolean removerCantor(int codigo) {
    	return cantorDAO.remover(codigo);
    }
}
