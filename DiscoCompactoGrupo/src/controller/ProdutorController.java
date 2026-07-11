package controller;

import java.util.List;

import dao.ProdutorDAO;
import model.Produtor;


public class ProdutorController {
	private ProdutorDAO produtorDAO;
	
	public ProdutorController() {
		this.produtorDAO = new ProdutorDAO();
	}
	
    public int cadastrarProdutor(Produtor produtor) {
        return produtorDAO.inserir(produtor);
    }
    
    public List<Produtor> listarProdutor() {
        return produtorDAO.listarTodos();
    }
    
    public Produtor buscarPorCodigo(int codigo) {
    		return produtorDAO.buscarPorCodigo(codigo);
    }
    
    public boolean atualizarProdutor(Produtor produtor) {
    	return produtorDAO.atualizar(produtor);
    }
    
    public boolean removerProdutor(int codigo) {
    	return produtorDAO.remover(codigo);
    }
}
