package logica;

import java.util.List;

import valueObjects.VOCliente;

public interface IFachada {

	List<String> listarClientes();

	VOCliente obtenerDatosCliente(String nombre);

	int insertarCliente(VOCliente cliente);

	boolean existeCliente(String nombre);

	
}
