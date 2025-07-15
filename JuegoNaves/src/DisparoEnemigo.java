import java.awt.*;

public class DisparoEnemigo {
    private int x;
    private int y;

    public DisparoEnemigo(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void moverDisparo() {
        this.y += 1;
    }

    public void dibujar(Graphics g) {
        g.setColor(Color.ORANGE);
        g.fillOval(x * 50 + 20, y * 50 + 20, 10, 10);
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }
}
