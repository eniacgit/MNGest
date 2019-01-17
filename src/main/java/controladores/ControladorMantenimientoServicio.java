package controladores;

import java.util.List;

import logica.Fachada;
import logica.IFachada;
import valueObjects.VOServicio;

public class ControladorMantenimientoServicio {

	public List<String> listarServicio(){
		IFachada fachada = Fachada.getInstanciaSingleton();
		return fachada.listarServicio();
	}
	
	public VOServicio obtenerDatosServicio(String nombre) {
		IFachada fachada = Fachada.getInstanciaSingleton();
		return fachada.obtenerDatosServicio(nombre);
	}
	
	public boolean existeServicio(String nombre) {
		IFachada fachada = Fachada.getInstanciaSingleton();
		return fachada.existeServicio(nombre);
	}
	
	public int insertarServicio(VOServicio servicio) {
		IFachada fachada = Fachada.getInstanciaSingleton();
		return fachada.insertarServicio(servicio);
	}
	
	public int modificarServicio(String nombre, VOServicio servicio) {
		IFachada fachada = Fachada.getInstanciaSingleton();
		return fachada.modificarServicio(nombre, servicio);
	}
}
