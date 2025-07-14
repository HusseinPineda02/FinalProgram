public class DisparoEnemigo {
    private int x, y;

    public DisparoEnemigo(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void mover() {
        y += 1; // el disparo baja
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}