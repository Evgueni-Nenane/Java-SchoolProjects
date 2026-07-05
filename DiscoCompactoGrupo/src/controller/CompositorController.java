package controller;

import java.util.List;

import dao.CompositorDAO;
import model.Compositor;


public class CompositorController {
	private CompositorDAO compositorDAO;
	
	public CompositorController() {
		this.compositorDAO = new CompositorDAO();
	}
	
    public boolean cadastrarCompositor(Compositor compositor) {
        return compositorDAO.inserir(compositor);
    }
    
    public List<Compositor> listarCompositor() {
        return compositorDAO.listarTodos();
    }
}
