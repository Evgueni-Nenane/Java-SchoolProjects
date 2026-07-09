package view;

import java.awt.*;
import java.awt.event.*;
import java.time.LocalDateTime;
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
	private JTextField txtTitulo, txtPreco, txtNovo, txtPrecoNovo;
	private JComboBox<Genero> cbGenero, cbGeneroNovo;
	private JSpinner spDataEdicao;
	private Logs log;
	private LogsController logController;
	private JButton btnSalvar, btnCancelar;
	private GeneroController generoController;
		
	public EditarDiscoDialog(DiscoController discoController, DiscoCompacto disco, LogsController logController) {
		this.discoController = discoController;
		this.disco = disco;
		this.logController = logController;
		this.generoController = new GeneroController();
		
		this.setTitle("Editar Disco");
		this.setLayout(new BorderLayout());
		this.setModal(true);
		
		initComponents();
		pack();
		this.setSize(900, 450); 
		this.setLocationRelativeTo(null);
		this.setResizable(false);
	}
	
	private void initComponents() {
		JPanel topContainer = new JPanel();
		topContainer.setBackground(Color.WHITE);
		topContainer.setPreferredSize(new Dimension(0, 60));
		topContainer.setLayout(new BoxLayout(topContainer, BoxLayout.Y_AXIS));
		topContainer.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		
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
		
		JPanel container = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.anchor = GridBagConstraints.WEST;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		
		JPanel containerInfoDisco = new JPanel(new GridBagLayout());
		containerInfoDisco.setBorder(BorderFactory.createTitledBorder(
			BorderFactory.createLineBorder(Color.GRAY, 1), 
			"INFORMAÇÕES DO DISCO",
			TitledBorder.LEFT, 
			TitledBorder.TOP,
			new Font("Arial", Font.BOLD, 12)
		));
		containerInfoDisco.setBackground(Color.WHITE);
		gbc.gridx = 0;
		gbc.gridwidth = 2;
		
		GridBagConstraints gbcd = new GridBagConstraints();
		gbcd.insets = new Insets(3, 5, 3, 5);
		gbcd.anchor = GridBagConstraints.WEST;
		gbcd.fill = GridBagConstraints.HORIZONTAL;
		gbcd.weightx = 1.0;
		
		gbcd.gridx = 0;
		gbcd.gridy = 0;
		gbcd.gridwidth = 1;
		JLabel lblTitulo = new JLabel("Título do Disco:*");
		lblTitulo.setFont(new Font("Arial", Font.BOLD, 11));
		containerInfoDisco.add(lblTitulo, gbcd);
		
		gbcd.gridx = 1;
		gbcd.gridy = 0;
		txtTitulo = new JTextField(50);
		txtTitulo.setEditable(false);
		containerInfoDisco.add(txtTitulo, gbcd);
		
		gbcd.gridx = 0;
		gbcd.gridy = 1;
		JLabel lblGenero = new JLabel("Gênero Musical:*");
		lblGenero.setFont(new Font("Arial", Font.BOLD, 11));
		containerInfoDisco.add(lblGenero, gbcd);
		
		gbcd.gridx = 1;
		gbcd.gridy = 1;
		cbGenero = new JComboBox<>();
		cbGenero = new JComboBox<>();

		for (Genero genero : generoController.listarGeneros()) {
		    cbGenero.addItem(genero);
		}

		cbGenero.setEditable(false);
		cbGenero.setEnabled(false);
		
		cbGenero.setEditable(false);
		cbGenero.setEnabled(false);
		containerInfoDisco.add(cbGenero, gbcd);
		
		gbcd.gridx = 0;
		gbcd.gridy = 2;
		JLabel lblPreco = new JLabel("Preço:*");
		lblPreco.setFont(new Font("Arial", Font.BOLD, 11));
		containerInfoDisco.add(lblPreco, gbcd);
		
		gbcd.gridx = 1;
		gbcd.gridy = 2;
		txtPreco = new JTextField(20);
		txtPreco.setEditable(false);
		containerInfoDisco.add(txtPreco, gbcd);
		
		gbcd.gridx = 0;
		gbcd.gridy = 3;
		JLabel lblDataEdicao = new JLabel("Data de Edição:*");
		lblDataEdicao.setFont(new Font("Arial", Font.BOLD, 11));
		containerInfoDisco.add(lblDataEdicao, gbcd);
		
		gbcd.gridx = 1;
		gbcd.gridy = 3;
		SpinnerDateModel dateModel = new SpinnerDateModel();
		spDataEdicao = new JSpinner(dateModel);
		JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(spDataEdicao, "dd/MM/yyyy");
		spDataEdicao.setEditor(dateEditor);
		spDataEdicao.setPreferredSize(new Dimension(150, 25));
		spDataEdicao.setEnabled(false);
		
		containerInfoDisco.add(spDataEdicao, gbcd);
		
		
		container.add(containerInfoDisco, gbc);
		
		JPanel containerInfoEd = new JPanel(new GridBagLayout());
		containerInfoEd.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createLineBorder(Color.GRAY, 1), 
				"ATUALIZAR DO DISCO",
				TitledBorder.LEFT, 
				TitledBorder.TOP,
				new Font("Arial", Font.BOLD, 12)
				));
		containerInfoEd.setBackground(Color.WHITE);
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 2;
		
		GridBagConstraints gbcn = new GridBagConstraints();
		gbcn.insets = new Insets(3, 5, 3, 5);
		gbcn.anchor = GridBagConstraints.WEST;
		gbcn.fill = GridBagConstraints.HORIZONTAL;
		gbcn.weightx = 1.0;
		
		gbcn.gridx = 0;
		gbcn.gridy = 0;
		gbcn.gridwidth = 1;
		JLabel lblTituloNv = new JLabel("Título do Disco:*");
		lblTituloNv.setFont(new Font("Arial", Font.BOLD, 11));
		containerInfoEd.add(lblTituloNv, gbcn);
		
		gbcn.gridx = 1;
		gbcn.gridy = 0;
		txtNovo = new JTextField(30);
		containerInfoEd.add(txtNovo, gbcn);
		
		gbcn.gridx = 0;
		gbcn.gridy = 1;
		JLabel lblGeneroNovo = new JLabel("Gênero Musical:*");
		lblGenero.setFont(new Font("Arial", Font.BOLD, 11));
		containerInfoEd.add(lblGeneroNovo, gbcn);
		
		gbcn.gridx = 1;
		gbcn.gridy = 1;
		cbGeneroNovo = new JComboBox<>();
		
		for (Genero genero : generoController.listarGeneros()) {
			cbGeneroNovo.addItem(genero);
		}
		containerInfoEd.add(cbGeneroNovo, gbcn);
		
		gbcn.gridx = 0;
		gbcn.gridy = 2;
		JLabel lblPrecoNovo = new JLabel("Preço:*");
		lblPreco.setFont(new Font("Arial", Font.BOLD, 11));
		containerInfoEd.add(lblPrecoNovo, gbcn);
		
		gbcn.gridx = 1;
		gbcn.gridy = 2;
		txtPrecoNovo = new JTextField(15);
		containerInfoEd.add(txtPrecoNovo, gbcn);
		
		container.add(containerInfoEd, gbc);
		
		JPanel bottomContainer = new JPanel();
		bottomContainer.setBackground(Color.WHITE);
		bottomContainer.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		bottomContainer.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		btnSalvar = new JButton("Salvar");
		btnSalvar.setBackground(new Color(0, 120, 215));
		btnSalvar.setForeground(Color.WHITE);
		btnSalvar.setFocusPainted(false);
		btnSalvar.addActionListener(this);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(this);
		
		bottomContainer.add(btnSalvar);
		bottomContainer.add(btnCancelar);
		
		this.add(topContainer, BorderLayout.NORTH);
		this.add(container, BorderLayout.CENTER);
		this.add(bottomContainer, BorderLayout.SOUTH);
		carregarDisco();
	}
	
	public void carregarDisco() {
		if (disco != null) {
			txtTitulo.setText(disco.getTitulo());
			txtPreco.setText(String.valueOf(disco.getPreco()));
			spDataEdicao.setValue(disco.getEdicao().getDataEdicao());
			
			Genero generoSelecionado = disco.getGeneroMusical();

	        for (int i = 0; i < cbGenero.getItemCount(); i++) {
	            Genero genero = cbGenero.getItemAt(i);

	            if (genero.getCodigoGenero() == generoSelecionado.getCodigoGenero()) {
	                cbGenero.setSelectedIndex(i);
	                break;
	            }
	        }
			
		}
	}
	

	
	private boolean validarCampos() {
		if (txtNovo.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(this, "O campo 'Título do Disco' é obrigatório.", "Campo Obrigatório", JOptionPane.WARNING_MESSAGE);
			txtNovo.requestFocus();
			return false;
		}
		
		if (txtPrecoNovo.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(this, "O campo 'Preço' é obrigatório.", "Campo Obrigatório", JOptionPane.WARNING_MESSAGE);
			txtPrecoNovo.requestFocus();
			return false;
		}
		
		try {
			Double.parseDouble(txtPrecoNovo.getText().trim());
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "O campo 'Preço' deve ser um número válido.", "Valor Inválido", JOptionPane.WARNING_MESSAGE);
			txtNovo.requestFocus();
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
	        disco.setGeneroMusical((Genero) cbGeneroNovo.getSelectedItem());
	        disco.setPreco(Double.parseDouble(txtPrecoNovo.getText().trim()));

	        discoController.actualizarDisco(disco);

	        JOptionPane.showMessageDialog(this, "Disco atualizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
	    	LocalDateTime horaAgora = LocalDateTime.now();
	        log = new Logs(
					Sessao.getUtilizadorLogado().getCodigo(), Sessao.getUtilizadorLogado().getNome(),
					Sessao.getUtilizadorLogado().getApelido(), Sessao.getUtilizadorLogado().getPerfil().getNome(),
					Sessao.getUtilizadorLogado().getEmail(), "Editou disco", horaAgora);
			logController.inserirLog(log);
	        dispose();

	    } catch (NumberFormatException e) {
	        JOptionPane.showMessageDialog(this, "O preço deve ser um valor numérico.", "Erro", JOptionPane.ERROR_MESSAGE);
	    }
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnSalvar) {
			salvarDisco();
		} else if (e.getSource() == btnCancelar) {
			this.dispose();
		} 
		}
	}
