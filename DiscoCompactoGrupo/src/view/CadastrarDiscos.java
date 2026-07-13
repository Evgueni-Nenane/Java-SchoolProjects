package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;

import controller.CantorController;
import controller.CompositorController;
import controller.DiscoController;
import controller.EditoraController;
import controller.GeneroController;
import controller.GravadoraController;
import controller.LogsController;
import controller.MusicoController;
import controller.ProdutorController;
import model.DiscoCompacto;
import model.Genero;
import model.Logs;
import model.Sessao;
import resources.EstilizarBotao;

public class CadastrarDiscos extends JPanel implements ActionListener, MouseListener {

	private static final long serialVersionUID = 1L;
	private DiscoController discoController;
	
	private JButton btnSalvar, btnLimpar, btnBanda, btnProducao, btnGenero;
	private JTextField txtNomeDisco, txtPrecoDisco;
	private JLabel partSelecionados, partSelecionadosProd, countCompositores, countMusicos, countCantores, countProdutores, countGravadoras, countEditoras;
	private JLabel lblGenerosSelecionados;
	private JSpinner spinnerDataDisco;
	private AdicionarGeneros adicionarGeneros;
	private CompositorController compositorController;
	private MusicoController musicoController;
	private CantorController cantorController;
	private Creditos_Producao creditosProducao;
	private ProdutorController produtorController;
	private GravadoraController gravadoraController;
	private EditoraController editoraController;
	private Participantes participantes;
	private Logs log;
	private LogsController logController;
	private ListaAcoesDiscos listaAcoesDiscos;
	private GeneroController generoController;
	
