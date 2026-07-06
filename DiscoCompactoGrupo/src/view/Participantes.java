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

import controller.CantorController;
import controller.CompositorController;
import controller.MusicoController;
import model.Cantor;
import model.Compositor;
import model.Musico;

public class Participantes extends JDialog implements ActionListener, MouseListener {

	private static final long serialVersionUID = 1L;
	private Set<Integer> compositoresSelecionados = new HashSet<>();
	private Set<Integer> musicosSelecionados = new HashSet<>();
	private Set<Integer> cantoresSelecionados = new HashSet<>();
	
	private DefaultTableModel tabelaCompModel, tabelaMusicModel, tabelaCantorModel;
	private JButton btnAdicionarComp, btnAdicionarMusico, btnCancelar, btnSalvar, btnAdicionarCantor, btnLimparMusico, btnLimparCompositor, btnLimparCantor;
	private CompositorController compositorController;
	private MusicoController musicoController;
	private CantorController cantorController;
	
	public Participantes(CompositorController compositorController, MusicoController musicoController, CantorController cantorController) {
		this.compositorController = compositorController;
		this.musicoController = musicoController;
		this.cantorController = cantorController;
		
		this.setSize(1000, 680);
		this.setLocationRelativeTo(null);
		this.setLayout(new BorderLayout());
		this.setModal(true);
		// ====================
		// 		TOPCONTAINER
		// ====================
		JPanel topContainer = new JPanel();
		topContainer.setBackground(Color.white);
		topContainer.setPreferredSize(new Dimension(0, 50));
		topContainer.setLayout(new BoxLayout(topContainer, BoxLayout.Y_AXIS));
		
		JLabel titulo = new JLabel("Informações da Banda e Participantes");
		JLabel descricao = new JLabel("Selecione a banda e defina os compositores, músicos e cantores.");
		
		topContainer.add(titulo);
		topContainer.add(descricao);
		
		// ========================
		// 		CENTER CONTAINER
		// ========================
		
		// ------------------------
		//		  Top Content
		// ------------------------
		
		JPanel centerPanel = new JPanel(new BorderLayout());
		centerPanel.setBackground(Color.WHITE);
		centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
				
		// ------------------------
		//		 Center Content
		// ------------------------
		
		JPanel container = new JPanel(new GridLayout(1, 3, 20, 0));
		
		// ===============
		// 		BOX 1
		// ===============
		
		JPanel box1 = new JPanel(new BorderLayout());
		box1.setBackground(Color.WHITE);
		
		// Parte de Cima da Box
		JPanel boxTitleContainer = new JPanel();
		boxTitleContainer.setLayout(new BoxLayout(boxTitleContainer, BoxLayout.Y_AXIS));
		
		JPanel boxLabelComp = new JPanel();
		boxLabelComp.setLayout(new BoxLayout(boxLabelComp, BoxLayout.Y_AXIS));
		
		JLabel lblCompositor = new JLabel("Compositores");
		JLabel lblDescrComp = new JLabel("Selecione todos os compositores que participam do disco.");
		boxLabelComp.add(lblCompositor);
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
		        tabelaCompModel.setRowCount(0);

		        List<Compositor> compositores = compositorController.listarCompositor();
		        for (Compositor c : compositores) {
		            if (texto.isEmpty() ||
		                c.getNomeCompositor().toLowerCase().contains(texto) ||
		                c.getEmailCompositor().toLowerCase().contains(texto) || 
		            	c.getApelidoCompositor().toLowerCase().contains(texto)) {

		                tabelaCompModel.addRow(new Object[]{
		                		compositoresSelecionados.contains(c.getCodigoCompositor()),
		                	    c.getNomeCompositor() + " " + c.getApelidoCompositor(),
		                	    "Compositor",
		                	    c.getCodigoCompositor()
		                });
		            }
		        }
		    }
		});
		
		box1.add(boxTitleContainer, BorderLayout.NORTH);
		
		// Parte do centro da box
		
		JPanel tabelaCompPanel = new JPanel();
		
		String[] colunasComp = {"", "Nome do Compositor", "", "id"};
		tabelaCompModel = new DefaultTableModel(colunasComp, 0) {
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
		
		JTable tabelaComp = new JTable(tabelaCompModel);
		tabelaComp.getTableHeader().setReorderingAllowed(false);
		tabelaComp.getTableHeader().setResizingAllowed(false);
		tabelaComp.setRowHeight(27);
		tabelaCompPanel.add(tabelaComp);
		TableColumn checkboxColumn = tabelaComp.getColumnModel().getColumn(0);
		checkboxColumn.setPreferredWidth(3);
		checkboxColumn.setHeaderRenderer(new HeaderIconRenderer());
		
		TableColumn idColumn = tabelaComp.getColumnModel().getColumn(3);
		idColumn.setMaxWidth(0);
		idColumn.setMinWidth(0);
		idColumn.setWidth(0);
		idColumn.setPreferredWidth(0);
		
		
		JScrollPane scrollComp = new JScrollPane(tabelaComp);
		
		box1.add(scrollComp, BorderLayout.CENTER);
		
		// Parte de Baixo
		JPanel acoesBox1 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		
		btnLimparCompositor = new JButton("Desmarcar tudo");
		btnLimparCompositor.addActionListener(this);
		
		acoesBox1.add(btnLimparCompositor);
		
		box1.add(acoesBox1, BorderLayout.SOUTH);
		
		container.add(box1);
		
		// ===============
		// 		Box 2
		// ================
		JPanel box2 = new JPanel(new BorderLayout());
		
		// Parte de Cima da Box
		JPanel boxTitleContainerMusic = new JPanel();
		boxTitleContainerMusic.setLayout(new BoxLayout(boxTitleContainerMusic, BoxLayout.Y_AXIS));
				
		JPanel boxLabelMusic = new JPanel();
		boxLabelMusic.setLayout(new BoxLayout(boxLabelMusic, BoxLayout.Y_AXIS));
			
		JLabel lblMusico = new JLabel("Músicos");
		JLabel lblDescrMusico = new JLabel("Selecione todos os músicos que participam do disco.");
		boxLabelMusic.add(lblMusico);
		boxLabelMusic.add(lblDescrMusico);
				
		JPanel accoesMusico = new JPanel(new GridBagLayout());
		GridBagConstraints gbcAM = new GridBagConstraints();
		gbcAM.insets = new Insets(5,5,5,5);
				
		JTextField pesquisarMusico = new JTextField();
		pesquisarMusico.setPreferredSize(new Dimension(150, 28));
		gbcAM.gridx = 0;
		gbcAM.gridy = 0;
		accoesMusico.add(pesquisarMusico, gbcAM);
				
		btnAdicionarMusico = new JButton("Adicionar");
		btnAdicionarMusico.addActionListener(this);
		btnAdicionarMusico.setPreferredSize(new Dimension(100, 28));
		gbcAM.gridx = 1;
		gbcAM.gridy = 0;
		accoesMusico.add(btnAdicionarMusico, gbcAM);
			
		boxTitleContainerMusic.add(boxLabelMusic);
		boxTitleContainerMusic.add(accoesMusico);
				
		pesquisarMusico.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
		    public void insertUpdate(javax.swing.event.DocumentEvent e) { filtrar(); }
		    public void removeUpdate(javax.swing.event.DocumentEvent e) { filtrar(); }
		    public void changedUpdate(javax.swing.event.DocumentEvent e) { filtrar(); }

		    private void filtrar() {
		        String texto = pesquisarMusico.getText().toLowerCase().trim();
		        tabelaMusicModel.setRowCount(0);

		        List<Musico> musicos = musicoController.listarMusicos();
		        for (Musico m : musicos) {
		            if (texto.isEmpty() ||
		                m.getNomeMusico().toLowerCase().contains(texto) ||
		                m.getEmailMusico().toLowerCase().contains(texto) || 
		            	m.getApelidoMusico().toLowerCase().contains(texto)) {

		                tabelaMusicModel.addRow(new Object[]{
		                		musicosSelecionados.contains(m.getCodigoMusico()),
		                	    m.getNomeMusico() + " " + m.getApelidoMusico(),
		                	    m.getInstrumento(),
		                	    m.getCodigoMusico()
		                });
		            }
		        }
		    }
		});
		box2.add(boxTitleContainerMusic, BorderLayout.NORTH);
		// Parte do centro da box
		
		JPanel tabelaMusicoPanel = new JPanel();
				
		String[] colunasMusic = {"", "Nome do Músico", "", "Id"};
		tabelaMusicModel = new DefaultTableModel(colunasMusic, 0) {
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
				
		JTable tabelaMusico = new JTable(tabelaMusicModel);
		tabelaMusico.getTableHeader().setReorderingAllowed(false);
		tabelaMusico.getTableHeader().setResizingAllowed(false);
		tabelaMusico.setRowHeight(27);
		tabelaMusicoPanel.add(tabelaMusico);
		checkboxColumn = tabelaMusico.getColumnModel().getColumn(0);
		checkboxColumn.setPreferredWidth(3);
		checkboxColumn.setHeaderRenderer(new HeaderIconRenderer());
		
		TableColumn idColumn2 = tabelaMusico.getColumnModel().getColumn(3);
		idColumn2.setMaxWidth(0);
		idColumn2.setMinWidth(0);
		idColumn2.setWidth(0);
		idColumn2.setPreferredWidth(0);
		
		JScrollPane scrollMusico = new JScrollPane(tabelaMusico);
			
		box2.add(scrollMusico, BorderLayout.CENTER);
		
		// Parte de Baixo
		JPanel acoesBox2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
				
		JLabel box2Baixo = new JLabel("Descrição da tabela de músicos");
		btnLimparMusico = new JButton("Desmarcar tudo");
		btnLimparMusico.addActionListener(this);		
		
		acoesBox2.add(btnLimparMusico);
		acoesBox2.add(box2Baixo);
		
				
		box2.add(acoesBox2, BorderLayout.SOUTH);
				
		
		container.add(box2);
		
		// Box 3
		
		JPanel box3 = new JPanel(new BorderLayout());
		
		// Parte de Cima da Box
		JPanel boxTitleContainerCantor = new JPanel();
		boxTitleContainerCantor.setLayout(new BoxLayout(boxTitleContainerCantor, BoxLayout.Y_AXIS));
				
		JPanel boxLabelCantor = new JPanel();
		boxLabelCantor.setLayout(new BoxLayout(boxLabelCantor, BoxLayout.Y_AXIS));
			
		JLabel lblCantor = new JLabel("Cantores");
		JLabel lblDescrCantor = new JLabel("Selecione todos os cantores que participam do disco.");
		boxLabelCantor.add(lblCantor);
		boxLabelCantor.add(lblDescrCantor);
				
		JPanel accoesCantor = new JPanel(new GridBagLayout());
		GridBagConstraints gbcACa = new GridBagConstraints();
		gbcACa.insets = new Insets(5,5,5,5);
				
		JTextField pesquisarCantor = new JTextField();
		pesquisarCantor.setPreferredSize(new Dimension(150, 28));
		gbcACa.gridx = 0;
		gbcACa.gridy = 0;
		accoesCantor.add(pesquisarCantor, gbcACa);
				
		btnAdicionarCantor = new JButton("Adicionar");
		btnAdicionarCantor.addActionListener(this);
		btnAdicionarCantor.setPreferredSize(new Dimension(100, 28));
		gbcACa.gridx = 1;
		gbcACa.gridy = 0;
		accoesCantor.add(btnAdicionarCantor, gbcACa);
			
		boxTitleContainerCantor.add(boxLabelCantor);
		boxTitleContainerCantor.add(accoesCantor);
		
		pesquisarCantor.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
		    public void insertUpdate(javax.swing.event.DocumentEvent e) { filtrar(); }
		    public void removeUpdate(javax.swing.event.DocumentEvent e) { filtrar(); }
		    public void changedUpdate(javax.swing.event.DocumentEvent e) { filtrar(); }

		    private void filtrar() {
		        String texto = pesquisarCantor.getText().toLowerCase().trim();
		        tabelaCantorModel.setRowCount(0);

		        List<Cantor> cantores = cantorController.listarCantor();
		        for (Cantor ca : cantores) {
		            if (texto.isEmpty() ||
		                ca.getNomeCantor().toLowerCase().contains(texto) ||
		                ca.getEmailCantor().toLowerCase().contains(texto) || 
		            	ca.getApelidoCantor().toLowerCase().contains(texto)) {

		                tabelaCantorModel.addRow(new Object[]{
		                		cantoresSelecionados.contains(ca.getCodigoCantor()),
		                	    ca.getNomeCantor() + " " + ca.getApelidoCantor(),
		                	    "Vocal",
		                	    ca.getCodigoCantor()
		                });
		            }
		        }
		    }
		});
		box3.add(boxTitleContainerCantor, BorderLayout.NORTH);
				
		// Parte do centro da box
		
		JPanel tabelaCantorPanel = new JPanel();
				
		String[] colunasCantor = {"", "Nome do Cantor", "", "Id"};
		tabelaCantorModel = new DefaultTableModel(colunasCantor, 0) {
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
				
		JTable tabelaCantor = new JTable(tabelaCantorModel);
		tabelaCantor.getTableHeader().setReorderingAllowed(false);
		tabelaCantor.getTableHeader().setResizingAllowed(false);
		tabelaCantor.setRowHeight(27);
		tabelaCantorPanel.add(tabelaCantor);
		checkboxColumn = tabelaCantor.getColumnModel().getColumn(0);
		checkboxColumn.setPreferredWidth(3);
		checkboxColumn.setHeaderRenderer(new HeaderIconRenderer());
		
		TableColumn idColumn3 = tabelaCantor.getColumnModel().getColumn(3);
		idColumn3.setMaxWidth(0);
		idColumn3.setMinWidth(0);
		idColumn3.setWidth(0);
		idColumn3.setPreferredWidth(0);
		
		JScrollPane scrollCantor = new JScrollPane(tabelaCantor);
		
		box3.add(scrollCantor, BorderLayout.CENTER);
		
		// Parte de Baixo
		JPanel acoesBox3 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		
		btnLimparCantor = new JButton("Desmarcar tudo");
		btnLimparCantor.addActionListener(this);
		
		acoesBox3.add(btnLimparCantor);
				
		box3.add(acoesBox3, BorderLayout.SOUTH);
				
		
		container.add(box3);
		
		centerPanel.add(container, BorderLayout.CENTER);
		// ========================
		// 		BOTTOM CONTAINER
		// ========================		
		JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		bottomPanel.setPreferredSize(new Dimension(0, 50));
		
		btnSalvar = new JButton("Confirmar Seleção");
		btnSalvar.addActionListener(this);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(this);
		
		bottomPanel.add(btnCancelar);
		bottomPanel.add(btnSalvar);
		
		carregarCompositores();
		carregarMusicos();
		carregarCantores();
		
		Timer timer = new Timer(5000, e -> {
			carregarCompositores();
			carregarMusicos();
			carregarCantores();
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
			new CadastrarCompositorDialog().setVisible(true);
		}
		if (e.getSource() == btnAdicionarMusico) {
			new CadastrarMusicoDialog().setVisible(true);
		}
		if (e.getSource() == btnAdicionarCantor) {
			new CadastrarCantorDialog().setVisible(true);
		}
		if (e.getSource() == btnLimparMusico) {
			desmarcarMusico();
		}
		if (e.getSource() == btnLimparCompositor) {
			desmarcarCompositor();
		}
		if (e.getSource() == btnLimparCantor) {
			desmarcarCantor();
		}
		if (e.getSource() == btnSalvar) {
			this.dispose();
		}
		if (e.getSource() == btnCancelar) {
			desmarcarCompositor();
			desmarcarMusico();
			desmarcarCantor();
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
	
	public void carregarCompositores() {
		for (int i = 0; i < tabelaCompModel.getRowCount(); i++) {
			boolean marcado = (Boolean) tabelaCompModel.getValueAt(i, 0);
			int id = (Integer) tabelaCompModel.getValueAt(i, 3);
			
			if (marcado) {
				compositoresSelecionados.add(id);
			} else {
				compositoresSelecionados.remove(id);
			}
		}
		
		tabelaCompModel.setRowCount(0);
		List<Compositor> compositores = compositorController.listarCompositor();
		for (Compositor compositor : compositores) {
			boolean estavaMarcado = compositoresSelecionados.contains(compositor.getCodigoCompositor());
			tabelaCompModel.addRow(new Object[] {
					estavaMarcado,
					compositor.getNomeCompositor() + " " + compositor.getApelidoCompositor(),
					"Compositor",
					compositor.getCodigoCompositor()
			});
		}
	}
	public void carregarMusicos() {
		for (int i = 0; i < tabelaMusicModel.getRowCount(); i++) {
			boolean marcado = (Boolean) tabelaMusicModel.getValueAt(i, 0);
			int id = (Integer) tabelaMusicModel.getValueAt(i, 3);
			
			if (marcado) {
				musicosSelecionados.add(id);
			} else {
				musicosSelecionados.remove(id);
			}
		}
		
		tabelaMusicModel.setRowCount(0);
		List<Musico> musicos = musicoController.listarMusicos();
		
		for (Musico musico : musicos) {
			boolean estavaMarcado = musicosSelecionados.contains(musico.getCodigoMusico());
			tabelaMusicModel.addRow(new Object[] {
					estavaMarcado,
					musico.getNomeMusico() + " " + musico.getApelidoMusico(),
					musico.getInstrumento(),
					musico.getCodigoMusico()
			});
		}
	}
	public void carregarCantores() {
		for (int i = 0; i < tabelaCantorModel.getRowCount(); i++) {
			boolean marcado = (Boolean) tabelaCantorModel.getValueAt(i, 0);
			int id = (Integer) tabelaCantorModel.getValueAt(i, 3);
			
			if (marcado) {
				cantoresSelecionados.add(id);
			} else {
				cantoresSelecionados.remove(id);
			}
		}
		
		tabelaCantorModel.setRowCount(0);
		List<Cantor> cantores = cantorController.listarCantor();
		
		for (Cantor cantor: cantores) {
			boolean estavaMarcado = cantoresSelecionados.contains(cantor.getCodigoCantor());
			tabelaCantorModel.addRow(new Object[] {
					estavaMarcado,
					cantor.getNomeCantor() + " " + cantor.getApelidoCantor(),
					"Vocal",
					cantor.getCodigoCantor()
			});
		}
	}
	
	public void desmarcarCompositor() {
		compositoresSelecionados.clear();
		
		tabelaCompModel.setRowCount(0);
		List<Compositor> compositores = compositorController.listarCompositor();
		for (Compositor compositor : compositores) {
			boolean estaDesmarcado = compositoresSelecionados.contains(compositor.getCodigoCompositor());
			tabelaCompModel.addRow(new Object[] {
					estaDesmarcado,
					compositor.getNomeCompositor() + " " + compositor.getApelidoCompositor(),
					"Compositor",
					compositor.getCodigoCompositor()
			});
		}
	
	}
	public void desmarcarMusico() {
		musicosSelecionados.clear();
		
		tabelaMusicModel.setRowCount(0);
		List<Musico> musicos = musicoController.listarMusicos();
		for (Musico musico : musicos) {
			boolean estaDesmarcado = musicosSelecionados.contains(musico.getCodigoMusico());
			tabelaMusicModel.addRow(new Object[] {
					estaDesmarcado,
					musico.getNomeMusico() + " " + musico.getApelidoMusico(),
					musico.getInstrumento(),
					musico.getCodigoMusico()
			});
		}
	
	}
	public void desmarcarCantor() {
		cantoresSelecionados.clear();
		
		tabelaCantorModel.setRowCount(0);
		List<Cantor> cantores = cantorController.listarCantor();
		for (Cantor cantor : cantores) {
			boolean estaDesmarcado = cantoresSelecionados.contains(cantor.getCodigoCantor());
			tabelaCantorModel.addRow(new Object[] {
					estaDesmarcado,
					cantor.getNomeCantor() + " " + cantor.getApelidoCantor(),
					"Vocal",
					cantor.getCodigoCantor()
			});
		}
	
	}
	
	public int totalSelecionados() {		
		return compositoresSelecionados.size() + musicosSelecionados.size() + cantoresSelecionados.size();
	}
	public int compositorContador() {
		return compositoresSelecionados.size();
	}
	public int musicoContador() {
		return musicosSelecionados.size();
	}
	public int cantorContador() {
		return cantoresSelecionados.size();
	}
	public List<Compositor> getCompositoresSelecionados() {
	    List<Compositor> resultado = new ArrayList<>();
	    List<Compositor> todos = compositorController.listarCompositor();
	    
	    for (Compositor compositor : todos) {
	    	if (compositoresSelecionados.contains(compositor.getCodigoCompositor())) {
	    		resultado.add(compositor);
	    	}
	    }
	    return resultado;
	}
	public List<Musico> getMusicoSelecionados() {
	    List<Musico> resultado = new ArrayList<>();
	    List<Musico> todos = musicoController.listarMusicos();
	    
	    for (Musico musico : todos) {
	    	if (musicosSelecionados.contains(musico.getCodigoMusico())) {
	    		resultado.add(musico);
	    	}
	    }
	    return resultado;
	}
	public List<Cantor> getCantoresSelecionados() {
	    List<Cantor> resultado = new ArrayList<>();
	    List<Cantor> todos = cantorController.listarCantor();
	    
	    for (Cantor cantor : todos) {
	    	if (cantoresSelecionados.contains(cantor.getCodigoCantor())) {
	    		resultado.add(cantor);
	    	}
	    }
	    return resultado;
	}
}
