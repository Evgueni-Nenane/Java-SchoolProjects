package controller;

import java.util.List;

import dao.MusicoDAO;
import model.Musico;

public class MusicoController {
	private MusicoDAO MusicoDAO;
	
	public MusicoController() {
		this.MusicoDAO = new MusicoDAO();
	}
	
    public boolean cadastrarMusico(Musico Musico) {
        return MusicoDAO.inserir(Musico);
    }
    
    public List<Musico> listarMusicos() {
        return MusicoDAO.listarTodos();
    }
}
