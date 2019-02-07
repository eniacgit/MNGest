package logica;

import java.util.List;

import javax.mail.MessagingException;

import valueObjects.VOCliente;
import valueObjects.VODetalleProveedor;
import valueObjects.VODetallesProducto;
import valueObjects.VOEmail;
import valueObjects.VOMaterial;
import valueObjects.VOProveedor;
import valueObjects.VOServicio;
import valueObjects.VOVenta;

public interface IFachada {

	List<String> listarClientes();

	// Dado el nombre de un cliente, retorna todos sus datos en un value object
	// Pre: el cliente existe
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

	List<VOVenta> listarTuplasVentas(String fechaInicio, String fechaFin);

	// Convierte una fecha con formato 'yyyy-MM-dd' al formato 'dd/MM/yyyy'
	String formatearFecha(String fecha);

	List<String> listarClientesRangoFechaProduccion(String fechaInicio, String fechaFin);

	String obtenerTipoCliente(String nombreCliente);

	VODetallesProducto obtenerDetallesProducto(String cotizacion);

	List<VODetalleProveedor> obtenerListaProveedores(String cotizacion);

	// Retorna una lista de todos los clientes (segun su tipo) que hicieron compras entre 2 fechas dadas
	List<String> listaClientesFechaEntrega(String fechaInicio, String fechaFin, String tipoCliente);

	// Retorna el correo electronico que se utiliza como remitente
	String obtenerRemitente();

	// A partir del nombre del cliente y un rango de fechas retorna la lista de productos que compro
	List<VOVenta> listarTuplasVentas(String fechaInicio, String fechaFin, String nombreCliente);

	// Envia correo electronico de gmail con archivos adjuntos, 
	// a partir de un remitente, destinatario, asunto y cuerpo de msj
	void enviarConGmail(VOEmail voEmail) throws MessagingException;

	
}
