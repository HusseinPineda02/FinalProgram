
import java.awt.*;
import java.awt.event.KeyEvent;

public class Nave {
    public int x, y, dx;
    private int velocidad = 5;

    public Nave(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void mover() {
        x += dx;

        if (x < 0) x = 0;
        if (x > 760) x = 760;
    }

    public void dibujar(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillRect(x, y, 40, 20);
    }

    public void teclaPresionada(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            dx = -velocidad;
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            dx = velocidad;
        }
    }

    public void teclaSoltada(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_RIGHT) {
            dx = 0;
        }
    }
}
