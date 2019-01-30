package controladores;

import java.util.List;

import logica.Fachada;
import logica.IFachada;
import valueObjects.VODetalleProveedor;
import valueObjects.VODetallesProducto;

public class ControladorPagoProveedores {
	public List<String> listarClientesRangoFechaProduccion(String fechaInicio, String fechaFin){
		IFachada fachada = Fachada.getInstanciaSingleton();
		return fachada.listarClientesRangoFechaProduccion(fechaInicio, fechaFin);
	}
	
	public String obtenerTipoCliente(String nombreCliente) {
		IFachada fachada = Fachada.getInstanciaSingleton();
		return fachada.obtenerTipoCliente(nombreCliente);
	}
	
	public VODetallesProducto obtenerDetallesProducto(String cotizacion) {
		IFachada fachada = Fachada.getInstanciaSingleton();
		return fachada.obtenerDetallesProducto(cotizacion);
	}
	
	public String formatearFecha(String fecha) {
		IFachada fachada = Fachada.getInstanciaSingleton();
		return fachada.formatearFecha(fecha);
	}
	
	public List<VODetalleProveedor> obtenerListaProveedores(String cotizacion){
		IFachada fachada = Fachada.getInstanciaSingleton();
		return fachada.obtenerListaProveedores(cotizacion);
	}
}
