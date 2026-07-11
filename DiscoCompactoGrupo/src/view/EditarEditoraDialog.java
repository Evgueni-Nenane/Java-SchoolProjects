package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;

import javax.swing.*;

import controller.EditoraController;
import controller.LogsController;
import model.Editora;
import model.Logs;
import model.Sessao;


public class EditarEditoraDialog extends JDialog implements ActionListener {

    private static final long serialVersionUID = 1L;
    private JTextField txtNome, txtContacto, txtEmail, txtEndereco;
    private JButton btnSalvar;
    private EditoraController editoraController;
    private LogsController logController;
    private Logs log;
    private Editora editora;
    
    public EditarEditoraDialog(Editora editora) {
    		this.editoraController = new EditoraController();
    		this.logController = new LogsController();
    		this.editora = editora;
    		
        setTitle("Atualizar Editora");
        setSize(560, 360);
        setLocationRelativeTo(null);
        setModal(true);
        setLayout(new BorderLayout());

        // Título da cena
        JPanel titulo = new JPanel(new FlowLayout(FlowLayout.CENTER));
        
        JLabel tituloDialog = new JLabel("Atualizar de Editora");
        
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
        
        nomePanel.add(new JLabel("Nome da Editora: "), gbc);
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
        form.add(new JLabel("Email da Editora"), gbco);
        txtEmail = new JTextField();
        txtEmail.setPreferredSize(new Dimension(250, 30));
        txtEmail.setMinimumSize(new Dimension(250, 30));
        txtEmail.setMaximumSize(new Dimension(250, 30));
        gbco.gridx = 0;
        gbco.gridy = 3;
        form.add(txtEmail, gbco);
        
        gbco.gridx = 0;
        gbco.gridy = 4;
        form.add(new JLabel("Contacto da Editora"), gbco);
        txtContacto = new JTextField();
        txtContacto.setPreferredSize(new Dimension(250, 30));
        txtContacto.setMinimumSize(new Dimension(250, 30));
        txtContacto.setMaximumSize(new Dimension(250, 30));
        gbco.gridx = 0;
        gbco.gridy = 5;
        form.add(txtContacto, gbco);

        gbco.gridx = 0;
        gbco.gridy = 6;
        form.add(new JLabel("Endereço da Editora"), gbco);
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
        carregarEditora();
    }

    public void carregarEditora() {
    		txtNome.setText(editora.getNomeEditora());
    		txtContacto.setText(editora.getContactoEditora());
    		txtEmail.setText(editora.getEmailEditora());
    		txtEndereco.setText(editora.getEndereco());
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
            
            editora.setContactoEditora(txtContacto.getText());
            editora.setEmailEditora(txtEmail.getText());
            editora.setEndereco(txtEndereco.getText());
            
            boolean sucesso = editoraController.atualizarEditora(editora);
            if(sucesso) {
    			LocalDateTime horaAgora = LocalDateTime.now();
    			log = new Logs(
    					Sessao.getUtilizadorLogado().getCodigo(), Sessao.getUtilizadorLogado().getNome(),
    					Sessao.getUtilizadorLogado().getApelido(), Sessao.getUtilizadorLogado().getPerfil().getNome(),
    					Sessao.getUtilizadorLogado().getEmail(), "Atualizou editora", horaAgora);
    			logController.inserirLog(log);
                JOptionPane.showMessageDialog(this, "Editora atualizada com sucesso!");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao atualizar Editora!");
            }
        }

    }    
}
