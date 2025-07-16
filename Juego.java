import java.awt.Color;
import javax.swing.*;
public class Juego extends JFrame {
	//ArrayList<Enemigo> enemigos = new ArrayList<Enemigo>();
	Nave nave = new Nave();
	
	Juego(){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(700,630);
		this.setLayout(null);
		this.setTitle("SHIP BATTLE BETA");
		this.setResizable(false);
		this.setBackground(Color.blue);
		
		JLabel titulo = new JLabel ("SHIP BATTLE BETA");
		titulo.setBounds(300, 20, 200, 50);
		
		Menu datos = new Menu(nave);
		Tablero mapa = new Tablero(nave, datos);
		Sonido musica = new Sonido();
		
		
		this.add(datos);
		this.add(mapa);
		this.add(titulo);
		
		this.setVisible(true);
		musica.pistaFondo("src/pokeymid.wav");
	}
	
    public static void main(String[] args){
    	
    	Juego game = new Juego();
 
    }
}