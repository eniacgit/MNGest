package controladores;

import java.util.List;

import logica.Fachada;
import logica.IFachada;
import valueObjects.VOVentas;

public class ControladorVentas {
	public List<VOVentas> listarTuplasVentas(String fechaInicio, String fechaFin){
		IFachada fachada = Fachada.getInstanciaSingleton();
		return fachada.listarTuplasVentas(fechaInicio,fechaFin);
	}
	
	public String formatearFecha(String fecha) {
		IFachada fachada = Fachada.getInstanciaSingleton();
		return fachada.formatearFecha(fecha);
	}
}
