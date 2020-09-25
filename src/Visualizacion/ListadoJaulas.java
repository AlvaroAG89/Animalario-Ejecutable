package Visualizacion;


import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import Dao.IJaula;
import Dao.JaulaDBImpl;
import Entity.Jaula;
import Entity.Usuario;

import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Toolkit;


/**
 * Listado de Jaulas en una DTM y su gestion
 * @author Alvaro
 * @version 1.1
 * @since 1.0
 */


public class ListadoJaulas extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JMenuItem mnCerrarSesion;
	private JMenu mnArchivo;
	private JMenu mnGestion;
	private JMenuItem mntmReservas;
	private JMenuItem mntmAvisos;
	private JMenu mnAyuda;
	private JMenuItem mntmContactarConAdministrador;
	private JMenuBar menuBar;

	
	private JPanel contentPane;
	private static DefaultTableModel dtm;
	private static JTable tabla;
	private JScrollPane jsp;

	private int fila = 0;
	private JButton btnModificar;
	private JButton btnVolver;
	private Usuario user;
	
	
	private static EntityManager manager;
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("Persistencia");

	IJaula almacenJaula;
	
	public static void main(String[] args, Usuario miUsuario) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListadoJaulas frame = new ListadoJaulas(miUsuario);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public ListadoJaulas(Usuario miUsuario) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ListadoJaulas.class.getResource("/Multimedia/icono.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 842, 509);
		
		almacenJaula = new JaulaDBImpl();
		
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
		
		
		
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		user= miUsuario;
		tabla = new JTable(generarDTM());
		tabla.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				fila = tabla.rowAtPoint(e.getPoint());
				
			}
		});
		tabla.setFont(new Font("Tahoma", Font.PLAIN, 11));
		tabla.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		//Ordenar el JTable con los nombres de las columnas
		final TableRowSorter<TableModel> tablasort = new TableRowSorter<TableModel>(dtm);
		tabla.setRowSorter(tablasort);	//Indicamos que estará ordenada
		tabla.setShowVerticalLines(true);
		tabla.setShowHorizontalLines(false);
		tabla.setRowSelectionAllowed(true);
		tabla.setColumnSelectionAllowed(false);
		tabla.setSelectionForeground(Color.blue);
		tabla.setSelectionBackground(Color.pink);
		tabla.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		tabla.setVisible(true);
		contentPane.setLayout(null);
		
		jsp = new JScrollPane(tabla);
		jsp.setBounds(0, 110, 826, 189);
		contentPane.add(jsp);
		
		JLabel lblListadoDeTodas = new JLabel("Listado de todas mis Jaulas");
		lblListadoDeTodas.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblListadoDeTodas.setForeground(Color.BLUE);
		lblListadoDeTodas.setBounds(278, 63, 366, 14);
		contentPane.add(lblListadoDeTodas);
		
		JButton btnBorrar = new JButton("Borrar");
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ArrayList<String> borrado = new ArrayList<String>();
				int seleccionadas [] = tabla.getSelectedRows();
				for(int j=0; j<seleccionadas.length; j++) {
					System.out.println(seleccionadas[j]);
					borrado.add(String.valueOf(dtm.getValueAt(seleccionadas[j], 0)));
				}
				almacenJaula.BorrarJaula(borrado);
				dispose();
				new ListadoJaulas(miUsuario).setVisible(true);
			}
		});
		btnBorrar.setBounds(342, 358, 115, 36);
		contentPane.add(btnBorrar);
		
		btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				SimpleDateFormat fecha = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				Date fecha_nacimiento = null;
				Date fecha_reserva = null;
				
				try {
					fecha_nacimiento = fecha.parse(String.valueOf(dtm.getValueAt(fila, 9)));
					fecha_reserva = fecha.parse(String.valueOf(dtm.getValueAt(fila, 12)));
					} catch (ParseException e) {
					e.printStackTrace();
				}
								
				
	            Jaula miJaula = null;
	            
				try {
					miJaula = new Jaula(String.valueOf(dtm.getValueAt(fila, 0)), 
							String.valueOf(dtm.getValueAt(fila, 1)), String.valueOf(dtm.getValueAt(fila, 2)), 
							String.valueOf(dtm.getValueAt(fila, 3)), String.valueOf(dtm.getValueAt(fila, 4)), 
							String.valueOf(dtm.getValueAt(fila, 5)), String.valueOf(dtm.getValueAt(fila, 6)), 
							Integer.parseInt(String.valueOf(dtm.getValueAt(fila, 7))), Integer.parseInt(String.valueOf(dtm.getValueAt(fila, 8))), 
							fecha_nacimiento, String.valueOf(dtm.getValueAt(fila, 10)), 
							String.valueOf(dtm.getValueAt(fila, 11)), fecha_reserva);
				} catch (NumberFormatException e) {
					e.printStackTrace();
				}
				dispose();
				new ModificarJaula(miJaula, miUsuario).setVisible(true);;
			}
		});
		btnModificar.setBounds(138, 358, 115, 36);
		contentPane.add(btnModificar);
		
		btnVolver = new JButton("Vista Racks");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new SelectorJaulas(miUsuario).setVisible(true);
				dispose();
			}
		});
		btnVolver.setBounds(550, 358, 115, 36);
		contentPane.add(btnVolver);
		
	}
	
	
	public DefaultTableModel generarDTM(){		//La creación del DTM en un método
		dtm = new DefaultTableModel(cargaDatos(), cargaNombresColumnas())
		{
			@Override
			//Método para indicar que la estructura ha cambiado
			public void fireTableStructureChanged() {
				super.fireTableStructureChanged();
			}

			private static final long serialVersionUID = 1L;

			@Override
			// Método para hacer las celdas no editables con FALSE
			public boolean isCellEditable(int row, int column) {
				return false;
			}

			@Override
			// Indicamos que ha cambiado para que se actualize
			public void fireTableDataChanged() {
				super.fireTableDataChanged();
			}
		};
		return dtm;
	}
	
	public int cargarNumColumnas() {
		int contador = 13;
		return contador;
	}

	public int cargarNumRegistros() { 
		int total = 0;
	
		conexion();

//		
		try { // Creo [][] con los tamaños de los métodos
			if(user.getUsuario().equals("Veterinario")) {
				total = manager.createQuery("FROM Jaula").getResultList().size();
			}else {
				total = manager.createQuery("FROM Jaula WHERE laboratorio = '" + user.getLaboratorio() + "'").getResultList().size();
			}
		} catch (Exception e) {
		JOptionPane.showMessageDialog(null,"Error de carga de registros");
		} finally {
			manager.close();
		}
		
		return total;
	}
	
	public Object[] cargaNombresColumnas(){
		
		Object[] col = {"Jaula", "Nombres", "Laboratorio", "Responsable", "Especie", "Cepa", "Tipo Rack", 
		                          "Ratas Hembra", "Ratas Macho", "Fecha Nacimiento", "Procedimiento", 
		                          "Observaciones", "Fecha Reserva"};

		return col;
	}
	
	@SuppressWarnings("unchecked")
	public Object[][] cargaDatos() { 
		Object[][] datos = new Object[cargarNumRegistros()][cargarNumColumnas()];
		
		conexion();

		Jaula miJaula = null;
		
		try { // Creo [][] con los tamaños de los métodos
			List<Jaula> encontrado;
			if(user.getUsuario().equals("Veterinario")) {
				encontrado = manager.createQuery("FROM Jaula").getResultList();
			}else{
				encontrado = manager.createQuery("FROM Jaula WHERE laboratorio= '" + user.getLaboratorio() + "'").getResultList();
				
			}
			int contador =0;
			for (Iterator<Jaula> iterator = encontrado.iterator(); iterator.hasNext();) {
				miJaula = iterator.next();
				datos[contador][0] = miJaula.getJaula();
				datos[contador][1] = miJaula.getNombres();
				datos[contador][2] = miJaula.getLaboratorio();
				datos[contador][3] = miJaula.getResponsable();
				datos[contador][4] = miJaula.getEspecie();
				datos[contador][5] = miJaula.getCepa();
				datos[contador][6] = miJaula.getTipo_jaula();
				datos[contador][7] = miJaula.getNumRatasHembras(); 
				datos[contador][8] = miJaula.getNumRatasMacho();
				datos[contador][9] = miJaula.getFechaNacimiento();
				datos[contador][10] = miJaula.getProcedimiento();
				datos[contador][11] = miJaula.getObservaciones();
				datos[contador][12] = miJaula.getFecha_reserva();
				contador++;
			}
		} catch (Exception e) {
		JOptionPane.showMessageDialog(null,"Error de Transacción Listado");
		} finally {
			manager.close();
		}
		return datos;
	}
	
	public static void conexion (){
		Logger.getLogger("org.hibernate").setLevel(Level.OFF);
		
		manager = emf.createEntityManager();
	}
	
}


