package controller;

import java.util.List;

import model.DiscoCompacto;
import dao.DiscoDAO;

public class DiscoController {

    private DiscoDAO discoDAO;

    public DiscoController() {
        this.discoDAO = new DiscoDAO();
    }

    public boolean cadastrarDisco(DiscoCompacto disco) {
        return discoDAO.inserir(disco);
    }

    public List<DiscoCompacto> listarDiscos() {
        return discoDAO.listarTodos();
    }

//    public DiscoCompacto buscarDisco(int codigoDisco) {
//        return discoDAO.buscarPorId(codigoDisco);
//    }
//
//    public void actualizarDisco(DiscoCompacto disco) {
//        discoDAO.atualizar(disco);
//    }

    public boolean removerDisco(int codigoDisco) {
        return discoDAO.remover(codigoDisco);
    }
}