package controller;

import java.util.List;

import dao.GravadoraDAO;
import model.Gravadora;

public class GravadoraController {

    private GravadoraDAO gravadoraDAO;

    public GravadoraController() {
        this.gravadoraDAO = new GravadoraDAO();
    }

    public boolean cadastrarGravadora(Gravadora gravadora) {
        return gravadoraDAO.inserir(gravadora);
    }
    
    public List<Gravadora> listarGravadoras() {
        return gravadoraDAO.listarTodos();
    }
}