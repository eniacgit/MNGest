package controladores;

import java.util.List;

import javax.mail.MessagingException;


import logica.Fachada;
import logica.IFachada;
import valueObjects.VOCliente;
import valueObjects.VOEmail;
import valueObjects.VOVenta;

public class ControladorCorreoPosVenta {
	
	public List<String> listaClientesFechaEntrega(String fechaInicio, String fechaFin, String tipoCliente){
		IFachada fachada = Fachada.getInstanciaSingleton();
		return fachada.listaClientesFechaEntrega(fechaInicio, fechaFin, tipoCliente);
	}
	
	public VOCliente obtenerDatosCliente(String nombre) {
		IFachada fachada = Fachada.getInstanciaSingleton();
		return fachada.obtenerDatosCliente(nombre);
	}
	
	public String obtenerRemitente() {
		IFachada fachada = Fachada.getInstanciaSingleton();
		return fachada.obtenerRemitente();
	}
	
	public List<VOVenta> listarTuplasVentas(String fechaInicio, String fechaFin, String nombreCliente){
		IFachada fachada = Fachada.getInstanciaSingleton();
		return fachada.listarTuplasVentas(fechaInicio, fechaFin, nombreCliente);
	}
	
	public String formatearFecha(String fecha) {
		IFachada fachada = Fachada.getInstanciaSingleton();
		return fachada.formatearFecha(fecha);
	}
	
	public void enviarConGmail (VOEmail voEmail) throws MessagingException {
		IFachada fachada = Fachada.getInstanciaSingleton();
		fachada.enviarConGmail(voEmail);
	}
}
