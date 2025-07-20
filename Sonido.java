import javax.sound.sampled.*;
import java.io.*;

public class Sonido {
	private Clip musica;
	
	public void pistaFondo(String rutaCancion) {
		File archivo = new File(rutaCancion);
		try {
			AudioInputStream cancion = AudioSystem.getAudioInputStream(archivo);
			musica = AudioSystem.getClip();
			musica.open(cancion);
			musica.loop(Clip.LOOP_CONTINUOUSLY);
		}	catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public void detenerMusica() {
		if (musica != null) {
			musica.stop();
		}
	}
}
