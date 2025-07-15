
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Juego extends JFrame {
    public Juego() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(700,630);
        this.setLayout(null);
        this.setTitle("SHIP BATTLE BETA");
        this.setResizable(false);

        JLabel titulo = new JLabel("SHIP BATTLE BETA");
        titulo.setBounds(300, 20, 200, 50);

        Nave nave = new Nave(); // instancias compartidas
        Menu datos = new Menu(nave);
        Tablero mapa = new Tablero(nave, datos);

        this.add(datos);
        this.add(mapa);
        this.add(titulo);

        this.setVisible(true);
    }

    public static void main(String[] args){
        Juego game = new Juego();
    }
}