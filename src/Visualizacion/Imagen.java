package Visualizacion;

import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * Carga las imagenes de fondo
 * @author Alvaro
 * @version 1.1
 * @since 1.0
 */

public class Imagen extends JPanel {

private static final long serialVersionUID = 1L;
	String ruta;
	int ancho, alto;
	
	public Imagen(String path, int anchura, int altura) {
		
		this.ruta=path;
		this.ancho=anchura;
		this.alto=altura;
	}

	@Override
	public void paintComponent(Graphics g) {
			
		ImageIcon fondo = new ImageIcon(getClass().getResource(ruta));
		g.drawImage(fondo.getImage(), 0, 0, ancho, alto,null );
		super.paintComponent(g);
	}
}
