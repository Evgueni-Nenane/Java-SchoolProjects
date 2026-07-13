package controller;
import java.util.Date;
import java.util.List;
import model.Cantor;
import model.Compositor;
import model.DiscoCompacto;
import model.Edicao;
import model.Editora;
import model.Genero;
import model.Gravadora;
import model.Musico;
import model.Produtor;
import dao.CantorDAO;
import dao.CompositorDAO;
import dao.DiscoDAO;
import dao.EdicaoDAO;
import dao.EditoraDAO;
import dao.GeneroDAO;
import dao.GravadoraDAO;
import dao.MusicoDAO;
import dao.ProdutorDAO;
public class DiscoController {
    private DiscoDAO discoDAO;
    private GravadoraDAO gravadoraDAO;
    private EditoraDAO editoraDAO;
    private ProdutorDAO produtorDAO;
    private CompositorDAO compositorDAO;
    private MusicoDAO musicoDAO;
    private CantorDAO cantorDAO;
    private EdicaoDAO edicaoDAO;
    private GeneroDAO generoDAO;
    public DiscoController() {
        this.discoDAO = new DiscoDAO();
        this.gravadoraDAO = new GravadoraDAO();
        this.editoraDAO = new EditoraDAO();
        this.produtorDAO = new ProdutorDAO();
        this.compositorDAO = new CompositorDAO();
        this.musicoDAO = new MusicoDAO();
        this.cantorDAO = new CantorDAO();
        this.edicaoDAO = new EdicaoDAO();
        this.generoDAO = new GeneroDAO();
    
    }
    public int cadastrarDisco(DiscoCompacto disco, Date dataEdicaoCompleta) {
	    	int codigoDisco = discoDAO.inserir(disco);
	        
	    	if (codigoDisco == -1) {
	    		return -1;
	    	}

	    	for (Genero genero : disco.getGeneroMusical()) {
	    		generoDAO.inserRelacaoGeneroDisco(codigoDisco, genero.getCodigoGenero());
	    	}

	    	for (Compositor compositor : disco.getCompositores()) {
	    		compositorDAO.inserRelacaoDiscoCompositor(codigoDisco, compositor.getCodigoCompositor());
	    	}
	    	
	    	for (Musico musico : disco.getMusicos()) {
	    		musicoDAO.inserRelacaoDiscoMusico(codigoDisco, musico.getCodigoMusico());
	    	}
	    	
	    	for (Cantor cantor : disco.getCantores()) {
	    		cantorDAO.inserRelacaoDiscoCantor(codigoDisco, cantor.getCodigoCantor());
	    	}
	    	
	    	for (Gravadora gravadora : disco.getGravadoras()) {
	    		gravadoraDAO.inserRelacaoDiscoGravadora(codigoDisco, gravadora.getCodigoGravadora());
	    	}
	    	
	    	for (Produtor produtor : disco.getProdutores()) {
	    		produtorDAO.inserRelacaoDiscoProdutor(codigoDisco, produtor.getCodigoProdutor());
	    	}
	    	
	    	for (Editora editora : disco.getEditoras()) {
	    		Edicao edicao = new Edicao(codigoDisco, editora.getCodigoEditora(), dataEdicaoCompleta);
	    		edicaoDAO.inserir(edicao);
	    	}
	        Edicao edicao = edicaoDAO.buscarPorCodigoDisco(codigoDisco);
	        disco.setEdicao(edicao);
	    	
	    	return 1;   
    }
    public List<DiscoCompacto> listarDiscos() {
        return discoDAO.listarTodos();
    }
    public void actualizarDisco(DiscoCompacto disco) {
        discoDAO.atualizar(disco);

        generoDAO.removerRelacoesPorDisco(disco.getCodigoDisco());
        for (Genero genero : disco.getGeneroMusical()) {
        		generoDAO.inserRelacaoGeneroDisco(disco.getCodigoDisco(), genero.getCodigoGenero());
        }
    }
    public boolean removerDisco(int codigoDisco) {
        return discoDAO.remover(codigoDisco);
    }
    
    public DiscoCompacto buscarDiscoCompleto(int codigoDisco) {
	    	DiscoCompacto disco = discoDAO.buscarPorCodigo(codigoDisco);
	    	    
	    	if (disco == null) {
	    		return null;
    	}
    	    
	    	disco.setGeneroMusical(generoDAO.listarPorDisco(codigoDisco));
	    	disco.setCompositores(compositorDAO.listarPorCodigo(codigoDisco));
	    	disco.setMusicos(musicoDAO.listarPorCodigo(codigoDisco));
	    	disco.setCantores(cantorDAO.listarPorCodigo(codigoDisco));
	    	disco.setProdutores(produtorDAO.listarPorCodigo(codigoDisco));
	    	disco.setGravadoras(gravadoraDAO.listarPorCodigo(codigoDisco));
	    	disco.setEditoras(editoraDAO.listarPorCodigo(codigoDisco));
	    	disco.setEdicao(edicaoDAO.buscarPorCodigoDisco(codigoDisco));
	    	return disco;
    }
}
