package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;

import javax.swing.*;

import controller.CantorController;
import controller.LogsController;
import model.Cantor;
import model.Logs;
import model.Sessao;


public class CadastrarCantorDialog extends JDialog implements ActionListener {

    private static final long serialVersionUID = 1L;
    private JTextField txtNome, txtApelido, txtContacto, txtEmail;
    private JButton btnSalvar;
    private LogsController logController;
    private Logs log;
    
    public CadastrarCantorDialog() {
    	new CantorController();
    	
        setTitle("Cadastrar Cantor");
        setSize(560, 360);
        setLocationRelativeTo(null);
        setModal(true);
        setLayout(new BorderLayout());

        // Título da cena
        JPanel titulo = new JPanel(new FlowLayout(FlowLayout.CENTER));
        
        JLabel tituloDialog = new JLabel("Cadastro de Cantor");
        
        titulo.add(tituloDialog);
        
        add(titulo, BorderLayout.NORTH);
        
        // Formulário
        JPanel form = new JPanel(new GridBagLayout());
        form.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

        GridBagConstraints gbco = new GridBagConstraints();
        gbco.insets = new Insets(5,5,5,5);
        gbco.gridx = 0;
        gbco.gridy = 0;
        gbco.anchor = GridBagConstraints.WEST;
        gbco.fill = GridBagConstraints.BOTH;
        
        JPanel nomePanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,5,5);        
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        
        nomePanel.add(new JLabel("Nome do cantor: "), gbc);
        txtNome = new JTextField();
        txtNome.setPreferredSize(new Dimension(250, 30));
        txtNome.setMinimumSize(new Dimension(250, 30));
        txtNome.setMaximumSize(new Dimension(250, 30));
        gbc.gridx = 0;
        gbc.gridy = 1;
        nomePanel.add(txtNome, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 0;
        nomePanel.add(new JLabel("Apelido do cantor: "), gbc);
        txtApelido = new JTextField();
        txtApelido.setPreferredSize(new Dimension(250, 30));
        txtApelido.setMinimumSize(new Dimension(250, 30));
        txtApelido.setMaximumSize(new Dimension(250, 30));        
        gbc.gridx = 1;
        gbc.gridy = 1;
        nomePanel.add(txtApelido, gbc);
        
        form.add(nomePanel, gbco);
        
        gbco.gridx = 0;
        gbco.gridy = 1;        
        form.add(new JLabel("Contacto do cantor: "), gbco);
        
        txtContacto = new JTextField();
        txtContacto.setPreferredSize(new Dimension(250, 30));
        txtContacto.setMinimumSize(new Dimension(250, 30));
        txtContacto.setMaximumSize(new Dimension(250, 30));
        gbco.gridx = 0;
        gbco.gridy = 2;
        form.add(txtContacto, gbco);
        

        gbco.gridx = 0;
        gbco.gridy = 3;
        form.add(new JLabel("Email do cantor"), gbco);
        txtEmail = new JTextField();
        txtEmail.setPreferredSize(new Dimension(250, 30));
        txtEmail.setMinimumSize(new Dimension(250, 30));
        txtEmail.setMaximumSize(new Dimension(250, 30));
        gbco.gridx = 0;
        gbco.gridy = 4;
        form.add(txtEmail, gbco);

        add(form, BorderLayout.CENTER);

        // Botões
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnSalvar = new JButton("Cadastrar");
        btnSalvar.addActionListener(this);
        btnSalvar.setPreferredSize(new Dimension(130, 30));
        painelBotoes.add(btnSalvar);
        add(painelBotoes, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnSalvar) {
            if(txtNome.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nome obrigatório!", "Erro", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if(txtContacto.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Contacto obrigatório!", "Erro", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if(txtEmail.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Email obrigatório!", "Erro", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            Cantor cantor = new Cantor(txtNome.getText(), txtApelido.getText(), txtContacto.getText(), txtEmail.getText());
            boolean sucesso = CantorController.cadastrarCantor(cantor);
            if(sucesso) {
    			LocalDateTime horaAgora = LocalDateTime.now();
    			log = new Logs(
    					Sessao.getUtilizadorLogado().getCodigo(), Sessao.getUtilizadorLogado().getNome(),
    					Sessao.getUtilizadorLogado().getApelido(), Sessao.getUtilizadorLogado().getPerfil().name(),
    					Sessao.getUtilizadorLogado().getEmail(), "Cadastrou Cantor no sistema", horaAgora);
    			logController.inserirLog(log);
                JOptionPane.showMessageDialog(this, "Cantor cadastrado com sucesso!");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao cadastrar Cantor!");
            }
        }

    }
 }
