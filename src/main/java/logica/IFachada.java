package logica;

import java.util.List;

import valueObjects.VOCliente;
import valueObjects.VOMaterial;
import valueObjects.VOProveedor;
import valueObjects.VOServicio;

public interface IFachada {

	List<String> listarClientes();

	VOCliente obtenerDatosCliente(String nombre);

	int insertarCliente(VOCliente cliente);

	boolean existeCliente(String nombre);

	int modificarCliente(String nombre, VOCliente cliente);
	
	List<VOCliente> listarTuplasClientes(String tipoCliente);

	List<String> listarServicio();

	VOServicio obtenerDatosServicio(String nombre);

	boolean existeServicio(String nombre);

	int insertarServicio(VOServicio servicio);

	int modificarServicio(String nombre, VOServicio servicio);

	List<String> listarMateriales();

	VOMaterial obtenerDatosMaterial(String nombre);

	boolean existeMaterial(String nombre);

	int insertarMaterial(VOMaterial material);

	int modificarMaterial(String nombre, VOMaterial material);

	List<String> listarProveedores();

	boolean existeProveedor(String nombre);

	int insertarProveedor(VOProveedor proveedor);

	VOProveedor obtenerDatosProveedor(String nombre);

	int modificarProveedor(String nombre, VOProveedor proveedor);

	int agregarServicioProveedor(String proveedor, String servicio);

	List<String> obtenerListaServiciosProveedor(String proveedor);

	boolean existeServicioProveedor(String proveedor, String servicio);

	int eliminarServicioProveedor(String proveedor, String servicio);

	int agregarMaterialProveedor(String proveedor, String material);

	List<String> obtenerListaMaterialesProveedor(String proveedor);

	boolean existeMaterialProveedor(String proveedor, String material);

	int eliminarMaterialProveedor(String proveedor, String material);

	
}
