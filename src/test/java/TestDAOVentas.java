import java.util.List;

import persistencia.DAOVenta;
import valueObjects.VOVenta;

public class TestDAOVentas {

	public static void main(String[] args) {
		DAOVenta dao = new DAOVenta();
		VOVenta venta = new VOVenta();
		
		List<VOVenta> lista = dao.listarTuplasVentas("2019-01-01","2019-01-31");
		
		for (int i=0; i < lista.size(); i++) {
			System.out.println(lista.get(i).getorden());
		}

	}

}
