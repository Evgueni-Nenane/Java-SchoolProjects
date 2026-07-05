package view;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class ExemploAtualizacaoTabela extends JFrame {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// O modelo é a peça central - guarda os dados e notifica a tabela
    private DefaultTableModel modeloTabela;
    private JTable tabela;
    private JLabel labelStatus;
    private Random random = new Random();
    
    public ExemploAtualizacaoTabela() {
        configurarJanela();
        criarComponentes();
        pack();
        setLocationRelativeTo(null);
    }
    
    private void configurarJanela() {
        setTitle("Exemplo - Atualizar Tabela sem Sair da Tela");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
    }
    
    private void criarComponentes() {
        // Painel superior com botões
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        
        JButton btnAtualizar = new JButton("Atualizar Agora");
        JButton btnIniciarTimer = new JButton("Iniciar Atualização Automática");
        JButton btnPararTimer = new JButton("Parar Automática");
        
        labelStatus = new JLabel("Pronto");
        labelStatus.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        
        painelBotoes.add(btnAtualizar);
        painelBotoes.add(btnIniciarTimer);
        painelBotoes.add(btnPararTimer);
        painelBotoes.add(labelStatus);
        
        // Configurar a tabela
        String[] colunas = {"Sensor", "Temperatura (°C)", "Humidade (%)", "Última Leitura"};
        modeloTabela = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Impede edição direta na tabela
            }
        };
        
        tabela = new JTable(modeloTabela);
        tabela.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(tabela);
        scrollPane.setPreferredSize(new Dimension(600, 300));
        
        // Adicionar à janela
        add(painelBotoes, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        
        // Carregar dados iniciais
        carregarDadosIniciais();
        
        // Configurar ações dos botões
        btnAtualizar.addActionListener(this::atualizarTabela);
        
        // Timer para atualização automática a cada 3 segundos
        Timer timer = new Timer(3000, this::atualizarTabela);
        
        btnIniciarTimer.addActionListener(e -> {
            if (!timer.isRunning()) {
                timer.start();
                labelStatus.setText("Atualização automática: LIGADA (a cada 3s)");
            }
        });
        
        btnPararTimer.addActionListener(e -> {
            if (timer.isRunning()) {
                timer.stop();
                labelStatus.setText("Atualização automática: DESLIGADA");
            }
        });
    }
    
    private void carregarDadosIniciais() {
        // Limpa a tabela
        modeloTabela.setRowCount(0);
        
        // Adiciona dados iniciais (simulando sensores)
        String[] sensores = {"Sala 101", "Sala 102", "Laboratório A", "Escritório B", "Armazém"};
        
        for (String sensor : sensores) {
            modeloTabela.addRow(new Object[]{
                sensor,
                String.format("%.1f", 20.0 + random.nextDouble() * 10), // Temperatura
                String.format("%.1f", 40.0 + random.nextDouble() * 30), // Humidade
                LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"))
            });
        }
    }
    
    private void atualizarTabela(ActionEvent e) {
        // Simula leitura de novos dados (substitui isto por consulta real)
        for (int i = 0; i < modeloTabela.getRowCount(); i++) {
            // Atualiza apenas as colunas de temperatura e humidade
            modeloTabela.setValueAt(
                String.format("%.1f", 20.0 + random.nextDouble() * 10), 
                i, 1
            );
            modeloTabela.setValueAt(
                String.format("%.1f", 40.0 + random.nextDouble() * 30), 
                i, 2
            );
            modeloTabela.setValueAt(
                LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")), 
                i, 3
            );
        }
        
        labelStatus.setText("Dados atualizados às " + 
                          LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ExemploAtualizacaoTabela().setVisible(true);
        });
    }
}