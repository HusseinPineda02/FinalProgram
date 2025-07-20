public class Enemigo {
    private int x, y;
    public boolean visible = true;
    
    
    public Enemigo(int x, int y) {
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
    public void bajar() {
    	this.y += 1;
    }
}