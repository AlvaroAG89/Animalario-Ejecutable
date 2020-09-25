package Dao;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;

import Entity.Aviso;

public class AvisoDBImpl implements IAviso{

	IUsuario usuario;
	
	private static EntityManager manager;
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("Persistencia");
	
	@Override
	public void insertar(Aviso nuevoAviso) {
		usuario = new UsuariosDBImpl();
		try {
			boolean encontrado = false;
			encontrado = usuario.busquedaUsuario(nuevoAviso.getDestinatario());
			if(encontrado==false) {
				JOptionPane.showMessageDialog(null, "El usuario " + nuevoAviso.getDestinatario() + " no existe");
				return;
			}
			conexion();
			
			manager.getTransaction().begin();
			manager.persist(nuevoAviso);
			manager.getTransaction().commit();

			JOptionPane.showMessageDialog(null, "Mensaje Enviado");


		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error al enviar mensaje");
		}finally {
			manager.close();
		}
		
	}

	@Override
	public void BorrarMensaje(Aviso borrado) {
		conexion();
		
		/**
		 * Borra mensaje
		 */
		
	}

	@Override
	public void VerAviso(Aviso miAviso) {
		conexion();
		try {	
			manager.getTransaction().begin();
			Aviso encontrado = manager.find(Aviso.class, miAviso.getId());
			encontrado.setLeido(true);
			manager.getTransaction().commit();

		} catch (Exception e) {
			System.out.println("Error de Transacción Actualización");
		} finally {
			manager.close();
		}
		
	}

	@Override
	public List<String> cargaContactos() {
		conexion();
		List<String> encontrado = new ArrayList<String>();

		/**
		 * Carga una lista de todos los usuarios registrados
		 */
		
		return encontrado;
	}

	@Override
	public void conexion() {
		Logger.getLogger("org.hibernate").setLevel(Level.OFF);
		
		manager = emf.createEntityManager();
		
	}

}
