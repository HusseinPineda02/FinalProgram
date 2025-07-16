import java.util.ArrayList;
import javax.swing.Timer;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Tablero extends JPanel implements KeyListener, ActionListener {
	
    ArrayList<Enemigo> enemigos = new ArrayList<>();
    ArrayList<EnemigoAmarillo> enemigosAmarillos = new ArrayList<>();
    ArrayList<Disparo> disparos = new ArrayList<>();
    ArrayList<DisparoEnemigo> disparosEnemigos = new ArrayList<>();
    
    private Nave nave;
    private Menu menu;
    private BufferedImage fondo;
    private BufferedImage naveSprite;
    private BufferedImage meteoro;
    private BufferedImage eaSprite;
    
    final int casilla = 50;
    int contGenerarEnemigo = 0;
    int contBajarEnemigo = 0;
    int contMoverAmarillo = 0;
    int contEnemigoAmarillo = 0;
    int contDisparoAmarillo = 0;
    

    public Tablero(Nave nave, Menu menu){
        this.nave = nave;
        this.menu = menu;
                
        this.setBounds(10,80,500,500);
        
        try {
			fondo = ImageIO.read(new File("src/fondo1.jpg"));
			naveSprite = ImageIO.read(new File("src/nave2.png"));
			meteoro = ImageIO.read(new File("src/meteoro.png"));
			eaSprite = ImageIO.read(new File("src/destructor.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        this.setVisible(true);
        this.setFocusable(true);
        this.addKeyListener(this);

        Timer timer = new Timer (100,this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        if(fondo!=null) {
        	g.drawImage(fondo, 0, 0, 500, 500, this);
        }
        
        if(naveSprite!=null) {
        	g.drawImage(naveSprite,  nave.getPosicionX(),  nave.getPosicionY(),  casilla, casilla,this );
        }

        for (int y=0 ; y<10; y++) {
            for (int x=0 ; x<10 ; x++ ) {
                g.setColor(Color.white);
                g.drawRect(x*casilla, y*casilla, casilla, casilla);
            }
        }
        
        for (Enemigo e : enemigos) {
        	g.drawImage(meteoro, e.getX()*50, e.getY()*50, casilla,  casilla,  this);
        }
        
        for (EnemigoAmarillo ea : enemigosAmarillos) {
        	if (ea.visible == true) {
        		 g.drawImage(eaSprite, ea.getX()*50, ea.getY()*50, casilla*2, casilla, this);
        	}        	
        }
        
        g.setColor(Color.YELLOW);
        for (Disparo d : disparos) {
            g.fillRect(d.getX()*50+20, d.getY()*50+20, 10, 10);
        }

        for (DisparoEnemigo de : disparosEnemigos) {
            de.dibujar(g);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int tecla = e.getKeyCode();

        switch(tecla) {
            case KeyEvent.VK_LEFT:
                if(nave.getCeldaX()!=0) nave.setCeldaX(nave.getCeldaX()-1);
                break;
            case KeyEvent.VK_RIGHT:
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
        ArrayList<Disparo> descartesD = new ArrayList<>();
        ArrayList<Enemigo> descartesE = new ArrayList<>();
        ArrayList<EnemigoAmarillo> descartesEA = new ArrayList<>();
        ArrayList<DisparoEnemigo> descartesDE = new ArrayList<>();

        for (Disparo d : disparos) {
            if (d.getY() < 1) descartesD.add(d);
            d.moverDisparo();
        }

        for (DisparoEnemigo de : disparosEnemigos) {
            if (de.getY() > 9) descartesDE.add(de);
            de.moverDisparo();
        }

        contBajarEnemigo++;
        if(contBajarEnemigo >= 10) {
        	contBajarEnemigo = 0;
            for (Enemigo enemigo : enemigos) {
                if (enemigo.getY() <= 9) {
                    enemigo.setY(enemigo.getY()+1);
                } else {
                    descartesE.add(enemigo);
                }
            }
        }
        // METEOROS
        contGenerarEnemigo++;
        if (contGenerarEnemigo >= 15) {
            contGenerarEnemigo = 0;
            Enemigo nuevoE = new Enemigo((int)(Math.random()*10), 0);
            enemigos.add(nuevoE);
        }
        // AMARILLO GRANDE
        contEnemigoAmarillo++;
        if (contEnemigoAmarillo >= 40 && enemigosAmarillos.size() < 2) {
            contEnemigoAmarillo = 0;
            EnemigoAmarillo ea = new EnemigoAmarillo(5, 0);
            enemigosAmarillos.add(ea);
        }
        // MOVIMIENTO HORIZONTAL AMARILLO
        for (EnemigoAmarillo ea1 : enemigosAmarillos) {
        	if (ea1.getX()==0) {
        		ea1.setDireccion(1);
        	} else if (ea1.getX()==8){
        		ea1.setDireccion(0);
        	}
        }        
        
        contMoverAmarillo++;
        if(contMoverAmarillo >= 20) {
        	contMoverAmarillo = 0;
        	for (EnemigoAmarillo ea2 : enemigosAmarillos) {
                if (ea2.getDireccion()==0) {
                    ea2.setX(ea2.getX()-1);
                } else {
                    ea2.setX(ea2.getX()+1);
                }        
        	}
        }
        boolean colisionNE, colisionDE, colisionNEA, colisionDEA, colisionNDE, colisionDDE; 
        // COLISION DE LA NAVE Y METEORITO
        for (Enemigo e1: enemigos) {
        	colisionNE = nave.getCeldaX()==e1.getX() && nave.getCeldaY()==e1.getY();
        	if(colisionNE){
        		descartesE.add(e1);
        		nave.recibirDamage(3);
        		if(nave.getVida() <= 0) {
        			GameOver();
        		}
        		nave.destruirEnemigos(1);
        		menu.actualizarDatos();

        	}
        	// COLISION DE DISPAROS CON METEORITO
        	for(Disparo d: disparos) {
            	colisionDE = (d.getX()==e1.getX() && d.getY()==e1.getY());
            	if(colisionDE) {
            		descartesE.add(e1);
            		descartesD.add(d);
            		nave.recuperarVida(1);
            		nave.destruirEnemigos(1);
            		menu.actualizarDatos();
            	}
            }
        }
        // COLISION CON ENEMIGO GRANDE
        for (EnemigoAmarillo ea: enemigosAmarillos) {
        	colisionNEA = ( (nave.getCeldaX()==ea.getX() || nave.getCeldaX()==ea.getX()+1) && nave.getCeldaY()==ea.getY() );
        	if(colisionNEA) {
        		nave.recibirDamage(3);
        		if(nave.getVida() <= 0) {
        			GameOver();
        		}
        		ea.recibirDamage(3);
        		if(ea.getVida()<=0) {
        			descartesEA.add(ea);
        			nave.destruirEnemigos(2);
        		}
        		menu.actualizarDatos();
        	}
        	for (Disparo d: disparos) {
        		colisionDEA = ( (d.getX()==ea.getX() || d.getX()==ea.getX()+1) && d.getY()==ea.getY());
        		if(colisionDEA) {
        			ea.recibirDamage(1);
        			if(ea.getVida()<=0) {
            			descartesEA.add(ea);
            			nave.destruirEnemigos(2);
            			nave.recuperarVida(2);
            			menu.actualizarDatos();
            		}
        		}
        	}
        }
        
        //COLISION CON DISPAROS ENEMIGOS
        for (DisparoEnemigo de: disparosEnemigos) {
        	
        }
        
        
        
        disparos.removeAll(descartesD);
        enemigos.removeAll(descartesE);
        enemigosAmarillos.removeAll(descartesEA);
        disparosEnemigos.removeAll(descartesDE);
        menu.actualizarDatos();
        repaint();  
    }
    
    public void GameOver() {
    	
    }
    @Override public void keyTyped(KeyEvent e) {}
    @Override public void keyReleased(KeyEvent e) {}
}