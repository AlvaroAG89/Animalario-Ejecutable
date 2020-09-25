package Dao;

import Entity.Usuario;

public interface IUsuario {
	
	public Usuario comprobacionLogin(String usuario, String contraseña);
	
	public boolean registrarUsuario(Usuario nuevo);
	
	public boolean busquedaUsuario (String usuario);
	
	public void conexion();
}
