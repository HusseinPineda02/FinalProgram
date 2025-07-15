

import java.util.ArrayList;
import javax.swing.Timer;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Tablero extends JPanel implements KeyListener, ActionListener {
    ArrayList<Enemigo> enemigos = new ArrayList<>();
    ArrayList<EnemigoAmarillo> enemigosAmarillos = new ArrayList<>();
    ArrayList<Disparo> disparos = new ArrayList<>();
    ArrayList<DisparoEnemigo> disparosEnemigos = new ArrayList<>();
    Nave nave = new Nave();

    final int casilla = 50;
    int contGenerarEnemigo = 0;
    int contBajarEnemigo = 0;
    int contEnemigoAmarillo = 0;
    int contDisparoAmarillo = 0;

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
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int y=0 ; y<10; y++) {
            for (int x=0 ; x<10 ; x++ ) {
                g.setColor(Color.white);
                g.drawRect(x*casilla, y*casilla, casilla, casilla);
            }
        }

        g.setColor(Color.GREEN);
        g.fillRect(nave.getPosicionX(),nave.getPosicionY(), casilla, casilla);

        for (Enemigo e : enemigos) {
            g.setColor(Color.RED);
            g.fillRect(e.getX()*50, e.getY()*50, casilla, casilla);
        }

        for (EnemigoAmarillo ea : enemigosAmarillos) {
            ea.dibujar(g);
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
        if(contBajarEnemigo >= 2) {
            for (Enemigo enemigo : enemigos) {
                if (enemigo.getY() <= 9) {
                    enemigo.setY(enemigo.getY()+1);
                } else {
                    descartesE.add(enemigo);
                    nave.recibirDamage(1);
                }
            }
        }

        contGenerarEnemigo++;
        if (contGenerarEnemigo >= 3) {
            contGenerarEnemigo = 0;
            Enemigo nuevoE = new Enemigo((int)(Math.random()*10), 0);
            enemigos.add(nuevoE);
        }

        contEnemigoAmarillo++;
        if (contEnemigoAmarillo >= 10 && enemigosAmarillos.size() < 2) {
            contEnemigoAmarillo = 0;
            EnemigoAmarillo ea = new EnemigoAmarillo(9, (int)(Math.random()*5));
            enemigosAmarillos.add(ea);
        }

        for (EnemigoAmarillo ea : enemigosAmarillos) {
            ea.mover();
            if (!ea.visible) descartesEA.add(ea);
        }

        contDisparoAmarillo++;
        if (contDisparoAmarillo >= 5) {
            contDisparoAmarillo = 0;
            for (EnemigoAmarillo ea : enemigosAmarillos) {
                DisparoEnemigo nuevoDisparo = new DisparoEnemigo(ea.getX(), ea.getY() + 1);
                disparosEnemigos.add(nuevoDisparo);
            }
        }

        
                    // COLISIONES

            // Disparo jugador vs Disparo enemigo
            for (Disparo d : disparos) {
                for (DisparoEnemigo de : disparosEnemigos) {
                    if (d.getX() == de.getX() && d.getY() == de.getY()) {
                        descartesD.add(d);
                        descartesDE.add(de);
                    }
                }
            }

            // Disparo enemigo vs Nave
            for (DisparoEnemigo de : disparosEnemigos) {
                if (de.getX() == nave.getCeldaX() && de.getY() == nave.getCeldaY()) {
                    descartesDE.add(de);
                    nave.recibirDamage(1);
                    if (nave.getVida() <= 0) {
                        nave.setCeldaX(-1);
                        nave.setCeldaY(-1);
                    }
                }
            }

            // Enemigo rojo vs Nave
            for (Enemigo enemigo : enemigos) {
                if (enemigo.getX() == nave.getCeldaX() && enemigo.getY() == nave.getCeldaY()) {
                    nave.setCeldaX(-1);
                    nave.setCeldaY(-1);
                }
            }

            // Disparo jugador vs Enemigo rojo
            for (Disparo d : disparos) {
                for (Enemigo enemigo : enemigos) {
                    if (d.getX() == enemigo.getX() && d.getY() == enemigo.getY()) {
                        descartesD.add(d);
                        descartesE.add(enemigo);
                    }
                }
            }

            // Disparo jugador vs Enemigo amarillo
            for (Disparo d : disparos) {
                for (EnemigoAmarillo ea : enemigosAmarillos) {
                    if (d.getX() == ea.getX() && d.getY() == ea.getY()) {
                        descartesD.add(d);
                        descartesEA.add(ea);
                        nave.destruirEnemigos();
                    }
                }
            }
        disparos.removeAll(descartesD);
        enemigos.removeAll(descartesE);
        enemigosAmarillos.removeAll(descartesEA);
        disparosEnemigos.removeAll(descartesDE);
        repaint();
    }

    @Override public void keyTyped(KeyEvent e) {}
    @Override public void keyReleased(KeyEvent e) {}
}

