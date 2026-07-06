package controller;

import java.util.List;

import model.Musico;
import dao.MusicoDAO;

public class MusicoController {
	private MusicoDAO MusicoDAO;
	
	public MusicoController() {
		this.MusicoDAO = new MusicoDAO();
	}
	
    public int cadastrarMusico(Musico Musico) {
        return MusicoDAO.inserir(Musico);
    }
    
    public List<Musico> listarMusicos() {
        return MusicoDAO.listarTodos();
    }
}
