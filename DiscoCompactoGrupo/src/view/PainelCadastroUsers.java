package view;
import javax.swing.JFileChooser;
import javax.swing.ImageIcon;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.FileInputStream;
import java.io.ByteArrayOutputStream;
import java.awt.Image;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.LocalDateTime;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.FicheiroTxt;
import controller.LogsController;
import controller.NivelController;
import controller.UtilizadorController;
import model.Logs;
import model.NivelAcesso;
import model.Sessao;
import model.Sexo;
import model.Utilizador;

public class PainelCadastroUsers extends JPanel implements ActionListener, MouseListener {

	private static final long serialVersionUID = 1L;
	private JButton btnGerarSenha, btnEscolherFoto, btnRemoverFoto, btnCadastrar;
	private JTextField txtNome, txtApelido, txtEmail, txtContacto, txtSenha, txtUser_Name;
	private UtilizadorController utilizadorController;
	private LogsController logController;
	private Logs log;
	private NivelController niveisController;
	private JComboBox<Sexo> generoSexual;
	JComboBox<NivelAcesso> permissao;
	private JLabel fotoLabel;
	private byte[] fotoBytes;

	public PainelCadastroUsers(UtilizadorController utilizadorController, LogsController logController) {
		this.utilizadorController = utilizadorController;
		this.logController = logController;
		this.niveisController = new NivelController();

		JPanel fotoFormPanel = new JPanel(new BorderLayout());
		fotoFormPanel.setBackground(Color.gray);

		JPanel fotoPanel = new JPanel();
		fotoPanel.setBackground(Color.WHITE);
		fotoPanel.setPreferredSize(new Dimension(350, 0));

		JPanel formularioPanel = new JPanel(new GridBagLayout());
		formularioPanel.setBackground(Color.WHITE);

		// ===========================
		// Formulario Panel
		// ===========================

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);

		// Nome
		JLabel lblNome = new JLabel("Primeiro Nome");
		gbc.anchor = GridBagConstraints.WEST;
		gbc.gridx = 0;
		gbc.gridy = 0;
		formularioPanel.add(lblNome, (GridBagConstraints) gbc.clone());

		txtNome = new JTextField();
		gbc.gridx = 0;
		gbc.gridy = 1;
		txtNome.setPreferredSize(new Dimension(300, 30));
		formularioPanel.add(txtNome, (GridBagConstraints) gbc.clone());

		// Apelido
		JLabel lblApelido = new JLabel("Último Nome");
		gbc.anchor = GridBagConstraints.WEST;
		gbc.gridx = 1;
		gbc.gridy = 0;
		formularioPanel.add(lblApelido, (GridBagConstraints) gbc.clone());

		txtApelido = new JTextField();
		gbc.gridx = 1;
		gbc.gridy = 1;
		formularioPanel.add(txtApelido, (GridBagConstraints) gbc.clone());
		txtApelido.setPreferredSize(new Dimension(300, 30));

		// Genero
		JLabel lblGenero = new JLabel("Genero");
		gbc.anchor = GridBagConstraints.WEST;
		gbc.gridx = 0;
		gbc.gridy = 2;
		formularioPanel.add(lblGenero, (GridBagConstraints) gbc.clone());

		generoSexual = new JComboBox<>(Sexo.values());
		generoSexual.setPreferredSize(new Dimension(300, 30));
		gbc.gridx = 0;
		gbc.gridy = 3;
		formularioPanel.add(generoSexual, (GridBagConstraints) gbc.clone());

		// Perfis de Acesso

		JLabel lblPerfis = new JLabel("Permissões de Utilizador");
		gbc.anchor = GridBagConstraints.WEST;
		gbc.gridx = 1;
		gbc.gridy = 2;
		formularioPanel.add(lblPerfis, (GridBagConstraints) gbc.clone());

