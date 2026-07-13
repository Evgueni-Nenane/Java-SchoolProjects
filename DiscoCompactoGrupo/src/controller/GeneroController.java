package controller;

import java.util.List;

import dao.GeneroDAO;
import model.Genero;

public class GeneroController {

    private GeneroDAO generoDAO;

    public GeneroController() {
        generoDAO = new GeneroDAO();
    }

    public boolean adicionarGenero(Genero genero) {
        return generoDAO.inserir(genero);
    }
    
    public List<Genero> listarGeneros() {
        return generoDAO.listarTodos();
    }
    
    public boolean removerGenero(int codigo) {
    		return generoDAO.remover(codigo);
    }

}