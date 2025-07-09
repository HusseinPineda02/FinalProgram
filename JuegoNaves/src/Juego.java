
import javax.swing.JFrame;


public class Juego {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Juego de Naves");
        Mapa panel = new Mapa();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.add(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        panel.iniciarJuego();
    }
}