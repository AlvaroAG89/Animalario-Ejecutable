package Dao;

import Entity.Rack;

public interface IRack {

	public boolean nuevoRack(Rack nuevo);
	
	public boolean ActualizarRack (Rack nuevoRack);
	
	public Rack BusquedaRacks(String tipo);
	
	public void conexion ();
}
