import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Superior extends JPanel {
	
    private JLabel titulo;
    private BufferedImage fondoTop;

    public Superior() {

        this.setBounds(0, 0, 700, 80);
        this.setBackground(Color.BLACK);
        
        try {
        	fondoTop = ImageIO.read(new File("src/stfighter.png"));
     	} catch (IOException e) {
     		e.printStackTrace();
     	}

    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(fondoTop, 0, 0, 700, 80, this);
    }	

}