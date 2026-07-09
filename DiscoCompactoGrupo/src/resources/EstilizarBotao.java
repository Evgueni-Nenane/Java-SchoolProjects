package resources;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;

public class EstilizarBotao {
    public static void aplicar(JButton botao){
    	botao.setBackground(new Color(19, 175, 119));
    	botao.setForeground(Color.white);
    	botao.setFont(new Font("Segoe UI", Font.BOLD, 13));
    	botao.setFocusPainted(false);
    	botao.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
    	
    }
    public static void aplicarSec(JButton botao) {
    	botao.setBackground(new Color(16, 185, 129));
    	botao.setForeground(Color.WHITE);
    	botao.setFont(new Font("Segoe UI", Font.BOLD, 12));
    	botao.setFocusPainted(false);
    }
}
