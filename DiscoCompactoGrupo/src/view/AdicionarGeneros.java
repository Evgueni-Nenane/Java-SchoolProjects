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
import javax.swing.table.DefaultTableModel;

import controller.GeneroController;
import model.Genero;
import resources.EstilizarBotao;

public class AdicionarGeneros extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private Set<Integer> generosSelecionados = new HashSet<>();

	private DefaultTableModel tabelaGeneroModel;
	private JButton btnAdicionarGenero, btnLimparGenero, btnCancelar, btnSalvar;
	private GeneroController generoController;

	public AdicionarGeneros(GeneroController generoController) {
		this.generoController = generoController;

		setTitle("Gêneros Musicais");
		setSize(560, 600);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		setModal(true);

		// ====================
		//      TOP CONTAINER
		// ====================

		JPanel topContainer = criarTopContainer();
		add(topContainer, BorderLayout.NORTH);

		// ========================
		//      CENTER CONTAINER
		// ========================

		JPanel centerPanel = new JPanel(new BorderLayout());
		centerPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

		JPanel container = new JPanel(new GridLayout(1, 1, 20, 0));

		// ====================
		// BOX - Gêneros
		// ====================

		JTextField pesquisarComp = new JTextField();
		btnAdicionarGenero = new JButton("Adicionar");
		EstilizarBotao.aplicarSec(btnAdicionarGenero);
		JPanel box1 = criarBoxHeader(
				"Gêneros",
				"Selecione todos os gêneros musicais do disco",
				pesquisarComp, btnAdicionarGenero);
		
		tabelaGeneroModel = criarModeloTabela(new String[] {"","Nome do Gênero", "id" });
		
		pesquisarComp.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void insertUpdate(javax.swing.event.DocumentEvent e) { filtrar(); }
            public void removeUpdate(javax.swing.event.DocumentEvent e) { filtrar(); }
            public void changedUpdate(javax.swing.event.DocumentEvent e) { filtrar(); }

            private void filtrar() {
                String texto = pesquisarComp.getText().toLowerCase().trim();
                tabelaGeneroModel.setRowCount(0);

                List<Genero> generos = generoController.listarGeneros();
                for (Genero d : generos) {
                    
                    if (texto.isEmpty() ||
                        String.valueOf(d.getNomeGenero()).toLowerCase().contains(texto)) {

                        tabelaGeneroModel.addRow(new Object[]{
                        		generosSelecionados.contains(d.getCodigoGenero()),
                        		d.getNomeGenero(),
                            d.getCodigoGenero()
                        });
                    }
                    }
                }
        });

		

		JTable tabelaComp = new JTable(tabelaGeneroModel);
		tabelaGeneroModel.addTableModelListener(e -> {

		    if (e.getType() != javax.swing.event.TableModelEvent.UPDATE) {
		        return;
		    }

		    int linha = e.getFirstRow();

		    if (linha < 0 || linha >= tabelaGeneroModel.getRowCount()) {
		        return;
		    }

		    Boolean selecionado = (Boolean) tabelaGeneroModel.getValueAt(linha, 0);
		    Integer id = (Integer) tabelaGeneroModel.getValueAt(linha, 2);

		    if (selecionado) {
		        generosSelecionados.add(id);
		    } else {
		        generosSelecionados.remove(id);
		    }
		});
		box1.add(new JScrollPane(tabelaComp) {{
			setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		}}, BorderLayout.CENTER);

		btnLimparGenero = new JButton("Desmarcar tudo");
		JPanel acoesBox1 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		acoesBox1.setBackground(Color.WHITE);
		acoesBox1.add(btnLimparGenero);
		box1.add(acoesBox1, BorderLayout.SOUTH);

		container.add(box1);
		centerPanel.add(container, BorderLayout.CENTER);
		add(centerPanel, BorderLayout.CENTER);

		// ========================
		//      BOTTOM CONTAINER
		// ========================

		JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		bottomPanel.setPreferredSize(new Dimension(0, 50));

		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(this);

		btnSalvar = new JButton("Confirmar Seleção");
		EstilizarBotao.aplicarSec(btnSalvar);
		btnSalvar.addActionListener(this);
		btnSalvar.setForeground(Color.WHITE);
		btnSalvar.setFocusPainted(false);
		btnSalvar.setBorderPainted(false);
		btnSalvar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		bottomPanel.add(btnCancelar);
		bottomPanel.add(btnSalvar);

		add(bottomPanel, BorderLayout.SOUTH);

		btnAdicionarGenero.addActionListener(this);
		btnLimparGenero.addActionListener(this);

		carregarGeneros();
	}

	private JPanel criarTopContainer() {
		JPanel topContainer = new JPanel();
		topContainer.setLayout(new BoxLayout(topContainer, BoxLayout.Y_AXIS));
		topContainer.setBorder(BorderFactory.createEmptyBorder(18, 25, 18, 25));
		topContainer.setPreferredSize(new Dimension(0, 90));

		JLabel titulo = new JLabel("Informações dos gêneros");
		titulo.setForeground(Color.WHITE);

		JLabel descricao = new JLabel("Selecione os gêneros musicais do disco");
		descricao.setForeground(new Color(230, 230, 230));

		topContainer.add(titulo);
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

		JLabel lblDescricao = new JLabel(descricao);
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

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnAdicionarGenero) {
			new CriarGeneroDialog(generoController).setVisible(true);
			carregarGeneros();
		}
		if (e.getSource() == btnLimparGenero) {
			desmarcarGeneros();
		}
		if (e.getSource() == btnSalvar) {
			this.dispose();
		}
		if (e.getSource() == btnCancelar) {
			desmarcarGeneros();
			this.dispose();
		}
	}

	public void carregarGeneros() {
		for (int i = 0; i < tabelaGeneroModel.getRowCount(); i++) {
			boolean marcado = (Boolean) tabelaGeneroModel.getValueAt(i, 0);
			int id = (Integer) tabelaGeneroModel.getValueAt(i, 2);

			if (marcado) {
				generosSelecionados.add(id);
			} else {
				generosSelecionados.remove(id);
			}
		}

		tabelaGeneroModel.setRowCount(0);
		List<Genero> generos = generoController.listarGeneros();
		for (Genero genero : generos) {
			boolean estavaMarcado = generosSelecionados.contains(genero.getCodigoGenero());
			tabelaGeneroModel.addRow(new Object[] {
					estavaMarcado,
					genero.getNomeGenero(),
					genero.getCodigoGenero()
			});
		}
	}

	public void desmarcarGeneros() {
		generosSelecionados.clear();

		tabelaGeneroModel.setRowCount(0);
		List<Genero> generos = generoController.listarGeneros();
		for (Genero genero : generos) {
			tabelaGeneroModel.addRow(new Object[] {
					false,
					genero.getNomeGenero(),
					genero.getCodigoGenero()
			});
		}
	}

	public void definirGenerosSelecionados(List<Genero> generos) {
		generosSelecionados.clear();
		if (generos != null) {
			for (Genero genero : generos) {
				generosSelecionados.add(genero.getCodigoGenero());
			}
		}
		carregarGeneros();
	}

	public List<Genero> getGenerosSelecionados() {
		List<Genero> resultado = new ArrayList<>();
		List<Genero> todos = generoController.listarGeneros();

		for (Genero genero : todos) {
			if (generosSelecionados.contains(genero.getCodigoGenero())) {
				resultado.add(genero);
			}
		}
		return resultado;
	}
}