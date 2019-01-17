package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import valueObjects.VOServicio;

public class DAOMantenimientoServicio {

	public List<String> listarServicio(){
		AccesoBD accesoBD = new AccesoBD();		
		Connection con = accesoBD.conectarBD();
		Consultas consultas = new Consultas();
		
		List<String> lstClientes=null;
		
		Statement stmt=null;
		PreparedStatement pstmt=null;
		
		try {
			stmt = con.createStatement();
			String strConsulta = consultas.listarServicios();
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
	
	public VOServicio obtenerDatosServicio(String nombre) {
		AccesoBD accesoBD = new AccesoBD();		
		Connection con = accesoBD.conectarBD();
		Consultas consultas = new Consultas();
		
		VOServicio voServicio = null;
		
		PreparedStatement pstmt=null;
		
		try {
			String strConsulta = consultas.obtenerDatosServicio();
			pstmt = con.prepareStatement(strConsulta);
			pstmt.setString(1, nombre);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			voServicio = new VOServicio(nombre, rs.getString("descripcion"));
			rs.close();
		//	stmt.close();			
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
		return voServicio;		
	}
	
	public boolean existeServicio(String nombre) {
		// Retorna true si el nombre del servicio ya fue dado de alta
		AccesoBD accesoBD = new AccesoBD();
			boolean existeServicio = false;
			Connection con = accesoBD.conectarBD();
			Consultas consultas = new Consultas();
			
			String select = consultas.existeServicio();
			try {
				PreparedStatement pstmt = con.prepareStatement(select);
				pstmt.setString(1, nombre);
				
				ResultSet rs = pstmt.executeQuery();
				if (rs.next())
					existeServicio = true;
				rs.close();
				pstmt.close();
				accesoBD.desconectarBD(con);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}					
			return existeServicio;
		}
	
	public int insertarServicio(VOServicio servicio) {
		AccesoBD accesoBD = new AccesoBD();		
		Connection con = accesoBD.conectarBD();
		Consultas consultas = new Consultas();
		
		int rowCount=0;
		String insert = consultas.insertarServicio();
		PreparedStatement pstmt = null;
		
		try {
			pstmt =con.prepareStatement(insert);			
			pstmt.setString(1, servicio.getNombre());
			pstmt.setString(2, servicio.getDescripcion());			
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
	
	public int modificarServicio(String nombre, VOServicio servicio) {
		AccesoBD accesoBD = new AccesoBD();		
		Connection con = accesoBD.conectarBD();
		Consultas consultas = new Consultas();
		
		int rowCount=0;
		String update = consultas.modificarServicio();
		PreparedStatement pstmt = null;
		
		try {
			pstmt =con.prepareStatement(update);
			
			pstmt.setString(1, servicio.getNombre());
			pstmt.setString(2, servicio.getDescripcion());
			pstmt.setString(3, nombre);
			
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
