	package view;

import java.awt.*;
import java.awt.event.*;
import java.util.Date;
import java.util.List;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.*;

import model.Cantor;
import model.Compositor;
import model.DiscoCompacto;
import model.Editora;
import model.Gravadora;
import model.Musico;
import model.Produtor;

public class DiscoCompletoDialog extends JDialog implements ActionListener, MouseListener {

	private static final long serialVersionUID = 1L;
	
	private JButton btnSalvar;
	
	private JLabel lblTituloDiscoReal, lblGeneroReal, lblPrecoReal, lblTempoExistenciaReal, lblDataEdicaoReal;
	
	private DefaultTableModel tabelaCompModel, tabelaProdModel, tabelaMusicModel, tabelaGravaModel, tabelaCantorModel, tabelaEditorModel;
	private JPanel containerInfoParticipantes, containerInfoProducao;
	
	public DiscoCompletoDialog(DiscoCompacto disco) {
		
		this.setSize(1000, 600);
		this.setResizable(false);
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
			BorderFactory.createLineBorder(Color.GRAY, 1), "INFORMAÇÕES DO DISCO", TitledBorder.LEFT, 
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
		lblTituloDisco.setFont(new Font("Arial", Font.BOLD, 12));
		innerInfoDisco.add(lblTituloDisco, gbcd);
		
		gbcd.gridx = 1;
		gbcd.gridy = 0;
		lblTituloDiscoReal = new JLabel("----");
		lblTituloDiscoReal.setFont(new Font("Arial", Font.PLAIN, 12));
		innerInfoDisco.add(lblTituloDiscoReal, gbcd);
		
		// Genero Musical
		gbcd.gridx = 0;
		gbcd.gridy = 1;
		JLabel lblGenero = new JLabel("Gênero Musical:");
		lblGenero.setFont(new Font("Arial", Font.BOLD, 12));
		innerInfoDisco.add(lblGenero, gbcd);
		
		gbcd.gridx = 1;
		gbcd.gridy = 1;
		lblGeneroReal = new JLabel("----");
		lblGeneroReal.setFont(new Font("Arial", Font.PLAIN, 12));
		innerInfoDisco.add(lblGeneroReal, gbcd);
		
		// Preco
		gbcd.gridx = 0;
		gbcd.gridy = 2;
		JLabel lblPreco = new JLabel("Preço:");
		lblPreco.setFont(new Font("Arial", Font.BOLD, 12));
		innerInfoDisco.add(lblPreco, gbcd);
		
		gbcd.gridx = 1;
		gbcd.gridy = 2;
		lblPrecoReal = new JLabel("----");
		lblPrecoReal.setFont(new Font("Arial", Font.PLAIN, 12));
		innerInfoDisco.add(lblPrecoReal, gbcd);
		
		// Tempo de Existência
		gbcd.gridx = 0;
		gbcd.gridy = 3;
		JLabel lblTempoExistencia = new JLabel("Tempo de Existência:");
		lblTempoExistencia.setFont(new Font("Arial", Font.BOLD, 12));
		innerInfoDisco.add(lblTempoExistencia, gbcd);
		
		gbcd.gridx = 1;
		gbcd.gridy = 3;
		lblTempoExistenciaReal = new JLabel("----");
		lblTempoExistenciaReal.setFont(new Font("Arial", Font.PLAIN, 12));
		innerInfoDisco.add(lblTempoExistenciaReal, gbcd);
		
		// Ano de edicao
		gbcd.gridx = 0;
		gbcd.gridy = 4;
		JLabel lblDataEdicao = new JLabel("Data de Edição:");
		lblDataEdicao.setFont(new Font("Arial", Font.BOLD, 12));
		innerInfoDisco.add(lblDataEdicao, gbcd);
		
		gbcd.gridx = 1;
		gbcd.gridy = 4;
		lblDataEdicaoReal = new JLabel("----");
		lblDataEdicaoReal.setFont(new Font("Arial", Font.PLAIN, 12));
		innerInfoDisco.add(lblDataEdicaoReal, gbcd);
		
		containerInfoDisco.add(innerInfoDisco, BorderLayout.CENTER);
		container.add(containerInfoDisco, gbc);
		
		// =================
		// 		Produção
		// =================
		containerInfoProducao = new JPanel(new BorderLayout());
		containerInfoProducao.setBorder(BorderFactory.createTitledBorder(
			BorderFactory.createLineBorder(Color.GRAY, 1), "PRODUÇÃO DO DISCO", TitledBorder.LEFT, TitledBorder.TOP,
			new Font("Arial", Font.BOLD, 12)
		));
		containerInfoProducao.setBackground(Color.WHITE);
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 2;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		gbc.fill = GridBagConstraints.BOTH;
		
		JPanel tablesProdPanel = new JPanel(new GridLayout(1, 3, 10, 0));
		tablesProdPanel.setBackground(Color.WHITE);
		
		// Tabela de Produtores
		JPanel prodPanel = criarPainelTabela("PRODUTORES", new String[]{"Nome", "Email"});
		tablesProdPanel.add(prodPanel);
		
		// Tabela de Gravadoras
		JPanel gravaPanel = criarPainelTabela("GRAVADORAS", new String[]{"Nome", "Email"});
		tablesProdPanel.add(gravaPanel);
		
		// Tabela de Editoras
		JPanel editoraPanel = criarPainelTabela("EDITORAS", new String[]{"Nome", "Email"});
		tablesProdPanel.add(editoraPanel);
		
		containerInfoProducao.add(tablesProdPanel, BorderLayout.CENTER);
		container.add(containerInfoProducao, gbc);
		
		// Participantes 
		containerInfoParticipantes = new JPanel(new BorderLayout());
		containerInfoParticipantes.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY, 1), 
			"PARTICIPANTES DO DISCO", TitledBorder.LEFT, TitledBorder.TOP, new Font("Arial", Font.BOLD, 12)
		));
		containerInfoParticipantes.setBackground(Color.WHITE);
		gbc.gridx = 0;
		gbc.gridy = 2;
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
		setDadosDisco(disco.getTitulo(), disco.getGeneroMusical().toString(), disco.getPreco(), disco.discoExistencia(disco.getAnoEdicao()), disco.getEdicao().getDataEdicao());
		popularProdutores(disco.getProdutores());
		popularCompositores(disco.getCompositores());
		popularMusicos(disco.getMusicos());
		popularCantores(disco.getCantores());
		popularGravadoras(disco.getGravadoras());
		popularEditoras(disco.getEditoras());
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
	private void popularProdutores(List<Produtor> produtores) {
	    tabelaProdModel.setRowCount(0);
	    if (produtores != null) {
	        for (Produtor p : produtores) {
	            tabelaProdModel.addRow(new Object[]{
	                p.getNomeProdutor() + " " + p.getApelidoProdutor(),
	                p.getEmailProdutor()
	            });
	        }
	    }
	}
	private void popularGravadoras(List<Gravadora> gravadoras) {
    tabelaGravaModel.setRowCount(0);
	    if (gravadoras != null) {
	        for (Gravadora g : gravadoras) {
	            tabelaGravaModel.addRow(new Object[]{
	                g.getNomeGravadora(),
	                g.getEmailGravadora()
	            });
	        }
	    }
	}
	private void popularEditoras(List<Editora> editoras) {
	    tabelaEditorModel.setRowCount(0);
		    if (editoras != null) {
		        for (Editora e : editoras) {
		            tabelaEditorModel.addRow(new Object[]{
		                e.getNomeEditora(),
		                e.getEmailEditora()
		            });
		        }
		    }
		}
	private JPanel criarPainelTabela(String titulo, String[] colunas) {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBackground(Color.WHITE);
		panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		
		// Título com contador
		JLabel lblTitulo = new JLabel(titulo);
		lblTitulo.setFont(new Font("Arial", Font.BOLD, 12));
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
		tabela.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
		tabela.setFont(new Font("Arial", Font.PLAIN, 12));
		JScrollPane scroll = new JScrollPane(tabela);
		scroll.setPreferredSize(new Dimension(0, 150));
		if (titulo.contains("PRODUTORES")) {
			tabelaProdModel = model;
		} else if (titulo.contains("GRAVADORAS")) {
			tabelaGravaModel = model;
		} else if (titulo.contains("EDITORAS")) {
			tabelaEditorModel = model;
		}
		else if (titulo.contains("COMPOSITORES")) {
		    tabelaCompModel = model;
		} else if (titulo.contains("MÚSICOS")) {
		    tabelaMusicModel = model;
		} else if (titulo.contains("CANTORES")) {
		    tabelaCantorModel = model;
		}
		panel.add(scroll, BorderLayout.CENTER);
		
		return panel;
	}
	
	
	public void setDadosDisco(String titulo, String genero, double preco, int ano, Date dataEdicao) {
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
	    
	    lblDataEdicaoReal.setText(dataEdicao + "");

	}
	
	@Override 
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnSalvar) {
			this.dispose();
			dispose();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}