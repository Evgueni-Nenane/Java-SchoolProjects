package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;

import javax.swing.*;

import controller.GravadoraController;
import controller.LogsController;
import model.Gravadora;
import model.Logs;
import model.Sessao;


public class EditarGravadoraDialog extends JDialog implements ActionListener {

    private static final long serialVersionUID = 1L;
    private JTextField txtNome, txtEmail, txtEndereco, txtContacto;
    private JButton btnSalvar;
    private GravadoraController gravadoraController;
    private LogsController logController;
    private Logs log;
    private Gravadora gravadora;
    
    public EditarGravadoraDialog(Gravadora gravadora) {
    		this.gravadoraController = new GravadoraController();
    		this.logController = new LogsController();
    		this.gravadora = gravadora;
    		
        setTitle("Atualizar Gravadora");
        setSize(560, 360);
        setLocationRelativeTo(null);
        setModal(true);
        setLayout(new BorderLayout());

        // Título da cena
        JPanel titulo = new JPanel(new FlowLayout(FlowLayout.CENTER));
        
        JLabel tituloDialog = new JLabel("Atualizar Gravadora");
        
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
        
        nomePanel.add(new JLabel("Nome da Gravadora: "), gbc);
        txtNome = new JTextField();
        txtNome.setPreferredSize(new Dimension(250, 30));
        txtNome.setMinimumSize(new Dimension(250, 30));
        txtNome.setMaximumSize(new Dimension(250, 30));
        txtNome.setEditable(false);
        gbc.gridx = 0;
        gbc.gridy = 1;
        nomePanel.add(txtNome, gbc);        
        form.add(nomePanel, gbco);
        

        gbco.gridx = 0;
        gbco.gridy = 2;
        form.add(new JLabel("Contacto da Gravadora"), gbco);
        txtContacto = new JTextField();
        txtContacto.setPreferredSize(new Dimension(250, 30));
        txtContacto.setMinimumSize(new Dimension(250, 30));
        txtContacto.setMaximumSize(new Dimension(250, 30));
        gbco.gridx = 0;
        gbco.gridy = 3;
        form.add(txtContacto, gbco);
        
        gbco.gridx = 0;
        gbco.gridy = 4;
        form.add(new JLabel("Email da Gravadora"), gbco);
        txtEmail = new JTextField();
        txtEmail.setPreferredSize(new Dimension(250, 30));
        txtEmail.setMinimumSize(new Dimension(250, 30));
        txtEmail.setMaximumSize(new Dimension(250, 30));
        gbco.gridx = 0;
        gbco.gridy = 5;
        form.add(txtEmail, gbco);
        

        gbco.gridx = 0;
        gbco.gridy = 6;
        form.add(new JLabel("Endereço da Gravadora"), gbco);
        txtEndereco = new JTextField();
        txtEndereco.setPreferredSize(new Dimension(250, 30));
        txtEndereco.setMinimumSize(new Dimension(250, 30));
        txtEndereco.setMaximumSize(new Dimension(250, 30));
        gbco.gridx = 0;
        gbco.gridy = 7;
        form.add(txtEndereco, gbco);

        add(form, BorderLayout.CENTER);

        // Botões
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnSalvar = new JButton("Atualizar");
        btnSalvar.addActionListener(this);
        btnSalvar.setPreferredSize(new Dimension(130, 30));
        painelBotoes.add(btnSalvar);
        add(painelBotoes, BorderLayout.SOUTH);
        carregarGravadora();
    }
    
    public void carregarGravadora() {
    		txtNome.setText(gravadora.getNomeGravadora());
    		txtContacto.setText(gravadora.getContactoGravadora());
    		txtEndereco.setText(gravadora.getEnderecoGravadora());
    		txtEmail.setText(gravadora.getEmailGravadora());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnSalvar) {
            if(txtNome.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nome obrigatório!", "Erro", JOptionPane.WARNING_MESSAGE);
                return;
                }
            if(txtEmail.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Email obrigatório!", "Erro", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if(txtEndereco.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Endereço obrigatório!", "Erro", JOptionPane.WARNING_MESSAGE);
                return;
            }
            gravadora.setContactoGravadora(txtContacto.getText());
            gravadora.setEmailGravadora(txtEmail.getText());
            gravadora.setEnderecoGravadora(txtEndereco.getText());
            	boolean sucesso = gravadoraController.atualizarGravadora(gravadora);
            	if(sucesso) {
    			LocalDateTime horaAgora = LocalDateTime.now();
    			log = new Logs(
    					Sessao.getUtilizadorLogado().getCodigo(), Sessao.getUtilizadorLogado().getNome(),
    					Sessao.getUtilizadorLogado().getApelido(), Sessao.getUtilizadorLogado().getPerfil().getNome(),
    					Sessao.getUtilizadorLogado().getEmail(), "Atualizou gravadora", horaAgora);
    			logController.inserirLog(log);
                JOptionPane.showMessageDialog(this, "Gravadora atualizada com sucesso!");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao atualizar gravadora!");
            }
        }

    }    
}
