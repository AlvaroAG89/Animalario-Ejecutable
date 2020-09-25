package Visualizacion;

import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Dao.IJaula;
import Dao.JaulaDBImpl;
import Entity.Jaula;
import Entity.Usuario;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

/**
 * Formulario de Modificacion de las jaulas ya registradas
 * @author Alvaro
 * @version 1.1
 * @since 1.0
 */

public class ModificarJaula extends FrameFormulario {


	private static final long serialVersionUID = 1L;
	private JButton btnModificar;
	private JButton btnVolver;
	protected static Imagen fondo;	
	
	IJaula almacenJaulas;
	
	public static void main(String[] args, Jaula miJaula, Usuario miUsuario) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ModificarJaula frame = new ModificarJaula(miJaula, miUsuario);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}




	//Necesitamos 2 constructores. Accedo a ello desde dos sitios. 
	
	//El primero desde la clase Listado de jaulas. Almaceno todos los datos en un objeto y lo mando aqui
	
	//El segundo lo necesito para cuando accedo desde el selector de jaulas, ya que el unico dato que tengo
	//ahi es el nombre de la jaula, y ya dentro del contructor, hago una llamada a la base de datos para
	//que me rescate el resto de los datos que no tengo.
	



	public ModificarJaula(Jaula miJaula, Usuario miUsuario) {
		super();
		setIconImage(Toolkit.getDefaultToolkit().getImage(ModificarJaula.class.getResource("/Multimedia/icono.png")));
		//Bloqueo de redimensionar ventana
		this.setResizable(false);
		
		almacenJaulas = new JaulaDBImpl();
		
		cargaFondo();
		getContentPane().add(fondo);	
		pFormulario.setBounds(10, 11, 610, 307);
		setBounds(100, 100, 642, 460);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/**
				 * Comprobamos que todo esta rellenado y llamamos a modificar
				 */
				}
				
		});
		getContentPane().setLayout(null);
		btnModificar.setBounds(171, 343, 89, 23);
		fondo.add(btnModificar);
		
		btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int confirmar = JOptionPane.showConfirmDialog(null, "Si sale se cancelará lo que no este insertado, ¿Desea Continuar?");
				if(confirmar == 0) {
					dispose();
					new SelectorJaulas(miUsuario).setVisible(true);
				}
			}
		});
		
		btnVolver.setBounds(318, 343, 89, 23);
		fondo.add(btnVolver);
		
		txtJaula.setText(miJaula.getJaula());
		txtLaboratorio.setText(miJaula.getLaboratorio());
		txtResponsable.setText(miJaula.getResponsable());
		txtNombre.setText(miJaula.getNombres());
		cbEspecie.setSelectedItem(miJaula.getEspecie());
		txtCepa.setText(miJaula.getCepa());
		cbRack.setSelectedItem(miJaula.getTipo_jaula());
		txtHembra.setText(String.valueOf(miJaula.getNumRatasHembras()));
		txtMacho.setText(String.valueOf(miJaula.getNumRatasMacho()));
		txtFechaNac.setDate(miJaula.getFechaNacimiento());
		txtProcedimiento.setText(miJaula.getProcedimiento());
		textArea.setText(miJaula.getObservaciones());
		
		//Si no soy el Administrador no podre hacer inserciones a nombre de otras personas o laboratorios
		txtJaula.setEditable(false);
		if(!miUsuario.getUsuario().equals("Veterinario")) {
			txtLaboratorio.setText(miUsuario.getLaboratorio());
			txtResponsable.setText(miUsuario.getNombre());
			txtLaboratorio.setEditable(false);
			txtResponsable.setEditable(false);
		}
		
	}
	

	public ModificarJaula(ArrayList<String> modificar, Usuario miUsuario) {
		super();
		setIconImage(Toolkit.getDefaultToolkit().getImage(ModificarJaula.class.getResource("/Multimedia/icono.png")));
		//Bloqueo de redimensionar ventana
		this.setResizable(false);
		almacenJaulas = new JaulaDBImpl();
		
		cargaFondo();
		getContentPane().add(fondo);
		pFormulario.setBounds(10, 11, 610, 307);
		setBounds(100, 100, 642, 460);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Jaula miJaula = almacenJaulas.BusquedaCompleta(modificar.get(0));
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				/**
				 * Comprobamos que todo esta rellenado y llamamos a modificar
				 */
				
				
				
			}
		});
		getContentPane().setLayout(null);
		btnModificar.setBounds(176, 343, 89, 23);
		fondo.add(btnModificar);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int confirmar = JOptionPane.showConfirmDialog(null, "Si sale se cancelará lo que no este insertado, ¿Desea Continuar?");
				if(confirmar == 0) {
					dispose();
					new SelectorJaulas(miUsuario).setVisible(true);
				}
			}
		});
		
		btnVolver.setBounds(341, 343, 89, 23);
		fondo.add(btnVolver);
		
		txtJaula.setText(miJaula.getJaula());
		txtLaboratorio.setText(miJaula.getLaboratorio());
		txtResponsable.setText(miJaula.getResponsable());
		txtNombre.setText(miJaula.getNombres());
		cbEspecie.setSelectedItem(miJaula.getEspecie());
		txtCepa.setText(miJaula.getCepa());
		cbRack.setSelectedItem(miJaula.getTipo_jaula());
		txtHembra.setText(String.valueOf(miJaula.getNumRatasHembras()));
		txtMacho.setText(String.valueOf(miJaula.getNumRatasMacho()));
		txtFechaNac.setDate(miJaula.getFechaNacimiento());
		txtProcedimiento.setText(miJaula.getProcedimiento());
		textArea.setText(miJaula.getObservaciones());

		//Si no soy el Administrador no podre hacer inserciones a nombre de otras personas o laboratorios
		txtJaula.setEditable(false);
		if(!miUsuario.getUsuario().equals("Veterinario")) {
			txtLaboratorio.setText(miUsuario.getLaboratorio());
			txtResponsable.setText(miUsuario.getNombre());
			txtLaboratorio.setEditable(false);
			txtResponsable.setEditable(false);
		}
	
	}
	
	public static void cargaFondo() {
		pFormulario.setOpaque(false);
		fondo = new Imagen("/Multimedia/modificarJaula2.png",644,446);		
		fondo.setBounds(0, 0, 644, 466);
		fondo.setOpaque(false);	//Requiere la opacidad a false para que se vea el fondo
		fondo.setLayout(null);
		fondo.setVisible(true);
		
	}
}
