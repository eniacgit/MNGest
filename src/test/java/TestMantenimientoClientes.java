import java.util.Iterator;
import java.util.List;

import persistencia.DAOMantenimientoClientes;
import valueObjects.VOCliente;

public class TestMantenimientoClientes {

	public static void main(String[] args) {
		
		DAOMantenimientoClientes dao = new DAOMantenimientoClientes();
		System.out.println("###### LISTADO DE CLIENTES ############################################");
		List<String> listaClientes = dao.listarClientes();
		if (listaClientes.size() > 0) {
			Iterator<String> iterClientes = listaClientes.iterator(); 
			while (iterClientes.hasNext()) {
				String cliente = iterClientes.next();
				System.out.println(cliente);
			}
		}
		
		String nombre = "Comadreja";
		VOCliente voCliente = dao.obtenerDatosCliente(nombre);
		System.out.println(voCliente.toString());

	}

}
