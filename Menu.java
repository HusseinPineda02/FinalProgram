import java.awt.Color;
import java.awt.Font;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Menu extends JPanel {
	
    private JLabel vidas;
    private JLabel bajas;
    private JLabel puntuacion;
    private Nave nave;
    private BufferedImage fondoMenu;

    public Menu(Nave nave) {
        this.nave = nave;

        this.setBounds(510, 81, 174, 499);
        this.setBackground(Color.gray);
        
        try {
        	fondoMenu = ImageIO.read(new File("src/fondoMenu.jpg"));
     	} catch (IOException e) {
     		e.printStackTrace();
     	}
        vidas = new JLabel("Vidas: " + nave.getVida());
        bajas = new JLabel("Enemigos eliminados: " + nave.getBajas());
        puntuacion = new JLabel("Puntuación: " + nave.getPuntuacion());
        
        vidas.setForeground(Color.WHITE);	vidas.setFont(new Font("Serif", Font.BOLD, 15));
        bajas.setForeground(Color.WHITE);	bajas.setFont(new Font("Serif", Font.BOLD, 15));
        puntuacion.setForeground(Color.WHITE);	puntuacion.setFont(new Font("Serif", Font.BOLD, 15));

        this.add(vidas);
        this.add(bajas);
        this.add(puntuacion);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(fondoMenu, 0, 0, 174, 499,this);
        
        Graphics2D filtro= (Graphics2D) g;
        Color negro = new Color(0, 0, 0, 100); // Último valor = opacidad (0–255)
        filtro.setColor(negro);
        filtro.fillRect(0, 0, getWidth(), getHeight());

        repaint();
    }	
    
    public void actualizarDatos() {
        vidas.setText("Vidas: " + nave.getVida());
        bajas.setText("Enemigos eliminados: " + nave.getBajas());
        puntuacion.setText("Puntuación: " + nave.getPuntuacion());
    }
}