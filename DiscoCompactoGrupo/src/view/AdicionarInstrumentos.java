package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import controller.InstrumentoController;
import model.Instrumento;
import model.NivelAcesso;
import model.Sessao;
import resources.EstilizarBotao;
import resources.EstilizarTabela;

public class AdicionarInstrumentos extends JDialog implements ActionListener, MouseListener {

	private static final long serialVersionUID = 1L;
	private Set<Integer> instrumentosSelecionados = new HashSet<>();
	
	private DefaultTableModel tabelaInstrumentoModel;
	private JButton btnAdicionarInstrumento, btnLimparInstrumento, btnRemoverInstrumento, btnCancelar, btnSalvar;
	private InstrumentoController instrumentoController;
	private AdicionarInstrumentoDialog adicionarInstrumento;
	private JTable tabelaComp;
	
	public AdicionarInstrumentos(InstrumentoController instrumentoController) {
		this.instrumentoController = instrumentoController;
		this.adicionarInstrumento = new AdicionarInstrumentoDialog();
		
		setTitle("Instrumentos");
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
		// BOX 1 - Instrumento
		// ====================

		JTextField pesquisarComp = new JTextField();
		btnAdicionarInstrumento = new JButton("Adicionar");
		JPanel box1 = criarBoxHeader(
				"Instrumentos",
				"Selecione todos os instrumentos que o músico toca",
				pesquisarComp, btnAdicionarInstrumento);

		tabelaInstrumentoModel = criarModeloTabela(new String[] {"","Nome do Instrumento", "id" });
		
		pesquisarComp.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void insertUpdate(javax.swing.event.DocumentEvent e) { filtrar(); }
            public void removeUpdate(javax.swing.event.DocumentEvent e) { filtrar(); }
            public void changedUpdate(javax.swing.event.DocumentEvent e) { filtrar(); }

            private void filtrar() {
                String texto = pesquisarComp.getText().toLowerCase().trim();
                tabelaInstrumentoModel.setRowCount(0);

                List<Instrumento> instrumentos = instrumentoController.listarInstrumentos();
                for (Instrumento d : instrumentos) {
                    
                    if (texto.isEmpty() ||
                        String.valueOf(d.getNome()).toLowerCase().contains(texto)) {

                        tabelaInstrumentoModel.addRow(new Object[]{
                        		instrumentosSelecionados.contains(d.getCodigo()),
                        		d.getNome(),
                            d.getCodigo()
                        });
                    }
                    }
                }
        });
		
		tabelaComp = new JTable(tabelaInstrumentoModel);
		tabelaComp.getModel().addTableModelListener(e -> {

		    int linha = e.getFirstRow();


		    if (linha < 0 || linha >= tabelaInstrumentoModel.getRowCount()) {
		        return;
		    }

		        Boolean selecionado = (Boolean) tabelaInstrumentoModel.getValueAt(linha, 0);
		        Integer id = (Integer) tabelaInstrumentoModel.getValueAt(linha, 2);
		        
		        if (selecionado) {
		            instrumentosSelecionados.add(id);
		        } else {
		            instrumentosSelecionados.remove(id);
		        }
		});
		box1.add(new JScrollPane(tabelaComp) {{
			setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		}}, BorderLayout.CENTER);

		btnLimparInstrumento = new JButton("Desmarcar tudo");
		btnRemoverInstrumento = new JButton("Remover");
		
		EstilizarTabela.aplicar(tabelaComp);
		tabelaComp.getColumnModel().getColumn(0).setMinWidth(30);
		tabelaComp.getColumnModel().getColumn(0).setMaxWidth(30);
		tabelaComp.getColumnModel().getColumn(0).setPreferredWidth(30);
		tabelaComp.getColumnModel().getColumn(2).setMinWidth(0);
		tabelaComp.getColumnModel().getColumn(2).setMaxWidth(0);
		tabelaComp.getColumnModel().getColumn(2).setPreferredWidth(0);
		
		
		JPanel acoesBox1 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		acoesBox1.setBackground(Color.WHITE);
		acoesBox1.add(btnLimparInstrumento);
		acoesBox1.add(btnRemoverInstrumento);
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
		EstilizarBotao.aplicarTerc(btnCancelar);
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

		btnAdicionarInstrumento.addActionListener(this);
		btnLimparInstrumento.addActionListener(this);
		btnRemoverInstrumento.addActionListener(this);

		carregarInstrumentos();
	}

	private JPanel criarTopContainer() {
		JPanel topContainer = new JPanel();
        topContainer.setBackground(new Color(19, 175, 119));
		topContainer.setLayout(new BoxLayout(topContainer, BoxLayout.Y_AXIS));
		topContainer.setBorder(BorderFactory.createEmptyBorder(18, 25, 18, 25));
		topContainer.setPreferredSize(new Dimension(0, 90));
		
		JLabel titulo = new JLabel("Informações dos instrumentos");
		titulo.setFont(titulo.getFont().deriveFont(16f).deriveFont(java.awt.Font.BOLD));
		titulo.setForeground(Color.WHITE);

		JLabel descricao = new JLabel("Selecione os instrumentos musicais");
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
		botaoAdicionar.setForeground(Color.WHITE);
		botaoAdicionar.setBackground(new Color(19, 175, 119));
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


	@Override public void mouseClicked(MouseEvent e) {}
	@Override public void mousePressed(MouseEvent e) {}
	@Override public void mouseReleased(MouseEvent e) {}
	@Override public void mouseEntered(MouseEvent e) {}
	@Override public void mouseExited(MouseEvent e) {}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnAdicionarInstrumento) {
			String instrumento = adicionarInstrumento.AdicionarInstrumentoDialogoInserir();
			Instrumento instrumentoMusical = new Instrumento(instrumento);
			instrumentoController.adicionarInstrumento(instrumentoMusical);
			carregarInstrumentos();
			
		}
		if (e.getSource() == btnLimparInstrumento) {
			desmarcarInstrumentos();
		}
		if (e.getSource() == btnRemoverInstrumento) {
			if(Sessao.getUtilizadorLogado().getPerfil().getCodigoNivel() == NivelAcesso.OPERADOR) {
                JOptionPane.showMessageDialog(null, "Sem permissão suficiente", "Erro de permissão", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            int linhaSelecionada = tabelaComp.getSelectedRow();

            if(linhaSelecionada == -1) {
                JOptionPane.showMessageDialog(null, "Selecione um disco!", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int confirmacao = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover este genero?",
                "Confirmar Remoção", JOptionPane.YES_NO_OPTION);

            if(confirmacao == JOptionPane.YES_OPTION) {

                int codigo = (int) tabelaInstrumentoModel.getValueAt(linhaSelecionada, 2);
                boolean sucesso = instrumentoController.remover(codigo);

                if(sucesso) {
                    JOptionPane.showMessageDialog(null, "Genero removido com sucesso!");
                    carregarInstrumentos();
                } else {
                    JOptionPane.showMessageDialog(null, "Não é possível remover este Genero por estar associado a um disco!");
                }
            }
		}
		if (e.getSource() == btnSalvar) {
			this.dispose();
		}
		if (e.getSource() == btnCancelar) {
			desmarcarInstrumentos();
			this.dispose();
		}
	}

	public class HeaderIconRenderer extends JLabel implements TableCellRenderer {

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
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
				boolean hasFocus, int row, int column) {
			return this;
		}
	}

	public void carregarInstrumentos() {
		for (int i = 0; i < tabelaInstrumentoModel.getRowCount(); i++) {
			boolean marcado = (Boolean) tabelaInstrumentoModel.getValueAt(i, 0);
			int id = (Integer) tabelaInstrumentoModel.getValueAt(i, 2);

			if (marcado) {
				instrumentosSelecionados.add(id);
			} else {
				instrumentosSelecionados.remove(id);
			}
		}

		tabelaInstrumentoModel.setRowCount(0);
		List<Instrumento> instrumentos = instrumentoController.listarInstrumentos();
		for (Instrumento instrumento : instrumentos) {
			boolean estavaMarcado = instrumentosSelecionados.contains(instrumento.getCodigo());
			tabelaInstrumentoModel.addRow(new Object[] {
					estavaMarcado,
					instrumento.getNome(),
					instrumento.getCodigo()
			});
		}
	}

	public void desmarcarInstrumentos() {
		instrumentosSelecionados.clear();

		tabelaInstrumentoModel.setRowCount(0);
		List<Instrumento> instrumentos = instrumentoController.listarInstrumentos();
		for (Instrumento instrumento : instrumentos) {
			boolean estaDesmarcado = instrumentosSelecionados.contains(instrumento.getCodigo());
			tabelaInstrumentoModel.addRow(new Object[] {
					estaDesmarcado,
					instrumento.getNome(),
					instrumento.getCodigo()
			});
		}
	}

	public List<Instrumento> getInstrumentosSelecionados() {
		List<Instrumento> resultado = new ArrayList<>();
		List<Instrumento> todos = instrumentoController.listarInstrumentos();

		for (Instrumento instrumento : todos) {
			if (instrumentosSelecionados.contains(instrumento.getCodigo())) {
				resultado.add(instrumento);
			}
		}
		return resultado;
	}
	public void definirInstrumentosSelecionados(List<Instrumento> instrumentos) {
	    instrumentosSelecionados.clear();
	    if (instrumentos != null) {
	        for (Instrumento instrumento : instrumentos) {
	            instrumentosSelecionados.add(instrumento.getCodigo());
	        }
	    }
	    carregarInstrumentos();
	}

}
