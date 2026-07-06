package view;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import controller.CantorController;
import controller.CompositorController;
import controller.DiscoController;
import controller.EditoraController;
import controller.GravadoraController;
import controller.MusicoController;
import controller.ProdutorController;
import model.Cantor;
import model.Compositor;
import model.DiscoCompacto;
import model.Generos;
import model.Musico;
import model.Produtor;

public class EditarDiscoDialog extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	private DiscoController discoController;
	private CompositorController compositorController;
	private MusicoController musicoController;
	private CantorController cantorController;
	private ProdutorController produtorController;
	private GravadoraController gravadoraController;
	private EditoraController editoraController;
	
	private JTextField txtTitulo, txtPreco;
	private JComboBox<Generos> cbGenero;
	private JSpinner spDataEdicao;
	private JTextArea txtDescricao;
	private JTextField txtEditora, txtDataEdicao;
	private JTextField txtGravadora, txtEndereco, txtEmailGravadora;
	private JTextField txtProdutor, txtEmailProdutor;
	
	private JButton btnSalvar, btnCancelar, btnSelecionarCompositores, btnSelecionarMusicos, btnSelecionarCantores, btnSelecionarProdutores;
	
	private JLabel lblCompositoresSelecionados, lblMusicosSelecionados, lblCantoresSelecionados, lblProdutoresSelecionados;
	
	private List<Compositor> compositoresSelecionados = new ArrayList<>();
	private List<Musico> musicosSelecionados = new ArrayList<>();
	private List<Cantor> cantoresSelecionados = new ArrayList<>();
	private List<Produtor> produtoresSelecionados = new ArrayList<>();
	
	private DiscoCompacto discoEditando;
	
	public EditarDiscoDialog(DiscoController discoController, 
							CompositorController compositorController,
							MusicoController musicoController,
							CantorController cantorController, 
							ProdutorController produtorController, 
							GravadoraController gravadoraController, 
							EditoraController editoraController) {
		this.discoController = discoController;
		this.compositorController = compositorController;
		this.musicoController = musicoController;
		this.cantorController = cantorController;
		this.produtorController = produtorController;
		this.gravadoraController = gravadoraController;
		this.editoraController = editoraController;
		
		this.setTitle("Editar Disco");
		this.setSize(900, 800);
		this.setLocationRelativeTo(null);
		this.setLayout(new BorderLayout());
		this.setModal(true);
		
		initComponents();
	}
	
	private void initComponents() {
		JPanel topContainer = new JPanel();
		topContainer.setBackground(Color.WHITE);
		topContainer.setPreferredSize(new Dimension(0, 60));
		topContainer.setLayout(new BoxLayout(topContainer, BoxLayout.Y_AXIS));
		topContainer.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		
		JLabel titulo = new JLabel("Editar Disco");
		titulo.setFont(new Font("Arial", Font.BOLD, 18));
		titulo.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		JLabel subtitulo = new JLabel("Altere as informações do disco selecionado.");
		subtitulo.setFont(new Font("Arial", Font.PLAIN, 12));
		subtitulo.setForeground(Color.GRAY);
		subtitulo.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		topContainer.add(titulo);
		topContainer.add(Box.createVerticalStrut(5));
		topContainer.add(subtitulo);
		
		JPanel container = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.anchor = GridBagConstraints.WEST;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 1.0;
		
		int row = 0;
		
		JPanel containerInfoDisco = new JPanel(new GridBagLayout());
		containerInfoDisco.setBorder(BorderFactory.createTitledBorder(
			BorderFactory.createLineBorder(Color.GRAY, 1), 
			"INFORMAÇÕES DO DISCO",
			TitledBorder.LEFT, 
			TitledBorder.TOP,
			new Font("Arial", Font.BOLD, 12)
		));
		containerInfoDisco.setBackground(Color.WHITE);
		gbc.gridx = 0;
		gbc.gridy = row++;
		gbc.gridwidth = 2;
		
		GridBagConstraints gbcd = new GridBagConstraints();
		gbcd.insets = new Insets(3, 5, 3, 5);
		gbcd.anchor = GridBagConstraints.WEST;
		gbcd.fill = GridBagConstraints.HORIZONTAL;
		gbcd.weightx = 1.0;
		
		gbcd.gridx = 0;
		gbcd.gridy = 0;
		gbcd.gridwidth = 1;
		JLabel lblTitulo = new JLabel("Título do Disco:*");
		lblTitulo.setFont(new Font("Arial", Font.BOLD, 11));
		containerInfoDisco.add(lblTitulo, gbcd);
		
		gbcd.gridx = 1;
		gbcd.gridy = 0;
		txtTitulo = new JTextField(30);
		containerInfoDisco.add(txtTitulo, gbcd);
		
		gbcd.gridx = 0;
		gbcd.gridy = 1;
		JLabel lblGenero = new JLabel("Gênero Musical:*");
		lblGenero.setFont(new Font("Arial", Font.BOLD, 11));
		containerInfoDisco.add(lblGenero, gbcd);
		
		gbcd.gridx = 1;
		gbcd.gridy = 1;
		cbGenero = new JComboBox<>(Generos.values());
		containerInfoDisco.add(cbGenero, gbcd);
		
		gbcd.gridx = 0;
		gbcd.gridy = 2;
		JLabel lblPreco = new JLabel("Preço:*");
		lblPreco.setFont(new Font("Arial", Font.BOLD, 11));
		containerInfoDisco.add(lblPreco, gbcd);
		
		gbcd.gridx = 1;
		gbcd.gridy = 2;
		txtPreco = new JTextField(15);
		containerInfoDisco.add(txtPreco, gbcd);
		
		gbcd.gridx = 0;
		gbcd.gridy = 3;
		JLabel lblDataEdicao = new JLabel("Data de Edição:*");
		lblDataEdicao.setFont(new Font("Arial", Font.BOLD, 11));
		containerInfoDisco.add(lblDataEdicao, gbcd);
		
		gbcd.gridx = 1;
		gbcd.gridy = 3;
		SpinnerDateModel dateModel = new SpinnerDateModel();
		spDataEdicao = new JSpinner(dateModel);
		JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(spDataEdicao, "dd/MM/yyyy");
		spDataEdicao.setEditor(dateEditor);
		spDataEdicao.setPreferredSize(new Dimension(150, 25));
		containerInfoDisco.add(spDataEdicao, gbcd);
		
		gbcd.gridx = 0;
		gbcd.gridy = 4;
		gbcd.gridwidth = 1;
		JLabel lblDescricao = new JLabel("Descrição:");
		lblDescricao.setFont(new Font("Arial", Font.BOLD, 11));
		containerInfoDisco.add(lblDescricao, gbcd);
		
		gbcd.gridx = 1;
		gbcd.gridy = 4;
		gbcd.gridwidth = 1;
		txtDescricao = new JTextArea(3, 30);
		txtDescricao.setLineWrap(true);
		txtDescricao.setWrapStyleWord(true);
		JScrollPane scrollDescricao = new JScrollPane(txtDescricao);
		scrollDescricao.setPreferredSize(new Dimension(0, 60));
		containerInfoDisco.add(scrollDescricao, gbcd);
		
		container.add(containerInfoDisco, gbc);
		
		JPanel containerGravadora = new JPanel(new GridBagLayout());
		containerGravadora.setBorder(BorderFactory.createTitledBorder(
			BorderFactory.createLineBorder(Color.GRAY, 1), 
			"GRAVADORA",
			TitledBorder.LEFT, 
			TitledBorder.TOP,
			new Font("Arial", Font.BOLD, 12)
		));
		containerGravadora.setBackground(Color.WHITE);
		gbc.gridx = 0;
		gbc.gridy = row++;
		gbc.gridwidth = 1;
		gbc.weightx = 0.5;
		
		GridBagConstraints gbcg = new GridBagConstraints();
		gbcg.insets = new Insets(3, 5, 3, 5);
		gbcg.anchor = GridBagConstraints.WEST;
		gbcg.fill = GridBagConstraints.HORIZONTAL;
		gbcg.weightx = 1.0;
		
		gbcg.gridx = 0;
		gbcg.gridy = 0;
		JLabel lblGravadora = new JLabel("Nome:");
		lblGravadora.setFont(new Font("Arial", Font.BOLD, 11));
		containerGravadora.add(lblGravadora, gbcg);
		
		gbcg.gridx = 1;
		gbcg.gridy = 0;
		txtGravadora = new JTextField(20);
		containerGravadora.add(txtGravadora, gbcg);
		
		gbcg.gridx = 0;
		gbcg.gridy = 1;
		JLabel lblEndereco = new JLabel("Endereço:");
		lblEndereco.setFont(new Font("Arial", Font.BOLD, 11));
		containerGravadora.add(lblEndereco, gbcg);
		
		gbcg.gridx = 1;
		gbcg.gridy = 1;
		txtEndereco = new JTextField(20);
		containerGravadora.add(txtEndereco, gbcg);
		
		gbcg.gridx = 0;
		gbcg.gridy = 2;
		JLabel lblEmailGravadora = new JLabel("E-mail:");
		lblEmailGravadora.setFont(new Font("Arial", Font.BOLD, 11));
		containerGravadora.add(lblEmailGravadora, gbcg);
		
		gbcg.gridx = 1;
		gbcg.gridy = 2;
		txtEmailGravadora = new JTextField(20);
		containerGravadora.add(txtEmailGravadora, gbcg);
		
		container.add(containerGravadora, gbc);
		
		JPanel containerProdutor = new JPanel(new GridBagLayout());
		containerProdutor.setBorder(BorderFactory.createTitledBorder(
			BorderFactory.createLineBorder(Color.GRAY, 1), 
			"PRODUTOR",
			TitledBorder.LEFT, 
			TitledBorder.TOP,
			new Font("Arial", Font.BOLD, 12)
		));
		containerProdutor.setBackground(Color.WHITE);
		gbc.gridx = 1;
		gbc.gridy = row - 1;
		gbc.gridwidth = 1;
		gbc.weightx = 0.5;
		
		GridBagConstraints gbcp = new GridBagConstraints();
		gbcp.insets = new Insets(3, 5, 3, 5);
		gbcp.anchor = GridBagConstraints.WEST;
		gbcp.fill = GridBagConstraints.HORIZONTAL;
		gbcp.weightx = 1.0;
		
		gbcp.gridx = 0;
		gbcp.gridy = 0;
		JLabel lblProdutor = new JLabel("Nome:");
		lblProdutor.setFont(new Font("Arial", Font.BOLD, 11));
		containerProdutor.add(lblProdutor, gbcp);
		
		gbcp.gridx = 1;
		gbcp.gridy = 0;
		txtProdutor = new JTextField(20);
		containerProdutor.add(txtProdutor, gbcp);
		
		gbcp.gridx = 0;
		gbcp.gridy = 1;
		JLabel lblEmailProdutor = new JLabel("E-mail:");
		lblEmailProdutor.setFont(new Font("Arial", Font.BOLD, 11));
		containerProdutor.add(lblEmailProdutor, gbcp);
		
		gbcp.gridx = 1;
		gbcp.gridy = 1;
		txtEmailProdutor = new JTextField(20);
		containerProdutor.add(txtEmailProdutor, gbcp);
		
		container.add(containerProdutor, gbc);
		
		JPanel containerEdicao = new JPanel(new GridBagLayout());
		containerEdicao.setBorder(BorderFactory.createTitledBorder(
			BorderFactory.createLineBorder(Color.GRAY, 1), 
			"INFORMAÇÕES DA EDIÇÃO",
			TitledBorder.LEFT, 
			TitledBorder.TOP,
			new Font("Arial", Font.BOLD, 12)
		));
		containerEdicao.setBackground(Color.WHITE);
		gbc.gridx = 0;
		gbc.gridy = row++;
		gbc.gridwidth = 2;
		gbc.weightx = 1.0;
		
		GridBagConstraints gbce = new GridBagConstraints();
		gbce.insets = new Insets(3, 5, 3, 5);
		gbce.anchor = GridBagConstraints.WEST;
		gbce.fill = GridBagConstraints.HORIZONTAL;
		gbce.weightx = 1.0;
		
		gbce.gridx = 0;
		gbce.gridy = 0;
		JLabel lblEditora = new JLabel("Editora:");
		lblEditora.setFont(new Font("Arial", Font.BOLD, 11));
		containerEdicao.add(lblEditora, gbce);
		
		gbce.gridx = 1;
		gbce.gridy = 0;
		txtEditora = new JTextField(20);
		containerEdicao.add(txtEditora, gbce);
		
		gbce.gridx = 0;
		gbce.gridy = 1;
		JLabel lblDataEdicaoDisco = new JLabel("Data da Edição:");
		lblDataEdicaoDisco.setFont(new Font("Arial", Font.BOLD, 11));
		containerEdicao.add(lblDataEdicaoDisco, gbce);
		
		gbce.gridx = 1;
		gbce.gridy = 1;
		txtDataEdicao = new JTextField(15);
		txtDataEdicao.setToolTipText("dd/MM/yyyy");
		containerEdicao.add(txtDataEdicao, gbce);
		
		container.add(containerEdicao, gbc);
		
		JPanel containerParticipantes = new JPanel(new GridBagLayout());
		containerParticipantes.setBorder(BorderFactory.createTitledBorder(
			BorderFactory.createLineBorder(Color.GRAY, 1), 
			"PARTICIPANTES",
			TitledBorder.LEFT, 
			TitledBorder.TOP,
			new Font("Arial", Font.BOLD, 12)
		));
		containerParticipantes.setBackground(Color.WHITE);
		gbc.gridx = 0;
		gbc.gridy = row++;
		gbc.gridwidth = 2;
		gbc.weightx = 1.0;
		
		GridBagConstraints gbcpart = new GridBagConstraints();
		gbcpart.insets = new Insets(3, 5, 3, 5);
		gbcpart.anchor = GridBagConstraints.WEST;
		gbcpart.fill = GridBagConstraints.HORIZONTAL;
		gbcpart.weightx = 1.0;
		
		gbcpart.gridx = 0;
		gbcpart.gridy = 0;
		JLabel lblCompositores = new JLabel("Compositores:");
		lblCompositores.setFont(new Font("Arial", Font.BOLD, 11));
		containerParticipantes.add(lblCompositores, gbcpart);
		
		gbcpart.gridx = 1;
		gbcpart.gridy = 0;
		lblCompositoresSelecionados = new JLabel("Nenhum selecionado");
		lblCompositoresSelecionados.setFont(new Font("Arial", Font.PLAIN, 11));
		containerParticipantes.add(lblCompositoresSelecionados, gbcpart);
		
		gbcpart.gridx = 2;
		gbcpart.gridy = 0;
		btnSelecionarCompositores = new JButton("Selecionar");
		btnSelecionarCompositores.addActionListener(this);
		containerParticipantes.add(btnSelecionarCompositores, gbcpart);
		
		gbcpart.gridx = 0;
		gbcpart.gridy = 1;
		JLabel lblMusicos = new JLabel("Músicos:");
		lblMusicos.setFont(new Font("Arial", Font.BOLD, 11));
		containerParticipantes.add(lblMusicos, gbcpart);
		
		gbcpart.gridx = 1;
		gbcpart.gridy = 1;
		lblMusicosSelecionados = new JLabel("Nenhum selecionado");
		lblMusicosSelecionados.setFont(new Font("Arial", Font.PLAIN, 11));
		containerParticipantes.add(lblMusicosSelecionados, gbcpart);
		
		gbcpart.gridx = 2;
		gbcpart.gridy = 1;
		btnSelecionarMusicos = new JButton("Selecionar");
		btnSelecionarMusicos.addActionListener(this);
		containerParticipantes.add(btnSelecionarMusicos, gbcpart);
		
		gbcpart.gridx = 0;
		gbcpart.gridy = 2;
		JLabel lblCantores = new JLabel("Cantores:");
		lblCantores.setFont(new Font("Arial", Font.BOLD, 11));
		containerParticipantes.add(lblCantores, gbcpart);
		
		gbcpart.gridx = 1;
		gbcpart.gridy = 2;
		lblCantoresSelecionados = new JLabel("Nenhum selecionado");
		lblCantoresSelecionados.setFont(new Font("Arial", Font.PLAIN, 11));
		containerParticipantes.add(lblCantoresSelecionados, gbcpart);
		
		gbcpart.gridx = 2;
		gbcpart.gridy = 2;
		btnSelecionarCantores = new JButton("Selecionar");
		btnSelecionarCantores.addActionListener(this);
		containerParticipantes.add(btnSelecionarCantores, gbcpart);
		
		gbcpart.gridx = 0;
		gbcpart.gridy = 3;
		JLabel lblProdutores = new JLabel("Produtores:");
		lblProdutores.setFont(new Font("Arial", Font.BOLD, 11));
		containerParticipantes.add(lblProdutores, gbcpart);
		
		gbcpart.gridx = 1;
		gbcpart.gridy = 3;
		lblProdutoresSelecionados = new JLabel("Nenhum selecionado");
		lblProdutoresSelecionados.setFont(new Font("Arial", Font.PLAIN, 11));
		containerParticipantes.add(lblProdutoresSelecionados, gbcpart);
		
		gbcpart.gridx = 2;
		gbcpart.gridy = 3;
		btnSelecionarProdutores = new JButton("Selecionar");
		btnSelecionarProdutores.addActionListener(this);
		containerParticipantes.add(btnSelecionarProdutores, gbcpart);
		
		container.add(containerParticipantes, gbc);
		
		JPanel bottomContainer = new JPanel();
		bottomContainer.setBackground(Color.WHITE);
		bottomContainer.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		bottomContainer.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		btnSalvar = new JButton("Salvar");
		btnSalvar.setBackground(new Color(0, 120, 215));
		btnSalvar.setForeground(Color.WHITE);
		btnSalvar.setFocusPainted(false);
		btnSalvar.addActionListener(this);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(this);
		
		bottomContainer.add(btnSalvar);
		bottomContainer.add(btnCancelar);
		
		this.add(topContainer, BorderLayout.NORTH);
		this.add(container, BorderLayout.CENTER);
		this.add(bottomContainer, BorderLayout.SOUTH);
	}
	
	public void carregarDisco(DiscoCompacto disco) {
		this.discoEditando = disco;
		
		if (disco != null) {
			txtTitulo.setText(disco.getTitulo());
			cbGenero.setSelectedItem(disco.getGeneroMusical());
			txtPreco.setText(String.valueOf(disco.getPreco()));
			spDataEdicao.setValue(disco.getAnoEdicao());
			
			if (disco.getCompositores() != null) {
				compositoresSelecionados = new ArrayList<>(disco.getCompositores());
				atualizarLabelsParticipantes();
			}
			
			if (disco.getMusicos() != null) {
				musicosSelecionados = new ArrayList<>(disco.getMusicos());
				atualizarLabelsParticipantes();
			}
			
			if (disco.getCantores() != null) {
				cantoresSelecionados = new ArrayList<>(disco.getCantores());
				atualizarLabelsParticipantes();
			}
			
			if (disco.getProdutores() != null) {
				produtoresSelecionados = new ArrayList<>(disco.getProdutores());
				atualizarLabelsParticipantes();
			}
		}
	}
	
	private void atualizarLabelsParticipantes() {
		if (compositoresSelecionados.isEmpty()) {
			lblCompositoresSelecionados.setText("Nenhum selecionado");
		} else {
			StringBuilder sb = new StringBuilder();
			for (Compositor c : compositoresSelecionados) {
				if (sb.length() > 0) sb.append(", ");
				sb.append(c.getNomeCompositor());
			}
			lblCompositoresSelecionados.setText(sb.toString());
		}
		
		if (musicosSelecionados.isEmpty()) {
			lblMusicosSelecionados.setText("Nenhum selecionado");
		} else {
			StringBuilder sb = new StringBuilder();
			for (Musico m : musicosSelecionados) {
				if (sb.length() > 0) sb.append(", ");
				sb.append(m.getNomeMusico());
			}
			lblMusicosSelecionados.setText(sb.toString());
		}
		
		if (cantoresSelecionados.isEmpty()) {
			lblCantoresSelecionados.setText("Nenhum selecionado");
		} else {
			StringBuilder sb = new StringBuilder();
			for (Cantor c : cantoresSelecionados) {
				if (sb.length() > 0) sb.append(", ");
				sb.append(c.getNomeCantor());
			}
			lblCantoresSelecionados.setText(sb.toString());
		}
		
		if (produtoresSelecionados.isEmpty()) {
			lblProdutoresSelecionados.setText("Nenhum selecionado");
		} else {
			StringBuilder sb = new StringBuilder();
			for (Produtor p : produtoresSelecionados) {
				if (sb.length() > 0) sb.append(", ");
				sb.append(p.getNomeProdutor());
			}
			lblProdutoresSelecionados.setText(sb.toString());
		}
	}
	
	private boolean validarCampos() {
		if (txtTitulo.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(this, 
				"O campo 'Título do Disco' é obrigatório.", 
				"Campo Obrigatório", 
				JOptionPane.WARNING_MESSAGE);
			txtTitulo.requestFocus();
			return false;
		}
		
		if (txtPreco.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(this, 
				"O campo 'Preço' é obrigatório.", 
				"Campo Obrigatório", 
				JOptionPane.WARNING_MESSAGE);
			txtPreco.requestFocus();
			return false;
		}
		
		try {
			Double.parseDouble(txtPreco.getText().trim());
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, 
				"O campo 'Preço' deve ser um número válido.", 
				"Valor Inválido", 
				JOptionPane.WARNING_MESSAGE);
			txtPreco.requestFocus();
			return false;
		}
		
		return true;
	}
	
	private void salvarDisco() {
		if (!validarCampos()) {
			return;
		}
		
		try {
			DiscoCompacto disco;
			if (discoEditando != null) {
				disco = discoEditando;
			} else {
				disco = new DiscoCompacto();
			}
			
			disco.setTitulo(txtTitulo.getText().trim());
			disco.setGeneroMusical((Generos) cbGenero.getSelectedItem());
			disco.setPreco(Double.parseDouble(txtPreco.getText().trim()));
			disco.setAnoEdicao((int) spDataEdicao.getValue());
			
			disco.setCompositores(compositoresSelecionados);
			disco.setMusicos(musicosSelecionados);
			disco.setCantores(cantoresSelecionados);
			disco.setProdutores(produtoresSelecionados);
			
			JOptionPane.showMessageDialog(this, 
				"Disco salvo com sucesso!", 
				"Sucesso", 
				JOptionPane.INFORMATION_MESSAGE);
			
			this.dispose();
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, 
				"Erro ao salvar o disco: " + e.getMessage(), 
				"Erro", 
				JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnSalvar) {
			salvarDisco();
		} else if (e.getSource() == btnCancelar) {
			this.dispose();
	} 
			//else if (e.getSource() == btnSelecionarCompositores) {
//			SelecionarCompositoresDialog dialog = new SelecionarCompositoresDialog(compositorController, compositoresSelecionados);
//			dialog.setVisible(true);
//			compositoresSelecionados = dialog.getCompositoresSelecionados();
//			atualizarLabelsParticipantes();
//		} else if (e.getSource() == btnSelecionarMusicos) {
//			SelecionarMusicosDialog dialog = new SelecionarMusicosDialog(musicoController, musicosSelecionados);
//			dialog.setVisible(true);
//			musicosSelecionados = dialog.getMusicosSelecionados();
//			atualizarLabelsParticipantes();
//		} else if (e.getSource() == btnSelecionarCantores) {
//			SelecionarCantoresDialog dialog = new SelecionarCantoresDialog(cantorController, cantoresSelecionados);
//			dialog.setVisible(true);
//			cantoresSelecionados = dialog.getCantoresSelecionados();
//			atualizarLabelsParticipantes();
//		} else if (e.getSource() == btnSelecionarProdutores) {
//			Creditos_Producao dialog = new Creditos_Producao(produtorController, gravadoraController, editoraController);
//			dialog.setVisible(true);
//			produtoresSelecionados = dialog.getProdutoresSelecionados();
//			atualizarLabelsParticipantes();
		}
	}
