package controladores;
import java.util.List;

import logica.Fachada;
import logica.IFachada;
import valueObjects.VOProveedor;

public class ControladorMantenimientoProveedor {
	
	public List<String> listarServicio(){
		IFachada fachada = Fachada.getInstanciaSingleton();
		return fachada.listarServicio();
	}
	
	public List<String> listarMateriales(){
		IFachada fachada = Fachada.getInstanciaSingleton();
		return fachada.listarMateriales();
	}
	
	public List<String> listarProveedores(){
		IFachada fachada = Fachada.getInstanciaSingleton();
		return fachada.listarProveedores();
	}
	
	public boolean existeProveedor(String nombre) {
		IFachada fachada = Fachada.getInstanciaSingleton();
		return fachada.existeProveedor(nombre);
	}
	
	public int insertarProveedor(VOProveedor proveedor) {
		IFachada fachada = Fachada.getInstanciaSingleton();
		return fachada.insertarProveedor(proveedor);
	}
	
	public VOProveedor obtenerDatosProveedor(String nombre) {
		IFachada fachada = Fachada.getInstanciaSingleton();
		return fachada.obtenerDatosProveedor(nombre);
	}
	
	public int modificarProveedor(String nombre, VOProveedor proveedor) {
		IFachada fachada = Fachada.getInstanciaSingleton();
		return fachada.modificarProveedor(nombre, proveedor);
	}
	
	public int agregarServicioProveedor(String proveedor, String servicio) {
		IFachada fachada = Fachada.getInstanciaSingleton();
		return fachada.agregarServicioProveedor(proveedor, servicio);
	}
	
	public List<String> obtenerListaServiciosProveedor(String proveedor){
		IFachada fachada = Fachada.getInstanciaSingleton();
		return fachada.obtenerListaServiciosProveedor(proveedor);
	}
	
	public boolean existeServicioProveedor(String proveedor, String servicio) {
		IFachada fachada = Fachada.getInstanciaSingleton();
		return fachada.existeServicioProveedor(proveedor, servicio);
	}
	
	public int eliminarServicioProveedor(String proveedor, String servicio) {
		IFachada fachada = Fachada.getInstanciaSingleton();
		return fachada.eliminarServicioProveedor(proveedor, servicio);
	}
	
	
	/// MATERIALES //////////////////////////////////
		public int agregarMaterialProveedor(String proveedor, String material) {
			IFachada fachada = Fachada.getInstanciaSingleton();
			return fachada.agregarMaterialProveedor(proveedor, material);
		}
		
		public List<String> obtenerListaMaterialesProveedor(String proveedor){
			IFachada fachada = Fachada.getInstanciaSingleton();
			return fachada.obtenerListaMaterialesProveedor(proveedor);
		}
		
		public boolean existeMaterialProveedor(String proveedor, String material) {
			IFachada fachada = Fachada.getInstanciaSingleton();
			return fachada.existeMaterialProveedor(proveedor, material);
		}
		
		public int eliminarMaterialProveedor(String proveedor, String material) {
			IFachada fachada = Fachada.getInstanciaSingleton();
			return fachada.eliminarMaterialProveedor(proveedor, material);
		}
	
	
}
