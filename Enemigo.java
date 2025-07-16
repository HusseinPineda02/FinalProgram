import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Enemigo {
    private int x, y;
    private int ancho = 40;
    private int alto = 20;
    private int vida = 1;

    private int limiteCiclos = 2;     // Ciclos que debe esperar antes de moverse (mayor = más lento)
    private int contador = 0;         // Cuenta los ciclos para saber cuándo moverse

    public boolean visible = true;

    // Constructor con posición en celdas (se convierte a píxeles)
    public Enemigo(int x, int y) {
        this.x = x * 50;
        this.y = y * 50;
    }

    // Permite ajustar la velocidad del enemigo desde Tablero
    public void setCiclosParaMover(int ciclos) {
        this.limiteCiclos = Math.max(1, ciclos); // mínimo 1 ciclo
    }

    // Movimiento vertical del enemigo
    public void mover() {
        contador++;
        if (contador >= limiteCiclos) {
            y += 1;
            contador = 0;
        }
        if (y > 600) {
            visible = false;
        }
    }

    public void bajar() {
        this.y += 1;
    }

    public void dibujar(Graphics g) {
        if (visible) {
            g.setColor(Color.RED);
            g.fillRect(x, y, ancho, alto);
        }
    }

    // --- Vida y colisiones ---
    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getVida() {
        return vida;
    }

    public void recibirDamage(int cantidad) {
        vida -= cantidad;
        if (vida < 0) vida = 0;
    }

    public boolean estaMuerto() {
        return vida <= 0;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, ancho, alto);
    }

    // Accesores para coordenadas (por si lo necesitamos)
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}