		permissao = new JComboBox<>();
		for (NivelAcesso perfil : niveisController.listarNiveis()) {
			permissao.addItem(perfil);
		}
		permissao.setPreferredSize(new Dimension(300, 30));
		gbc.gridx = 1;
		gbc.gridy = 3;
		formularioPanel.add(permissao, (GridBagConstraints) gbc.clone());

		// Username
		JLabel lblUserName = new JLabel("Nome de Utilizador");
		gbc.anchor = GridBagConstraints.WEST;
		gbc.gridx = 0;
		gbc.gridy = 4;
		formularioPanel.add(lblUserName, (GridBagConstraints) gbc.clone());

		txtUser_Name = new JTextField();
		txtUser_Name.setPreferredSize(new Dimension(300, 30));
		gbc.gridx = 0;
		gbc.gridy = 5;
		gbc.gridwidth = 2;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		formularioPanel.add(txtUser_Name, (GridBagConstraints) gbc.clone());

		// E-mail
		JLabel lblEmail = new JLabel("E-mail Corporativo");
		gbc.anchor = GridBagConstraints.WEST;
		gbc.gridx = 0;
		gbc.gridy = 6;
		formularioPanel.add(lblEmail, (GridBagConstraints) gbc.clone());

		txtEmail = new JTextField();
		gbc.gridx = 0;
		gbc.gridy = 7;
		gbc.gridwidth = 2;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		txtEmail.setPreferredSize(new Dimension(0, 30));
		formularioPanel.add(txtEmail, (GridBagConstraints) gbc.clone());

		// Contacto
		JLabel lblContacto = new JLabel("Número de telemóvel");
		gbc.anchor = GridBagConstraints.WEST;
		gbc.gridx = 0;
		gbc.gridy = 8;
		formularioPanel.add(lblContacto, (GridBagConstraints) gbc.clone());

		txtContacto = new JTextField();
		gbc.gridx = 0;
		gbc.gridy = 9;
		gbc.gridwidth = 2;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		txtContacto.setPreferredSize(new Dimension(0, 30));
		formularioPanel.add(txtContacto, (GridBagConstraints) gbc.clone());

		// Gerador da Senha
		JLabel lblSenha = new JLabel("Password Inicial");
		gbc.anchor = GridBagConstraints.WEST;
		gbc.gridx = 0;
		gbc.gridy = 10;
		gbc.gridwidth = 1;
		formularioPanel.add(lblSenha, (GridBagConstraints) gbc.clone());

		txtSenha = new JTextField();
		txtSenha.setEditable(false);
		gbc.gridx = 0;
		gbc.gridy = 11;
		gbc.gridwidth = 1;
		gbc.fill = GridBagConstraints.NONE;
		txtSenha.setPreferredSize(new Dimension(300, 30));
		formularioPanel.add(txtSenha, (GridBagConstraints) gbc.clone());

		btnGerarSenha = new JButton("Gerar Senha");
		gbc.gridx = 1;
		gbc.gridy = 11;
		gbc.gridwidth = 1;
		gbc.fill = GridBagConstraints.NONE;
		btnGerarSenha.setPreferredSize(new Dimension(150, 30));
		btnGerarSenha.addActionListener(this);
		formularioPanel.add(btnGerarSenha, (GridBagConstraints) gbc.clone());

		btnCadastrar = new JButton("Salvar Utilizador");
		gbc.gridx = 0;
		gbc.gridy = 13;
		gbc.gridwidth = 2;
		gbc.anchor = GridBagConstraints.CENTER;
		btnCadastrar.setPreferredSize(new Dimension(200, 35));
		btnCadastrar.addActionListener(this);
		formularioPanel.add(btnCadastrar, (GridBagConstraints) gbc.clone());

		// =====================
		// Foto Painel
		// =====================

