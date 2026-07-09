package view;

import javax.swing.*;
import controller.LogsController;
import dao.LoginDAO;
import model.Logs;
import model.Sessao;

import java.awt.*;
import java.time.LocalDateTime;

public class AlterarSenha extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTextField textFieldUser;
    private JPasswordField passwordFieldAntiga;
    private JPasswordField passwordFieldNova;
    private String utilizadorLogado;
    private LogsController logController;
    private Logs log;
    
    public AlterarSenha(String utilizador, String nivel) {
        this.utilizadorLogado = utilizador;
        setTitle("Alterar Senha");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(855, 566);
        setLocationRelativeTo(null);
        setLayout(null);
        initialize();
    }

    private void initialize() {
    	this.logController = new LogsController();

        JLabel titulo = new JLabel("ALTERAR SENHA");
        titulo.setForeground(Color.BLACK);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titulo.setBounds(280, 50, 300, 30);
        add(titulo);

        JLabel lblUser = new JLabel("Utilizador:");
        lblUser.setForeground(Color.WHITE);
        lblUser.setBounds(280, 110, 100, 25);
        add(lblUser);

        textFieldUser = new JTextField(utilizadorLogado);
        textFieldUser.setEditable(false);
        textFieldUser.setBounds(280, 135, 250, 28);
        add(textFieldUser);

        JLabel lblSenhaAntiga = new JLabel("Senha Antiga:");
        lblSenhaAntiga.setForeground(Color.BLACK);
        lblSenhaAntiga.setBounds(280, 175, 150, 25);
        add(lblSenhaAntiga);

        passwordFieldAntiga = new JPasswordField();
        passwordFieldAntiga.setBounds(280, 200, 250, 28);
        add(passwordFieldAntiga);

        JLabel lblSenhaNova = new JLabel("Nova Senha:");
        lblSenhaNova.setForeground(Color.BLACK);
        lblSenhaNova.setBounds(280, 240, 150, 25);
        add(lblSenhaNova);

        passwordFieldNova = new JPasswordField();
        passwordFieldNova.setBounds(280, 265, 250, 28);
        add(passwordFieldNova);

        JButton btnSalvar = new JButton("SALVAR");
        btnSalvar.setBackground(Color.GREEN);
        btnSalvar.setForeground(Color.WHITE);
        btnSalvar.setBounds(280, 320, 110, 34);
        btnSalvar.addActionListener(e -> salvarNovaSenha());
        add(btnSalvar);
    }

    private void salvarNovaSenha() {
        String senhaAntiga = new String(passwordFieldAntiga.getPassword()).trim();
        String senhaNova = new String(passwordFieldNova.getPassword()).trim();

        if (senhaAntiga.isEmpty() || senhaNova.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        String utilizadorON = Sessao.getUtilizadorLogado().getUser_name();
        
        boolean senhaAlterada = LoginDAO.atualizarSenha(utilizadorON, senhaAntiga, senhaNova);
        
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