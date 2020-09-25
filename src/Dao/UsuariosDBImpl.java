package Dao;

import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;

import Entity.Usuario;

public class UsuariosDBImpl implements IUsuario{


	private static EntityManager manager;
	private static EntityManagerFactory emf;
	
		
	@Override
	public Usuario comprobacionLogin(String usuario, String contraseña) {
		
		conexion();
		Usuario resultado = null;
		
		/**
		 * Se comprueba si existe ese usuario y contraseña
		 */
		return resultado;
	}
	
	@Override
	public boolean registrarUsuario(Usuario nuevo) {
		
		boolean encontrado = false;
		encontrado = busquedaUsuario(nuevo.getUsuario());
		boolean registrado = false;
		/**
		 * Registramos usuario
		 */
		return registrado;
	}
	
	@Override
	public boolean busquedaUsuario (String usuario) {
		conexion();
		Boolean encontrado = false;
		
		/**
		 * Busqueda usuario
		 */
		
		return encontrado;		
	}
	
	@Override
	public void conexion(){
		Logger.getLogger("org.hibernate").setLevel(Level.OFF);
		emf = Persistence.createEntityManagerFactory("Persistencia");
		manager = emf.createEntityManager();
	}



}
