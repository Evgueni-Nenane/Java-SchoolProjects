package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Banda_Participants extends JPanel implements ActionListener, MouseListener {

	private static final long serialVersionUID = 1L;
	private DefaultTableModel tabelaCompModel, tabelaMusicModel, tabelaCantorModel;

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
		
		JButton adicionarComp = new JButton("Adicionar");
		adicionarComp.setPreferredSize(new Dimension(100, 28));
		gbcAC.gridx = 1;
		gbcAC.gridy = 0;
		accoesComp.add(adicionarComp, gbcAC);
		
		boxTitleContainer.add(boxLabelComp);
		boxTitleContainer.add(accoesComp);
		
		box1.add(boxTitleContainer, BorderLayout.NORTH);
		
		// Parte do centro da box
		
		JPanel tabelaCompPanel = new JPanel();
		
		String[] colunasComp = {"Boxtxt", "Nome do Compositor", "Ações"};
		tabelaCompModel = new DefaultTableModel(colunasComp, 0);		
		
		JTable tabelaComp = new JTable(tabelaCompModel);
		tabelaComp.getTableHeader().setReorderingAllowed(false);
		tabelaComp.getTableHeader().setResizingAllowed(false);
		tabelaComp.setRowHeight(27);
		tabelaCompPanel.add(tabelaComp);

		JScrollPane scrollComp = new JScrollPane(tabelaComp);
		
		box1.add(scrollComp, BorderLayout.CENTER);
				
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
		gbcAC.insets = new Insets(5,5,5,5);
				
		JTextField pesquisarMusico = new JTextField();
		pesquisarMusico.setPreferredSize(new Dimension(150, 28));
		gbcAM.gridx = 0;
		gbcAM.gridy = 0;
		accoesMusico.add(pesquisarMusico, gbcAM);
				
		JButton adicionarMusico = new JButton("Adicionar");
		adicionarMusico.setPreferredSize(new Dimension(100, 28));
		gbcAM.gridx = 1;
		gbcAM.gridy = 0;
		accoesMusico.add(adicionarMusico, gbcAM);
			
		boxTitleContainerMusic.add(boxLabelMusic);
		boxTitleContainerMusic.add(accoesMusico);
				
		box2.add(boxTitleContainerMusic, BorderLayout.NORTH);
		// Parte do centro da box
		
		JPanel tabelaMusicoPanel = new JPanel();
				
		String[] colunasMusic = {"Boxtxt", "Nome do Músico", "Ações"};
		tabelaMusicModel = new DefaultTableModel(colunasMusic, 0);		
				
		JTable tabelaMusico = new JTable(tabelaCompModel);
		tabelaMusico.getTableHeader().setReorderingAllowed(false);
		tabelaMusico.getTableHeader().setResizingAllowed(false);
		tabelaMusico.setRowHeight(27);
		tabelaMusicoPanel.add(tabelaMusico);

		JScrollPane scrollMusico = new JScrollPane(tabelaMusico);
			
		box2.add(scrollMusico, BorderLayout.CENTER);
		
		
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
				
		JButton adicionarCantor = new JButton("Adicionar");
		adicionarCantor.setPreferredSize(new Dimension(100, 28));
		gbcACa.gridx = 1;
		gbcACa.gridy = 0;
		accoesCantor.add(adicionarCantor, gbcACa);
			
		boxTitleContainerCantor.add(boxLabelCantor);
		boxTitleContainerCantor.add(accoesCantor);
		box3.add(boxTitleContainerCantor, BorderLayout.NORTH);
				
		// Parte do centro da box
		
		JPanel tabelaCantorPanel = new JPanel();
				
		String[] colunasCantor = {"Boxtxt", "Nome do Cantor", "Ações"};
		tabelaCantorModel = new DefaultTableModel(colunasCantor, 0);		
				
		JTable tabelaCantor = new JTable(tabelaCompModel);
		tabelaCantor.getTableHeader().setReorderingAllowed(false);
		tabelaCantor.getTableHeader().setResizingAllowed(false);
		tabelaCantor.setRowHeight(27);
		tabelaCantorPanel.add(tabelaCantor);

		JScrollPane scrollCantor = new JScrollPane(tabelaCantor);
		
		box3.add(scrollCantor, BorderLayout.CENTER);
		
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
	@Override public void actionPerformed(ActionEvent e) {}
}
