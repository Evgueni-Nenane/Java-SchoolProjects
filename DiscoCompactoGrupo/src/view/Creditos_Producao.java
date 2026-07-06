package view;

import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.*;
import javax.swing.table.*;

import controller.EditoraController;
import controller.ProdutorController;
import controller.GravadoraController;
import model.Editora;
import model.Produtor;
import model.Gravadora;

public class Creditos_Producao extends JDialog implements ActionListener, MouseListener {

	private static final long serialVersionUID = 1L;
	private Set<Integer> produtoresSelecionados = new HashSet<>();
	private Set<Integer> gravadorasSelecionadas = new HashSet<>();
	private Set<Integer> editorasSelecionadas = new HashSet<>();
	
	private DefaultTableModel tabelaProdutorModel, tabelaGravadoraModel, tabelaEditoraModel;
	private JButton btnAdicionarComp, btnAdicionarGravadora, btnCancelar, btnSalvar, btnAdicionarEditora, btnLimparGravadora, btnLimparProdutor, btnLimparEditora;
	private ProdutorController produtorController;
	private GravadoraController gravadoraController;
	private EditoraController editoraController;
	
	public Creditos_Producao(ProdutorController produtorController, GravadoraController gravadoraController, EditoraController editoraController) {
		this.produtorController = produtorController;
		this.gravadoraController = gravadoraController;
		this.editoraController = editoraController;
		
		this.setSize(1000, 760);
		this.setLocationRelativeTo(null);
		this.setLayout(new BorderLayout());
		this.setModal(true);
		
		JPanel topContainer = new JPanel();
		topContainer.setBackground(Color.white);
		topContainer.setPreferredSize(new Dimension(0, 50));
		topContainer.setLayout(new BoxLayout(topContainer, BoxLayout.Y_AXIS));
		
		JLabel titulo = new JLabel("Informações da produção");
		JLabel descricao = new JLabel("Selecione e defina os produtores, gravadoras e editoras.");
		
		topContainer.add(titulo);
		topContainer.add(descricao);
		
		JPanel centerPanel = new JPanel(new BorderLayout());
		centerPanel.setBackground(Color.WHITE);
		centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		JPanel container = new JPanel(new GridLayout(1, 3, 20, 0));
		
		JPanel box1 = new JPanel(new BorderLayout());
		box1.setBackground(Color.WHITE);
		
		JPanel boxTitleContainer = new JPanel();
		boxTitleContainer.setLayout(new BoxLayout(boxTitleContainer, BoxLayout.Y_AXIS));
		
		JPanel boxLabelComp = new JPanel();
		boxLabelComp.setLayout(new BoxLayout(boxLabelComp, BoxLayout.Y_AXIS));
		
		JLabel lblProdutor = new JLabel("Produtores");
		JLabel lblDescrComp = new JLabel("Selecione todos os produtores que participam do disco.");
		boxLabelComp.add(lblProdutor);
		boxLabelComp.add(lblDescrComp);
		
		JPanel accoesComp = new JPanel(new GridBagLayout());
		GridBagConstraints gbcAC = new GridBagConstraints();
		gbcAC.insets = new Insets(5,5,5,5);
		
		JTextField pesquisarComp = new JTextField();
		pesquisarComp.setPreferredSize(new Dimension(150, 28));
		gbcAC.gridx = 0;
		gbcAC.gridy = 0;
		accoesComp.add(pesquisarComp, gbcAC);
		
		btnAdicionarComp = new JButton("Adicionar");
		btnAdicionarComp.addActionListener(this);
		btnAdicionarComp.setPreferredSize(new Dimension(100, 28));
		gbcAC.gridx = 1;
		gbcAC.gridy = 0;
		accoesComp.add(btnAdicionarComp, gbcAC);
		
		boxTitleContainer.add(boxLabelComp);
		boxTitleContainer.add(accoesComp);
		
		pesquisarComp.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
		    public void insertUpdate(javax.swing.event.DocumentEvent e) { filtrar(); }
		    public void removeUpdate(javax.swing.event.DocumentEvent e) { filtrar(); }
		    public void changedUpdate(javax.swing.event.DocumentEvent e) { filtrar(); }

		    private void filtrar() {
		        String texto = pesquisarComp.getText().toLowerCase().trim();
		        tabelaProdutorModel.setRowCount(0);

		        List<Produtor> produtores = produtorController.listarProdutor();
		        for (Produtor p : produtores) {
		            if (texto.isEmpty() ||
		                p.getNomeProdutor().toLowerCase().contains(texto) ||
		                String.valueOf(p.getApelidoProdutor()).toLowerCase().contains(texto) ||
		                p.getEmailProdutor().toLowerCase().contains(texto) ||
		                String.valueOf(p.getEmailProdutor()).toLowerCase().contains(texto) ||
		                String.valueOf(p.getContactoProdutor()).toLowerCase().contains(texto) ||
		                String.valueOf(p.getCodigoProdutor()).contains(texto)) {

		                tabelaProdutorModel.addRow(new Object[]{
		                		produtoresSelecionados.contains(p.getCodigoProdutor()),
		                	    p.getNomeProdutor() + " " + p.getApelidoProdutor(),
		                	    "Produtor",
		                	    p.getCodigoProdutor()
		                });
		            }
		        }
		    }
		});
		
		box1.add(boxTitleContainer, BorderLayout.NORTH);
		
		JPanel tabelaProdutorPanel = new JPanel();
		
		String[] colunasProdutor = {"", "Nome do Produtor", "", "id"};
		tabelaProdutorModel = new DefaultTableModel(colunasProdutor, 0) {
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
		
		JTable tabelaProdutor = new JTable(tabelaProdutorModel);
		tabelaProdutor.getTableHeader().setReorderingAllowed(false);
		tabelaProdutor.getTableHeader().setResizingAllowed(false);
		tabelaProdutor.setRowHeight(27);
		tabelaProdutorPanel.add(tabelaProdutor);
		TableColumn checkboxColumn = tabelaProdutor.getColumnModel().getColumn(0);
		checkboxColumn.setPreferredWidth(3);
		checkboxColumn.setHeaderRenderer(new HeaderIconRenderer());
		
		TableColumn idColumn = tabelaProdutor.getColumnModel().getColumn(3);
		idColumn.setMaxWidth(0);
		idColumn.setMinWidth(0);
		idColumn.setWidth(0);
		idColumn.setPreferredWidth(0);
		
		JScrollPane scrollComp = new JScrollPane(tabelaProdutor);
		
		box1.add(scrollComp, BorderLayout.CENTER);
		
		JPanel acoesBox1 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		
		btnLimparProdutor = new JButton("Desmarcar tudo");
		btnLimparProdutor.addActionListener(this);
		
		acoesBox1.add(btnLimparProdutor);
		
		box1.add(acoesBox1, BorderLayout.SOUTH);
		
		container.add(box1);
		
		JPanel box2 = new JPanel(new BorderLayout());
		
		JPanel boxTitleContainerMusic = new JPanel();
		boxTitleContainerMusic.setLayout(new BoxLayout(boxTitleContainerMusic, BoxLayout.Y_AXIS));
				
		JPanel boxLabelMusic = new JPanel();
		boxLabelMusic.setLayout(new BoxLayout(boxLabelMusic, BoxLayout.Y_AXIS));
			
		JLabel lblGravadora = new JLabel("Gravadoras");
		JLabel lblDescrGravadora = new JLabel("Selecione todas as gravadoras que participam do disco.");
		boxLabelMusic.add(lblGravadora);
		boxLabelMusic.add(lblDescrGravadora);
				
		JPanel accoesGravadora = new JPanel(new GridBagLayout());
		GridBagConstraints gbcAM = new GridBagConstraints();
		gbcAM.insets = new Insets(5,5,5,5);
				
		JTextField pesquisarGravadora = new JTextField();
		pesquisarGravadora.setPreferredSize(new Dimension(150, 28));
		gbcAM.gridx = 0;
		gbcAM.gridy = 0;
		accoesGravadora.add(pesquisarGravadora, gbcAM);
				
		btnAdicionarGravadora = new JButton("Adicionar");
		btnAdicionarGravadora.addActionListener(this);
		btnAdicionarGravadora.setPreferredSize(new Dimension(100, 28));
		gbcAM.gridx = 1;
		gbcAM.gridy = 0;
		accoesGravadora.add(btnAdicionarGravadora, gbcAM);
			
		boxTitleContainerMusic.add(boxLabelMusic);
		boxTitleContainerMusic.add(accoesGravadora);
		
		pesquisarGravadora.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
		    public void insertUpdate(javax.swing.event.DocumentEvent e) { filtrar(); }
		    public void removeUpdate(javax.swing.event.DocumentEvent e) { filtrar(); }
		    public void changedUpdate(javax.swing.event.DocumentEvent e) { filtrar(); }

		    private void filtrar() {
		        String texto = pesquisarGravadora.getText().toLowerCase().trim();
		        tabelaGravadoraModel.setRowCount(0);

		        List<Gravadora> gravadoras = gravadoraController.listarGravadoras();
		        for (Gravadora g : gravadoras) {
		            if (texto.isEmpty() ||
		                g.getNomeGravadora().toLowerCase().contains(texto) ||
		                g.getEmailGravadora().toLowerCase().contains(texto)) {

		                tabelaGravadoraModel.addRow(new Object[]{
		                		gravadorasSelecionadas.contains(g.getCodigoGravadora()),
		                	    g.getNomeGravadora(),
		                	    "Gravadora",
		                	    g.getCodigoGravadora()
		                });
		            }
		        }
		    }
		});
		
		box2.add(boxTitleContainerMusic, BorderLayout.NORTH);
		
		JPanel tabelaGravadoraPanel = new JPanel();
				
		String[] colunasGravadora = {"", "Nome da Gravadora", "", "Id"};
		tabelaGravadoraModel = new DefaultTableModel(colunasGravadora, 0) {
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
				
		JTable tabelaGravadora = new JTable(tabelaGravadoraModel);
		tabelaGravadora.getTableHeader().setReorderingAllowed(false);
		tabelaGravadora.getTableHeader().setResizingAllowed(false);
		tabelaGravadora.setRowHeight(27);
		tabelaGravadoraPanel.add(tabelaGravadora);
		checkboxColumn = tabelaGravadora.getColumnModel().getColumn(0);
		checkboxColumn.setPreferredWidth(3);
		checkboxColumn.setHeaderRenderer(new HeaderIconRenderer());
		
		TableColumn idColumn2 = tabelaGravadora.getColumnModel().getColumn(3);
		idColumn2.setMaxWidth(0);
		idColumn2.setMinWidth(0);
		idColumn2.setWidth(0);
		idColumn2.setPreferredWidth(0);
		
		JScrollPane scrollGravadora = new JScrollPane(tabelaGravadora);
			
		box2.add(scrollGravadora, BorderLayout.CENTER);
		
		JPanel acoesBox2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
				
		btnLimparGravadora = new JButton("Desmarcar tudo");
		btnLimparGravadora.addActionListener(this);		
		
		acoesBox2.add(btnLimparGravadora);
		
		box2.add(acoesBox2, BorderLayout.SOUTH);
		
		container.add(box2);
		
		JPanel box3 = new JPanel(new BorderLayout());
		
		JPanel boxTitleContainerEditora = new JPanel();
		boxTitleContainerEditora.setLayout(new BoxLayout(boxTitleContainerEditora, BoxLayout.Y_AXIS));
				
		JPanel boxLabelEditora = new JPanel();
		boxLabelEditora.setLayout(new BoxLayout(boxLabelEditora, BoxLayout.Y_AXIS));
			
		JLabel lblEditora = new JLabel("Editoras");
		JLabel lblDescrEditora = new JLabel("Selecione todas as editoras que participam do disco.");
		boxLabelEditora.add(lblEditora);
		boxLabelEditora.add(lblDescrEditora);
				
		JPanel accoesEditora = new JPanel(new GridBagLayout());
		GridBagConstraints gbcACa = new GridBagConstraints();
		gbcACa.insets = new Insets(5,5,5,5);
				
		JTextField pesquisarEditora = new JTextField();
		pesquisarEditora.setPreferredSize(new Dimension(150, 28));
		gbcACa.gridx = 0;
		gbcACa.gridy = 0;
		accoesEditora.add(pesquisarEditora, gbcACa);
				
		btnAdicionarEditora = new JButton("Adicionar");
		btnAdicionarEditora.addActionListener(this);
		btnAdicionarEditora.setPreferredSize(new Dimension(100, 28));
		gbcACa.gridx = 1;
		gbcACa.gridy = 0;
		accoesEditora.add(btnAdicionarEditora, gbcACa);
			
		boxTitleContainerEditora.add(boxLabelEditora);
		boxTitleContainerEditora.add(accoesEditora);
		
		pesquisarEditora.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
		    public void insertUpdate(javax.swing.event.DocumentEvent e) { filtrar(); }
		    public void removeUpdate(javax.swing.event.DocumentEvent e) { filtrar(); }
		    public void changedUpdate(javax.swing.event.DocumentEvent e) { filtrar(); }

		    private void filtrar() {
		        String texto = pesquisarEditora.getText().toLowerCase().trim();
		        tabelaEditoraModel.setRowCount(0);

		        List<Editora> editoras = editoraController.listarEditoras();
		        for (Editora e : editoras) {
		            if (texto.isEmpty() ||
		                e.getNomeEditora().toLowerCase().contains(texto)){

		                tabelaEditoraModel.addRow(new Object[]{
		                		editorasSelecionadas.contains(e.getCodigoEditora()),
		                	    e.getNomeEditora(),
		                	    "Editora",
		                	    e.getCodigoEditora()
		                });
		            }
		        }
		    }
		});
		
		box3.add(boxTitleContainerEditora, BorderLayout.NORTH);
		
		JPanel tabelaEditoraPanel = new JPanel();
				
		String[] colunasEditora = {"", "Nome do Editora", "", "Id"};
		tabelaEditoraModel = new DefaultTableModel(colunasEditora, 0) {
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
				
		JTable tabelaEditora = new JTable(tabelaEditoraModel);
		tabelaEditora.getTableHeader().setReorderingAllowed(false);
		tabelaEditora.getTableHeader().setResizingAllowed(false);
		tabelaEditora.setRowHeight(27);
		tabelaEditoraPanel.add(tabelaEditora);
		checkboxColumn = tabelaEditora.getColumnModel().getColumn(0);
		checkboxColumn.setPreferredWidth(3);
		checkboxColumn.setHeaderRenderer(new HeaderIconRenderer());
		
		TableColumn idColumn3 = tabelaEditora.getColumnModel().getColumn(3);
		idColumn3.setMaxWidth(0);
		idColumn3.setMinWidth(0);
		idColumn3.setWidth(0);
		idColumn3.setPreferredWidth(0);
		
		JScrollPane scrollEditora = new JScrollPane(tabelaEditora);
		
		box3.add(scrollEditora, BorderLayout.CENTER);
		
		JPanel acoesBox3 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		
		btnLimparEditora = new JButton("Desmarcar tudo");
		btnLimparEditora.addActionListener(this);
		
		acoesBox3.add(btnLimparEditora);
		
		box3.add(acoesBox3, BorderLayout.SOUTH);
		
		container.add(box3);
		
		centerPanel.add(container, BorderLayout.CENTER);
		
		JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		bottomPanel.setPreferredSize(new Dimension(0, 50));
		
		btnSalvar = new JButton("Confirmar Seleção");
		btnSalvar.addActionListener(this);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(this);
		
		bottomPanel.add(btnCancelar);
		bottomPanel.add(btnSalvar);
		
		carregarProdutores();
		carregarGravadoras();
		carregarEditoras();
		
		Timer timer = new Timer(5000, e -> {
			carregarProdutores();
			carregarGravadoras();
			carregarEditoras();
		});
		
		timer.setInitialDelay(5000);
		timer.start();
		
		this.add(topContainer, BorderLayout.NORTH);
		this.add(centerPanel, BorderLayout.CENTER);
		this.add(bottomPanel, BorderLayout.SOUTH);
	}

	@Override public void mouseClicked(MouseEvent e) {}
	@Override public void mousePressed(MouseEvent e) {}
	@Override public void mouseReleased(MouseEvent e) {}
	@Override public void mouseEntered(MouseEvent e) {}
	@Override public void mouseExited(MouseEvent e) {}
	
	@Override 
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnAdicionarComp) {
			new CadastrarProdutorDialog().setVisible(true);
		}
		if (e.getSource() == btnAdicionarGravadora) {
			new CadastrarGravadoraDialog().setVisible(true);
		}
		if (e.getSource() == btnAdicionarEditora) {
			new CadastrarEditoraDialog().setVisible(true);
		}
		if (e.getSource() == btnLimparGravadora) {
			desmarcarGravadora();
		}
		if (e.getSource() == btnLimparProdutor) {
			desmarcarProdutor();
		}
		if (e.getSource() == btnLimparEditora) {
			desmarcarEditora();
		}
		if (e.getSource() == btnSalvar) {
			this.dispose();
		}
		if (e.getSource() == btnCancelar) {
			desmarcarProdutor();
			desmarcarGravadora();
			desmarcarEditora();
			this.dispose();
		}
	}

	public class HeaderIconRenderer extends JLabel implements TableCellRenderer{

		private static final long serialVersionUID = 1L;

		public HeaderIconRenderer() {
			URL url = this.getClass().getResource("/resources/iconcheckbox.png");
			ImageIcon icone = new ImageIcon(url);
			Image imagemCrua = icone.getImage();
			Image imagemRedimensionada = imagemCrua.getScaledInstance(16, 16, Image.SCALE_SMOOTH);
			ImageIcon iconeFinal = new ImageIcon(imagemRedimensionada);
			
			setHorizontalAlignment(JLabel.CENTER);
			setIcon(iconeFinal);
		}
		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			return this;
		}
		
	}
	
	public void carregarProdutores() {
		for (int i = 0; i < tabelaProdutorModel.getRowCount(); i++) {
			boolean marcado = (Boolean) tabelaProdutorModel.getValueAt(i, 0);
			int id = (Integer) tabelaProdutorModel.getValueAt(i, 3);
			
			if (marcado) {
				produtoresSelecionados.add(id);
			} else {
				produtoresSelecionados.remove(id);
			}
		}
		
		tabelaProdutorModel.setRowCount(0);
		List<Produtor> produtores = produtorController.listarProdutor();
		for (Produtor produtor : produtores) {
			boolean estavaMarcado = produtoresSelecionados.contains(produtor.getCodigoProdutor());
			tabelaProdutorModel.addRow(new Object[] {
					estavaMarcado,
					produtor.getNomeProdutor() + " " + produtor.getApelidoProdutor(),
					"Produtor",
					produtor.getCodigoProdutor()
			});
		}
	}
	
	public void carregarGravadoras() {
		for (int i = 0; i < tabelaGravadoraModel.getRowCount(); i++) {
			boolean marcado = (Boolean) tabelaGravadoraModel.getValueAt(i, 0);
			int id = (Integer) tabelaGravadoraModel.getValueAt(i, 3);
			
			if (marcado) {
				gravadorasSelecionadas.add(id);
			} else {
				gravadorasSelecionadas.remove(id);
			}
		}
		
		tabelaGravadoraModel.setRowCount(0);
		List<Gravadora> gravadoras = gravadoraController.listarGravadoras();
		
		for (Gravadora gravadora : gravadoras) {
			boolean estavaMarcado = gravadorasSelecionadas.contains(gravadora.getCodigoGravadora());
			tabelaGravadoraModel.addRow(new Object[] {
					estavaMarcado,
					gravadora.getNomeGravadora(),
					"Gravadora",
					gravadora.getCodigoGravadora()
			});
		}
	}
	
	public void carregarEditoras() {
		for (int i = 0; i < tabelaEditoraModel.getRowCount(); i++) {
			boolean marcado = (Boolean) tabelaEditoraModel.getValueAt(i, 0);
			int id = (Integer) tabelaEditoraModel.getValueAt(i, 3);
			
			if (marcado) {
				editorasSelecionadas.add(id);
			} else {
				editorasSelecionadas.remove(id);
			}
		}
		
		tabelaEditoraModel.setRowCount(0);
		List<Editora> editoraes = editoraController.listarEditoras();
		
		for (Editora editora: editoraes) {
			boolean estavaMarcado = editorasSelecionadas.contains(editora.getCodigoEditora());
			tabelaEditoraModel.addRow(new Object[] {
					estavaMarcado,
					editora.getNomeEditora(),
					"Editora",
					editora.getCodigoEditora()
			});
		}
	}
	
	public void desmarcarProdutor() {
		produtoresSelecionados.clear();
		
		tabelaProdutorModel.setRowCount(0);
		List<Produtor> produtores = produtorController.listarProdutor();
		for (Produtor produtor : produtores) {
			boolean estaDesmarcado = produtoresSelecionados.contains(produtor.getCodigoProdutor());
			tabelaProdutorModel.addRow(new Object[] {
					estaDesmarcado,
					produtor.getNomeProdutor() + " " + produtor.getApelidoProdutor(),
					"Produtor",
					produtor.getCodigoProdutor()
			});
		}
	
	}
	
	public void desmarcarGravadora() {
		gravadorasSelecionadas.clear();
		
		tabelaGravadoraModel.setRowCount(0);
		List<Gravadora> gravadoras = gravadoraController.listarGravadoras();
		for (Gravadora gravadora : gravadoras) {
			boolean estaDesmarcado = gravadorasSelecionadas.contains(gravadora.getCodigoGravadora());
			tabelaGravadoraModel.addRow(new Object[] {
					estaDesmarcado,
					gravadora.getNomeGravadora(),
					"Gravadora",
					gravadora.getCodigoGravadora()
			});
		}
	
	}
	
	public void desmarcarEditora() {
		editorasSelecionadas.clear();
		
		tabelaEditoraModel.setRowCount(0);
		List<Editora> editoraes = editoraController.listarEditoras();
		for (Editora editora : editoraes) {
			boolean estaDesmarcado = editorasSelecionadas.contains(editora.getCodigoEditora());
			tabelaEditoraModel.addRow(new Object[] {
					estaDesmarcado,
					editora.getNomeEditora(),
					"Editora",
					editora.getCodigoEditora()
			});
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