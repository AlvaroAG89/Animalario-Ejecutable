package Visualizacion;

import java.awt.EventQueue;

import javax.swing.JFrame;

import Entity.Aviso;
import Entity.Usuario;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

/**
 * Formulario de envio de correo
 * @author Alvaro
 * @version 1.1
 * @since 1.0
 */

public class LeerCorreo extends FrameCorreo {


	private static final long serialVersionUID = 1L;
	private JButton btnVolver;


	public static void main(String[] args, Usuario miUsuario, Aviso miAviso) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LeerCorreo frame = new LeerCorreo(miUsuario, miAviso);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public LeerCorreo(Usuario miUsuario, Aviso miAviso) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(LeerCorreo.class.getResource("/Multimedia/icono.png")));
		setBounds(100, 100, 521, 452);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Bloqueo de redimensionar ventana
		this.setResizable(false);
		
		btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				new ListaAvisos(miUsuario).setVisible(true);
			}
		});
		btnVolver.setBounds(193, 350, 89, 23);
		getContentPane().add(btnVolver);
		txtAsunto.setEditable(false);
		txtEmisor.setEditable(false);
		txtReceptor.setEditable(false);
		textArea.setEditable(false);
		
		txtReceptor.setText(miAviso.getDestinatario());
		txtEmisor.setText(miAviso.getRemitente());
		txtAsunto.setText(miAviso.getAsunto());
		textArea.setText(miAviso.getMensaje());
	}
}
