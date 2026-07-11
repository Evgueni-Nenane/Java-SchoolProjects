package controller;

import java.util.List;

import dao.InstrumentoDAO;
import model.Instrumento;

public class InstrumentoController {
	private InstrumentoDAO instrumentoDAO;
	
	public InstrumentoController() {
		instrumentoDAO = new InstrumentoDAO();
	}
	
	public boolean adicionarInstrumento(Instrumento instrumento) {
		return instrumentoDAO.inserir(instrumento);
		
	}
	
	public List<Instrumento> listarInstrumentos() {
		return instrumentoDAO.listarTodos();
	}
	
	public Instrumento buscarPorCodigo(int codigo) {
		return instrumentoDAO.listarPorCodigo(codigo);
	}
	
	public boolean remover(int codigo) {
		return instrumentoDAO.remover(codigo);
	}
}
