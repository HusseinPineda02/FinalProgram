import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Puntuaciones {
	private static final String ARCHIVO_PUNTUACIONES = "src/puntajes.txt";
	
	public static class Puntaje {
		String nombre;
		int puntuacion;
		
		public Puntaje(String nombre, int puntuacion) {
			this.nombre = nombre;
			this.puntuacion = puntuacion;
		}
		@Override
		public String toString() {
			return nombre + ": " + puntuacion;
		}
	}
	
	public static void guardarPuntuacion(String nombre, int puntuacion) {
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO_PUNTUACIONES,true))){
			bw.write(nombre+","+puntuacion);
			bw.newLine();
		} catch(IOException e) {
			JOptionPane.showMessageDialog(null, "Error al mostrar puntuaciones.");
		}
	}
	
	public static ArrayList<Puntaje> leerPuntuaciones(){
		ArrayList<Puntaje> puntuaciones = new ArrayList<>();
		
		try(BufferedReader br = new BufferedReader(new FileReader(ARCHIVO_PUNTUACIONES))){
			String linea;
			
			while((linea = br.readLine())!=null) {
				String[] partesPunt = linea.split(",");
				
				if(partesPunt.length==2) {
					
					String nombre = partesPunt[0].trim();
					int puntuacion = Integer.parseInt(partesPunt[1].trim());
					puntuaciones.add(new Puntaje(nombre,puntuacion));
				}
			}			
		} catch(IOException e) {
			JOptionPane.showMessageDialog(null, "Error guardando puntuacion");
		}
		return puntuaciones;
	}
	
	public static void mejoresPuntuaciones() {
		ArrayList<Puntaje> puntuaciones = leerPuntuaciones();
		puntuaciones.sort((a,b) -> b.puntuacion-a.puntuacion);
		
		StringBuilder sb = new StringBuilder("MEJORES PUNTAJES\n\n");
		for(int i=0 ; i<Math.min(5, puntuaciones.size());i++) {
			sb.append(i+1).append(". ").append(puntuaciones.get(i).toString()).append("\n");
		}
		
		if(puntuaciones.isEmpty()) {
			sb.append("AUN NO HAY PUNTAJES");
		}
		JOptionPane.showMessageDialog(null, sb.toString(), "MEJORES PUNTAJES", JOptionPane.INFORMATION_MESSAGE);
	}
}