package view;

import java.awt.*;
import java.awt.event.*;
import java.net.URL;

import javax.swing.*;
import javax.swing.table.*;

public class Banda_Participants extends JPanel implements ActionListener, MouseListener {

	private static final long serialVersionUID = 1L;
	private DefaultTableModel tabelaCompModel, tabelaMusicModel, tabelaCantorModel;
	private JButton btnAdicionarComp, btnAdicionarMusico, btnAdicionarCantor;
	
	public Banda_Participants() {
		this.setLayout(new BorderLayout());
		this.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		
		// ====================
		// 		TOPCONTAINER
		// ====================
		JPanel topContainer = new JPanel();
		topContainer.setBackground(Color.GRAY);
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
		centerPanel.setBackground(Color.CYAN);
		centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		JPanel bandaPanel = new JPanel(new GridBagLayout());	
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		
		JLabel lblBanda = new JLabel("Banda:");
		gbc.gridx = 0; 
		gbc.gridy = 0; 
		gbc.weightx = 0.0;
		gbc.anchor = GridBagConstraints.WEST; 
		bandaPanel.add(lblBanda, gbc);

		JComboBox<String> bandas = new JComboBox<>();
		bandas.setPreferredSize(new Dimension(250, 28));
		gbc.gridx = 0; 
		gbc.gridy = 1; 
		gbc.weightx = 0.0;
		gbc.anchor = GridBagConstraints.WEST;
		bandaPanel.add(bandas, gbc);
		
		JButton cadastrarBanda = new JButton("Cadastrar Banda");
		cadastrarBanda.setPreferredSize(new Dimension(150, 28));
		
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.weightx = 1.0;
		gbc.anchor = GridBagConstraints.EAST;
		
		bandaPanel.add(cadastrarBanda, gbc);
		centerPanel.add(bandaPanel, BorderLayout.NORTH);
		
		// ------------------------
		//		 Center Content
		// ------------------------
		
		JPanel container = new JPanel(new GridLayout(1, 3, 20, 0));
		
		
		// ===============
		// 		BOX 1
		// ===============
		
		JPanel box1 = new JPanel(new BorderLayout());
		box1.setBackground(Color.green);
		
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
		
		box1.add(boxTitleContainer, BorderLayout.NORTH);
		
		// Parte do centro da box
		
		JPanel tabelaCompPanel = new JPanel();
		
		String[] colunasComp = {"", "Nome do Compositor", "Ações"};
		tabelaCompModel = new DefaultTableModel(colunasComp, 0);		
		
		JTable tabelaComp = new JTable(tabelaCompModel);
		tabelaComp.getTableHeader().setReorderingAllowed(false);
		tabelaComp.getTableHeader().setResizingAllowed(false);
		tabelaComp.setRowHeight(27);
		tabelaCompPanel.add(tabelaComp);
		TableColumn checkboxColumn = tabelaComp.getColumnModel().getColumn(0);
		checkboxColumn.setPreferredWidth(10);
		checkboxColumn.setHeaderRenderer(new HeaderIconRenderer());
		
		JScrollPane scrollComp = new JScrollPane(tabelaComp);
		
		box1.add(scrollComp, BorderLayout.CENTER);
		
		// Parte de Baixo
		JPanel acoesBox1 = new JPanel(new BorderLayout());
		
		JLabel box1Baixo = new JLabel("Descrição da tabela de compositores");
		
		acoesBox1.add(box1Baixo);
		
		box1.add(acoesBox1, BorderLayout.SOUTH);
		
		container.add(box1);
		
		// ===============
		// 		Box 2
		// ================
		JPanel box2 = new JPanel(new BorderLayout());
		box2.setBackground(Color.green);
		
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
				
		box2.add(boxTitleContainerMusic, BorderLayout.NORTH);
		// Parte do centro da box
		
		JPanel tabelaMusicoPanel = new JPanel();
				
		String[] colunasMusic = {"", "Nome do Músico", "Ações"};
		tabelaMusicModel = new DefaultTableModel(colunasMusic, 0);		
				
		JTable tabelaMusico = new JTable(tabelaMusicModel);
		tabelaMusico.getTableHeader().setReorderingAllowed(false);
		tabelaMusico.getTableHeader().setResizingAllowed(false);
		tabelaMusico.setRowHeight(27);
		tabelaMusicoPanel.add(tabelaMusico);
		checkboxColumn = tabelaMusico.getColumnModel().getColumn(0);
		checkboxColumn.setPreferredWidth(10);
		checkboxColumn = tabelaMusico.getColumnModel().getColumn(0);
		checkboxColumn.setPreferredWidth(10);
		checkboxColumn.setHeaderRenderer(new HeaderIconRenderer());
		
		JScrollPane scrollMusico = new JScrollPane(tabelaMusico);
			
		box2.add(scrollMusico, BorderLayout.CENTER);
		
		// Parte de Baixo
		JPanel acoesBox2 = new JPanel(new BorderLayout());
				
		JLabel box2Baixo = new JLabel("Descrição da tabela de músicos");
				
		acoesBox2.add(box2Baixo);
				
		box2.add(acoesBox2, BorderLayout.SOUTH);
				
		
		container.add(box2);
		
		// Box 3
		
		JPanel box3 = new JPanel(new BorderLayout());
		box3.setBackground(Color.green);
		
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
		box3.add(boxTitleContainerCantor, BorderLayout.NORTH);
				
		// Parte do centro da box
		
		JPanel tabelaCantorPanel = new JPanel();
				
		String[] colunasCantor = {"", "Nome do Cantor", "Ações"};
		tabelaCantorModel = new DefaultTableModel(colunasCantor, 0);		
				
		JTable tabelaCantor = new JTable(tabelaCantorModel);
		tabelaCantor.getTableHeader().setReorderingAllowed(false);
		tabelaCantor.getTableHeader().setResizingAllowed(false);
		tabelaCantor.setRowHeight(27);
		tabelaCantorPanel.add(tabelaCantor);
		checkboxColumn = tabelaCantor.getColumnModel().getColumn(0);
		checkboxColumn.setPreferredWidth(10);
		checkboxColumn = tabelaCantor.getColumnModel().getColumn(0);
		checkboxColumn.setPreferredWidth(10);
		checkboxColumn.setHeaderRenderer(new HeaderIconRenderer());

		JScrollPane scrollCantor = new JScrollPane(tabelaCantor);
		
		box3.add(scrollCantor, BorderLayout.CENTER);
		
		// Parte de Baixo
		JPanel acoesBox3 = new JPanel(new BorderLayout());
				
		JLabel box3Baixo = new JLabel("Descrição da tabela de cantores");
				
		acoesBox3.add(box3Baixo);
				
		box3.add(acoesBox3, BorderLayout.SOUTH);
				
		
		container.add(box3);
		
		centerPanel.add(container, BorderLayout.CENTER);
		// ========================
		// 		BOTTOM CONTAINER
		// ========================		
		JPanel bottomPanel = new JPanel();
		bottomPanel.setBackground(Color.YELLOW);
		bottomPanel.setPreferredSize(new Dimension(0, 50));
		
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

}
