

public class Disparo {

    private int x;
    private int y;

    public Disparo(int x, int y) {
    	this.x = x;
    	this.y = y;
	}
	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}
    public int getX(){
    	return x;
    }
    public int getY(){
    	return y;
    }
    public void moverDisparo() {
    	this.y -= 1;
    }
} 