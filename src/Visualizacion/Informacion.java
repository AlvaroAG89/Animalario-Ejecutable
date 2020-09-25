package Visualizacion;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Toolkit;
import javax.swing.border.LineBorder;
import java.awt.Color;


/**
 * Proporciona informacion de contacto
 * @author Alvaro
 * @version 1.1
 * @since 1.0
 */


public class Informacion extends JFrame {



	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblSiTienesCualquier;
	private JLabel lblNewLabel;



	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Informacion frame = new Informacion();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}



	public Informacion() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Informacion.class.getResource("/Multimedia/icono.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 371, 404);
		//Bloqueo de redimensionar ventana
		this.setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblSiTienesCualquier = new JLabel("<html>\r\nSi tienes cualquier problema puedes ponerte\r\n<br> en contacto con el administrador a traves de: </br>\r\n<br></br>\r\n<br> 1\u00BA - Mediante email al correo yo@yomail.com </br>\r\n<br></br>\r\n<br> 2\u00BA - O a trav\u00E9s del correo interno dirigiendote a Alvaro </br>\r\n</html>");
		lblSiTienesCualquier.setBounds(38, 26, 285, 105);
		contentPane.add(lblSiTienesCualquier);
		
		lblNewLabel = new JLabel("<html>\r\n<center>\r\nMi Animalario est\u00E1 hecho en java, con una\r\n<br>base de datos en H2, cuya conexion est\u00E1\r\ngestionada por hibernate. \r\n<br>\r\n<br>Internamente se genera\r\nun historial de movimientos en un word con la \r\nbiblioteca POIWord\r\n<br><br>\r\nRealizado por \u00C1lvaro Alc\u00E1zar Garrido 2020\r\n<br> V. 1.1\r\n</center>\r\n</html>");
		lblNewLabel.setBorder(new LineBorder(new Color(128, 0, 128)));
		lblNewLabel.setBounds(38, 161, 285, 175);
		contentPane.add(lblNewLabel);
	}
}
