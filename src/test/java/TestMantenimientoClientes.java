import java.util.Iterator;
import java.util.List;

import persistencia.DAOMantenimientoClientes;

public class TestMantenimientoClientes {

	public static void main(String[] args) {
		
		DAOMantenimientoClientes dao = new DAOMantenimientoClientes();
		
		List<String> listaClientes = dao.listarClientes();
		if (listaClientes.size() > 0) {
			Iterator<String> iterClientes = listaClientes.iterator(); 
			while (iterClientes.hasNext()) {
				String cliente = iterClientes.next();
				System.out.println(cliente);
			}
		}

	}

}
