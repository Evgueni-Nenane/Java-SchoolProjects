package controller;

import java.util.List;

import dao.EditoraDAO;
import model.Editora;

public class EditoraController {
	private EditoraDAO editoraDAO;
	
	public EditoraController() {
		this.editoraDAO = new EditoraDAO();
	}
	
    public boolean cadastrarEditora(Editora editora) {
        return editoraDAO.inserir(editora);
    }
    
    public List<Editora> listarEditoras() {
        return editoraDAO.listarTodos();
    }
    
    public Editora buscarPorCodigo(int codigo) {
    		return editoraDAO.buscarPorCodigo(codigo);
    }
    
    public boolean atualizarEditora(Editora editora) {
    		return editoraDAO.atualizar(editora);
    }
    
    public boolean removerEditora(int codigo) {
    		return editoraDAO.remover(codigo);
    }
}
