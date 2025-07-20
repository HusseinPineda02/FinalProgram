import java.awt.Color;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.*;
public class Juego extends JFrame {

	Nave nave;
	Tablero mapa;
	Menu menu;
	Superior top;
	String nombre;
	Sonido musica;
	
	Juego(){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(700,630);
		this.setLayout(null);
		this.setTitle("StarDust Fighter");
		this.setResizable(false);
		this.setBackground(Color.blue);
	
		this.nave = new Nave();
		this.top = new Superior();
		this.menu = new Menu(nave);
		this.musica = new Sonido();
		musica.pistaFondo("src/pokeymid.wav");
		
		this.mapa = new Tablero(nave, menu, musica);
		menu.setTablero(mapa);
		
		this.setIconImage(new ImageIcon("src/sticon.png").getImage());
		
		this.add(top);
		this.add(menu);
		this.add(mapa);

		
		this.setVisible(true);
		
	}

    public static void main(String[] args){
    	
    	Juego game = new Juego();
    	 
    }
}