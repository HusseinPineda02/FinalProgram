import java.awt.Color;
import java.awt.event.*;
import javax.swing.*;

public class Menu extends JPanel implements ActionListener, KeyListener{
    Nave nave = new Nave();

    Menu(){
        this.setBounds(510, 81, 174, 499);
        this.setBackground(Color.gray);

        JLabel vidas = new JLabel("Vidas: " + nave.getVida());
        JLabel bajas = new JLabel("Enemigos eliminados: " + nave.getBajas());
        JLabel puntuacion = new JLabel("Puntuacion: " + nave.getPuntuacion());


        this.add(vidas);
        this.add(bajas);
        this.add(puntuacion);


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