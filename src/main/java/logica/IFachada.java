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

	// inserta un cliente en la bd a partir de todos sus datos
	// si la inserción fue exitosa retorna un valor > 0
	int insertarCliente(VOCliente cliente);

	// retorna true si el cliente existe
	boolean existeCliente(String nombre);

	// Modifica los datos de un cliente a partir de su nombre,
	// Si el ingreso fue exitoso retorna un valor >0
	int modificarCliente(String nombre, VOCliente cliente);
	
	// Retorna una lista con los datos del cliente a partir del tipo (consumidor final, o corportativo)
	List<VOCliente> listarTuplasClientes(String tipoCliente);

	// retorna una lista de servicios
	List<String> listarServicio();

	// retorna en un valueObject los datos de un servicio a partir de su nombre
	VOServicio obtenerDatosServicio(String nombre);

	// retorna true si un servicio existe a partir de su nombre
	boolean existeServicio(String nombre);
	
	// inserta un servicio
	// si la operacion fue exitosa retorna un valor >0
	int insertarServicio(VOServicio servicio);

	// Modifica los datos de un servicio a partir de su npmbre
	// Si la modificacion fue exitosa retorna un valor > 0
	int modificarServicio(String nombre, VOServicio servicio);

	// retorna una lista de materiales
	List<String> listarMateriales();

	// retorna un valueObject con todos los datos del material a partir de su nombre
	VOMaterial obtenerDatosMaterial(String nombre);

	// retorna true si un material existe a partir de su nombre
	boolean existeMaterial(String nombre);

	// inserta un material en la bd a partir de todos sus valores contenidos en un value object
	int insertarMaterial(VOMaterial material);

	// dado un nombre de material modifica sus atributos a partir de un value object
	int modificarMaterial(String nombre, VOMaterial material);

	// retorna una lista con los nombres de todos los proveedores
	List<String> listarProveedores();

	// returna true si existe el proveedor cuyo nombre es nombre
	boolean existeProveedor(String nombre);

	// inserta un proveedor en la bd
	// si la operacion es exitosa retorna un valor >0
	int insertarProveedor(VOProveedor proveedor);

	// obtiene los datos de un proveedor en un value object a partir de su nombre
	VOProveedor obtenerDatosProveedor(String nombre);

	// modifica los datos de un provedor a partir de su nombre
	// si la operacion fue exitosa retorna un valor > 0
	int modificarProveedor(String nombre, VOProveedor proveedor);

	// le asigna un servicio al proveedor
	// si la operacion es exitosa retorna un valor > 0
	int agregarServicioProveedor(String proveedor, String servicio);

	// retorna una lista con los nombres de servicios que dispone un proveedor
	List<String> obtenerListaServiciosProveedor(String proveedor);

	// retorna true si el proveedor dispone de un servicio dado
	boolean existeServicioProveedor(String proveedor, String servicio);

	// elimina un servicio de un proveedor
	// si la operacion es exitosa retorna un valor > 0
	int eliminarServicioProveedor(String proveedor, String servicio);

	// le asigna un material a un proveedor
	// si la operacion es exitosa retorna un valor > 0
	int agregarMaterialProveedor(String proveedor, String material);

	// retorna la lista de materiales de un proveedor
	List<String> obtenerListaMaterialesProveedor(String proveedor);

	// retorna true si un proveedor dispone del material ingresado
	boolean existeMaterialProveedor(String proveedor, String material);

	// elimina un material del proveedor
	// si la operacion es exitosa retorna un valor > 0
	int eliminarMaterialProveedor(String proveedor, String material);

	// lista una tupla con los datos de ventass realizadas entre 2 fechas
	List<VOVenta> listarTuplasVentas(String fechaInicio, String fechaFin);

	// Convierte una fecha con formato 'yyyy-MM-dd' al formato 'dd/MM/yyyy'
	String formatearFecha(String fecha);

	// retorna una lista de clientes entre 2 fechas dadas
	List<String> listarClientesRangoFechaProduccion(String fechaInicio, String fechaFin);

	// retorna el tipo de cliente a partir de su nombre
	String obtenerTipoCliente(String nombreCliente);

	// retorna en un value object los datos del producto a partir de su cotizacion
	VODetallesProducto obtenerDetallesProducto(String cotizacion);

	// obtiene una lista con los datos del proveedor a partir de una cotizacion
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
