import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.*;
import java.util.ArrayList;
public class Juego extends JFrame {
	//ArrayList<Enemigo> enemigos = new ArrayList<Enemigo>();
	//Nave nave = new Nave();
	
	Juego(){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(700,630);
		this.setLayout(null);
		this.setTitle("SHIP BATTLE BETA");
		this.setResizable(false);
		this.setBackground(Color.blue);
		
		JLabel titulo = new JLabel ("SHIP BATTLE BETA");
		titulo.setBounds(300, 20, 200, 50);
		
		Menu datos = new Menu();

		Tablero mapa = new Tablero();
		
		this.add(datos);
		this.add(mapa);
		this.add(titulo);
		
		this.setVisible(true);
	}
	
    public static void main(String[] args){
    	
    	Juego game = new Juego();
 
    }
}