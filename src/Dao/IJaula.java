package Dao;

import java.util.ArrayList;

import Entity.Jaula;
import Entity.Usuario;


public interface IJaula {
	
	public void insertarJaula(Jaula nuevaJaula);
	
	public Jaula BusquedaCompleta(String jaula);
	
	public ArrayList<String> BusquedaPropios(Usuario miUsuario);
	
	public ArrayList<String> BusquedaOcupados(String responsable);
	
	public void ModificarJaula(Jaula miJaula);
	
	public void BorrarJaula (ArrayList<String> jaulas);
	
	public void conexion();
	
}
