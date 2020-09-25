package Visualizacion;

import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import Dao.IUsuario;
import Dao.UsuariosDBImpl;
import Entity.Usuario;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Formulario de Alta de usuarios
 * @author Alvaro
 * @version 1.1
 * @since 1.0
 */

public class Alta_Usuario extends JFrame {


	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUsuario;
	private JPasswordField txtContraseña;
	private JPasswordField txtComprobacion;
	private JTextField txtNombre;
	private JTextField txtApellidos;
	private JLabel lblApellidos;
	private JLabel lblNombre;
	private JLabel lblComprobarContrasea;
	private JLabel lblContrasea;
	private JLabel lblNombreUsuario;
	private JButton btnRegistrar;
	private JLabel lblLaboratorio;
	private JTextField txtLaboratorio;

	IUsuario almacenUsuarios;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Alta_Usuario frame = new Alta_Usuario();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	
	public Alta_Usuario() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 433, 428);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/Multimedia/icono.png")));
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		almacenUsuarios = new UsuariosDBImpl();
		
		lblNombreUsuario = new JLabel("Nombre Usuario");
		lblNombreUsuario.setBounds(24, 51, 109, 14);
		contentPane.add(lblNombreUsuario);
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(183, 48, 102, 20);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		lblContrasea = new JLabel("Contrase\u00F1a");
		lblContrasea.setBounds(24, 93, 87, 14);
		contentPane.add(lblContrasea);
		
		txtContraseña = new JPasswordField();
		txtContraseña.setBounds(183, 91, 102, 17);
		contentPane.add(txtContraseña);
		txtContraseña.setColumns(10);
		
		lblComprobarContrasea = new JLabel("Comprobar contrase\u00F1a");
		lblComprobarContrasea.setBounds(24, 135, 140, 14);
		contentPane.add(lblComprobarContrasea);
		
		txtComprobacion = new JPasswordField();
		txtComprobacion.setBounds(183, 132, 102, 20);
		contentPane.add(txtComprobacion);
		txtComprobacion.setColumns(10);
		
		lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(24, 177, 46, 14);
		contentPane.add(lblNombre);
		
		lblApellidos = new JLabel("Apellidos");
		lblApellidos.setBounds(24, 219, 46, 14);
		contentPane.add(lblApellidos);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(183, 174, 102, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtApellidos = new JTextField();
		txtApellidos.setBounds(183, 216, 102, 20);
		contentPane.add(txtApellidos);
		txtApellidos.setColumns(10);
		
		btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String contraseña = new String (txtContraseña.getPassword());
				String comprobar = new String (txtComprobacion.getPassword());
				
				if(FrameFormulario.ComprobacionCampos(txtUsuario) && FrameFormulario.ComprobacionCampos(txtContraseña) &&
						FrameFormulario.ComprobacionCampos(txtComprobacion) && FrameFormulario.ComprobacionCampos(txtNombre)
						&& FrameFormulario.ComprobacionCampos(txtApellidos) && FrameFormulario.ComprobacionCampos(txtLaboratorio)) {
				
					if(contraseña.equals(comprobar)) {
						Usuario nuevoUsuario = new Usuario(txtUsuario.getText(), contraseña, txtLaboratorio.getText(), 
								txtNombre.getText(), txtApellidos.getText());
	
						boolean alta = almacenUsuarios.registrarUsuario(nuevoUsuario);
						if(alta==true) {
							JOptionPane.showMessageDialog(null, "Registro Completado");
							dispose();								
						}
					
					}else {
						JOptionPane.showMessageDialog(null, "La contraseña no coincide");
					}
				}
			}
		});
		btnRegistrar.setBounds(161, 316, 89, 23);
		contentPane.add(btnRegistrar);
		
		lblLaboratorio = new JLabel("Laboratorio");
		lblLaboratorio.setBounds(24, 263, 109, 14);
		contentPane.add(lblLaboratorio);
		
		txtLaboratorio = new JTextField();
		txtLaboratorio.setBounds(183, 260, 102, 20);
		contentPane.add(txtLaboratorio);
		txtLaboratorio.setColumns(10);
	}
}
