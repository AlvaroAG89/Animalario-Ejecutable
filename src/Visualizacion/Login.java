package Visualizacion;

import java.awt.Desktop;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import Dao.IRack;
import Dao.IUsuario;
import Dao.RackDBImpl;
import Dao.UsuariosDBImpl;
import Entity.Rack;
import Entity.Usuario;

import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


/**
 * Formulario de login
 * @author Alvaro
 * @version 1.1
 * @since 1.0
 */


public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	protected Imagen login;
	protected Imagen perfil;
	protected Imagen contraseña;
	private JTextField txtUsuario;
	private JPasswordField txtContraseña;
	private JButton btnIniciarSesion;
	private JButton btnRegistrarse;
	private JLabel lblNewLabel_1;
	private JLabel lbLink;
	private JLabel lblAutorlvaroAlczar;
	
	
	IUsuario almacenUsuarios;
	IRack almacenRack;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public Login() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/Multimedia/icono.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 436, 525);
		//Bloqueo de redimensionar ventana
		this.setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		almacenUsuarios = new UsuariosDBImpl();
		almacenRack = new RackDBImpl();
				
		JPanel panelLogin = new JPanel();
		panelLogin.setBounds(0, 0, 433, 103);
		getContentPane().add(panelLogin);
		panelLogin.setLayout(null);
		
		
		login = new Imagen("/Multimedia/login.png",453,113);		
		login.setBounds(0, 0, 484, 103);
		login.setOpaque(false);	//Requiere la opacidad a false para que se vea el fondo
		login.setLayout(null);


		panelLogin.add(login);	
		cargarDatosBD();
		txtUsuario = new JTextField();
		txtUsuario.setBounds(152, 145, 174, 29);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		txtContraseña = new JPasswordField();
		txtContraseña.setColumns(10);
		txtContraseña.setBounds(152, 185, 174, 29);
		contentPane.add(txtContraseña);
		
		btnIniciarSesion = new JButton("Iniciar Sesion");
		btnIniciarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Usuario miUsuario = new Usuario();
				//necesario para convertir getPassword a String, ya que gettext esta Deprecated
				String contraseña = new String (txtContraseña.getPassword());
				miUsuario= almacenUsuarios.comprobacionLogin(txtUsuario.getText(), contraseña);
				if(miUsuario == null) {
					JOptionPane.showMessageDialog(null, "Usuario no encontrado");
				}else {
					dispose();
					new MenuPrincipal(miUsuario).setVisible(true);
				}
			}
		});
		btnIniciarSesion.setBounds(83, 307, 110, 23);
		contentPane.add(btnIniciarSesion);
		
		btnRegistrarse = new JButton("Registrarse");
		btnRegistrarse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Alta_Usuario().setVisible(true);
			}
		});
		btnRegistrarse.setBounds(217, 307, 125, 23);
		contentPane.add(btnRegistrarse);
		
		
		perfil = new Imagen("/Multimedia/perfil.jpg",35,35);
		perfil.setBounds(100, 145, 42, 29);
		contentPane.add(perfil);
		perfil.setOpaque(false);	//Requiere la opacidad a false para que se vea el fondo
		perfil.setLayout(null);
		
		contraseña = new Imagen("/Multimedia/contraseña.jpg",30,30);
		contraseña.setBounds(100, 185, 35, 35);
		contentPane.add(contraseña);
		contraseña.setOpaque(false);	//Requiere la opacidad a false para que se vea el fondo
		contraseña.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Administrador: Veterinario/veterinario");
		lblNewLabel.setBounds(83, 241, 243, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblInvitadoPepepepe = new JLabel("Invitado: Pepe89/pepe");
		lblInvitadoPepepepe.setBounds(83, 266, 155, 14);
		contentPane.add(lblInvitadoPepepepe);
		
		JLabel lblV = new JLabel("V. 1.1");
		lblV.setBounds(64, 471, 46, 14);
		contentPane.add(lblV);
		
		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(Login.class.getResource("/Multimedia/licencia.PNG")));
		lblNewLabel_1.setBounds(170, 341, 96, 35);
		contentPane.add(lblNewLabel_1);
		
		lbLink = new JLabel("<html>Este obra está bajo una <a rel=\"license\" href=\"http://creativecommons.org/licenses/by-nc-nd/4.0/\">licencia de Creative Commons Reconocimiento-NoComercial-SinObraDerivada 4.0 Internacional</a>.</html>");
		lbLink.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (Desktop.isDesktopSupported()) {
                    try {
				        String osName = System.getProperty("os.name");
				        String urlPath = "http://creativecommons.org/licenses/by-nc-nd/4.0/";
				
				        if (osName.startsWith("Windows"))
				            Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + urlPath);
				        else {
				            String[] browsers = { "firefox", "opera", "konqueror", "epiphany", "mozilla", "netscape" };
				            String browser = null;
				            for (int count = 0; count < browsers.length && browser == null; count++)
				                if (Runtime.getRuntime().exec(new String[] { "which", browsers[count] }).waitFor() == 0)
				                    browser = browsers[count];
				            Runtime.getRuntime().exec(new String[] { browser, urlPath });
				        }
				    }
				    catch (Exception ex) {
				        JOptionPane.showMessageDialog(null, "No se pudo abrir el navegador");
				    }
				}
			}
		});
		
		lbLink.setBounds(71, 385, 331, 54);
		contentPane.add(lbLink);
		
		lblAutorlvaroAlczar = new JLabel("Autor: \u00C1lvaro Alc\u00E1zar Garrido");
		lblAutorlvaroAlczar.setBounds(141, 450, 174, 14);
		contentPane.add(lblAutorlvaroAlczar);
	}
	
	public void cargarDatosBD() {
		boolean cargado = false;
		cargado = almacenUsuarios.busquedaUsuario("Veterinario");
		
		if(cargado == false) {
			Usuario usuario1 = new Usuario("Veterinario", "veterinario", "Veterinario", "Montse", "Sanchez");
			Usuario usuario2 = new Usuario("Alvaro89", "alvaro", "Medicina Regenerativa", "Alvaro", "Alcazar");
			Usuario usuario3 = new Usuario("Alvaro", "alvaro", "Oncologia", "Alvaro", "df");
			Usuario usuario4 = new Usuario("Pepe89", "pepe", "reuma", "Pepe", "Rodriguez");
			almacenUsuarios.registrarUsuario(usuario1);
			almacenUsuarios.registrarUsuario(usuario2);
			almacenUsuarios.registrarUsuario(usuario3);
			almacenUsuarios.registrarUsuario(usuario4);
			
			Rack rack1 = new Rack("Grande", 20, 20);
			Rack rack2 = new Rack("Pequeño", 20, 20);
			Rack rack3 = new Rack("Ventilado", 20, 20);
			
			almacenRack.nuevoRack(rack1);
			almacenRack.nuevoRack(rack2);
			almacenRack.nuevoRack(rack3);
		}
	}
}
