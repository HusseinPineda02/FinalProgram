import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Tablero extends JPanel implements KeyListener {
	
    ArrayList<Enemigo> enemigos = new ArrayList<Enemigo>();
    ArrayList<Disparo> disparos = new ArrayList<Disparo>();
    Nave nave = new Nave();
    //Enemigo enemigo = new Enemigo();

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
    	Disparo d1 = new Disparo( (int)(Math.random()*10+1) , (int)(Math.random()*10+1));
    	Disparo d2 = new Disparo( (int)(Math.random()*10+1) , (int)(Math.random()*10+1));
    	
    	disparos.add(d1);
    	disparos.add(d2);
    	Enemigo e1 = new Enemigo();
    	Enemigo e2 = new Enemigo();

    	enemigos.add(e1);
    	enemigos.add(e2);
    	
    	g.setColor(Color.GREEN);
    	g.fillRect(nave.getPosicionX(),nave.getPosicionY(), casilla, casilla);
    	
    	g.setColor(Color.RED);
    	for(int i=0 ; i<enemigos.size() ; i++) {
    		g.fillRect(enemigos.get(i).getX(), enemigos.get(i).getY(), casilla, casilla);
    	}
    	g.setColor(Color.YELLOW);
    	for(int i=0 ; i<disparos.size() ; i++) {
    		g.fillRect(disparos.get(i).getX(), disparos.get(i).getY(), 10, 10);
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