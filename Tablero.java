import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Tablero extends JPanel implements KeyListener, ActionListener {
	
    ArrayList<Enemigo> enemigos = new ArrayList<>();
    ArrayList<EnemigoAmarillo> enemigosAmarillos = new ArrayList<>();
    ArrayList<Disparo> disparos = new ArrayList<>();
    ArrayList<DisparoEnemigo> disparosEnemigos = new ArrayList<>();
   ArrayList<String> puntuaciones = new ArrayList<String>();
    
   	private Puntuaciones puntajes;
    private Nave nave;
    private Menu menu;
    private Sonido musica;
    private boolean pausado;
    private BufferedImage fondo;
    private BufferedImage naveSprite;
    private BufferedImage meteoro;
    private BufferedImage eaSprite;
    public  Timer timer;
      
    final int casilla = 50;
    int contGenerarEnemigo = 0;
    int contBajarEnemigo = 0;
    int contMoverAmarillo = 0;
    int contEnemigoAmarillo = 0;
    int contDisparoAmarillo = 0;
    int contDificultad = 0;
    int velocidad = 100; 
    int limiteDificultad = 60;

    public Tablero(Nave nave, Menu menu, Sonido musica){
        this.nave = nave;
        this.menu = menu;
        this.musica = musica;
        
        this.setBounds(10,80,500,500);
        
        try {
			fondo = ImageIO.read(new File("src/fondo1.jpg"));
			naveSprite = ImageIO.read(new File("src/nave2.png"));
			meteoro = ImageIO.read(new File("src/asteroide.png"));
			eaSprite = ImageIO.read(new File("src/destructor.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        this.setVisible(true);
        this.setFocusable(true);
        this.addKeyListener(this);

        timer = new Timer (velocidad,this);
        timer.start();
    }	

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        if(fondo!=null) {
        	g.drawImage(fondo, 0, 0, 500, 500, this);
        }
        Graphics2D filtro= (Graphics2D) g;
        Color negro = new Color(0, 0, 0, 150);
        filtro.setColor(negro);
        filtro.fillRect(0, 0, getWidth(), getHeight());

        
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
        
        contDificultad++;
        if(contDificultad >= limiteDificultad && velocidad >=30) {
        	limiteDificultad = limiteDificultad * 2;
        	velocidad -= 10;
        	timer.stop();
        	timer = new Timer(velocidad, this);
        	timer.start();
        }
        for (Disparo d : disparos) {
            if (d.getY() < 1) descartesD.add(d);
            d.moverDisparo();
        }

        for (DisparoEnemigo de : disparosEnemigos) {
            if (de.getY() > 9) descartesDE.add(de);
            de.moverDisparo();
        }
        // CREAR METEORITOS
        contGenerarEnemigo++;
        if (contGenerarEnemigo >= 10) {
            contGenerarEnemigo = 0;
            Enemigo nuevoE = new Enemigo((int)(Math.random()*10), 0);
            enemigos.add(nuevoE);
        }
        
        // BAJAR METEORITOS
        contBajarEnemigo++;
        if(contBajarEnemigo >= 5) {
        	contBajarEnemigo = 0;
            for (Enemigo enemigo : enemigos) {
                if (enemigo.getY() <= 9) {
                    enemigo.setY(enemigo.getY()+1);
                } else {
                    descartesE.add(enemigo);
                    nave.recibirDamage(4);
                    if(nave.getVida() <= 0) {
            			try {
            				this.removeKeyListener(this);
							gameOver("L");
						} catch (IOException e1) {
							e1.printStackTrace();
						}
            		}
                    menu.actualizarDatos();
                }
            }
        }
        // CREAR ENEMIGO GRANDE
        contEnemigoAmarillo++;
        if (contEnemigoAmarillo >= 40 && enemigosAmarillos.size() < 3) {
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
        
        // MOVIMIENTO HORIZONTAL DE AMARILLO
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
        // DISPARO DE NAVE GRANDE AMARILLO
        contDisparoAmarillo++;
        if(contDisparoAmarillo>=10) {
        	contDisparoAmarillo = 0 ;
        	for (EnemigoAmarillo ea: enemigosAmarillos) {
            	
            	DisparoEnemigo de = new DisparoEnemigo(ea.getX(),ea.getY()+1);
            	
            	disparosEnemigos.add(de);
            }
        }      
        boolean colisionNE, colisionDE, colisionNEA, colisionDEA, colisionNDE, colisionDDE; 
        // COLISION DE LA NAVE Y METEORITO
        for (Enemigo e1: enemigos) {
        	colisionNE = nave.getCeldaX()==e1.getX() && nave.getCeldaY()==e1.getY();
        	if(colisionNE){
        		descartesE.add(e1);
        		try {
        			this.removeKeyListener(this);
					gameOver("L");
				} catch (IOException e2) {
					e2.printStackTrace();
				}
        	}
        	// COLISION DE DISPAROS CON METEORITO
        	for(Disparo d: disparos) {
            	colisionDE = (d.getX()==e1.getX() && d.getY()==e1.getY());
            	if(colisionDE) {
            		descartesE.add(e1);
            		descartesD.add(d);
            		nave.recuperarVida(1);
            		nave.destruirEnemigos(1);
            		if(nave.getPuntuacion()>=5000) {
        				try {
        					this.removeKeyListener(this);
							gameOver("W");
						} catch (IOException e2) {
							e2.printStackTrace();
						}
	        			}
	            		menu.actualizarDatos();
            	}
            }	
        }
        // COLISION CON ENEMIGO GRANDE
        for (EnemigoAmarillo ea: enemigosAmarillos) {
        	colisionNEA = ( (nave.getCeldaX()==ea.getX() || nave.getCeldaX()==ea.getX()+1) && nave.getCeldaY()==ea.getY() );
        	if(colisionNEA) {
	        	try {
	        		this.removeKeyListener(this);
					gameOver("L");
				} catch (IOException e2) {
					e2.printStackTrace();
				}
        	}
        	for (Disparo d: disparos) {
        		colisionDEA = ( (d.getX()==ea.getX() || d.getX()==ea.getX()+1) && d.getY()==ea.getY());
        		if(colisionDEA) {
        			ea.recibirDamage(1);
        			if(ea.getVida()<=0) {
            			descartesEA.add(ea);
            			nave.destruirEnemigos(2);
            			if(nave.getPuntuacion()>=5000) {
            				try {
            					this.removeKeyListener(this);
								gameOver("W");
							} catch (IOException e2) {
								e2.printStackTrace();
							}
            			}
            			nave.recuperarVida(2);
            			menu.actualizarDatos();
            		}
        		}
        	}
        }
        
        //COLISION CON DISPAROS ENEMIGOS
        for (DisparoEnemigo de: disparosEnemigos) {
        	colisionNDE = ( nave.getCeldaX()==de.getX() && nave.getCeldaY()==de.getY());
        	if(colisionNDE) {
        		nave.recibirDamage(1);
        		if(nave.getVida() <= 0) {
        			try {
						gameOver("L");
					} catch (IOException e2) {
						e2.printStackTrace();
					}
        		}
        		descartesDE.add(de);
        		menu.actualizarDatos();
        	}
        }
        disparos.removeAll(descartesD);
        enemigos.removeAll(descartesE);
        enemigosAmarillos.removeAll(descartesEA);
        disparosEnemigos.removeAll(descartesDE);
        menu.actualizarDatos();
        repaint();
        
    }
	 public void gameOver(String condicion) throws IOException {
		 timer.stop();	
		 musica.detenerMusica();
		 puntajes.guardarPuntuacion(nave.getNombre(), nave.getPuntuacion());
		 String gano = (condicion.equals("W")) ? "VICTORIA" : "DERROTA";
		 int opcion = JOptionPane.showOptionDialog(this, gano + "\nJugador(a): "+nave.getNombre()+"\nPuntaje: "+nave.getPuntuacion(),
				 									"FIN DEL JUEGO", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE,
				 									null, new String[] {"PUNTUACIONES", "REINICIAR", "SALIR"}, "VOLVER A JUGAR");
		 if (opcion == JOptionPane.YES_OPTION) {
			 mostrarPuntuaciones();
			 System.exit(0);
		 }	else if (opcion == JOptionPane.NO_OPTION){
			 SwingUtilities.invokeLater(()->reiniciarJuego());
		 }	else {
			 System.exit(0);
		 }
	 }
	 public void reiniciarJuego() {
		 
		    contGenerarEnemigo = 0;
		    contBajarEnemigo = 0;
		    contMoverAmarillo = 0;
		    contEnemigoAmarillo = 0;
		    contDisparoAmarillo = 0;
		    contDificultad = 0;
		    velocidad = 100;
		    limiteDificultad = 60;
		    
		 	musica.detenerMusica();
			disparos.clear();
			enemigos.clear();
			enemigosAmarillos.clear();
			disparosEnemigos.clear();
			
			nave = new Nave();
			
	        this.setFocusable(true);
	        this.setVisible(true);
	        this.requestFocusInWindow();
	       
	        menu.setNave(nave);
	        
	        timer = new Timer(velocidad, this);
			timer.start();
			musica.pistaFondo("src/pokeymid.wav");
	 }
	 
		public void pausarJuego() {
			timer.stop();
			pausado = true;
			this.setFocusable(true);
			this.requestFocusInWindow();
		}
		public void continuarJuego() {
			timer.start();
			pausado = false;
			this.setFocusable(true);
			this.requestFocusInWindow();
		}
		public boolean estaPausado() {
			return pausado;
		}

		public void mostrarPuntuaciones() {
			puntajes.mejoresPuntuaciones();
		}
    @Override public void keyTyped(KeyEvent e) {}
    @Override public void keyReleased(KeyEvent e) {}

}	