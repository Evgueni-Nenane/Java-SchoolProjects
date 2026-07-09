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
    
    public Utilizador listarPorId(int codigoUser) {
    	return utilizadorDAO.buscarPorId(codigoUser);
    }
    public boolean suspenderUtilizador(int codigo) {
    	return utilizadorDAO.remover(codigo);
    }
    
    public boolean adicionarFotoUser(int codigo, Utilizador utilizador) {
        return utilizadorDAO.adicionarFoto(codigo, utilizador);
    }
    
    public boolean atualizarUser(int codigo, Utilizador utilizador) {
        return utilizadorDAO.atualizarUser(codigo, utilizador);
    }
    public byte[] buscarFoto(int codigoUser) {
        return utilizadorDAO.buscarFoto(codigoUser);
    }
}