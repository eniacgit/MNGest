package controladores;

import java.util.List;

import logica.Fachada;
import logica.IFachada;
import valueObjects.VOVenta;

public class ControladorVentas {
	public List<VOVenta> listarTuplasVentas(String fechaInicio, String fechaFin){
		IFachada fachada = Fachada.getInstanciaSingleton();
		return fachada.listarTuplasVentas(fechaInicio,fechaFin);
	}
	
	public String formatearFecha(String fecha) {
		IFachada fachada = Fachada.getInstanciaSingleton();
		return fachada.formatearFecha(fecha);
	}
}
