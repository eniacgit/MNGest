package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import valueObjects.VOVentas;

public class DAOVentas {
	
	public List<VOVentas> listarTuplasVentas(String fechaInicio, String fechaFin){
		VOVentas ventas = null;
		List<VOVentas> tuplas = null;
		AccesoBD accesoBD = new AccesoBD();		
		Connection con = accesoBD.conectarBD();
		Consultas consultas = new Consultas();
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		tuplas = new ArrayList<VOVentas>();		
		
		try {
			String listar = consultas.listarTuplasVentas();
			pstmt =con.prepareStatement(listar);
			pstmt.setString(1, fechaInicio);
			pstmt.setString(2, fechaFin);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ventas = new VOVentas();
				ventas.setorden(rs.getString("p.cotizacion"));
				ventas.setnombreCliente(rs.getString("c.nombre"));
				ventas.setTipoCliente(rs.getString("c.tipo"));
				ventas.setNombreProducto(rs.getString("pMN.nombre"));
				ventas.setCategoria(rs.getString("cat.nombre"));
				ventas.setCantidad(rs.getString("pMN.cantidad"));
				ventas.setMoneda(rs.getString("pMN.moneda"));
				ventas.setTotal(rs.getString("pMN.total"));
				ventas.setfechaProduccion(rs.getString("pMN.fechaProduccion"));
				ventas.setFechaEntrega(rs.getString("pMN.fechaEntrega"));
				tuplas.add(ventas);
			}			
					
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				rs.close();
				pstmt.close();
				accesoBD.desconectarBD(con);
			} catch (SQLException e) {
				System.out.println(">> ERROR 2");
				e.printStackTrace();
			}			
		}						
		return tuplas;
	}
}
