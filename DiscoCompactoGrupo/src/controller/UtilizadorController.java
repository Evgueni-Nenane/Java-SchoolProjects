package controller;

import java.util.List;

import dao.UtilizadorDAO;
import model.Utilizador;

public class UtilizadorController {

    private UtilizadorDAO utilizadorDAO;

    public UtilizadorController() {
        this.utilizadorDAO = new UtilizadorDAO();
    }

    public boolean cadastrarUtilizador(Utilizador utilizador) {
        return utilizadorDAO.inserir(utilizador);
    }
    
    public List<Utilizador> listarUtilizador() {
        return utilizadorDAO.listarTodos();
    }
    
    public boolean suspenderUtilizador(int codigo) {
    	return utilizadorDAO.remover(codigo);
    }
}