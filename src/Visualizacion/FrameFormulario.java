package Visualizacion;


import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import com.toedter.calendar.JDateChooser;
import javax.swing.JComboBox;

/**
 * Plantilla para formularios de altas y modificaciones de jaulas
 * @author Alvaro
 * @version 1.1
 * @since 1.0
 */

@SuppressWarnings("serial")
public class FrameFormulario extends JFrame {

	protected JTextField txtJaula;
	protected JTextField txtNombre;
	protected JTextField txtLaboratorio;
	protected JTextField txtResponsable;
	protected JTextField txtHembra;
	protected JTextField txtMacho;
	protected JTextField txtProcedimiento;
	protected JTextArea textArea;

	protected static ArrayList<String> paginas = new ArrayList<String>();
	protected JDateChooser txtFechaNac;
	protected JComboBox<String> cbRack;
	protected JComboBox<String> cbEspecie;
	protected JTextField txtCepa;
	protected static JPanel pFormulario;
	private JLabel lblLaboratorio;
	private JLabel lblResponsable;
	private JLabel lblCepa;
	private JLabel lblRatasMacho;
	private JLabel lblObservaciones;
	private JLabel lblTipoDeRack;
	private JLabel lblFechaNacimiento;
	private JLabel lblProcedimiento;
	private JLabel lblRatasHembra;
	private JLabel lblEspecie;
	private JLabel lbNombres;
	private JLabel lblJaula;




	public static void main(String[] args /*Usuario miUsuario*/) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameFormulario frame = new FrameFormulario();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	//Este Frame lo heredaran la Clase AltaJaula, y la Clase ModificarJaula
	
	
	//Ponemos dos constructores distintos. El primero sera para la modificación. Envio un objeto
	//Jaula con todos los datos de la jaula a modificar
	
