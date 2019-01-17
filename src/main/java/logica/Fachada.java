package logica;

import java.util.List;

import persistencia.DAOMantenimientoClientes;
import persistencia.DAOMantenimientoMaterial;
import persistencia.DAOMantenimientoProveedor;
import persistencia.DAOMantenimientoServicio;
import valueObjects.VOCliente;
import valueObjects.VOMaterial;
import valueObjects.VOProveedor;
import valueObjects.VOServicio;

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
	
	public int modificarCliente(String nombre, VOCliente cliente) {
		DAOMantenimientoClientes dao = new DAOMantenimientoClientes();
		return dao.modificarCliente(nombre, cliente);
	}
	
	public List<VOCliente> listarTuplasClientes(String tipoCliente){
		DAOMantenimientoClientes dao = new DAOMantenimientoClientes();
		return dao.listarTuplasClientes(tipoCliente);
	}
	
	//Mantenimiento de servicios

	public List<String> listarServicio(){
		DAOMantenimientoServicio dao = new DAOMantenimientoServicio();
		return dao.listarServicio();
	}
	
	public VOServicio obtenerDatosServicio(String nombre) {
		DAOMantenimientoServicio dao = new DAOMantenimientoServicio();
		return dao.obtenerDatosServicio(nombre);
	}
	
	public boolean existeServicio(String nombre) {
		DAOMantenimientoServicio dao = new DAOMantenimientoServicio();
		return dao.existeServicio(nombre);
	}

	public int insertarServicio(VOServicio servicio) {
		DAOMantenimientoServicio dao = new DAOMantenimientoServicio();
		return dao.insertarServicio(servicio);
	}

	public int modificarServicio(String nombre, VOServicio servicio) {
		DAOMantenimientoServicio dao = new DAOMantenimientoServicio();
		return dao.modificarServicio(nombre, servicio);
	}
	
	//Mantenimiento de materiales
		
	public List<String> listarMateriales(){
		DAOMantenimientoMaterial dao = new DAOMantenimientoMaterial();
		return dao.listarMateriales();
	}
	
	public VOMaterial obtenerDatosMaterial(String nombre) {
		DAOMantenimientoMaterial dao = new DAOMantenimientoMaterial();
		return dao.obtenerDatosMaterial(nombre);
	}
	
	public boolean existeMaterial(String nombre) {
		DAOMantenimientoMaterial dao = new DAOMantenimientoMaterial();
		return dao.existeMaterial(nombre);
	}
	
	public int insertarMaterial(VOMaterial material) {
		DAOMantenimientoMaterial dao = new DAOMantenimientoMaterial();
		return dao.insertarMaterial(material);
	}
	
	public int modificarMaterial(String nombre, VOMaterial material) {
		DAOMantenimientoMaterial dao = new DAOMantenimientoMaterial();
		return dao.modificarMaterial(nombre, material);
	}
	
	// Mantenimiento de proveedores
	
	public List<String> listarProveedores(){
		DAOMantenimientoProveedor dao = new DAOMantenimientoProveedor();
		return dao.listarProveedores();
	}
	
	public boolean existeProveedor(String nombre) {
		DAOMantenimientoProveedor dao = new DAOMantenimientoProveedor();
		return dao.existeProveedor(nombre);
	}
	
	public int insertarProveedor(VOProveedor proveedor) {
		DAOMantenimientoProveedor dao = new DAOMantenimientoProveedor();
		return dao.insertarProveedor(proveedor);
	}
	
	public VOProveedor obtenerDatosProveedor(String nombre) {
		DAOMantenimientoProveedor dao = new DAOMantenimientoProveedor();
		return dao.obtenerDatosProveedor(nombre);
	}
	
	public int modificarProveedor(String nombre, VOProveedor proveedor) {
		DAOMantenimientoProveedor dao = new DAOMantenimientoProveedor();
		return dao.modificarProveedor(nombre, proveedor);
	}	
	
	public int agregarServicioProveedor(String proveedor, String servicio) {
		DAOMantenimientoProveedor dao = new DAOMantenimientoProveedor();
		return dao.agregarServicioProveedor(proveedor, servicio);
	}
	
	public List<String> obtenerListaServiciosProveedor(String proveedor){
		DAOMantenimientoProveedor dao = new DAOMantenimientoProveedor();
		return dao.obtenerListaServiciosProveedor(proveedor);
	}
	public boolean existeServicioProveedor(String proveedor, String servicio) {
		DAOMantenimientoProveedor dao = new DAOMantenimientoProveedor();
		return dao.existeServicioProveedor(proveedor, servicio);
	}
	
	public int eliminarServicioProveedor(String proveedor, String servicio) {
		DAOMantenimientoProveedor dao = new DAOMantenimientoProveedor();
		return dao.eliminarServicioProveedor(proveedor, servicio);
	}
	
	
	/// MATERIALES //////////////////////////////////
	public int agregarMaterialProveedor(String proveedor, String material) {
		DAOMantenimientoProveedor dao = new DAOMantenimientoProveedor();
		return dao.agregarMaterialProveedor(proveedor, material);
	}
	
	public List<String> obtenerListaMaterialesProveedor(String proveedor){
		DAOMantenimientoProveedor dao = new DAOMantenimientoProveedor();
		return dao.obtenerListaMaterialesProveedor(proveedor);
	}
	
	public boolean existeMaterialProveedor(String proveedor, String material) {
		DAOMantenimientoProveedor dao = new DAOMantenimientoProveedor();
		return dao.existeMaterialProveedor(proveedor, material);
	}
	
	public int eliminarMaterialProveedor(String proveedor, String material) {
		DAOMantenimientoProveedor dao = new DAOMantenimientoProveedor();
		return dao.eliminarMaterialProveedor(proveedor, material);
	}
}
