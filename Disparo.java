public class Disparo {

    private int x;
    private int y;

    public Disparo(int x, int y) {
    	this.x = x * 50 + 20;
    	this.y = y * 50 + 20;
	}
	public void setX(int x) {
		this.x = x * 50 + 20;
	}
	public void setY(int y) {
		this.y = y * 50 + 20;
	}
    public int getX(){
    	return x;
    }
    public int getY(){
    	return y;
    }

} 