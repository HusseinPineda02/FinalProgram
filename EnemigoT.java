import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class EnemigoT extends Enemigo {
    private int anchoExtra = 80;
    private int altoExtra = 40;
    private int velocidadEspecial = 1;
    private int contador = 0;

    public EnemigoT(int x, int y) {
        super(x , y); // convierte de celda a píxeles
    }

    @Override
    public void mover() {
        contador++;
        if (contador >= 4) { // más lento que enemigo común
            setY(getY() + velocidadEspecial);
            contador = 0;
        }
        if (getY() > 600) {
            this.visible = false;
        }
    }

    @Override
    public void dibujar(Graphics g) {
        if (visible) {
            g.setColor(Color.YELLOW);
            g.fillRect(getX(), getY(), anchoExtra, altoExtra);
        }
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(getX(), getY(), anchoExtra, altoExtra);
    }
}