public class Disparo {
    private int x;
    private int y;

    public Disparo(int x, int y) {      //Constructor para posicionar el disparo en una ubicacion ene especifico
        this.x = x;
        this.y = y;
    }
    public void setX(int x) {           // Esto para cambiar la posicion del disparo si se necesitara
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
    public int getX(){          //Esto nos permite acceder a los disparos desde otras clases
        return x;
    }
    public int getY(){
        return y;
    }
    public void moverDisparo() {        // Moviliza el disparo una casilla hacia arriba
        this.y -= 1;
    }

}
