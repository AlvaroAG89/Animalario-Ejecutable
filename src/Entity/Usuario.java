package Entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Contiene informacion de los Usuarios registrados
 * @author Alvaro
 * @version 1.1
 * @since 1.0
 */

@Entity
@Table(name="Users")
public class Usuario implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name="Usuario")
	@Id
	private String usuario;
	
	@Column(name="Contrasena")
	private String contrase�a;
	
	@Column(name="Laboratorio")
	private String laboratorio;
	
	@Column(name="Nombre")
	private String nombre;
	
	@Column(name="Apellidos")
	private String apellidos;
	
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getContrase�a() {
		return contrase�a;
	}
	public void setContrase�a(String contrase�a) {
		this.contrase�a = contrase�a;
	}
	public String getLaboratorio() {
		return laboratorio;
	}
	public void setLaboratorio(String laboratorio) {
		this.laboratorio = laboratorio;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	
	
	
	@Override
	public String toString() {
		return "Usuario [usuario=" + usuario + ", contrase�a=" + contrase�a + ", laboratorio=" + laboratorio
				+ ", nombre=" + nombre + ", apellidos=" + apellidos + "]";
	}
	
	public Usuario() {
		super();
	}
	public Usuario(String usuario, String contrase�a, String laboratorio, String nombre, String apellidos) {
		super();
		this.usuario = usuario;
		this.contrase�a = contrase�a;
		this.laboratorio = laboratorio;
		this.nombre = nombre;
		this.apellidos = apellidos;
	}
	
	
}
