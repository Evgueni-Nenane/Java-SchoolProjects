package view;

import java.awt.*;
import java.awt.event.*;
import java.time.LocalDateTime;
import java.util.List;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import controller.DiscoController;
import controller.GeneroController;
import controller.LogsController;
import model.DiscoCompacto;
import model.Genero;
import model.Logs;
import model.Sessao;

public class EditarDiscoDialog extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;

	private DiscoController discoController;
	private DiscoCompacto disco;

	private JTextField txtTitulo;
	private JTextField txtPreco;
	private JTextField txtNovo;
	private JTextField txtPrecoNovo;

	private JList<Genero> lstGenerosAtuais;
	private JList<Genero> lstGenerosDisponiveis;
	private JList<Genero> lstGenerosSelecionados;

	private DefaultListModel<Genero> modelGenerosAtuais;
	private DefaultListModel<Genero> modelGenerosDisponiveis;
	private DefaultListModel<Genero> modelGenerosSelecionados;

	private JSpinner spDataEdicao;

	private Logs log;
	private LogsController logController;

	private JButton btnSalvar;
	private JButton btnCancelar;
	private JButton btnAdicionarGenero;
	private JButton btnRemoverGenero;

	private GeneroController generoController;

	public EditarDiscoDialog(
			DiscoController discoController,
			DiscoCompacto disco,
			LogsController logController) {

		this.discoController = discoController;
		this.disco = disco;
		this.logController = logController;
		this.generoController = new GeneroController();

		setTitle("Editar Disco");
		setLayout(new BorderLayout());
		setModal(true);

		initComponents();

		pack();
		setSize(950, 550);
		setLocationRelativeTo(null);
		setResizable(false);
	}

	private void initComponents() {
		// Painel superior (título)
		JPanel topContainer = new JPanel();
		topContainer.setBackground(Color.WHITE);
		topContainer.setPreferredSize(new Dimension(0, 60));
		topContainer.setLayout(new BoxLayout(topContainer, BoxLayout.Y_AXIS));
		topContainer.setBorder(
				BorderFactory.createEmptyBorder(10, 20, 10, 20)
		);

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

		// Painel principal
		JPanel container = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 1;
		gbc.weighty = 1;

		// Painel de informações atuais do disco
		JPanel containerInfoDisco = criarPainelInfoDisco();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		container.add(containerInfoDisco, gbc);

		// Painel de edição
		JPanel containerInfoEd = criarPainelEdicao();
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 2;
		container.add(containerInfoEd, gbc);

		// Painel inferior (botões)
		JPanel bottomContainer = new JPanel();
		bottomContainer.setBackground(Color.WHITE);
		bottomContainer.setLayout(new FlowLayout(FlowLayout.RIGHT));

		btnSalvar = new JButton("Salvar");
		btnSalvar.setBackground(new Color(0, 120, 215));
		btnSalvar.setForeground(Color.WHITE);
		btnSalvar.addActionListener(this);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(this);

		bottomContainer.add(btnSalvar);
		bottomContainer.add(btnCancelar);

		add(topContainer, BorderLayout.NORTH);
		add(container, BorderLayout.CENTER);
		add(bottomContainer, BorderLayout.SOUTH);

		carregarDisco();
	}

	private JPanel criarPainelInfoDisco() {
		JPanel containerInfoDisco = new JPanel(new GridBagLayout());
		containerInfoDisco.setBorder(
				BorderFactory.createTitledBorder(
						BorderFactory.createLineBorder(Color.GRAY),
						"INFORMAÇÕES ATUAIS DO DISCO",
						TitledBorder.LEFT,
						TitledBorder.TOP,
						new Font("Arial", Font.BOLD, 12)
				)
		);

		GridBagConstraints gbcd = new GridBagConstraints();
		gbcd.insets = new Insets(3, 5, 3, 5);
		gbcd.fill = GridBagConstraints.HORIZONTAL;
		gbcd.weightx = 1;

		// Título
		gbcd.gridx = 0;
		gbcd.gridy = 0;
		JLabel lblTitulo = new JLabel("Título do Disco:");
		lblTitulo.setFont(new Font("Arial", Font.BOLD, 11));
		containerInfoDisco.add(lblTitulo, gbcd);

		gbcd.gridx = 1;
		txtTitulo = new JTextField(30);
		txtTitulo.setEditable(false);
		containerInfoDisco.add(txtTitulo, gbcd);

		// Gêneros atuais
		gbcd.gridx = 0;
		gbcd.gridy = 1;
		JLabel lblGenero = new JLabel("Gêneros Atuais:");
		lblGenero.setFont(new Font("Arial", Font.BOLD, 11));
		containerInfoDisco.add(lblGenero, gbcd);
		
		gbcd.gridx = 1;
		modelGenerosAtuais = new DefaultListModel<>();
		lstGenerosAtuais = new JList<>(modelGenerosAtuais);
		lstGenerosAtuais.setEnabled(false);
		JScrollPane scrollGeneros = new JScrollPane(lstGenerosAtuais);
		scrollGeneros.setPreferredSize(new Dimension(250, 60));
		containerInfoDisco.add(scrollGeneros, gbcd);

		// Preço
		gbcd.gridx = 0;
		gbcd.gridy = 2;
		JLabel lblPreco = new JLabel("Preço Atual:");
		lblPreco.setFont(new Font("Arial", Font.BOLD, 11));
		containerInfoDisco.add(lblPreco, gbcd);

		gbcd.gridx = 1;
		txtPreco = new JTextField(15);
		txtPreco.setEditable(false);
		containerInfoDisco.add(txtPreco, gbcd);

		// Data de edição
		gbcd.gridx = 0;
		gbcd.gridy = 3;
		JLabel lblData = new JLabel("Última Edição:");
		lblData.setFont(new Font("Arial", Font.BOLD, 11));
		containerInfoDisco.add(lblData, gbcd);

		gbcd.gridx = 1;
		spDataEdicao = new JSpinner(new SpinnerDateModel());
		JSpinner.DateEditor editor = new JSpinner.DateEditor(spDataEdicao, "dd/MM/yyyy");
		spDataEdicao.setEditor(editor);
		spDataEdicao.setEnabled(false);
		containerInfoDisco.add(spDataEdicao, gbcd);

		return containerInfoDisco;
	}

	private JPanel criarPainelEdicao() {
		JPanel containerInfoEd = new JPanel(new GridBagLayout());
		containerInfoEd.setBorder(
				BorderFactory.createTitledBorder(
						BorderFactory.createLineBorder(Color.GRAY),
						"ATUALIZAR DISCO",
						TitledBorder.LEFT,
						TitledBorder.TOP,
						new Font("Arial", Font.BOLD, 12)
				)
		);

		GridBagConstraints gbcn = new GridBagConstraints();
		gbcn.insets = new Insets(3, 5, 3, 5);
		gbcn.fill = GridBagConstraints.HORIZONTAL;
		gbcn.weightx = 1;

		// Novo título
		gbcn.gridx = 0;
		gbcn.gridy = 0;
		JLabel lblTituloNovo = new JLabel("Novo título:");
		lblTituloNovo.setFont(new Font("Arial", Font.BOLD, 11));
		containerInfoEd.add(lblTituloNovo, gbcn);

		gbcn.gridx = 1;
		txtNovo = new JTextField(30);
		containerInfoEd.add(txtNovo, gbcn);

		// Painel de gêneros com botões de adicionar/remover
		gbcn.gridx = 0;
		gbcn.gridy = 1;
		JLabel lblGeneroNovo = new JLabel("Gerenciar Gêneros:");
		lblGeneroNovo.setFont(new Font("Arial", Font.BOLD, 11));
		containerInfoEd.add(lblGeneroNovo, gbcn);

		gbcn.gridx = 1;
		JPanel painelGeneros = criarPainelGeneros();
		containerInfoEd.add(painelGeneros, gbcn);

		// Novo preço
		gbcn.gridx = 0;
		gbcn.gridy = 2;
		JLabel lblPrecoNovo = new JLabel("Novo preço:");
		lblPrecoNovo.setFont(new Font("Arial", Font.BOLD, 11));
		containerInfoEd.add(lblPrecoNovo, gbcn);

		gbcn.gridx = 1;
		txtPrecoNovo = new JTextField(15);
		containerInfoEd.add(txtPrecoNovo, gbcn);

		return containerInfoEd;
	}

	private JPanel criarPainelGeneros() {
		JPanel painelPrincipal = new JPanel(new BorderLayout(10, 0));
		
		modelGenerosDisponiveis = new DefaultListModel<>();
		modelGenerosSelecionados = new DefaultListModel<>();
		
		for (Genero genero : generoController.listarGeneros()) {
			modelGenerosDisponiveis.addElement(genero);
		}

		JPanel painelDisponiveis = new JPanel(new BorderLayout());
		painelDisponiveis.setBorder(BorderFactory.createTitledBorder("Disponíveis"));
		
		lstGenerosDisponiveis = new JList<>(modelGenerosDisponiveis);
		lstGenerosDisponiveis.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		JScrollPane scrollDisponiveis = new JScrollPane(lstGenerosDisponiveis);
		scrollDisponiveis.setPreferredSize(new Dimension(200, 100));
		painelDisponiveis.add(scrollDisponiveis, BorderLayout.CENTER);
		
		JPanel painelSelecionados = new JPanel(new BorderLayout());
		painelSelecionados.setBorder(BorderFactory.createTitledBorder("Selecionados"));
		
		lstGenerosSelecionados = new JList<>(modelGenerosSelecionados);
		lstGenerosSelecionados.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		JScrollPane scrollSelecionados = new JScrollPane(lstGenerosSelecionados);
		scrollSelecionados.setPreferredSize(new Dimension(200, 100));
		painelSelecionados.add(scrollSelecionados, BorderLayout.CENTER);
		
		// Painel de botões (centro)
		JPanel painelBotoes = new JPanel(new GridBagLayout());
		GridBagConstraints gbcBotoes = new GridBagConstraints();
		gbcBotoes.gridx = 0;
		gbcBotoes.fill = GridBagConstraints.HORIZONTAL;
		gbcBotoes.insets = new Insets(5, 5, 5, 5);
		
		btnAdicionarGenero = new JButton(">>");
		btnAdicionarGenero.setToolTipText("Adicionar gêneros selecionados");
		btnAdicionarGenero.addActionListener(this);
		
		btnRemoverGenero = new JButton("<<");
		btnRemoverGenero.setToolTipText("Remover gêneros selecionados");
		btnRemoverGenero.addActionListener(this);
		
		gbcBotoes.gridy = 0;
		painelBotoes.add(btnAdicionarGenero, gbcBotoes);
		
		gbcBotoes.gridy = 1;
		painelBotoes.add(btnRemoverGenero, gbcBotoes);
		
		// Montar painel principal
		painelPrincipal.add(painelDisponiveis, BorderLayout.WEST);
		painelPrincipal.add(painelBotoes, BorderLayout.CENTER);
		painelPrincipal.add(painelSelecionados, BorderLayout.EAST);
		
		return painelPrincipal;
	}

	private void carregarDisco() {
		if (disco == null) {
			return;
		}

		txtTitulo.setText(disco.getTitulo());
		txtPreco.setText(String.valueOf(disco.getPreco()));
		txtNovo.setText(disco.getTitulo());
		txtPrecoNovo.setText(String.valueOf(disco.getPreco()));

		if (disco.getEdicao() != null) {
			spDataEdicao.setValue(disco.getEdicao().getDataEdicao());
		}

		if (disco.getGeneroMusical() != null) {
			for (Genero generoDisco : disco.getGeneroMusical()) {
				modelGenerosAtuais.addElement(generoDisco);
			}
		}

		if (disco.getGeneroMusical() != null) {
			for (Genero generoDisco : disco.getGeneroMusical()) {
				modelGenerosSelecionados.addElement(generoDisco);
				
				for (int i = modelGenerosDisponiveis.size() - 1; i >= 0; i--) {
					Genero generoLista = modelGenerosDisponiveis.get(i);
					if (generoLista.getCodigoGenero() == generoDisco.getCodigoGenero()) {
						modelGenerosDisponiveis.remove(i);
						break;
					}
				}
			}
		}
	}

	private void adicionarGeneros() {
		List<Genero> selecionados = lstGenerosDisponiveis.getSelectedValuesList();
		
		if (selecionados.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Selecione pelo menos um gênero para adicionar.", 
				"Aviso", 
				JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		for (Genero genero : selecionados) {
			modelGenerosSelecionados.addElement(genero);
			modelGenerosDisponiveis.removeElement(genero);
		}
	}

	private void removerGeneros() {
		List<Genero> selecionados = lstGenerosSelecionados.getSelectedValuesList();
		
		if (selecionados.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Selecione pelo menos um gênero para remover.", 
				"Aviso", 
				JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		for (Genero genero : selecionados) {
			modelGenerosDisponiveis.addElement(genero);
			modelGenerosSelecionados.removeElement(genero);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnSalvar) {
			salvarDisco();
		} else if (e.getSource() == btnCancelar) {
			this.dispose();
		} else if (e.getSource() == btnAdicionarGenero) {
			adicionarGeneros();
		} else if (e.getSource() == btnRemoverGenero) {
			removerGeneros();
		}
	}

	private boolean validarCampos() {
		if (txtNovo.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(this, "O título do disco não pode estar vazio.", 
				"Erro de Validação", 
				JOptionPane.ERROR_MESSAGE);
			txtNovo.requestFocus();
			return false;
		}
		
		if (txtPrecoNovo.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(this, "O preço do disco não pode estar vazio.", "Erro de Validação", 
				JOptionPane.ERROR_MESSAGE);
			txtPrecoNovo.requestFocus();
			return false;
		}
		
		if (modelGenerosSelecionados.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Selecione pelo menos um gênero para o disco.", 
				"Erro de Validação", 
				JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		return true;
	}

	private void salvarDisco() {
		if (!validarCampos()) {
			return;
		}

		try {
			disco.setTitulo(txtNovo.getText().trim());
			disco.setPreco(Double.parseDouble(txtPrecoNovo.getText().trim()));

			List<Genero> generosSelecionados = new java.util.ArrayList<>();
			for (int i = 0; i < modelGenerosSelecionados.size(); i++) {
				generosSelecionados.add(modelGenerosSelecionados.get(i));
			}
			disco.setGeneroMusical(generosSelecionados);

			discoController.actualizarDisco(disco);

			JOptionPane.showMessageDialog(this, 
				"Disco atualizado com sucesso!", 
				"Sucesso", 
				JOptionPane.INFORMATION_MESSAGE);

			// Registrar log
			LocalDateTime horaAgora = LocalDateTime.now();
			log = new Logs(
				Sessao.getUtilizadorLogado().getCodigo(),
				Sessao.getUtilizadorLogado().getNome(),
				Sessao.getUtilizadorLogado().getApelido(),
				Sessao.getUtilizadorLogado().getPerfil().getNome(),
				Sessao.getUtilizadorLogado().getEmail(),
				"Editou disco: " + disco.getTitulo(),
				horaAgora
			);

			logController.inserirLog(log);
			dispose();

		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "O preço deve ser um valor numérico válido.", "Erro", 
				JOptionPane.ERROR_MESSAGE);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, 
				"Erro ao atualizar disco: " + e.getMessage(), "Erro", 
				JOptionPane.ERROR_MESSAGE);
		}
	}
}