package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import valueObjects.VOMaterial;

public class DAOMantenimientoMaterial {

	public List<String> listarMateriales(){
		AccesoBD accesoBD = new AccesoBD();		
		Connection con = accesoBD.conectarBD();
		Consultas consultas = new Consultas();
		
		List<String> lstClientes=null;
		
		Statement stmt=null;
		PreparedStatement pstmt=null;
		
		try {
			stmt = con.createStatement();
			String strConsulta = consultas.listarMateriales();
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
	
	public VOMaterial obtenerDatosMaterial(String nombre) {
		AccesoBD accesoBD = new AccesoBD();		
		Connection con = accesoBD.conectarBD();
		Consultas consultas = new Consultas();
		
		VOMaterial voMaterial = null;
		
		PreparedStatement pstmt=null;
		
		try {
			String strConsulta = consultas.obtenerDatosMaterial();
			pstmt = con.prepareStatement(strConsulta);
			pstmt.setString(1, nombre);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			voMaterial = new VOMaterial(nombre, rs.getString("descripcion"));
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
		return voMaterial;		
	}
	
	public boolean existeMaterial(String nombre) {
		// Retorna true si el nombre del material ya fue dado de alta
		AccesoBD accesoBD = new AccesoBD();
			boolean existeMaterial = false;
			Connection con = accesoBD.conectarBD();
			Consultas consultas = new Consultas();
			
			String select = consultas.existeMaterial();
			try {
				PreparedStatement pstmt = con.prepareStatement(select);
				pstmt.setString(1, nombre);
				
				ResultSet rs = pstmt.executeQuery();
				if (rs.next())
					existeMaterial = true;
				rs.close();
				pstmt.close();
				accesoBD.desconectarBD(con);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}					
			return existeMaterial;
		}
	
	public int insertarMaterial(VOMaterial material) {
		AccesoBD accesoBD = new AccesoBD();		
		Connection con = accesoBD.conectarBD();
		Consultas consultas = new Consultas();
		
		int rowCount=0;
		String insert = consultas.insertarMaterial();
		PreparedStatement pstmt = null;
		
		try {
			pstmt =con.prepareStatement(insert);			
			pstmt.setString(1, material.getNombre());
			pstmt.setString(2, material.getDescripcion());			
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
	
	public int modificarMaterial(String nombre, VOMaterial material) {
		AccesoBD accesoBD = new AccesoBD();		
		Connection con = accesoBD.conectarBD();
		Consultas consultas = new Consultas();
		
		int rowCount=0;
		String update = consultas.modificarMaterial();
		PreparedStatement pstmt = null;
		
		try {
			pstmt =con.prepareStatement(update);
			
			pstmt.setString(1, material.getNombre());
			pstmt.setString(2, material.getDescripcion());
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
