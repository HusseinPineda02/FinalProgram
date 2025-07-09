
import java.awt.*;

public class Disparo {
    public int x, y;
    private int velocidad = 10;

    public Disparo(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void mover() {
        y -= velocidad;
    }

    public void dibujar(Graphics g) {
        g.setColor(Color.YELLOW);
        g.fillRect(x, y, 5, 10);
    }
}
