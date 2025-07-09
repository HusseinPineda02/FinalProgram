import java.util.ArrayList;
public class Nave {
    private int vida, puntuacion;
    private int x , y;
    private char sprite;
    private int direccion;

    public Nave(){
        this.setVida(5);
        this.setX(5);
        this.setY(9);
        this.setDireccion(1);
    }
    public void setVida(int vida){
        this.vida = vida;
    }
    public void setPuntuacion(int puntuacion){
        this.puntuacion = puntuacion;
    }
    public void setX(int x){
        this.x = x;
    }
    public void setY(int y){
        this.y = y;
    }
    public void setDireccion(int direccion){
        this.direccion = direccion;
    }
    public void setSprite(short direccion){
        switch(direccion){
            case 0: this.sprite = '\u2190';
                    break;
            case 1: this.sprite = '\u2193';
                    break;
            case 2: this.sprite = '\u2192';
                    break;
            case 3: this.sprite = '\u2193';
                    break;
        }
    }
    public int getVida(){
        return vida;
    }
    public int getPuntuacion(){
        return puntuacion;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public int getDireccion(){
        return direccion;
    }
    public char getSprite(){
        return sprite;
    }
    public void mostrarInfo() {
        System.out.printf("Vida: %d\tPuntos: %d\n",vida,puntuacion);
    }
    public void recuperarVida(){
        vida++;
    }
    public void recibirDaño(int cantidad){ 
        vida-=cantidad;
        if (vida<0) vida=0;
    }
    public void naveDisparar(ArrayList<Disparo> disparos){
        Disparo disparo;
        switch(this.direccion){
            case 0: disparo = new Disparo(this.x-1,this.y);       disparos.add(disparo);
            case 1: disparo = new Disparo(this.x,this.y-1);       disparos.add(disparo);
            case 2: disparo = new Disparo(this.x+1,this.y);       disparos.add(disparo);
            case 3: disparo = new Disparo(this.x,this.y+1);       disparos.add(disparo);
        }
    }
    public void moverArriba(){
        if(this.y>0){
            y--;
        }
    }
    public void moverAbajo(){
        if(this.y<9){
            y++;
        }
    }
    public void moverIzquierda(){
        if(this.x>0){
            x--;
        }
    }
    public void moverDerecha(){
        if(this.x<9){
            x++;
        }
    }
}