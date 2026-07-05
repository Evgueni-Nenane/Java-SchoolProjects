package controller;

import java.util.List;

import dao.CantorDAO;
import model.Cantor;


public class CantorController {
	private static CantorDAO cantorDAO;
	
	public CantorController() {
		CantorController.cantorDAO = new CantorDAO();
	}
	
    public static boolean cadastrarCantor(Cantor Cantor) {
        return cantorDAO.inserir(Cantor);
    }
    
    public List<Cantor> listarCantor() {
        return cantorDAO.listarTodos();
    }
}
