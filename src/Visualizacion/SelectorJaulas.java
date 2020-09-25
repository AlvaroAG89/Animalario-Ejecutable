package Visualizacion;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Entity.Usuario;

import javax.swing.JTabbedPane;
import java.awt.Toolkit;

/**
 * Clase que proporciona todos los racks disponibles para trabajar
 * @author Alvaro
 * @version 1.1
 * @since 1.0
 */

@SuppressWarnings("serial")
public class SelectorJaulas extends JFrame {
	
	private JMenuItem mnCerrarSesion;
	private JMenu mnArchivo;
	private JMenu mnAyuda;
	private JMenuItem mntmContactarConAdministrador;
	private JMenuBar menuBar;
	private JPanel contentPane;
	private JMenuItem mntmMenuPrincipal;


	
	public static void main(String[] args, Usuario miUsuario) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SelectorJaulas frame = new SelectorJaulas(miUsuario);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}



	

	public SelectorJaulas(Usuario miUsuario) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(SelectorJaulas.class.getResource("/Multimedia/icono.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 616, 671);
		//Bloqueo de redimensionar ventana
		this.setResizable(false);
		
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
		
		mntmMenuPrincipal = new JMenuItem("Menu Principal");
		mntmMenuPrincipal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new MenuPrincipal(miUsuario).setVisible(true);
			}
		});
		mnArchivo.add(mntmMenuPrincipal);
		mnArchivo.add(mnCerrarSesion);
		
		mnAyuda = new JMenu("Ayuda");
		menuBar.add(mnAyuda);
		
		mntmContactarConAdministrador = new JMenuItem("Contactar con Administrador");
		mntmContactarConAdministrador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Informacion().setVisible(true);
			}
		});
		mnAyuda.add(mntmContactarConAdministrador);
		
		
		
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(1, 1, 615, 643);;
		contentPane.add(tabbedPane);
		
		JPanel paneRackA = new JPanel();
		tabbedPane.addTab("Rack A", null, paneRackA, null);
		paneRackA.setLayout(null);
		
		JPanel paneRackB = new JPanel();
		tabbedPane.addTab("Rack B", null, paneRackB, null);
		paneRackB.setLayout(null);
		
		//Llamo al PaneRack con la letra A para trabajar con ese rack
		PaneRack rackA = new PaneRack("A", miUsuario);
		rackA.setLocation(0, 0);
		paneRackA.add(rackA);
		
		//Llamo al PaneRack con la letra B para trabajar con ese rack
		PaneRack rackB = new PaneRack("B", miUsuario);
		rackB.setSize(600, 604);
		rackB.setLocation(0, 0);
		paneRackB.add(rackB);
		
		
	}
}
