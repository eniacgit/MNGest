package logica;

import java.util.List;

import persistencia.DAOMantenimientoClientes;
import valueObjects.VOCliente;

public class Fachada implements IFachada{

	private static IFachada fachada;
	
	private Fachada(){
		
	}
	
	public static IFachada getInstanciaSingleton() {
		if (fachada == null) {
			fachada = new Fachada();
		}
		return fachada;
	}
	
	public List<String> listarClientes(){
		DAOMantenimientoClientes dao = new DAOMantenimientoClientes();
		return dao.listarClientes();
	}
	
	public VOCliente obtenerDatosCliente(String nombre) {
		DAOMantenimientoClientes dao = new DAOMantenimientoClientes();
		return dao.obtenerDatosCliente(nombre);
	}
	
	public int insertarCliente(VOCliente cliente) {
		DAOMantenimientoClientes dao = new DAOMantenimientoClientes();
		return dao.insertarCliente(cliente);
	}
	public boolean existeCliente(String nombre) {
		DAOMantenimientoClientes dao = new DAOMantenimientoClientes();
		return dao.existeCliente(nombre);
	}
	
}
