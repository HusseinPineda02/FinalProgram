public class EnemigoAmarillo {
    private int x, y;
    private int vida = 5;
    public boolean visible = true;
    private int direccion = 0 ; // 0 izq 1 der
    

    public EnemigoAmarillo(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void mover() {
        x -= 1;
        if (x < 0) {
            this.visible = false;
        }
    }
    public void recibirDamage() {
    	this.vida--;
    }
    public int getDireccion() {
	 	return direccion;
	}
	public void setDireccion(int direccion) {
		this.direccion = direccion;
	}
    public int getVida() {
    	return vida;
    }
    public void setVida(int vida) {
    	this.vida = vida;
    }
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