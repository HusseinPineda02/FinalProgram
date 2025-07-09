import java.util.ArrayList;
public class Nave {
    private int vida, puntuacion, bajas;
    private int celdaX , celdaY, posicionX, posicionY;
    private char sprite;
    private int direccion;

    public Nave(){
        this.setVida(3);
        this.setBajas(0);
        this.setDireccion(1); // 0 izq 1 arr 2 der 3 aba
        this.setCeldaX(5);
        this.setCeldaY(9);
    }
    public void setVida(int vida){
        this.vida = vida;
    }
    public void setPuntuacion(int puntuacion){
        this.puntuacion = puntuacion;
    }
    public void setCeldaX(int celdaX){
        this.celdaX = celdaX;
        this.setPosicionX(celdaX);
    }
    public void setCeldaY(int celdaY){
        this.celdaY = celdaY;
        this.setPosicionY(celdaY);
    }
    public void setPosicionX(int celdaX){
        this.posicionX = celdaX * 50;
    }
    public void setPosicionY(int celdaY){
        this.posicionY = celdaY * 50+1;
    }
    public void setDireccion(int direccion){
        this.direccion = direccion;
    }
    public void setBajas(int bajas) {
    	this.bajas = bajas;
    }
    public int getVida(){
        return vida;
    }
    public int getPuntuacion(){
        return puntuacion;
    }
    public int getCeldaX(){
        return celdaX;
    }
    public int getCeldaY(){
        return celdaY;
    }
    public int getPosicionX() {
    	return posicionX;
    }
    public int getPosicionY() {
    	return posicionY;
    }
    public int getDireccion(){
        return direccion;
    }
    public char getSprite(){
        return sprite;
    }
    public int getBajas() {
    	return bajas;
    }
    public void mostrarInfo() {
        System.out.printf("Vida: %d\tPuntos: %d\n",vida,puntuacion);
    }
    public void recuperarVida(){
        vida++;
    }
    public void recibirDamage(int cantidad){ 
        vida-=cantidad;
        if (vida<0) vida=0;
    }
    public void destruirEnemigos() {
    	bajas++;
    	this.puntuacion += 50;
    }
    public void naveDisparar(ArrayList<Disparo> disparos){
        Disparo disparo;
        switch(this.direccion){
            case 0: disparo = new Disparo(this.celdaX-1,this.celdaY);       disparos.add(disparo);
            case 1: disparo = new Disparo(this.celdaX,this.celdaY-1);       disparos.add(disparo);
            case 2: disparo = new Disparo(this.celdaX+1,this.celdaY);       disparos.add(disparo);
            case 3: disparo = new Disparo(this.celdaX,this.celdaY+1);       disparos.add(disparo);
        }
    }

    public void moverArriba(){
        if(this.celdaY>0){
            celdaY--;
        }
        this.setPosicionY(celdaY);
    }
    public void moverAbajo(){
        if(this.celdaY<9){
            celdaY++;
        }
        this.setPosicionY(celdaY);
    }
    public void moverIzquierda(){
        if(this.celdaX>0){
            celdaX--;
        }
        this.setPosicionX(celdaX);
    }
    public void moverDerecha(){
        if(this.celdaX<9){
            celdaX++;
        }
        this.setPosicionX(celdaX);
    }
    
}