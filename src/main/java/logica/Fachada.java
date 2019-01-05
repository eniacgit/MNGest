package logica;

import java.util.List;

import persistencia.DAOMantenimientoClientes;

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
	
	
	
	
	
}
