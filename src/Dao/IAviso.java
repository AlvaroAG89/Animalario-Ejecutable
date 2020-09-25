package Dao;

import java.util.List;

import Entity.Aviso;

public interface IAviso{
	
	public void insertar(Aviso nuevoAviso);
	
	public void BorrarMensaje (Aviso borrado);
	
	public void VerAviso (Aviso miAviso);
	
	public List<String> cargaContactos();
	
	public void conexion();
	
	
}
