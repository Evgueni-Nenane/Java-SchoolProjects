package controller;

import java.util.List;

import dao.CompositorDAO;
import model.Compositor;


public class CompositorController {
	private CompositorDAO compositorDAO;
	
	public CompositorController() {
		this.compositorDAO = new CompositorDAO();
	}
	
    public int cadastrarCompositor(Compositor compositor) {
        return compositorDAO.inserir(compositor);
    }
    
    public List<Compositor> listarCompositor() {
        return compositorDAO.listarTodos();
    }
    
    public Compositor buscarPorCodigo(int codigo) {
    		return compositorDAO.buscarPorCodigo(codigo);
    }
    
    public boolean atualizarCompositor(Compositor compositor) {
    		return compositorDAO.atualizar(compositor);
    }
    
    public boolean removerCompositor(int codigo) {
    		return compositorDAO.remover(codigo);
    }
}
