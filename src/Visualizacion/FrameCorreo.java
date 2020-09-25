package Visualizacion;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;

/**
 * Plantilla para los formularios de envio y lectura de correos
 * @author Alvaro
 * @version 1.1
 * @since 1.0
 */

public class FrameCorreo extends JFrame {


	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	protected JTextField txtEmisor;
	protected JTextField txtReceptor;
	protected JTextField txtAsunto;
	protected JTextArea textArea;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblTexto;
	private JScrollPane scrollPane;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					FrameCorreo frame = new FrameCorreo();
					frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
										
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public FrameCorreo() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 517, 441);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblNewLabel = new JLabel("De:");
		lblNewLabel.setBounds(22, 63, 46, 14);
		contentPane.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("Para:");
		lblNewLabel_1.setBounds(22, 91, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		txtEmisor = new JTextField();
		txtEmisor.setBounds(72, 60, 135, 20);
		contentPane.add(txtEmisor);
		txtEmisor.setColumns(10);
		
		txtReceptor = new JTextField();
		txtReceptor.setBounds(72, 88, 261, 20);
		contentPane.add(txtReceptor);
		txtReceptor.setColumns(10);
		
		txtAsunto = new JTextField();
		txtAsunto.setBounds(72, 119, 135, 20);
		contentPane.add(txtAsunto);
		txtAsunto.setColumns(10);
		
		textArea = new JTextArea();
		//textArea.setBounds(72, 158, 382, 160);
		//contentPane.add(textArea);
				
		scrollPane = new JScrollPane(textArea);
		scrollPane.setBounds(72, 158, 382, 160);
		contentPane.add(scrollPane);
		
		lblNewLabel_2 = new JLabel("Asunto");
		lblNewLabel_2.setBounds(22, 122, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		lblTexto = new JLabel("Texto");
		lblTexto.setBounds(22, 163, 46, 14);
		contentPane.add(lblTexto);
	}
}
