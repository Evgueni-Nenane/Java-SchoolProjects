package view;

import javax.swing.*;
import java.awt.*;

public class AdicionarInstrumentoDialog {

    public String AdicionarInstrumentoDialogoInserir() {

    	JPanel painel = new JPanel(new BorderLayout(0, 10));

        JLabel label = new JLabel("Digite o nome do instrumento:");
        label.setFont(new Font("Arial", Font.PLAIN, 16));
        painel.add(label, BorderLayout.NORTH);

        JTextField campoTexto = new JTextField(25); 
        campoTexto.setFont(new Font("Arial", Font.PLAIN, 16));
        painel.add(campoTexto, BorderLayout.CENTER);

        int resultado = JOptionPane.showConfirmDialog(null, painel, "Inserir Instrumento", JOptionPane.OK_CANCEL_OPTION, 
            JOptionPane.PLAIN_MESSAGE);

        if (resultado == JOptionPane.OK_OPTION) {
            String nome = campoTexto.getText().trim();
            if (!nome.isEmpty()) {
                return nome;
            }
        }
        return null;
    }
    }
