import java.awt.Color;
import javax.swing.*;

public class Juego extends JFrame {
	
	Juego(){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(700,630);
		this.setLayout(null);
		this.setTitle("SHIP BATTLE BETA");
		this.setResizable(false);
		this.setBackground(Color.blue);
		
		JLabel titulo = new JLabel ("SHIP BATTLE BETA");
		titulo.setBounds(300, 20, 200, 50);
				
		Tablero mapa = new Tablero();
		
		this.add(mapa);
		this.add(titulo);
		this.setVisible(true);
		mapa.requestFocusInWindow();
	}
	
    public static void main(String[] args){
    	
    	Juego game = new Juego();

    	
 
    }
}