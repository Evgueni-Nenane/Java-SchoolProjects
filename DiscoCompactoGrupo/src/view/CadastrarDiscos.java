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
import javax.swing.JComboBox;
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
import controller.GravadoraController;
import controller.LogsController;
import controller.MusicoController;
import controller.ProdutorController;
import model.DiscoCompacto;
import model.Editora;
import model.Generos;
import model.Logs;
import model.Sessao;

public class CadastrarDiscos extends JPanel implements ActionListener, MouseListener {

	private static final long serialVersionUID = 1L;
	private DiscoController discoController;
	
	private JButton btnSalvar, btnLimpar, btnBanda, btnProducao;
	private JTextField txtNomeDisco, txtPrecoDisco;
	private JLabel partSelecionados, partSelecionadosProd, countCompositores,countMusicos, countCantores, countProdutores, countGravadoras, countEditoras;
	private JSpinner spinnerDataDisco;
	private JComboBox<Generos> generos;
	private CompositorController compositorController;
	private MusicoController musicoController;
	private CantorController cantorController;
	private EditoraController editoraController;
	private GravadoraController gravadoraController;
	private Creditos_Producao creditosProducao;
	private ProdutorController produtorController;
	private Participantes participantes;
	private Logs log;
	private LogsController logController;
	
	public CadastrarDiscos(DiscoController discoController, EditoraController editoraController,
			GravadoraController gravadoraController, LogsController logController) {
		this.discoController = discoController;
		this.editoraController = editoraController;
		this.gravadoraController = gravadoraController;
		this.logController = logController;
		compositorController = new CompositorController();
		musicoController = new MusicoController();
		cantorController = new CantorController();
		produtorController = new ProdutorController();
		gravadoraController = new GravadoraController();
		editoraController = new EditoraController();
		participantes = new Participantes(compositorController, musicoController, cantorController);
		creditosProducao = new Creditos_Producao(produtorController, gravadoraController, editoraController);
		
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

		gbc.weightx = 2;
		gbc.weighty = 0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.WEST;

		// =====================
		// Informações do Disco
		// =====================
		JPanel infoDiscos = new JPanel(new BorderLayout());
		infoDiscos.setBackground(new Color(246, 247, 249));
		JLabel lblTituloDisco = new JLabel("Informações do Disco");
		lblTituloDisco.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 0));
		infoDiscos.add(lblTituloDisco, BorderLayout.NORTH);

		JPanel infoMainDisco = new JPanel(new GridBagLayout());
		infoMainDisco.setBackground(new Color(246, 247, 249));
		GridBagConstraints gbcDisco = new GridBagConstraints();
		gbcDisco.insets = new Insets(5, 5, 2, 5);
		gbcDisco.anchor = GridBagConstraints.WEST;
		gbcDisco.weightx = 1.0;
		gbcDisco.fill = GridBagConstraints.HORIZONTAL;
		
		// Nome
		gbcDisco.gridx = 0;
		gbcDisco.gridy = 0;
		
		infoMainDisco.add(new JLabel("Nome do Disco"), (GridBagConstraints) gbcDisco.clone());
		txtNomeDisco = new JTextField();
		txtNomeDisco.setPreferredSize(new Dimension(500, 25));
		
		gbcDisco.gridx = 0;
		gbcDisco.gridy = 1;
		gbcDisco.gridwidth = 3;
		gbcDisco.weightx = 1.0;
		gbcDisco.fill = GridBagConstraints.HORIZONTAL;
		infoMainDisco.add(txtNomeDisco, gbcDisco);
		infoMainDisco.add(txtNomeDisco, (GridBagConstraints) gbcDisco.clone());

		// Preço
		gbcDisco.gridwidth = 1;
		gbcDisco.gridx = 0;
		gbcDisco.gridy = 2;
		infoMainDisco.add(new JLabel("Preço"), (GridBagConstraints) gbcDisco.clone());
		txtPrecoDisco = new JTextField();
		txtPrecoDisco.setPreferredSize(new Dimension(100, 25));
		txtPrecoDisco.setMinimumSize(new Dimension(100, 25));
		txtPrecoDisco.setMaximumSize(new Dimension(100, 25));
		gbcDisco.gridx = 0;
		gbcDisco.gridy = 3;
		infoMainDisco.add(txtPrecoDisco, (GridBagConstraints) gbcDisco.clone());

		// Genero
		gbcDisco.gridx = 1;
		gbcDisco.gridy = 2;
		infoMainDisco.add(new JLabel("Gênero Musical"), (GridBagConstraints) gbcDisco.clone());
		generos = new JComboBox<>(Generos.values());
		generos.setPreferredSize(new Dimension(220, 25));
		generos.setMinimumSize(new Dimension(220, 25));
		generos.setMaximumSize(new Dimension(220, 25));
		gbcDisco.gridx = 1;
		gbcDisco.gridy = 3;
		infoMainDisco.add(generos, (GridBagConstraints) gbcDisco.clone());
		
		// Data de Edição
		gbcDisco.gridx = 2;
		gbcDisco.gridy = 2;
		infoMainDisco.add(new JLabel("Data de Edição"), (GridBagConstraints) gbcDisco.clone());
		spinnerDataDisco = new JSpinner(new SpinnerDateModel());
		spinnerDataDisco.setEditor(new JSpinner.DateEditor(spinnerDataDisco, "dd/MM/yyyy"));
		spinnerDataDisco.setPreferredSize(new Dimension(125, 25));
		spinnerDataDisco.setMinimumSize(new Dimension(125, 25));
		spinnerDataDisco.setMaximumSize(new Dimension(125, 25));
		gbcDisco.gridx = 2;
		gbcDisco.gridy = 3;
		infoMainDisco.add(spinnerDataDisco, (GridBagConstraints) gbcDisco.clone());

		infoDiscos.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(220, 220, 220)));
		infoDiscos.add(infoMainDisco, BorderLayout.CENTER);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		cadastroPanel.add(infoDiscos, (GridBagConstraints) gbc.clone());


		// ====================================
		// Informações da Banda e Participantes
		// ====================================
		JPanel infoBandaPart = new JPanel(new BorderLayout());
		infoBandaPart.setPreferredSize(new Dimension(910, 150));
		infoBandaPart.setBackground(new Color(246, 247, 249));
		infoBandaPart.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(220, 220, 220)));

		JLabel lblTituloBandaPart = new JLabel("Créditos do Disco");
		lblTituloBandaPart.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 0));
		infoBandaPart.add(lblTituloBandaPart, BorderLayout.NORTH);

		JPanel infoMainBandaPart = new JPanel(new GridBagLayout());
		infoMainBandaPart.setBackground(new Color(246, 247, 249));
		GridBagConstraints gbcBanda = new GridBagConstraints();
		gbcBanda.insets = new Insets(5, 5, 2, 5);
		gbcBanda.anchor = GridBagConstraints.WEST;

		// Banda
		gbcBanda.gridx = 0;
		gbcBanda.gridy = 0;
		infoMainBandaPart.add(new JLabel("Participantes"), (GridBagConstraints) gbcBanda.clone());
		btnBanda = new JButton("Selecionar Participantes");
		btnBanda.addActionListener(this);
		btnBanda.setPreferredSize(new Dimension(450, 25));
		btnBanda.setMinimumSize(new Dimension(450, 25));
		btnBanda.setMaximumSize(new Dimension(450, 25));
		gbcBanda.gridx = 0;
		gbcBanda.gridy = 1;
		infoMainBandaPart.add(btnBanda, (GridBagConstraints) gbcBanda.clone());

		// Cantor
		JPanel informacaoMain = new JPanel(new BorderLayout());
		informacaoMain.setBackground(Color.WHITE);
		informacaoMain.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		informacaoMain.setPreferredSize(new Dimension(450, 70));
		informacaoMain.setMinimumSize(new Dimension(450, 70));
		informacaoMain.setMaximumSize(new Dimension(450, 70));
		partSelecionados = new JLabel(participantes.totalSelecionados() +" Participantes Selecionados");
		
		JPanel innerInfo = new JPanel(new GridLayout(1, 3, 5, 5));
		
		countCompositores = new JLabel("Compositores: " + participantes.compositorContador());
		countMusicos = new JLabel("Músicos: " + participantes.musicoContador());
		countCantores = new JLabel("Cantores: " + participantes.cantorContador());
		
		innerInfo.add(countCompositores);
		innerInfo.add(countMusicos);
		innerInfo.add(countCantores);
		
		informacaoMain.add(partSelecionados, BorderLayout.NORTH);
		informacaoMain.add(innerInfo, BorderLayout.CENTER);
		gbcBanda.gridx = 0;
		gbcBanda.gridy = 2;
		infoMainBandaPart.add(informacaoMain, gbcBanda);

		// Compositor
		btnProducao = new JButton("Selecionar Produção");
		btnProducao.addActionListener(this);
		btnProducao.setPreferredSize(new Dimension(450, 25));
		btnProducao.setMinimumSize(new Dimension(450, 25));
		btnBanda.setMaximumSize(new Dimension(450, 25));
		gbcBanda.gridx = 1;
		gbcBanda.gridy = 1;
		infoMainBandaPart.add(btnProducao, (GridBagConstraints) gbcBanda.clone());
		
		JPanel informacaoMainProd = new JPanel(new BorderLayout());
		informacaoMainProd.setBackground(Color.WHITE);
		informacaoMainProd.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		informacaoMainProd.setPreferredSize(new Dimension(450, 70));
		informacaoMainProd.setMinimumSize(new Dimension(450, 70));
		informacaoMainProd.setMaximumSize(new Dimension(450, 70));
		partSelecionadosProd = new JLabel(creditosProducao.totalSelecionados() +" Membros Selecionados");
		
		JPanel innerInfoProd = new JPanel(new GridLayout(1, 3, 5, 5));
		
		countProdutores = new JLabel("Produtores: " + creditosProducao.produtorContador());
		countGravadoras = new JLabel("Gravadoras: " + creditosProducao.gravadoraContador());
		countEditoras = new JLabel("Editoras: "  + creditosProducao.editoraContador());
		
		innerInfoProd.add(countProdutores);
		innerInfoProd.add(countGravadoras);
		innerInfoProd.add(countEditoras);
		
		informacaoMainProd.add(partSelecionadosProd, BorderLayout.NORTH);
		informacaoMainProd.add(innerInfoProd, BorderLayout.CENTER);
		
		gbcBanda.gridx = 1;
		gbcBanda.gridy = 2;
		infoMainBandaPart.add(informacaoMainProd, gbcBanda);
		// Músicos
		

		infoBandaPart.add(infoMainBandaPart, BorderLayout.CENTER);
		gbc.gridwidth = 2;
		gbc.gridx = 0;
		gbc.gridy = 1;
		cadastroPanel.add(infoBandaPart, (GridBagConstraints) gbc.clone());
		gbc.gridwidth = 1;
		
		// ====================================
		// Informações do Produtor
		// ====================================
		JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JPanel painelBotoes = new JPanel(new BorderLayout());
		painelBotoes.setBackground(new Color(246, 247, 249));
		painelBotoes.setPreferredSize(new Dimension(400, 100));
		painelBotoes.setMinimumSize(new Dimension(400, 100));
		painelBotoes.setMaximumSize(new Dimension(400, 100));
		btnPanel.setBackground(new Color(246, 247, 249));

		painelBotoes.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(220, 220, 220)));

		btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(this);
		btnSalvar.setPreferredSize(new Dimension(100, 25));

		btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(this);
		btnLimpar.setPreferredSize(new Dimension(100, 25));

		btnPanel.add(btnSalvar);
		btnPanel.add(btnLimpar);

		gbc.gridx = 1;
		gbc.gridy = 2;
		painelBotoes.add(btnPanel, BorderLayout.SOUTH);

		cadastroPanel.add(painelBotoes, (GridBagConstraints) gbc.clone());

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

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		    if(e.getSource() == btnSalvar) {
		    	Date dataSelecionada = (Date) spinnerDataDisco.getValue();
		    	Calendar cal = Calendar.getInstance();
		    	cal.setTime(dataSelecionada);	
		    	
	            // Validação
	            if(txtNomeDisco.getText().trim().isEmpty()) {
	                JOptionPane.showMessageDialog(null, "Por favor insira o nome do disco!", "Erro", JOptionPane.WARNING_MESSAGE);
	                return;
	            }
	            if(txtPrecoDisco.getText().trim().isEmpty()) {
	                JOptionPane.showMessageDialog(null, "Por favor insira o preço!", "Erro", JOptionPane.WARNING_MESSAGE);
	                return;
	            }
	            try {
	                Double.parseDouble(txtPrecoDisco.getText());
	            } catch(NumberFormatException ex) {
	                JOptionPane.showMessageDialog(null, "Preço inválido! Use apenas números.", "Erro", JOptionPane.WARNING_MESSAGE);
	                return;
	            }
		    	
		        String titulo = txtNomeDisco.getText();
		        int ano = cal.get(Calendar.YEAR);
		        Generos genero = (Generos) generos.getSelectedItem();
		        Double preco = Double.parseDouble(txtPrecoDisco.getText());

		        DiscoCompacto disco = new DiscoCompacto(
		            ano, titulo, genero, preco, ano, participantes.getCantoresSelecionados(), participantes.getMusicoSelecionados(),
		            participantes.getCompositoresSelecionados(), creditosProducao.getGravadoraSelecionados(),creditosProducao.getProdutoresSelecionados(), creditosProducao.getEditorasSelecionados()
		        );		            

		        Date dataSelecionada1 = (Date) spinnerDataDisco.getValue();
		        
		        int sucesso = discoController.cadastrarDisco(disco, dataSelecionada1);
		        
		        if(sucesso != -1) {
		            JOptionPane.showMessageDialog(null, "Disco cadastrado com sucesso!");
					LocalDateTime horaAgora = LocalDateTime.now();
					System.out.println(Sessao.getUtilizadorLogado().getNome());
					System.out.println(Sessao.getUtilizadorLogado().getApelido());
					System.out.println(Sessao.getUtilizadorLogado().getPerfil());
					System.out.println(Sessao.getUtilizadorLogado().getEmail());
					log = new Logs(
							Sessao.getUtilizadorLogado().getCodigo(), Sessao.getUtilizadorLogado().getNome(),
							Sessao.getUtilizadorLogado().getApelido(), Sessao.getUtilizadorLogado().getPerfil().name(),
							Sessao.getUtilizadorLogado().getEmail(), "Cadastrar Disco", horaAgora);
					logController.inserirLog(log);
		            limparCampos();
		        } else {
		            JOptionPane.showMessageDialog(null, "Erro ao cadastrar disco!");
		    }
		}
		if(e.getSource() == btnLimpar){
		   	limparCampos();
		}
		if (e.getSource() == btnBanda) {
			participantes.setVisible(true);
			partSelecionados.setText(participantes.totalSelecionados() +" Participantes Selecionados");
			countCompositores.setText("Compositores: " + participantes.compositorContador());
			countMusicos.setText("Músicos: " + participantes.musicoContador());
			countCantores.setText("Cantores: " + participantes.cantorContador());
		}
		if (e.getSource() == btnProducao) {
			creditosProducao.setVisible(true);
			partSelecionadosProd.setText(creditosProducao.totalSelecionados() +" Membros Selecionados");
			countProdutores.setText("Produtores: " + creditosProducao.produtorContador());
			countGravadoras.setText("Gravadoras: " + creditosProducao.gravadoraContador());
			countEditoras.setText("Editoras: " + creditosProducao.editoraContador());
		}
	}
	
	private void limparCampos() {
	    txtNomeDisco.setText("");
	    txtPrecoDisco.setText("");
	    spinnerDataDisco.setValue(new Date());
	    generos.setSelectedIndex(0);
	    participantes.desmarcarCantor();
	    participantes.desmarcarCompositor();
	    participantes.desmarcarMusico();
	    partSelecionados.setText(participantes.totalSelecionados() +" Participantes Selecionados");
	    countCompositores.setText("Compositores: " + participantes.compositorContador());
		countMusicos.setText("Músicos: " + participantes.musicoContador());
		countCantores.setText("Cantores: " + participantes.cantorContador());
		
		partSelecionadosProd.setText(creditosProducao.totalSelecionados() +" Membros Selecionados");
		countProdutores.setText("Produtores: " + creditosProducao.produtorContador());
		countGravadoras.setText("Gravadoras: " + creditosProducao.gravadoraContador());
		countEditoras.setText("Editoras: " + creditosProducao.editoraContador());
	}
}