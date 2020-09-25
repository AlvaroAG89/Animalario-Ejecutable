package Dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;

import Entity.Jaula;
import Entity.Rack;
import Entity.Usuario;

public class JaulaDBImpl implements IJaula{

	private static EntityManager manager;
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("Persistencia");
	
	IRack almacenRack = new RackDBImpl();
	
	@Override
	public void insertarJaula(Jaula nuevaJaula) {
		//Para JDBC
//		java.sql.Date fecha_nacimiento = convertirFecha(nuevaJaula.getFechaNacimiento());
//		java.sql.Date fecha_reserva = convertirFecha(nuevaJaula.getFecha_reserva());
		
		Rack miRack = null;
		try {
			//Primero calculamos si hay hueco en los Racks
			miRack = almacenRack.BusquedaRacks(nuevaJaula.getTipo_jaula());
			if(miRack.getStock()>0) {
				conexion();
				manager.getTransaction().begin();
				manager.persist(nuevaJaula);
				manager.getTransaction().commit();
				miRack.setStock(miRack.getStock() - 1);
				almacenRack.ActualizarRack(miRack);
				Jaula.GuardarHistorico(nuevaJaula, "Inserción");
				System.out.println("Insertado correctamente");
			}else {
				JOptionPane.showMessageDialog(null, "No hay racks del tipo '" + miRack.getTipo_caja() + "' disponibles");
			}
		}catch (Exception e) {
			System.out.println("Error en la inserción");
			//Si saltase un fallo después de la actualización de rack hacemos un rollback,
			//si factory tiene valor es que la actualización se hizo pero no la inserción, lo dejamos como estaba
			miRack.setStock(miRack.getStock() + 1);
			almacenRack.ActualizarRack(miRack);
		} finally {
			manager.close();
		}
	}
	
	//Esta funcion se usa para seleccionar una jaula ocupada, y rescatar todos los campos para modificar ese 
	//registro
	@Override
	public Jaula BusquedaCompleta(String jaula) {
		conexion();
		Jaula miJaula = null;
		/**
		 * Busqueda de todas las jaulas
		 */
		return miJaula;
	}
	
	//Rescata una lista de jaulas propias ocupadas con solo el codigo de la jaula, ya que no se necesitan
	//todas las columnas para cargar el formulario
	@Override
	public ArrayList<String> BusquedaPropios(Usuario miUsuario) {
		conexion();
		ArrayList<String> resultado = new ArrayList<String>();
		
		try {
			//En hibernate, se coloca en la Query los nombres de la clase y los atributos del xml o de la clase, 
			//no el nombre de la tabla y sus columnas
			List encontrado = new ArrayList<String>(); 
			
			if(miUsuario.getUsuario().equals("Veterinario")) {
				encontrado = manager.createQuery("FROM Jaula").getResultList();
			}else {
				encontrado = manager.createQuery("FROM Jaula WHERE laboratorio= '" + miUsuario.getLaboratorio() + "'").getResultList();
			}
			
			Jaula jaula = new Jaula();
			for (Iterator<Jaula> iterator = encontrado.iterator(); iterator.hasNext();) {
				jaula = iterator.next();
				resultado.add(jaula.getJaula());
			}
		} catch (Exception e) {
			System.out.println("Error de Transacción Listado");
		} finally {
			manager.close();
		}
		return resultado;		
	}
	
	//Busqueda de Jaulas ocupadas, solo se rescata numero de jaula, lo demas por seguridad no se rescata
	@Override
	public ArrayList<String> BusquedaOcupados(String responsable) {
		
		conexion();
		ArrayList<String> resultado = new ArrayList<String>();
		
		try {
			List<Jaula> encontrado = new ArrayList<Jaula>(); 
			encontrado = manager.createQuery("FROM Jaula WHERE laboratorio <> '" + responsable + "'").getResultList();
		
			Jaula jaula = new Jaula();
	
			for (Iterator<Jaula> iterator = encontrado.iterator(); iterator.hasNext();) {
				jaula = iterator.next();
				resultado.add(jaula.getJaula());
			}
		} catch (Exception e) {
			System.out.println("Error de Transacción Listado");
		} finally {
			manager.close();
		}
		return resultado;		
	}
	
