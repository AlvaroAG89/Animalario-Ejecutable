package Entity;

import java.io.Serializable;

/**
 * Gestiona los avisos enviados de un usuario a otro
 * @author Alvaro
 * @version 1.1
 * @since 1.0
 */


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Aviso")
public class Aviso implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name="Id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name="Destinatario")
	private String destinatario;
	
	@Column(name="Remitente")
	private String remitente;
	
	@Column(name="Asunto")
	private String asunto;
	
	@Column(name="fecha_envio")
	private Date fecha_envio;
	
	@Column(name="Mensaje")
	private String mensaje;
	
	@Column(name="Leido")
	private boolean leido;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public boolean isLeido() {
		return leido;
	}
	public void setLeido(boolean leido) {
		this.leido = leido;
	}
	public String getDestinatario() {
		return destinatario;
	}
	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}
	public String getRemitente() {
		return remitente;
	}
	public void setRemitente(String remitente) {
		this.remitente = remitente;
	}
	public String getAsunto() {
		return asunto;
	}
	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}
	public Date getFecha_envio() {
		return fecha_envio;
	}
	public void setFecha_envio(Date fecha_envio) {
		this.fecha_envio = fecha_envio;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	public Aviso(String destinatario, String remitente, String asunto, String mensaje) {
		super();
		this.destinatario = destinatario;
		this.remitente = remitente;
		this.asunto = asunto;
		this.mensaje = mensaje;
		Date fecha = new Date();
		this.fecha_envio = fecha;
		this.leido =false;
	}
	
	public Aviso(String destinatario, String remitente, String asunto,  String mensaje, Date fecha_envio) {
		super();
		this.destinatario = destinatario;
		this.remitente = remitente;
		this.asunto = asunto;
		this.fecha_envio = fecha_envio;
		this.mensaje = mensaje;
		this.leido =false;
	}
	public Aviso(int id, String destinatario, String remitente, String asunto, String mensaje, Date fecha_envio,
			boolean leido) {
		super();
		this.id = id;
		this.destinatario = destinatario;
		this.remitente = remitente;
		this.asunto = asunto;
		this.fecha_envio = fecha_envio;
		this.mensaje = mensaje;
		this.leido = leido;
	}
	public Aviso(String destinatario, String remitente, String asunto,String mensaje, Date fecha_envio,
			boolean leido) {
		super();
		this.destinatario = destinatario;
		this.remitente = remitente;
		this.asunto = asunto;
		this.fecha_envio = fecha_envio;
		this.mensaje = mensaje;
		this.leido = leido;
	}
	
	public Aviso() {
		
	}
	
	
	
	
}