	public CadastrarDiscos(DiscoController discoController, EditoraController editoraController,
			GravadoraController gravadoraController, LogsController logController, ListaAcoesDiscos listaAcoesDiscos) {
		this.discoController = discoController;
		this.listaAcoesDiscos = listaAcoesDiscos;
		this.logController = logController;
		generoController = new GeneroController();
		adicionarGeneros = new AdicionarGeneros(generoController);
		compositorController = new CompositorController();
		musicoController = new MusicoController();
		cantorController = new CantorController();
		produtorController = new ProdutorController();
		this.gravadoraController = gravadoraController;
		this.editoraController = editoraController;
		participantes = new Participantes(compositorController, musicoController, cantorController);
		creditosProducao = new Creditos_Producao(produtorController, this.gravadoraController, this.editoraController);
		
		JPanel painelPrincipal = new JPanel(new BorderLayout());
		painelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		// Topbar
		JPanel parteDescritiva = new JPanel();
		parteDescritiva.setLayout(new BoxLayout(parteDescritiva, BoxLayout.Y_AXIS));
		parteDescritiva.setPreferredSize(new Dimension(0, 50));
		parteDescritiva.setBackground(new Color(254, 254, 254));
		parteDescritiva.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
		parteDescritiva.add(Box.createVerticalGlue());
		parteDescritiva.add(new JLabel("Cadastrar Discos"));
		parteDescritiva.add(Box.createVerticalGlue());

		// Painel principal do formulário
		JPanel cadastroPanel = new JPanel(new GridBagLayout());
		cadastroPanel.setBackground(new Color(250, 251, 252));

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.weightx = 1.0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.WEST;

		// =====================
		// Informações do Disco
		// =====================
		JPanel infoDiscos = new JPanel(new BorderLayout());
		infoDiscos.setBackground(new Color(246, 247, 249));
		infoDiscos.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(220, 220, 220)));

		JLabel lblTituloDisco = new JLabel("Informações do Disco");
		lblTituloDisco.setFont(lblTituloDisco.getFont().deriveFont(13f).deriveFont(java.awt.Font.BOLD));
		lblTituloDisco.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 0));
		infoDiscos.add(lblTituloDisco, BorderLayout.NORTH);

		JPanel infoMainDisco = new JPanel(new GridBagLayout());
		infoMainDisco.setBackground(new Color(246, 247, 249));
		infoMainDisco.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));
		
		GridBagConstraints gbcDisco = new GridBagConstraints();
		gbcDisco.insets = new Insets(3, 5, 3, 5);
		gbcDisco.fill = GridBagConstraints.HORIZONTAL;
		gbcDisco.anchor = GridBagConstraints.WEST;

		// Linha 0: Labels
		gbcDisco.gridy = 0;
		gbcDisco.gridx = 0;
		gbcDisco.weightx = 0.25;
		infoMainDisco.add(new JLabel("Preço:"), gbcDisco);
		
		gbcDisco.gridx = 1;
		infoMainDisco.add(new JLabel("Data de Edição:"), gbcDisco);
		
		gbcDisco.gridx = 2;
		infoMainDisco.add(new JLabel("Gênero Musical:"), gbcDisco);

		// Linha 1: Campos
		gbcDisco.gridy = 1;
		
		gbcDisco.gridx = 0;
		txtPrecoDisco = new JTextField();
		txtPrecoDisco.setPreferredSize(new Dimension(0, 30));
		infoMainDisco.add(txtPrecoDisco, gbcDisco);
		
		gbcDisco.gridx = 1;
		spinnerDataDisco = new JSpinner(new SpinnerDateModel());
		spinnerDataDisco.setEditor(new JSpinner.DateEditor(spinnerDataDisco, "dd/MM/yyyy"));
		spinnerDataDisco.setPreferredSize(new Dimension(0, 30));
		infoMainDisco.add(spinnerDataDisco, gbcDisco);
		
		gbcDisco.gridx = 2;
		btnGenero = new JButton("Selecionar Gêneros");
		btnGenero.addActionListener(this);
		btnGenero.setBackground(new Color(19, 175, 119));
		btnGenero.setForeground(Color.WHITE);
		btnGenero.setFocusPainted(false);
		btnGenero.setBorderPainted(false);
		btnGenero.setPreferredSize(new Dimension(0, 30));
		infoMainDisco.add(btnGenero, gbcDisco);

		// Contador de gêneros
		JPanel painelContador = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
		painelContador.setBackground(new Color(246, 247, 249));
		lblGenerosSelecionados = new JLabel(adicionarGeneros.getGenerosSelecionados().size() + " gênero(s)");
		lblGenerosSelecionados.setForeground(new Color(19, 175, 119));
		painelContador.add(lblGenerosSelecionados);
		
		gbcDisco.gridy = 2;
		gbcDisco.gridx = 0;
		gbcDisco.gridwidth = 3;
		infoMainDisco.add(painelContador, gbcDisco);

		// Linha 3: Nome do Disco label
		gbcDisco.gridy = 3;
		gbcDisco.gridx = 0;
		gbcDisco.gridwidth = 3;
		infoMainDisco.add(new JLabel("Nome do Disco:"), gbcDisco);

		// Linha 4: Nome do Disco campo
		gbcDisco.gridy = 4;
		txtNomeDisco = new JTextField();
		txtNomeDisco.setPreferredSize(new Dimension(0, 30));
		infoMainDisco.add(txtNomeDisco, gbcDisco);

		infoDiscos.add(infoMainDisco, BorderLayout.CENTER);

		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		cadastroPanel.add(infoDiscos, gbc);

		// ====================================
		// Créditos do Disco
		// ====================================
		JPanel infoBandaPart = new JPanel(new BorderLayout());
		infoBandaPart.setBackground(new Color(246, 247, 249));
		infoBandaPart.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(220, 220, 220)));

		JLabel lblTituloBandaPart = new JLabel("Créditos do Disco");
		lblTituloBandaPart.setFont(lblTituloBandaPart.getFont().deriveFont(13f).deriveFont(java.awt.Font.BOLD));
		lblTituloBandaPart.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 0));
		infoBandaPart.add(lblTituloBandaPart, BorderLayout.NORTH);

		JPanel infoMainBandaPart = new JPanel(new GridLayout(1, 2, 20, 0));
		infoMainBandaPart.setBackground(new Color(246, 247, 249));
		infoMainBandaPart.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));

		// Painel Participantes
		infoMainBandaPart.add(criarPainelParticipantes());

		// Painel Produção
		infoMainBandaPart.add(criarPainelProducao());

		infoBandaPart.add(infoMainBandaPart, BorderLayout.CENTER);

		gbc.gridx = 0;
		gbc.gridy = 1;
		cadastroPanel.add(infoBandaPart, gbc);

		// ====================================
		// Botões
		// ====================================
		JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		btnPanel.setBackground(new Color(250, 251, 252));
		
		btnSalvar = new JButton("Salvar Disco");
		btnSalvar.addActionListener(this);
		btnSalvar.setBackground(new Color(19, 175, 119));
		btnSalvar.setForeground(Color.WHITE);
		btnSalvar.setFocusPainted(false);
		btnSalvar.setBorderPainted(false);
		btnSalvar.setPreferredSize(new Dimension(120, 35));

		btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(this);
		EstilizarBotao.aplicarTerc(btnLimpar);
		btnLimpar.setPreferredSize(new Dimension(120, 35));

		btnPanel.add(btnLimpar);
		btnPanel.add(btnSalvar);

		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.anchor = GridBagConstraints.EAST;
		gbc.fill = GridBagConstraints.NONE;
		cadastroPanel.add(btnPanel, gbc);

		painelPrincipal.add(parteDescritiva, BorderLayout.NORTH);
		JScrollPane scroll = new JScrollPane(cadastroPanel);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));
		scroll.getViewport().setBackground(new Color(250, 251, 252));
		painelPrincipal.add(scroll, BorderLayout.CENTER);

		this.setLayout(new BorderLayout());
		this.add(painelPrincipal, BorderLayout.CENTER);
	}

	private JPanel criarPainelParticipantes() {
		JPanel painel = new JPanel(new BorderLayout(0, 10));
		painel.setBackground(Color.WHITE);
		painel.setBorder(BorderFactory.createCompoundBorder(
			BorderFactory.createLineBorder(new Color(220, 220, 220)),
			BorderFactory.createEmptyBorder(15, 15, 15, 15)
		));

		btnBanda = new JButton("Selecionar Participantes");
		btnBanda.addActionListener(this);
		btnBanda.setBackground(new Color(19, 175, 119));
		btnBanda.setForeground(Color.WHITE);
		btnBanda.setFocusPainted(false);
		btnBanda.setBorderPainted(false);
		btnBanda.setPreferredSize(new Dimension(0, 35));
		painel.add(btnBanda, BorderLayout.NORTH);

		JPanel painelInfo = new JPanel(new BorderLayout(0, 5));
		painelInfo.setBackground(Color.WHITE);
		painelInfo.setBorder(BorderFactory.createCompoundBorder(
			BorderFactory.createLineBorder(new Color(220, 220, 220)),
			BorderFactory.createEmptyBorder(10, 10, 10, 10)
		));

		partSelecionados = new JLabel(participantes.totalSelecionados() + " Participantes Selecionados");
		partSelecionados.setFont(partSelecionados.getFont().deriveFont(java.awt.Font.BOLD));

		JPanel innerInfo = new JPanel(new GridLayout(1, 3, 5, 5));
		innerInfo.setBackground(Color.WHITE);

		countCompositores = new JLabel("Compositores: " + participantes.compositorContador());
		countMusicos = new JLabel("Músicos: " + participantes.musicoContador());
		countCantores = new JLabel("Cantores: " + participantes.cantorContador());

		innerInfo.add(countCompositores);
		innerInfo.add(countMusicos);
		innerInfo.add(countCantores);

		painelInfo.add(partSelecionados, BorderLayout.NORTH);
		painelInfo.add(innerInfo, BorderLayout.CENTER);

		painel.add(painelInfo, BorderLayout.CENTER);
		return painel;
	}

	private JPanel criarPainelProducao() {
		JPanel painel = new JPanel(new BorderLayout(0, 10));
		painel.setBackground(Color.WHITE);
		painel.setBorder(BorderFactory.createCompoundBorder(
			BorderFactory.createLineBorder(new Color(220, 220, 220)),
			BorderFactory.createEmptyBorder(15, 15, 15, 15)
		));

		btnProducao = new JButton("Selecionar Produção");
		btnProducao.addActionListener(this);
		btnProducao.setBackground(new Color(19, 175, 119));
		btnProducao.setForeground(Color.WHITE);
		btnProducao.setFocusPainted(false);
		btnProducao.setBorderPainted(false);
		btnProducao.setPreferredSize(new Dimension(0, 35));
		painel.add(btnProducao, BorderLayout.NORTH);

		JPanel painelInfo = new JPanel(new BorderLayout(0, 5));
		painelInfo.setBackground(Color.WHITE);
		painelInfo.setBorder(BorderFactory.createCompoundBorder(
			BorderFactory.createLineBorder(new Color(220, 220, 220)),
			BorderFactory.createEmptyBorder(10, 10, 10, 10)
		));

		partSelecionadosProd = new JLabel(creditosProducao.totalSelecionados() + " Membros Selecionados");
		partSelecionadosProd.setFont(partSelecionadosProd.getFont().deriveFont(java.awt.Font.BOLD));

		JPanel innerInfoProd = new JPanel(new GridLayout(1, 3, 5, 5));
		innerInfoProd.setBackground(Color.WHITE);

		countProdutores = new JLabel("Produtores: " + creditosProducao.produtorContador());
		countGravadoras = new JLabel("Gravadoras: " + creditosProducao.gravadoraContador());
		countEditoras = new JLabel("Editoras: " + creditosProducao.editoraContador());

		innerInfoProd.add(countProdutores);
		innerInfoProd.add(countGravadoras);
		innerInfoProd.add(countEditoras);

		painelInfo.add(partSelecionadosProd, BorderLayout.NORTH);
		painelInfo.add(innerInfoProd, BorderLayout.CENTER);

		painel.add(painelInfo, BorderLayout.CENTER);
		return painel;
	}

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnSalvar) {
			salvarDisco();
		}
		if(e.getSource() == btnLimpar){
			limparCampos();
		}
		if (e.getSource() == btnBanda) {
			participantes.setVisible(true);
			atualizarContadoresParticipantes();
		}
		if (e.getSource() == btnProducao) {
			creditosProducao.setVisible(true);
			atualizarContadoresProducao();
		}
		if (e.getSource() == btnGenero) {
			adicionarGeneros.setVisible(true);
			lblGenerosSelecionados.setText(adicionarGeneros.getGenerosSelecionados().size() + " gênero(s)");
		}
	}

	private void salvarDisco() {
		Date dataSelecionada = (Date) spinnerDataDisco.getValue();
		Calendar cal = Calendar.getInstance();
		cal.setTime(dataSelecionada);	
		
		if(txtNomeDisco.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Por favor insira o nome do disco!", "Erro", JOptionPane.WARNING_MESSAGE);
			return;
		}
		if(txtPrecoDisco.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Por favor insira o preço!", "Erro", JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		double preco;
		try {
			preco = Double.parseDouble(txtPrecoDisco.getText().trim());
			if (preco <= 0) {
				JOptionPane.showMessageDialog(null, "O preço deve ser maior que zero!", "Erro", JOptionPane.WARNING_MESSAGE);
				return;
			}
		} catch(NumberFormatException ex) {
			JOptionPane.showMessageDialog(null, "Preço inválido! Use apenas números.", "Erro", JOptionPane.WARNING_MESSAGE);
			return;
		}

		List<Genero> generosSelecionados = adicionarGeneros.getGenerosSelecionados();
		if (generosSelecionados.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Selecione pelo menos um gênero musical!", "Erro", JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		if (participantes.totalSelecionados() == 0) {
			JOptionPane.showMessageDialog(null, "Selecione pelo menos um participante!", "Erro", JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		if (creditosProducao.getEditorasSelecionados() == null) {
			JOptionPane.showMessageDialog(null, "Selecione pelo menos uma editora", "Erro", JOptionPane.WARNING_MESSAGE);
		}
		
		String titulo = txtNomeDisco.getText().trim();
		int ano = cal.get(Calendar.YEAR);

		DiscoCompacto disco = new DiscoCompacto(
			ano, titulo, generosSelecionados, preco, ano, 
			participantes.getCantoresSelecionados(), 
			participantes.getMusicoSelecionados(),
			participantes.getCompositoresSelecionados(), 
			creditosProducao.getGravadoraSelecionados(),
			creditosProducao.getProdutoresSelecionados(), 
			creditosProducao.getEditorasSelecionados()
		);

		Date dataSelecionada1 = (Date) spinnerDataDisco.getValue();
		int sucesso = discoController.cadastrarDisco(disco, dataSelecionada1);
		
		if(sucesso != -1) {
			JOptionPane.showMessageDialog(null, "Disco cadastrado com sucesso!");
			LocalDateTime horaAgora = LocalDateTime.now();
			log = new Logs(
				Sessao.getUtilizadorLogado().getCodigo(), 
				Sessao.getUtilizadorLogado().getNome(),
				Sessao.getUtilizadorLogado().getApelido(), 
				Sessao.getUtilizadorLogado().getPerfil().getNome(),
				Sessao.getUtilizadorLogado().getEmail(), 
				"Cadastrou disco: " + titulo, horaAgora
			);
			logController.inserirLog(log);
			limparCampos();
			listaAcoesDiscos.carregarDiscos();
		} else {
			JOptionPane.showMessageDialog(null, "Erro ao cadastrar disco!");
		}
	}

	private void atualizarContadoresParticipantes() {
		partSelecionados.setText(participantes.totalSelecionados() + " Participantes Selecionados");
		countCompositores.setText("Compositores: " + participantes.compositorContador());
		countMusicos.setText("Músicos: " + participantes.musicoContador());
		countCantores.setText("Cantores: " + participantes.cantorContador());
	}

	private void atualizarContadoresProducao() {
		partSelecionadosProd.setText(creditosProducao.totalSelecionados() + " Membros Selecionados");
		countProdutores.setText("Produtores: " + creditosProducao.produtorContador());
		countGravadoras.setText("Gravadoras: " + creditosProducao.gravadoraContador());
		countEditoras.setText("Editoras: " + creditosProducao.editoraContador());
	}

	private void limparCampos() {
		txtNomeDisco.setText("");
		txtPrecoDisco.setText("");
		spinnerDataDisco.setValue(new Date());
		
		adicionarGeneros.desmarcarGeneros();
		lblGenerosSelecionados.setText("0 gênero(s)");
		
		participantes.desmarcarCantor();
		participantes.desmarcarCompositor();
		participantes.desmarcarMusico();
		atualizarContadoresParticipantes();
		
		creditosProducao.desmarcarProdutor();
		creditosProducao.desmarcarGravadora();
		creditosProducao.desmarcarEditora();
		atualizarContadoresProducao();
	}
}