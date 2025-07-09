import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Tablero extends JPanel implements KeyListener {
	
    ArrayList<Enemigo> enemigos;
    ArrayList<Disparo> disparos;

    public Tablero(){
    	this.setBounds(10,80,500,500);
    	this.setBackground(Color.black);
    	this.setVisible(true);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
    	super.paintComponent(g);
    	
    	int casilla = 50;
    	
    	for (int y=0 ; y<10; y++) {
    		for (int x=0 ; x<10 ; x++ ) {
    			g.setColor(Color.white);
    			g.drawRect(x*casilla, y*casilla, casilla, casilla);
    		}
    	}
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

}