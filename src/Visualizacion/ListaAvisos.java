package Visualizacion;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
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

import Dao.AvisoDBImpl;
import Dao.IAviso;
import Entity.Aviso;
import Entity.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;


/**
 * DTM con la lista de correo que dispongo
 * @author Alvaro
 * @version 1.1
 * @since 1.0
 */



public class ListaAvisos extends JFrame {

	
	private static final long serialVersionUID = 1L;
	
	private JMenuItem mnCerrarSesion;
	private JMenu mnArchivo;
	private JMenu mnAyuda;
	private JMenuItem mntmContactarConAdministrador;
	private JMenuBar menuBar;
	
	
	private JPanel contentPane;
	private static DefaultTableModel dtm;
	private static JTable tabla;
	private JScrollPane jsp;
	
	private static EntityManager manager;
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("Persistencia");
	
	
	private int fila = 0;
	private Usuario user;
	private JButton btnBorrar;
	private JButton btnRedactar;
	private JMenuItem mntmMenuPrincipal;

	IAviso almacenAvisos;
	
	public static void main(String[] args, Usuario miUsuario) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListaAvisos frame = new ListaAvisos(miUsuario);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	
	public ListaAvisos(Usuario miUsuario) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ListaAvisos.class.getResource("/Multimedia/icono.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 683, 372);
		//Bloqueo de redimensionar ventana
		this.setResizable(false);
		
		almacenAvisos = new AvisoDBImpl();
		
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
		user= miUsuario;
				
		tabla = new JTable(generarDTM());
		tabla.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				fila = tabla.rowAtPoint(e.getPoint());
				//Si hago un dobleClick podre acceder a la lectura del correo
				if(e.getClickCount() == 2) {
					
					SimpleDateFormat fecha = new SimpleDateFormat("yyyy-MM-dd");
					Date fecha_envio = null;

					try {
						fecha_envio = fecha.parse(String.valueOf(dtm.getValueAt(fila, 4)));
						} catch (ParseException e1) {
						e1.printStackTrace();
					}
					boolean leido = Boolean.parseBoolean(String.valueOf(dtm.getValueAt(fila, 5)));
					
					Aviso esteAviso = new Aviso(Integer.parseInt(String.valueOf(dtm.getValueAt(fila, 6))),
							String.valueOf(dtm.getValueAt(fila, 0)), 
							String.valueOf(dtm.getValueAt(fila, 1)), 
							String.valueOf(dtm.getValueAt(fila, 2)), 
							String.valueOf(dtm.getValueAt(fila, 3)),
							fecha_envio, leido); 
					almacenAvisos.VerAviso(esteAviso);
					dispose();
					new LeerCorreo(miUsuario, esteAviso).setVisible(true);
				}
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
	
		
		//volver invisible la fila 5 con los valores booleanos de leido y no leido
		tabla.getColumnModel().getColumn(5).setResizable(false);
		tabla.getColumnModel().getColumn(5).setMaxWidth(0);
		tabla.getColumnModel().getColumn(5).setMinWidth(0);
		tabla.getTableHeader().getColumnModel().getColumn(5).setMaxWidth(0);
		tabla.getTableHeader().getColumnModel().getColumn(5).setMinWidth(0);
		
		//lo mismo con columna ID
		tabla.getColumnModel().getColumn(6).setResizable(false);
		tabla.getColumnModel().getColumn(6).setMaxWidth(0);
		tabla.getColumnModel().getColumn(6).setMinWidth(0);
		tabla.getTableHeader().getColumnModel().getColumn(6).setMaxWidth(0);
		tabla.getTableHeader().getColumnModel().getColumn(6).setMinWidth(0);
		
		
		jsp = new JScrollPane(tabla);
		jsp.setBounds(22, 121, 626, 189);
		contentPane.add(jsp);
		
		JLabel lblListadoDeTodas = new JLabel("Buz\u00F3n de Avisos");
		lblListadoDeTodas.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblListadoDeTodas.setForeground(Color.RED);
		lblListadoDeTodas.setBounds(259, 62, 197, 14);
		contentPane.add(lblListadoDeTodas);
		
		//marcamos los correos que no estan leidos
		pintar();
		
		btnBorrar = new JButton();
		btnBorrar.setIcon(new ImageIcon(ListaAvisos.class.getResource("/Multimedia/borrar.png"))); 
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					
	            int[] filasSeleccionadas = tabla.getSelectedRows(); 
	            int numFilas = tabla.getSelectedRowCount();

				if(numFilas != 0) {
					
		            for (int indice : filasSeleccionadas) { 
						SimpleDateFormat fecha = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
						Date fecha_envio = null;
	
						try {
							fecha_envio = fecha.parse(String.valueOf(dtm.getValueAt(indice, 4)));
							} catch (ParseException e) {
							e.printStackTrace();
						}
						
						boolean leido = Boolean.parseBoolean(String.valueOf(dtm.getValueAt(fila, 5)));
						
						Aviso miAviso = new Aviso(Integer.parseInt(String.valueOf(dtm.getValueAt(fila, 6))),
								String.valueOf(dtm.getValueAt(fila, 0)), 
								String.valueOf(dtm.getValueAt(fila, 1)), 
								String.valueOf(dtm.getValueAt(fila, 2)), 
								String.valueOf(dtm.getValueAt(fila, 3)),
								fecha_envio, leido); 
								
						
						almacenAvisos.BorrarMensaje(miAviso);
		            }
					dispose();
					new ListaAvisos(miUsuario).setVisible(true);
				}else {
					JOptionPane.showMessageDialog(null, "Por favor selecciona solo una fila");
					System.out.println(fila);
				}
			}
		});
		btnBorrar.setBounds(70, 87, 27, 27);
		contentPane.add(btnBorrar);
		
		btnRedactar = new JButton("");
		btnRedactar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				new EnviarCorreo(miUsuario).setVisible(true);
			}
		});
		btnRedactar.setIcon(new ImageIcon(ListaAvisos.class.getResource("/Multimedia/redactar.png")));
		btnRedactar.setBounds(34, 87, 27, 27);
		contentPane.add(btnRedactar);
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
		int contador = 7;

		return contador;
	}

	public int cargarNumRegistros() { 
		int total = 0;
		conexion();
		
		try { // Creo [][] con los tamaños de los métodos
			
			total = manager.createQuery("FROM Aviso WHERE destinatario = '" + user.getUsuario() + "'").getResultList().size();

		} catch (Exception e) {
		JOptionPane.showMessageDialog(null,"Error de carga de registros");
		} finally {
			manager.close();
		}
		
		return total;
	}
	
	public Object[] cargaNombresColumnas(){
		
		Object[] col = {"Destinatario", "Remitente", "Asunto", "Mensaje", "Fecha Envio", "Leido", "Id"};
		
		return col;
	}
	
	

	@SuppressWarnings("unchecked")
	public Object[][] cargaDatos() { 
		Object[][] datos = new Object[cargarNumRegistros()][cargarNumColumnas()];
	
		conexion();
	
		Aviso miAviso = null;
		
		try { // Creo [][] con los tamaños de los métodos
		
			List<Aviso> encontrado;

			encontrado = manager.createQuery("FROM Aviso WHERE destinatario ='" + user.getUsuario() + "' ORDER BY fecha_envio DESC").getResultList();

			int contador =0;
			for (Iterator<Aviso> iterator = encontrado.iterator(); iterator.hasNext();) {
				miAviso = iterator.next();

				datos[contador][0] = miAviso.getDestinatario();
				datos[contador][1] = miAviso.getRemitente();
				datos[contador][2] = miAviso.getAsunto();
				datos[contador][3] = miAviso.getMensaje();
				datos[contador][4] = miAviso.getFecha_envio();
				datos[contador][5] = miAviso.isLeido();
				datos[contador][6] = miAviso.getId();
				
				contador++;
			}
		} catch (Exception e) {
		JOptionPane.showMessageDialog(null,"Error de Transacción Listado");
		} finally {
			manager.close();
		}
		
		return datos;
	}
	
	
	public void pintar() {
		//Llamo a la clase MiRenderer, la cual cambia el fondo por defecto de cada fila segun
		//la columna leido sea true o false
		
		ListaAvisos.tabla.setDefaultRenderer(Object.class, new MiRenderer());
	}
	
	
	public static void conexion (){
		Logger.getLogger("org.hibernate").setLevel(Level.OFF);
		
		manager = emf.createEntityManager();
	}
}
