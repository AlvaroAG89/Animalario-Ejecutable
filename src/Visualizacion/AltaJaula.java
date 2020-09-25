package Visualizacion;

import java.awt.EventQueue;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import Dao.IJaula;
import Dao.JaulaDBImpl;
import Entity.Jaula;
import Entity.Usuario;

import java.awt.Toolkit;

/**
 * Formulario de altas de nuevas jaulas
 * @author Alvaro
 * @version 1.1
 * @since 1.0
 */

@SuppressWarnings("serial")
public class AltaJaula extends FrameFormulario {
	protected int indice = 0;
	protected Jaula aJaula[];
	protected Imagen fondo;	

	protected static String miJaula;
	private JLabel lbPagina;
	private JButton btnInsertar;
	private JButton btnVolver;
	
	
	IJaula almacenJaula;
	
	public static void main(String[] args, int numPaginas, Usuario miUsuario) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AltaJaula frame = new AltaJaula(paginas, miUsuario);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}



	protected AltaJaula(ArrayList<String> paginas, Usuario miUsuario) {

		super();
		setIconImage(Toolkit.getDefaultToolkit().getImage(AltaJaula.class.getResource("/Multimedia/icono.png")));
		//Bloqueo de redimensionar ventana
		this.setResizable(false);
		pFormulario.setOpaque(false);
		fondo = new Imagen("/Multimedia/alta_jaula.png",644,426);		
		fondo.setBounds(1, 0, 644, 426);
		fondo.setOpaque(false);	//Requiere la opacidad a false para que se vea el fondo
		fondo.setLayout(null);
		fondo.setVisible(true);
		getContentPane().add(fondo);		
		
		almacenJaula = new JaulaDBImpl();
		
		pFormulario.setBounds(15, 11, 582, 307);
		//creamos un array de tipo Jaula cuya longitud sea la de las jaulas seleccionadas
		txtJaula.setEditable(false);
		if(!miUsuario.getUsuario().equals("Veterinario")) {
			txtLaboratorio.setText(miUsuario.getLaboratorio());
			txtResponsable.setText(miUsuario.getNombre());
			txtLaboratorio.setEditable(false);
			txtResponsable.setEditable(false);
		}

		txtJaula.setText(paginas.get(0));
		aJaula = new Jaula[paginas.size()];
		//inicializamos todos los campos a nulos para que no haya problemas con los int al pasar pagina
		for(int i=0; i<paginas.size(); i++) {
			aJaula[i] = new Jaula("", "", "", "", "", "", "", 0, 0, null, "", "");
			
		}
		btnInsertar = new JButton("Insertar Todos");
		btnInsertar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(ComprobacionCampos(txtResponsable)&& ComprobacionCampos(txtLaboratorio) && 
						ComprobacionCampos(txtHembra) && ComprobacionCampos(txtMacho) && 
						ComprobacionCampos(txtProcedimiento)&& ComprobacionFechas(txtFechaNac) && ComprobacionCampos(txtCepa)) {

				//si solo indicamos una pagina, que no se pueda avanzar o retroceder
				if(paginas.size()==1) {
					aJaula[indice] = new Jaula(txtJaula.getText(), txtNombre.getText(), 
							txtLaboratorio.getText(), txtResponsable.getText(), 
							String.valueOf(cbEspecie.getSelectedItem()), txtCepa.getText(), 
							String.valueOf(cbRack.getSelectedItem()), Integer.parseInt(txtHembra.getText()),
							Integer.parseInt(txtMacho.getText()), txtFechaNac.getDate(), 
							txtProcedimiento.getText(), textArea.getText());
				//si nos encontramos en la ultima pagina que no pueda avanzar mas
				}else if(lbPagina.getText().equals(String.valueOf(paginas.size()))) {
					aJaula[paginas.size()-1] = new Jaula(txtJaula.getText(), txtNombre.getText(), 
							txtLaboratorio.getText(), txtResponsable.getText(), 
							String.valueOf(cbEspecie.getSelectedItem()), txtCepa.getText(), 
							String.valueOf(cbRack.getSelectedItem()), Integer.parseInt(txtHembra.getText()),
							Integer.parseInt(txtMacho.getText()), txtFechaNac.getDate(), 
							txtProcedimiento.getText(), textArea.getText());
				}else {
					JOptionPane.showMessageDialog(null, "Rellena todos los campos o ve a la ultima hoja para insertar");
				}
					//Realizamos la insercion pagina por pagina y lo almacenamos en el historico
					int op = JOptionPane.showConfirmDialog(null, "¿Desea Insertar los datos?");
					
					//recorremos todo el array para la insercion
					if(op==0) {
						
						for(int i = 0; i<paginas.size();i++) {
							almacenJaula.insertarJaula(aJaula[i]);
						}
						dispose();
						new SelectorJaulas(miUsuario).setVisible(true);
					}
					
				}else {
					JOptionPane.showMessageDialog(null, "Faltan Campos por rellenar");
				}
			}
		});
		getContentPane().setLayout(null);
		btnInsertar.setBounds(158, 376, 127, 23);
		fondo.add(btnInsertar);
		
		JButton btnAnterior = new JButton("<");
		btnAnterior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(ComprobacionCampos(txtResponsable)&& ComprobacionCampos(txtLaboratorio) && 
						ComprobacionCampos(txtHembra) && ComprobacionCampos(txtMacho) && 
						ComprobacionCampos(txtProcedimiento) && ComprobacionFechas(txtFechaNac)) {
					if(Integer.parseInt(lbPagina.getText()) == 1) {
						JOptionPane.showMessageDialog(null, "Ya estas en la primera pagina");
					}else {
						//si estas en la ultima pagina, guarda lo almacenado en ella al array antes de pasar a otra pagina
						if(lbPagina.getText().equals(String.valueOf(paginas.size()))) {
										
							aJaula[indice] = new Jaula(txtJaula.getText(), txtNombre.getText(), txtLaboratorio.getText(), txtResponsable.getText(), 
									String.valueOf(cbEspecie.getSelectedItem()), txtCepa.getText(), String.valueOf(cbRack.getSelectedItem()),
									Integer.parseInt(txtHembra.getText()), Integer.parseInt(txtMacho.getText()), 
									txtFechaNac.getDate(), txtProcedimiento.getText(), txtProcedimiento.getText());
						}
						//toman los campos los valores introducidos en esa pagina antes
						indice--;
						lbPagina.setText(String.valueOf(indice+1));
						txtJaula.setText(paginas.get(indice));
						txtLaboratorio.setText(miUsuario.getLaboratorio());
						txtResponsable.setText(miUsuario.getNombre());
						cbEspecie.setSelectedItem(aJaula[indice].getEspecie());
						txtCepa.setText(aJaula[indice].getCepa());
						cbRack.setSelectedItem(aJaula[indice].getTipo_jaula());
						txtNombre.setText(String.valueOf(aJaula[indice].getNombres()));
						txtHembra.setText(String.valueOf(aJaula[indice].getNumRatasHembras()));
						txtMacho.setText(String.valueOf(aJaula[indice].getNumRatasMacho()));
						txtFechaNac.setDate(aJaula[indice].getFechaNacimiento());
						txtProcedimiento.setText(aJaula[indice].getProcedimiento());
						textArea.setText(aJaula[indice].getObservaciones());
					}
				}else {
					JOptionPane.showMessageDialog(null, "Falta un campo por rellenar");
				}
				
			}
		});
		btnAnterior.setBounds(44, 347, 41, 17);
		fondo.add(btnAnterior);
		
		JButton btnSiguiente = new JButton(">");
		btnSiguiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/**
				 * Comprobamos campos
				 */
					
					if((Integer.parseInt(lbPagina.getText())) == paginas.size()) {
						JOptionPane.showMessageDialog(null, "Ya estas en la ultima pagina");
					}else {
						/**
						 * almacenamos lo que rellenamos en la pagina en el array antes de pasar a la siguiente
						 * y vaciamos las casillas
						 */
						 
					}
				}
		});
		btnSiguiente.setBounds(534, 347, 41, 17);
		fondo.add(btnSiguiente);
		
		lbPagina = new JLabel(String.valueOf(indice+1));
		lbPagina.setBounds(462, 348, 46, 14);
		fondo.add(lbPagina);
		
		btnVolver = new JButton("Volver");
		btnVolver.setBounds(331, 376, 127, 23);
		fondo.add(btnVolver);
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int confirmar = JOptionPane.showConfirmDialog(null, "Si sale se cancelará lo que no este insertado, ¿Desea Continuar?");
				if(confirmar == 0) {
					dispose();
					new SelectorJaulas(miUsuario).setVisible(true);;
				}
			}
		});
	}
}
