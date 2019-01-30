package logica;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import persistencia.DAOMantenimientoClientes;
import persistencia.DAOMantenimientoMaterial;
import persistencia.DAOMantenimientoProveedor;
import persistencia.DAOMantenimientoServicio;
import persistencia.DAOPagoProveedores;
import persistencia.DAOVentas;
import valueObjects.VOCliente;
import valueObjects.VODetalleProveedor;
import valueObjects.VODetallesProducto;
import valueObjects.VOMaterial;
import valueObjects.VOProveedor;
import valueObjects.VOServicio;
import valueObjects.VOVentas;

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
	
	// VENTAS
	public List<VOVentas> listarTuplasVentas(String fechaInicio, String fechaFin){
		DAOVentas dao = new DAOVentas();
		return dao.listarTuplasVentas(fechaInicio,fechaFin);
	}
	
	public List<String> listarClientesRangoFechaProduccion(String fechaInicio, String fechaFin){
		DAOPagoProveedores dao = new DAOPagoProveedores();
		return dao.listarClientesRangoFechaProduccion(fechaInicio, fechaFin);
	}
	
	// FUNCIONES UTILES
	// Convierte una fecha con formato yyyy-MM-dd a dd/MM/yyyy
	public String formatearFecha(String fecha) {
		String fechAux="";		
		try {
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
			fechAux = sdf2.format(sdf1.parse(fecha));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fechAux;
	}
	
	public String obtenerTipoCliente(String nombreCliente) {
		DAOPagoProveedores dao = new DAOPagoProveedores();
		return dao.obtenerTipoCliente(nombreCliente);
	}
	
	public VODetallesProducto obtenerDetallesProducto(String cotizacion) {
		DAOPagoProveedores dao = new DAOPagoProveedores();
		return dao.obtenerDetallesProducto(cotizacion);
	}
	
	public List<VODetalleProveedor> obtenerListaProveedores(String cotizacion){
		DAOPagoProveedores dao = new DAOPagoProveedores();
		return dao.obtenerListaProveedores(cotizacion);
	}
}
