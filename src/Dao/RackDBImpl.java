package Dao;

import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;

import Entity.Rack;

public class RackDBImpl implements IRack {

	private static EntityManager manager;
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("Persistencia");
	
	@Override
	public boolean nuevoRack(Rack nuevo) {
		
		boolean registrado = false;
		/**
		 * comprobamos si existe y si no lo creamos
		 */
		return registrado;
	}
	
	@Override
	public boolean ActualizarRack (Rack nuevoRack) {

		conexion();
		boolean comprobacion = false;
		/**
		 * si existe actualiza
		 */
		return comprobacion;
	}
	
	@Override
	public Rack BusquedaRacks(String tipo) {
		
		conexion();
		Rack miRack = null;
		
		/**
		 *  Busqueda de Rack
		 */
		
		return miRack;
	}

	@Override
	public void conexion (){
		Logger.getLogger("org.hibernate").setLevel(Level.OFF);
		
		manager = emf.createEntityManager();
	}
}
