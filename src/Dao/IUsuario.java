package Dao;

import Entity.Usuario;

public interface IUsuario {
	
	public Usuario comprobacionLogin(String usuario, String contraseņa);
	
	public boolean registrarUsuario(Usuario nuevo);
	
	public boolean busquedaUsuario (String usuario);
	
	public void conexion();
}
