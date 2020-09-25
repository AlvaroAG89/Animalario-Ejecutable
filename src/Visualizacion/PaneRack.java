package Visualizacion;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.border.EmptyBorder;
import org.jdesktop.swingx.JXButton;

import Dao.IJaula;
import Dao.JaulaDBImpl;
import Entity.Jaula;
import Entity.Usuario;

import javax.swing.JButton;

/**
 * Plantilla para implementar 1 o varios racks con sus respectivas jaulas en el SelectorJaulas
 * @author Alvaro
 * @version 1.1
 * @since 1.0
 */

@SuppressWarnings("serial")
public class PaneRack extends JPanel {
	protected Imagen bg1;	
	protected JPanel contentPane;
	protected JToggleButton jt01;
	protected JToggleButton jt02;
	protected JToggleButton jt03;
	protected JToggleButton jt04;
	protected JToggleButton jt05;
	protected JToggleButton jt06;
	protected JToggleButton jt07;
	protected JToggleButton jt08;
	protected JToggleButton jt09;
	protected JToggleButton jt10;
	protected JToggleButton jt11;
	protected JToggleButton jt12;
	protected JToggleButton jt13;
	protected JToggleButton jt14;
	protected JToggleButton jt15;
	protected JToggleButton jt16;
	protected JToggleButton jt17;
	protected JToggleButton jt18;
	protected JToggleButton jt19;
	protected JToggleButton jt20;
	protected JToggleButton jt21;
	protected JToggleButton jt22;
	protected JToggleButton jt23;
	protected JToggleButton jt24;
	protected JToggleButton jt25;
	protected ArrayList<String> propios = new ArrayList<String>();
	protected ArrayList<String> ocupados = new ArrayList<String> ();
	Jaula miJaula = new Jaula();
	private JButton btnListado;
	private JXButton btnVer;
	private JXButton btnLiberar;
	private JXButton btnAñadir;
	
	IJaula almacenJaulas;

	public PaneRack(String tipoRack, Usuario miUsuario) {
		
		
		//Hacemos solo una llamada a la base de datos para cargar los huecos
		almacenJaulas = new JaulaDBImpl();
		
		ocupados = almacenJaulas.BusquedaOcupados(miUsuario.getLaboratorio());	
		propios = almacenJaulas.BusquedaPropios(miUsuario);
		//----------------------------------------------------------//
	
		
		setLayout(null);
		setBounds(100, 100, 615, 643);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null); 

		
		//Agregamos la imagen con su resolucion original. Esta hecha pixel a pixel para que cuadre bien
		bg1 = new Imagen("/Multimedia/Rack_Rata.jpg",600,604);		
		bg1.setBounds(0, 0, 600, 604);
		bg1.setOpaque(false);	//Requiere la opacidad a false para que se vea el fondo
		bg1.setLayout(null);
		bg1.setVisible(true);
		add(bg1);		
		
		
		//Declaramos y añadimos los botones de activar/desactivar 
		jt01 = new JToggleButton(tipoRack + "01");
		jt01.setBounds(84, 49, 67, 67);
		//Llamamos al metodo para comprobar el estado de esta jaula
		Pintar(jt01, ocupados, propios);
		bg1.add(jt01);
		
		jt02 = new JToggleButton(tipoRack +"02");
		jt02.setBounds(174, 49, 67, 67);
		Pintar(jt02, ocupados, propios);
		bg1.add(jt02);
		
		jt03 = new JToggleButton(tipoRack + "03");
		jt03.setBounds(264, 49, 67, 67);
		Pintar(jt03, ocupados, propios);
		bg1.add(jt03);
		
		jt04 = new JToggleButton(tipoRack + "04");
		jt04.setBounds(354, 49, 67, 67);
		Pintar(jt04, ocupados, propios);
		bg1.add(jt04);
		
		jt05 = new JToggleButton(tipoRack + "05");
		jt05.setBounds(444, 49, 67, 67);
		Pintar(jt05, ocupados, propios);
		bg1.add(jt05);
		
		jt06 = new JToggleButton(tipoRack + "06");
		jt06.setBounds(84, 139, 67, 67);
		Pintar(jt06, ocupados, propios);
		bg1.add(jt06);
		
