package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

import controller.EditoraController;
import controller.GravadoraController;
import controller.ProdutorController;
import model.Editora;
import model.Gravadora;
import model.Produtor;

public class Creditos_Producao extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;

	private Set<Integer> produtoresSelecionados = new HashSet<>();
	private Set<Integer> gravadorasSelecionadas = new HashSet<>();
	private Set<Integer> editorasSelecionadas = new HashSet<>();

	private DefaultTableModel tabelaProdutorModel, tabelaGravadoraModel, tabelaEditoraModel;
	private JTable tabelaProdutor, tabelaGravadora, tabelaEditora;
	private JButton btnAdicionarProdutor, btnAdicionarGravadora, btnAdicionarEditora;
	private JButton btnSalvar, btnCancelar;
	private JButton btnLimparProdutor, btnLimparGravadora, btnLimparEditora;

	private ProdutorController produtorController;
	private GravadoraController gravadoraController;
	private EditoraController editoraController;

	public Creditos_Producao(ProdutorController produtorController,
	                         GravadoraController gravadoraController,
	                         EditoraController editoraController) {

		this.produtorController = produtorController;
		this.gravadoraController = gravadoraController;
		this.editoraController = editoraController;

		setTitle("Créditos de Produção");
		setSize(1000, 680);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		setModal(true);

		// Top Container
		JPanel topContainer = criarTopContainer();
		add(topContainer, BorderLayout.NORTH);

		// Center Container
		JPanel centerPanel = new JPanel(new BorderLayout());
		centerPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

		JPanel container = new JPanel(new GridLayout(1, 3, 20, 0));

		// Box 1 - Produtores
		JTextField pesquisarProdutor = new JTextField();
		btnAdicionarProdutor = new JButton("Adicionar");
		JPanel box1 = criarBoxHeader(
				"Produtores",
				"Selecione todos os produtores que participam do disco.",
				pesquisarProdutor, btnAdicionarProdutor);

		tabelaProdutorModel = criarModeloTabela(new String[]{"", "Nome do Produtor", "", "Id"});
		tabelaProdutor = new JTable(tabelaProdutorModel);
		configurarTabela(tabelaProdutor);
		box1.add(new JScrollPane(tabelaProdutor) {{
			setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		}}, BorderLayout.CENTER);

		btnLimparProdutor = new JButton("Desmarcar tudo");
		btnLimparProdutor.addActionListener(this);
		JPanel acoesBox1 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		acoesBox1.setBackground(Color.WHITE);
		acoesBox1.add(btnLimparProdutor);
		box1.add(acoesBox1, BorderLayout.SOUTH);
		container.add(box1);

		// Box 2 - Gravadoras
		JTextField pesquisarGravadora = new JTextField();
		btnAdicionarGravadora = new JButton("Adicionar");
		JPanel box2 = criarBoxHeader("Gravadoras", "Selecione todas as gravadoras que participam do disco.",
				pesquisarGravadora, btnAdicionarGravadora);

		tabelaGravadoraModel = criarModeloTabela(new String[]{"", "Nome da Gravadora", "", "Id"});
		tabelaGravadora = new JTable(tabelaGravadoraModel);
		configurarTabela(tabelaGravadora);
		box2.add(new JScrollPane(tabelaGravadora) {{
			setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		}}, BorderLayout.CENTER);

		btnLimparGravadora = new JButton("Desmarcar tudo");
		btnLimparGravadora.addActionListener(this);
		JPanel acoesBox2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		acoesBox2.setBackground(Color.WHITE);
		acoesBox2.add(btnLimparGravadora);
		box2.add(acoesBox2, BorderLayout.SOUTH);
		container.add(box2);

		// Box 3 - Editoras
		JTextField pesquisarEditora = new JTextField();
		btnAdicionarEditora = new JButton("Adicionar");
		JPanel box3 = criarBoxHeader("Editoras", "Selecione todas as editoras que participam do disco.",
				pesquisarEditora, btnAdicionarEditora);

		tabelaEditoraModel = criarModeloTabela(new String[]{"", "Nome da Editora", "", "Id"});
		tabelaEditora = new JTable(tabelaEditoraModel);
		configurarTabela(tabelaEditora);
		box3.add(new JScrollPane(tabelaEditora) {{
			setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		}}, BorderLayout.CENTER);

		btnLimparEditora = new JButton("Desmarcar tudo");
		btnLimparEditora.addActionListener(this);
		JPanel acoesBox3 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		acoesBox3.setBackground(Color.WHITE);
		acoesBox3.add(btnLimparEditora);
		box3.add(acoesBox3, BorderLayout.SOUTH);
		container.add(box3);

		centerPanel.add(container, BorderLayout.CENTER);
		add(centerPanel, BorderLayout.CENTER);

		// Bottom Container
		JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		bottomPanel.setPreferredSize(new Dimension(0, 50));
		bottomPanel.setBackground(Color.WHITE);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(this);

		btnSalvar = new JButton("Confirmar Seleção");
		btnSalvar.addActionListener(this);
		btnSalvar.setBackground(new Color(19, 175, 119));
		btnSalvar.setForeground(Color.WHITE);
		btnSalvar.setFocusPainted(false);
		btnSalvar.setBorderPainted(false);
		btnSalvar.setCursor(new Cursor(Cursor.HAND_CURSOR));

		bottomPanel.add(btnCancelar);
		bottomPanel.add(btnSalvar);

		add(bottomPanel, BorderLayout.SOUTH);

		btnAdicionarProdutor.addActionListener(this);
		btnAdicionarGravadora.addActionListener(this);
		btnAdicionarEditora.addActionListener(this);

		// Adicionar filtros de pesquisa
		adicionarFiltroPesquisa(pesquisarProdutor, tabelaProdutorModel, "produtor");
		adicionarFiltroPesquisa(pesquisarGravadora, tabelaGravadoraModel, "gravadora");
		adicionarFiltroPesquisa(pesquisarEditora, tabelaEditoraModel, "editora");

		carregarProdutores();
		carregarGravadoras();
		carregarEditoras();
	}

	private JPanel criarTopContainer() {
		JPanel topContainer = new JPanel();
		topContainer.setBackground(new Color(19, 175, 119));
		topContainer.setLayout(new BoxLayout(topContainer, BoxLayout.Y_AXIS));
		topContainer.setBorder(BorderFactory.createEmptyBorder(18, 25, 18, 25));
		topContainer.setPreferredSize(new Dimension(0, 90));

		JLabel titulo = new JLabel("Créditos de Produção");
		titulo.setFont(titulo.getFont().deriveFont(16f).deriveFont(java.awt.Font.BOLD));
		titulo.setForeground(Color.WHITE);

		JLabel descricao = new JLabel("Selecione os produtores, gravadoras e editoras do disco.");
		descricao.setFont(descricao.getFont().deriveFont(12f));
		descricao.setForeground(new Color(230, 230, 230));

		topContainer.add(titulo);
		topContainer.add(Box.createVerticalStrut(5));
		topContainer.add(descricao);
		return topContainer;
	}

	private JPanel criarBoxHeader(String titulo, String descricao, JTextField campoPesquisa, JButton botaoAdicionar) {
		JPanel box = new JPanel(new BorderLayout());
		box.setBackground(Color.WHITE);
		box.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1),
				BorderFactory.createEmptyBorder(15, 15, 15, 15)));

		JPanel boxTitleContainer = new JPanel();
		boxTitleContainer.setLayout(new BoxLayout(boxTitleContainer, BoxLayout.Y_AXIS));
		boxTitleContainer.setBackground(Color.WHITE);

		JPanel boxLabel = new JPanel();
		boxLabel.setLayout(new BoxLayout(boxLabel, BoxLayout.Y_AXIS));
		boxLabel.setBackground(Color.WHITE);

		JLabel lblTitulo = new JLabel(titulo);
		lblTitulo.setFont(lblTitulo.getFont().deriveFont(14f).deriveFont(java.awt.Font.BOLD));

		JLabel lblDescricao = new JLabel(descricao);
		lblDescricao.setFont(lblDescricao.getFont().deriveFont(11f));
		lblDescricao.setForeground(Color.GRAY);

		boxLabel.add(lblTitulo);
		boxLabel.add(Box.createVerticalStrut(5));
		boxLabel.add(lblDescricao);

		JPanel acoes = new JPanel(new GridBagLayout());
		acoes.setBackground(Color.WHITE);

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(8, 5, 5, 5);

		campoPesquisa.setPreferredSize(new Dimension(180, 34));

		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		acoes.add(campoPesquisa, gbc);

		botaoAdicionar.setPreferredSize(new Dimension(110, 34));
		botaoAdicionar.setBackground(new Color(19, 175, 119));
		botaoAdicionar.setForeground(Color.WHITE);
		botaoAdicionar.setFocusPainted(false);
		botaoAdicionar.setBorderPainted(false);
		botaoAdicionar.setCursor(new Cursor(Cursor.HAND_CURSOR));

		gbc.gridx = 1;
		gbc.weightx = 0;
		gbc.fill = GridBagConstraints.NONE;
		acoes.add(botaoAdicionar, gbc);

		boxTitleContainer.add(boxLabel);
		boxTitleContainer.add(Box.createVerticalStrut(10));
		boxTitleContainer.add(acoes);

		box.add(boxTitleContainer, BorderLayout.NORTH);
		return box;
	}

	private DefaultTableModel criarModeloTabela(String[] colunas) {
		return new DefaultTableModel(colunas, 0) {
			private static final long serialVersionUID = 1L;

			@Override
			public Class<?> getColumnClass(int columnIndex) {
				if (columnIndex == 0) {
					return Boolean.class;
				}
				return super.getColumnClass(columnIndex);
			}

			@Override
			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return columnIndex == 0;
			}
		};
	}

	private void configurarTabela(JTable tabela) {
		tabela.getColumnModel().getColumn(0).setPreferredWidth(30);
		tabela.getColumnModel().getColumn(0).setMaxWidth(30);
		tabela.getColumnModel().getColumn(2).setMinWidth(0);
		tabela.getColumnModel().getColumn(2).setMaxWidth(0);
		tabela.getColumnModel().getColumn(2).setWidth(0);
		tabela.getColumnModel().getColumn(3).setMinWidth(0);
		tabela.getColumnModel().getColumn(3).setMaxWidth(0);
		tabela.getColumnModel().getColumn(3).setWidth(0);
		tabela.setRowHeight(25);
		tabela.getTableHeader().setReorderingAllowed(false);
	}

	private void adicionarFiltroPesquisa(JTextField campoPesquisa, DefaultTableModel model, String tipo) {
		campoPesquisa.getDocument().addDocumentListener(new DocumentListener() {
			public void insertUpdate(DocumentEvent e) { filtrar(); }
			public void removeUpdate(DocumentEvent e) { filtrar(); }
			public void changedUpdate(DocumentEvent e) { filtrar(); }

			private void filtrar() {
				String texto = campoPesquisa.getText().toLowerCase().trim();

				salvarSelecoesAtuais();

				switch (tipo) {
					case "produtor": carregarProdutores(); break;
					case "gravadora": carregarGravadoras(); break;
					case "editora": carregarEditoras(); break;
				}

				for (int i = model.getRowCount() - 1; i >= 0; i--) {
					String nome = model.getValueAt(i, 1).toString().toLowerCase();
					if (!texto.isEmpty() && !nome.contains(texto)) {
						model.removeRow(i);
					}
				}
			}
		});
	}

	private void salvarSelecoesAtuais() {
		produtoresSelecionados.clear();
		for (int i = 0; i < tabelaProdutorModel.getRowCount(); i++) {
			boolean marcado = (Boolean) tabelaProdutorModel.getValueAt(i, 0);
			int id = (Integer) tabelaProdutorModel.getValueAt(i, 3);
			if (marcado) produtoresSelecionados.add(id);
		}

		gravadorasSelecionadas.clear();
		for (int i = 0; i < tabelaGravadoraModel.getRowCount(); i++) {
			boolean marcado = (Boolean) tabelaGravadoraModel.getValueAt(i, 0);
			int id = (Integer) tabelaGravadoraModel.getValueAt(i, 3);
			if (marcado) gravadorasSelecionadas.add(id);
		}

		editorasSelecionadas.clear();
		for (int i = 0; i < tabelaEditoraModel.getRowCount(); i++) {
			boolean marcado = (Boolean) tabelaEditoraModel.getValueAt(i, 0);
			int id = (Integer) tabelaEditoraModel.getValueAt(i, 3);
			if (marcado) editorasSelecionadas.add(id);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnSalvar) {
			salvarSelecoesAtuais();
			this.dispose();
		}
		if (e.getSource() == btnCancelar) {
			this.dispose();
		}
		if (e.getSource() == btnLimparProdutor) {
			desmarcarProdutor();
		}
		if (e.getSource() == btnLimparGravadora) {
			desmarcarGravadora();
		}
		if (e.getSource() == btnLimparEditora) {
			desmarcarEditora();
		}
	}

	public void carregarProdutores() {
		tabelaProdutorModel.setRowCount(0);
		List<Produtor> produtores = produtorController.listarProdutor();
		for (Produtor produtor : produtores) {
			boolean estavaMarcado = produtoresSelecionados.contains(produtor.getCodigoProdutor());
			tabelaProdutorModel.addRow(new Object[]{
					estavaMarcado,
					produtor.getNomeProdutor() + " " + produtor.getApelidoProdutor(),
					"Produtor",
					produtor.getCodigoProdutor()
			});
		}
	}

	public void carregarGravadoras() {
		tabelaGravadoraModel.setRowCount(0);
		List<Gravadora> gravadoras = gravadoraController.listarGravadoras();
		for (Gravadora gravadora : gravadoras) {
			boolean estavaMarcado = gravadorasSelecionadas.contains(gravadora.getCodigoGravadora());
			tabelaGravadoraModel.addRow(new Object[]{
					estavaMarcado,
					gravadora.getNomeGravadora(),
					"Gravadora",
					gravadora.getCodigoGravadora()
			});
		}
	}

	public void carregarEditoras() {
		tabelaEditoraModel.setRowCount(0);
		List<Editora> editoras = editoraController.listarEditoras();
		for (Editora editora : editoras) {
			boolean estavaMarcado = editorasSelecionadas.contains(editora.getCodigoEditora());
			tabelaEditoraModel.addRow(new Object[]{
					estavaMarcado,
					editora.getNomeEditora(),
					"Editora",
					editora.getCodigoEditora()
			});
		}
	}

	public void desmarcarProdutor() {
		produtoresSelecionados.clear();
		for (int i = 0; i < tabelaProdutorModel.getRowCount(); i++) {
			tabelaProdutorModel.setValueAt(false, i, 0);
		}
	}

	public void desmarcarGravadora() {
		gravadorasSelecionadas.clear();
		for (int i = 0; i < tabelaGravadoraModel.getRowCount(); i++) {
			tabelaGravadoraModel.setValueAt(false, i, 0);
		}
	}

	public void desmarcarEditora() {
		editorasSelecionadas.clear();
		for (int i = 0; i < tabelaEditoraModel.getRowCount(); i++) {
			tabelaEditoraModel.setValueAt(false, i, 0);
		}
	}

	public int totalSelecionados() {
		return produtoresSelecionados.size() + gravadorasSelecionadas.size() + editorasSelecionadas.size();
	}

	public int produtorContador() {
		return produtoresSelecionados.size();
	}

	public int gravadoraContador() {
		return gravadorasSelecionadas.size();
	}

	public int editoraContador() {
		return editorasSelecionadas.size();
	}

	public List<Produtor> getProdutoresSelecionados() {
		List<Produtor> resultado = new ArrayList<>();
		List<Produtor> todos = produtorController.listarProdutor();
		for (Produtor produtor : todos) {
			if (produtoresSelecionados.contains(produtor.getCodigoProdutor())) {
				resultado.add(produtor);
			}
		}
		return resultado;
	}

	public List<Gravadora> getGravadoraSelecionados() {
		List<Gravadora> resultado = new ArrayList<>();
		List<Gravadora> todos = gravadoraController.listarGravadoras();
		for (Gravadora gravadora : todos) {
			if (gravadorasSelecionadas.contains(gravadora.getCodigoGravadora())) {
				resultado.add(gravadora);
			}
		}
		return resultado;
	}

	public List<Editora> getEditorasSelecionados() {
		List<Editora> resultado = new ArrayList<>();
		List<Editora> todos = editoraController.listarEditoras();
		for (Editora editora : todos) {
			if (editorasSelecionadas.contains(editora.getCodigoEditora())) {
				resultado.add(editora);
			}
		}
		return resultado;
	}
}