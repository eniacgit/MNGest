package controladores;

import java.util.List;

import logica.Fachada;
import logica.IFachada;
import valueObjects.VOMaterial;

public class ControladorMantenimientoMaterial {

	public List<String> listarMateriales(){
		IFachada fachada = Fachada.getInstanciaSingleton();
		return fachada.listarMateriales();
	}
	
	public VOMaterial obtenerDatosMaterial(String nombre) {
		IFachada fachada = Fachada.getInstanciaSingleton();
		return fachada.obtenerDatosMaterial(nombre);
	}
	
	public boolean existeMaterial(String nombre) {
		IFachada fachada = Fachada.getInstanciaSingleton();
		return fachada.existeMaterial(nombre);
	}
	
	public int insertarMaterial(VOMaterial material) {
		IFachada fachada = Fachada.getInstanciaSingleton();
		return fachada.insertarMaterial(material);
	}
	
	public int modificarMterial(String nombre, VOMaterial material) {
		IFachada fachada = Fachada.getInstanciaSingleton();
		return fachada.modificarMaterial(nombre, material);
	}
}
