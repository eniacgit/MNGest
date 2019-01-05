package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class DAOMantenimientoClientes {

	public List<String> listarClientes(){
		AccesoBD accesoBD = new AccesoBD();		
		Connection con = accesoBD.conectarBD();
		Consultas consultas = new Consultas();
		
		List<String> lstClientes=null;
		
		Statement stmt=null;
		PreparedStatement pstmt=null;
		
		try {
			stmt = con.createStatement();
			String strConsulta = consultas.listarClientes();
			pstmt = con.prepareStatement(strConsulta);
			ResultSet rs = pstmt.executeQuery();
			lstClientes = new ArrayList<String>();
			while (rs.next()) {
				lstClientes.add(rs.getString("nombre"));
			}
			rs.close();
			stmt.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				pstmt.close();
				accesoBD.desconectarBD(con);
			} catch (SQLException e) {
				System.out.println(">> ERROR 2");
				e.printStackTrace();
			}			
		}
		return lstClientes;				
	}
	
}