		jt07 = new JToggleButton(tipoRack + "07");
		jt07.setBounds(174, 139, 67, 67);
		Pintar(jt07, ocupados, propios);
		bg1.add(jt07);
		
		jt08 = new JToggleButton(tipoRack + "08");
		jt08.setBounds(264, 139, 67, 67);
		Pintar(jt08, ocupados, propios);
		bg1.add(jt08);
		
		jt09 = new JToggleButton(tipoRack + "09");
		jt09.setBounds(354, 139, 67, 67);
		Pintar(jt09, ocupados, propios);
		bg1.add(jt09);
		
		jt10 = new JToggleButton(tipoRack + "10");
		jt10.setBounds(444, 139, 67, 67);
		Pintar(jt10, ocupados, propios);
		bg1.add(jt10);
		
		jt11 = new JToggleButton(tipoRack + "11");
		jt11.setBounds(84, 229, 67, 67);
		Pintar(jt11, ocupados, propios);
		bg1.add(jt11);
		
		jt12 = new JToggleButton(tipoRack + "12");
		jt12.setBounds(174, 229, 67, 67);
		Pintar(jt12, ocupados, propios);
		bg1.add(jt12);
		
		jt13 = new JToggleButton(tipoRack + "13");
		jt13.setBounds(264, 229, 67, 67);
		Pintar(jt13, ocupados, propios);
		bg1.add(jt13);
		
		jt14 = new JToggleButton(tipoRack + "14");
		jt14.setBounds(354, 229, 67, 67);
		Pintar(jt14, ocupados, propios);
		bg1.add(jt14);
		
		jt15 = new JToggleButton(tipoRack + "15");
		jt15.setBounds(444, 229, 67, 67);
		Pintar(jt15, ocupados, propios);
		bg1.add(jt15);
		
		jt16 = new JToggleButton(tipoRack + "16");
		jt16.setBounds(84, 320, 67, 67);
		Pintar(jt16, ocupados, propios);
		bg1.add(jt16);
		
		jt17 = new JToggleButton(tipoRack + "17");
		jt17.setBounds(174, 320, 67, 67);
		Pintar(jt17, ocupados, propios);
		bg1.add(jt17);
		
		jt18 = new JToggleButton(tipoRack + "18");
		jt18.setBounds(264, 320, 67, 67);
		Pintar(jt18, ocupados, propios);
		bg1.add(jt18);
		
		jt19 = new JToggleButton(tipoRack + "19");
		jt19.setBounds(354, 320, 67, 67);
		Pintar(jt19, ocupados, propios);
		bg1.add(jt19);
		
		jt20 = new JToggleButton(tipoRack + "20");
		jt20.setBounds(444, 320, 67, 67);
		Pintar(jt20, ocupados, propios);
		bg1.add(jt20);
		
		jt21 = new JToggleButton(tipoRack + "21");
		jt21.setBounds(84, 410, 67, 67);
		Pintar(jt21, ocupados, propios);
		bg1.add(jt21);
		
		jt22 = new JToggleButton(tipoRack + "22");
		jt22.setBounds(174, 410, 67, 67);
		Pintar(jt22, ocupados, propios);
		bg1.add(jt22);		
		
		jt23 = new JToggleButton(tipoRack + "23");
		jt23.setBounds(264, 410, 67, 67);
		Pintar(jt23, ocupados, propios);
		bg1.add(jt23);
		
		jt24 = new JToggleButton(tipoRack + "24");
		jt24.setBounds(354, 410, 67, 67);
		Pintar(jt24, ocupados, propios);
		bg1.add(jt24);
		
		jt25 = new JToggleButton(tipoRack + "25");
		jt25.setBounds(444, 410, 67, 67);
		Pintar(jt25, ocupados, propios);
		bg1.add(jt25);
		
		btnAñadir = new JXButton();
		btnAñadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ArrayList<String> añadir = new ArrayList<String>();
				añadir=ComprobacionSeleccionados(); //Comprueba cuantos botones tengo seleccionados y los almacena en una lista

