import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class MenuPrincipal extends JFrame{
	
	private Image fondo;
	private Juego juego;	
	Sonido musica;
	
	public MenuPrincipal() {
		
	    this.setTitle("StarDust Fighter - Inicio");
	    this.setSize(700, 700);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setLocationRelativeTo(null);
	    this.setResizable(false);
	    this.setLayout(null);
	    
		
		Sonido musica = new Sonido();
		musica.pistaFondo("src/existini.wav");
		
	        
	    JLabel fondo = new JLabel(new ImageIcon(new ImageIcon("src/stmenu.png").getImage().getScaledInstance(700, 700, Image.SCALE_SMOOTH)));
		fondo.setBounds(0,0,700, 700);
		this.setContentPane(fondo);
		
		JButton iniciar = new JButton();
		iniciar.setBounds(390,301,225,85);
		botonInvisible(iniciar);
		iniciar.addActionListener(e->{
			new Juego();
			this.dispose();
			musica.detenerMusica();
		});
		this.add(iniciar);
		
		JButton puntuaciones = new JButton();
		puntuaciones.setBounds(390,420,225,85);
		botonInvisible(puntuaciones);
		puntuaciones.addActionListener(e->{
			Puntuaciones puntaje = new Puntuaciones();
			puntaje.mejoresPuntuaciones();
		});
		this.add(puntuaciones);
		
		JButton salir = new JButton();
		salir.setBounds(390,539,225,85);	
		botonInvisible(salir);
		salir.addActionListener(e->{
			System.exit(0);
		});
		this.add(salir);
		this.setVisible(true);
	}		
	
	public void botonInvisible(JButton boton) {
		boton.setOpaque(false);
		boton.setContentAreaFilled(false);
		boton.setBorderPainted(false);
		boton.setFocusPainted(false);
	}
	
	protected void paintComponent(Graphics g) {
	    super.paintComponents(g);
	    Image fondo = new ImageIcon("src/stmenu.png").getImage();
	    g.drawImage(fondo, 0, 0, 700, 700, this);
	}
}	