
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Mapa extends JPanel implements ActionListener, KeyListener {
    Timer tiempo;    //actualiza cada 15 ms
    Nave nave;       //es el jugador
    ArrayList<Disparo> disparos; //lista de disparos activos
    ArrayList<Enemigo> enemigos;  //lista de enemigos en pantalla
    int contador = 0;   // crea enemigos cada cierto tiempo

    public Mapa() {
        this.setPreferredSize(new Dimension(800, 600));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.addKeyListener(this);

        nave = new Nave(400, 500);
        disparos = new ArrayList<>();
        enemigos = new ArrayList<>();
        tiempo = new Timer(15, this);
    }

    public void iniciarJuego() {
        tiempo.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        nave.dibujar(g);

        for (Disparo d : disparos)
            d.dibujar(g);

        for (Enemigo e : enemigos)
            e.dibujar(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        nave.mover();

        for (Disparo d : disparos)
            d.mover();

        for (Enemigo enemigo : enemigos)
            enemigo.mover();

        // Agregar enemigos periódicamente
        contador++;
        if (contador % 100 == 0) {
            enemigos.add(new Enemigo((int) (Math.random() * 760), 0));
        }

        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        nave.teclaPresionada(e);

        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            disparos.add(new Disparo(nave.x + 17, nave.y));
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        nave.teclaSoltada(e);
    }

    @Override
    public void keyTyped(KeyEvent e) {}
}

