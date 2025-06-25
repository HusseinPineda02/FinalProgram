
public class Disparo {
    // Variables para la posicion del disparo 

    private int x;
    private int y;
    private char diseño = '!'; // Este sera el simbolo de nuestro disparo


    public Disparo(int x, int y){ //Nuestro constructor para posicionarlo despues
   
        this.x = x;
        this.y = y;
    }

    public void movimientoArriba(){ //La "bala" subiendo casilla a casilla en la matriz eso siedo mayo a 0 para que no se salga del tablero
        if (y>0) y--;
    }

    //Nuestros getters para acceder mas adelante a nuestros atributos de la posicion y del simbolo 

    public int getX(){return x; }
    public int getY(){return y; }
    public char getDiseño(){return diseño;}
} 