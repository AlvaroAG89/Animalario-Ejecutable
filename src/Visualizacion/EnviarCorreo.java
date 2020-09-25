package Visualizacion;

import java.awt.EventQueue;

import javax.swing.JFrame;


import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JScrollPane;

import Dao.AvisoDBImpl;
import Dao.IAviso;
import Entity.Aviso;
import Entity.Usuario;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.Toolkit;

/**
 * Formulario de envio de avisos
 * @author Alvaro
 * @version 1.1
 * @since 1.0
 */

public class EnviarCorreo extends FrameCorreo {

	
	private static final long serialVersionUID = 1L;
	private JList<String> listUsers;
	JScrollPane sp1;
	private JButton btnEnviar;
	private JButton btnVolver;
	
	IAviso almacenAvisos;
	
	public static void main(String[] args, Usuario miUsuario) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EnviarCorreo frame = new EnviarCorreo(miUsuario);
					frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public EnviarCorreo(Usuario miUsuario) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(EnviarCorreo.class.getResource("/Multimedia/icono.png")));
		
		setBounds(100, 100, 518, 432);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Bloqueo de redimensionar ventana
		this.setResizable(false);
		
		almacenAvisos = new AvisoDBImpl();
		
		//Controlamos los clicks en la lista
		listUsers = new JList<String>();
		listUsers.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String seleccionado;
				//capturamos cada usuario que pinchamos
				seleccionado = listUsers.getSelectedValue();
				if(txtReceptor.getText().equals("")) {
					txtReceptor.setText(seleccionado + ",");
				}else {
					txtReceptor.setText(txtReceptor.getText() + seleccionado + ",");
				}
			}
		});
		
		sp1 = new JScrollPane(listUsers);
		
		sp1.setBounds(340,55,140,25);
		
		//Cargamos los usuarios registrados en el JList
		List<String> usuarios = null;
		usuarios = almacenAvisos.cargaContactos();
		DefaultListModel<String> modelo = new DefaultListModel<String>();
		
		for(int i=0; i<usuarios.size(); i++) {
			modelo.addElement(usuarios.get(i));
		}
		listUsers.setModel(modelo);
		
		listUsers.addMouseMotionListener(new MouseMotionAdapter() {
			//Este metodo funciona mejor que el mouseEntered
			@Override
			public void mouseMoved(MouseEvent e) {
				sp1.setBounds(340,55,140,90);
				listUsers.setModel(modelo);
				//ampliamos la lista tambien, o solo se extendera el JScroll
				listUsers.setFixedCellWidth(400);
				listUsers.setVisible(true);

			}
		});
		
		
		//Controlamos la salida con el JScroll ya que si fijamos el Jlist, el desplegable se ira si vamos a la
		//barra del scroll
		sp1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				sp1.setBounds(340,55,140,20);
				listUsers.setModel(modelo);
				//hay que devolver los valores que tenia por defecto aqui tambien, no solo en
				//el setBounds o no se acopla bien al JScroll
				listUsers.setFixedCellWidth(20);
			}
		});
		getContentPane().add(sp1);
		
		txtEmisor.setBounds(72, 60, 135, 20);
		txtEmisor.setText(miUsuario.getUsuario());
		txtEmisor.setEditable(false);
		
		
		//----Boton Enviar----//
		btnEnviar = new JButton("Enviar");
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ArrayList<String> listaDefinitiva = new ArrayList<String>();
				
				//Preparamos una collection de todos los nombres escritos en el JTextfield de Destinatario
				//obviando los repetidos
				listaDefinitiva = prepararEnvio();
				
				for(int i=0; i<listaDefinitiva.size(); i++) {
					Aviso nuevoAviso = new Aviso(listaDefinitiva.get(i), txtEmisor.getText(), txtAsunto.getText(),
							textArea.getText());
					almacenAvisos.insertar(nuevoAviso);
				}

				
				dispose();
				new ListaAvisos(miUsuario).setVisible(true);
			}
		});
		
		btnEnviar.setBounds(118, 342, 89, 23);
		getContentPane().add(btnEnviar);
		
		
		//---- Boton Volver ----///
		btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new ListaAvisos(miUsuario).setVisible(true);
			}
		});
		btnVolver.setBounds(267, 342, 89, 23);
		getContentPane().add(btnVolver);

	}
	
	
	public ArrayList<String>  prepararEnvio() {
		//primero separamos los contactos que hemos escrito
		StringTokenizer a = new StringTokenizer(txtReceptor.getText(),",");
		ArrayList<String> listaSeleccionados = new ArrayList<String>();
		while(a.hasMoreTokens()) {
			listaSeleccionados.add(a.nextToken());
		}
		
		//Una vez ya seleccionados, debemos asegurarnos que no haya duplicados
		ArrayList<String> listaDefinitiva = new ArrayList<String>();
		
		for(int i=0; i<listaSeleccionados.size(); i++) {
			if(!listaDefinitiva.contains(listaSeleccionados.get(i))) {
				listaDefinitiva.add(listaSeleccionados.get(i));
			}
		}
		
		//La lista queda sin duplicados
		return listaDefinitiva;
	}
	

}