	//El segundo constructor se usara para la insercción. Como tenemos la posibilidad de rellenar mas de 
	//una jaula en el mismo formulario, necesito enviar el numero de jaulas que voy a insertar
	
	
	public FrameFormulario() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 646, 460);
		pFormulario = new JPanel();
		
		pFormulario.setBounds(10, 11, 610, 307);
		getContentPane().add(pFormulario);
	
		
		textArea = new JTextArea();
		textArea.setBounds(108, 218, 154, 59);
		
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setBounds(399, 176, 178, 87);
		pFormulario.add(scrollPane);
				
		pFormulario.setLayout(null);
		
		txtJaula = new JTextField();
		txtJaula.setBounds(130, 27, 119, 20);
		pFormulario.add(txtJaula);
		txtJaula.setColumns(10);
		
		lblJaula = new JLabel("Jaula");
		lblJaula.setBounds(10, 31, 34, 14);
		pFormulario.add(lblJaula);
		
		txtLaboratorio = new JTextField();
		txtLaboratorio.setBounds(399, 27, 155, 20);
		pFormulario.add(txtLaboratorio);
		txtLaboratorio.setColumns(10);

		
		lblLaboratorio = new JLabel("Laboratorio");
		lblLaboratorio.setBounds(302, 30, 74, 14);
		pFormulario.add(lblLaboratorio);
		
		lblRatasHembra = new JLabel("Ratas Hembra");
		lblRatasHembra.setBounds(10, 141, 101, 14);
		pFormulario.add(lblRatasHembra);
		
		txtHembra = new JTextField();
		txtHembra.setBounds(130, 138, 119, 20);
		pFormulario.add(txtHembra);
		txtHembra.setColumns(10);
		
		lblFechaNacimiento = new JLabel("Fecha Nacimiento");
		lblFechaNacimiento.setBounds(10, 219, 101, 14);
		pFormulario.add(lblFechaNacimiento);
		
		lblResponsable = new JLabel("Responsable");
		lblResponsable.setBounds(302, 65, 94, 14);
		pFormulario.add(lblResponsable);
		
		lblRatasMacho = new JLabel("Ratas Macho");
		lblRatasMacho.setBounds(302, 141, 94, 14);
		pFormulario.add(lblRatasMacho);
		
		lblProcedimiento = new JLabel("Procedimiento");
		lblProcedimiento.setBounds(7, 256, 104, 14);
		pFormulario.add(lblProcedimiento);
		
		txtProcedimiento = new JTextField();
		txtProcedimiento.setBounds(130, 253, 119, 20);
		pFormulario.add(txtProcedimiento);
		txtProcedimiento.setText("");
		txtProcedimiento.setColumns(10);
		
		txtResponsable = new JTextField();
		txtResponsable.setBounds(399, 62, 155, 20);
		pFormulario.add(txtResponsable);
		txtResponsable.setColumns(10);
		
		txtMacho = new JTextField();
		txtMacho.setBounds(399, 138, 119, 20);
		pFormulario.add(txtMacho);
		txtMacho.setColumns(10);
		
		lblObservaciones = new JLabel("Observaciones");
		lblObservaciones.setBounds(302, 179, 94, 14);
		pFormulario.add(lblObservaciones);
		
		txtFechaNac = new JDateChooser();
		txtFechaNac.setBounds(130, 216, 119, 20);
		pFormulario.add(txtFechaNac);
		
		lbNombres = new JLabel("Nombres");
		lbNombres.setBounds(10, 65, 74, 25);
		pFormulario.add(lbNombres);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(130, 62, 119, 20);
		pFormulario.add(txtNombre);
		txtNombre.setColumns(10);

		lblTipoDeRack = new JLabel("Tipo de Rack");
		lblTipoDeRack.setBounds(10, 179, 94, 14);
		pFormulario.add(lblTipoDeRack);
		
		cbRack = new JComboBox<String>();
		cbRack.setBounds(130, 176, 119, 20);
		pFormulario.add(cbRack);
		
		cbEspecie = new JComboBox<String>();
		cbEspecie.setBounds(130, 102, 119, 20);
		pFormulario.add(cbEspecie);
		cbEspecie.addItem("Rata");
		cbEspecie.addItem("Ratón");
		
		lblEspecie = new JLabel("Especie");
		lblEspecie.setBounds(10, 105, 46, 14);
		pFormulario.add(lblEspecie);
		
		lblCepa = new JLabel("Cepa");
		lblCepa.setBounds(302, 105, 46, 14);
		pFormulario.add(lblCepa);
		
		txtCepa = new JTextField();
		txtCepa.setBounds(399, 102, 119, 20);
		pFormulario.add(txtCepa);
		txtCepa.setColumns(10);
		cbRack.addItem("Grande");
		cbRack.addItem("Pequeño Simple");
		cbRack.addItem("Pequeño Ventilado");	
				
		
		txtMacho.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				int c = (int) e.getKeyChar();
				if(!Character.isDigit(c)) {
					getToolkit().beep();
					e.consume();
				}
			}
		});
		txtResponsable.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				int c = (int) e.getKeyChar();
				if(!Character.isDigit(c) || (c=='.' && !txtResponsable.getText().contains("."))) {
					
				}else {
					getToolkit().beep();
					e.consume();
				}
			}
		});
		txtHembra.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				int c = (int) e.getKeyChar();
				if(!Character.isDigit(c)) {
					getToolkit().beep();
					e.consume();
				}
			}
		});
		
	}
	

	//Comprobamos si los campos que deseo estan vacios. Lo llamo desde el constructor
	public static boolean ComprobacionCampos(JTextField caja) {
		if(caja.getText().isEmpty()) {
			caja.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
			caja.requestFocus();
			return false;
		}else {
			caja.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
			return true;
		}
	}
	
	//Igual que el anterior pero para el JDateChooser
	public boolean ComprobacionFechas(JDateChooser caja) {
		boolean encontrado = false;
		/**
		 * Se comprueba
		 */
		return encontrado;
	}
}
