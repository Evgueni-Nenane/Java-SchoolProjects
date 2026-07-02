package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CadastrarGravadoraDialog extends JDialog implements ActionListener {

    private static final long serialVersionUID = 1L;
    private JTextField txtNome, txtEmail, txtEndereco, txtContacto;
    private JButton btnSalvar, btnCancelar;

    public CadastrarGravadoraDialog() {
        setTitle("Cadastrar Gravadora");
        setSize(500, 330);
        setLocationRelativeTo(null);
        setModal(true);
        setLayout(new BorderLayout());

        // Formulário
        JPanel form = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 10, 5, 10);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0; gbc.gridy = 0;
        form.add(new JLabel("Nome da Gravadora:"), gbc);
        txtNome = new JTextField();
        txtNome.setPreferredSize(new Dimension(250, 30));
        txtNome.setMinimumSize(new Dimension(250, 30));
        txtNome.setMaximumSize(new Dimension(250, 30));
        gbc.gridx = 0; gbc.gridy = 1;
        form.add(txtNome, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        form.add(new JLabel("Email:"), gbc);
        txtEmail = new JTextField();
        txtEmail.setPreferredSize(new Dimension(250, 30));
        txtEmail.setMinimumSize(new Dimension(250, 30));
        txtEmail.setMaximumSize(new Dimension(250, 30));
        gbc.gridx = 0; gbc.gridy = 3;
        form.add(txtEmail, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        form.add(new JLabel("Endereço:"), gbc);
        txtEndereco = new JTextField();
        txtEndereco.setPreferredSize(new Dimension(250, 30));
        txtEndereco.setMinimumSize(new Dimension(250, 30));
        txtEndereco.setMaximumSize(new Dimension(250, 30));
        gbc.gridx = 0; gbc.gridy = 5;
        form.add(txtEndereco, gbc);

        gbc.gridx = 0; gbc.gridy = 6;
        form.add(new JLabel("Contacto:"), gbc);
        txtContacto = new JTextField();
        txtContacto.setPreferredSize(new Dimension(250, 30));
        txtContacto.setMinimumSize(new Dimension(250, 30));
        txtContacto.setMaximumSize(new Dimension(250, 30));
        gbc.gridx = 0; gbc.gridy = 7;
        form.add(txtContacto, gbc);

        add(form, BorderLayout.CENTER);

        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(this);
        btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(this);
        painelBotoes.add(btnCancelar);
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
            JOptionPane.showMessageDialog(this, "Gravadora cadastrada com sucesso!");
            dispose();
        }
        if(e.getSource() == btnCancelar) {
            dispose();
        }
    }
}