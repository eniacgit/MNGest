package controladores;

import java.util.List;

import logica.Fachada;
import logica.IFachada;
import valueObjects.VOCliente;

public class ControladorMantenimientoClientes {

	public List<String> listarClientes(){
		IFachada fachada = Fachada.getInstanciaSingleton();
		return fachada.listarClientes();
	}
	
	public VOCliente obtenerDatosCliente(String nombre) {
		IFachada fachada = Fachada.getInstanciaSingleton();
		return fachada.obtenerDatosCliente(nombre);
	}
	
	public int insertarCliente(VOCliente cliente) {
		IFachada fachada = Fachada.getInstanciaSingleton();
		return fachada.insertarCliente(cliente);
	}
	
	public boolean existeCliente(String nombre) {
		IFachada fachada = Fachada.getInstanciaSingleton();
		return fachada.existeCliente(nombre);
	}
	
	public int modificarCliente(String nombre, VOCliente cliente) {
		IFachada fachada = Fachada.getInstanciaSingleton();
		return fachada.modificarCliente(nombre, cliente);
	}
	
	public List<VOCliente> listarTuplasClientes(String tipoCliente){
		IFachada fachada = Fachada.getInstanciaSingleton();
		return fachada.listarTuplasClientes(tipoCliente);
	}
}
