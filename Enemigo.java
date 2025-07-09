import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle; 

public class Enemigo {
    private int x, y; //declarando la posicion del enemigo
    private int ancho = 40; //tamaño
    private int alto = 20;
    private int velocidad = 2; //velocidad de movimiento
    public boolean visible = true; //indica si esta dentro del panel o ya salió
    
    
    public Enemigo(int x, int y) { //coloca al enemigo en la parte superior de forma aleatoria
        this.x = x;
        this.y = y;
    }
    public Enemigo() {
    	this.x = ((int)(Math.random()*10));
    	this.y = 0;
    }
    public void setX (int x) {
    	this.x = x;
    }
    public void setY (int y) {
    	this.y = y;
    }
    public int getX() {
    	return x;
    }
    public int getY() {
    	return y;
    }

    public void mover() {
        y += velocidad;
        if (y > 600) { //si pasa los 600 pixeles se vuelve invisible
            visible = false;
        }
    }
    public void bajar() {
    	this.y += 1;
    }

    public void dibujar(Graphics g) {//lo dibuja de rojo
        if (visible) {
            g.setColor(Color.RED);
            g.fillRect(x, y, ancho, alto);
        }
    }

    public Rectangle getBounds() {//esto es para detectar choques (aun no se usa)
        return new Rectangle(x, y, ancho, alto);
    }
}