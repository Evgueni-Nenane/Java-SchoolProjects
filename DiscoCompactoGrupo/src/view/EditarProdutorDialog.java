package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;

import javax.swing.*;

import controller.LogsController;
import controller.ProdutorController;
import model.Logs;
import model.Produtor;
import model.Sessao;


public class EditarProdutorDialog extends JDialog implements ActionListener {

    private static final long serialVersionUID = 1L;
    private JTextField txtNome, txtContacto, txtEmail;
    private JButton btnSalvar;
    private Produtor produtor;
    private ProdutorController produtorController;
    private LogsController logController;
    private Logs log;
    
    public EditarProdutorDialog(Produtor produtor) {
	    	this.produtorController = new ProdutorController();
	    	this.logController = new LogsController();
	    	this.produtor = produtor;
	    	
        setTitle("Atualizar Produtor");
        setSize(560, 360);
        setLocationRelativeTo(null);
        setModal(true);
        setLayout(new BorderLayout());

        // Título da cena
        JPanel titulo = new JPanel(new FlowLayout(FlowLayout.CENTER));
        
        JLabel tituloDialog = new JLabel("Atualizar Produtor");
        
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
        
        nomePanel.add(new JLabel("Nome do produtor: "), gbc);
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
        gbco.gridy = 1;        
        form.add(new JLabel("Contacto do produtor: "), gbco);
        
        txtContacto = new JTextField();
        txtContacto.setPreferredSize(new Dimension(250, 30));
        txtContacto.setMinimumSize(new Dimension(250, 30));
        txtContacto.setMaximumSize(new Dimension(250, 30));
        gbco.gridx = 0;
        gbco.gridy = 2;
        form.add(txtContacto, gbco);
        

        gbco.gridx = 0;
        gbco.gridy = 3;
        form.add(new JLabel("Email do produtor"), gbco);
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
        carregarProdutor();
    }

    public void carregarProdutor() {
    		txtNome.setText(produtor.getNomeCompleto());
    		txtContacto.setText(produtor.getContactoProdutor());
    		txtEmail.setText(produtor.getEmailProdutor());
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
            
            
            produtor.setContactoProdutor(txtContacto.getText());
            produtor.setEmailProdutor(txtEmail.getText());
            boolean sucesso = produtorController.atualizarProdutor(produtor);
            if(sucesso) {
    			LocalDateTime horaAgora = LocalDateTime.now();
    			log = new Logs(
    					Sessao.getUtilizadorLogado().getCodigo(), Sessao.getUtilizadorLogado().getNome(),
    					Sessao.getUtilizadorLogado().getApelido(), Sessao.getUtilizadorLogado().getPerfil().getNome(),
    					Sessao.getUtilizadorLogado().getEmail(), "Atualizou produtor", horaAgora);
    			logController.inserirLog(log);
                JOptionPane.showMessageDialog(this, "Produtor atualizado com sucesso!");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao atualizar produtor!");
            }
        }

    }
 }
