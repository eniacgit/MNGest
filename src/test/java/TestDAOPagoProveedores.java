import persistencia.DAOPagoProveedores;
import valueObjects.VODetallesProducto;

public class TestDAOPagoProveedores {

	public static void main(String[] args) {
		DAOPagoProveedores dao = new DAOPagoProveedores();
		VODetallesProducto producto = dao.obtenerDetallesProducto("190124-02");
		
		System.out.println(producto.getNombre());
		System.out.println(producto.getCategoria());
		System.out.println(producto.getUnidades());
		System.out.println(producto.getFechaProduccion());
		System.out.println(producto.getMoneda());
		System.out.println(producto.getTotal());

	}

}
