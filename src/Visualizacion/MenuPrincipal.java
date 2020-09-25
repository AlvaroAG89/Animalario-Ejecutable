package Visualizacion;

import java.awt.EventQueue;

import javax.swing.JFrame;


import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.SwingConstants;

import Entity.Usuario;

import java.awt.Toolkit;

/**
 * Menu Principal del usuario
 * @author Alvaro
 * @version 1.1
 * @since 1.0
 */

public class MenuPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JMenuItem mnCerrarSesion;
	private JMenu mnArchivo;
	private JMenu mnGestion;
	private JMenuItem mntmReservas;
	private JMenuItem mntmAvisos;
	private JMenu mnAyuda;
	private JMenuItem mntmContactarConAdministrador;
	private JMenuBar menuBar;
	protected Imagen fondo;	

	
	public static void main(String[] args, Usuario miUsuario) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuPrincipal frame = new MenuPrincipal(miUsuario);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public MenuPrincipal(Usuario miUsuario) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(MenuPrincipal.class.getResource("/Multimedia/icono.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 469, 384);
		//Bloqueo de redimensionar ventana
		this.setResizable(false);
				
		fondo = new Imagen("/Multimedia/MenuPrincipal.jpg",480,330);		
		fondo.setBounds(0, 0, 489, 358);
		fondo.setOpaque(false);	//Requiere la opacidad a false para que se vea el fondo
		fondo.setLayout(null);
		fondo.setVisible(true);
		getContentPane().setLayout(null);
		getContentPane().add(fondo);	
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mnArchivo = new JMenu("Archivo");
		menuBar.add(mnArchivo);
		
		mnCerrarSesion = new JMenuItem("Cerrar Sesi\u00F3n");
		mnCerrarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				new Login();
				Login.main(null);
			}
		});
		mnArchivo.add(mnCerrarSesion);
		
		mnGestion = new JMenu("Gestion");
		menuBar.add(mnGestion);
		
		mntmReservas = new JMenuItem("Reservas");
		mntmReservas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new SelectorJaulas(miUsuario).setVisible(true); 
			}
		});
		mnGestion.add(mntmReservas);
		
		mntmAvisos = new JMenuItem("Avisos");
		mntmAvisos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new ListaAvisos(miUsuario).setVisible(true);
			}
		});
		mnGestion.add(mntmAvisos);
		
		mnAyuda = new JMenu("Ayuda");
		menuBar.add(mnAyuda);
		
		mntmContactarConAdministrador = new JMenuItem("Contactar con Administrador");
		mntmContactarConAdministrador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Informacion().setVisible(true);
			}
		});
		mnAyuda.add(mntmContactarConAdministrador);
		
		JLabel lbTitulo = new JLabel("Bienvenido " + miUsuario.getNombre());
		lbTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lbTitulo.setForeground(new Color(0, 139, 139));
		lbTitulo.setFont(new Font("Tahoma", Font.BOLD, 24));
		lbTitulo.setBounds(117, 58, 268, 39);
		fondo.add(lbTitulo);
		
		JButton btnReservas = new JButton("Reservas");
		btnReservas.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnReservas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new SelectorJaulas(miUsuario).setVisible(true);
			}
		});
		btnReservas.setBounds(94, 181, 100, 39);
		fondo.add(btnReservas);
		
		JButton btnAvisos = new JButton("Avisos");
		btnAvisos.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnAvisos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new ListaAvisos(miUsuario).setVisible(true);
			}
		});
		btnAvisos.setBounds(252, 181, 100, 39);
		fondo.add(btnAvisos);
	}
}
