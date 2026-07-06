package view;

import java.awt.*;
import java.awt.event.*;
import java.util.Date;
import java.util.List;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.*;

import controller.CantorController;
import controller.CompositorController;
import controller.DiscoController;
import controller.MusicoController;
import model.Cantor;
import model.Compositor;
import model.DiscoCompacto;
import model.Editora;
import model.Gravadora;
import model.Musico;
import model.Produtor;

public class DiscoCompletoDialog extends JDialog implements ActionListener, MouseListener {

	private static final long serialVersionUID = 1L;
	
	private JButton btnCancelar, btnSalvar, btnInfoPart;
	
	private JLabel lblTituloDiscoReal, lblGeneroReal, lblPrecoReal, lblTempoExistenciaReal;
	private JLabel lblEditoraReal, lblDataEdicaoDiscoReal;
	private JLabel lblGravadoraReal, lblEnderecoReal, lblEmailReal;
	private JLabel lblProdutorReal, lblEmailProdutorReal;
	
	private JTable tabelaCompositores;
	private JTable tabelaMusicos;
	private JTable tabelaCantores;
	private DefaultTableModel tabelaCompModel, tabelaMusicModel, tabelaCantorModel;
	private JPanel containerInfoParticipantes;
	
	public DiscoCompletoDialog(DiscoCompacto disco) {
		
		this.setSize(1000, 600);
		this.setLocationRelativeTo(null);
		this.setLayout(new BorderLayout());
		this.setModal(true);
		
		// ====================
		// 		TOPCONTAINER
		// ====================
		JPanel topContainer = new JPanel();
		topContainer.setBackground(Color.WHITE);
		topContainer.setPreferredSize(new Dimension(0, 60));
		topContainer.setLayout(new BoxLayout(topContainer, BoxLayout.Y_AXIS));
		topContainer.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		
		JLabel titulo = new JLabel("Detalhes do Disco");
		titulo.setFont(new Font("Arial", Font.BOLD, 18));
		titulo.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		JLabel subtitulo = new JLabel("Visualize as informações cadastradas para este disco.");
		subtitulo.setFont(new Font("Arial", Font.PLAIN, 12));
		subtitulo.setForeground(Color.GRAY);
		subtitulo.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		topContainer.add(titulo);
		topContainer.add(Box.createVerticalStrut(5));
		topContainer.add(subtitulo);
		
		// ========================
		// 		CENTER CONTAINER
		// ========================
		JPanel centerPanel = new JPanel(new BorderLayout());
		centerPanel.setBackground(Color.WHITE);
		centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		
		JPanel container = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.anchor = GridBagConstraints.WEST;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 1.0;
		
		// =========================
		// 	Informacoes do disco
		// =========================
		JPanel containerInfoDisco = new JPanel(new BorderLayout());
		containerInfoDisco.setBorder(BorderFactory.createTitledBorder(
			BorderFactory.createLineBorder(Color.GRAY, 1), 
			"INFORMAÇÕES DO DISCO",
			TitledBorder.LEFT, 
			TitledBorder.TOP,
			new Font("Arial", Font.BOLD, 12)
		));
		containerInfoDisco.setBackground(Color.WHITE);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		gbc.weightx = 1.0;
		
		JPanel innerInfoDisco = new JPanel(new GridBagLayout());
		innerInfoDisco.setBackground(Color.WHITE);
		GridBagConstraints gbcd = new GridBagConstraints();
		gbcd.insets = new Insets(3, 5, 3, 5);
		gbcd.anchor = GridBagConstraints.WEST;
		gbcd.fill = GridBagConstraints.HORIZONTAL;
		gbcd.weightx = 1.0;
		
		// Título
		gbcd.gridx = 0;
		gbcd.gridy = 0;
		gbcd.gridwidth = 1;
		JLabel lblTituloDisco = new JLabel("Título do Disco:");
		lblTituloDisco.setFont(new Font("Arial", Font.BOLD, 11));
		innerInfoDisco.add(lblTituloDisco, gbcd);
		
		gbcd.gridx = 1;
		gbcd.gridy = 0;
		lblTituloDiscoReal = new JLabel("----");
		lblTituloDiscoReal.setFont(new Font("Arial", Font.PLAIN, 11));
		innerInfoDisco.add(lblTituloDiscoReal, gbcd);
		
		// Genero Musical
		gbcd.gridx = 0;
		gbcd.gridy = 1;
		JLabel lblGenero = new JLabel("Gênero Musical:");
		lblGenero.setFont(new Font("Arial", Font.BOLD, 11));
		innerInfoDisco.add(lblGenero, gbcd);
		
		gbcd.gridx = 1;
		gbcd.gridy = 1;
		lblGeneroReal = new JLabel("----");
		lblGeneroReal.setFont(new Font("Arial", Font.PLAIN, 11));
		innerInfoDisco.add(lblGeneroReal, gbcd);
		
		// Preco
		gbcd.gridx = 0;
		gbcd.gridy = 2;
		JLabel lblPreco = new JLabel("Preço:");
		lblPreco.setFont(new Font("Arial", Font.BOLD, 11));
		innerInfoDisco.add(lblPreco, gbcd);
		
		gbcd.gridx = 1;
		gbcd.gridy = 2;
		lblPrecoReal = new JLabel("----");
		lblPrecoReal.setFont(new Font("Arial", Font.PLAIN, 11));
		innerInfoDisco.add(lblPrecoReal, gbcd);
		
		// Tempo de Existência
		gbcd.gridx = 0;
		gbcd.gridy = 3;
		JLabel lblTempoExistencia = new JLabel("Tempo de Existência:");
		lblTempoExistencia.setFont(new Font("Arial", Font.BOLD, 11));
		innerInfoDisco.add(lblTempoExistencia, gbcd);
		
		gbcd.gridx = 1;
		gbcd.gridy = 3;
		lblTempoExistenciaReal = new JLabel("----");
		lblTempoExistenciaReal.setFont(new Font("Arial", Font.PLAIN, 11));
		innerInfoDisco.add(lblTempoExistenciaReal, gbcd);
		
		containerInfoDisco.add(innerInfoDisco, BorderLayout.CENTER);
		container.add(containerInfoDisco, gbc);
		
		// =================
		// 		Gravadora
		// =================
		JPanel containerInfoGravadora = new JPanel(new BorderLayout());
		containerInfoGravadora.setBorder(BorderFactory.createTitledBorder(
			BorderFactory.createLineBorder(Color.GRAY, 1), 
			"GRAVADORA",
			TitledBorder.LEFT, 
			TitledBorder.TOP,
			new Font("Arial", Font.BOLD, 12)
		));
		containerInfoGravadora.setBackground(Color.WHITE);
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.weightx = 0.5;
		
		JPanel innerInfoGravadora = new JPanel(new GridBagLayout());
		innerInfoGravadora.setBackground(Color.WHITE);
		GridBagConstraints gbcg = new GridBagConstraints();
		gbcg.insets = new Insets(3, 5, 3, 5);
		gbcg.anchor = GridBagConstraints.WEST;
		gbcg.fill = GridBagConstraints.HORIZONTAL;
		gbcg.weightx = 1.0;
		
		// Nome da Gravadora
		gbcg.gridx = 0;
		gbcg.gridy = 0;
		JLabel lblGravadora = new JLabel("Nome da Gravadora:");
		lblGravadora.setFont(new Font("Arial", Font.BOLD, 11));
		innerInfoGravadora.add(lblGravadora, gbcg);
		
		gbcg.gridx = 1;
		gbcg.gridy = 0;
		lblGravadoraReal = new JLabel("----");
		lblGravadoraReal.setFont(new Font("Arial", Font.PLAIN, 11));
		innerInfoGravadora.add(lblGravadoraReal, gbcg);
		
		// Endereço
		gbcg.gridx = 0;
		gbcg.gridy = 1;
		JLabel lblEndereco = new JLabel("Endereço:");
		lblEndereco.setFont(new Font("Arial", Font.BOLD, 11));
		innerInfoGravadora.add(lblEndereco, gbcg);
		
		gbcg.gridx = 1;
		gbcg.gridy = 1;
		lblEnderecoReal = new JLabel("----");
		lblEnderecoReal.setFont(new Font("Arial", Font.PLAIN, 11));
		innerInfoGravadora.add(lblEnderecoReal, gbcg);
		
		// Email
		gbcg.gridx = 0;
		gbcg.gridy = 2;
		JLabel lblEmail = new JLabel("E-mail:");
		lblEmail.setFont(new Font("Arial", Font.BOLD, 11));
		innerInfoGravadora.add(lblEmail, gbcg);
		
		gbcg.gridx = 1;
		gbcg.gridy = 2;
		lblEmailReal = new JLabel("----");
		lblEmailReal.setFont(new Font("Arial", Font.PLAIN, 11));
		innerInfoGravadora.add(lblEmailReal, gbcg);
		
		containerInfoGravadora.add(innerInfoGravadora, BorderLayout.CENTER);
		container.add(containerInfoGravadora, gbc);
		
		// ================
		// 		PRODUTOR
		// ================
		JPanel containerInfoProdutor = new JPanel(new BorderLayout());
		containerInfoProdutor.setBorder(BorderFactory.createTitledBorder(
			BorderFactory.createLineBorder(Color.GRAY, 1), 
			"INFORMAÇÕES CRÉDITOS",
			TitledBorder.LEFT, 
			TitledBorder.TOP,
			new Font("Arial", Font.BOLD, 12)
		));
		containerInfoProdutor.setBackground(Color.WHITE);
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.weightx = 0.5;
		
		JPanel innerInfoProdutor = new JPanel(new GridBagLayout());
		innerInfoProdutor.setBackground(Color.WHITE);
		GridBagConstraints gbcp = new GridBagConstraints();
		gbcp.insets = new Insets(3, 5, 3, 5);
		gbcp.anchor = GridBagConstraints.WEST;
		gbcp.fill = GridBagConstraints.HORIZONTAL;
		gbcp.weightx = 1.0;
		
		btnInfoPart = new JButton("Ver Participantes");
		btnInfoPart.addActionListener(this);
		innerInfoProdutor.add(btnInfoPart);
		
		// Informacao da Edicao
		JPanel containerInfoEdicao = new JPanel(new BorderLayout());
		containerInfoEdicao.setBorder(BorderFactory.createTitledBorder(
			BorderFactory.createLineBorder(Color.GRAY, 1), 
			"INFORMAÇÕES DA EDIÇÃO",
			TitledBorder.LEFT, 
			TitledBorder.TOP,
			new Font("Arial", Font.BOLD, 12)
		));
		containerInfoEdicao.setBackground(Color.WHITE);
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 2;
		gbc.weightx = 1.0;
		
		JPanel innerInfoEdicao = new JPanel(new GridBagLayout());
		innerInfoEdicao.setBackground(Color.WHITE);
		GridBagConstraints gbce = new GridBagConstraints();
		gbce.insets = new Insets(3, 5, 3, 5);
		gbce.anchor = GridBagConstraints.WEST;
		gbce.fill = GridBagConstraints.HORIZONTAL;
		gbce.weightx = 1.0;
		
		// Editora
		gbce.gridx = 0;
		gbce.gridy = 0;
		JLabel lblEditora = new JLabel("Editora:");
		lblEditora.setFont(new Font("Arial", Font.BOLD, 11));
		innerInfoEdicao.add(lblEditora, gbce);
		
		gbce.gridx = 1;
		gbce.gridy = 0;
		lblEditoraReal = new JLabel("----");
		lblEditoraReal.setFont(new Font("Arial", Font.PLAIN, 11));
		innerInfoEdicao.add(lblEditoraReal, gbce);
		
		// Data da Edição
		gbce.gridx = 0;
		gbce.gridy = 1;
		JLabel lblDataEdicaoDisco = new JLabel("Data da Edição:");
		lblDataEdicaoDisco.setFont(new Font("Arial", Font.BOLD, 11));
		innerInfoEdicao.add(lblDataEdicaoDisco, gbce);
		
		gbce.gridx = 1;
		gbce.gridy = 1;
		lblDataEdicaoDiscoReal = new JLabel("----");
		lblDataEdicaoDiscoReal.setFont(new Font("Arial", Font.PLAIN, 11));
		innerInfoEdicao.add(lblDataEdicaoDiscoReal, gbce);
		
		containerInfoEdicao.add(innerInfoEdicao, BorderLayout.CENTER);
		container.add(containerInfoEdicao, gbc);
		
		// Participantes 
		
		containerInfoParticipantes = new JPanel(new BorderLayout());
		containerInfoParticipantes.setBorder(BorderFactory.createTitledBorder(
			BorderFactory.createLineBorder(Color.GRAY, 1), 
			"PARTICIPANTES DO DISCO",
			TitledBorder.LEFT, 
			TitledBorder.TOP,
			new Font("Arial", Font.BOLD, 12)
		));
		containerInfoParticipantes.setBackground(Color.WHITE);
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.gridwidth = 2;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		gbc.fill = GridBagConstraints.BOTH;
		
		JPanel tablesPanel = new JPanel(new GridLayout(1, 3, 10, 0));
		tablesPanel.setBackground(Color.WHITE);
		
		// Tabela de Compositores
		JPanel compPanel = criarPainelTabela("COMPOSITORES", new String[]{"Nome", "Email"});
		tablesPanel.add(compPanel);
		
		// Tabela de Músicos
		JPanel musicPanel = criarPainelTabela("MÚSICOS", new String[]{"Nome", "Instrumento"});
		tablesPanel.add(musicPanel);
		
		// Tabela de Cantores
		JPanel cantorPanel = criarPainelTabela("CANTORES", new String[]{"Nome", "Email"});
		tablesPanel.add(cantorPanel);
		
		containerInfoParticipantes.add(tablesPanel, BorderLayout.CENTER);
		container.add(containerInfoParticipantes, gbc);
		
		// Bottom Container

		JPanel bottomContainer = new JPanel();
		bottomContainer.setBackground(Color.WHITE);
		bottomContainer.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		bottomContainer.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		btnSalvar = new JButton("Fechar");
		btnSalvar.addActionListener(this);
		bottomContainer.add(btnSalvar);
		
		this.add(topContainer, BorderLayout.NORTH);
		this.add(container, BorderLayout.CENTER);
		this.add(bottomContainer, BorderLayout.SOUTH);
		setDadosDisco(disco.getTitulo(), disco.getGeneroMusical().toString(), disco.getPreco(), disco.discoExistencia(disco.getAnoEdicao()));
		setDadosEdicao(disco.getEditoras(), disco.getAnoEdicao());
		setDadosGravadora(disco.getGravadoras());
		popularCompositores(disco.getCompositores());
		popularMusicos(disco.getMusicos());
		popularCantores(disco.getCantores());
//		popularGravadora(disco.getGravadoras());
//		popularEditora(disco.getEditoras());
	}
	
	

