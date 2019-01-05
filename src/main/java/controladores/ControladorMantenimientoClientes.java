package controladores;

import java.util.List;

import logica.Fachada;
import logica.IFachada;

public class ControladorMantenimientoClientes {

	public List<String> listarClientes(){
		IFachada fachada = Fachada.getInstanciaSingleton();
		return fachada.listarClientes();
	}
}
