import java.util.ArrayList;
import javax.swing.Timer;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Tablero extends JPanel implements KeyListener, ActionListener {

    ArrayList<DisparoEnemigo> disparosEnemigos = new ArrayList<>();
    ArrayList<Enemigo> enemigos = new ArrayList<Enemigo>();
    ArrayList<Disparo> disparos = new ArrayList<Disparo>();

    Nave nave = new Nave();

    final int casilla = 50;
    int contGenerarEnemigo = 0;
    int contBajarEnemigo = 0;
    int ciclosGlobalEnemigos = 2; // inicia moviéndose cada 3 ciclos
    int contadorCiclos = 0;       // para saber cuándo acelerar
    int contadorCiclosDisparo = 0;

    private Menu menu;
    //Enemigo enemigo = new Enemigo();

    public Tablero(Menu menu) {
        this.menu = menu;
        this.setBounds(10,80,500,500);
        this.setBackground(Color.black);
        this.setVisible(true);
        this.setFocusable(true);
        this.addKeyListener(this);
        Timer timer = new Timer (150,this);
        timer.start();

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.CYAN);

        for (DisparoEnemigo d : disparosEnemigos) {
            g.fillRect(d.getX() * 50 + 20, d.getY() * 50 + 20, 10, 10);
        }
        for (Enemigo enemigo : enemigos) {
            enemigo.dibujar(g);
        }

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
        for (Enemigo enemigo : enemigos) {
            enemigo.dibujar(g);
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
        ArrayList<DisparoEnemigo> descartesDE = new ArrayList<>(); // limpiar disparos enemigos que salían del tablero


        for (Enemigo enemigo : enemigos) {
            if (Math.random() < 0.02) { // 2% de probabilidad por ciclo
                int celdaX = enemigo.getX() / 50;
                int celdaY = enemigo.getY() / 50;
                disparosEnemigos.add(new DisparoEnemigo(celdaX, celdaY + 1));
            }
        }


        for (DisparoEnemigo d : disparosEnemigos) {
            d.mover();
            if (d.getY() >= 10) {
                descartesDE.add(d);
            }
        }


        Rectangle rNave = new Rectangle(nave.getPosicionX(), nave.getPosicionY(), 50, 50);
        for (int i = disparosEnemigos.size() - 1; i >= 0; i--) {
            DisparoEnemigo d = disparosEnemigos.get(i);
            Rectangle rDisparoE = new Rectangle(d.getX() * 50 + 20, d.getY() * 50 + 20, 10, 10);

            if (rDisparoE.intersects(rNave)) {
                System.out.println("VIDA ANTES DEL DAÑO: " + nave.getVida());
                nave.recibirDamage(1);
                System.out.println("VIDA DESPUÉS DEL DAÑO: " + nave.getVida());

                disparosEnemigos.remove(i); // Eliminar inmediatamente

                if (nave.getVida() <= 0) {
                    JOptionPane.showMessageDialog(null, "¡La nave fue destruida! Fin del juego.");
                    System.exit(0);
                }

                break; // Solo un disparo puede dañar por ciclo
            }
        }
        disparosEnemigos.removeAll(descartesDE);

        for (Enemigo enemigo : enemigos) {
            enemigo.mover();
        }
        for(int i=0 ; i<disparos.size() ; i++) {
            if(disparos.get(i).getY() < 1) {
                descartesD.add(disparos.get(i));
            }
            disparos.get(i).moverDisparo();
        }

        descartesD.removeAll(disparos);
        contBajarEnemigo++;

        for (Disparo disparo : disparos) {
            for (Enemigo enemigo : enemigos) {
                Rectangle rDisparo = new Rectangle(disparo.getX() * 50 + 20, disparo.getY() * 50 + 20, 10, 10);
                Rectangle rEnemigo = enemigo.getBounds();

                if (rDisparo.intersects(rEnemigo)) {
                    enemigo.recibirDamage(1);        // le quitamos 1 vida al enemigo
                    descartesD.add(disparo);         // eliminamos el disparo

                    if (enemigo.estaMuerto()) {
                        descartesE.add(enemigo);     // eliminamos enemigo si su vida llegó a 0
                        nave.destruirEnemigos();     // sumamos puntos
                    }
                }
            }
        }

        enemigos.removeAll(descartesE);
        disparos.removeAll(descartesD);

        contadorCiclosDisparo++;
        if (contadorCiclosDisparo >= 2) {
            for (int i = 0 ; i < disparos.size() ; i++) {
            }
            contadorCiclosDisparo = 0;
        }

        descartesE.removeAll(enemigos);
        contGenerarEnemigo++;

        if (contGenerarEnemigo >= 6) {
            contGenerarEnemigo = 0;
            int columna = (int)(Math.random() * 10); // 0 a 9
            if (Math.random() < 0.8) {  //80% de los enemigos son comunes
                Enemigo nuevo = new Enemigo(columna, 0);
                nuevo.setCiclosParaMover(ciclosGlobalEnemigos); //
                enemigos.add(nuevo);
            } else {
                EnemigoT tanque = new EnemigoT(columna, 0);
                enemigos.add(tanque);
            }
        }
        menu.actualizarDatos(nave);
        repaint();
        contadorCiclos++;
        if (contadorCiclos >= 30) { // cada 30 ciclos, acelera
            if (ciclosGlobalEnemigos > 1) {
                ciclosGlobalEnemigos--;
                for (Enemigo enemigo : enemigos) {
                    enemigo.setCiclosParaMover(ciclosGlobalEnemigos);
                }
                System.out.println("Aumentando velocidad de enemigos. Ahora: cada " + ciclosGlobalEnemigos + " ciclos.");
            }
            contadorCiclos = 0;
        }
    }


    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}


}