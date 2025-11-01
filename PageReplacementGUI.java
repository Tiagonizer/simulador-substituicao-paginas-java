import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PageReplacementGUI extends JFrame {

    private final PageReplacementSimulator simulator;
    private final JTextField refStringField;
    private final JTextField frameCountField;
    private final JTextArea resultArea;

    public PageReplacementGUI() {
        // Inicializa o simulador (a lógica de substituição de páginas)
        simulator = new PageReplacementSimulator();

        // Configuração básica da janela
        setTitle("Simulador de Algoritmos de Substituição de Páginas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        
        // Painel de Entrada
        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        
        inputPanel.add(new JLabel("String de Referência (separada por vírgulas):"));
        refStringField = new JTextField("7, 0, 1, 2, 0, 3, 0, 4, 2, 3, 0, 3, 2, 1, 2, 0, 1, 7, 0, 1");
        inputPanel.add(refStringField);

        inputPanel.add(new JLabel("Número de Frames:"));
        frameCountField = new JTextField("3");
        inputPanel.add(frameCountField);

        JButton simulateButton = new JButton("Simular");
        simulateButton.addActionListener(this::simulateAction);
        inputPanel.add(simulateButton);
        
        add(inputPanel, BorderLayout.NORTH);

        // Painel de Resultados
        resultArea = new JTextArea(10, 40);
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea);
        
        add(scrollPane, BorderLayout.CENTER);

        // Finalização
        pack();
        setLocationRelativeTo(null); // Centraliza a janela
    }

    private void simulateAction(ActionEvent e) {
        try {
            // 1. Obter e validar a String de Referência
            String refStringText = refStringField.getText().trim();
            List<Integer> pageReferenceString = Arrays.stream(refStringText.split(","))
                    .map(String::trim)
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());

            // 2. Obter e validar o Número de Frames
            int frameCount = Integer.parseInt(frameCountField.getText().trim());
            if (frameCount <= 0) {
                JOptionPane.showMessageDialog(this, "O número de frames deve ser maior que zero.", "Erro de Entrada", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // 3. Executar as simulações
            int faultsFIFO = simulator.simulateFIFO(pageReferenceString, frameCount);
            int faultsLRU = simulator.simulateLRU(pageReferenceString, frameCount);
            int faultsOptimal = simulator.simulateOptimal(pageReferenceString, frameCount);
            int faultsClock = simulator.simulateClock(pageReferenceString, frameCount);

            // 4. Exibir os resultados
            StringBuilder results = new StringBuilder();
            results.append("--- Resultados da Simulação ---\n");
            results.append("String de Referência: ").append(pageReferenceString).append("\n");
            results.append("Número de Frames: ").append(frameCount).append("\n\n");
            results.append("Faltas de Página:\n");
            results.append("- Método FIFO: ").append(faultsFIFO).append(" faltas de página\n");
            results.append("- Método LRU: ").append(faultsLRU).append(" faltas de página\n");
            results.append("- Método Ótimo: ").append(faultsOptimal).append(" faltas de página\n");
            results.append("- Método Relógio: ").append(faultsClock).append(" faltas de página\n");

            resultArea.setText(results.toString());

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Entrada inválida. Certifique-se de que a String de Referência e o Número de Frames são números inteiros válidos.", "Erro de Entrada", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Ocorreu um erro durante a simulação: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        // Garante que a GUI seja executada na Thread de Despacho de Eventos (EDT)
        SwingUtilities.invokeLater(() -> {
            new PageReplacementGUI().setVisible(true);
        });
    }
}

// A lógica do simulador (PageReplacementSimulator) deve estar no mesmo diretório
// ou ser importada corretamente. Como o usuário forneceu o código em um arquivo separado,
// vamos garantir que ele esteja no mesmo diretório e seja compilado junto.
// O código do PageReplacementSimulator.java é o mesmo da fase anterior.
