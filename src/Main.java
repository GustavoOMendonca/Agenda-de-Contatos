import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {
        // Inicia a interface gráfica na thread de despacho de eventos do Swing
        SwingUtilities.invokeLater(() -> {
            new AgendaGUI().setVisible(true);
        });
    }
}