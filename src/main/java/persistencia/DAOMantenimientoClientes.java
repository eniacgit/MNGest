package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import valueObjects.VOCliente;


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
	
	public VOCliente obtenerDatosCliente(String nombre) {
		AccesoBD accesoBD = new AccesoBD();		
		Connection con = accesoBD.conectarBD();
		Consultas consultas = new Consultas();
		
		VOCliente voCliente = null;
		
		Statement stmt=null;
		PreparedStatement pstmt=null;
		
		try {
			stmt = con.createStatement();
			String strConsulta = consultas.obtenerDatosCliente();
			pstmt = con.prepareStatement(strConsulta);
			pstmt.setString(1, nombre);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			voCliente = new VOCliente(nombre, rs.getString("email"), rs.getString("telefono"), rs.getString("celular"), rs.getString("rut"), rs.getString("razonSocial"), rs.getString("tipo"), rs.getString("direccion"));
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
		return voCliente;		
	}
	
	public boolean existeCliente(String nombre) {
		// Retorna true si el nombre del cliente ya fue dado de alta
		AccesoBD accesoBD = new AccesoBD();
			boolean existeCliente = false;
			Connection con = con = accesoBD.conectarBD();
			Consultas consultas = new Consultas();
			
			String select = consultas.existeCliente();
			try {
				PreparedStatement pstmt = con.prepareStatement(select);
				pstmt.setString(1, nombre);
				
				ResultSet rs = pstmt.executeQuery();
				if (rs.next())
					existeCliente = true;
				rs.close();
				pstmt.close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
			accesoBD.desconectarBD(con);
			return existeCliente;
		}
	
	public int insertarCliente(VOCliente cliente) {
		AccesoBD accesoBD = new AccesoBD();		
		Connection con = accesoBD.conectarBD();
		Consultas consultas = new Consultas();
		
		int rowCount=0;
		String insert = consultas.insertarCliente();
		PreparedStatement pstmt = null;
		
		try {
			pstmt =con.prepareStatement(insert);
			
			pstmt.setString(1, cliente.getNombre());
			pstmt.setString(2, cliente.getEmail());
			pstmt.setString(3, cliente.getTelefono());
			pstmt.setString(4, cliente.getCelular());
			pstmt.setString(5, cliente.getRut());
			pstmt.setString(6, cliente.getRazonSocial());
			pstmt.setString(7, cliente.getTipo());
			pstmt.setString(8, cliente.getDireccion());		
			
			rowCount = pstmt.executeUpdate();			
		} catch (SQLException e) {
			System.out.println(">> Tipo de datos incorrectos");
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
		return rowCount;
	}
	
}
