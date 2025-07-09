import java.util.ArrayList;
import javax.swing.Timer;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Tablero extends JPanel implements KeyListener, ActionListener {
	
    ArrayList<Enemigo> enemigos = new ArrayList<Enemigo>();
    ArrayList<Disparo> disparos = new ArrayList<Disparo>();
    Nave nave = new Nave();

    final int casilla = 50;
    int contGenerarEnemigo = 0;
    int contBajarEnemigo = 0;
    //Enemigo enemigo = new Enemigo();

    public Tablero(){
    	this.setBounds(10,80,500,500);
    	this.setBackground(Color.black);
    	this.setVisible(true);
    	this.setFocusable(true);
    	this.addKeyListener(this);
    	Timer timer = new Timer (200,this);
    	timer.start();
    	
    }
    
    @Override
    protected void paintComponent(Graphics g) {
    	super.paintComponent(g);
    	
    	for (int y=0 ; y<10; y++) {
    		for (int x=0 ; x<10 ; x++ ) {
    			g.setColor(Color.white);
    			g.drawRect(x*casilla, y*casilla, casilla, casilla);
    		}
    	}
    	/*Disparo d1 = new Disparo( (int)(Math.random()*10+1) , (int)(Math.random()*10+1));
    	Disparo d2 = new Disparo( (int)(Math.random()*10+1) , (int)(Math.random()*10+1));
    	
    	disparos.add(d1);
    	disparos.add(d2);
    	Enemigo e1 = new Enemigo();
    	Enemigo e2 = new Enemigo();

    	enemigos.add(e1);
    	enemigos.add(e2);*/
    	
    	g.setColor(Color.GREEN);
    	g.fillRect(nave.getPosicionX(),nave.getPosicionY(), casilla, casilla);
    	
    	g.setColor(Color.RED);
    	for(int i=0 ; i<enemigos.size() ; i++) {
    		g.fillRect(enemigos.get(i).getX()*50, enemigos.get(i).getY()*50, casilla, casilla);
    	}
    	g.setColor(Color.YELLOW);
    	for(int i=0 ; i<disparos.size() ; i++) {
    		g.fillRect(disparos.get(i).getX()*50+20, disparos.get(i).getY()*50+20, 10, 10);
    	}
    
    }
    
	@Override
	public void keyPressed(KeyEvent e) {
		int tecla = e.getKeyCode();
		
		switch(tecla) {
			case KeyEvent.VK_LEFT: 
				if(nave.getCeldaX()!=0) nave.setCeldaX(nave.getCeldaX()-1);
				break;
			case KeyEvent.VK_RIGHT: ; 
				if(nave.getCeldaX()!=9) nave.setCeldaX(nave.getCeldaX()+1);
				break;
			case KeyEvent.VK_UP:
				if(nave.getCeldaY()!=0) nave.setCeldaY(nave.getCeldaY()-1);
				break;
			case KeyEvent.VK_DOWN:
				if(nave.getCeldaY()!=9) nave.setCeldaY(nave.getCeldaY()+1);
				break;
			case KeyEvent.VK_SPACE:
				if(nave.getCeldaY()!=0) {
					Disparo nuevoD = new Disparo(nave.getCeldaX(), nave.getCeldaY()-1);
					disparos.add(nuevoD);
				}
				break;
		}
		repaint();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		ArrayList<Disparo> descartesD = new ArrayList<Disparo>();
		ArrayList<Enemigo> descartesE = new ArrayList<Enemigo>();
		
		for(int i=0 ; i<disparos.size() ; i++) {
			if(disparos.get(i).getY() < 1) {
				descartesD.add(disparos.get(i));
			}
			disparos.get(i).moverDisparo();
		}
		
		descartesD.removeAll(disparos);
		contBajarEnemigo++;
		
		if(contBajarEnemigo>=2) {
			for(int i=0 ; i<enemigos.size() ; i++) {
				if(enemigos.get(i).getY()<=9) {
					enemigos.get(i).setY(enemigos.get(i).getY()+1);;
				}	else {
					descartesE.add(enemigos.get(i));
					nave.recibirDamage(1);;
					if(nave.getVida() == 0) {
						//fin del juego, pantalla o ventana de fin de juego, implementar despues
					}
				}
			}

		}
		descartesE.removeAll(enemigos);
		contGenerarEnemigo++;
		
		if(contGenerarEnemigo>=3) {
			contGenerarEnemigo = 0;
			Enemigo nuevoE = new Enemigo( (int)(Math.random()*10+1) , 0 );
			enemigos.add(nuevoE);
		}

		repaint();
	}

	
	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyReleased(KeyEvent e) {}


}