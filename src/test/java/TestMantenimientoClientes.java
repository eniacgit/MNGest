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

		
		/*
		System.out.println("###### UPDATE CLIENTES ############################################");
		String nombreCliente = "segsdgsdg";
		VOCliente cliente = dao.obtenerDatosCliente(nombreCliente);
		
		// datos a modificar
		cliente.setNombre("TuxSoft");
		cliente.setTelefono("22228888");
		cliente.setDireccion("Magallanes 9999");
		
		if (dao.modificarCliente(nombreCliente, cliente) == 0) {
			System.out.println("Los datos no puedieron ser modificados!.");			
		}else
			System.out.println("Datos modificados con exito!.");
	
	*/
		System.out.println("###### LISTA CLIENTES ############################################");
		List<VOCliente> listaDOClientes = dao.listarTuplasClientes("CONSUMIDOR FINAL");
		for (int i=0; i<listaDOClientes.size(); i++) {
			VOCliente cliente = listaDOClientes.get(i);
			System.out.println(cliente.toString());
		}
	
	
	}
	
	
	
	

}
