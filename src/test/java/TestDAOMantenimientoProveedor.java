import java.util.List;

import org.springframework.beans.factory.parsing.Problem;

import persistencia.DAOMantenimientoProveedor;
import valueObjects.VOProveedor;

public class TestDAOMantenimientoProveedor {

	public static void main(String[] args) {
		DAOMantenimientoProveedor dao = new DAOMantenimientoProveedor();
		/*VOProveedor proveedor = new VOProveedor("Juan", "Amazonas 1234","2136549", "http://myweb.com", "2019-01-14");
		
		if (dao.insertarProveedor(proveedor) != 0) {
			System.out.println("Proveedor insertado con exito!");
		}else
			System.out.println("Error al insertar proveedor!");*/
		
		/*VOProveedor proveedor = dao.obtenerDatosProveedor("Juan");
		System.out.println(proveedor.getNombre());
		System.out.println(proveedor.getFechaIngreso());*/
		
		/*String proveedor ="Aluminios s.a";
		String servicio = "HERRERIA";
		
		if (dao.agregarServicioProveedor(proveedor, servicio) !=0) {
			System.out.println("Servicio agregado con exito!");
		}else
			System.out.println("Error al agregar servicio!");
		*/
		
		/*
		List<String> listaServicios = dao.obtenerListaServiciosProveedor("Juan Construye");
		for (int i=0; i<listaServicios.size(); i++) {
			System.out.println(listaServicios.get(i));
		}*/
		
		String proveedor = "Aluminios s.a";
		//String servicio = "CARPINTERIA";
		String servicio = "HERRERIA";
		
		if (dao.existeServicioProveedor(proveedor, servicio)) {
			System.out.println("El proveedor tiene asignado el servicio");
		}else
			System.out.println("El proveedor NO tiene asignado el servicio");
		

	}

}
