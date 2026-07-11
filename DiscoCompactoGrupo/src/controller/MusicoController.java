package controller;

import java.util.List;

import model.Instrumento;
import model.Musico;
import dao.InstrumentoDAO;
import dao.MusicoDAO;

public class MusicoController {
	private MusicoDAO musicoDAO;
	private InstrumentoDAO instrumentoDAO;
	
	public MusicoController() {
		this.musicoDAO = new MusicoDAO();
		this.instrumentoDAO = new InstrumentoDAO();
	}
	
    public int cadastrarMusico(Musico musico) {
    		int codigoMusico = musicoDAO.inserir(musico);
    		
    		if (codigoMusico == -1) {
    			return -1;
    		}
    		
    		for (Instrumento instrumento : musico.getInstrumento()) {
    				instrumentoDAO.inserRelacaoMusicoInstrumento(codigoMusico, instrumento.getCodigo());
    			}
    		return 1;
        
    }
    
    public List<Musico> listarMusicos() {
        return musicoDAO.listarTodos();
    }
    
    public Musico buscarPorCodigo(int codigo) {
    		return musicoDAO.buscarPorCodigo(codigo);
    }

    public boolean atualizarMusico(Musico musico) {
    	return musicoDAO.atualizar(musico);
    }
    
    public boolean removerMusico(int codigo) {
    	return musicoDAO.remover(codigo);
    }
    
}
