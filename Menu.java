import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Menu extends JPanel {
	
    private JLabel vidas;
    private JLabel bajas;
    private JLabel puntuacion;
    private JLabel player;
    private Nave nave;
    private BufferedImage fondoMenu;
    JButton pausa;
    JButton reiniciar;
    JButton salir;
    
    private Tablero mapa;
    
    public void setTablero(Tablero mapa) {
    	this.mapa = mapa;
    }
    public void setNave(Nave nave) {
    	this.nave = nave;
    }
    public Nave getNave() {
    	return nave;
    }

    public Menu(Nave nave) {
        this.nave = nave;
        this.setBounds(510, 81, 174, 499);
        this.setBackground(Color.gray);
        this.setLayout(null);
        
        
        try {
        	fondoMenu = ImageIO.read(new File("src/fondoMenu.jpg"));
     	} catch (IOException e) {
     		e.printStackTrace();
     	}
        
        
        vidas = new JLabel("Vidas: " + nave.getVida());
        bajas = new JLabel("Bajas: " + nave.getBajas());
        puntuacion = new JLabel("Puntuación: " + nave.getPuntuacion());
        player = new JLabel("J: " + nave.getNombre());
        
        vidas.setForeground(Color.WHITE);	//vidas.setFont(new Font("Dialog", Font.BOLD, 15));
        bajas.setForeground(Color.WHITE);	//bajas.setFont(new Font("Dialog", Font.BOLD, 15));
        puntuacion.setForeground(Color.WHITE);	//puntuacion.setFont(new Font("Dialog", Font.BOLD, 15));
        
        player.setForeground(Color.WHITE);	player.setFont(new Font("Dialog", Font.BOLD, 15));
        
        
        
        pausa = new JButton("PAUSA");
        reiniciar = new JButton("REINICIAR");
        salir = new JButton("SALIR");
        
        
        player.setBounds(40, 10, 100, 50);
        
        vidas.setBounds(40, 40, 100, 50);
        bajas.setBounds(40, 80, 100, 50);
        puntuacion.setBounds(40, 120, 100, 50);
        
        pausa.setBounds(45, 280, 100, 50);
        reiniciar.setBounds(45, 340, 100, 50);
        salir.setBounds(45, 400, 100, 50);
        
        pausa.setBackground(Color.WHITE);
        reiniciar.setBackground(Color.GREEN);
        salir.setBackground(Color.RED);   
        
        pausa.addActionListener(e->{
        	if(this.mapa.estaPausado()) {
        		mapa.continuarJuego();
        		pausa.setText("PAUSAR");
        		mapa.setFocusable(true);
        	}	else {
        		mapa.pausarJuego();
        		pausa.setText("CONTINUAR");
        		mapa.setFocusable(true);
        	}
        	mapa.requestFocusInWindow();
        	mapa.setFocusable(true);
        });
        
        reiniciar.addActionListener(e->{
        	mapa.reiniciarJuego();
        	this.setTablero(mapa);
            mapa.setFocusable(true);
            this.setVisible(true);
        });
        
        
        salir.addActionListener(e->{
        	System.exit(0);
        });
        
        this.add(player);
        
        this.add(vidas);
        this.add(bajas);
        this.add(puntuacion);
        
        this.add(pausa);
        this.add(reiniciar);
        this.add(salir);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(fondoMenu, 0, 0, 174, 499,this);
        
        Graphics2D filtro= (Graphics2D) g;
        Color negro = new Color(0, 0, 0, 100);
        filtro.setColor(negro);
        filtro.fillRect(0, 0, getWidth(), getHeight());

        repaint();
    }	
    
    public void actualizarDatos() {
    	player.setText("J: " + nave.getNombre());
        vidas.setText("Vidas: " + nave.getVida());
        bajas.setText("Bajas: " + nave.getBajas());
        puntuacion.setText("Puntuación: " + nave.getPuntuacion());
    }
    
    public void pausarJuego(Timer timer) {
    	this.pausa.add("CONTINUAR", pausa);
    	mapa.setFocusable(true);
    	timer.stop();
    }
}