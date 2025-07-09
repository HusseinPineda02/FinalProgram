
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle; 


public class Enemigo {
    public int x, y;
    private int ancho = 40;
    private int alto = 20;
    private int velocidad = 2;
    public boolean visible = true;

    public Enemigo(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void mover() {
        y += velocidad;
        if (y > 600) {
            visible = false;
        }
    }

    public void dibujar(Graphics g) {
        if (visible) {
            g.setColor(Color.RED);
            g.fillRect(x, y, ancho, alto);
        }
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, ancho, alto);
    }
}
