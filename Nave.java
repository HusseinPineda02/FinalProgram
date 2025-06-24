public class Nave {
    private int vida;
    private int x , y;
    private char sprite;
    private short direccion;

    public void setVida(int vida){
        this.vida = vida;
    }
    public void setX(int x){
        this.x = x;
    }
    public void setY(int y){
        this.y = y;
    }
    public void setDireccion(short direccion){
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
    public int getX(){
        return x;
    }
    public int getY(){
        return Y;
    }
    public short getDireccion(){
        return direccion;
    }
    public char getSpriet(){
        return sprite;
    }
}