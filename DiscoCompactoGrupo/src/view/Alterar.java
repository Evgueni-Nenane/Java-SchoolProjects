package view;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.time.LocalDateTime;

import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.border.LineBorder;

import controller.LoginController;
import controller.LogsController;
import model.Logs;
import model.Sessao;

public class Alterar extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JPasswordField pwSenhaAntiga;
	private JPasswordField pwSenhaNova;
	private String utilizadorLogado;
    private LoginController loginController;
    private LogsController logController;
    private Logs log;
    
	/**
	 * Create the dialog.
	 */
	
	public Alterar(String utilizador, String senha) {
		this.utilizadorLogado = utilizador;
		loginController = new LoginController();
		logController = new LogsController();
		setTitle("Alteração de Senha");
		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(true);
		setResizable(false);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		initialize();
	}
	
	
	private void initialize() {
		JLabel lblTitulo = new JLabel("Alterar Senha");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTitulo.setBounds(168, 22, 100, 20);
		contentPanel.add(lblTitulo);
		
		JLabel lblNome = new JLabel("Nome de Usuário");
		lblNome.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNome.setBounds(80, 73, 103, 14);
		contentPanel.add(lblNome);
		
		JLabel lblSenhaAntiga = new JLabel("Senha Antiga");
		lblSenhaAntiga.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSenhaAntiga.setBounds(80, 112, 103, 14);
		contentPanel.add(lblSenhaAntiga);
		
		pwSenhaAntiga = new JPasswordField();
		pwSenhaAntiga.setColumns(10);
		pwSenhaAntiga.setBounds(193, 106, 158, 29);
		contentPanel.add(pwSenhaAntiga);
		
		JLabel lblNome_1_1 = new JLabel("Senha Nova");
		lblNome_1_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNome_1_1.setBounds(80, 151, 92, 14);
		contentPanel.add(lblNome_1_1);
		
		pwSenhaNova = new JPasswordField();
		pwSenhaNova.setColumns(10);
		pwSenhaNova.setBounds(193, 146, 158, 29);
		contentPanel.add(pwSenhaNova);
		
		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnAlterar.setBorderPainted(false);
		btnAlterar.setBackground(new Color(128, 255, 0));
		btnAlterar.setBounds(152, 215, 132, 37);
		contentPanel.add(btnAlterar);
		
		JLabel lblNome2 = new JLabel(utilizadorLogado);
		lblNome2.setBackground(new Color(255, 255, 255));
		lblNome2.setBorder(new LineBorder(new Color(171, 173, 179)));
		lblNome2.setBounds(193, 66, 158, 29);
		contentPanel.add(lblNome2);
		btnAlterar.addActionListener(e -> salvarNovaSenha());
	}
	
	private void salvarNovaSenha() {
        String senhaAntiga = new String(pwSenhaAntiga.getPassword()).trim();
        String senhaNova = new String(pwSenhaNova.getPassword()).trim();

        if (senhaAntiga.isEmpty() || senhaNova.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        String utilizadorON = Sessao.getUtilizadorLogado().getUser_name();
        
        boolean senhaAlterada = loginController.atualizarSenha(utilizadorON, senhaAntiga, senhaNova);
        
        if (senhaAlterada) {
        		dispose();
				LocalDateTime horaAgora = LocalDateTime.now();
				log = new Logs(
						Sessao.getUtilizadorLogado().getCodigo(), Sessao.getUtilizadorLogado().getNome(),
						Sessao.getUtilizadorLogado().getApelido(), Sessao.getUtilizadorLogado().getPerfil().getNome(),
						Sessao.getUtilizadorLogado().getEmail(), "Alterou de Senha", horaAgora);
				logController.inserirLog(log);
            JOptionPane.showMessageDialog(this, "Senha alterada com sucesso!");
            JLogin login= new JLogin();
            login.setVisible(true);
        }
        else {
        	JOptionPane.showMessageDialog(this, "Senha inválida.");
        }
    }
	
}
