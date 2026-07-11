package controller;

import java.util.List;

import dao.GravadoraDAO;
import model.Gravadora;

public class GravadoraController {

    private GravadoraDAO gravadoraDAO;

    public GravadoraController() {
        this.gravadoraDAO = new GravadoraDAO();
    }

    public int cadastrarGravadora(Gravadora gravadora) {
        return gravadoraDAO.inserir(gravadora);
    }
    
    public List<Gravadora> listarGravadoras() {
        return gravadoraDAO.listarTodos();
    }
    
    public Gravadora buscarPorCodigo(int codigo) {
    		return gravadoraDAO.buscarPorCodigo(codigo);
    }
    
    public boolean atualizarGravadora(Gravadora gravadora) {
    	return gravadoraDAO.atualizar(gravadora);
    }
    
    public boolean removerGravadora(int codigo) {
    	return gravadoraDAO.remover(codigo);
    }
}