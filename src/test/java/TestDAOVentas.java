import java.util.List;

import persistencia.DAOVentas;
import valueObjects.VOVentas;

public class TestDAOVentas {

	public static void main(String[] args) {
		DAOVentas dao = new DAOVentas();
		VOVentas venta = new VOVentas();
		
		List<VOVentas> lista = dao.listarTuplasVentas("2019-01-01","2019-01-31");
		
		for (int i=0; i < lista.size(); i++) {
			System.out.println(lista.get(i).getorden());
		}

	}

}