		fotoPanel.setLayout(new BoxLayout(fotoPanel, BoxLayout.Y_AXIS));
		JPanel EscolherUser = new JPanel(new FlowLayout(FlowLayout.LEFT));
		EscolherUser.setBackground(fotoPanel.getBackground());
		EscolherUser.setPreferredSize(new Dimension(350, 20));
		EscolherUser.setMinimumSize(new Dimension(350, 20));
		EscolherUser.setMaximumSize(new Dimension(350, 20));
		fotoPanel.add(EscolherUser);

		JLabel escolherUser = new JLabel("Foto de Utilizador");
		EscolherUser.add(escolherUser);
		fotoPanel.add(EscolherUser);
		fotoPanel.add(Box.createVerticalStrut(100));

		// Foto em Si

		fotoLabel = new JLabel();
		fotoLabel.setPreferredSize(new Dimension(150, 150));
		fotoLabel.setMaximumSize(new Dimension(150, 150));
		fotoLabel.setMinimumSize(new Dimension(150, 150));
		fotoLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		fotoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		fotoPanel.add(Box.createVerticalStrut(10));
		fotoPanel.add(fotoLabel);

		// Botao de Escolher foto

		btnEscolherFoto = new JButton("Escolher Foto");
		btnEscolherFoto.setPreferredSize(new Dimension(150, 30));
		btnEscolherFoto.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnEscolherFoto.addActionListener(this);
		fotoPanel.add(Box.createVerticalStrut(10));
		fotoPanel.add(btnEscolherFoto);

		// Remover foto
		fotoPanel.add(Box.createVerticalStrut(10));
		btnRemoverFoto = new JButton("Remover Foto");
		btnRemoverFoto.setPreferredSize(new Dimension(150, 30));
		btnRemoverFoto.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnRemoverFoto.addActionListener(this);
		fotoPanel.add(btnRemoverFoto);

		fotoPanel.add(Box.createVerticalGlue());

		fotoFormPanel.add(fotoPanel, BorderLayout.WEST);
		fotoFormPanel.add(formularioPanel, BorderLayout.CENTER);
		
		
		URL urlImagem = getClass().getResource("/resources/noneProfile.png");
		ImageIcon icon = new ImageIcon(urlImagem);
		Image imagemEscalada = icon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
		fotoLabel.setIcon(new ImageIcon(imagemEscalada));
		
