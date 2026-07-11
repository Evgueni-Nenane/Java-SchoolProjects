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


public class EditarCantorDialog extends JDialog implements ActionListener {

    private static final long serialVersionUID = 1L;
    private JTextField txtNome, txtContacto, txtEmail;
    private JButton btnSalvar;
    private Cantor cantor;
    private CantorController cantorController;
    private LogsController logController;
    private Logs log;
    
    public EditarCantorDialog(Cantor cantor) {
	    	this.cantorController = new CantorController();
	    	this.logController = new LogsController();
	    	this.cantor = cantor;
	    	
	    	
        setTitle("Editar Cantor");
        setSize(560, 360);
        setLocationRelativeTo(null);
        setModal(true);
        setLayout(new BorderLayout());

        // Título da cena
        JPanel titulo = new JPanel(new FlowLayout(FlowLayout.CENTER));
        
        JLabel tituloDialog = new JLabel("Edição de Cantor");
        
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
        
        nomePanel.add(new JLabel("Nome do Cantor: "), gbc);
        txtNome = new JTextField();
        txtNome.setEditable(false);
        txtNome.setPreferredSize(new Dimension(250, 30));
        txtNome.setMinimumSize(new Dimension(250, 30));
        txtNome.setMaximumSize(new Dimension(250, 30));
        gbc.gridx = 0;
        gbc.gridy = 1;
        nomePanel.add(txtNome, gbc);
        form.add(nomePanel, gbco);
        
        gbco.gridx = 0;
        gbco.gridy = 1;        
        form.add(new JLabel("Contacto do Cantor: "), gbco);
        
        txtContacto = new JTextField();
        txtContacto.setPreferredSize(new Dimension(250, 30));
        txtContacto.setMinimumSize(new Dimension(250, 30));
        txtContacto.setMaximumSize(new Dimension(250, 30));
        gbco.gridx = 0;
        gbco.gridy = 2;
        form.add(txtContacto, gbco);
        

        gbco.gridx = 0;
        gbco.gridy = 3;
        form.add(new JLabel("Email do compositor"), gbco);
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
        btnSalvar = new JButton("Atualizar");
        btnSalvar.addActionListener(this);
        btnSalvar.setPreferredSize(new Dimension(130, 30));
        painelBotoes.add(btnSalvar);
        add(painelBotoes, BorderLayout.SOUTH);
        carregarCantor();
    }
    
    public void carregarCantor() {
		if (cantor != null) {
			txtNome.setText(cantor.getNomeCompleto());
			txtContacto.setText(String.valueOf(cantor.getContactoCantor()));
			txtEmail.setText(String.valueOf(cantor.getEmailCantor()));
		}
	}
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnSalvar) {
            if(txtContacto.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Contacto obrigatório!", "Erro", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if(txtEmail.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Email obrigatório!", "Erro", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            cantor.setEmailCantor(txtEmail.getText());
            cantor.setContactoCantor(txtContacto.getText());
            boolean sucesso = cantorController.atualizarCantor(cantor);
            if(sucesso) {
	    			LocalDateTime horaAgora = LocalDateTime.now();
	    			log = new Logs(
	    					Sessao.getUtilizadorLogado().getCodigo(), Sessao.getUtilizadorLogado().getNome(),
	    					Sessao.getUtilizadorLogado().getApelido(), Sessao.getUtilizadorLogado().getPerfil().getNome(),
	    					Sessao.getUtilizadorLogado().getEmail(), "Editou cantor no sistema", horaAgora);
	    			logController.inserirLog(log);
                JOptionPane.showMessageDialog(this, "Cantor/a editado/a com sucesso!");
                dispose();
                
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao editar compositor!");
            }
        }

    }
 }
