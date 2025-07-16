import java.awt.Color;
import java.awt.event.*;
import javax.swing.*;

public class Menu extends JPanel implements ActionListener, KeyListener{

    Nave nave = new Nave();

    private JLabel vidas;
    private JLabel bajas;
    private JLabel puntuacion;

    Menu(){
        this.setBounds(510, 81, 174, 499);
        this.setBackground(Color.gray);

        vidas = new JLabel("Vidas: " + nave.getVida());
        bajas = new JLabel("Enemigos eliminados: " + nave.getBajas());
        puntuacion = new JLabel("Puntuacion: " + nave.getPuntuacion());


        this.add(vidas);
        this.add(bajas);
        this.add(puntuacion);



    }
    public void actualizarDatos(Nave nave) {
        vidas.setText("Vidas: " + nave.getVida());
        bajas.setText("Enemigos eliminados: " + nave.getBajas());
        puntuacion.setText("Puntuacion: " + nave.getPuntuacion());
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }



}