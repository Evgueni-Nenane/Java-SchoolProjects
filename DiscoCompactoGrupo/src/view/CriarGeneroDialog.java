package view;

import java.awt.Color;
import java.awt.Font;
import java.time.LocalDateTime;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import controller.GeneroController;
import controller.LogsController;
import model.Genero;
import model.Logs;
import model.Sessao;

public class CriarGeneroDialog extends JDialog {

    private static final long serialVersionUID = 1L;

    private JTextField txtGenero;
    private GeneroController generoController;
    private LogsController logController;
    private Logs log;

    public CriarGeneroDialog(GeneroController generoController) {

        this.generoController = generoController;
        this.logController = new LogsController();

        setTitle("Adicionar Genero");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(400, 220);
        setLocationRelativeTo(null);
        setModal(true);
        setLayout(null);

        initialize();
    }

    private void initialize() {

        JLabel titulo = new JLabel("ADICIONAR GÉNERO");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titulo.setBounds(80, 15, 250, 30);
        add(titulo);

        JLabel lblGenero = new JLabel("Género:");
        lblGenero.setBounds(30, 65, 80, 25);
        add(lblGenero);

        txtGenero = new JTextField();
        txtGenero.setBounds(30, 90, 330, 30);
        add(txtGenero);

        JButton btnSalvar = new JButton("Adicionar");
        btnSalvar.setBackground(Color.GREEN);
        btnSalvar.setForeground(Color.WHITE);
        btnSalvar.setBounds(120, 140, 140, 35);
        btnSalvar.addActionListener(e -> adicionarGenero());
        add(btnSalvar);
    }

    private void adicionarGenero() {

        String nomeGenero = txtGenero.getText().trim();

        if (nomeGenero.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Preencha o campo Género.",
                    "Aviso",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        Genero genero = new Genero(nomeGenero);

        boolean sucesso = generoController.adicionarGenero(genero);

        if (sucesso) {

            LocalDateTime horaAgora = LocalDateTime.now();

            log = new Logs(
                    Sessao.getUtilizadorLogado().getCodigo(),
                    Sessao.getUtilizadorLogado().getNome(),
                    Sessao.getUtilizadorLogado().getApelido(),
                    Sessao.getUtilizadorLogado().getPerfil().getNome(),
                    Sessao.getUtilizadorLogado().getEmail(),
                    "Adicionou género",
                    horaAgora
            );

            logController.inserirLog(log);

            JOptionPane.showMessageDialog(this,
                    "Género adicionado com sucesso!");

            dispose();

        } else {

            JOptionPane.showMessageDialog(this,
                    "Este género já existe na base de dados.");
        }
    }
}