	@Override
	public void ModificarJaula(Jaula miJaula) {
		conexion();
		boolean comprobacion = false;
		try {
			manager.getTransaction().begin();
			Jaula encontrado = manager.find(Jaula.class, miJaula.getJaula());
			if(encontrado.getTipo_jaula().equals(miJaula.getTipo_jaula())) {
				comprobacion=true;
			}
			
			if(comprobacion == false) {
				Rack antiguo = almacenRack.BusquedaRacks(encontrado.getTipo_jaula());
				Rack nuevo = almacenRack.BusquedaRacks(miJaula.getTipo_jaula());
				antiguo.setStock(antiguo.getStock()+1);
				nuevo.setStock(nuevo.getStock()-1);
				if(nuevo.getStock()<0) {
						JOptionPane.showMessageDialog(null, "No hay espacio en ese Rack, selecciona otro");
					return ;
				}else {
					almacenRack.ActualizarRack(nuevo);
					almacenRack.ActualizarRack(antiguo);
					//hay que hacer la conexion otra vez porque el metodo actualizar rack, nos cierra el manager
					conexion();
					manager.getTransaction().begin();
					encontrado = manager.find(Jaula.class, miJaula.getJaula());
				}
			}

			encontrado.setNombres(miJaula.getNombres());
			encontrado.setLaboratorio(miJaula.getLaboratorio());
			encontrado.setCepa(miJaula.getCepa());
			encontrado.setEspecie(miJaula.getEspecie());
			encontrado.setFechaNacimiento(miJaula.getFechaNacimiento());
			encontrado.setNumRatasHembras(miJaula.getNumRatasHembras());
			encontrado.setNumRatasMacho(miJaula.getNumRatasMacho());
			encontrado.setResponsable(miJaula.getResponsable());
			encontrado.setProcedimiento(miJaula.getProcedimiento());
			encontrado.setObservaciones(miJaula.getObservaciones());
			encontrado.setTipo_jaula(miJaula.getTipo_jaula());
			manager.getTransaction().commit();
			
	
		} catch (Exception e) {
			System.out.println("Error de Transacción Actualización");
		} finally {
			manager.close();
		}
	}
	
	@Override
	public void BorrarJaula (ArrayList<String> jaulas) {
	
		int borradas=0;

		int op = JOptionPane.showConfirmDialog(null, "¿Realmente desea borrar las jaulas seleccionadas?");
		if(op==0) {
			boolean cambio = false;
			Rack miRack = null;
			try {
				for(int i = 0; i<jaulas.size(); i++) {
					//Antes de borrar almaceno en un objeto los datos para que si se borra se pueda almacenar en un historico los datos
					Jaula borrar = BusquedaCompleta(jaulas.get(i));
					try {

						miRack = almacenRack.BusquedaRacks(borrar.getTipo_jaula());
						miRack.setStock(miRack.getStock() + 1);
						cambio = almacenRack.ActualizarRack(miRack);
						conexion();
						manager.getTransaction().begin();
						borrar = manager.merge(borrar);
						manager.remove(borrar);
						manager.getTransaction().commit();
						borradas++;
						Jaula.GuardarHistorico(borrar, "Liberacion");
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, "Error al eliminar jaula");
						manager.getTransaction().rollback();
						//Si se hizo la actualizacion del rack, pero no se elimino la jaula, deshacemos el cambio
						if(cambio == true) {
							miRack.setStock(miRack.getStock() + 1);
							almacenRack.ActualizarRack(miRack);
						}
					}
				}
				JOptionPane.showMessageDialog(null, "Se han eliminado " + borradas + " jaulas");
			}catch(Exception e) {
				JOptionPane.showMessageDialog(null, "Error en actualizacion de datos");
			}finally {
				manager.close();
			}
		}else {
			JOptionPane.showMessageDialog(null, "Se ha cancelado el borrado");
		}
	}
	
	@Override
	public void conexion (){
		Logger.getLogger("org.hibernate").setLevel(Level.OFF);
		
		manager = emf.createEntityManager();
	}
	
}
