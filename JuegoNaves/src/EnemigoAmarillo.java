import java.awt.*;

public class EnemigoAmarillo {
    private int x, y;
    private int ancho = 100;
    private int alto = 40;
    private int velocidad = 1;
    public boolean visible = true;

    public EnemigoAmarillo(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void mover() {
        x -= velocidad;
        if (x < 0) {
            visible = false;
        }
    }

    public void dibujar(Graphics g) {
        if (visible) {
            g.setColor(Color.YELLOW);
            g.fillRect(x * 50, y * 50, ancho, alto);
        }
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }
}