		this.setLayout(new BorderLayout());
		this.add(fotoFormPanel, BorderLayout.CENTER);

	}

	
	@Override
	public void actionPerformed(ActionEvent e) {

		//escolher foto
		if (e.getSource() == btnEscolherFoto) {
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setFileFilter(new FileNameExtensionFilter("Imagens", "jpg", "jpeg", "png"));
			int resultado = fileChooser.showOpenDialog(this);
			if (resultado == JFileChooser.APPROVE_OPTION) {
				try {
					File ficheiro = fileChooser.getSelectedFile();
					FileInputStream fis = new FileInputStream(ficheiro);
					ByteArrayOutputStream baos = new ByteArrayOutputStream();
					byte[] buffer = new byte[1024];
					int lidos;
					while ((lidos = fis.read(buffer)) != -1) {
						baos.write(buffer, 0, lidos);
					}
					fis.close();
					fotoBytes = baos.toByteArray();

					ImageIcon icon = new ImageIcon(ficheiro.getAbsolutePath());
					Image imagemEscalada = icon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
					fotoLabel.setIcon(new ImageIcon(imagemEscalada));
				} catch (IOException ex) {
					JOptionPane.showMessageDialog(this, "Erro ao carregar a imagem: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
				}
			}
			return;
		}

		if (e.getSource() == btnRemoverFoto) {
			fotoBytes = null;
			fotoLabel.setIcon(null);
			return;
		}

		if (e.getSource() == btnCadastrar) {

			// Validação
			if (txtNome.getText().trim().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Por favor insira o nome do utilizador!", "Erro", JOptionPane.WARNING_MESSAGE);
				return;
			}
			if (txtApelido.getText().trim().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Por favor insira o apelido do utilizador!", "Erro", JOptionPane.WARNING_MESSAGE);
				return;
			}
			if (txtUser_Name.getText().trim().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Por favor insira o username do utilizador!", "Erro", JOptionPane.WARNING_MESSAGE);
				return;
			}
			if (txtEmail.getText().trim().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Por favor insira o email do utilzador!", "Erro", JOptionPane.WARNING_MESSAGE);
				return;
			}
			if (txtSenha.getText().trim().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Por favor gere a senha!", "Erro", JOptionPane.WARNING_MESSAGE);
				return;
			}
			
			if (fotoBytes == null) {
				try {
					URL fotoPadrao = getClass().getResource("/resources/noneProfile.png");
					InputStream is = fotoPadrao.openStream();
					ByteArrayOutputStream baos = new ByteArrayOutputStream();
					byte[] buffer = new byte[1024];
					int lidos;
						while ((lidos = is.read(buffer)) != -1) {
							baos.write(buffer, 0, lidos);
						}
						fotoBytes = baos.toByteArray();
				} catch (IOException x) {
					x.printStackTrace();
				} catch (NullPointerException h) {
					JOptionPane.showMessageDialog(null, "Excepçao null" + h.getMessage() + "",
							"Mensagem de Erro", JOptionPane.ERROR_MESSAGE);
					h.printStackTrace();
				}
				
			}
			
			boolean firstAccess = true;
			Utilizador utilizador = new Utilizador(fotoBytes, txtNome.getText(), txtApelido.getText(), txtUser_Name.getText(),
					(Sexo) generoSexual.getSelectedItem(), (NivelAcesso) permissao.getSelectedItem(),
					txtEmail.getText(), txtContacto.getText(), txtSenha.getText(), firstAccess);

			boolean sucesso = utilizadorController.cadastrarUtilizador(utilizador);

			if (sucesso) {
				try {
					LocalDateTime horaAgora = LocalDateTime.now();
					log = new Logs(
							Sessao.getUtilizadorLogado().getCodigo(), Sessao.getUtilizadorLogado().getNome(),
							Sessao.getUtilizadorLogado().getApelido(), Sessao.getUtilizadorLogado().getPerfil().getNome(),
							Sessao.getUtilizadorLogado().getEmail(), "Cadastrar Utilizador", horaAgora);
					logController.inserirLog(log);
					FicheiroTxt.guardarTxt(utilizador);

				} catch (IOException e1) {
					e1.printStackTrace();
				}
				JOptionPane.showMessageDialog(null, "Utilizador cadastrado com sucesso!");
				limparCampos();
			} else {
				JOptionPane.showMessageDialog(null, "Erro ao cadastrar utilizador!");
			}
			return;
		}

		if (e.getSource() == btnGerarSenha) {
			if (txtNome.getText().trim().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Por favor insira o nome do utilizador!", "Erro", JOptionPane.WARNING_MESSAGE);
				return;
			}
			if (txtApelido.getText().trim().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Por favor insira o apelido do utilizador!", "Erro", JOptionPane.WARNING_MESSAGE);
				return;
			}
			String apelido = txtApelido.getText();
			String senha = txtUser_Name.getText().charAt(0) + ""  + apelido + "258";
			txtSenha.setText(senha);
		}
	}

	private void limparCampos() {
		txtNome.setText("");
		txtApelido.setText("");
		txtUser_Name.setText("");
		txtEmail.setText("");
		txtContacto.setText("");
		txtSenha.setText("");
		permissao.setSelectedIndex(0);
		generoSexual.setSelectedIndex(0);
		fotoBytes = null;
		URL fotoPadrao = getClass().getResource("/resources/noneProfile.png");
		ImageIcon icon = new ImageIcon(fotoPadrao);
		Image imagemEscalada = icon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
		fotoLabel.setIcon(new ImageIcon(imagemEscalada));
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