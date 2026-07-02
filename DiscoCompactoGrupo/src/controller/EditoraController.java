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
}
