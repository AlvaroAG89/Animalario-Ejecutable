package Entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Contiene informacion de los racks que se meten
 * @author Alvaro
 * @version 1.1
 * @since 1.0
 */

@Entity
@Table(name="Rack")
public class Rack implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name="Tipo_Rack")
	@Id
	private String tipo_caja;
	
	@Column(name="Stock")
	private int stock;
	
	@Column(name="Total")
	private int total;
	
	
	public String getTipo_caja() {
		return tipo_caja;
	}
	public void setTipo_caja(String tipo_caja) {
		this.tipo_caja = tipo_caja;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	
	
	public Rack() {
		super();
	}
	
	public Rack(String tipo_caja, int stock, int total) {
		super();
		this.tipo_caja = tipo_caja;
		this.stock = stock;
		this.total = total;
	}
	
	
	
}
