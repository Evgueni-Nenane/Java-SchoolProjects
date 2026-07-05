package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

import dao.LoginDAO;

public class JLogin extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTextField textFieldNome;
    private JPasswordField passwordField;
    
    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    JLogin window = new JLogin();
                    window.setVisible(true); // ← usa o próprio JLogin
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public JLogin() {
        initialize();
    }

    private boolean ValidarCampos() {
        String nome = textFieldNome.getText();
        String password = new String(passwordField.getPassword());
        if (nome.isEmpty()) {
            JOptionPane.showMessageDialog(this, "O campo Username não pode estar vazio.", "REVEJA OS CAMPOS", JOptionPane.ERROR_MESSAGE);
            textFieldNome.requestFocus();
            return false;
        }
        if (password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "O campo Password não pode estar vazio.", "REVEJA OS CAMPOS", JOptionPane.ERROR_MESSAGE);
            passwordField.requestFocus();
            return false;
        }
        return true;
    }

    private void initialize() {
        this.setResizable(false);
        this.setBounds(100, 100, 903, 546);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.getContentPane().setLayout(null);

        // Painel esquerdo
        JPanel painelEsquerdo = new JPanel();
        painelEsquerdo.setBounds(0, 0, 443, 506);
        painelEsquerdo.setLayout(null);
        painelEsquerdo.setBackground(SystemColor.activeCaption);
        this.getContentPane().add(painelEsquerdo);

        // Painel direito com imagem
        JPanel painelImagem = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon img = new ImageIcon(getClass().getResource("/resources/sideIMG.jpg"));
                g.drawImage(img.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        painelImagem.setBounds(443, 0, 444, 506);
        painelImagem.setLayout(null);
        this.getContentPane().add(painelImagem);

        JLabel lblNewLabel = new JLabel("LOGIN");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Serif", Font.BOLD, 20));
        lblNewLabel.setBounds(160, 68, 123, 45);
        painelEsquerdo.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Nome");
        lblNewLabel_1.setFont(new Font("Serif", Font.PLAIN, 18));
        lblNewLabel_1.setBounds(35, 160, 96, 32);
        painelEsquerdo.add(lblNewLabel_1);

        JLabel lblNewLabel_2 = new JLabel("Password");
        lblNewLabel_2.setFont(new Font("Serif", Font.PLAIN, 18));
        lblNewLabel_2.setBounds(35, 249, 135, 32);
        painelEsquerdo.add(lblNewLabel_2);

        textFieldNome = new JTextField();
        textFieldNome.setBounds(35, 189, 281, 32);
        textFieldNome.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        textFieldNome.setOpaque(false);
        textFieldNome.setColumns(10);
        textFieldNome.setCaretColor(Color.BLACK);
        painelEsquerdo.add(textFieldNome);

        passwordField = new JPasswordField();
        passwordField.setBounds(35, 272, 281, 32);
        passwordField.setBorder(new MatteBorder(0, 0, 2, 0, Color.BLACK));
        passwordField.setOpaque(false);
        passwordField.setCaretColor(Color.BLACK);
        painelEsquerdo.add(passwordField);

        JButton btnEntrar = new JButton("ENTRAR");
        btnEntrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!ValidarCampos()) return;

                String usuario = textFieldNome.getText();
                String senha = new String(passwordField.getPassword());

                if (LoginDAO.login(usuario, senha)) {
                	dispose();
                    
                	JOptionPane.showMessageDialog(JLogin.this, "Bem-vindo, " + usuario + "!");
	                if (LoginDAO.isPrimeiroAcesso(usuario)) {
	                    AlterarSenha alterarSenha = new AlterarSenha(usuario, senha);
	                    alterarSenha.setVisible(true);
	                } else {
	                    Dashboard dashboard = new Dashboard();
	                    dashboard.setVisible(true);
	                    }
                } else {
                    JOptionPane.showMessageDialog(JLogin.this, "Utilizador ou password incorreto!",
                        "Acesso Negado", JOptionPane.ERROR_MESSAGE);
                    passwordField.setText("");
                    passwordField.requestFocus();
                }
            }
        });
        btnEntrar.setForeground(Color.WHITE);
        btnEntrar.setBackground(Color.BLACK);
        btnEntrar.setBounds(110, 379, 111, 32);
        painelEsquerdo.add(btnEntrar);
    }
}