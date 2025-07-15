import java.awt.Color;
import javax.swing.*;

public class Menu extends JPanel {
    private JLabel vidas;
    private JLabel bajas;
    private JLabel puntuacion;
    private Nave nave;

    public Menu(Nave nave) {
        this.nave = nave;

        this.setBounds(510, 81, 174, 499);
        this.setBackground(Color.gray);

        vidas = new JLabel("Vidas: " + nave.getVida());
        bajas = new JLabel("Enemigos eliminados: " + nave.getBajas());      //muestra atributos de la nave
        puntuacion = new JLabel("Puntuación: " + nave.getPuntuacion());

        this.add(vidas);
        this.add(bajas);
        this.add(puntuacion);
    }

    
    public void actualizarDatos() {
        vidas.setText("Vidas: " + nave.getVida());
        bajas.setText("Enemigos eliminados: " + nave.getBajas());       // Método que se llamará desde Tablero para actualizar los datos
        puntuacion.setText("Puntuación: " + nave.getPuntuacion());
    }
}