				if(añadir.size()==0) {//si la lista esta vacia
					JOptionPane.showMessageDialog(null, "Por favor marca una jaula");
				}else {
					//Comprobamos que todos los campos seleccionados estan libres, si no salta advertencia y se cancela
					boolean libres = true;
					for(int i=0; i<añadir.size(); i++) {
						if(propios.contains(añadir.get(i))) {//Si la lista esta libre pero con una jaula ya ocupada
							JOptionPane.showMessageDialog(null, "Marca una jaula libre");
							libres = false;
							break;
						}
					}
					
					if(libres == true) {
						//enviamos un ArrayList con la cantidad de huecos seleccionados para hacer multiples inserciones
						dispose();
						new AltaJaula(añadir, miUsuario).setVisible(true);
					}

					
				}
			}
		});
		
		btnAñadir.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnAñadir.setText("A\u00F1adir");
		btnAñadir.setBounds(35, 524, 111, 30);
		bg1.add(btnAñadir);
		
		btnVer = new JXButton();
		btnVer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ArrayList<String> modificar = new ArrayList<String>();
				modificar=ComprobacionSeleccionados(); //Comprueba cuantos botones tengo seleccionados y los almacena en una lista
				if(modificar.size()>1) { //Si la lista almacena mas de una jaula
					JOptionPane.showMessageDialog(null, "Has marcado mas de una jaula. Por favor marca solo una");
				}else if(modificar.size()==0) {//si la lista esta vacia
					JOptionPane.showMessageDialog(null, "Por favor marca una jaula");
				}else {
					if(!propios.contains(modificar.get(0))) {//Si la lista esta libre pero con una jaula ya ocupada
						JOptionPane.showMessageDialog(null, "Marca una jaula tuya");
					}else {
						new ModificarJaula(modificar, miUsuario).setVisible(true);
					}
				}
			}
		});
		
		btnVer.setText("Ver Jaula");
		btnVer.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnVer.setBounds(281, 524, 119, 30);
		bg1.add(btnVer);
		
		btnLiberar = new JXButton();
		btnLiberar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ArrayList<String> eliminar = new ArrayList<String>();
				eliminar = ComprobacionSeleccionados();
				almacenJaulas.BorrarJaula(eliminar);
				try {
					Thread.sleep(500);
					dispose();
					new SelectorJaulas(miUsuario).setVisible(true);
				}catch (InterruptedException e) {
					e.printStackTrace();
				}				
			}
		});
		
		btnLiberar.setText("Liberar");
		btnLiberar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnLiberar.setBounds(156, 524, 111, 30);
		bg1.add(btnLiberar);
		
		btnListado = new JButton("Ver en Lista");
		btnListado.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnListado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				new ListadoJaulas(miUsuario).setVisible(true);
			}
		});
		btnListado.setBounds(410, 525, 140, 29);
		bg1.add(btnListado);
	}
	
	//Como un JPanel no tiene la funcion de dispose, la cual pertenece a JFrame, la creo
	public void dispose() {
	    JFrame parent = (JFrame) this.getTopLevelAncestor();
	    parent.dispose();
	}
	
	//LLamariamos a esta funcion desde el constructor para pintar los botones segun disponibilidad
	//en la BD
	public void Pintar (JToggleButton boton, ArrayList<String> ocupados, ArrayList<String> propios) {
		//Comprobamos primero si estan ocupados, porque si no, al administrador no le entran todos como propios
			if(propios.contains(boton.getText())) {
				boton.setBackground(Color.CYAN);
			}else if(ocupados.contains(boton.getText())) {
				boton.setBackground(Color.RED);
				boton.setEnabled(false);
			}else {
				//Los botones ocupados serán de color rojo y estaran bloqueados por el usuario ajeno
				boton.setBackground(Color.GREEN);
			}
	}

	//Con esto, almacenamos en un array los botones seleccionados
	//de tal modo podemos pedir que solo se seleccione uno para agregar
	// o modificar, y varios para eliminar. Tambien se si quita la seleccion
	// el sistema no lo cuente
	public ArrayList<String> ComprobacionSeleccionados() {
		ArrayList<String> listaSeleccionados = new ArrayList<String>();
		
		/**
		 * Comprobamos cuantos se seleccionaron
		 */
				
		return listaSeleccionados;
	}
		
		
	}