	private void popularCantores(List<Cantor> cantores) {
		tabelaCantorModel.setRowCount(0);
	    if (cantores != null) {
	        for (Cantor c : cantores) {
	            tabelaCantorModel.addRow(new Object[]{
	                c.getNomeCantor() + " " + c.getApelidoCantor(),
	                c.getEmailCantor()
	            });
	        }
	    }		
	}

	private void popularMusicos(List<Musico> musicos) {
		tabelaMusicModel.setRowCount(0);
	    if (musicos != null) {
	        for (Musico m : musicos) {
	            tabelaMusicModel.addRow(new Object[]{
	                m.getNomeMusico() + " " + m.getApelidoMusico(),
	                m.getInstrumento()
	            });
	        }
	    }
	}

	private void popularCompositores(List<Compositor> compositores) {
	    tabelaCompModel.setRowCount(0);
	    if (compositores != null) {
	        for (Compositor c : compositores) {
	            tabelaCompModel.addRow(new Object[]{
	                c.getNomeCompositor() + " " + c.getApelidoCompositor(),
	                c.getEmailCompositor()
	            });
	        }
	    }
	}

	private JPanel criarPainelTabela(String titulo, String[] colunas) {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBackground(Color.WHITE);
		panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		
		// Título com contador
		JLabel lblTitulo = new JLabel(titulo + " (0)");
		lblTitulo.setFont(new Font("Arial", Font.BOLD, 11));
		panel.add(lblTitulo, BorderLayout.NORTH);
		
		// Tabela
		DefaultTableModel model = new DefaultTableModel(colunas, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		JTable tabela = new JTable(model);
		tabela.setRowHeight(25);
		tabela.getTableHeader().setFont(new Font("Arial", Font.BOLD, 10));
		tabela.setFont(new Font("Arial", Font.PLAIN, 10));
		JScrollPane scroll = new JScrollPane(tabela);
		scroll.setPreferredSize(new Dimension(0, 150));
		if (titulo.contains("COMPOSITORES")) {
		    tabelaCompositores = tabela;
		    tabelaCompModel = model;
		} else if (titulo.contains("MÚSICOS")) {
		    tabelaMusicos = tabela;
		    tabelaMusicModel = model;
		} else if (titulo.contains("CANTORES")) {
		    tabelaCantores = tabela;
		    tabelaCantorModel = model;
		}
		panel.add(scroll, BorderLayout.CENTER);
		
		return panel;
	}
	
	
	public void setDadosDisco(String titulo, String genero, double preco, int ano) {
	    if (titulo != null) {
	        lblTituloDiscoReal.setText(titulo);
	    } else {
	        lblTituloDiscoReal.setText("----");
	    }
	    
	    if (genero != null) {
	        lblGeneroReal.setText(genero);
	    } else {
	        lblGeneroReal.setText("----");
	    }
	    
	    lblPrecoReal.setText(String.format("MZN. %.2f", preco));
	    
	    lblTempoExistenciaReal.setText("" + ano);    

	}

	public void setDadosEdicao(List<Editora> editoras, int ano) {
		String editora1 = "";
		for (Editora editora : editoras) {
		   editora1 = editora.getNomeEditora();
	   }
		if (editora1 != null) {
	        lblEditoraReal.setText(editora1);
	    } else {
	        lblEditoraReal.setText("----");
	    }
	    		
	    if (ano + "" != null) {
	        lblDataEdicaoDiscoReal.setText(ano + "");
	    } else {
	        lblDataEdicaoDiscoReal.setText("----");
	       }
	}

	public void setDadosGravadora(List<Gravadora> gravadoras) {
		String nome = "";
		String endereco = "";
		String email = "";
		for (Gravadora gravadora : gravadoras) {
			nome = gravadora.getNomeGravadora();
			endereco = gravadora.getEnderecoGravadora();
			email = gravadora.getContactoGravadora();
		}
		if (nome != null) {
	    	
	        lblGravadoraReal.setText(nome);
	    } else {
	        lblGravadoraReal.setText("----");
	    }
	    
	    if (endereco != null) {
	        lblEnderecoReal.setText(endereco);
	    } else {
	        lblEnderecoReal.setText("----");
	    }
	    
	    if (email != null) {
	        lblEmailReal.setText(email);
	    } else {
	        lblEmailReal.setText("----");
	    }
	}


	

	@Override public void mouseClicked(MouseEvent e) {}
	@Override public void mousePressed(MouseEvent e) {}
	@Override public void mouseReleased(MouseEvent e) {}
	@Override public void mouseEntered(MouseEvent e) {}
	@Override public void mouseExited(MouseEvent e) {}
	
	@Override 
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnSalvar) {
			this.dispose();
			dispose();
		}
	}

}